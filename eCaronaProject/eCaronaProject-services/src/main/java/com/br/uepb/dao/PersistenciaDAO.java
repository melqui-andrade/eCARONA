package com.br.uepb.dao;

import java.util.HashMap;
import java.util.Map;

import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;

public class PersistenciaDAO {
	
	private Map<String, UsuarioDomain> usuarioBD;
	private Map<String, SessaoDomain> sessaoBD;
	private Map<String, CaronaDomain> caronaBD;
	
	public PersistenciaDAO(){
		this.usuarioBD = new HashMap<String, UsuarioDomain>();
		this.sessaoBD = new HashMap<String, SessaoDomain>();
		this.caronaBD = new HashMap<String, CaronaDomain>();
	}	
	
	/**
	 * @return the usuarioBD
	 */
	public Map<String, UsuarioDomain> getUsuarioBD() {
		return usuarioBD;
	}

	/**
	 * @return the sessaoBD
	 */
	public Map<String, SessaoDomain> getSessaoBD() {
		return sessaoBD;
	}
	
	/**
	 * @return the caronaBD
	 */
	public Map<String, CaronaDomain> getCaronaBD() {
		return caronaBD;
	}
	
}
