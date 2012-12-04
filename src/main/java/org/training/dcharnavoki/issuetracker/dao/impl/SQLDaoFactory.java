package org.training.dcharnavoki.issuetracker.dao.impl;

import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.dao.ICommentDAO;
import org.training.dcharnavoki.issuetracker.dao.IConfDAO;
import org.training.dcharnavoki.issuetracker.dao.IIssueDAO;
import org.training.dcharnavoki.issuetracker.dao.IProjectDAO;
import org.training.dcharnavoki.issuetracker.dao.IUserDAO;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.CommentImplSql;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.ConfImplSql;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.IssueImplSql;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.ProjectImplSql;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.UserImplSql;

/**
 * A factory for creating SQLDao objects.
 */
public class SQLDaoFactory extends DaoFactory {
	/** The comment dao. */
	private ICommentDAO commentDAO = null;
	/** The conf dao. */
	private IConfDAO confDAO = null;
	/** The issue dao. */
	private IIssueDAO issueDAO = null;
	/** The project dao. */
	private IProjectDAO projectDAO = null;
	/** The user dao. */
	private IUserDAO userDAO = null;

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.DaoFactory#getCommentDAO()
	 */
	@Override
	public ICommentDAO getCommentDAO() throws DaoException {
		if (commentDAO == null) {
			commentDAO = new CommentImplSql();
		}
		return commentDAO;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.DaoFactory#getConfDAO()
	 */
	@Override
	public IConfDAO getConfDAO()  throws DaoException {
		if (confDAO == null) {
			confDAO = new ConfImplSql();
		}
		return confDAO;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.DaoFactory#getIssueDAO()
	 */
	@Override
	public IIssueDAO getIssueDAO() throws DaoException {
		if (issueDAO == null) {
			issueDAO = new IssueImplSql();
		}
		return issueDAO;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.DaoFactory#getProjectDAO()
	 */
	@Override
	public IProjectDAO getProjectDAO() throws DaoException {
		if (projectDAO == null) {
			projectDAO = new ProjectImplSql();
		}
		return projectDAO;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.DaoFactory#getUserDAO()
	 */
	@Override
	public IUserDAO getUserDAO() throws DaoException {
		if (userDAO == null) {
			userDAO = new UserImplSql();
		}
		return userDAO;
	}

}