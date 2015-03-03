package Control;

public class Usuario {

	private String login;
	private String senha;
	private String nome;
	private String endereco;
	private String email;

	public Usuario() {
		// TODO Auto-generated constructor stub
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

		if (stringIniciaComNumero(login)) {
			// System.out.println("Login inválido");
			return false;
		}
		novoUsuario.setLogin(login);
		if (senha.equals(null) || senha.equals("") || senha.equals(" ")) {
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

		if (email.equals(null) || email.equals("") || email.equals(" ")) {
			System.out.println("Email inválido");
			return false;
		}
		novoUsuario.setEmail(email);

		return true;
	}

	public boolean stringIniciaComNumero(String str) {

		char caractereInicial = str.charAt(0);
		int valorASC = caractereInicial;
		// System.out.println(valorASC);
		// os valores a seguir corresponde aos valores das letras em ASC II
		int a_ = 65;
		int z_ = 90;
		int A_ = 97;
		int Z_ = 122;

		if ((valorASC >= a_ && valorASC <= z_)
				|| (valorASC >= A_ && valorASC <= Z_)) {
			return false;
		}
		return true;
	}

}
