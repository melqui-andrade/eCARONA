package com.br.uepb.business;

import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.UsuarioDomain;
import com.br.uepb.persistencia.Persistencia;

/**
 * Implementação das regras de negócio de UsuarioDomain
 * Todas as ações relacionadas ao gerenciamento de usuários se encontram nessa classe
 * 
 * @see UsuarioDomain
 *
 */
public class UsuarioBusiness {

	
	private Persistencia persistenciaBD;

	/**
	 * Construtor da classe, inicializa a entidade de persistência
	 */
	public UsuarioBusiness() {
		
		this.persistenciaBD = new Persistencia();
	}

	/**
	 * Criar um novo usuário
	 * 
	 * @param login Login do usuário
	 * @param senha senha
	 * @param nome  Nome do usuário 
	 * @param endereco Rua, número e bairro
	 * @param email Endereço de email
	 * @throws Exception
	 *             caso algum dos atributos sejam inválidos (campos vazios,
	 *             formato de email incorreto) ou já exista um usuário com mesmo
	 *             login ou email
	 */
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws ECaronaException {

		UsuarioDomain usuario = new UsuarioDomain();

		usuario.setLogin(login);

		if (persistenciaBD.getUsuarioBD().jaCadastrado(login)) {
			throw new ECaronaException(MensagensDeErro.EXISTE_USUARIO_C_LOGIN);
		}

		usuario.setSenha(senha);

		usuario.setNome(nome);

		usuario.setEndereco(endereco);

		usuario.setEmail(email);

		for (UsuarioDomain u : persistenciaBD.getUsuarioBD().list()) {
			if (u.getEmail().equals(email)) {
				throw new ECaronaException(
						MensagensDeErro.EXISTE_USUARIO_C_EMAIL);
			}
		}
		persistenciaBD.getUsuarioBD().save(usuario);

	}

	/**
	 * Pegar um atributo específico de um usuário
	 * 
	 * @param login Login do usuário
	 * @param atributo Atributos válidos: nome | endereço | email
	 * @return O valor do atributo especificado
	 * @throws Exception caso o usuário não exista na base ou o atributo não seja válido
	 */
	public String getAtributoUsuario(String login, String atributo)
			throws ECaronaException {

		UsuarioDomain usuario;

		if (login == null || login.equals(""))
			throw new ECaronaException(MensagensDeErro.LOGIN_INVALIDO);

		if (persistenciaBD.getUsuarioBD().jaCadastrado(login)) {
			usuario = persistenciaBD.getUsuarioBD().getUsuario(login);

			if (atributo == "" || atributo == null)
				throw new ECaronaException(MensagensDeErro.ATRIBUTO_INVALIDO);

			switch (atributo) {

			case "":
				throw new ECaronaException(MensagensDeErro.ATRIBUTO_INVALIDO);

			case "nome":
				return usuario.getNome();

			case "endereco":
				return usuario.getEndereco();

			case "email":
				return usuario.getEmail();

			default:
				throw new ECaronaException(MensagensDeErro.ATRIBUTO_INEXISTENTE);

			}
		} else {

			throw new ECaronaException(MensagensDeErro.USUARIO_INEXISTENTE);
		}

	}

	/**
	 * Verifica se um dado login existe na base de usuários
	 * 
	 * @param login Identificador do usuário
	 * @return True, caso exista
	 */
	public boolean verificaLoginExistente(String login) {
		// verifica se existe no BD o login informado
		
		return persistenciaBD.getUsuarioBD().jaCadastrado(login);

	}
	
	/**
	 * Limpar todos os registros de Usuario. Como usuário é o elemento raiz de Carona, quando deletado
	 * todos os registros de carona são deletados em cascata
	 */
	public void zerarBase(){
		persistenciaBD.getUsuarioBD().excluirTudo();
	}

	

}
