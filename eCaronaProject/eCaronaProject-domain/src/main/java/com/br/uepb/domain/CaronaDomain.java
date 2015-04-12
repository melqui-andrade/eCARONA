package com.br.uepb.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.br.uepb.constants.MensagensDeErro;

public class CaronaDomain {

	private String id;
	private String origem;
	private String destino;
	private String data;
	private String hora;
	private Integer vagas;
	private HashMap<String , String[] > pontos;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) throws Exception {

		if (origem == null || origem.equals("") || origem.equals("-")
				|| origem.equals("!") || origem.equals("!?"))
			throw new Exception(MensagensDeErro.ORIGEM_INVALIDA);

		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) throws Exception {

		if (destino == null || destino.equals("") || destino.equals("()")
				|| destino.equals(".") || destino.equals("!?"))
			throw new Exception(MensagensDeErro.DESTINO_INVALIDO);

		this.destino = destino;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) throws Exception {

		if (data == null || data.equals(""))
			throw new Exception(MensagensDeErro.DATA_INVALIDA);

		SimpleDateFormat dataStringFormat = new SimpleDateFormat("dd/MM/yyyy");
		dataStringFormat.setLenient(false);

		try {
			Date data_ = dataStringFormat.parse(data); // VALIDA A DATA
		} catch (ParseException e) {
			throw new Exception(MensagensDeErro.DATA_INVALIDA);

		}

		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) throws Exception {

		if (hora == null || hora.equals("") || hora.trim() == null)
			throw new Exception(MensagensDeErro.HORA_INVALIDA);
		
		SimpleDateFormat horaStringFormat = new SimpleDateFormat("HH:mm");

		horaStringFormat.setLenient(false);
		try {
			Date hora_ = horaStringFormat.parse(hora);
		} catch (ParseException e) {
			throw new Exception(MensagensDeErro.HORA_INVALIDA);
		}

		

		this.hora = hora;
	}

	public int getVagas() {
		return vagas;
	}

	public void setVagas(Integer vagas) throws Exception {

	
		if (vagas == null || vagas.equals(""))
			throw new Exception(MensagensDeErro.VAGA_INVALIDA);

		try{
			this.vagas = vagas;

		}catch (Exception e){
			throw new Exception(MensagensDeErro.VAGA_INVALIDA);

		}
	}
	
	public String[] getPontosDeEncontro(String idPonto){
		return pontos.get(idPonto);
	}
	
	
	
	public void setPontoDeEncontro(String idPonto, String[] novosPontos){
		pontos.replace(idPonto, novosPontos);
	}

	@Override
	public String toString() {
		return origem + " para " + destino + ", no dia " + data + ", as "
				+ hora;
	}

}
