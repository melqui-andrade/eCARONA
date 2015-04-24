package com.br.uepb.domain;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.constants.ECaronaException;
import com.br.uepb.validator.adapter.ValidadorDeStringsAdapter;

/**
 * Um usuário do sistema, todo usuário do sistema deve ter um login, senha
 * nome endereço, email e um conjunto de caronas
 * @author Melqui
 *
 */
@Entity
@Table(name="USUARIO_BD")
public class UsuarioDomain {

	@Id
	@Column(name="LOGIN")
	private String login;
	
	@Column(name="SENHA")
	private String senha;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="ENDERECO")
	private String endereco;
	
	@Column(name="EMAIL")
	private String email;
	
	private ArrayList<CaronaDomain> caronas = new ArrayList<CaronaDomain>();

	private ValidadorDeStringsAdapter validarString = new ValidadorDeStringsAdapter();

	/********************* GETTERS and SETTERS ********************/

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) throws ECaronaException {

		if (validarString.validarLogin(login)) {
			this.login = login;
		} else {
			throw new ECaronaException(MensagensDeErro.LOGIN_INVALIDO);

		}

	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws ECaronaException {

		if (validarString.validarSenha(senha)) {
			this.senha = senha;
		} else {
			throw new ECaronaException(MensagensDeErro.SENHA_INVALIDA);

		}

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws ECaronaException {

		if (nome == null || nome.isEmpty() || nome.trim().isEmpty()) {
			throw new ECaronaException(MensagensDeErro.NOME_INVALIDO);

		} else {
			this.nome = nome;

		}

	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) throws ECaronaException {

		if (endereco == null || endereco.isEmpty() || endereco.trim().isEmpty()) {
			throw new ECaronaException(MensagensDeErro.ENDERECO_INVALIDO);

		} else {
			this.endereco = endereco;
		}

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws ECaronaException {

		if (validarString.validarEmail(email)) {
			this.email = email;
		} else {
			throw new ECaronaException(MensagensDeErro.EMAIL_INVALIDO);
		}

	}

	public ArrayList<CaronaDomain> getCaronas() {
		return caronas;
	}
	/**
	 * 
	 * @param carona
	 */
	public void adicionarCarona(CaronaDomain carona){
		caronas.add(carona);
	}

}
