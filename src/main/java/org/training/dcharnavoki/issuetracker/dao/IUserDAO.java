package org.training.dcharnavoki.issuetracker.dao;

import org.training.dcharnavoki.issuetracker.beans.User;

/**
 * The Interface IUserDAO.
 */
public interface IUserDAO extends GenericDAO<User, Integer> {

	/**
	 * Gets the user.
	 * @param email
	 *           the email
	 * @return the user
	 * @throws DaoException
	 *            the dao exception
	 */
	User getUser(String email) throws DaoException;

}
