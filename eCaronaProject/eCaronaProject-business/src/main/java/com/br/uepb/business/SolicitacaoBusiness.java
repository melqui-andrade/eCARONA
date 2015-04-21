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
		long tempoCorrente = System.currentTimeMillis();
		String idSolicitacao = idSessaoDoSolicitante.substring((idSessaoDoSolicitante.length()-1)) +
				idCarona.substring((idCarona.length()-2), (idCarona.length()-1)) + tempoCorrente;
		SolicitacaoDomain novaSolicitacao = new SolicitacaoDomain();
		
		novaSolicitacao.setId(idSolicitacao);
		novaSolicitacao.setSessaoSolicitante(idSessaoDoSolicitante);
		novaSolicitacao.setIdCarona(idCarona);
		novaSolicitacao.setLocal(local);
		
		persistencia.getSolicitacaoBD().put(idSolicitacao, novaSolicitacao);
		return idSolicitacao;
	}

	public void aceitarPontoDeEncontro(String idSessao, String idSolicitacao) {

	}

	public String getAtributoSolicitacao(String idSolicitacao, String atributo) throws ECaronaException {
		SolicitacaoDomain solicitacao = persistencia.getSolicitacaoBD().get(idSolicitacao);
		
		if(atributo == "" || atributo.equals(null)){
			throw new ECaronaException(MensagensDeErro.ATRIBUTO_INVALIDO);
		}
		CaronaDomain carona = persistencia.getCaronaBD().get(solicitacao.getIdCarona());
		UsuarioDomain dono = persistencia.getUsuarioBD().get(carona.getUsuarioLogin());
		SessaoDomain sessaoSolicitante = persistencia.getSessaoBD().get(solicitacao.getIdSessaoSolicitante());
		UsuarioDomain caroneiro = persistencia.getUsuarioBD().get(sessaoSolicitante.getUsuarioLogin());
		
		if(persistencia.getSolicitacaoBD().containsKey(idSolicitacao)){
		switch(atributo){
		
		case "origem":			
			return carona.getOrigem();
		
		case "destino":
			return carona.getOrigem();
		
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
