package testesDeUnidadeBusiness;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.br.uepb.business.SessaoBusiness;
import com.br.uepb.constants.ECaronaException;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;

public class SessaoBusinessTest {
	private SessaoBusiness gerenciadorDeSessao;
	

	@Before
	public void setUp() {
		gerenciadorDeSessao = new SessaoBusiness();

	}

	@Test
	public void todosOsMetodosTest() throws ECaronaException {

		UsuarioDomain usuario1 = new UsuarioDomain();
		usuario1.setLogin("mark");
		usuario1.setSenha("m@rk");

		UsuarioDomain usuario2 = new UsuarioDomain();
		usuario2.setLogin("sidney");
		usuario2.setSenha("sid01");

		UsuarioDomain usuario3 = new UsuarioDomain();
		usuario3.setLogin("sidney2");
		usuario3.setSenha("sid02");

		gerenciadorDeSessao.adicionaSessao("ID01", usuario1);
		gerenciadorDeSessao.adicionaSessao("ID02", usuario2);
		gerenciadorDeSessao.adicionaSessao("ID03", usuario3);

		SessaoDomain sessao = gerenciadorDeSessao.getSessao("ID01");

		assertEquals(sessao.getId(), "ID01");
		

		sessao = gerenciadorDeSessao.getSessao("ID02");

		assertEquals(sessao.getId(), "ID02");

		sessao = gerenciadorDeSessao.getSessao("ID03");

		assertEquals(sessao.getId(), "ID03");
		
		assertFalse(gerenciadorDeSessao.ehSessaoValida(""));
		assertFalse(gerenciadorDeSessao.ehSessaoValida(null));
		assertFalse(gerenciadorDeSessao.ehSessaoValida("ID04"));

		
		assertTrue(gerenciadorDeSessao.ehSessaoValida("ID01"));
		assertTrue(gerenciadorDeSessao.ehSessaoValida("ID02"));
		assertTrue(gerenciadorDeSessao.ehSessaoValida("ID03"));


	}

}
