package com.br.uepb.business;

import servicesBackup.PersistenciaDAO;

public class PontoDeEncontroBusiness {

	private PersistenciaDAO persistencia;

	public PontoDeEncontroBusiness(PersistenciaDAO persistencia){
		this.persistencia = persistencia;
	}
	
	/**
	 * 
	 * @param idSessao
	 * @param idCarona
	 * @param pontosSugeridos
	 * @return
	 */
	public String sugerirPontoEncontro(String idSessao, String idCarona,
			String[] pontosSugeridos) {
		return idSessao + idCarona;
	}

	public void aceitarPontoDeEncontro(String idSessao, String idSolicitacao) {

	}
}
