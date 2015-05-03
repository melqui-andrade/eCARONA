package com.br.uepb.business;

import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SolicitacaoDomain;
import com.br.uepb.persistencia.Persistencia;

import servicesBackup.PersistenciaDAO;

public class PontoDeEncontroBusiness {

	private PersistenciaDAO persistenciaDAO;
	private Persistencia persistenciaBD;	

	public PontoDeEncontroBusiness(PersistenciaDAO persistencia){
		this.persistenciaDAO = persistencia;
		this.persistenciaBD = new Persistencia();
	}
	
	public void sugerirPontoEncontro(String idSessao, String idCarona,
			String[] pontosSugeridos) throws ECaronaException {
		
		if(pontosSugeridos.length == 0 || pontosSugeridos.equals(null)){
			throw new ECaronaException(MensagensDeErro.PONTO_INVALIDO);
		}
		for(String ponto : pontosSugeridos){
			if(ponto.isEmpty()) throw new ECaronaException(MensagensDeErro.PONTO_INVALIDO);
		}
	}
	
	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSegestao, String[] pontosSugeridos) throws ECaronaException {		

		if(pontosSugeridos.length == 0 || pontosSugeridos.equals(null)){
			throw new ECaronaException(MensagensDeErro.PONTO_INVALIDO);
		}
		for(String ponto : pontosSugeridos){
			if(ponto.isEmpty()) throw new ECaronaException(MensagensDeErro.PONTO_INVALIDO);
		}
	}

	public void aceitarPontoDeEncontro(String idSessao, String idSolicitacao) throws ECaronaException {

		
		
		SolicitacaoDomain solicitacao = persistenciaDAO.getSolicitacaoBD().get(idSolicitacao);
		if(solicitacao.equals(null)){
			throw new ECaronaException(MensagensDeErro.SOLICITACAO_INEXISTENTE);		
		}
		else{
			if(!solicitacao.foiAceita()){
				CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(solicitacao.getIdCarona());
				carona.setVagas(carona.getVagas()-1);
				solicitacao.foiAceita(true);
				persistenciaBD.getCaronaBD().update(carona);
				solicitacao.foiAceita(true);
			}
			else{throw new ECaronaException(MensagensDeErro.SOLICITACAO_INEXISTENTE);}			
			
		}			
	}
}
