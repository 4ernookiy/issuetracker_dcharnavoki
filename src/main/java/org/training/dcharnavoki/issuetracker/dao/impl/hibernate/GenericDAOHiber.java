package org.training.dcharnavoki.issuetracker.dao.impl.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.GenericDAO;

/**
 * The Class GenericDAOImpl.
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 */
public abstract class GenericDAOHiber<T, ID extends Serializable> implements GenericDAO<T, ID> {

	/** The class. */
	private Class<T> klass;
	/** The sf. */
	private SessionFactory sf;

	/**
	 * Instantiates a new generic dao hiber.
	 * @param klass
	 *            the klass
	 * @param sessionFactory
	 *            the session factory
	 */
	public GenericDAOHiber(Class<T> klass, SessionFactory sessionFactory) {
		this.klass = klass;
		sf = sessionFactory;
	}

	/**
	 * Gets the klass.
	 * @return the klass
	 */
	public Class<T> getKlass() {
		return klass;
	}

	/**
	 * Gets the session.
	 * @return the session
	 */
	protected SessionFactory getSessionFactory() {
		return sf;
	}

	/**
	 * save.
	 * @param entity
	 *            the entity
	 * @return the id
	 * @throws DaoException
	 *             the dao exception
	 */
	@SuppressWarnings("unchecked")
	public ID save(T entity) throws DaoException {
		Session session = null;

		try {
			session = getSessionFactory().getCurrentSession();
			session.beginTransaction();
			ID id = (ID) session.save(entity);
			session.getTransaction().commit();
			return id;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new DaoException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	/**
	 * update.
	 * @param entity
	 *            the entity
	 * @throws DaoException
	 *             the dao exception
	 */
	public void update(T entity) throws DaoException {
		Session session = null;
		try {
			session = getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(entity);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new DaoException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	/**
	 * delete.
	 * @param entity
	 *            the entity
	 * @throws DaoException
	 *             the dao exception
	 */
	public void deleteEntity(T entity) throws DaoException {
		Session session = null;
		try {
			session = getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.delete(entity);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new DaoException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	/**
	 * get All .
	 * @return the list
	 * @throws DaoException
	 *             the dao exception
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() throws DaoException {
		Session session = null;
		List<T> t = null;
		try {
			session = getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from " + klass.getName());
			t = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new DaoException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return t;
	}

	/**
	 * Gets the by id.
	 * @param id
	 *            the id
	 * @return the by id
	 * @throws DaoException
	 *             the dao exception
	 */
	@SuppressWarnings("unchecked")
	public T findByID(Integer id) throws DaoException {
		Session session = null;
		T t = null;
		try {
			session = getSessionFactory().getCurrentSession();
			session.beginTransaction();
			t = (T) session.get(klass, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new DaoException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return t;
	}

}