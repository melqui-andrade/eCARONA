package testes;

import model.Usuario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utilitarios.MensagensDeErro;
import control.GerenciadorDeUsuario;

public class CriarUsuarioTest {

	private GerenciadorDeUsuario gerenciadorDeUsuario;

	@Before
	public void setUp() {
		gerenciadorDeUsuario = new GerenciadorDeUsuario();
	}

	@Test
	public void testCriarUsuarioOk() throws Exception {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");
		gerenciadorDeUsuario.criarUsuario("steve", "5t3v3", "Steven Paul Jobs",
				"Palo Alto, California", "jobs@apple.com");
		gerenciadorDeUsuario.criarUsuario("bill", "severino",
				"William Henry Gates III", "Medina, Washington",
				"billzin@msn.com");

		// teste para verificar se steve foi criado
		Assert.assertNotNull(gerenciadorDeUsuario.usuarioBD.get("steve"));
		Assert.assertEquals(gerenciadorDeUsuario.usuarioBD.get("steve")
				.getLogin(), "steve");
		Assert.assertEquals(gerenciadorDeUsuario.usuarioBD.get("steve")
				.getSenha(), "5t3v3");
		Assert.assertEquals(gerenciadorDeUsuario.usuarioBD.get("steve")
				.getNome(), "Steven Paul Jobs");
		Assert.assertEquals(gerenciadorDeUsuario.usuarioBD.get("steve")
				.getEndereco(), "Palo Alto, California");
		Assert.assertEquals(gerenciadorDeUsuario.usuarioBD.get("steve")
				.getEmail(), "jobs@apple.com");

		// teste para verificar se mark foi criado
		Assert.assertNotNull(gerenciadorDeUsuario.usuarioBD.get("mark"));
		Assert.assertEquals(gerenciadorDeUsuario.usuarioBD.get("mark")
				.getLogin(), "mark");
		Assert.assertEquals(gerenciadorDeUsuario.usuarioBD.get("mark")
				.getSenha(), "m@rk");
		Assert.assertEquals(gerenciadorDeUsuario.usuarioBD.get("mark")
				.getNome(), "Mark Zuckerberg");
		Assert.assertEquals(gerenciadorDeUsuario.usuarioBD.get("mark")
				.getEndereco(), "Palo Alto, California");
		Assert.assertEquals(gerenciadorDeUsuario.usuarioBD.get("mark")
				.getEmail(), "mark@facebook.com");

		// teste para verificar se bill foi criado
		Assert.assertNotNull(gerenciadorDeUsuario.usuarioBD.get("bill"));
		Assert.assertEquals(gerenciadorDeUsuario.usuarioBD.get("bill")
				.getLogin(), "bill");
		Assert.assertEquals(gerenciadorDeUsuario.usuarioBD.get("bill")
				.getSenha(), "severino");
		Assert.assertEquals(gerenciadorDeUsuario.usuarioBD.get("bill")
				.getNome(), "William Henry Gates III");
		Assert.assertEquals(gerenciadorDeUsuario.usuarioBD.get("bill")
				.getEndereco(), "Medina, Washington");
		Assert.assertEquals(gerenciadorDeUsuario.usuarioBD.get("bill")
				.getEmail(), "billzin@msn.com");

	}

	@Test(expected = Exception.class)
	public void testLoginInvalidoCriarUsuario() throws Exception {

		gerenciadorDeUsuario.criarUsuario(null, "xptz", "xpto", "xpto",
				"logininvalido@gmail.com");

		gerenciadorDeUsuario.criarUsuario("", "xptz", "xpto", "xpto",
				"deuerro@gmail.com");

	}

}
