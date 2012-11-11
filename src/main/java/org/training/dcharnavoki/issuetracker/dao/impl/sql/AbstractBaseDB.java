package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp.ConfKeys;

/**
 * The Class AbstractBaseDB.
 */
public abstract class AbstractBaseDB {
	private static Logger log = Logger.getLogger("log."
			+ AbstractBaseDB.class.getName());
	private static Logger errorLog = Logger.getLogger("error."
			+ AbstractBaseDB.class.getName());
	static {
		try {
			ConfigApp config = DaoFactory.getConfigAplication();
			log.info("Loading JDBC driver '" + config.get(ConfKeys.DB_DRIVER) + "'");
			Class.forName(config.get(ConfKeys.DB_DRIVER)).newInstance();
			log.info("JDBC Driver loaded.");
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			log.fatal("DBDriver loading failed!");
			errorLog.fatal("DBDriver loading failed! " + ex.getMessage());
		}
	}

	private Connection connection;

	/**
	 */
	public AbstractBaseDB() {
		super();
		try {
			ConfigApp config = null;
			try {
				config = DaoFactory.getConfigAplication();
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(config.get(ConfKeys.DB_URL),
					config.get(ConfKeys.DB_USER), config.get(ConfKeys.DB_PASSWORD));
		} catch (SQLException e) {
			log.fatal("Datasource acquiring failed!");
			errorLog.fatal("Datasource acquiring failed! " + e.getMessage());
		}
	}

	/**
	 * Gets the connection.
	 * @return the connection
	 * @throws Exception
	 *             the dao exception
	 */
	public Connection getConnection() throws Exception {
			return connection;
	}

	/**
	 * Close connection.
	 * @param dbConnection
	 *            the db connection
	 */
	public static void closeConnection(Connection dbConnection) {
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
			log.error("Can't close connection.");
			errorLog.error("Can't close connection. " + e.getMessage());
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
				log.error("ResultSet cannot be closed");
				errorLog.error("ResultSet cannot be closed", e);
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
				log.error("Statement cannot be closed");
				errorLog.error("Statement cannot be closed", e);
			}
		}
	}

}
