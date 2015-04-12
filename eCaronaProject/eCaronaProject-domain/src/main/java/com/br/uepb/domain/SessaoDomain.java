package com.br.uepb.domain;

/**
 * Representa uma sessão de determinado usuário no sistema
 * Guarda o id do usuário
 * @author Sidney
 *
 */
public class SessaoDomain {

	private String id;
	private UsuarioDomain usuario;

	public SessaoDomain(String id1, UsuarioDomain usuario1) {
		this.id = id1;
		this.usuario = usuario1;
	}	

	public String getId() {
		return id;
	}

	public UsuarioDomain getUsuario() {
		return usuario;
	}

}
