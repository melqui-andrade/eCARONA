package com.br.uepb.business;

import java.util.ArrayList;
import java.util.Random;

import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.SolicitacaoDomain;
import com.br.uepb.domain.SugestaoEncontroDomain;
import com.br.uepb.domain.UsuarioDomain;
import com.br.uepb.persistencia.Persistencia;

import servicesBackup.PersistenciaDAO;

/**
 * Implementação das regras de negócio de SolicitacaoDomain
 * Todas as ações relacionadas ao gerenciamento de solicitações se encontram nessa classe
 */
public class SolicitacaoBusiness {

	private Persistencia persistenciaBD;

	public SolicitacaoBusiness() {
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

		if (pontosSugeridos.length == 0 || pontosSugeridos.equals(null)) {
			throw new ECaronaException(MensagensDeErro.PONTO_INVALIDO);
		}
		for (String ponto : pontosSugeridos) {
			if (ponto.isEmpty())
				throw new ECaronaException(MensagensDeErro.PONTO_INVALIDO);
		}
		//TODO Nada implementado nesse método
	}

	/**
	 * Solicitar uma vaga em uma carona
	 * @param idSessaoDoSolicitante Id do usuário interessado na carona
	 * @param idCarona Id da carona desejada
	 * @param local ponto de encontro sugerido pelo interessado
	 * @return ID da solicitação feita
	 */
	public String solicitarVaga(String idSessaoDoSolicitante, String idCarona,
			String local) {
		
		CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(idCarona);		
		String idSolicitacao = "solic" + (carona.getSolicitacoes().size() + 1);

		SugestaoEncontroDomain sugestao = new SugestaoEncontroDomain();
		sugestao.foiAceita(false);
		sugestao.foiRejeitada(false);
		sugestao.setIdCarona(idCarona);
		sugestao.setIdSessao(idSessaoDoSolicitante);
		sugestao.setLocal(local);
		
		SolicitacaoDomain novaSolicitacao = new SolicitacaoDomain();
		novaSolicitacao.setSessaoSolicitante(idSessaoDoSolicitante);
		novaSolicitacao.setId(idSolicitacao);
		novaSolicitacao.setIdCarona(idCarona);
		novaSolicitacao.adicionarSugestao(sugestao);
		novaSolicitacao.foiAceita(false);
		novaSolicitacao.foiRejeitada(false);
		
		carona.adicionarSolicitacao(novaSolicitacao);
		persistenciaBD.getCaronaBD().update(carona);

		return idSolicitacao;
	}

	/**
	 * Solicitar uma vaga em uma carona
	 * @param idSessaoDoSolicitante Id do usuário interessado na carona
	 * @param idCarona Id da carona desejada
	 * @return ID da solicitação feita
	 */
	public String solicitarVaga(String idSessaoDoSolicitante, String idCarona) {
		
		CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(idCarona);
		String idSolicitacao = "solic" + (carona.getSolicitacoes().size() + 1);

		SolicitacaoDomain novaSolicitacao = new SolicitacaoDomain();
		novaSolicitacao.setSessaoSolicitante(idSessaoDoSolicitante);
		novaSolicitacao.setId(idSolicitacao);
		novaSolicitacao.setIdCarona(idCarona);
		novaSolicitacao.foiAceita(false);
		novaSolicitacao.foiRejeitada(false);
		
		carona.adicionarSolicitacao(novaSolicitacao);
		persistenciaBD.getCaronaBD().update(carona);
		
		return idSolicitacao;
	}

	/**
	 * Aceitar uma solicitação de carona
	 * @param idSessao ID da sessão do usuário que fornece a carona
	 * @param idSolicitacao ID da solicitação a ser aceita
	 * @throws ECaronaException caso a solicitação não exista na base ou a 
	 * solicitação já tenha sido aceita
	 */
	public void aceitarSolicitacao(String idSessao, String idSolicitacao)
			throws ECaronaException {

		SolicitacaoDomain solicitacao = persistenciaBD.getSolicitacaoBD().
				getSolicitacao(idSolicitacao);
		if (solicitacao.equals(null)) {
			throw new ECaronaException(MensagensDeErro.SOLICITACAO_INEXISTENTE);
		} else {
			if (!solicitacao.foiAceita()) {				
				CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(
						solicitacao.getIdCarona());
				
				carona.setVagas(carona.getVagas() - 1);
			
				solicitacao.foiAceita(true);
				
				carona.adicionarSolicitacao(solicitacao);
				
				persistenciaBD.getCaronaBD().update(carona);
			} else {
				throw new ECaronaException(
						MensagensDeErro.SOLICITACAO_INEXISTENTE);
			}

		}

	}

