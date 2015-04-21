package com.br.uepb.domain;

public class SolicitacaoDomain {
	private String id;
	private String idSessaoSolicitante;
	private String idCarona;
	private String local;
	
	
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
}
