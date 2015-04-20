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

import com.br.uepb.dao.CaronaDAO;
import com.br.uepb.dao.SessaoDAO;
import com.br.uepb.dao.UsuarioDAO;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.domain.UsuarioDomain;

@Repository
public class ISessaoDAO implements SessaoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private SessaoDomain sessao;
	private int ID_SESSAO_DAO;

	@Override
	public void setID(int id) {
		this.ID_SESSAO_DAO = id;
	}

	@Override
	public SessaoDomain getSessao() {
		return sessao;
	}

	@Override
	public void setSessao(SessaoDomain sessao) {
		this.sessao = sessao;

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
	public List<SessaoDomain> getListar() {
		List<SessaoDomain> lista = new ArrayList<SessaoDomain>();
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
			sessao = (SessaoDomain) session.get(SessaoDomain.class, ID_SESSAO_DAO);
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
			session.save(sessao);
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
			session.update(sessao);
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
			session.delete(sessao);
			fecharTransacao();
			return true;
		} catch (Throwable e) {
			return false;
		}
	}

}
