package br.com.souninja.dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.souninja.dao.HibernateDAO;
import br.com.souninja.dao.ImpostoDAO;
import br.com.souninja.entity.Cliente;
import br.com.souninja.entity.Imposto;
import br.com.souninja.infrastructure.HibernateUtil;

public class ImpostoDAOImpl extends HibernateDAO<Imposto, Integer> implements ImpostoDAO {

	private Session session;
	private Transaction transaction;

	public ImpostoDAOImpl() {
		super(Imposto.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Imposto> findByClienteAndReferencia(Cliente cliente, LocalDate referencia) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = session.beginTransaction();
			Query query = this.session.createQuery(
					"FROM Imposto i WHERE i.cliente = :cliente AND month(i.referencia) = :mes AND year(i.referencia) = :ano");
			query.setParameter("cliente", cliente);
			query.setParameter("mes", referencia.getMonth().getValue());
			query.setParameter("ano", referencia.getYear());
			this.transaction.commit();
			return (List<Imposto>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {

			if (this.session.isOpen()) {
				this.session.close();
			}

		}
	}

	@Override
	public void deleteByClienteAndReferencia(Cliente cliente, LocalDate referencia) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = session.beginTransaction();
			Query query = this.session.createQuery(
					"DELETE FROM Imposto i WHERE i.cliente = :cliente AND month(i.referencia) = :mes AND year(i.referencia) = :ano");
			query.setParameter("cliente", cliente);
			query.setParameter("mes", referencia.getMonth().getValue());
			query.setParameter("ano", referencia.getYear());
			query.executeUpdate();
			this.transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {

			if (this.session.isOpen()) {
				this.session.close();
			}

		}

	}
	
	@Override
	public void updateFromPagamentoCompleted(Cliente cliente, Integer idImposto) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = session.beginTransaction();
			Query query = this.session.createQuery(
					"UPDATE Imposto i set i.pago = true WHERE i.cliente = :cliente AND i.id = :id");
			query.setParameter("cliente", cliente);
			query.setParameter("id", idImposto);
			query.executeUpdate();
			this.transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {

			if (this.session.isOpen()) {
				this.session.close();
			}

		}

	}

	@Override
	public void updateFromPagamentoCompleted(Cliente cliente, List<Integer> idImpostoList) {		
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = session.beginTransaction();
			Query query = this.session.createQuery(
					"UPDATE Imposto i set i.pago = true WHERE i.cliente = :cliente AND i.id in (:idlist)");
			query.setParameter("cliente", cliente);
			query.setParameterList("idlist", idImpostoList);
			query.executeUpdate();
			this.transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {

			if (this.session.isOpen()) {
				this.session.close();
			}

		}

		
	}

}
