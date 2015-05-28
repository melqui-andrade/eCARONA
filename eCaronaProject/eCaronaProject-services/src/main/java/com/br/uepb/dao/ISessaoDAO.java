package com.br.uepb.dao;

import java.util.List;
import com.br.uepb.domain.SessaoDomain;

public interface ISessaoDAO {

	public void save(SessaoDomain sessao);
	public SessaoDomain getSessao(String id);
	public List<SessaoDomain> list();
	public void remove(SessaoDomain sessao);
	public void update(SessaoDomain sessao);
	public void excluirTudo();

}
