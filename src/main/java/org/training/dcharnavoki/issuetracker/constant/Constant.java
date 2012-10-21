package org.training.dcharnavoki.issuetracker.constant;

/**
 * The Class Constant.
 */
public final class Constant {
	/** The config property file. */
	public static final String CONFIG_PROPERTY_FILE = "/config.property";
	/** The empty value. */
	public static final String EMPTY_VALUE = "";
	/** The empty value. */
	public static final String FORWARD_CONTROL_MAIN = "/start";
	/** The empty value. */
	public static final String REDIRECT_CONTROL_MAIN = "/issuetracker/start";

	/**
	 * The Enum Keys.
	 */
	public enum Keys {

		/** The user. */
		USER("key_user"),

		/** The login. */
		LOGIN("key_login"),

		/** The password. */
		PASSWORD("key_password"),

		/** The error message. */
		ERROR_MESSAGE("key_errorMessage");
		/** The string. */
		private String string;

		/**
		 * Instantiates a new dao.
		 *
		 * @param key the key
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
