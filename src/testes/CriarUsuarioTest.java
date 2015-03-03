package testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Control.Usuario;

public class CriarUsuarioTest {

	private Usuario novoUsuario;

	// os valores a seguir corresponde aos valores das letras em ASC II - regex expressions ** para padrão de e-mails
	private int a_ = 65;
	private int z_ = 90;
	private int A_ = 97;
	private int Z_ = 122;

	@Before
	public void setUp() {
		novoUsuario = new Usuario();
	}

	@Test
	public void testCriarUsuarioLogin() {

		char letra;
		String letraString;

		// Verifica se o nome do login inicia-se com letras ao invés de numeros
		// ou simbolos
		// teste para iniciar como as letras minusculas
		for (int i = a_; i <= z_; i++) {
			letra = (char) i;
			letraString = Character.toString(letra);
			Assert.assertTrue(novoUsuario.criarUsuario(letraString, "m@rk",
					"Mark Zuckerberg", "Palo Alto, California",
					"mark@facebook.com"));

		}
		// teste para iniciar com as letras maiusculas
		for (int i = A_; i <= Z_; i++) {
			letra = (char) i;
			letraString = Character.toString(letra);
			Assert.assertTrue(novoUsuario.criarUsuario(letraString, "m@rk",
					"Mark Zuckerberg", "Palo Alto, California",
					"mark@facebook.com"));

		}
		// teste para os demais caracteres
		for (int i = 0; i < a_; i++) {
			letra = (char) i;
			letraString = Character.toString(letra);
			Assert.assertFalse(novoUsuario.criarUsuario(letraString, "m@rk",
					"Mark Zuckerberg", "Palo Alto, California",
					"mark@facebook.com"));

		}
		for (int i = z_ + 1; i < A_; i++) {
			letra = (char) i;
			letraString = Character.toString(letra);
			Assert.assertFalse(novoUsuario.criarUsuario(letraString, "m@rk",
					"Mark Zuckerberg", "Palo Alto, California",
					"mark@facebook.com"));

		}
		for (int i = Z_ + 1; i <= 127; i++) {
			letra = (char) i;
			letraString = Character.toString(letra);
			Assert.assertFalse(novoUsuario.criarUsuario(letraString, "m@rk",
					"Mark Zuckerberg", "Palo Alto, California",
					"mark@facebook.com"));

		}

	}

	@Test
	public void testCriarUsuarioSenha() {
		Assert.assertFalse(novoUsuario.criarUsuario("A", "", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com"));
		Assert.assertFalse(novoUsuario
				.criarUsuario("A", " ", "Mark Zuckerberg",
						"Palo Alto, California", "mark@facebook.com"));

	}

	@Test
	public void testCriarUsuarioNome() {
		Assert.assertFalse(novoUsuario.criarUsuario("A", "A", "",
				"Palo Alto, California", "mark@facebook.com"));
		Assert.assertFalse(novoUsuario.criarUsuario("A", "A", " ",
				"Palo Alto, California", "mark@facebook.com"));

	}

	@Test
	public void testCriarUsuarioEndereco() {
		Assert.assertFalse(novoUsuario.criarUsuario("A", "A", "A", "",
				"mark@facebook.com"));
		Assert.assertFalse(novoUsuario.criarUsuario("A", "A", "A", " ",
				"mark@facebook.com"));

	}

	@Test
	public void testCriarUsuarioEmail() {
		Assert.assertFalse(novoUsuario.criarUsuario("A", "A", "A", "A", ""));
		Assert.assertFalse(novoUsuario.criarUsuario("A", "A", "A", "A", " "));

	}

}
