package com.br.uepb.business;

import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.SolicitacaoDomain;
import com.br.uepb.domain.UsuarioDomain;

import servicesBackup.PersistenciaDAO;

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
	 * @throws ECaronaException 
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
	 * @param idSessaoDoSolicitante Id do usu⳩o interessado na carona
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
		novaSolicitacao.foiAceita(false);
		persistencia.getSolicitacaoBD().put(idSolicitacao, novaSolicitacao);
		

		return idSolicitacao;
	}
	
	public String solicitarVaga(String idSessaoDoSolicitante, String idCarona){
		long instante = System.currentTimeMillis();
		SolicitacaoDomain novaSolicitacao = new SolicitacaoDomain();
		String idSolicitacao = idSessaoDoSolicitante.substring(0,0) + idCarona.substring((idCarona.length()-1)) + instante;
		
		novaSolicitacao.setSessaoSolicitante(idSessaoDoSolicitante);
		novaSolicitacao.setId(idSolicitacao);
		novaSolicitacao.setIdCarona(idCarona);
		novaSolicitacao.foiAceita(false);
		persistencia.getSolicitacaoBD().put(idSolicitacao, novaSolicitacao);
		

		return idSolicitacao;
	}
	
	

	public void aceitarSolicitacao(String idSessao, String idSolicitacao) throws ECaronaException {

				
		
			SolicitacaoDomain solicitacao = persistencia.getSolicitacaoBD().get(idSolicitacao);
			if(solicitacao.equals(null)){
				throw new ECaronaException(MensagensDeErro.SOLICITACAO_INEXISTENTE);		
			}
			else{
				if(!solicitacao.foiAceita()){
					CaronaDomain carona = persistencia.getCaronaBD().get(solicitacao.getIdCarona());
					carona.setVagas(carona.getVagas()-1);
					solicitacao.foiAceita(true);
				}
				else{throw new ECaronaException(MensagensDeErro.SOLICITACAO_INEXISTENTE);}			
				
			}			
		
	}
	
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao)throws ECaronaException{
		
		SolicitacaoDomain solicitacao = persistencia.getSolicitacaoBD().get(idSolicitacao);
		
		
		if(!solicitacao.foiRejeitada()){
			solicitacao.foiRejeitada(true);
		}
		else{throw new ECaronaException(MensagensDeErro.SOLICITACAO_INEXISTENTE);}
	}
	
	public void desistirRequisicao(String idSessao, String idCarona, String idSolicitacao) throws ECaronaException{
		SolicitacaoDomain solicitacao = persistencia.getSolicitacaoBD().get(idSolicitacao);
		CaronaDomain carona = persistencia.getCaronaBD().get(solicitacao.getIdCarona());
		
		carona.setVagas(carona.getVagas()+1);
		solicitacao.foiAceita(false);
	}


	public String getAtributoSolicitacao(String idSolicitacao, String atributo) throws ECaronaException {
		SolicitacaoDomain solicitacao = persistencia.getSolicitacaoBD().get(idSolicitacao);
		
		if(atributo == "" || atributo.equals(null)){
			throw new ECaronaException(MensagensDeErro.ATRIBUTO_INVALIDO);
		}
		CaronaDomain carona = persistencia.getCaronaBD().get(solicitacao.getIdCarona());
		SessaoDomain sessaoDono = persistencia.getSessaoBD().get(carona.getIdSessao());
		SessaoDomain sessaoSolicitante = persistencia.getSessaoBD().get(solicitacao.getIdSessaoSolicitante());
		UsuarioDomain dono = persistencia.getUsuarioBD().get(sessaoDono.getIdUsuario());
		UsuarioDomain caroneiro = persistencia.getUsuarioBD().get(sessaoSolicitante.getIdUsuario());
		
		if(persistencia.getSolicitacaoBD().containsKey(idSolicitacao)){
		switch(atributo){
		
		case "origem":			
			return carona.getOrigem();
		
		case "destino":
			return carona.getDestino();
		
		case "Dono da carona":
			return dono.getNome();
		
		case "Dono da solicitacao":
			return caroneiro.getNome();
			
		case "Ponto de Encontro":
			return solicitacao.getLocal();			
		
		default:
			throw new ECaronaException(MensagensDeErro.ATRIBUTO_INEXISTENTE);
		}
		}
		else{
			throw new ECaronaException(MensagensDeErro.ITEM_INEXISTENTE);
		}
		
		
		
		
	}

}
