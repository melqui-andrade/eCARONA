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
import com.br.uepb.utilities.HibernateUtil;

@Repository
public class ISessaoDAO implements SessaoDAO {

	@Override
	public void save(SessaoDomain sessao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(sessao);
		t.commit();
	}

	@Override
	public SessaoDomain getSessao(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (SessaoDomain) session.load(UsuarioDomain.class, id);
	}

	@Override
	public List<SessaoDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<SessaoDomain> lista = session.createQuery("from sessao_dao").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(SessaoDomain sessao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(sessao);
		t.commit();
	}

	@Override
	public void update(SessaoDomain sessao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(sessao);
		t.commit();
	}
	
	@Override
	public void excluirTudo() {  
        List<SessaoDomain> list = list();
        for(SessaoDomain sessao:list){
        	remove(sessao);
        }
    } 

	

}
