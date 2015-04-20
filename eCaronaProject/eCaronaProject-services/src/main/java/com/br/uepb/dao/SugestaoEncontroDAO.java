package com.br.uepb.dao;

import java.util.List;

import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SugestaoEncontroDomain;
import com.br.uepb.domain.UsuarioDomain;

public interface SugestaoEncontroDAO {

	public void setID(String strID);

	public SugestaoEncontroDomain getCarona();

	public void setCarona(SugestaoEncontroDomain sugestaoEncontro);

	public void abrirTransacao();

	public void fecharTransacao();

	public List<SugestaoEncontroDomain> getListar();

	public boolean getObter();

	public boolean getIncluir();

	public boolean getAlterar();

	public boolean getExcluir();
}
