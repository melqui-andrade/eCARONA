package com.br.uepb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.br.uepb.dao.UsuarioDAO;
import com.br.uepb.domain.UsuarioDomain;

@Repository
public class IUsuarioDAO implements UsuarioDAO {

	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private UsuarioDomain usuario;
	private String login;

	@Override
	public void setLogin(String strLogin) {
		this.login = strLogin;
	}

	@Override
	public UsuarioDomain getUsuario() {
		return usuario;
	}

	@Override
	public void setUsuario(UsuarioDomain usuario) {
		this.usuario = usuario;

	}

	@Override
	public void abrirTransacao() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		session.beginTransaction();
	}

	@Override
	public void fecharTransacao() {
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public List<UsuarioDomain> getListar() {
		List<UsuarioDomain> lista = new ArrayList<UsuarioDomain>();
		try {
			abrirTransacao();
			Query query = session.createQuery("from USUARIO_DAO");
			lista = query.list();
			fecharTransacao();
		} catch (Throwable e) {
		}
		return lista;

	}

	@Override
	public boolean getObter() {
		try {
			abrirTransacao();
			usuario = (UsuarioDomain) session.get(UsuarioDomain.class, login);
			fecharTransacao();
			return true;
		} catch (Throwable e) {
			System.out.println("ID: " + e.getMessage());
			return false;
		}

	}

	@Override
	public boolean getIncluir() {
		try {
			abrirTransacao();
			session.save(usuario);
			fecharTransacao();
			return true;
		} catch (Throwable e) {
			return false;
		}

	}

	@Override
	public boolean getAlterar() {
		try {
			abrirTransacao();
			session.update(usuario);
			fecharTransacao();
			return true;
		} catch (Throwable e) {
			return false;
		}

	}

	@Override
	public boolean getExcluir() {
		try {
			abrirTransacao();
			session.delete(usuario);
			fecharTransacao();
			return true;
		} catch (Throwable e) {
			return false;
		}
	}

}
