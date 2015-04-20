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
import com.br.uepb.dao.SugestaoEncontroDAO;
import com.br.uepb.dao.UsuarioDAO;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SugestaoEncontroDomain;
import com.br.uepb.domain.UsuarioDomain;

@Repository
public class ISugestaoEncontroDAO implements SugestaoEncontroDAO {

	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private SugestaoEncontroDomain sugestaoEncontro;
	private String ID_SUGESTAO_ENCONTRO_DAO;

	@Override
	public void setID(String strID) {
		this.ID_SUGESTAO_ENCONTRO_DAO = strID;
	}

	@Override
	public SugestaoEncontroDomain getCarona() {
		return sugestaoEncontro;
	}

	@Override
	public void setCarona(SugestaoEncontroDomain sugestaoEncontro) {
		this.sugestaoEncontro = sugestaoEncontro;

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
	public List<SugestaoEncontroDomain> getListar() {
		List<SugestaoEncontroDomain> lista = new ArrayList<SugestaoEncontroDomain>();
		try {
			abrirTransacao();
			Query query = session.createQuery("from SUGESTAO_ENCONTRO_DAO");
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
			sugestaoEncontro = (SugestaoEncontroDomain) session.get(SugestaoEncontroDomain.class, ID_SUGESTAO_ENCONTRO_DAO);
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
			session.save(sugestaoEncontro);
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
			session.update(sugestaoEncontro);
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
			session.delete(sugestaoEncontro);
			fecharTransacao();
			return true;
		} catch (Throwable e) {
			return false;
		}
	}

}
