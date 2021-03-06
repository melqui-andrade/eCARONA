/*package testesDeUnidadeBusiness;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.UsuarioBusiness;
import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.PersistenciaDomain;
import com.br.uepb.domain.UsuarioDomain;

public class UsuarioBusinessTest {

	private PersistenciaDomain persistencia = new PersistenciaDomain();
	private UsuarioBusiness gerenciadorDeUsuario;

	@Before
	public void setUp() {
		gerenciadorDeUsuario = new UsuarioBusiness(persistencia);

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
		Assert.assertNotNull(gerenciadorDeUsuario.getPersistencia().getUsuarioBD().get("steve"));
		Assert.assertEquals(gerenciadorDeUsuario.getPersistencia().getUsuarioBD().get("steve")
				.getLogin(), "steve");
		Assert.assertEquals(gerenciadorDeUsuario.getPersistencia().getUsuarioBD().get("steve")
				.getSenha(), "5t3v3");
		Assert.assertEquals(gerenciadorDeUsuario.getPersistencia().getUsuarioBD().get("steve")
				.getNome(), "Steven Paul Jobs");
		Assert.assertEquals(gerenciadorDeUsuario.getPersistencia().getUsuarioBD().get("steve")
				.getEndereco(), "Palo Alto, California");
		Assert.assertEquals(gerenciadorDeUsuario.getPersistencia().getUsuarioBD().get("steve")
				.getEmail(), "jobs@apple.com");

		// teste para verificar se mark foi criado
		Assert.assertNotNull(gerenciadorDeUsuario.getPersistencia().getUsuarioBD().get("mark"));
		Assert.assertEquals(gerenciadorDeUsuario.getPersistencia().getUsuarioBD().get("mark")
				.getLogin(), "mark");
		Assert.assertEquals(gerenciadorDeUsuario.getPersistencia().getUsuarioBD().get("mark")
				.getSenha(), "m@rk");
		Assert.assertEquals(gerenciadorDeUsuario.getPersistencia().getUsuarioBD().get("mark")
				.getNome(), "Mark Zuckerberg");
		Assert.assertEquals(gerenciadorDeUsuario.getPersistencia().getUsuarioBD().get("mark")
				.getEndereco(), "Palo Alto, California");
		Assert.assertEquals(gerenciadorDeUsuario.getPersistencia().getUsuarioBD().get("mark")
				.getEmail(), "mark@facebook.com");

		// teste para verificar se bill foi criado
		Assert.assertNotNull(gerenciadorDeUsuario.getPersistencia().getUsuarioBD().get("bill"));
		Assert.assertEquals(gerenciadorDeUsuario.getPersistencia().getUsuarioBD().get("bill")
				.getLogin(), "bill");
		Assert.assertEquals(gerenciadorDeUsuario.getPersistencia().getUsuarioBD().get("bill")
				.getSenha(), "severino");
		Assert.assertEquals(gerenciadorDeUsuario.getPersistencia().getUsuarioBD().get("bill")
				.getNome(), "William Henry Gates III");
		Assert.assertEquals(gerenciadorDeUsuario.getPersistencia().getUsuarioBD().get("bill")
				.getEndereco(), "Medina, Washington");
		Assert.assertEquals(gerenciadorDeUsuario.getPersistencia().getUsuarioBD().get("bill")
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

		// VERIFICAR ATRIBUTOS DE MARK
		String idSessao = gerenciadorDeUsuario.abrirSessao("mark", "m@rk");
		UsuarioDomain usuario = gerenciadorDeUsuario.getPersistencia().getSessaoBD()
				.get(idSessao).getUsuario();
		String loginUsuario = usuario.getLogin();

		Assert.assertEquals(
				gerenciadorDeUsuario.getAtributoUsuario(loginUsuario, "nome"),
				"Mark Zuckerberg");
		Assert.assertEquals(gerenciadorDeUsuario.getAtributoUsuario(
				loginUsuario, "endereco"), "Palo Alto, California");
		Assert.assertEquals(
				gerenciadorDeUsuario.getAtributoUsuario(loginUsuario, "email"),
				"mark@facebook.com");

		try {
			gerenciadorDeUsuario.getAtributoUsuario(loginUsuario, "");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ATRIBUTO_INVALIDO, eCa.getMessage());
		}

		// VERIFICAR ATRIBUTOS DE STEVE
		idSessao = gerenciadorDeUsuario.abrirSessao("steve", "5t3v3");
		usuario = gerenciadorDeUsuario.getPersistencia().getSessaoBD().get(idSessao).getUsuario();
		loginUsuario = usuario.getLogin();

		Assert.assertEquals(
				gerenciadorDeUsuario.getAtributoUsuario(loginUsuario, "nome"),
				"Steven Paul Jobs");
		Assert.assertEquals(gerenciadorDeUsuario.getAtributoUsuario(
				loginUsuario, "endereco"), "Palo Alto, California");
		Assert.assertEquals(
				gerenciadorDeUsuario.getAtributoUsuario(loginUsuario, "email"),
				"jobs@apple.com");

		try {
			gerenciadorDeUsuario.getAtributoUsuario(loginUsuario, "");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ATRIBUTO_INVALIDO, eCa.getMessage());
		}

		// VERIFICAR ATRIBUTOS DE BILL
		idSessao = gerenciadorDeUsuario.abrirSessao("bill", "severino");
		usuario = gerenciadorDeUsuario.getPersistencia().getSessaoBD().get(idSessao).getUsuario();
		loginUsuario = usuario.getLogin();

		Assert.assertEquals(
				gerenciadorDeUsuario.getAtributoUsuario(loginUsuario, "nome"),
				"William Henry Gates III");
		Assert.assertEquals(gerenciadorDeUsuario.getAtributoUsuario(
				loginUsuario, "endereco"), "Medina, Washington");
		Assert.assertEquals(
				gerenciadorDeUsuario.getAtributoUsuario(loginUsuario, "email"),
				"billzin@msn.com");

		try {
			gerenciadorDeUsuario.getAtributoUsuario(loginUsuario, "");
		} catch (ECaronaException eCa) {
			assertEquals(MensagensDeErro.ATRIBUTO_INVALIDO, eCa.getMessage());
		}

	}

	// TESTES RELACIONADOS AO US02
	@Test
	public void localizarCaronaTest() throws ECaronaException {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		// VERIFICAR ATRIBUTOS DE MARK
		String idSessao = gerenciadorDeUsuario.abrirSessao("mark", "m@rk");

		Assert.assertEquals(gerenciadorDeUsuario.localizarCarona(idSessao,
				"Campina Grande", "João Pessoa"), "{}");
		Assert.assertEquals(gerenciadorDeUsuario.localizarCarona(idSessao,
				"São Francisco", "Palo Alto"), "{}");
		Assert.assertEquals(gerenciadorDeUsuario.localizarCarona(idSessao,
				"Rio de Janeiro", "São Paulo"), "{}");

	}

	@Test
	public void cadastrarCaronaTest() throws ECaronaException {

		gerenciadorDeUsuario.criarUsuario("mark", "m@rk", "Mark Zuckerberg",
				"Palo Alto, California", "mark@facebook.com");

		// VERIFICAR ATRIBUTOS DE MARK
		String idSessao = gerenciadorDeUsuario.abrirSessao("mark", "m@rk");

		ArrayList<String> allID = new ArrayList<String>();
		int i = 0;

		// PRIMEIRO ID
		allID.add(gerenciadorDeUsuario.cadastrarCarona(idSessao,
				"Campina Grande", "João Pessoa", "23/06/2013", "16:00", "3"));
		Assert.assertEquals(
				gerenciadorDeUsuario.getAtributoCarona(allID.get(i), "origem"),
				"Campina Grande");
		Assert.assertEquals(
				gerenciadorDeUsuario.getAtributoCarona(allID.get(i), "destino"),
				"João Pessoa");
		Assert.assertEquals(
				gerenciadorDeUsuario.getTrajetoCarona(allID.get(i)),
				"Campina Grande - João Pessoa");
		i++;

		// SEGUNDO ID
		allID.add(gerenciadorDeUsuario.cadastrarCarona(idSessao,
				"Rio de Janeiro", "São Paulo", "31/05/2013", "08:00", "2"));
		Assert.assertEquals(
				gerenciadorDeUsuario.getAtributoCarona(allID.get(i), "data"),
				"31/05/2013");
		Assert.assertEquals(
				gerenciadorDeUsuario.getAtributoCarona(allID.get(i), "vagas"),
				"2");
		i++;

		// TERCEIRO ID
		allID.add(gerenciadorDeUsuario.cadastrarCarona(idSessao, "João Pessoa",
				"Campina Grande", "25/11/2026", "06:59", "4"));
		Assert.assertEquals(gerenciadorDeUsuario.getCaronaInfo(allID.get(i)),
				"João Pessoa para Campina Grande, no dia 25/11/2026, as 06:59");
		i++;

		// QUARTO ID
		allID.add(gerenciadorDeUsuario.cadastrarCarona(idSessao, "João Pessoa",
				"Lagoa Seca", "25/11/2016", "05:00", "4"));
		Assert.assertEquals(gerenciadorDeUsuario.getCaronaInfo(allID.get(i)),
				"João Pessoa para Lagoa Seca, no dia 25/11/2016, as 05:00");
		i++;

		// QUINTO ID
		allID.add(gerenciadorDeUsuario.cadastrarCarona(idSessao, "João Pessoa",
				"Lagoa Seca", "25/11/2017", "05:00", "4"));
		Assert.assertEquals(gerenciadorDeUsuario.getCaronaInfo(allID.get(i)),
				"João Pessoa para Lagoa Seca, no dia 25/11/2017, as 05:00");
		i++;

		// LOCALIZAR CARONA PARA OS DADOS DESSE ESCOPO
		Assert.assertEquals(gerenciadorDeUsuario.localizarCarona(idSessao,
				"São Francisco", "Palo Alto"), "{}");
		Assert.assertEquals(gerenciadorDeUsuario.localizarCarona(idSessao,
				"Rio de Janeiro", "São Paulo"), "{" + allID.get(1) + "}");
		Assert.assertEquals(gerenciadorDeUsuario.localizarCarona(idSessao,
				"João Pessoa", "Campina Grande"), "{" + allID.get(2) + "}");

		// LOCALIZAR TODAS AS CARONA PARA UMA ORIGEM ESPECIFICA CADASTRADA
		Assert.assertEquals(gerenciadorDeUsuario.localizarCarona(idSessao,
				"João Pessoa", ""), "{carona3ID,carona4ID,carona5ID}");
		Assert.assertEquals(gerenciadorDeUsuario.localizarCarona(idSessao,
				"", "São Paulo"), "{carona2ID}");
		
		//LOCALIZAR TODAS AS CARONAS
		Assert.assertEquals(gerenciadorDeUsuario.localizarCarona(idSessao,
				"", ""), "{carona1ID,carona2ID,carona3ID,carona4ID,carona5ID}");
	}

	//TESTES RELACIONADOS AO US03
	
	
}
*/