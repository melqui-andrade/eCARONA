package testesDeUnidadeBusiness;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;

public class UsuarioBusinessExceptionsTest {

	private UsuarioBusiness gerenciadorDeUsuario;

	@Before
	public void setUp() {
		gerenciadorDeUsuario = new UsuarioBusiness();

	}

	@Test
	public void loginInvalidoTest() throws ECaronaException {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			gerenciadorDeUsuario.criarUsuario(null, "xptz", "xpto", "xpto",
					"xpto");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.LOGIN_INVALIDO, eCa.getMessage());
		}

		try {
			gerenciadorDeUsuario.criarUsuario("", "xptz", "xpto", "xpto",
					"xpto");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.LOGIN_INVALIDO, eCa.getMessage());
		}

		try {
			gerenciadorDeUsuario.abrirSessao(null, "teste");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.LOGIN_INVALIDO, eCa.getMessage());
		}

		try {
			gerenciadorDeUsuario.abrirSessao("", "segundoTeste");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.LOGIN_INVALIDO, eCa.getMessage());
		}

		try {
			gerenciadorDeUsuario.abrirSessao("mark", "segundoTeste");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.LOGIN_INVALIDO, eCa.getMessage());
		}

		try {
			gerenciadorDeUsuario.abrirSessao(null, null);
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.LOGIN_INVALIDO, eCa.getMessage());
		}

		try {
			gerenciadorDeUsuario.abrirSessao("", "");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.LOGIN_INVALIDO, eCa.getMessage());
		}

	}

	@Test
	public void nomeInvalidoTest() throws ECaronaException {

		try {
			gerenciadorDeUsuario.criarUsuario("xpto", "xptz", null, "xpto",
					"xpto");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.NOME_INVALIDO, eCa.getMessage());
		}

		try {
			gerenciadorDeUsuario.criarUsuario("xpto", "xptz", "", "xpto",
					"xpto");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.NOME_INVALIDO, eCa.getMessage());
		}

		try {
			gerenciadorDeUsuario.criarUsuario("xpto", "xptz", "    ", "xpto",
					"xpto");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.NOME_INVALIDO, eCa.getMessage());
		}

	}

	@Test
	public void emailInvalidoTest() throws ECaronaException {

		try {
			gerenciadorDeUsuario.criarUsuario("xpto", "xptz", "xpto", "xpto",
					null);
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.EMAIL_INVALIDO, eCa.getMessage());
		}

		try {
			gerenciadorDeUsuario.criarUsuario("xpto", "xptz", "xpto", "xpto",
					"");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.EMAIL_INVALIDO, eCa.getMessage());
		}

		try {
			gerenciadorDeUsuario.criarUsuario("xpto", "xptz", "xpto", "xpto",
					"chico");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.EMAIL_INVALIDO, eCa.getMessage());
		}

		try {
			gerenciadorDeUsuario.criarUsuario("xpto", "xptz", "xpto", "xpto",
					"chico@casa");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.EMAIL_INVALIDO, eCa.getMessage());
		}

	}

	@Test
	public void existeUsuarioComEsseEmailTest() throws ECaronaException {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			gerenciadorDeUsuario.criarUsuario("xpto", "tttppp", "markito",
					"xpto", "mark@facebook.com");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.EXISTE_USUARIO_C_EMAIL,
					eCa.getMessage());
		}
	}

	@Test
	public void existeUsuarioComEsseLoginTest() throws ECaronaException {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			gerenciadorDeUsuario.criarUsuario("mark", "tttppp", "markito",
					"xpto", "markinho@facebook.com");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.EXISTE_USUARIO_C_LOGIN,
					eCa.getMessage());
		}

	}

	@Test
	public void usuarioInexistenteTest() throws ECaronaException {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");
		gerenciadorDeUsuario.criarUsuario("steve", "5t3v3", "Steven Paul Jobs",
				"Palo Alto, California", "jobs@apple.com");
		gerenciadorDeUsuario.criarUsuario("bill", "severino",
				"William Henry Gates III", "Medina, Washington",
				"billzin@msn.com");

		try {
			gerenciadorDeUsuario.abrirSessao("hufflees", "123");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.USUARIO_INEXISTENTE, eCa.getMessage());
		}

		try {
			gerenciadorDeUsuario.getAtributoUsuario("warrior", "nome");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.USUARIO_INEXISTENTE, eCa.getMessage());
		}

	}

	@Test
	public void atributoInvalidoTest() throws ECaronaException {
		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			gerenciadorDeUsuario.getAtributoUsuario("mark", "");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ATRIBUTO_INVALIDO, eCa.getMessage());
		}
		try {
			gerenciadorDeUsuario.getAtributoUsuario("mark", null);
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ATRIBUTO_INVALIDO, eCa.getMessage());
		}

	}

	@Test
	public void atributoInexistenteTest() throws ECaronaException {
		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			gerenciadorDeUsuario.getAtributoUsuario("mark", "casa");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ATRIBUTO_INEXISTENTE, eCa.getMessage());
		}

		try {
			gerenciadorDeUsuario.getAtributoUsuario("mark", "lixo");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ATRIBUTO_INEXISTENTE, eCa.getMessage());
		}

		try {
			gerenciadorDeUsuario.getAtributoUsuario("mark", "????????????????");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ATRIBUTO_INEXISTENTE, eCa.getMessage());
		}

		try {
			gerenciadorDeUsuario.getAtributoUsuario("mark",
					"testandoOutoAtributo");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ATRIBUTO_INEXISTENTE, eCa.getMessage());
		}
	}

}
