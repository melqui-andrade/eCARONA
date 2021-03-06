package com.br.uepb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representa uma sessão de determinado usuário no sistema
 * Guarda o id do usuário
 * @author Sidney
 *
 */
@Entity
@Table(name="SESSAO_BD")
public class SessaoDomain {

	@Id
	@Column(name="ID_SESSAO")
	private String idSessao;
	
	@Column(name="ESTA_ATIVA")
	private boolean estaAtiva;
	
	@Column(name="DATA_")
	private String data;
	
	@Column(name="HORA_")
	private String hora;
	
	@Column(name="USUARIO_LOGIN")
	private String usuarioLogin;


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

	/**
	 * @return the estaAtiva
	 */
	public boolean isEstaAtiva() {
		return estaAtiva;
	}

	/**
	 * @param estaAtiva the estaAtiva to set
	 */
	public void setEstaAtiva(boolean estaAtiva) {
		this.estaAtiva = estaAtiva;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * @param hora the hora to set
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * @return the usuarioLogin
	 */
	public String getIdUsuario() {
		return usuarioLogin;
	}

	/**
	 * @param usuarioLogin the usuarioLogin to set
	 */
	public void setIdUsuario(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}
	
	public String getId() {
		return idSessao;
	}

}
