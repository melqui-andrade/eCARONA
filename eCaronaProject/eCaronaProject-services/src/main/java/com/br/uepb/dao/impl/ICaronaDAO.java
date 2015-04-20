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
import com.br.uepb.dao.UsuarioDAO;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.UsuarioDomain;

@Repository
public class ICaronaDAO implements CaronaDAO {

	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private CaronaDomain carona;
	private String ID_CARONA_DAO;

	@Override
	public void setID(String strLogin) {
		this.ID_CARONA_DAO = strLogin;
	}

	@Override
	public CaronaDomain getCarona() {
		return carona;
	}

	@Override
	public void setCarona(CaronaDomain carona) {
		this.carona = carona;

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
	public List<CaronaDomain> getListar() {
		List<CaronaDomain> lista = new ArrayList<CaronaDomain>();
		try {
			abrirTransacao();
			Query query = session.createQuery("from CARONA_DAO");
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
			carona = (CaronaDomain) session.get(CaronaDomain.class, ID_CARONA_DAO);
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
			session.save(carona);
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
			session.update(carona);
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
			session.delete(carona);
			fecharTransacao();
			return true;
		} catch (Throwable e) {
			return false;
		}
	}

}
