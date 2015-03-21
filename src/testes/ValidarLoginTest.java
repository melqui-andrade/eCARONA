package testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utilitarios.ValidadorDeStrings;

public class ValidarLoginTest {

	private ValidadorDeStrings strValidador;

	String[] loginsValidos = new String[] { "mkyong34", "mkyong_2002",
			"mkyong-2002", "mk3-4_yong" };
	String[] loginsInvalidos = new String[] { "mk", "mk@yong",
			"mkyong123456789_-" };

	@Before
	public void setUp() {
		strValidador = new ValidadorDeStrings();
	}

	@Test
	public void ValidUsernameTest() {

		for (String temp : loginsValidos) {
			boolean ehValido = strValidador.validarLogin(temp);
			//System.out.println("Username is valid : " + temp + " , " + valid);
			Assert.assertEquals(true, ehValido);
		}

	}

	@Test
	public void InValidUsernameTest() {

		for (String temp : loginsInvalidos) {
			boolean ehValido = strValidador.validarLogin(temp);
			//System.out.println("username is valid : " + temp + " , " + valid);
			Assert.assertEquals(false, ehValido);
		}

	}

}
