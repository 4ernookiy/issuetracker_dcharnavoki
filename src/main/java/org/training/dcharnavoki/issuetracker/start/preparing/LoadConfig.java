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

	/** The Constant EXT_PROPERTY. */
	private static final String EXT_PROPERTY = ".property".toUpperCase();

	/** The map impl. */
	private enum KEYS {
		ISSUE("issue"), USER("user"), CONF("conf"), PROJECT("project");
		private String string;
		/**
		 * Instantiates a new dao.
		 *
		 * @param key the string
		 */
		private KEYS(String key) {
			this.string = key;
		}
		/**
		 * Gets the implementation.
		 *
		 * @return the implementation
		 */
		public String getKeyString() {
			return string;
		}

	}
	private static Map<String, DAO> mapIMPL = new HashMap<String, DAO>();

	static {
		mapIMPL.put("issue.xml", DAO.XML_ISSUE);
		mapIMPL.put("user.xml", DAO.XML_USER);
		mapIMPL.put("conf.xml", DAO.XML_CONF);
		mapIMPL.put("project.xml", DAO.XML_PROJECT);
		mapIMPL.put("conf.sql", DAO.SQL_CONF);
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

			DAO issueImpl;
			DAO confImpl;
			DAO userImpl;
			DAO implProject;
			issueImpl = getImpl(KEYS.ISSUE, properties);
			userImpl = getImpl(KEYS.USER, properties);
			confImpl = getImpl(KEYS.CONF, properties);
			implProject = getImpl(KEYS.PROJECT, properties);
			properties.clear();
			ConfigApp configApp = new ConfigApp(issueImpl, userImpl, confImpl,
					implProject);

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
		String value = properties.getProperty(key.getKeyString()).toLowerCase().trim();
		return mapIMPL.get(value);
	}

}
