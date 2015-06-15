package com.br.uepb.business;

import java.util.List;

import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SolicitacaoDomain;
import com.br.uepb.domain.SugestaoEncontroDomain;
import com.br.uepb.persistencia.Persistencia;

/**
  * Implementação das regras de negócio de PontoDeEncontro
 * Todas as ações relacionadas ao gerenciamento de caronas se encontra nessa classe
 */
public class PontoDeEncontroBusiness {
	private Persistencia persistenciaBD;	

	/**
	 * Contrutor da classe
	 * @param persistencia Entidade responsavel em persistir os dados do sistema
	 */
	public PontoDeEncontroBusiness(){
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
		String idPontoSeEncontro = idSessao + "IN" + String.valueOf(System.currentTimeMillis());
		
		SugestaoEncontroDomain pontoDeEncontro = new SugestaoEncontroDomain();
		pontoDeEncontro.setIdSugestao(idPontoSeEncontro);
		pontoDeEncontro.foiAceita(false);
		pontoDeEncontro.foiRejeitada(false);
		pontoDeEncontro.setIdCarona(idCarona);
		pontoDeEncontro.setIdSessao(idSessao);
		StringBuilder locais = new StringBuilder();
		
		for(String local : pontosSugeridos){
			locais.append(local);
			locais.append(";");
		}
		locais.deleteCharAt(locais.length()-1);
		pontoDeEncontro.setLocal(locais.toString());
		SolicitacaoDomain solicitacao = buscaSolicitacao(idCarona, idSessao);
		if(solicitacao != null){
		solicitacao.adicionarSugestao(pontoDeEncontro);
		persistenciaBD.getSolicitacaoBD().update(solicitacao);
		persistenciaBD.getSugestaoEncontroBD().save(pontoDeEncontro);
		}
		else{
			solicitacao = new SolicitacaoDomain();
			String idSolicitacao = "solic" + (persistenciaBD.getSolicitacaoBD().list().size() + 1);
			solicitacao.setId(idSolicitacao);
			solicitacao.setIdCarona(idCarona);
			solicitacao.foiAceita(false);
			solicitacao.foiRejeitada(false);
			solicitacao.adicionarSugestao(pontoDeEncontro);
			persistenciaBD.getSolicitacaoBD().save(solicitacao);
		}		
	}
	
	private SolicitacaoDomain buscaSolicitacao(String idCarona, String idSolicitante){
		
		
		for(SolicitacaoDomain s : persistenciaBD.getSolicitacaoBD().list()){
			if(s.getIdSessaoSolicitante().equals(idSolicitante) &&
					s.getIdCarona().equals(idCarona)){
				return s;
			}
		}
		return null;
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
			String idCarona, String idSugestao, String[] pontosSugeridos) throws ECaronaException {		

		if(pontosSugeridos.length == 0 || pontosSugeridos.equals(null)){
			throw new ECaronaException(MensagensDeErro.PONTO_INVALIDO);
		}
		for(String ponto : pontosSugeridos){
			if(ponto.isEmpty()) throw new ECaronaException(MensagensDeErro.PONTO_INVALIDO);
		}
		SugestaoEncontroDomain sugestaoAntiga = persistenciaBD.getSugestaoEncontroBD().getSugestaoEncontro(idSugestao);
		sugestaoAntiga.foiRejeitada(true);
		SugestaoEncontroDomain sugestaoNova = new SugestaoEncontroDomain();
		sugestaoNova.foiAceita(false);
		sugestaoNova.foiRejeitada(false);
		sugestaoNova.setIdCarona(idCarona);
		sugestaoNova.setIdSessao(idSessao);
		StringBuilder locais = new StringBuilder();
		
		for(String local : pontosSugeridos){
			locais.append(local);
			locais.append(",");
		}
		locais.deleteCharAt(locais.length()-1);
		sugestaoNova.setLocal(locais.toString());
		persistenciaBD.getSugestaoEncontroBD().save(sugestaoNova);		
		
	}

