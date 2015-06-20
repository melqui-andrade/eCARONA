package com.br.uepb.business;

import java.util.ArrayList;
import java.util.List;

import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.InteresseDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;
import com.br.uepb.persistencia.Persistencia;

public class InteresseBusiness {
	
	private Persistencia persistenciaBD;
	
	public InteresseBusiness(){
		this.persistenciaBD = new Persistencia();
	}
	
	public String cadastrarInteresse(String idSessao, String origem, String destino,
			String data, String horaInicio, String horaFim) throws ECaronaException{
		
		if(origem == null || origem.length() <= 1){
			throw new ECaronaException(MensagensDeErro.ORIGEM_INVALIDA);
		}
		if(destino == null || destino.length() <= 1){
			throw new ECaronaException(MensagensDeErro.DESTINO_INVALIDO);
		}
		if(data == null){
			throw new ECaronaException(MensagensDeErro.DATA_INVALIDA);
		}
		
		InteresseDomain interesse = new InteresseDomain();
		
		interesse.setIdSessao(idSessao);
		interesse.setOrigem(origem);
		interesse.setDestino(destino);
		interesse.setData(data);
		interesse.setHorarioInicio(horaInicio);
		interesse.setHorarioFim(horaFim);
		interesse.setNotificacaoCarona("");
		
		persistenciaBD.getInteresseBD().save(interesse);
		
		return String.valueOf(interesse.getIdInteresse());
	}
	
	public String verificarNotificacoes(String idSessao){
		List<String> notificacoes = getNotificacoesDoUsuario(idSessao);
		String saida = "[";
		String msnInicio = "Carona cadastrada no dia ";
		String msnFim = " de acordo com os seus interesses registrados. Entrar em contato com ";
		
		for(String notificacao : notificacoes){
			CaronaDomain carona = persistenciaBD.getCaronaBD().getCarona(notificacao);
			UsuarioDomain usuario = persistenciaBD.getUsuarioBD().getUsuario(carona.getUsuarioLogin());
			saida += msnInicio + carona.getData() + ", às " + carona.getHora() + msnFim + usuario.getEmail() + "\n";
		}
		
		if(saida.length() > 1) saida = saida.substring(0, saida.length() -1);
		
		saida += "]";
		return saida;
	}
	
	private List<String> getNotificacoesDoUsuario(String idSessao){
		List<String> notificacoes = new ArrayList<String>(); 
		SessaoDomain sessaoParametro = persistenciaBD.getSessaoBD().getSessao(idSessao);
		for(InteresseDomain interesse : persistenciaBD.getInteresseBD().list()){
			SessaoDomain sessaoInteresse = persistenciaBD.getSessaoBD().getSessao(interesse.getIdSessao());
			if(sessaoParametro.getIdUsuario().equals(sessaoInteresse.getIdUsuario())){
				if(!interesse.getNotificacaoCarona().isEmpty()){
					notificacoes.add(interesse.getNotificacaoCarona());
				}
			}
		}
		return notificacoes;
	}
	
	public void zerarBase(){
		persistenciaBD.getInteresseBD().excluirTudo();
	}

}
