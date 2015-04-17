package testesDeUnidadeBusiness;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;

public class UsuarioBusinessTest {

	private UsuarioBusiness gerenciadorDeUsuario;

	@Before
	public void setUp() {
		gerenciadorDeUsuario = new UsuarioBusiness();

	}

	@Test
	public void verificarDadosSalvosDoUsuarioCriadoTest()
			throws ECaronaException {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");
		gerenciadorDeUsuario.criarUsuario("steve", "5t3v3", "Steven Paul Jobs",
				"Palo Alto, California", "jobs@apple.com");
		gerenciadorDeUsuario.criarUsuario("bill", "severino",
				"William Henry Gates III", "Medina, Washington",
				"billzin@msn.com");

		// teste para verificar se steve foi criado
		Assert.assertNotNull(gerenciadorDeUsuario.getUsuarioBD().get("steve"));
		Assert.assertEquals(gerenciadorDeUsuario.getUsuarioBD().get("steve")
				.getLogin(), "steve");
		Assert.assertEquals(gerenciadorDeUsuario.getUsuarioBD().get("steve")
				.getSenha(), "5t3v3");
		Assert.assertEquals(gerenciadorDeUsuario.getUsuarioBD().get("steve")
				.getNome(), "Steven Paul Jobs");
		Assert.assertEquals(gerenciadorDeUsuario.getUsuarioBD().get("steve")
				.getEndereco(), "Palo Alto, California");
		Assert.assertEquals(gerenciadorDeUsuario.getUsuarioBD().get("steve")
				.getEmail(), "jobs@apple.com");

		// teste para verificar se mark foi criado
		Assert.assertNotNull(gerenciadorDeUsuario.getUsuarioBD().get("mark"));
		Assert.assertEquals(gerenciadorDeUsuario.getUsuarioBD().get("mark")
				.getLogin(), "mark");
		Assert.assertEquals(gerenciadorDeUsuario.getUsuarioBD().get("mark")
				.getSenha(), "m@rk");
		Assert.assertEquals(gerenciadorDeUsuario.getUsuarioBD().get("mark")
				.getNome(), "Mark Zuckerberg");
		Assert.assertEquals(gerenciadorDeUsuario.getUsuarioBD().get("mark")
				.getEndereco(), "Palo Alto, California");
		Assert.assertEquals(gerenciadorDeUsuario.getUsuarioBD().get("mark")
				.getEmail(), "mark@facebook.com");

		// teste para verificar se bill foi criado
		Assert.assertNotNull(gerenciadorDeUsuario.getUsuarioBD().get("bill"));
		Assert.assertEquals(gerenciadorDeUsuario.getUsuarioBD().get("bill")
				.getLogin(), "bill");
		Assert.assertEquals(gerenciadorDeUsuario.getUsuarioBD().get("bill")
				.getSenha(), "severino");
		Assert.assertEquals(gerenciadorDeUsuario.getUsuarioBD().get("bill")
				.getNome(), "William Henry Gates III");
		Assert.assertEquals(gerenciadorDeUsuario.getUsuarioBD().get("bill")
				.getEndereco(), "Medina, Washington");
		Assert.assertEquals(gerenciadorDeUsuario.getUsuarioBD().get("bill")
				.getEmail(), "billzin@msn.com");

	}

	@Test
	public void getAtributoUsuarioTest() throws ECaronaException {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");
		gerenciadorDeUsuario.criarUsuario("steve", "5t3v3", "Steven Paul Jobs",
				"Palo Alto, California", "jobs@apple.com");
		gerenciadorDeUsuario.criarUsuario("bill", "severino",
				"William Henry Gates III", "Medina, Washington",
				"billzin@msn.com");

		String idSessao = gerenciadorDeUsuario.abrirSessao("mark", "m@rk");

	}

}
