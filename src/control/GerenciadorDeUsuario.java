package control;

import java.util.HashMap;
import java.util.Map;

import model.Usuario;

public class GerenciadorDeUsuario {

	public Map<String, Usuario> usuarioBD;
	public Usuario usuarioBuffer;
	private ValidadorDeStrings validarString;

	public GerenciadorDeUsuario() {
		usuarioBD = new HashMap<String, Usuario>();
		validarString = new ValidadorDeStrings();
	}

	public void criarUsuario(String login, String senha, String nome,	String endereco, String email) throws Exception {
		Usuario usuario = new Usuario();

		if (validarString.validarLogin(login)) {
			usuario.setLogin(login);
		} else {
			throw new Exception(MensagensDeErro.LOGIN_INVALIDO);

		}

		if (validarString.validarSenha(senha)) {
			usuario.setSenha(senha);
		} else {
			throw new Exception(MensagensDeErro.SENHA_INVALIDA);

		}

		if (nome == null || nome.equals("") || nome.equals(" ")) {
			throw new Exception(MensagensDeErro.NOME_INVALIDO);

		}
		usuario.setNome(nome);

		if (endereco == null || endereco.equals("") || endereco.equals(" ")) {
			throw new Exception(MensagensDeErro.ENDERECO_INVALIDO);

		}
		usuario.setEndereco(endereco);

		if (validarString.validarEmail(email)) {
			usuario.setEmail(email);
		} else {
			throw new Exception(MensagensDeErro.EMAIL_INVALIDO);
		}
		
		
		try {
			usuarioBD.put(login, usuario);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(
					MensagensDeErro.EXISTE_USUARIO_C_EMAIL);
		}

	}
}
