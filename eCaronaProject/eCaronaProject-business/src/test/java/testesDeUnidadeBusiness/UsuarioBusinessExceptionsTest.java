package testesDeUnidadeBusiness;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.CaronaBusiness;
import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;

public class UsuarioBusinessExceptionsTest {

	private UsuarioBusiness gerenciadorDeUsuario;
	private SessaoBusiness gerenciadorDeSessao;
	private CaronaBusiness gerenciadorDeCarona;

	@Before
	public void setUp() {
		this.gerenciadorDeUsuario = new UsuarioBusiness();
		this.gerenciadorDeSessao = new SessaoBusiness();
		this.gerenciadorDeCarona = new CaronaBusiness();
	}

	// ERROS DO US01
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
			gerenciadorDeSessao.abrirSessao(null, "teste");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.LOGIN_INVALIDO, eCa.getMessage());
		}

		try {
			gerenciadorDeSessao.abrirSessao("", "segundoTeste");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.LOGIN_INVALIDO, eCa.getMessage());
		}

		try {
			gerenciadorDeSessao.abrirSessao("mark", "segundoTeste");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.LOGIN_INVALIDO, eCa.getMessage());
		}

		try {
			gerenciadorDeSessao.abrirSessao(null, null);
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.LOGIN_INVALIDO, eCa.getMessage());
		}

		try {
			gerenciadorDeSessao.abrirSessao("", "");
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
			gerenciadorDeSessao.abrirSessao("hufflees", "123");
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

	// ERROS DO US02
	@Test
	public void sessaoInvalidaTest() throws ECaronaException {

		try {
			gerenciadorDeCarona.cadastrarCarona(null, "Campina Grande",
					"João Pessoa", "23/06/2013", "16:00", "3");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.SESSAO_INVALIDA, eCa.getMessage());
		}

		try {
			gerenciadorDeCarona.cadastrarCarona("", "Campina Grande",
					"João Pessoa", "23/06/2013", "16:00", "3");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.SESSAO_INVALIDA, eCa.getMessage());
		}

	}

	@Test
	public void sessaoInexistenteTest() throws ECaronaException {

		try {
			gerenciadorDeCarona.cadastrarCarona("teste", "Campina Grande",
					"João Pessoa", "23/06/2013", "16:00", "3");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.SESSAO_INEXISTENTE, eCa.getMessage());
		}

	}

	@Test
	public void origemInvalidaTest() throws ECaronaException {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");
		String idSessao = gerenciadorDeSessao.abrirSessao("mark", "m@rk");

		try {
			gerenciadorDeCarona.cadastrarCarona(idSessao, "", "João Pessoa",
					"23/06/2013", "16:00", "3");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ORIGEM_INVALIDA, eCa.getMessage());
		}

		try {
			gerenciadorDeCarona.cadastrarCarona(idSessao, null, "João Pessoa",
					"23/06/2013", "16:00", "3");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ORIGEM_INVALIDA, eCa.getMessage());
		}

	}

	@Test
	public void destinoInvalidoTest() throws ECaronaException {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");
		String idSessao = gerenciadorDeSessao.abrirSessao("mark", "m@rk");

		try {
			gerenciadorDeCarona.cadastrarCarona(idSessao, "Campina Grande",
					"", "23/06/2013", "16:00", "3");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.DESTINO_INVALIDO, eCa.getMessage());
		}

		try {
			gerenciadorDeCarona.cadastrarCarona(idSessao, "Campina Grande",
					null, "23/06/2013", "16:00", "3");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.DESTINO_INVALIDO, eCa.getMessage());
		}

	}

	@Test
	public void dataInvalidaTest() throws ECaronaException {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");
		String idSessao = gerenciadorDeSessao.abrirSessao("mark", "m@rk");

		try {
			gerenciadorDeCarona.cadastrarCarona(idSessao, "Campina Grande",
					"João Pessoa", "", "16:00", "3");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.DATA_INVALIDA, eCa.getMessage());
		}

		try {
			gerenciadorDeCarona.cadastrarCarona(idSessao, "Campina Grande",
					"João Pessoa", null, "16:00", "3");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.DATA_INVALIDA, eCa.getMessage());
		}
		try {
			gerenciadorDeCarona.cadastrarCarona(idSessao, "Campina Grande",
					"João Pessoa", "30/02/2012", "16:00", "3");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.DATA_INVALIDA, eCa.getMessage());
		}

		try {
			gerenciadorDeCarona.cadastrarCarona(idSessao, "Campina Grande",
					"João Pessoa", "31/04/2012", "16:00", "3");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.DATA_INVALIDA, eCa.getMessage());
		}

		try {
			gerenciadorDeCarona.cadastrarCarona(idSessao, "Campina Grande",
					"João Pessoa", "32/12/2012", "16:00", "3");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.DATA_INVALIDA, eCa.getMessage());
		}

		try {
			gerenciadorDeCarona.cadastrarCarona(idSessao, "Campina Grande",
					"João Pessoa", "30/02/2011", "16:00", "3");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.DATA_INVALIDA, eCa.getMessage());
		}

	}

	@Test
	public void horaInvalidaTest() throws ECaronaException {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");
		String idSessao = gerenciadorDeSessao.abrirSessao("mark", "m@rk");

		try {
			gerenciadorDeCarona.cadastrarCarona(idSessao, "Campina Grande",
					"João Pessoa", "23/06/2013", "", "3");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.HORA_INVALIDA, eCa.getMessage());
		}
		try {
			gerenciadorDeCarona.cadastrarCarona(idSessao, "Campina Grande",
					"João Pessoa", "23/06/2013", null, "3");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.HORA_INVALIDA, eCa.getMessage());
		}
		try {
			gerenciadorDeCarona.cadastrarCarona(idSessao, "Campina Grande",
					"João Pessoa", "23/06/2013", "sete", "3");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.HORA_INVALIDA, eCa.getMessage());
		}
	}

	@Test
	public void vagaInvalidaTest() throws ECaronaException {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");
		String idSessao = gerenciadorDeSessao.abrirSessao("mark", "m@rk");

		try {
			gerenciadorDeCarona.cadastrarCarona(idSessao, "Campina Grande",
					"João Pessoa", "23/06/2013", "16:00", "");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.VAGA_INVALIDA, eCa.getMessage());
		}
		try {
			gerenciadorDeCarona.cadastrarCarona(idSessao, "Campina Grande",
					"João Pessoa", "23/06/2013", "16:00", "null");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.VAGA_INVALIDA, eCa.getMessage());
		}
		try {
			gerenciadorDeCarona.cadastrarCarona(idSessao, "Campina Grande",
					"João Pessoa", "23/06/2013", "16:00", "tres");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.VAGA_INVALIDA, eCa.getMessage());
		}
	}

	@Test
	public void identificadorCaronaInvalidoTest() throws ECaronaException {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			gerenciadorDeCarona.getAtributoCarona("", "origem");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.IDENTIFICADOR_CARONA_INVALIDO,
					eCa.getMessage());
		}
		try {
			gerenciadorDeCarona.getAtributoCarona(null, "origem");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.IDENTIFICADOR_CARONA_INVALIDO,
					eCa.getMessage());
		}
	}

	@Test
	public void itemInexistenteTest() throws ECaronaException {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		try {
			gerenciadorDeCarona.getAtributoCarona("xpto", "origem");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ITEM_INEXISTENTE, eCa.getMessage());
		}
	}

	@Test
	public void atributoInvalido_2Test() throws ECaronaException {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		String idSessao = gerenciadorDeSessao.abrirSessao("mark", "m@rk");

		String idCarona = gerenciadorDeCarona.cadastrarCarona(idSessao,
				"Rio de Janeiro", "São Paulo", "31/05/2013", "08:00", "2");

		try {
			gerenciadorDeCarona.getAtributoCarona(idCarona, "");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ATRIBUTO_INVALIDO, eCa.getMessage());
		}
		try {
			gerenciadorDeCarona.getAtributoCarona(idCarona, null);
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ATRIBUTO_INVALIDO, eCa.getMessage());
		}
		try {
			gerenciadorDeCarona.getAtributoCarona(idCarona, "xpto");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ATRIBUTO_INEXISTENTE, eCa.getMessage());
		}
	}

	@Test
	public void origemInvalida_2Test() throws ECaronaException {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		String idSessao = gerenciadorDeSessao.abrirSessao("mark", "m@rk");

		try {
			gerenciadorDeCarona.localizarCarona(idSessao, "-", "João Pessoa");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ORIGEM_INVALIDA, eCa.getMessage());
		}
		try {
			gerenciadorDeCarona.localizarCarona(idSessao, "()", "João Pessoa");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ORIGEM_INVALIDA, eCa.getMessage());
		}
		try {
			gerenciadorDeCarona.localizarCarona(idSessao, "!", "João Pessoa");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ORIGEM_INVALIDA, eCa.getMessage());
		}
		try {
			gerenciadorDeCarona.localizarCarona(idSessao, "!?", "João Pessoa");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ORIGEM_INVALIDA, eCa.getMessage());
		}
	}

	@Test
	public void destinoInvalido_2Test() throws ECaronaException {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		String idSessao = gerenciadorDeSessao.abrirSessao("mark", "m@rk");

		try {
			gerenciadorDeCarona.localizarCarona(idSessao, "Campina Grande", ".");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.DESTINO_INVALIDO, eCa.getMessage());
		}
		try {
			gerenciadorDeCarona.localizarCarona(idSessao, "Campina Grande", "()");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.DESTINO_INVALIDO, eCa.getMessage());
		}
		try {
			gerenciadorDeCarona.localizarCarona(idSessao, "Campina Grande", "!?");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.DESTINO_INVALIDO, eCa.getMessage());
		}
	}

	
	
}
