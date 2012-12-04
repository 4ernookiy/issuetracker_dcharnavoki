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
	 * @param id
	 *            the id
	 * @return the issue
	 * @throws DaoException
	 *             the dao exception
	 */
	Issue getIssue(int id) throws DaoException;

	/**
	 * Gets the all issues.
	 * @return the all issues
	 * @throws DaoException
	 *             the dao exception
	 */
	List<Issue> getAllIssues() throws DaoException;

	/**
	 * Gets the issues for user.
	 * @param user
	 *            the user
	 * @return the issues for user
	 * @throws DaoException
	 *             the dao exception
	 */
	List<Issue> getIssuesForUser(User user) throws DaoException;

	/**
	 * Gets the id for new issue.
	 * @return the id for new issue
	 * @throws DaoException
	 *             the dao exception
	 */
	int getIdForNewIssue() throws DaoException;

	/**
	 * Adds the issue.
	 * @param newIssue
	 *            the new issue
	 * @throws DaoException
	 *             the dao exception
	 */
	void addIssue(Issue newIssue) throws DaoException;

	/**
	 * Update issue.
	 * @param update
	 *            the update
	 * @throws DaoException
	 *             the dao exception
	 */
	void updateIssue(Issue update) throws DaoException;
}
