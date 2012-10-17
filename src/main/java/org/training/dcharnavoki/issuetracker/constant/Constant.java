package org.training.dcharnavoki.issuetracker.constant;

/**
 * The Class Constant.
 */
public final class Constant {
	/** The config property file. */
	public static final String CONFIG_PROPERTY_FILE = "/config.property";
	/** The empty value. */
	public static final String EMPTY_VALUE = "";

	/**
	 * The Enum Keys.
	 */
	public enum Keys {

		/** The user. */
		USER("key_user"),

		/** The login. */
		LOGIN("key_login"),

		/** The password. */
		PASSWORD("key_password");
		/** The string. */
		private String string;

		/**
		 * Instantiates a new dao.
		 *
		 * @param string
		 *            the string
		 */
		private Keys(String key) {
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

	/**
	 * Instantiates a new constant.
	 */
	private Constant() {
		super();
	}
}
