package com.br.uepb.business;

import java.sql.Time;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

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
		long instante = System.currentTimeMillis();
		SolicitacaoDomain novaSolicitacao = new SolicitacaoDomain();
		String idSolicitacao = idSessaoDoSolicitante.substring(0,0) + idCarona.substring((idCarona.length()-1)) + instante;
		
		novaSolicitacao.setSessaoSolicitante(idSessaoDoSolicitante);
		novaSolicitacao.setId(idSolicitacao);
		novaSolicitacao.setIdCarona(idCarona);
		novaSolicitacao.setLocal(local);
		
		return idSolicitacao;
	}

	public void aceitarPontoDeEncontro(String idSessao, String idSolicitacao) {

	}
	
	
}
