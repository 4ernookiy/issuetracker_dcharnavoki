package org.training.dcharnavoki.issuetracker.constant;

/**
 * The Class Constant.
 */
public final class Constant {
	/** The Constant FORWARD_CONTROL_MAIN. */
	public static final String FORWARD_CONTROL_MAIN = "/issuetracker/Main";
	/** The Constant REDIRECT_CONTROL_MAIN. */
	public static final String REDIRECT_CONTROL_MAIN = "/issuetracker/Main";
	/** The Constant REDIRECT_REGISTER_JSP. */
	public static final String REDIRECT_REGISTER_JSP = "/issuetracker/pageRegister";
	/** The Constant REDIRECT_SAVE_ISSUE_JSP. */
	public static final String REDIRECT_SAVE_ISSUE_JSP = "/issuetracker/SaveIssue";
	/** The Constant FORWARD_CONTROL_LOGIN. */
	public static final String FORWARD_CONTROL_LOGIN = "/issuetracker/Login";
	/** The Constant FORWARD_CONTROL_LOGIN. */
	/** The Constant REDIRECT_SAVE_ISSUE_JSP. */
	public static final String REDIRECT_CONTROL_CREATE_ISSUE = "/issuetracker/CreateIssue";
	/** The Constant FORWARD_CONTROL_CREATE_ISSUE. */
	public static final String JUMP_CONTROL_CREATE_ISSUE = "/CreateIssue";
	/** The Constant MAX_ROWS_FOR_VIEW. */
	public static final int MAX_ROWS_FOR_VIEW = 13;
	/** The Constant MAIN_JSP. */
	public static final String MAIN_JSP = "/main.jsp";
	/** The Constant LOG_EVENTS. */
	public static final String LOG_EVENTS = "events.";
	/** The Constant ISSUE_ID. */
	public static final String ISSUE_ID = "issueId";
	/** The Constant Message. */
	public static final String MESSAGE = "message4jsp";
	/** The Constant STATUS_NEW. */
	public static final int STATUS_NEW = 1;
	/** The Constant STATUS_ASSIGNED. */
	public static final int STATUS_ASSIGNED = 2;
	/** The Delimiter Message. */
	public static final String DELIMETER = ":";
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
		PASSWORD("key_password");
		/** The error message. */
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
