package org.training.dcharnavoki.issuetracker.dao;

import org.training.dcharnavoki.issuetracker.beans.Issue;

/**
 * The Interface IIssueDAO.
 */
public interface IIssueDAO extends IDAO {
	/**
	 * Gets the issue.
	 *
	 * @param id the id
	 * @return the issue
	 */
	Issue getIssue(int id);

}
