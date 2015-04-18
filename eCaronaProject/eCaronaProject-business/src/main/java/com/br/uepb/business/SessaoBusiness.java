package com.br.uepb.business;

import java.util.ArrayList;

import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;
/**
 * Gerenciador de sessão
 * As sessões criadas ficam amazenadas temporariamente em um banco de sessões
 * e são encerradas após comando do usuário
 * @author Sidney
 */
public class SessaoBusiness {
	
	private ArrayList<SessaoDomain> sessoes;
	
	public SessaoBusiness(){
		sessoes = new ArrayList<SessaoDomain>();
	}
	/**
	 * Cria uma nova sessão no sistema
	 * @param id : Identificador da sessão
	 * @param usuario : O usuário que vai logar a sessão
	 */
	public void adicionaSessao(String id, UsuarioDomain usuario){
		SessaoDomain sessao = new SessaoDomain(id, usuario);
		sessoes.add(sessao);
	}
	/**
	 * Busca uma sessão pelo seu ID
	 * @param idSessao ID da sessão
	 * @return Uma sessão
	 */
	public SessaoDomain getSessao(String idSessao){
		SessaoDomain sessao = null;
		for(SessaoDomain s : sessoes){
			if(s.getId() == idSessao){
				sessao = s;
			}
		}		
		return sessao;
	}
	/**
	 * Verifica se o ID passado pertence a alguma sessão do banco de sessões
	 * @param idSessao ID desejado
	 * @return True, caso exista alguma sessão com o tal ID
	 */
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
