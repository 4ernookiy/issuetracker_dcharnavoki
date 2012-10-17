package org.training.dcharnavoki.issuetracker.dao;

import java.util.List;

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
	/**
	 * Gets the all issues.
	 *
	 * @return the all issues
	 */
	List<Issue> getAllIssues();

}
