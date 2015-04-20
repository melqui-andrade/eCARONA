package com.br.uepb.business;

import com.br.uepb.dao.PersistenciaDAO;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SolicitacaoDomain;

public class SolicitacaoBusiness {

	private PersistenciaDAO persistencia;

	public SolicitacaoBusiness(PersistenciaDAO persistencia){
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
		return "";
	}
	/**
	 * Solicita uma vaga em determinada carona
	 * @param idSessaoDoSolicitante Id do usuário interessado na carona
	 * @param idCarona Id da carona desejada
	 * @param local ponto de encontro sugerido pelo interessado
	 * @return id da solicitação feita
	 */
	public String solicitarVaga(String idSessaoDoSolicitante, String idCarona, String local){
		
		SolicitacaoDomain novaSolicitacao = new SolicitacaoDomain();
		novaSolicitacao.setId("");
		return "";
	}

	public void aceitarPontoDeEncontro(String idSessao, String idSolicitacao) {

	}
	
	
}
