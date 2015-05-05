package com.br.uepb.dao;

import java.util.List;
import com.br.uepb.domain.SolicitacaoDomain;

public interface ISolicitacaoDAO {
	
	public void save(SolicitacaoDomain carona);
	public SolicitacaoDomain getCarona(String login);
	public List<SolicitacaoDomain> list();
	public void remove(SolicitacaoDomain carona);
	public void update(SolicitacaoDomain carona);
	public void excluirTudo();

}
