package org.training.dcharnavoki.issuetracker.dao;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.dao.impl.HiberDaoFactory;
import org.training.dcharnavoki.issuetracker.dao.impl.SQLDaoFactory;
import org.training.dcharnavoki.issuetracker.dao.impl.XMLDaoFactory;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp.ConfKeys;
import org.training.dcharnavoki.issuetracker.start.preparing.LoadConfig;

/**
 * A factory for creating Dao objects.
 */
public abstract class DaoFactory {
	/** The config app. */
	private static ConfigApp configAplication;
	/** The Constant XML_IMPL. */
	private static final String XML_IMPL = "xml".toLowerCase();
	/** The Constant SQL_IMPL. */
	private static final String SQL_IMPL = "sql".toLowerCase();
	/** The Constant HIBERNATE_IMPL. */
	private static final String HIBERNATE_IMPL = "hibernate".toLowerCase();
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(Constant.LOG_EVENTS
			+ DaoFactory.class);
	/** The instance. */
	private static DaoFactory instance = null;

	/** The comment dao. */
	private ICommentDAO commentDAO;
	/** The issue dao. */
	private IIssueDAO issueDAO;
	/** The project dao. */
	private IProjectDAO projectDAO;
	/** The user dao. */
	private IUserDAO userDAO;
	/** The build dao. */
	private IBuildDAO buildDAO;
	/** The priority dao. */
	private IPriorityDAO priorityDAO;
	/** The resolution dao. */
	private IResolutionDAO resolutionDAO;
	/** The status dao. */
	private IStatusDAO statusDAO;
	/** The type dao. */
	private ITypeDAO typeDAO;

	/**
	 * Instantiates a new dao factory.
	 */
	public DaoFactory() {
		super();
	}

	/**
	 * Gets the priority dao.
	 * @return the priority dao
	 */
	public IPriorityDAO getPriorityDAO() {
		return priorityDAO;
	}

	/**
	 * Sets the priority dao.
	 * @param priorityDAO
	 *            the new priority dao
	 */
	protected void setPriorityDAO(IPriorityDAO priorityDAO) {
		this.priorityDAO = priorityDAO;
	}

	/**
	 * Gets the resolution dao.
	 * @return the resolution dao
	 */
	public IResolutionDAO getResolutionDAO() {
		return resolutionDAO;
	}

	/**
	 * Sets the resolution dao.
	 * @param resolutionDAO
	 *            the new resolution dao
	 */
	protected void setResolutionDAO(IResolutionDAO resolutionDAO) {
		this.resolutionDAO = resolutionDAO;
	}

	/**
	 * Gets the status dao.
	 * @return the status dao
	 */
	public IStatusDAO getStatusDAO() {
		return statusDAO;
	}

	/**
	 * Sets the status dao.
	 * @param statusDAO
	 *            the new status dao
	 */
	protected void setStatusDAO(IStatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}

	/**
	 * Gets the type dao.
	 * @return the type dao
	 */
	public ITypeDAO getTypeDAO() {
		return typeDAO;
	}

	/**
	 * Sets the type dao.
	 * @param typeDAO
	 *            the new type dao
	 */
	protected void setTypeDAO(ITypeDAO typeDAO) {
		this.typeDAO = typeDAO;
	}

	/**
	 * Gets the builds the dao.
	 * @return the builds the dao
	 */
	public IBuildDAO getBuildDAO() {
		return buildDAO;
	}

	/**
	 * Sets the builds the dao.
	 * @param buildDAO
	 *            the new builds the dao
	 */
	protected void setBuildDAO(IBuildDAO buildDAO) {
		this.buildDAO = buildDAO;
	}

	/**
	 * Gets the user dao.
	 * @return the user dao
	 */
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * Sets the user dao.
	 * @param userDAO
	 *            the new user dao
	 */
	protected void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * Gets the comment dao.
	 * @return the comment dao
	 */
	public ICommentDAO getCommentDAO() {
		return commentDAO;
	}

	/**
	 * Sets the comment dao.
	 * @param commentDAO
	 *            the new comment dao
	 */
	public void setCommentDAO(ICommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	/**
	 * Gets the project dao.
	 * @return the project dao
	 */
	public IProjectDAO getProjectDAO() {
		return projectDAO;
	}

	/**
	 * Sets the project dao.
	 * @param projectDAO
	 *            the new project dao
	 */
	protected void setProjectDAO(IProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	/**
	 * Gets the issue dao.
	 * @return the issue dao
	 */
	public IIssueDAO getIssueDAO() {
		return issueDAO;
	}

	/**
	 * Sets the issue dao.
	 * @param issueDAO
	 *            the new issue dao
	 */
	protected void setIssueDAO(IIssueDAO issueDAO) {
		this.issueDAO = issueDAO;
	}

	/**
	 * Gets the factory.
	 * @return the factory
	 * @throws DaoException
	 *             the dao exception
	 */
	public static DaoFactory getFactory() throws DaoException {
		if (instance == null) {
			if (XML_IMPL.equalsIgnoreCase(getConfigAplication().get(ConfKeys.IMPL))) {
				instance = new XMLDaoFactory();
			} else if (SQL_IMPL.equalsIgnoreCase(getConfigAplication().get(ConfKeys.IMPL))) {
				instance = new SQLDaoFactory();
			} else if (HIBERNATE_IMPL.equalsIgnoreCase(getConfigAplication()
					.get(ConfKeys.IMPL))) {
				instance = new HiberDaoFactory();
			} else {
				throw new DaoException("not found implementation for key:'"
						+ getConfigAplication().get(ConfKeys.IMPL) + "' is configApp");
			}
			LOGGER.info("Create Factory:" + instance.getClass().getSimpleName());
		}
		return instance;
	}

	/**
	 * Gets the config aplication.
	 * @return the config aplication
	 * @throws DaoException
	 *             the dao exception
	 */
	public static ConfigApp getConfigAplication() throws DaoException {
		if (null == configAplication) {
			try {
				configAplication = LoadConfig.getConfig();
			} catch (IOException e) {
				throw new DaoException(e);
			}
		}
		return configAplication;
	}

}