package com.br.uepb.business;


import servicesBackup.PersistenciaDAO;

import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.UsuarioDomain;
import com.br.uepb.persistencia.Persistencia;

/**
 * Gerenciador de usuario Classe responsávél por cadastrar e editar objetos do
 * tipo UsuarioDomain, gerenciar sessões do usuário, manipular caronas
 * 
 * @author Melqui
 * @see UsuarioDomain
 *
 */
public class UsuarioBusiness {

	private PersistenciaDAO persistencia;
	private Persistencia persistenciaBD;

	public UsuarioBusiness(PersistenciaDAO persistencia) {
		this.persistencia = persistencia;
		this.persistenciaBD = new Persistencia();
	}

	/**
	 * Cria um novo usuário
	 * 
	 * @param login
	 *            login do usuário
	 * @param senha
	 *            senha
	 * @param nome
	 *            nome
	 * @param endereco
	 *            rua, número e bairro
	 * @param email
	 *            endereço de email
	 * @throws Exception
	 *             caso algum dos atributos sejam inválidos (campos vazios,
	 *             formato de email incorreto) ou já exista um usuário com mesmo
	 *             login ou email
	 */
	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws ECaronaException {

		UsuarioDomain usuario = new UsuarioDomain();

		usuario.setLogin(login);

		if (persistencia.getUsuarioBD().containsKey(login)) {
			throw new ECaronaException(MensagensDeErro.EXISTE_USUARIO_C_LOGIN);
		}

		usuario.setSenha(senha);

		usuario.setNome(nome);

		usuario.setEndereco(endereco);

		usuario.setEmail(email);

		for (UsuarioDomain u : persistencia.getUsuarioBD().values()) {
			if (u.getEmail().equals(email)) {
				throw new ECaronaException(
						MensagensDeErro.EXISTE_USUARIO_C_EMAIL);
			}
		}

		persistencia.getUsuarioBD().put(login, usuario);
		persistenciaBD.getUsuarioBD().save(usuario);

	}

	/**
	 * Pegar um atributo específico de um usuário
	 * 
	 * @param login
	 *            login do usuário
	 * @param atributo
	 *            nome | endereço | email
	 * @return O valor do atributo especificado
	 * @throws Exception
	 *             caso o usuário não exista na base ou o atributo não seja
	 *             válido
	 */
	public String getAtributoUsuario(String login, String atributo)
			throws ECaronaException {

		UsuarioDomain usuario;

		if (login == null || login.equals(""))
			throw new ECaronaException(MensagensDeErro.LOGIN_INVALIDO);

		if (persistencia.getUsuarioBD().containsKey(login)) {
			usuario = persistencia.getUsuarioBD().get(login);

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
	 * @param login
	 * @return true, caso exista
	 */
	public boolean verificaLoginExistente(String login) {
		// verifica se existe no BD o login informado
		UsuarioDomain usuario = persistencia.getUsuarioBD().get(login);
		if (usuario != null) {
			return true;
		} else {
			return false;

		}

	}

	

}
