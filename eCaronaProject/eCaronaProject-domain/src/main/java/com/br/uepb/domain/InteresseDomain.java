package com.br.uepb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INTERESSE_BD")
public class InteresseDomain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_INTERESSE")
	private int idInteresse;
	
	@Column(name="ORIGEM")
	private String origem;
	
	@Column(name="ID_SESSAO")
	private String idSessao;
	
	@Column(name="DESTINO")
	private String destino;
	
	@Column(name="DATA")
	private String data;
	
	@Column(name="HORARIO_INICIO")
	private String horarioInicio;
	
	@Column(name="HORAIO_FIM")
	private String horarioFim;

	public int getIdInteresse() {
		return idInteresse;
	}

	public void setIdInteresse(int idInteresse) {
		this.idInteresse = idInteresse;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(String horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public String getHorarioFim() {
		return horarioFim;
	}

	public void setHorarioFim(String horarioFim) {
		this.horarioFim = horarioFim;
	}

	public String getIdSessao() {
		return idSessao;
	}
}
