package br.com.souninja.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.souninja.dao.ClienteAnexoTipoDAO;
import br.com.souninja.dao.HibernateDAO;
import br.com.souninja.entity.Cliente;
import br.com.souninja.entity.ClienteAnexoTipo;
import br.com.souninja.infrastructure.HibernateUtil;

public class ClienteAnexoTipoDAOImpl extends HibernateDAO<ClienteAnexoTipo, Integer> implements ClienteAnexoTipoDAO{

	private Session session;
	private Transaction transaction;
	
	public ClienteAnexoTipoDAOImpl() {
		super(ClienteAnexoTipo.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClienteAnexoTipo> findByCliente(Cliente cliente) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			Query query = this.session.createQuery("FROM ClienteAnexoTipo c WHERE c.cliente = :cliente");
			query.setParameter("cliente", cliente);
			this.transaction.commit();
			return (List<ClienteAnexoTipo>) query.list();
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
