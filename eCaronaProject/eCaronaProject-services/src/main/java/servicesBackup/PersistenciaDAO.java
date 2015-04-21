package servicesBackup;

import java.util.HashMap;
import java.util.Map;

import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.SolicitacaoDomain;
import com.br.uepb.domain.UsuarioDomain;

public class PersistenciaDAO {
	
	private Map<String, UsuarioDomain> usuarioBD;
	private Map<String, SessaoDomain> sessaoBD;
	private Map<String, CaronaDomain> caronaBD;
	private Map<String, SolicitacaoDomain> solicitacaoBD;
	
	public PersistenciaDAO(){
		this.usuarioBD = new HashMap<String, UsuarioDomain>();
		this.sessaoBD = new HashMap<String, SessaoDomain>();
		this.caronaBD = new HashMap<String, CaronaDomain>();
		this.solicitacaoBD = new HashMap<String, SolicitacaoDomain>();
	}	
	
	/**
	 * @return o banco de dados de usuarios
	 */
	public Map<String, UsuarioDomain> getUsuarioBD() {
		return usuarioBD;
	}

	/**
	 * @return o banco de dados de sessoes
	 */
	public Map<String, SessaoDomain> getSessaoBD() {
		return sessaoBD;
	}
	
	/**
	 * @return o banco de dados de caronas
	 */
	public Map<String, CaronaDomain> getCaronaBD() {
		return caronaBD;
	}
	
	/**
	 * 
	 * @return o banco de dados de solicitações de carona
	 */
	public Map<String, SolicitacaoDomain> getSolicitacaoBD(){
		return solicitacaoBD;
	}
	
}
