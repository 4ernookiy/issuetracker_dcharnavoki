package org.training.dcharnavoki.issuetracker.dao;

import java.util.List;

import org.training.dcharnavoki.issuetracker.beans.Issue;
import org.training.dcharnavoki.issuetracker.beans.User;

/**
 * The Interface IIssueDAO.
 */
public interface IIssueDAO {
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
	/**
	 * Gets the issues for user.
	 *
	 * @param user the user
	 * @return the issues for user
	 */
	List<Issue> getIssuesForUser(User user);

}
