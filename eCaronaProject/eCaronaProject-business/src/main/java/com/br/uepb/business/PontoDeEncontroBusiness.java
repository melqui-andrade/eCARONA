package com.br.uepb.business;

import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SolicitacaoDomain;
import com.br.uepb.persistencia.Persistencia;

import servicesBackup.PersistenciaDAO;

/**
  * Implementação das regras de negócio de PontoDeEncontro
 * Todas as ações relacionadas ao gerenciamento de caronas se encontra nessa classe
 */
public class PontoDeEncontroBusiness {

	private PersistenciaDAO persistenciaDAO;
	private Persistencia persistenciaBD;	

	/**
	 * Contrutor da classe
	 * @param persistencia Entidade responsavel em persistir os dados do sistema
	 */
	public PontoDeEncontroBusiness(PersistenciaDAO persistencia){
		this.persistenciaDAO = persistencia;
		this.persistenciaBD = new Persistencia();
	}
	
	/**
	 * Sugerir um ponto de encontro para uma dada solicitação de carona
	 * @param idSessao ID da Sessão do usuário solicitante 
	 * @param idCarona ID da carona requisitada
	 * @param pontosSugeridos Locais de encontro sugeridos pelo usuário solicitante
	 * @throws ECaronaException caso pontosSugeridos seja nulo ou vazio
	 */
	public void sugerirPontoEncontro(String idSessao, String idCarona,
			String[] pontosSugeridos) throws ECaronaException {
		
		if(pontosSugeridos.length == 0 || pontosSugeridos.equals(null)){
			throw new ECaronaException(MensagensDeErro.PONTO_INVALIDO);
		}
		for(String ponto : pontosSugeridos){
			if(ponto.isEmpty()) throw new ECaronaException(MensagensDeErro.PONTO_INVALIDO);
		}
	}
	
	/**
	 * Responder uma sugestão de pontos de encontro enviando outros pontos de encontro
	 * @param idSessao ID da Sessão do usuário solicitante 
	 * @param idCarona ID da carona requisitada
	 * @param idSegestao ID da sugestão que se deseja mandar a resposta
	 * @param pontosSugeridos Novos locais de encontro sugeridos pelo usuário solicitante
	 * @throws ECaronaException caso pontosSugeridos seja nulo ou vazio
	 */
	public void responderSugestaoPontoEncontro(String idSessao,
			String idCarona, String idSegestao, String[] pontosSugeridos) throws ECaronaException {		

		if(pontosSugeridos.length == 0 || pontosSugeridos.equals(null)){
			throw new ECaronaException(MensagensDeErro.PONTO_INVALIDO);
		}
		for(String ponto : pontosSugeridos){
			if(ponto.isEmpty()) throw new ECaronaException(MensagensDeErro.PONTO_INVALIDO);
		}
	}

	/**
	 * Acertar ponto de encontro de uma carona
	 * @param idSessao ID da sessão do usuário que fornece a carona
	 * @param idSolicitacao ID da solicitação
	 * @throws ECaronaException caso ID da solicitação seja nulo ou a solicitação já tenha sido aceita
	 */
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
