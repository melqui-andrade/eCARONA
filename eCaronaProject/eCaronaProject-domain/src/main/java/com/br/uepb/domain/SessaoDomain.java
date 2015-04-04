package com.br.uepb.domain;

public class SessaoDomain {

	private String id;
	private UsuarioDomain usuario;
	private CaronaDomain carona;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UsuarioDomain getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDomain usuario) {
		this.usuario = usuario;
	}
	public CaronaDomain getCarona() {
		return carona;
	}
	public void setCarona(CaronaDomain carona) {
		this.carona = carona;
	}
	
	
}
