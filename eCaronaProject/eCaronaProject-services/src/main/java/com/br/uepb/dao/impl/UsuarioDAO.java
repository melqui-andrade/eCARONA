package com.br.uepb.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.br.uepb.dao.IUsuarioDAO;
import com.br.uepb.domain.UsuarioDomain;
import com.br.uepb.utilities.HibernateUtil;


@Repository
public class UsuarioDAO implements IUsuarioDAO {

	@Override
	public void save(UsuarioDomain usuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(usuario);
		t.commit();
	}

	@Override
	public UsuarioDomain getUsuario(String login) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (UsuarioDomain) session.load(UsuarioDomain.class, login);
	}

	@Override
	public List<UsuarioDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<UsuarioDomain> lista = session.createQuery("from usuario_dao").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(UsuarioDomain usuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(usuario);
		t.commit();
	}

	@Override
	public void update(UsuarioDomain usuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(usuario);
		t.commit();
	}
	
	@Override
	public void excluirTudo() {  
        List<UsuarioDomain> list = list();
        for(UsuarioDomain usuario:list){
        	remove(usuario);
        }
    } 
	
	
	
}
