package org.training.dcharnavoki.issuetracker.constant;

/**
 * The Class Constant.
 */
public final class Constant {
	/** The Constant FORWARD_CONTROL_MAIN. */
	public static final String FORWARD_CONTROL_MAIN = "/issuetracker/Main";
	/** The Constant REDIRECT_CONTROL_MAIN. */
	public static final String REDIRECT_CONTROL_MAIN = "/issuetracker/Main";
	/** The Constant FORWARD_CONTROL_LOGIN. */
	public static final String FORWARD_CONTROL_LOGIN = "/issuetracker/Login";
	/** The Constant MAX_ROWS_FOR_VIEW. */
	public static final int MAX_ROWS_FOR_VIEW = 13;
	/** The Constant MAIN_JSP. */
	public static final String MAIN_JSP = "/main.jsp";
	/** The Constant LOG_EVENTS. */
	public static final String LOG_EVENTS = "events.";
	/** The Constant ISSUE_ID. */
	public static final String ISSUE_ID = "issueId";
	/**
	 * The Enum Keys.
	 */
	public enum Keys {
		/** The key_issue. */
		ISSUE("key_issue"),
		/** The key_issues. */
		ISSUES("key_issues"),
		/** The key_issue. */
		COMMENTS("key_comments"),
		/** The key_issue. */
		USER("key_user"),
		/** The LOCALE. */
		LOCALE("key_locale"),
		/** The login. */
		LOGIN("key_login"),
		/** The password. */
		PASSWORD("key_password"),
		/** The error message. */
		ALERT_ERROR("key_alert_error"),
		/** The error message. */
		ALERT_WARNING("key_alert_warning"),
		/** The error message. */
		ALERT_SUCCESS("key_alert_success"),
		/** The error message. */
		ALERT_INFO("key_alert_info");
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
