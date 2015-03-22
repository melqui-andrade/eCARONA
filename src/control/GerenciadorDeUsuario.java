package control;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import utilitarios.MensagensDeErro;
import utilitarios.ValidadorDeStrings;
import model.Usuario;

public class GerenciadorDeUsuario {

	public Map<String, Usuario> usuarioBD;
	public Usuario usuarioBuffer;

	public GerenciadorDeUsuario() {
		usuarioBD = new HashMap<String, Usuario>();
	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {
		
		Usuario usuario = new Usuario();

		usuario.setLogin(login);

		if (usuarioBD.containsKey(login)) {
			throw new Exception(MensagensDeErro.EXISTE_USUARIO_C_LOGIN);
		}

		usuario.setSenha(senha);

		usuario.setNome(nome);

		usuario.setEndereco(endereco);

		usuario.setEmail(email);

		for (Usuario u : usuarioBD.values()) {
			if (u.getEmail().equals(email)) {
				throw new Exception(MensagensDeErro.EXISTE_USUARIO_C_EMAIL);
			}
		}

		usuarioBD.put(login, usuario);

	}

	public int abrirSessao(String login, String senha) throws Exception {

		Usuario usuario = usuarioBD.get(login);

		if (login == null || senha == null)
			throw new Exception(MensagensDeErro.LOGIN_INVALIDO);

		if (login.equals("") || senha.equals(""))
			throw new Exception(MensagensDeErro.LOGIN_INVALIDO);

		if (usuario == null)
			throw new Exception(MensagensDeErro.USUARIO_INEXISTENTE);

		if (!usuario.getSenha().equals(senha))
			throw new Exception(MensagensDeErro.LOGIN_INVALIDO);

		Date time = new Date();
		int idSessao = (int) time.getTime();
		return idSessao;
	}

	public String getAtributoUsuario(String login, String atributo)
			throws Exception {

		Usuario usuario;

		if (login == null || login.equals(""))
			throw new Exception(MensagensDeErro.LOGIN_INVALIDO);

		if (usuarioBD.containsKey(login)) {
			usuario = usuarioBD.get(login);

			if (atributo == "" || atributo == null)
				throw new Exception(MensagensDeErro.ATRIBUTO_INVALIDO);

			switch (atributo) {

			case "":
				throw new Exception(MensagensDeErro.ATRIBUTO_INVALIDO);

			case "nome":
				return usuario.getNome();

			case "endereco":
				return usuario.getEndereco();

			case "email":
				return usuario.getEmail();

			default:
				throw new Exception(MensagensDeErro.ATRIBUTO_INEXISTENTE);

			}
		} else {

			throw new Exception(MensagensDeErro.USUARIO_INEXISTENTE);
		}

	}

	public boolean verificaLoginExistente(String login) {
		// verifica se existe no BD o login informado
		Usuario usuario = usuarioBD.get(login);
		if (usuario != null) {
			return true;
		} else {
			return false;

		}

	}
}
