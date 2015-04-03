package com.br.uepb.business;

import java.util.Map;

import com.br.uepb.domain.CaronaDomain;

public class CaronaBusiness {
	
	public Map<String, CaronaDomain> caronaBD;
	public Map<String, CaronaBusiness> usuarioBD;

	public void cadastrarCarona(String origem, String destino, String data,
			String hora, int vagas) {

		CaronaDomain carona = new CaronaDomain();

		carona.setOrigem(origem);

		if (caronaBD.containsKey(origem)) {
			// throw new Exception(MensagensDeErro.EXISTE_USUARIO_C_LOGIN);
		}

		carona.setDestino(destino);

		carona.setData(data);

		carona.setVagas(vagas);

		String identificadorCarona = data + "_" + vagas + "_" + origem + "_"
				+ destino;

		caronaBD.put(identificadorCarona, carona);

	}

	public void localizarCarona(String idSessao, String origem, String destino){
		
		
	}

}
