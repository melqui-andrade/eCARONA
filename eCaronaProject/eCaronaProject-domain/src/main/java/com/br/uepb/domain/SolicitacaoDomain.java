package com.br.uepb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
}
