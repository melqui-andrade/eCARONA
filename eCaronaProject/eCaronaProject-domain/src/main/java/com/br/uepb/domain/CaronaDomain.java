package com.br.uepb.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.br.uepb.constants.MensagensDeErro;
import com.br.uepb.constants.ECaronaException;
/***
 * Uma carona representa a carona que um usuário pode oferecer
 * é composta de um ID, o nome da origem, o nome do destino, uma data, horário
 * e quantidade de vagas
 * @author Melqui
 *
 */
@Entity
@Table(name="CARONA_BD")
public class CaronaDomain {
	
	@Id
	@Column(name="ID_CARONA")
	private String idCarona;
	
	@Column(name="ID_SESSAO")
	private String idSessao;
			
	@Column(name="ORIGEM")
	private String origem;
	
	@Column(name="DESTINO")
	private String destino;
	
	@Column(name="DATA_")
	private String data;
	
	@Column(name="HORA_")
	private String hora;
	
	@Column(name="VAGAS")
	private Integer vagas;

	@Column(name="USUARIO_LOGIN")
	private String usuarioLogin;
	
	@Column(name="FOI_CONCLUIDA")
	private boolean foiConcluida;

	@Column(name="FOI_TRANQUILA")
	private boolean foiTranquila;
	
	@Column(name="PASSAGEIROS_PRESENTES")
	int passageirosPresentes;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<SolicitacaoDomain> solicitacoes = new ArrayList<SolicitacaoDomain>();

	

	/**
	 * @param solicitacoes the solicitacoes to set
	 */
	public void setSolicitacoes(Collection<SolicitacaoDomain> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	/**
	 * @return the idCarona
	 */
	public String getIdCarona() {
		return idCarona;
	}

	/**
	 * @param idCarona the idCarona to set
	 */
	public void setIdCarona(String idCarona) {
		this.idCarona = idCarona;
	}

	/**
	 * @return the usuarioLogin
	 */
	public String getUsuarioLogin() {
		return usuarioLogin;
	}

	/**
	 * @param usuarioLogin the usuarioLogin to set
	 */
	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}


	private HashMap<String , String[] > pontos;

	public void setId(String id) {
		this.idCarona = id;
	}
	
	public String getIdSessao(){
		return idSessao;
	}
	
	public void setIdSessao(String idSessao){
		this.idSessao = idSessao;
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
	
	public boolean foiConcluida(){
		return this.foiConcluida;
	}
	
	public void foiConcluida(boolean foi){
		this.foiConcluida = foi;
	}
	
	public boolean foiTranquila() {
		return foiTranquila;
	}

	public void foiTranquila(boolean foi) {
		this.foiTranquila = foi;
	}

	public int getPassageirosPresentes() {
		return passageirosPresentes;
	}

	public void setPassageirosPresentes(int passageirosPresentes) {
		this.passageirosPresentes = passageirosPresentes;
	}

	
	@Override
	/**
	 * Mensagem no formato: this.origem + " para " + this.destino + ", no dia " + this.data + ", as " + this.hora
	 */
	public String toString() {
		return origem + " para " + destino + ", no dia " + data + ", as "
				+ hora;
	}

	public String getId() {
		
		return this.idCarona;
	}
	
	public ArrayList<SolicitacaoDomain> getSolicitacoes() {
		return new ArrayList<SolicitacaoDomain>(solicitacoes);
	}
	

	public void adicionarSolicitacao(SolicitacaoDomain solicitacao){
		solicitacoes.add(solicitacao);
		
	}

	
}
