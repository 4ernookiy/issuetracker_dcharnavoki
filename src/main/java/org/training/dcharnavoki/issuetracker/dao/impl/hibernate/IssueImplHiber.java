package org.training.dcharnavoki.issuetracker.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.training.dcharnavoki.issuetracker.beans.Issue;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.IIssueDAO;
import org.training.dcharnavoki.issuetracker.util.HibernateUtil;

/**
 * The Class IssueImplHiber.
 */
public class IssueImplHiber extends GenericDAOHiber<Issue, Integer> implements IIssueDAO {

	/**
	 * Instantiates a new issue impl hiber.
	 */
	public IssueImplHiber() {
		super(Issue.class, HibernateUtil.getSessionFactory());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.IIssueDAO#getIssuesForUser(
	 * org.training.dcharnavoki.issuetracker.beans.User)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Issue> getIssuesForUser(User user) throws DaoException {
		Session session = null;
		List<Issue> issues = new ArrayList<Issue>();
		try {
			session = getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from " + getKlass().getName()
					+ " where assigned = ?");
			query.setInteger(0, user.getId());
			issues = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new DaoException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return issues;
	}

}
