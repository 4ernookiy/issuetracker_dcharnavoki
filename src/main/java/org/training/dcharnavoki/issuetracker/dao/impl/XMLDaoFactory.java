package org.training.dcharnavoki.issuetracker.dao.impl;

import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.dao.ICommentDAO;
import org.training.dcharnavoki.issuetracker.dao.IConfDAO;
import org.training.dcharnavoki.issuetracker.dao.IIssueDAO;
import org.training.dcharnavoki.issuetracker.dao.IProjectDAO;
import org.training.dcharnavoki.issuetracker.dao.IUserDAO;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.ParserComment;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.ParserConf;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.ParserIssue;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.ParserProject;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.ParserUser;

/**
 * DAO Factory that creates ConferenceDao for memory usage.
 */
public class XMLDaoFactory extends DaoFactory {
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
	public ICommentDAO getCommentDAO() {
		if (commentDAO == null) {
			commentDAO = new ParserComment();
		}
		return commentDAO;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.DaoFactory#getConfDAO()
	 */
	@Override
	public IConfDAO getConfDAO() {
		if (confDAO == null) {
			confDAO = new ParserConf();
		}
		return confDAO;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.DaoFactory#getIssueDAO()
	 */
	@Override
	public IIssueDAO getIssueDAO() {
		if (issueDAO == null) {
			issueDAO = new ParserIssue();
		}
		return issueDAO;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.DaoFactory#getProjectDAO()
	 */
	@Override
	public IProjectDAO getProjectDAO() {
		if (projectDAO == null) {
			projectDAO = new ParserProject();
		}
		return projectDAO;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.DaoFactory#getUserDAO()
	 */
	@Override
	public IUserDAO getUserDAO() {
		if (userDAO == null) {
			userDAO = new ParserUser();
		}
		return userDAO;
	}

}