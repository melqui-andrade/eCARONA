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
import com.br.uepb.utilities.HibernateUtil;

@Repository
public class ISugestaoEncontroDAO implements SugestaoEncontroDAO {
	@Override
	public void save(SugestaoEncontroDomain sugestaoEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(sugestaoEncontro);
		t.commit();
	}

	@Override
	public SugestaoEncontroDomain getSugestaoEncontro(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (SugestaoEncontroDomain) session.load(SugestaoEncontroDomain.class, id);
	}

	@Override
	public List<SugestaoEncontroDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<SugestaoEncontroDomain> lista = session.createQuery("from sugestao_encontro_dao").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(SugestaoEncontroDomain sugestaoEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(sugestaoEncontro);
		t.commit();
	}

	@Override
	public void update(SugestaoEncontroDomain sugestaoEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(sugestaoEncontro);
		t.commit();
	}
	
	@Override
	public void excluirTudo() {  
        List<SugestaoEncontroDomain> list = list();
        for(SugestaoEncontroDomain sugestaoEncontro:list){
        	remove(sugestaoEncontro);
        }
    } 
	
}
