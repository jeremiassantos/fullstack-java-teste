package br.com.souninja.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.souninja.dao.ClienteDAO;
import br.com.souninja.dao.HibernateDAO;
import br.com.souninja.entity.Cliente;
import br.com.souninja.infrastructure.HibernateUtil;

public class ClienteDAOImpl extends HibernateDAO<Cliente, Integer> implements ClienteDAO {

	private Session session;
	private Transaction transaction;

	public ClienteDAOImpl() {
		super(Cliente.class);
	}

	@Override
	public Cliente saveWithClienteAnexoTipos(Cliente cliente) {

		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			if (cliente.getClienteAnexoTipos() != null) {
				cliente.getClienteAnexoTipos().stream().forEach(p -> p.setCliente(cliente));
			}
			this.session.saveOrUpdate(cliente);
			this.transaction.commit();
			return cliente;
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
	public Cliente findByCnpj(String cnpj) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			Query query = this.session.createQuery("FROM Cliente c WHERE c.cnpj = :cnpj");
			query.setParameter("cnpj", cnpj);
			return (Cliente) query.uniqueResult();
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
	public Cliente findByEmail(String email) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			Query query = this.session.createQuery("FROM Cliente c WHERE c.email = :email");
			query.setParameter("email", email);
			return (Cliente) query.uniqueResult();
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
	public Cliente findById(Integer id) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			Query query = this.session.createQuery("FROM Cliente c WHERE c.id = :id");
			query.setParameter("id", id);
			return (Cliente) query.uniqueResult();
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
