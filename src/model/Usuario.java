package model;

import java.util.HashMap;
import java.util.Map;

import utilitarios.MensagensDeErro;
import utilitarios.ValidadorDeStrings;

public class Usuario {

	private String login;
	private String senha;
	private String nome;
	private String endereco;
	private String email;

	private ValidadorDeStrings validarString = new ValidadorDeStrings();

	/********************* GETTERS and SETTERS ********************/
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) throws Exception {

		if (validarString.validarLogin(login)) {
			this.login = login;
		} else {
			throw new Exception(MensagensDeErro.LOGIN_INVALIDO);

		}

	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws Exception {

		if (validarString.validarSenha(senha)) {
			this.senha = senha;
		} else {
			throw new Exception(MensagensDeErro.SENHA_INVALIDA);

		}

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws Exception {

		if (nome == null || nome.isEmpty() || nome.trim().isEmpty()) {
			throw new Exception(MensagensDeErro.NOME_INVALIDO);

		} else {
			this.nome = nome;

		}

	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) throws Exception {

		if (endereco == null || endereco.isEmpty() || endereco.trim().isEmpty()) {
			throw new Exception(MensagensDeErro.ENDERECO_INVALIDO);

		} else {
			this.endereco = endereco;
		}

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {

		if (validarString.validarEmail(email)) {
			this.email = email;
		} else {
			throw new Exception(MensagensDeErro.EMAIL_INVALIDO);
		}

	}

}
