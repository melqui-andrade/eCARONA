package com.br.uepb.validator.adapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorDeStringsAdapter {

	private Pattern padraoLogin;
	private Pattern padraoSenha;
	private Pattern padraoEmail;
	private Matcher verificadorDePadrao;

	private static final String LOGIN_PADRAO = "^[a-z0-9_-]{3,15}$";
	private static final String SENHA_PADRAO = "^([a-zA-Z0-9@*#]{4,15})$";
	private static final String EMAIL_PADRAO = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public ValidadorDeStringsAdapter() {
		padraoLogin = Pattern.compile(LOGIN_PADRAO);
		padraoSenha = Pattern.compile(SENHA_PADRAO);
		padraoEmail = Pattern.compile(EMAIL_PADRAO);
	}

	public boolean validarLogin(final String login) {

		if (login == null)
			return false;

		verificadorDePadrao = padraoLogin.matcher(login);
		return verificadorDePadrao.matches();

	}

	public boolean validarSenha(final String senha) {

		if (senha == null) {
			return false;
		}

		verificadorDePadrao = padraoSenha.matcher(senha);
		return verificadorDePadrao.matches();

	}

	public boolean validarEmail(final String email) {

		if (email == null)
			return false;

		verificadorDePadrao = padraoEmail.matcher(email);
		return verificadorDePadrao.matches();

	}

}
