package com.br.uepb.dao;

import java.util.List;

import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.UsuarioDomain;

public interface CaronaDAO {

	public void setID(String strID);

	public CaronaDomain getCarona();

	public void setCarona(CaronaDomain carona);

	public void abrirTransacao();

	public void fecharTransacao();

	public List<CaronaDomain> getListar();

	public boolean getObter();

	public boolean getIncluir();

	public boolean getAlterar();

	public boolean getExcluir();
}
