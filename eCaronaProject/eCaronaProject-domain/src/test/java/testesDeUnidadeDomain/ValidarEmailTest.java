package  testesDeUnidadeDomain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.br.uepb.validator.adapter.ValidadorDeStringsAdapter;

public class ValidarEmailTest {

	private ValidadorDeStringsAdapter strValidador;
	String[] emailsValidos = new String[] { "mkyong@yahoo.com",
			"mkyong-100@yahoo.com", "mkyong.100@yahoo.com",
			"mkyong111@mkyong.com", "mkyong-100@mkyong.net",
			"mkyong.100@mkyong.com.au", "mkyong@1.com", "mkyong@gmail.com.com",
			"mkyong+100@gmail.com", "mkyong-100@yahoo-test.com" };

	String[] emailsInvalidos = new String[] { "mkyong", "mkyong@.com.my",
			"mkyong123@gmail.a", "mkyong123@.com", "mkyong123@.com.com",
			".mkyong@mkyong.com", "mkyong()*@gmail.com", "mkyong@%*.com",
			"mkyong..2002@gmail.com", "mkyong.@gmail.com",
			"mkyong@mkyong@gmail.com", "mkyong@gmail.com.1a" };

	@Before
	public void setUp() {
		strValidador = new ValidadorDeStringsAdapter();
	}

	@Test
	public void ValidEmailTest() {

		for (String temp : emailsValidos) {
			boolean valid = strValidador.validarEmail(temp);
			//System.out.println("Email is valid : " + temp + " , " + valid);
			Assert.assertEquals(valid, true);
		}

	}

	@Test
	public void InValidEmailTest() {

		for (String temp : emailsInvalidos) {
			boolean valid = strValidador.validarEmail(temp);
			//System.out.println("Email is valid : " + temp + " , " + valid);
			Assert.assertEquals(valid, false);
		}
	}
}