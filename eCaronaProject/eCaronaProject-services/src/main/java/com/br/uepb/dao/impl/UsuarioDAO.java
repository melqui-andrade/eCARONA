package com.br.uepb.dao.impl;

import java.util.List;

import javax.swing.JOptionPane;

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
		try{
			Transaction t = session.beginTransaction();
			session.save(usuario);
			t.commit();
		}
		catch(Exception e){
			System.out.println("Erro ao salvar o registro" + e.getMessage());
		}
		finally{
			session.close();
		}
	}

	@Override
	public UsuarioDomain getUsuario(String login) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		UsuarioDomain usuario = (UsuarioDomain) session.get(UsuarioDomain.class, login);
		session.close();
		return usuario;
	}

	@Override
	public List<UsuarioDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<UsuarioDomain> lista = session.createQuery("from UsuarioDomain").list();
		t.commit();
		session.close();
		return lista;
	}

	@Override
	public void remove(UsuarioDomain usuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Transaction t = session.beginTransaction();
			session.delete(usuario);
			t.commit();
		}
		catch(Exception e){
			System.out.println("Erro ao salvar o registro" + e.getMessage());
		}
		finally{
			session.close();
		}
	}

	@Override
	public void update(UsuarioDomain usuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Transaction t = session.beginTransaction();
			session.update(usuario);
			t.commit();
		}
		catch(Exception e){
			System.out.println("Erro ao salvar o registro" + e.getMessage());
		}
		finally{
			session.close();
		}
	}
	
	@Override
	public void excluirTudo() {  
        List<UsuarioDomain> list = list();
        for(UsuarioDomain usuario:list){
        	remove(usuario);
        }
    }

	public boolean jaCadastrado(String login) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		UsuarioDomain usuario = (UsuarioDomain) session.get(UsuarioDomain.class, login);
		session.close();
		if(usuario == null){
			return false;
		}
		return true;
	} 
	
	
	
}
