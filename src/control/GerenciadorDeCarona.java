package control;

import java.util.Map;

import utilitarios.MensagensDeErro;
import model.Carona;
import model.Usuario;

public class GerenciadorDeCarona {

	public Map<String, Carona> caronaBD;
	public Map<String, Carona> usuarioBD;

	public void cadastrarCarona(String origem, String destino, String data,
			String hora, int vagas) {

		Carona carona = new Carona();

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
