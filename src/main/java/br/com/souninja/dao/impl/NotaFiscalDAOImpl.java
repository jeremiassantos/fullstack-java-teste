package br.com.souninja.dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.souninja.dao.HibernateDAO;
import br.com.souninja.dao.NotaFiscalDAO;
import br.com.souninja.entity.Cliente;
import br.com.souninja.entity.NotaFiscal;
import br.com.souninja.infrastructure.HibernateUtil;

public class NotaFiscalDAOImpl extends HibernateDAO<NotaFiscal, Integer> implements NotaFiscalDAO {

	private Session session;
	private Transaction transaction;

	public NotaFiscalDAOImpl() {
		super(NotaFiscal.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotaFiscal> findByClienteAndDataEmissao(Cliente cliente, LocalDate dataEmissao) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = session.beginTransaction();
			Query query = this.session.createQuery(
					"FROM NotaFiscal n WHERE n.cliente = :cliente AND month(n.dataEmissao) = :mes AND year(n.dataEmissao) = :ano");
			query.setParameter("cliente", cliente);
			query.setParameter("mes", dataEmissao.getMonth().getValue());
			query.setParameter("ano", dataEmissao.getYear());
			this.transaction.commit();
			return (List<NotaFiscal>) query.list();
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
