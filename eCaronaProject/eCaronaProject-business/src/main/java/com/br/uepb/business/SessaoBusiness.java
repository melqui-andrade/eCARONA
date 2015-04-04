package com.br.uepb.business;

import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;

public class SessaoBusiness {
	
	private SessaoDomain sessao;
	
	public SessaoBusiness(SessaoDomain sessao){
		this.sessao = sessao; 
	}
	
	public String localizarCarona(String origem, String destino){
		
		CaronaDomain caronaProcurada = null;
		for(CaronaDomain carona : sessao.getBancoCaronas()){
			if(carona.getOrigem() == origem && carona.getDestino() == destino){
				caronaProcurada = carona;
				break;
			}
		}
		if(caronaProcurada == null) return "";
		return String.valueOf(caronaProcurada.getId());
	}

}
