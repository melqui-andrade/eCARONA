package com.br.uepb.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
	}

	@Override
	public SessaoDomain getSessao(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (SessaoDomain) session.get(SessaoDomain.class, id);
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
