package com.br.uepb.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.constants.ECaronaException;
/***
 * Uma carona representa a carona que um usuário pode oferecer
 * é composta de um ID, o nome da origem, o nome do destino, uma data, horário
 * e quantidade de vagas
 * @author Melqui
 *
 */
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

	public void setOrigem(String origem) throws ECaronaException {

		if (origem == null || origem.equals("") || origem.equals("-")
				|| origem.equals("!") || origem.equals("!?"))
			throw new ECaronaException(MensagensDeErro.ORIGEM_INVALIDA);

		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) throws ECaronaException {

		if (destino == null || destino.equals("") || destino.equals("()")
				|| destino.equals(".") || destino.equals("!?"))
			throw new ECaronaException(MensagensDeErro.DESTINO_INVALIDO);

		this.destino = destino;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) throws ECaronaException {

		if (data == null || data.equals(""))
			throw new ECaronaException(MensagensDeErro.DATA_INVALIDA);

		SimpleDateFormat dataStringFormat = new SimpleDateFormat("dd/MM/yyyy");
		dataStringFormat.setLenient(false);

		try {
			Date data_ = dataStringFormat.parse(data); // VALIDA A DATA
		} catch (ParseException e) {
			throw new ECaronaException(MensagensDeErro.DATA_INVALIDA);

		}

		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) throws ECaronaException {

		if (hora == null || hora.equals("") || hora.trim() == null)
			throw new ECaronaException(MensagensDeErro.HORA_INVALIDA);
		
		SimpleDateFormat horaStringFormat = new SimpleDateFormat("HH:mm");

		horaStringFormat.setLenient(false);
		try {
			Date hora_ = horaStringFormat.parse(hora);
		} catch (ParseException e) {
			throw new ECaronaException(MensagensDeErro.HORA_INVALIDA);
		}

		

		this.hora = hora;
	}

	public int getVagas() {
		return vagas;
	}

	public void setVagas(Integer vagas) throws ECaronaException {

	
		if (vagas == null || vagas.equals(""))
			throw new ECaronaException(MensagensDeErro.VAGA_INVALIDA);

		try{
			this.vagas = vagas;

		}catch (Exception e){
			throw new ECaronaException(MensagensDeErro.VAGA_INVALIDA);

		}
	}
	
	public String[] getPontosDeEncontro(String idPonto){
		return pontos.get(idPonto);
	}
	
	
	
	public void setPontoDeEncontro(String idPonto, String[] novosPontos){
		pontos.replace(idPonto, novosPontos);
	}

	
	@Override
	/**
	 * Mensagem no formato: this.origem + " para " + this.destino + ", no dia " + this.data + ", as " + this.hora
	 */
	public String toString() {
		return origem + " para " + destino + ", no dia " + data + ", as "
				+ hora;
	}

}
