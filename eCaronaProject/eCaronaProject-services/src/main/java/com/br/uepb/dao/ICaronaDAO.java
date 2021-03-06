package com.br.uepb.dao;

import java.util.List;
import com.br.uepb.domain.CaronaDomain;

public interface ICaronaDAO {

	public void save(CaronaDomain carona);
	public CaronaDomain getCarona(String id);
	public List<CaronaDomain> list();
	public void remove(CaronaDomain carona);
	public void update(CaronaDomain carona);
	public void excluirTudo();
}
