package com.br.uepb.dao.impl;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.dao.IInteresseDAO;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.InteresseDomain;
import com.br.uepb.utilities.HibernateUtil;

public class InteresseDAO implements IInteresseDAO {

	@Override
	public void save(InteresseDomain interesse) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		Transaction t = session.beginTransaction();
		session.save(interesse);
		t.commit();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Erro ao salvar o registro" + e.getMessage());
		}
		finally{
			session.close();
		}

	}

	@Override
	public InteresseDomain getInteresse(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		InteresseDomain interesse = (InteresseDomain) session.get(InteresseDomain.class, id);
		session.close();
		return interesse;
	}

	@Override
	public void remove(InteresseDomain interesse) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Transaction t = session.beginTransaction();
			session.delete(interesse);
			t.commit();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Erro ao salvar o registro" + e.getMessage());
		}
		finally{
			session.close();
		}

	}

	@Override
	public void update(InteresseDomain interesse) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Transaction t = session.beginTransaction();
			session.update(interesse);
			t.commit();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Erro ao salvar o registro" + e.getMessage());
		}
		finally{
			session.close();
		}

	}
	
	@Override
	public List<InteresseDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<InteresseDomain> lista = session.createQuery("from InteresseDomain").list();
		t.commit();
		session.close();
		return lista;
	}

	@Override
	public void excluirTudo() {
		List<InteresseDomain> list = list();
        for(InteresseDomain interesse:list){
        	remove(interesse);
        }

	}


}
