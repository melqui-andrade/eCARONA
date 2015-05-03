package com.br.uepb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SUGESTAO_ENCONTRO_BD")
public class SugestaoEncontroDomain {

	@Id
	@Column(name="ID_SUGESTAO_ENCONTRO")
	private String idSugestao;
	
	@Column(name="LOCAL")
	private String local;
	
	@Column(name="CARONA_ID")
	private String idCarona;
	
	@Column(name="SESSAO_ID")
	private String idSessao;

	/**
	 * @return the idSugestao
	 */
	public String getIdSugestao() {
		return idSugestao;
	}

	/**
	 * @param idSugestao the idSugestao to set
	 */
	public void setIdSugestao(String idSugestao) {
		this.idSugestao = idSugestao;
	}

	/**
	 * @return the local
	 */
	public String getLocal() {
		return local;
	}

	/**
	 * @param local the local to set
	 */
	public void setLocal(String local) {
		this.local = local;
	}

	/**
	 * @return the idCarona
	 */
	public String getIdCarona() {
		return idCarona;
	}

	/**
	 * @param idCarona the idCarona to set
	 */
	public void setIdCarona(String idCarona) {
		this.idCarona = idCarona;
	}

	/**
	 * @return the idSessao
	 */
	public String getIdSessao() {
		return idSessao;
	}

	/**
	 * @param idSessao the idSessao to set
	 */
	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}

	
}