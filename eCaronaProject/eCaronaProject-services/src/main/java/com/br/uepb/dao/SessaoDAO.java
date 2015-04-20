package com.br.uepb.dao;

import java.util.List;

import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;

public interface SessaoDAO {

	public void setID(int id);

	public SessaoDomain getSessao();

	public void setSessao(SessaoDomain sessao);

	public void abrirTransacao();

	public void fecharTransacao();

	public List<SessaoDomain> getListar();

	public boolean getObter();

	public boolean getIncluir();

	public boolean getAlterar();

	public boolean getExcluir();
}
