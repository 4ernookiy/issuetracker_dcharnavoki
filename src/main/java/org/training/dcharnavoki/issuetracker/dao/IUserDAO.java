package org.training.dcharnavoki.issuetracker.dao;

import org.training.dcharnavoki.issuetracker.beans.User;

/**
 * The Interface IUserDAO.
 */
public interface IUserDAO extends IDAO {
	/**
	 * Gets the user.
	 *
	 * @param uId the id
	 * @return the user
	 */
	User getUser(int uId);
	/**
	 * Gets the user.
	 *
	 * @param email the email
	 * @param password the password
	 * @return the user
	 */
	User getUser(String email, String password);

}
