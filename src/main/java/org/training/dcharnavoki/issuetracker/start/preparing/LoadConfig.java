package org.training.dcharnavoki.issuetracker.start.preparing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.training.dcharnavoki.issuetracker.dao.impl.DAO;

/**
 * The Class LoadConfig.
 */
public final class LoadConfig {
	/** The Constant EXT_XML. */
	private static final String EXT_XML = ".xml".toUpperCase();

	/** The Constant XML_IMPL. */
	private static final String XML_IMPL = "xml";

	/** The Constant XML_IMPL. */
	private static final String SQL_IMPL = "sql";

	/** The Constant EXT_XML. */
	private static final String DOT = ".";

	/** The Constant EXT_PROPERTY. */
	private static final String EXT_PROPERTY = ".property".toUpperCase();

	/** The map impl. */
	private enum KEYS {
		ISSUE("issue"), USER("user"), CONF("conf"), PROJECT("project"), COMMENT(
				"comment");
		private String string;

		/**
		 * Instantiates a new dao.
		 *
		 * @param key
		 *            the string
		 */
		private KEYS(String key) {
			this.string = key;
		}

		/**
		 * Gets the implementation.
		 *
		 * @return the implementation
		 */
		public String getKey() {
			return string;
		}

	}

	private static Map<String, DAO> mapIMPL = new HashMap<String, DAO>();

	static {
		mapIMPL.put(KEYS.ISSUE.getKey() + DOT + XML_IMPL, DAO.XML_ISSUE);
		mapIMPL.put(KEYS.USER.getKey() + DOT + XML_IMPL, DAO.XML_USER);
		mapIMPL.put(KEYS.CONF.getKey() + DOT + XML_IMPL, DAO.XML_CONF);
		mapIMPL.put(KEYS.PROJECT.getKey() + DOT + XML_IMPL, DAO.XML_PROJECT);
		mapIMPL.put(KEYS.COMMENT.getKey() + DOT + XML_IMPL, DAO.XML_COMMENT);
		mapIMPL.put(KEYS.CONF.getKey() + DOT + SQL_IMPL, DAO.SQL_CONF);
	}

	/**
	 *
	 */
	private LoadConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the config.
	 *
	 * @param file
	 *            the file
	 * @return the config
	 */
	public static ConfigApp getConfig(String file) {

		Properties properties = new Properties();

		try {
			InputStream is = LoadConfig.class.getResourceAsStream(file);
			if (is == null) {
				throw new FileNotFoundException("file not found:" + file);
			}
			if (file.toUpperCase().endsWith(EXT_PROPERTY.toUpperCase())) {
				properties.load(is);
			} else if (file.toUpperCase().endsWith(EXT_XML.toUpperCase())) {
				properties.loadFromXML(is);
			} else {
				throw new IllegalArgumentException(
						"this is file is not correct extensions");
			}
			is.close();
			ConfigApp configApp = new ConfigApp();
			configApp.setImplIssue(getImpl(KEYS.ISSUE, properties));
			configApp.setImplUser(getImpl(KEYS.USER, properties));
			configApp.setImplConf(getImpl(KEYS.CONF, properties));
			configApp.setImplProject(getImpl(KEYS.PROJECT, properties));
			configApp.setImplComment(getImpl(KEYS.COMMENT, properties));
			return configApp;
		} catch (IOException e) {
			return new ConfigApp();
		}
	}

	/**
	 * Gets the impl.
	 *
	 * @param key
	 *            the key
	 * @param properties
	 *            the properties
	 * @return the impl
	 */
	private static DAO getImpl(KEYS key, Properties properties) {
		String value = properties.getProperty(key.getKey()).toLowerCase()
				.trim();
		return mapIMPL.get(key.getKey() + DOT + value);
	}

}
