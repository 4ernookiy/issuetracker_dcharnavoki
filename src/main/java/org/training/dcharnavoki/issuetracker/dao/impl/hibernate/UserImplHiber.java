package org.training.dcharnavoki.issuetracker.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.IUserDAO;
import org.training.dcharnavoki.issuetracker.util.HibernateUtil;

/**
 * The Class UserImplHiber.
 */
public class UserImplHiber extends GenericDAOHiber<User, Integer> implements IUserDAO {

	/**
	 * Instantiates a new user impl hiber.
	 */
	public UserImplHiber() {
		super(User.class, HibernateUtil.getSessionFactory());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.IUserDAO#getUser(java.lang.
	 * String)
	 */
	@Override
	public User getUser(String email) throws DaoException {
		Session session = null;
		User user = null;
		List<User> users = new ArrayList<User>();
		try {
			session = getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from " + getKlass().getName()
					+ " where email = ?");
			query.setString(0, email);
			users = query.list();
			if (users != null && users.size() > 0) {
				user = users.get(0);
			}
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new DaoException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return user;
	}

}
