package com.br.uepb.dao;

import java.util.List;

import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.UsuarioDomain;

public interface ICaronaDAO {

	public void save(CaronaDomain carona);
	public CaronaDomain getCarona(String login);
	public List<CaronaDomain> list();
	public void remove(CaronaDomain carona);
	public void update(CaronaDomain carona);
	public void excluirTudo();
}