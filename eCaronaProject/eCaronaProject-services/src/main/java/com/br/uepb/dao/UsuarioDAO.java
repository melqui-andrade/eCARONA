package com.br.uepb.dao;

import java.util.List;

import com.br.uepb.domain.UsuarioDomain;

public interface UsuarioDAO {

	public void setLogin(String strLogin);

	public UsuarioDomain getUsuario();

	public void setUsuario(UsuarioDomain usuario);

	public void abrirTransacao();

	public void fecharTransacao();

	public List<UsuarioDomain> getListar();

	public boolean getObter();

	public boolean getIncluir();

	public boolean getAlterar();

	public boolean getExcluir();

}
