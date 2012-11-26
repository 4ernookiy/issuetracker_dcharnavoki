package org.training.dcharnavoki.issuetracker.dao;

import java.util.List;

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

	/**
	 * Gets the id for new user.
	 *
	 * @return the id for new user
	 * @throws DaoException the dao exception
	 */
	int getIdForNewUser() throws DaoException;
	/**
	 * Adds the user.
	 *
	 * @param newUser the new user
	 * @throws DaoException the dao exception
	 */
	void addUser(User newUser) throws DaoException;
	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 * @throws DaoException the dao exception
	 */
	List<User> getAllUsers() throws DaoException;

}
