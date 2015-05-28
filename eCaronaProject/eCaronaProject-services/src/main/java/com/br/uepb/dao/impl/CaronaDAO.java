package com.br.uepb.dao.impl;

import java.util.List;

import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import com.br.uepb.dao.ICaronaDAO;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.utilities.HibernateUtil;

@Repository
public class CaronaDAO implements ICaronaDAO {

	@Override
	public void save(CaronaDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		Transaction t = session.beginTransaction();
		session.save(carona);
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
	public CaronaDomain getCarona(String login) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CaronaDomain carona = (CaronaDomain) session.get(CaronaDomain.class, login);
		session.close();
		return carona;
	}

	@Override
	public List<CaronaDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<CaronaDomain> lista = session.createQuery("from CaronaDomain").list();
		t.commit();
		session.close();
		return lista;
	}

	@Override
	public void remove(CaronaDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Transaction t = session.beginTransaction();
			session.delete(carona);
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
	public void update(CaronaDomain carona) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Transaction t = session.beginTransaction();
			session.update(carona);
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
        List<CaronaDomain> list = list();
        for(CaronaDomain carona:list){
        	remove(carona);
        }
    } 
	
	

}
