package com.br.uepb.business;

import java.util.ArrayList;
import java.util.List;

import com.br.uepb.business.utilities.CorreioEletronico;
import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.SolicitacaoDomain;
import com.br.uepb.domain.UsuarioDomain;
import com.br.uepb.persistencia.Persistencia;

/**
 * Classe para visualização de perfil do usuário
 * Um perfil contém dados do usuário, histórico de caronas, qualificação de caronas
 * qualificação do usuário, caronas que faltou  
 *
 */
public class VisualizadorPerfil {
	
	private Persistencia persistenciaBD;
	
	public VisualizadorPerfil(){
		persistenciaBD = new Persistencia();
	}
	
	/**
	 * Pegar um atributo específico de uma carona
	 * @param idUsuario ID do usuário
	 * @param atributo Atributos válidos: nome | endereco | historico de caronas |
	 * 									  vagas | historico de vagas em caronas	 |
	 * 									  caronas seguras e tranquilas 			 |
	 * 									  caronas que não funcionaram 			 |
	 * 									  faltas em vagas de caronas 			 |
	 * 									  presenças em vagas de caronas
	 * @return
	 * @throws ECaronaException
	 */
	public String getAtributoPerfil(String idUsuario, String atributo) throws ECaronaException {
		
		UsuarioDomain usuario = persistenciaBD.getUsuarioBD().getUsuario(idUsuario);
		if(usuario.equals(null)) throw new ECaronaException(MensagensDeErro.USUARIO_INEXISTENTE);
		
		switch(atributo){
		case "nome":
			return usuario.getNome();
		case "endereco":
			return usuario.getEndereco();
		case "email":
			return usuario.getEmail();
		case "historico de caronas":
			return historicoCarona(usuario);
		case "historico de vagas em caronas":
			return historicoDeVagas(usuario);
		case "caronas seguras e tranquilas":
			return getTotalCaronaTranquilas(usuario);
		case "caronas que não funcionaram":
			return getTotalCaronasRuim(usuario.getCaronas());
		case "faltas em vagas de caronas":
			return getTotalFaltas(idUsuario);			
		case "presenças em vagas de caronas":
			return getTotalPresencas(idUsuario);
		default:
			throw new ECaronaException(MensagensDeErro.OPCAO_INVALIDA);
		}
	}

	private String getTotalCaronasRuim(List<CaronaDomain> caronas) {
		int total = 0;
		for(CaronaDomain carona : caronas){
			if(carona.getNaoFuncionou() != null){
				if(carona.getNaoFuncionou().split(",").length > 0){
					total++;
				}
			}
		}
		return String.valueOf(total);
	}

	/**
	 * Visualizar perfil do usuário (ainda não implementado)
	 * @param idSessao ID da sessão do usuário
	 * @param login ID do usuário
	 * @return o nome do usuário
	 * @throws ECaronaException caso o usuário não exista na base
	 */
	public String visualizarPerfil(String idSessao, String login) throws ECaronaException {
		UsuarioDomain usuario = persistenciaBD.getUsuarioBD().getUsuario(login);
		List<CaronaDomain> caronas = persistenciaBD.getCaronaBD().list();
		if(usuario == null) throw new ECaronaException(MensagensDeErro.USUARIO_INEXISTENTE);
		return usuario.getNome();
	}
	
	/**
	 * Visualizar historico de caronas de um usuário
	 * @param usuario UsuarioDomain que se deseja visualizar o perfil
	 * @return Listagem das caronas do usuário
	 */
	private String historicoCarona(UsuarioDomain usuario){
		String historico = "[";
		for(CaronaDomain carona : usuario.getCaronas()){
			historico += carona.getIdCarona();
			historico += ",";
		}
		if(historico.length() > 1) {
			historico = historico.substring(0, (historico.length()-1));
		}
		
		historico += "]";
		return historico;
	}
	
	private String historicoDeVagas(UsuarioDomain usuario){
		
		String historico = "[";
		List<CaronaDomain> caronas = persistenciaBD.getCaronaBD().list();
		List<SolicitacaoDomain> solicitacoes = persistenciaBD.getSolicitacaoBD().list();
		for(CaronaDomain carona : caronas){
			for(SolicitacaoDomain solicitacao : solicitacoes){
				if(solicitacao.getIdCarona().equals(carona.getId())){
					SessaoDomain sessao = persistenciaBD.getSessaoBD().getSessao(solicitacao.getIdSessaoSolicitante());
					if(sessao.getIdUsuario().equals(usuario.getLogin())){
						historico = historico + carona.getId() + ",";
						break;
					}
				}
			}
		}
		if(historico.length() > 1) {
			historico = historico.substring(0, (historico.length()-1));
		}
		
		historico += "]";
		
		return historico;
	}
	
	private String getTotalCaronaTranquilas(UsuarioDomain usuario){
		int total = 0;
		for(CaronaDomain carona : usuario.getCaronas()){
			if(carona.foiConcluida()){
				if(carona.getTranquila() != null)
					if(carona.getTranquila().split(",").length > 0){
						total++;
					}
			}
		}
		return String.valueOf(total);
	}
	
