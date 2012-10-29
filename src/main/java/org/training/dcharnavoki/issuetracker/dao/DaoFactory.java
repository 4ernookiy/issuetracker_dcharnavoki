package org.training.dcharnavoki.issuetracker.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.dao.impl.XMLDaoFactory;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp.ConfKeys;
import org.training.dcharnavoki.issuetracker.start.preparing.LoadConfig;

/**
 * A factory for creating Dao objects.
 */
public abstract class DaoFactory {
	/** The Constant CONFIG_APP. */
	public static final ConfigApp CONFIG_APP = LoadConfig.getConfig(Constant.CONFIG_PROPERTY_FILE);

	/** The Constant LOGGER. */
	private static final String XML_IMPL = "xml".toLowerCase();

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DaoFactory.class);
	/** The instance. */
	private static DaoFactory instance = null;
	/**
	 * Creates DAO factory of the given type.
	 *
	 * @return DAO factory of the given type or null if type was incorrectly
	 *         defined
	 */
	public static DaoFactory getFactory() {
		if (instance == null) {
			if (CONFIG_APP.get(ConfKeys.IMPL).equals(XML_IMPL)) {
				instance = new XMLDaoFactory();
				LOGGER.debug("creating factory for type: " + instance.getClass());
			}
			System.out.println(CONFIG_APP.values());
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
	 */
	public abstract ICommentDAO getCommentDAO();
	/**
	 * Gets the conf dao.
	 *
	 * @return the conf dao
	 */
	public abstract IConfDAO getConfDAO();
	/**
	 * Gets the issue dao.
	 *
	 * @return the issue dao
	 */
	public abstract IIssueDAO getIssueDAO();
	/**
	 * Gets the project dao.
	 *
	 * @return the project dao
	 */
	public abstract IProjectDAO getProjectDAO();
	/**
	 * Gets the user dao.
	 *
	 * @return the user dao
	 */
	public abstract IUserDAO getUserDAO();

}