	/**
	 * Acertar ponto de encontro de uma carona
	 * @param idSessao ID da sessão do usuário que fornece a carona
	 * @param idSolicitacao ID da solicitação
	 * @throws ECaronaException caso ID da solicitação seja nulo ou a solicitação já tenha sido aceita
	 */
	public void aceitarPontoDeEncontro(String idSessao, String idSolicitacao) throws ECaronaException {

		
		
		SolicitacaoDomain solicitacao = persistenciaBD.getSolicitacaoBD().getSolicitacao(idSolicitacao);
		SugestaoEncontroDomain sugestao = buscaPontoDeEncontro(solicitacao.getIdSessaoSolicitante(), solicitacao.getIdCarona());
		
		if(solicitacao.equals(null)){
			throw new ECaronaException(MensagensDeErro.SOLICITACAO_INEXISTENTE);		
		}
		else{
			if(!solicitacao.foiAceita()){
				CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(solicitacao.getIdCarona());
				if(sugestao != null){
					sugestao.foiAceita(true);
					persistenciaBD.getSugestaoEncontroBD().update(sugestao);				
				}
				carona.setVagas(carona.getVagas()-1);
				carona.setPontoDeEncontro(sugestao.getIdSugestao(), sugestao.getLocal().split(","));;
				solicitacao.foiAceita(true);
				persistenciaBD.getSolicitacaoBD().update(solicitacao);
				persistenciaBD.getCaronaBD().update(carona);
				
			}
			else{throw new ECaronaException(MensagensDeErro.SOLICITACAO_INEXISTENTE);}			
			
		}			
	}
	
	public void zerarBase(){
		persistenciaBD.getSugestaoEncontroBD().excluirTudo();
	}

	public String getPontosSugeridos(String idSessao, String idCarona) throws ECaronaException {
		List<SugestaoEncontroDomain> pontosDeEncontro = persistenciaBD.
				getSugestaoEncontroBD().list();
		
		String locais = buscaSugestaoDeEncontro(pontosDeEncontro, idSessao, idCarona);
		
		if(locais.length() < 2){
			throw new ECaronaException(MensagensDeErro.PONTO_INVALIDO);
		}
		else{
			return locais;
		}
	}
	
	private String buscaSugestaoDeEncontro
	(List<SugestaoEncontroDomain> pontos, String idSessao, String idCarona){
		
		StringBuilder locais = new StringBuilder();
		locais.append("[");
		for(SugestaoEncontroDomain p : pontos){
			if(p.getIdCarona().equals(idCarona)){
				locais.append(p.getLocal());
				locais.append(";");
			}
		}
		locais.deleteCharAt(locais.length()-1);
		locais.append("]");
		return locais.toString();
	}
	
	private SugestaoEncontroDomain buscaPontoDeEncontro(String idSessao, String idCarona){
		SugestaoEncontroDomain ponto = null;
		for(SugestaoEncontroDomain p : persistenciaBD.getSugestaoEncontroBD().list()){
			if(p.getIdSessao().equals(idSessao) && p.getIdCarona().equals(idCarona)){
				ponto = p;
				break;
			}
		}
		
		return ponto;
	}

	public String getPontosConfirmados(String idSessao, String idCarona) {
		
		List<SugestaoEncontroDomain> pontosDeEncontro = persistenciaBD.
				getSugestaoEncontroBD().list();
		StringBuilder locais = new StringBuilder();
		locais.append("[");
		for(SugestaoEncontroDomain p : pontosDeEncontro){
			if(p.getIdCarona().equals(idCarona) && p.foiAceita()){
				locais.append(p.getLocal());
				locais.append(";");
			}
		}
		locais.deleteCharAt(locais.length()-1);
		locais.append("]");
		return locais.toString();
	}
	
	
}
