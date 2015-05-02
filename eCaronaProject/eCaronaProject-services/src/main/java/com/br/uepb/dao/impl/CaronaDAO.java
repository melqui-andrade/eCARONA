package com.br.uepb.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.br.uepb.dao.ICaronaDAO;
import com.br.uepb.dao.IUsuarioDAO;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.UsuarioDomain;
import com.br.uepb.utilities.HibernateUtil;

@Repository
public class CaronaDAO implements ICaronaDAO {

	@Override
	public void save(CaronaDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(carona);
		t.commit();
	}

	@Override
	public CaronaDomain getCarona(String login) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (CaronaDomain) session.load(CaronaDomain.class, login);
	}

	@Override
	public List<CaronaDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<CaronaDomain> lista = session.createQuery("from carona_dao").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(CaronaDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(carona);
		t.commit();
	}

	@Override
	public void update(CaronaDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(carona);
		t.commit();
	}
	
	@Override
	public void excluirTudo() {  
        List<CaronaDomain> list = list();
        for(CaronaDomain carona:list){
        	remove(carona);
        }
    } 
	
	

}
