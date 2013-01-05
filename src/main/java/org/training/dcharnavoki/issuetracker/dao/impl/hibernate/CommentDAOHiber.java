package org.training.dcharnavoki.issuetracker.dao.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.training.dcharnavoki.issuetracker.beans.Comment;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.ICommentDAO;
import org.training.dcharnavoki.issuetracker.util.HibernateUtil;

/**
 * The Class CommentDAO.
 */
public class CommentDAOHiber extends GenericDAOHiber<Comment, Integer> implements ICommentDAO {

	/**
	 * Instantiates a new comment dao.
	 */
	public CommentDAOHiber() {
		super(Comment.class, HibernateUtil.getSessionFactory());
	}

	@Override
	public List<Comment> getCommentsForIssue(Integer id) throws DaoException {
		Session session = null;
		Comment comment = null;
		List<Comment> comments = new ArrayList<Comment>();
		try {
			session = getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from " + getKlass().getName()
					+ " where issueId = ?");
			query.setInteger(0, id);
			comments = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new DaoException(e);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return comments;
	}

}
