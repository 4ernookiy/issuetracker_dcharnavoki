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

	/** The impl project. */
	private DAO implComment = DAO.XML_COMMENT;

	private String dbDriver;
	private String dbUrl;
	private String dbUser;
	private String dbPassword;
	/**
	 * Instantiates a new config app.
	 */
	public ConfigApp() {
		super();
	}
	/**
	 * Gets the impl comment.
	 *
	 * @return the implComment
	 */
	public DAO getImplComment() {
		return implComment;
	}

	/**
	 * Sets the impl comment.
	 *
	 * @param implDAO the new impl comment
	 */
	public void setImplComment(DAO implDAO) {
		if (implDAO != null) {
			this.implComment = implDAO;
		}
	}

	/**
	 * Sets the impl issue.
	 *
	 * @param implDAO the new impl issue
	 */
	public void setImplIssue(DAO implDAO) {
		if (implDAO != null) {
		this.implIssue = implDAO;
		}
	}

	/**
	 * Sets the impl user.
	 *
	 * @param implDAO the new impl user
	 */
	public void setImplUser(DAO implDAO) {
		if (implDAO != null) {
		this.implUser = implDAO;
		}
	}

	/**
	 * Sets the impl conf.
	 *
	 * @param implDAO the new impl conf
	 */
	public void setImplConf(DAO implDAO) {
		if (implDAO != null) {
		this.implConf = implDAO;
		}
	}

	/**
	 * Sets the impl project.
	 *
	 * @param implDAO the new impl project
	 */
	public void setImplProject(DAO implDAO) {
		if (implDAO != null) {
		this.implProject = implDAO;
		}
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
	/**
	 * @return the dbDriver
	 */
	public String getDbDriver() {
		return dbDriver;
	}
	/**
	 * @param dbDriver the dbDriver to set
	 */
	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}
	/**
	 * @return the dbUrl
	 */
	public String getDbUrl() {
		return dbUrl;
	}
	/**
	 * @param dbUrl the dbUrl to set
	 */
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	/**
	 * @return the dbUser
	 */
	public String getDbUser() {
		return dbUser;
	}
	/**
	 * @param dbUser the dbUser to set
	 */
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}
	/**
	 * @return the dbPassword
	 */
	public String getDbPassword() {
		return dbPassword;
	}
	/**
	 * @param dbPassword the dbPassword to set
	 */
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

}
