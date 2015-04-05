package com.br.uepb.business;

import java.util.ArrayList;

import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;

public class SessaoBusiness {
	
	private ArrayList<SessaoDomain> sessoes;
	
	public SessaoBusiness(){
		
	}
	
	public void adicionaSessao(String id, UsuarioDomain usuario){
		SessaoDomain sessao = new SessaoDomain(id, usuario);
		sessoes.add(sessao);
	}
	
	public SessaoDomain getSessao(String idSessao){
		SessaoDomain sessao = null;
		for(SessaoDomain s : sessoes){
			if(s.getId() == idSessao){
				sessao = s;
			}
		}		
		return sessao;
	}
	
	public boolean ehSessaoValida(String idSessao){
		
		if(idSessao == null || idSessao == "") return false;
		
		for(SessaoDomain s : sessoes){
			if(s.getId() == idSessao){
				return true;
			}
		}
		return false;
	}
}
