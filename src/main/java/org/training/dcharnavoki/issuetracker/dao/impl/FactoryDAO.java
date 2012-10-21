package org.training.dcharnavoki.issuetracker.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.dao.IDAO;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.SqlConf;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.ParserComment;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.ParserConf;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.ParserIssue;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.ParserProject;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.ParserUser;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp;
import org.training.dcharnavoki.issuetracker.start.preparing.LoadConfig;

/**
 * The Class FactoryDAO.
 */
public final class FactoryDAO {
	/**
	 * The Enum Choice.
	 */
	public enum Choice {
		/** The config. */
		CONFIG,
		/** The issue. */
		ISSUE,
		/** The user. */
		USER,
		/** The project. */
		PROJECT,
		/** The comment. */
		COMMENT;
	}
	/** The config app. */
	private static ConfigApp configApp = LoadConfig
			.getConfig(Constant.CONFIG_PROPERTY_FILE);;

	/** The map. */
	private static Map<String, IDAO> map = new HashMap<String, IDAO>();

	static {
		map.put(ParserConf.class.getCanonicalName(), new ParserConf());
		map.put(ParserUser.class.getCanonicalName(), new ParserUser());
		map.put(ParserProject.class.getCanonicalName(), new ParserProject());
		map.put(SqlConf.class.getCanonicalName(), new SqlConf());
		map.put(ParserIssue.class.getCanonicalName(), new ParserIssue());
		map.put(ParserComment.class.getCanonicalName(), new ParserComment());
	}

	/**
	 * Instantiates a new factory dao.
	 */
	private FactoryDAO() {
		super();
	}

	/**
	 * Gets the implementation.
	 *
	 * @param choice
	 *            the choice
	 * @return the implementation
	 */

	public static IDAO getImplementation(Choice choice) {

		switch (choice) {
		case CONFIG:
			return map.get(configApp.getImplConf().getImplementation());

		case ISSUE:
			return map.get(configApp.getImplIssue().getImplementation());

		case USER:
			return map.get(configApp.getImplUser().getImplementation());

		case PROJECT:
			return map.get(configApp.getImplProject().getImplementation());

		case COMMENT:
			return map.get(configApp.getImplComment().getImplementation());
		default:
			 throw new IllegalArgumentException();
		}
	}
}
