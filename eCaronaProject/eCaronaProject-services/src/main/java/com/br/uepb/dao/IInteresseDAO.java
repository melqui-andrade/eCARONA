package com.br.uepb.dao;

import java.util.List;

import com.br.uepb.domain.InteresseDomain;

public interface IInteresseDAO {

	public void save(InteresseDomain interesse);
	public InteresseDomain getInteresse(String id);
	public List<InteresseDomain> list();
	public void remove(InteresseDomain interesse);
	public void update(InteresseDomain interesse);
	public void excluirTudo();
	
}
