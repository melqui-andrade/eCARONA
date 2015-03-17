package testes;

import model.Usuario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import control.GerenciadorDeUsuario;
import control.MensagensDeErro;

public class CriarUsuarioTest {

	private GerenciadorDeUsuario gerenciadorDeUsuario;

	@Before
	public void setUp() {
		gerenciadorDeUsuario = new GerenciadorDeUsuario();
	}

	@Test
	public void testCriarUsuarioOk() throws Exception {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",	"Palo Alto, California", "mark@facebook.com");
		Assert.assertNotNull(gerenciadorDeUsuario.usuarioBD.get("mark"));
		Assert.assertEquals(gerenciadorDeUsuario.usuarioBD.get("mark").getLogin(),"mark");

		//Assert.assertTrue(novoUsuario.getNome().contentEquals("Mark Zuckerberg"));
		
		
		/*Assert.assertTrue(novoUsuario.criarUsuario("steve", "5t3v3",
				"Steven Paul Jobs", "Palo Alto, California", "jobs@apple.com"));

		Assert.assertTrue(novoUsuario.criarUsuario("bill", "severino",
				"William Henry Gates III", "Medina, Washington",
				"billzin@msn.com"));
*/
	}

	@Test (expected=Exception.class)
	public void testLoginInvalidoCriarUsuario() throws Exception {

		
		gerenciadorDeUsuario.criarUsuario(null, "xptz", "xpto", "xpto", "logininvalido@gmail.com");
		

		gerenciadorDeUsuario.criarUsuario("", "xptz", "xpto", "xpto", "deuerro@gmail.com");
		
		

	}

}
