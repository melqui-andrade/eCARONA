package com.br.uepb.dao.impl;

import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import com.br.uepb.dao.ISugestaoEncontroDAO;
import com.br.uepb.domain.SugestaoEncontroDomain;
import com.br.uepb.utilities.HibernateUtil;

@Repository
public class SugestaoEncontroDAO implements ISugestaoEncontroDAO {
	@Override
	public void save(SugestaoEncontroDomain sugestaoEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Transaction t = session.beginTransaction();
			session.save(sugestaoEncontro);
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
	public SugestaoEncontroDomain getSugestaoEncontro(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (SugestaoEncontroDomain) session.get(SugestaoEncontroDomain.class, id);
	}

	@Override
	public List<SugestaoEncontroDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<SugestaoEncontroDomain> lista = session.createQuery("from SugestaoEncontroDomain").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(SugestaoEncontroDomain sugestaoEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Transaction t = session.beginTransaction();
			session.delete(sugestaoEncontro);
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
	public void update(SugestaoEncontroDomain sugestaoEncontro) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Transaction t = session.beginTransaction();
			session.update(sugestaoEncontro);
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
	public void excluirTudo() {
		//Session session = HibernateUtil.getSessionFactory().openSession();		
        List<SugestaoEncontroDomain> list = list();
        for(SugestaoEncontroDomain sugestaoEncontro:list){
        	remove(sugestaoEncontro);
        }
    } 
	
}
