package com.br.uepb.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SOLICITACAO_BD")
public class SolicitacaoDomain {
	
	@Id
	@Column(name="ID_SUGESTAO_ENCONTRO")
	private String id;
	
	@Column(name="ID_SESSAO_SOLICITANTE")
	private String idSessaoSolicitante;
	
	@Column(name="CARONA_ID")
	private String idCarona;
	
	@Column(name="LOCAL")
	private String local;
	
	@Column(name="FOI_ACEITA")
	private boolean foiAceita;
	
	@Column(name="FOI_REJEITADA")
	private boolean foiRejeitada;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<SugestaoEncontroDomain> sugestoesDeEncontro = new ArrayList<SugestaoEncontroDomain>();
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getIdCarona(){
		return this.idCarona;
	}
	public void setIdCarona(String idCarona){
		this.idCarona = idCarona;
	}	
	public String getIdSessaoSolicitante(){
		return idSessaoSolicitante;
	}
	public void setSessaoSolicitante(String idSessaoSolicitante){
		this.idSessaoSolicitante = idSessaoSolicitante;
	}
	
	public ArrayList<SugestaoEncontroDomain> getSugestoesDeEncontro(){
		return new ArrayList<SugestaoEncontroDomain>(this.sugestoesDeEncontro);
	}
	
	public void setSugestoesDeEncontro(Collection<SugestaoEncontroDomain> sugestoes){
		this.sugestoesDeEncontro = sugestoes;
	}
	
	public boolean foiAceita(){
		return this.foiAceita;
	}
	
	public void foiAceita(boolean foi){
		this.foiAceita = foi;
	}
	
	public boolean foiRejeitada(){
		return this.foiRejeitada;
	}
	
	public void foiRejeitada(boolean foi){
		this.foiRejeitada = foi;
	}
	
	public void adicionarSugestao(SugestaoEncontroDomain sugestao){
		this.sugestoesDeEncontro.add(sugestao);
	}
}
