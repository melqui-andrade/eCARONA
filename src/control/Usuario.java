package control;

import java.util.Map;

public class Usuario {

	private String login;
	private String senha;
	private String nome;
	private String endereco;
	private String email;

	public Map<String, Usuario> usuarioBD;
	public Usuario usuarioBuffer;

	private StringValidator strValidator;

	public Usuario() {
		strValidator = new StringValidator();
	}

	/********************* GETTERS and SETTERS ********************/
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @throws Exception
	 **************************************************************/

	public boolean criarUsuario(String login, String senha, String nome,
			String endereco, String email) throws Exception {
		Usuario novoUsuario = new Usuario();

		// System.out.println(login + " " + senha + " " + nome + " " + endereco
		// + " " + email);
		if (strValidator.validateUsername(login)) {
			novoUsuario.setLogin(login);
		} else {
			throw new Exception(ErrorMessenger.LOGIN_INVALIDO);
			// Can i do this?

		}

		if (strValidator.validatePassword(senha)) {
			novoUsuario.setSenha(senha);
		} else {
			throw new Exception(ErrorMessenger.SENHA_INVALIDA);

		}

		if (nome == null || nome.equals("") || nome.equals(" ")) {
			throw new Exception(ErrorMessenger.NOME_INVALIDO);

		}
		novoUsuario.setNome(nome);

		if (endereco == null || endereco.equals("") || endereco.equals(" ")) {
			throw new Exception(ErrorMessenger.ENDERECO_INVALIDO);

		}
		novoUsuario.setEndereco(endereco);

		if (strValidator.validateEmail(email)) {
			novoUsuario.setEmail(email);
		} else {
			throw new Exception(ErrorMessenger.EMAIL_INVALIDO);
		}

		//usuarioBD.put(login, novoUsuario);
		return true;
	}

	public void abrirSessao(String login, String senha) {
		usuarioBuffer = usuarioBD.get(login);

		if (usuarioBuffer.login == login && usuarioBuffer.senha == senha) {
			// RETORNAR ID DA SESSAO
		}

	}

	public void zerarSistema() {
		setLogin(null);
		setSenha(null);
		setNome(null);
		setEndereco(null);
		setEmail(null);

		usuarioBD = null;
		usuarioBuffer = new Usuario();
	}
}
