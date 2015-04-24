package com.br.uepb.persistencia;

import com.br.uepb.dao.impl.ICaronaDAO;
import com.br.uepb.dao.impl.ISessaoDAO;
import com.br.uepb.dao.impl.ISugestaoEncontroDAO;
import com.br.uepb.dao.impl.IUsuarioDAO;

public class Persistencia {

	private IUsuarioDAO usuarioBD;
	private ICaronaDAO caronaBD;
	private ISessaoDAO sessaoBD;
	private ISugestaoEncontroDAO sugestaoEncontroBD;
	
	
	public Persistencia() {
		usuarioBD = new IUsuarioDAO();
		caronaBD = new ICaronaDAO();
		sessaoBD = new ISessaoDAO();
		sugestaoEncontroBD = new ISugestaoEncontroDAO();
	}


	/**
	 * @return the usuarioBD
	 */
	public IUsuarioDAO getUsuarioBD() {
		return usuarioBD;
	}


	/**
	 * @param usuarioBD the usuarioBD to set
	 */
	public void setUsuarioBD(IUsuarioDAO usuarioBD) {
		this.usuarioBD = usuarioBD;
	}


	/**
	 * @return the caronaBD
	 */
	public ICaronaDAO getCaronaBD() {
		return caronaBD;
	}


	/**
	 * @param caronaBD the caronaBD to set
	 */
	public void setCaronaBD(ICaronaDAO caronaBD) {
		this.caronaBD = caronaBD;
	}


	/**
	 * @return the sessaoBD
	 */
	public ISessaoDAO getSessaoBD() {
		return sessaoBD;
	}


	/**
	 * @param sessaoBD the sessaoBD to set
	 */
	public void setSessaoBD(ISessaoDAO sessaoBD) {
		this.sessaoBD = sessaoBD;
	}


	/**
	 * @return the sugestaoEncontroBD
	 */
	public ISugestaoEncontroDAO getSugestaoEncontroBD() {
		return sugestaoEncontroBD;
	}


	/**
	 * @param sugestaoEncontroBD the sugestaoEncontroBD to set
	 */
	public void setSugestaoEncontroBD(ISugestaoEncontroDAO sugestaoEncontroBD) {
		this.sugestaoEncontroBD = sugestaoEncontroBD;
	}

}
