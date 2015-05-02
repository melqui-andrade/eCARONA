package com.br.uepb.dao;

import java.util.List;

import com.br.uepb.domain.UsuarioDomain;

public interface IUsuarioDAO {

	public void save(UsuarioDomain usuario);
	public UsuarioDomain getUsuario(String login);
	public List<UsuarioDomain> list();
	public void remove(UsuarioDomain usuario);
	public void update(UsuarioDomain usuario);
	public void excluirTudo();

}
