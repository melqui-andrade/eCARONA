package com.br.uepb.business;

import com.br.uepb.constants.ECaronaException;
import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.UsuarioDomain;

public class VisualizadorPerfil {

	public String getAtributoPerfil(UsuarioDomain usuario, String atributo) throws ECaronaException {
		
		if(usuario.equals(null)) throw new ECaronaException(MensagensDeErro.USUARIO_INEXISTENTE);
		
		switch(atributo){
		case "nome":
			return usuario.getNome();
		case "endereco":
			return usuario.getEndereco();
		case "email":
			return usuario.getEmail();
		case "historico de caronas":
			return historicoCarona(usuario);
		case "historico de vagas em caronas":
			return historicoDeVagas(usuario);
		case "caronas seguras e tranquilas":
			return getTotalCaronaTranquilas(usuario);
		case "caronas que não funcionaram":
			return "0";
		case "faltas em vagas de caronas":
			return "0";
		case "presenças em vagas de caronas":
			return "0";
		}
		return "";
	}

	public String visualizarPerfil(String idSessao, String login) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String historicoCarona(UsuarioDomain usuario){
		String historico = "[";
		for(CaronaDomain carona : usuario.getCaronas()){
			historico += carona.getIdCarona();
			historico += ",";
		}
		if(historico.length() > 1) {
			historico = historico.substring(0, (historico.length()-1));
		}
		
		historico += "]";
		return historico;
	}
	
	private String historicoDeVagas(UsuarioDomain usuario){
		
		String historico = "[";
		for(CaronaDomain carona : usuario.getCaronas()){
			if(carona.foiConcluida()){
				historico += carona.getVagas();
				historico += ",";
			}
		}
		if(historico.length() > 1) {
			historico = historico.substring(0, (historico.length()-1));
		}
		
		historico += "]";
		
		return historico;
	}
	
	private String getTotalCaronaTranquilas(UsuarioDomain usuario){
		int total = 0;
		for(CaronaDomain carona : usuario.getCaronas()){
			if(carona.foiConcluida() && carona.foiTranquila()){
				total++;
			}
		}
		return String.valueOf(total);
	}

}
