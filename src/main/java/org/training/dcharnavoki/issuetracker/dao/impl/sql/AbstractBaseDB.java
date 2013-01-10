package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp.ConfKeys;

/**
 * The Class AbstractBaseDB.
 */
public class AbstractBaseDB {

	/** The log. */
	private static final Logger LOG = Logger.getLogger(AbstractBaseDB.class);

	/** The config. */
	private ConfigApp config;
	/** The driver. */
	private String driver;
	/** The url. */
	private String url;
	/** The properties. */
	private Properties properties;

	/**
	 * Instantiates a new abstract base db.
	 *
	 * @throws DaoException the dao exception
	 */
	public AbstractBaseDB() throws DaoException {
		super();
		try {
			config = DaoFactory.getConfigAplication();
			driver = config.get(ConfKeys.DB_DRIVER);
			url = config.get(ConfKeys.DB_URL);
			properties = new Properties();
			properties.setProperty("user", config.get(ConfKeys.DB_USER));
			properties.setProperty("password", config.get(ConfKeys.DB_PASSWORD));
			properties.setProperty("useUnicode", "true");
			properties.setProperty("characterEncoding", "utf8");
		} catch (DaoException e) {
			throw e;
		}
	}

	/**
	 * Gets the connection.
	 * @return the connection
	 * @throws DaoException
	 *             the dao exception
	 */
	public Connection getConnection() throws DaoException {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, properties);
			// return DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (ClassNotFoundException e) {
			throw new DaoException(e);
		}
	}

	/**
	 * Close connection.
	 * @param dbConnection
	 *            the db connection
	 */
	public static void closeResource(Connection dbConnection) {
		try {
			if (dbConnection != null) {
				if (!dbConnection.getAutoCommit()) {
					try {
						dbConnection.commit();
					} catch (SQLException e) {
						throw new SQLException(e);
					}
				}
				dbConnection.close();
			}
		} catch (SQLException e) {
			LOG.error("Can't close connection. " + e.getMessage());
		}
	}

	/**
	 * Close resource.
	 * @param rs
	 *            the rs
	 */
	public static void closeResource(ResultSet... rs) {
		for (ResultSet r : rs) {
			try {
				if (r != null) {
					r.close();
				}
			} catch (SQLException e) {
				LOG.error("ResultSet cannot be closed", e);
			}
		}
	}

	/**
	 * Close resource.
	 * @param st
	 *            the st
	 */
	public static void closeResource(Statement... st) {
		for (Statement s : st) {
			try {
				if (s != null) {
					s.close();
				}
			} catch (SQLException e) {
				LOG.error("Statement cannot be closed", e);
			}
		}
	}

	/**
	 * Gets the config.
	 * @return the config
	 */
	public ConfigApp getConfig() {
		return config;
	}

}
