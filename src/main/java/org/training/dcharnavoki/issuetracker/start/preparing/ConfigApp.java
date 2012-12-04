package org.training.dcharnavoki.issuetracker.start.preparing;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.training.dcharnavoki.issuetracker.dao.DaoException;

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
		DB_PASSWORD("db.password"),
		/** The message length min. */
		MESSAGE_LENGTH_MIN("message.length.min"),
		/** The pattern email. */
		PATTERN_EMAIL("pattern.email"),
		/** The pattern password. */
		PATTERN_PASSWORD("pattern.password.yes"),

		/** The sql select all issues. */
		SQL_ISSUE_SELECT_ALL("SQL.ISSUE.SELECT.ALL"),
		/** The sql select all issues. */
		SQL_ISSUE_INSERT_NEW("SQL.ISSUE.INSERT.NEW"),
		/** The sql select issue from id. */
		SQL_ISSUE_SELECT_FROM_ID("SQL.ISSUE.SELECT.FROM.ID"),
		/** The sql update issue from id. */
		SQL_ISSUE_UPDATE_FROM_ID("SQL.ISSUE.UPDATE.FROM.ID"),
		/** The sql select issue from user. */
		SQL_ISSUE_SELECT_FROM_ID_ASSIGNED("SQL.ISSUE.SELECT.FROM.ID.ASSIGNED"),

		/** The sql conf status select all. */
		SQL_CONF_STATUS_SELECT_ALL("SQL.CONF.STATUS.SELECT.ALL"),
		/** The sql conf status select from id. */
		SQL_CONF_STATUS_SELECT_FROM_ID("SQL.CONF.STATUS.SELECT.FROM.ID"),
		/** The sql conf status update. */
		SQL_CONF_STATUS_UPDATE("SQL.CONF.STATUS.UPDATE"),

		/** The sql conf priority select all. */
		SQL_CONF_PRIORITY_SELECT_ALL("SQL.CONF.PRIORITY.SELECT.ALL"),
		/** The sql conf priority select from id. */
		SQL_CONF_PRIORITY_SELECT_FROM_ID("SQL.CONF.PRIORITY.SELECT.FROM.ID"),
		/** The sql conf priority insert new. */
		SQL_CONF_PRIORITY_INSERT_NEW("SQL.CONF.PRIORITY.INSERT.NEW"),
		/** The sql conf priority update. */
		SQL_CONF_PRIORITY_UPDATE("SQL.CONF.PRIORITY.UPDATE"),

		/** The sql conf resolution select all. */
		SQL_CONF_RESOLUTION_SELECT_ALL("SQL.CONF.RESOLUTION.SELECT.ALL"),
		/** The sql conf resolution select from id. */
		SQL_CONF_RESOLUTION_SELECT_FROM_ID("SQL.CONF.RESOLUTION.SELECT.FROM.ID"),
		/** The sql conf resolution new. */
		SQL_CONF_RESOLUTION_INSERT_NEW("SQL.CONF.RESOLUTION.INSERT.NEW"),
		/** The sql conf resolution update. */
		SQL_CONF_RESOLUTION_UPDATE("SQL.CONF.RESOLUTION.UPDATE"),

		/** The sql conf type select all. */
		SQL_CONF_TYPE_SELECT_ALL("SQL.CONF.TYPE.SELECT.ALL"),
		/** The sql conf type select from id. */
		SQL_CONF_TYPE_SELECT_FROM_ID("SQL.CONF.TYPE.SELECT.FROM.ID"),
		/** The sql conf type insert new. */
		SQL_CONF_TYPE_INSERT_NEW("SQL.CONF.TYPE.INSERT.NEW"),
		/** The sql conf type update. */
		SQL_CONF_TYPE_UPDATE("SQL.CONF.TYPE.UPDATE"),

		/** The sql conf priority select from id. */
		SQL_PROJECT_SELECT_FROM_ID("SQL.PROJECT.SELECT.FROM.ID"),
		/** The sql project select project all. */
		SQL_PROJECT_SELECT_ALL("SQL.PROJECT.SELECT.ALL"),
		/** The sql project select max id. */
		SQL_PROJECT_SELECT_MAX_ID("SQL.PROJECT.SELECT.MAX.ID"),
		/** The sql project add new. */
		SQL_PROJECT_INSERT_NEW("SQL.PROJECT.INSERT.NEW"),
		/** The sql project add new. */
		SQL_PROJECT_UPDATE("SQL.PROJECT.UPDATE"),

		/** The sql build select from id. */
		SQL_BUILD_SELECT_FROM_ID("SQL.BUILD.SELECT.FROM.ID"),
		/** The sql build insert new. */
		SQL_BUILD_INSERT_NEW("SQL.BUILD.INSERT.NEW"),

		/** The sql comment select all. */
		SQL_COMMENT_SELECT_FROM_ID("SQL.COMMENT.SELECT.FROM.ID"),
		/** The sql comment select all. */
		SQL_COMMENT_INSERT_NEW("SQL.COMMENT.INSERT.NEW"),

		/** The sql user select all. */
		SQL_USER_SELECT_ALL("SQL.USER.SELECT.ALL"),
		/** The sql user select from id. */
		SQL_USER_SELECT_FROM_ID("SQL.USER.SELECT.FROM.ID"),
		/** The sql user select from email. */
		SQL_USER_SELECT_FROM_EMAIL("SQL.USER.SELECT.FROM.EMAIL"),
		/** The sql user insert new. */
		SQL_USER_INSERT_NEW("SQL.USER.INSERT.NEW"),
		/** The sql user update. */
		SQL_USER_UPDATE("SQL.USER.UPDATE");
		/** The string. */
		private static final Map<String, ConfKeys> TO_ENUM = new HashMap<String, ConfKeys>();
		/** The string. */
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
	 * @throws DaoException
	 *             the dao exception
	 * @see java.util.HashMap#get(java.lang.Object)
	 */
	public String get(Object key) throws DaoException {
		String value = keys.get(key);
		if (null == value) {
			throw new DaoException("key not found:" + key);
		}
		return value;
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

	/**
	 * Gets the int.
	 * @param key
	 *            the key
	 * @return the int
	 * @throws DaoException
	 *             the dao exception
	 */
	public int getInt(Object key) throws DaoException {
		String tmp = keys.get(key);
		int number = Integer.MIN_VALUE;
		if (null != tmp) {
			try {
				number = Integer.parseInt(tmp);
			} catch (NumberFormatException e) {
				throw new DaoException("key not found:" + key);
			}
		}
		return number;
	}
}
