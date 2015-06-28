package com.br.uepb.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.spi.SessionDelegatorBaseImpl;
import org.springframework.stereotype.Repository;

import com.br.uepb.dao.ISessaoDAO;
import com.br.uepb.domain.SessaoDomain;
import com.br.uepb.utilities.HibernateUtil;

@Repository
public class SessaoDAO implements ISessaoDAO {

	@Override
	public void save(SessaoDomain sessao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(sessao);
		t.commit();
		session.close();
	}

	@Override
	public SessaoDomain getSessao(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		SessaoDomain sessao =  (SessaoDomain) session.get(SessaoDomain.class, id);
		session.close();
		return sessao;
	}

	@Override
	public List<SessaoDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<SessaoDomain> lista = session.createQuery("from SessaoDomain").list();
		t.commit();
		session.close();
		return lista;
	}

	@Override
	public void remove(SessaoDomain sessao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(sessao);
		t.commit();
		session.close();
	}

	@Override
	public void update(SessaoDomain sessao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(sessao);
		t.commit();
		session.close();
	}
	
	@Override
	public void excluirTudo() {  
        List<SessaoDomain> list = list();
        for(SessaoDomain sessao:list){
        	remove(sessao);
        }
    } 

	

}
