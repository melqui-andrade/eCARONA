package com.br.uepb.persistencia;

import com.br.uepb.dao.impl.CaronaDAO;
import com.br.uepb.dao.impl.SessaoDAO;
import com.br.uepb.dao.impl.SugestaoEncontroDAO;
import com.br.uepb.dao.impl.UsuarioDAO;

public class Persistencia {

	private UsuarioDAO usuarioBD;
	private CaronaDAO caronaBD;
	private SessaoDAO sessaoBD;
	private SugestaoEncontroDAO sugestaoEncontroBD;
	
	
	public Persistencia() {
		usuarioBD = new UsuarioDAO();
		caronaBD = new CaronaDAO();
		sessaoBD = new SessaoDAO();
		sugestaoEncontroBD = new SugestaoEncontroDAO();
	}


	/**
	 * @return the usuarioBD
	 */
	public UsuarioDAO getUsuarioBD() {
		return usuarioBD;
	}


	/**
	 * @param usuarioBD the usuarioBD to set
	 */
	public void setUsuarioBD(UsuarioDAO usuarioBD) {
		this.usuarioBD = usuarioBD;
	}


	/**
	 * @return the caronaBD
	 */
	public CaronaDAO getCaronaBD() {
		return caronaBD;
	}


	/**
	 * @param caronaBD the caronaBD to set
	 */
	public void setCaronaBD(CaronaDAO caronaBD) {
		this.caronaBD = caronaBD;
	}


	/**
	 * @return the sessaoBD
	 */
	public SessaoDAO getSessaoBD() {
		return sessaoBD;
	}


	/**
	 * @param sessaoBD the sessaoBD to set
	 */
	public void setSessaoBD(SessaoDAO sessaoBD) {
		this.sessaoBD = sessaoBD;
	}


	/**
	 * @return the sugestaoEncontroBD
	 */
	public SugestaoEncontroDAO getSugestaoEncontroBD() {
		return sugestaoEncontroBD;
	}


	/**
	 * @param sugestaoEncontroBD the sugestaoEncontroBD to set
	 */
	public void setSugestaoEncontroBD(SugestaoEncontroDAO sugestaoEncontroBD) {
		this.sugestaoEncontroBD = sugestaoEncontroBD;
	}

}
