package org.training.dcharnavoki.issuetracker.dao.impl;

import org.training.dcharnavoki.issuetracker.dao.impl.sql.SqlConf;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.ParserConf;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.ParserIssue;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.ParserProject;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.ParserUser;

/**
 * The Enum DAO.
 */
public enum DAO {
	/** The xml conf. */
	XML_CONF(ParserConf.class.getCanonicalName()),
	/** The xml issue. */
	XML_ISSUE(ParserIssue.class.getCanonicalName()),
	/** The xml user. */
	XML_USER(ParserUser.class.getCanonicalName()),
	/** The xml project. */
	XML_PROJECT(ParserProject.class.getCanonicalName()),
	/** The sql conf. */
	SQL_CONF(SqlConf.class.getCanonicalName());
	/** The string. */
	private String string;
	/**
	 * Instantiates a new dao.
	 *
	 * @param key the string
	 */
	private DAO(String key) {
		this.string = key;
	}
	/**
	 * Gets the implementation.
	 *
	 * @return the implementation
	 */
	public String getImplementation() {
		return string;
	}
}