	/**
	 * Rejeitar uma solicitação de carona
	 * @param idSessao ID da sessão do usuário fornecedor da carona
	 * @param idSolicitacao ID da solicitação de carona
	 * @throws ECaronaException caso a solicitação já tenha sido rejeitada
	 */
	public void rejeitarSolicitacao(String idSessao, String idSolicitacao)
			throws ECaronaException {

		SolicitacaoDomain solicitacao = persistenciaBD.getSolicitacaoBD().
				getSolicitacao(idSolicitacao);

		if (!solicitacao.foiRejeitada()) {
			solicitacao.foiRejeitada(true);
			
		} else {
			throw new ECaronaException(MensagensDeErro.SOLICITACAO_INEXISTENTE);
		}
	}
	
	/**
	 * Pegar o conjunto de solicitações pendentes para uma dada carona
	 * @param idSessao ID da sessão do usuário que fornece a carona
	 * @param idCarona ID da carona
	 * @return IDs das solicitações pendentes no formato "{{IDsolicitacao01, IDsolicitacao02, ...}"
	 */
	public String getSolicitacoesPendentes(String idSessao, String idCarona){
		CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(idCarona);
		StringBuilder saidaSolicitacoes = new StringBuilder();
		saidaSolicitacoes.append("{");
		for(SolicitacaoDomain solicitacao : carona.getSolicitacoes()){
			if(!solicitacao.foiAceita() && !solicitacao.foiRejeitada()){
				saidaSolicitacoes.append("{");
				saidaSolicitacoes.append(solicitacao.getId());
				saidaSolicitacoes.append("},");
			}
		}
		saidaSolicitacoes.deleteCharAt(saidaSolicitacoes.length()-1);	
		
		if(saidaSolicitacoes.toString().isEmpty()) return "{}";
		return saidaSolicitacoes.toString();
	}

	/**
	 * Pegar o conjunto de caronas confirmadas
	 * @param idSessao ID da sessão do usuário que fornece a carona
	 * @param idCarona ID da carona
	 * @return IDs das solicitações no formato "{IDsolicitacao01, IDsolicitacao02, ...}"
	 */
	public String getSolicitacoesConfirmadas(String idSessao, String idCarona) {		
		
		CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(idCarona);
		StringBuilder saidaSolicitacoes = new StringBuilder();
		saidaSolicitacoes.append("{");
		for(SolicitacaoDomain solicitacao : carona.getSolicitacoes()){
			if(solicitacao.foiAceita()){
				saidaSolicitacoes.append(solicitacao.getId());
				saidaSolicitacoes.append("},");
			}
		}
		saidaSolicitacoes.deleteCharAt(saidaSolicitacoes.length()-1);	
		
		return saidaSolicitacoes.toString();

	}

	/**
	 * Abandonar a vaga em uma carona. Marca a solicitação previamente confirmada
	 * como não aceita, e incrementa a quantidade de vagas na carona em +1
	 * @param idSessao ID da Sessao do usuário que solicitou a carona
	 * @param idCarona ID da carona solicitada
	 * @param idSolicitacao ID da solicitação efetuada
	 * @throws ECaronaException caso algum dos campos seja nulo ou vazio
	 */
	public void desistirRequisicao(String idSessao, String idCarona,
			String idSolicitacao) throws ECaronaException {
		SolicitacaoDomain solicitacao = persistenciaBD.getSolicitacaoBD().
				getSolicitacao(idSolicitacao);
		CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(
				solicitacao.getIdCarona());

		carona.setVagas(carona.getVagas() + 1);
		solicitacao.foiRejeitada(true);
		persistenciaBD.getCaronaBD().update(carona);
	}

	/**
	 * Pegar um atributo específico de uma solicitação
	 * @param idSolicitacao ID da solicitação
	 * @param atributo Atributos válidos: origem | destino | Dono da carona | Dono da solicitacao | Ponto de Encontro
	 * @return O valor do atributo especificado
	 * @throws Exception caso a carona não exista na base ou o atributo não seja válido
	 */
	public String getAtributoSolicitacao(String idSolicitacao, String atributo)
			throws ECaronaException {

		SolicitacaoDomain solicitacao = persistenciaBD.getSolicitacaoBD().
				getSolicitacao(idSolicitacao);

		if (atributo == "" || atributo.equals(null)) {
			throw new ECaronaException(MensagensDeErro.ATRIBUTO_INVALIDO);
		}
		CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(
				solicitacao.getIdCarona());
		SessaoDomain sessaoDono = persistenciaBD.getSessaoBD().
				getSessao(carona.getIdSessao());
		SessaoDomain sessaoSolicitante = persistenciaBD.getSessaoBD().
				getSessao(solicitacao.getIdSessaoSolicitante());
		UsuarioDomain dono = persistenciaBD.getUsuarioBD().
				getUsuario(sessaoDono.getIdUsuario());
		UsuarioDomain caroneiro = persistenciaBD.getUsuarioBD().
				getUsuario(sessaoSolicitante.getIdUsuario());

		if (solicitacao != null) {
			switch (atributo) {

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
		} else {
			throw new ECaronaException(MensagensDeErro.ITEM_INEXISTENTE);
		}

	}

}
