package com.br.uepb.dao.impl;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.br.uepb.dao.ISolicitacaoDAO;
import com.br.uepb.domain.CaronaDomain;
import com.br.uepb.domain.SolicitacaoDomain;
import com.br.uepb.utilities.HibernateUtil;

public class SolicitacaoDAO implements ISolicitacaoDAO {

	@Override
	public void save(SolicitacaoDomain solicitacao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
		Transaction t = session.beginTransaction();
		session.save(solicitacao);
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
	public SolicitacaoDomain getSolicitacao(String idSolicitacao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		SolicitacaoDomain solicitacao = (SolicitacaoDomain) session.get(SolicitacaoDomain.class, idSolicitacao);
		session.close();
		return solicitacao;
	}

	@Override
	public List<SolicitacaoDomain> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<SolicitacaoDomain> lista = session.createQuery("from SolicitacaoDomain").list();
		t.commit();
		session.close();
		return lista;
	}

	@Override
	public void remove(SolicitacaoDomain solicitacao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Transaction t = session.beginTransaction();
			session.delete(solicitacao);
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
	public void update(SolicitacaoDomain solicitacao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			Transaction t = session.beginTransaction();
			session.update(solicitacao);
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
		List<SolicitacaoDomain> list = list();
        for(SolicitacaoDomain solicitacao : list){
        	remove(solicitacao);
        }

	}

}
