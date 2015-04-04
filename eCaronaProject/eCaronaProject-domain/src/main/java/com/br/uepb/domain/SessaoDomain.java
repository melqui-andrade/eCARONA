package com.br.uepb.domain;

import java.util.ArrayList;

public class SessaoDomain {

	private String id;
	private UsuarioDomain usuario;
	private ArrayList<CaronaDomain> caronas;

	public SessaoDomain(String id1, UsuarioDomain usuario1) {
		this.id = id1;
		this.usuario = usuario1;
	}
	
	public void AdicionaCarona(CaronaDomain carona){
		caronas.add(carona);
	}
	
	public ArrayList<CaronaDomain> getBancoCaronas(){
		return caronas;
	}

	public String getId() {
		return id;
	}

	public UsuarioDomain getUsuario() {
		return usuario;
	}

}
