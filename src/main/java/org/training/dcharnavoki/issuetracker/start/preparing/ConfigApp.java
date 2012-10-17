package org.training.dcharnavoki.issuetracker.start.preparing;

import org.training.dcharnavoki.issuetracker.dao.impl.DAO;

/**
 * The Class ConfigApp.
 */
public class ConfigApp {

	/** The impl issue. */
	private DAO implIssue = DAO.XML_ISSUE;

	/** The impl user. */
	private DAO implUser = DAO.XML_USER;

	/** The impl conf. */
	private DAO implConf = DAO.XML_CONF;

	/** The impl project. */
	private DAO implProject = DAO.XML_PROJECT;

	/**
	 * Instantiates a new config app.
	 *
	 * @param implIssue
	 *            the impl issue
	 * @param implUser
	 *            the impl user
	 * @param implConf
	 *            the impl conf
	 * @param implProject
	 *            the impl project
	 */
	public ConfigApp(DAO implIssue, DAO implUser, DAO implConf, DAO implProject) {
		super();
		this.implIssue = implIssue;
		this.implUser = implUser;
		this.implConf = implConf;
		this.implProject = implProject;
	}

	/**
	 * Instantiates a new config app.
	 */
	public ConfigApp() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the impl issue.
	 *
	 * @return the implIssue
	 */
	public DAO getImplIssue() {
		return implIssue;
	}

	/**
	 * Gets the impl user.
	 *
	 * @return the implUser
	 */
	public DAO getImplUser() {
		return implUser;
	}

	/**
	 * Gets the impl conf.
	 *
	 * @return the implConf
	 */
	public DAO getImplConf() {
		return implConf;
	}

	/**
	 * Gets the impl project.
	 *
	 * @return the implProject
	 */
	public DAO getImplProject() {
		return implProject;
	}

}
