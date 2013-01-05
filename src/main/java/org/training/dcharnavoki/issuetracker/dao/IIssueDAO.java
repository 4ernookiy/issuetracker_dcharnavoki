package org.training.dcharnavoki.issuetracker.dao;

import java.util.List;

import org.training.dcharnavoki.issuetracker.beans.Issue;
import org.training.dcharnavoki.issuetracker.beans.User;

/**
 * The Interface IIssueDAO.
 */
public interface IIssueDAO extends GenericDAO<Issue, Integer> {

	/**
	 * Gets the issues for user.
	 *
	 * @param user the user
	 * @return the issues for user
	 * @throws DaoException the dao exception
	 */
	List<Issue> getIssuesForUser(User user) throws DaoException;

}
