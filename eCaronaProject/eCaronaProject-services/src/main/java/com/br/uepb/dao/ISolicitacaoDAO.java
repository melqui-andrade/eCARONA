package com.br.uepb.dao;

import java.util.List;
import com.br.uepb.domain.SolicitacaoDomain;

public interface ISolicitacaoDAO {
	
	public void save(SolicitacaoDomain solicitacao);
	public SolicitacaoDomain getSolicitacao(String idSolicitacao);
	public List<SolicitacaoDomain> list();
	public void remove(SolicitacaoDomain solicitacao);
	public void update(SolicitacaoDomain solicitacao);
	public void excluirTudo();

}
