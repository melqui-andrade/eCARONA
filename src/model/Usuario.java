package model;

import java.util.HashMap;
import java.util.Map;

import control.MensagensDeErro;
import control.ValidadorDeStrings;

public class Usuario {

	private String login;
	private String senha;
	private String nome;
	private String endereco;
	private String email;

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

	/*
	 * public void abrirSessao(String login, String senha) { usuarioBuffer =
	 * usuarioBD.get(login);
	 * 
	 * if (usuarioBuffer.login == login && usuarioBuffer.senha == senha) { //
	 * RETORNAR ID DA SESSAO }
	 * 
	 * }
	 * 
	 * public void zerarSistema() { setLogin(null); setSenha(null);
	 * setNome(null); setEndereco(null); setEmail(null);
	 * 
	 * usuarioBD = null; usuarioBuffer = new Usuario(); }
	 */
}
