package com.br.uepb.dao;

import java.util.List;
import com.br.uepb.domain.SugestaoEncontroDomain;

public interface ISugestaoEncontroDAO {

	public void save(SugestaoEncontroDomain sugestaoEncontro);
	public SugestaoEncontroDomain getSugestaoEncontro(String id);
	public List<SugestaoEncontroDomain> list();
	public void remove(SugestaoEncontroDomain sugestaoEncontro);
	public void update(SugestaoEncontroDomain sugestaoEncontro);
	public void excluirTudo();

	
}
