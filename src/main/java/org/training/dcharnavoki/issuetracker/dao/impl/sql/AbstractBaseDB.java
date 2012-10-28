package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp;

/**
 * The Class AbstractBaseDB.
 */
public abstract class AbstractBaseDB {
	private static Logger log = Logger.getLogger("log."
			+ AbstractBaseDB.class.getName());
	private static Logger errorLog = Logger.getLogger("error."
			+ AbstractBaseDB.class.getName());
	private static final ConfigApp CONFIG = FactoryDAO.CONFIG_APP;
	static {
		try {
			log.info("Loading JDBC driver '" + CONFIG.getDbDriver() + "'");
			Class.forName(CONFIG.getDbDriver()).newInstance();
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
			connection = DriverManager.getConnection(CONFIG.getDbUrl(),
					CONFIG.getDbUser(), CONFIG.getDbPassword());
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
