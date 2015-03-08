package testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import control.Usuario;

public class CriarUsuarioTest {

	private Usuario novoUsuario;

	@Before
	public void setUp() {
		novoUsuario = new Usuario();
	}

	@Test
	public void testCriarUsuarioOk() throws Exception {

		Assert.assertTrue(novoUsuario
				.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
						"Palo Alto, California", "mark@facebook.com"));

		Assert.assertTrue(novoUsuario.criarUsuario("steve", "5t3v3",
				"Steven Paul Jobs", "Palo Alto, California", "jobs@apple.com"));

		Assert.assertTrue(novoUsuario.criarUsuario("bill", "severino",
				"William Henry Gates III", "Medina, Washington",
				"billzin@msn.com"));

	}

	@Test
	public void testLoginInvalidoCriarUsuario() throws Exception {

		Assert.assertFalse(novoUsuario.criarUsuario(null, "xptz", "xpto",
				"xpto", "logininvalido@gmail.com"));

		Assert.assertFalse(novoUsuario.criarUsuario("", "xptz", "xpto", "xpto",
				"deuerro@gmail.com"));
	
		
		

	}

}
