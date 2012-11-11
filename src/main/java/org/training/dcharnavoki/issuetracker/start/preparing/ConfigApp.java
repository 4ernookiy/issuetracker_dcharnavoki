package org.training.dcharnavoki.issuetracker.start.preparing;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class ConfigApp.
 */
public class ConfigApp {

	/**
	 * The Enum ConfKeys.
	 */
	public enum ConfKeys {
		/** The impl. */
		IMPL("impl"),
		/** The db driver. */
		DB_DRIVER("db.driver"),
		/** The db url. */
		DB_URL("db.url"),
		/** The db user. */
		DB_USER("db.user"),
		/** The db password. */
		DB_PASSWORD("db.password");

		/** The string. */
		private static final Map<String, ConfKeys> TO_ENUM = new HashMap<String, ConfKeys>();
		private String string;

		static {
			for (ConfKeys op : values()) {
				TO_ENUM.put(op.toString(), op);
			}
		}

		/**
		 * Instantiates a new dao.
		 * @param key
		 *            the string
		 */
		private ConfKeys(String key) {
			this.string = key;
		}

		/**
		 * From string.
		 * @param string
		 *            the string
		 * @return the tags with attr
		 */
		public static ConfKeys fromString(String string) {
			return TO_ENUM.get(string.toUpperCase());
		}

		/**
		 * Gets the implementation.
		 * @return the implementation
		 */
		public String getKey() {
			return string;
		}

	}

	/** The keys. */
	private HashMap<ConfKeys, String> keys = new HashMap<ConfigApp.ConfKeys, String>();

	/**
	 * Instantiates a new config app.
	 */
	public ConfigApp() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Clear.
	 * @see java.util.HashMap#clear()
	 */
	public void clear() {
		keys.clear();
	}

	/**
	 * Gets the.
	 * @param key
	 *            the key
	 * @return the string
	 * @see java.util.HashMap#get(java.lang.Object)
	 */
	public String get(Object key) {
		return keys.get(key);
	}

	/**
	 * Put.
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the string
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	public String put(ConfKeys key, String value) {
		return keys.put(key, value);
	}

	/**
	 * Size.
	 * @return the int
	 * @see java.util.HashMap#size()
	 */
	public int size() {
		return keys.size();
	}

	/**
	 * Values.
	 * @return the collection
	 * @see java.util.HashMap#values()
	 */
	public Collection<String> values() {
		return keys.values();
	}

}