	private String getTotalFaltas(String idUsuario){
		int total = 0;
		for(SolicitacaoDomain solicitacao : persistenciaBD.getSolicitacaoBD().list()){
			SessaoDomain sessao = persistenciaBD.getSessaoBD().getSessao(solicitacao.getIdSessaoSolicitante());
			if(sessao.getIdUsuario().equals(idUsuario) && solicitacao.isFaltou()){
				total++;
			}
		}
		return String.valueOf(total);
	}
	
	private String getTotalPresencas(String idUsuario){
		int total = 0;
		for(SolicitacaoDomain solicitacao : persistenciaBD.getSolicitacaoBD().list()){
			SessaoDomain sessao = persistenciaBD.getSessaoBD().getSessao(solicitacao.getIdSessaoSolicitante());
			if(sessao.getIdUsuario().equals(idUsuario) && !solicitacao.isFaltou()){
				CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(solicitacao.getIdCarona());
				if(carona.foiConcluida()){
					total++;
				}
			}
		}
		return String.valueOf(total);
	}

	public void reviewVagaEmCarona(String idSessao, String idCarona,
			String loginCaroneiro, String review) throws ECaronaException {
		
		SolicitacaoDomain solicitacao = null;
		
		for(SolicitacaoDomain s : persistenciaBD.getSolicitacaoBD().list()){
			if(s.getIdCarona().equals(idCarona)){
				SessaoDomain sessao = persistenciaBD.getSessaoBD().getSessao(s.getIdSessaoSolicitante());
				if(sessao.getIdUsuario().equals(loginCaroneiro)){
					solicitacao = s;
					break;
				}
			}
		}
		if (solicitacao != null) {
			CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(idCarona);
			carona.foiConcluida(true);
			persistenciaBD.getCaronaBD().update(carona);
			
			switch (review) {

			case "faltou":
				solicitacao.setFaltou(true);
				persistenciaBD.getSolicitacaoBD().update(solicitacao);
				break;

			case "não faltou":
				solicitacao.setFaltou(false);
				persistenciaBD.getSolicitacaoBD().update(solicitacao);
				break;
				
			default:
				throw new ECaronaException(MensagensDeErro.OPCAO_INVALIDA);
			}
		}
		else{
			throw new ECaronaException(MensagensDeErro.USUARIO_CLADESTINO);
		}
	}

	public void reviewCarona(String idSessao, String idCarona, String review)
		throws ECaronaException{
		
		SolicitacaoDomain solicitacao = null;
		SessaoDomain sessaoPassageiro = persistenciaBD.getSessaoBD().getSessao(idSessao);
		CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(idCarona);
		UsuarioDomain usuario = persistenciaBD.getUsuarioBD().getUsuario(carona.getUsuarioLogin());
		
		for(SolicitacaoDomain s : persistenciaBD.getSolicitacaoBD().list()){
			if(s.getIdCarona().equals(idCarona)){
				SessaoDomain sessao = persistenciaBD.getSessaoBD().getSessao(s.getIdSessaoSolicitante());
				if(sessao.getIdUsuario().equals(sessaoPassageiro.getIdUsuario())){
					solicitacao = s;
					break;
				}
			}
		}
		
		if (solicitacao != null) {
			carona.foiConcluida(true);
			persistenciaBD.getCaronaBD().update(carona);
			
			switch (review) {

			case "não funcionou":
				removeCarona(carona, usuario);
				if(carona.getNaoFuncionou() == null){
					carona.setNaoFuncionou(sessaoPassageiro.getId());
				}
				else{
					carona.setNaoFuncionou(carona.getNaoFuncionou() + ", "  + sessaoPassageiro.getId());
				}
				usuario.adicionarCarona(carona);
				persistenciaBD.getCaronaBD().update(carona);
				break;
			case "segura e tranquila":
				removeCarona(carona, usuario);
				if(carona.getTranquila() == null || carona.getTranquila().isEmpty()){
					carona.setTranquila(sessaoPassageiro.getId());
				}
				else{
				carona.setTranquila(carona.getTranquila() + ", "  + sessaoPassageiro.getId());
				}
				usuario.adicionarCarona(carona);
				persistenciaBD.getUsuarioBD().update(usuario);
				persistenciaBD.getCaronaBD().update(carona);
				break;

			default:
				throw new ECaronaException(MensagensDeErro.OPCAO_INVALIDA);
			}
		}
		else{
			throw new ECaronaException(MensagensDeErro.USUARIO_CLADESTINO);
		}
		
		
	}

	private void removeCarona(CaronaDomain carona, UsuarioDomain usuario) {
		for(CaronaDomain c : usuario.getCaronas()){
			if(c.getId().equals(carona.getId())){
				usuario.getCaronas().remove(c);
				break;
			}
		}
	}

	public String verificarMensagensPerfil(String idSessao) {
		InteresseBusiness gerenciadorInteresse = new InteresseBusiness();
		return gerenciadorInteresse.verificarNotificacoes(idSessao);
	}	
	
	public boolean enviarEmail(String idSessao, String emailDestino, String mensagem){
		SessaoDomain sessao = persistenciaBD.getSessaoBD().getSessao(idSessao);
		UsuarioDomain usuario = persistenciaBD.getUsuarioBD().getUsuario(sessao.getIdUsuario());
		String assunto = "[Notificação ECarona]";
		
		CorreioEletronico correio = new CorreioEletronico();
		try {
			correio.enviaEmail(usuario.getEmail(), emailDestino, assunto, mensagem);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
	}
}
