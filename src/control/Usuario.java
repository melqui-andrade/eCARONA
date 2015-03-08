package control;

public class Usuario {

	private String login;
	private String senha;
	private String nome;
	private String endereco;
	private String email;

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

	/****************************************************************/

	public boolean criarUsuario(String login, String senha, String nome,
			String endereco, String email) {
		Usuario novoUsuario = new Usuario();

		if (strValidator.validateUsername(login)) {
			novoUsuario.setLogin(login);
			System.out.println("Login inválido");
		} else {
			return false;
		}

		novoUsuario.setLogin(login);
		if (!strValidator.validatePassword(senha)) {
			System.out.println("Senha inválida");
			return false;
		}
		novoUsuario.setSenha(senha);

		if (nome.equals(null) || nome.equals("") || nome.equals(" ")) {
			System.out.println("Nome inválido");
			return false;
		}
		novoUsuario.setNome(nome);

		if (endereco.equals(null) || endereco.equals("")
				|| endereco.equals(" ")) {
			System.out.println("Endereço inválido");
			return false;
		}
		novoUsuario.setEndereco(endereco);

		if (!strValidator.validateEmail(email)) {
			System.out.println("Email inválido");
			return false;
		}
		novoUsuario.setEmail(email);

		return true;
	}

}
