package org.training.dcharnavoki.issuetracker.dao;

import org.training.dcharnavoki.issuetracker.beans.User;

/**
 * The Interface IUserDAO.
 */
public interface IUserDAO {

	/**
	 * Gets the user.
	 * @param uId
	 *            the id
	 * @return the user
	 * @throws DaoException
	 *             the dao exception
	 */
	User getUser(int uId) throws DaoException;

	/**
	 * Gets the user.
	 * @param email
	 *            the email
	 * @return the user
	 * @throws DaoException
	 *             the dao exception
	 */
	User getUser(String email) throws DaoException;

}
