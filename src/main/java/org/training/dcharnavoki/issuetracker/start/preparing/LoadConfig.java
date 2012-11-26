package org.training.dcharnavoki.issuetracker.start.preparing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp.ConfKeys;

/**
 * The Class LoadConfig.
 */
public final class LoadConfig {
	/** The config property file. */
	private static final String CONFIG_PROPERTY_FILE = "/config.property";

	/** The Constant EXT_XML. */
	private static final String EXT_XML = ".xml".toUpperCase();

	/** The Constant EXT_PROPERTY. */
	private static final String EXT_PROPERTY = ".property".toUpperCase();

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(LoadConfig.class);

	/**
	 * Instantiates a new load config.
	 */
	private LoadConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the config.
	 *
	 * @param file the file
	 * @return the config
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ConfigApp getConfig(String file) throws IOException {

		Properties properties = new Properties();
		ConfigApp configApp = new ConfigApp();
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

			List<ConfKeys> keys = Arrays.asList(ConfKeys.values());
			for (ConfKeys key: keys) {
				String value = properties.getProperty(key.getKey());
				if (value != null) {
					value = value.trim();
					configApp.put(key, value);
				}
			}
			properties.clear();
		} catch (IOException e) {
			LOG.error(e);
			throw new IOException(e);
		}
		return configApp;
	}

	/**
	 * Gets the config.
	 *
	 * @return the config
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static ConfigApp getConfig() throws IOException {
		return getConfig(CONFIG_PROPERTY_FILE);
	}
}
