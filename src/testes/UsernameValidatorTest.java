package testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import control.ValidadorDeStrings;

public class UsernameValidatorTest {

	private ValidadorDeStrings strValidator;

	String[] validUsernameProvider = new String[] { "mkyong34", "mkyong_2002",
			"mkyong-2002", "mk3-4_yong" };
	String[] invalidUsernameProvider = new String[] { "mk", "mk@yong",
			"mkyong123456789_-" };

	@Before
	public void setUp() {
		strValidator = new ValidadorDeStrings();
	}

	@Test
	public void ValidUsernameTest() {

		for (String temp : validUsernameProvider) {
			boolean valid = strValidator.validarLogin(temp);
			//System.out.println("Username is valid : " + temp + " , " + valid);
			Assert.assertEquals(true, valid);
		}

	}

	@Test
	public void InValidUsernameTest() {

		for (String temp : invalidUsernameProvider) {
			boolean valid = strValidator.validarLogin(temp);
			//System.out.println("username is valid : " + temp + " , " + valid);
			Assert.assertEquals(false, valid);
		}

	}

}
