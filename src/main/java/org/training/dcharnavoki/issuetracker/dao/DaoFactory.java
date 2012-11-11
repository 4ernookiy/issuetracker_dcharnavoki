package org.training.dcharnavoki.issuetracker.dao;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.training.dcharnavoki.issuetracker.constant.Constant;
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
	private static final String SQL_IMPL = "xml".toLowerCase();

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(Constant.LOG_EVENTS + DaoFactory.class);
	/** The instance. */
	private static DaoFactory instance = null;

	/**
	 * Gets the factory.
	 *
	 * @return the factory
	 * @throws DaoException the dao exception
	 */
	public static DaoFactory getFactory() throws DaoException {
		if (instance == null) {
			if (XML_IMPL.equals(getConfigAplication().get(ConfKeys.IMPL))) {
				instance = new XMLDaoFactory();
			} else if (SQL_IMPL.equals(getConfigAplication().get(ConfKeys.IMPL))) {
				instance = new SQLDaoFactory();
			} else {
				throw new DaoException("not found implementation for key is configApp");
			}

			if (null != instance) {
				LOGGER.info("creating factory of type: " + instance.getClass().getCanonicalName());
			}
		}
		return instance;
//		switch (whichFactory) {
//		case MEMORY:
//			return new MemoryDaoFactory();
//		case DATABASE:
//			return new DatabaseDaoFactory();
//		default:
//			return null;
//		}
	}

	/**
	 * Gets the comment dao.
	 *
	 * @return the comment dao
	 * @throws DaoException the dao exception
	 */
	public abstract ICommentDAO getCommentDAO()  throws DaoException;
	/**
	 * Gets the conf dao.
	 *
	 * @return the conf dao
	 * @throws DaoException the dao exception
	 */
	public abstract IConfDAO getConfDAO() throws DaoException;
	/**
	 * Gets the issue dao.
	 *
	 * @return the issue dao
	 * @throws DaoException the dao exception
	 */
	public abstract IIssueDAO getIssueDAO() throws DaoException;
	/**
	 * Gets the project dao.
	 *
	 * @return the project dao
	 * @throws DaoException the dao exception
	 */
	public abstract IProjectDAO getProjectDAO() throws DaoException;
	/**
	 * Gets the user dao.
	 *
	 * @return the user dao
	 * @throws DaoException the dao exception
	 */
	public abstract IUserDAO getUserDAO() throws DaoException;

	/**
	 * Gets the config aplication.
	 *
	 * @return the config aplication
	 * @throws DaoException the dao exception
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