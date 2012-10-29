package org.training.dcharnavoki.issuetracker.start.preparing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp.ConfKeys;

/**
 * The Class LoadConfig.
 */
public final class LoadConfig {
	/** The Constant EXT_XML. */
	private static final String EXT_XML = ".xml".toUpperCase();

	/** The Constant EXT_PROPERTY. */
	private static final String EXT_PROPERTY = ".property".toUpperCase();

	/**
	 *
	 */
	private LoadConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the config.
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
			List<ConfKeys> keys = Arrays.asList(ConfKeys.values());
			for (ConfKeys key: keys) {
				String value = properties.getProperty(key.getKey());
				if (value != null) {
					value = value.toLowerCase().trim();
					configApp.put(key, value);
				}
			}
			properties.clear();
			return configApp;
		} catch (IOException e) {
			return new ConfigApp();
		}
	}

}
