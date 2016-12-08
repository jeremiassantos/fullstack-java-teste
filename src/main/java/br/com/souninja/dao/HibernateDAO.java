package br.com.souninja.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.souninja.infrastructure.HibernateUtil;

public abstract class HibernateDAO<T, Type extends Serializable> implements GenericDAO<T, Type> {

	private Class<T> persistentClass;

	private Session session;
	private Transaction transaction;

	public HibernateDAO(Class<T> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}

	@Override
	public T save(T entity) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			this.session.saveOrUpdate(entity);
			this.transaction.commit();
			return entity;
		} catch (HibernateException e) {
			this.transaction.rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {

			if (this.session.isOpen()) {
				this.session.close();
			}

		}

	}

	@Override
	public void delete(T entity) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			this.session.delete(entity);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.transaction = this.session.beginTransaction();
			Criteria criteria = this.session.createCriteria(persistentClass);
			this.transaction.commit();
			return criteria.list();
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