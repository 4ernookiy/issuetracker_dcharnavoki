package org.training.dcharnavoki.issuetracker.dao;

import org.training.dcharnavoki.issuetracker.beans.Priority;
import org.training.dcharnavoki.issuetracker.beans.Resolution;
import org.training.dcharnavoki.issuetracker.beans.Status;
import org.training.dcharnavoki.issuetracker.beans.Type;

/**
 * The Interface IConfDAO.
 */
public interface IConfDAO {

	/**
	 * Gets the status.
	 * @param sId
	 *            the s id
	 * @return the status
	 * @throws DaoException
	 *             the dao exception
	 */
	Status getStatus(int sId) throws DaoException;

	/**
	 * Gets the priority.
	 * @param pId
	 *            the id
	 * @return the priority
	 * @throws DaoException
	 *             the dao exception
	 */
	Priority getPriority(int pId) throws DaoException;

	/**
	 * Gets the resolution.
	 * @param rId
	 *            the r id
	 * @return the resolution
	 * @throws DaoException
	 *             the dao exception
	 */
	Resolution getResolution(int rId) throws DaoException;

	/**
	 * Gets the type.
	 * @param tId
	 *            the t id
	 * @return the type
	 * @throws DaoException
	 *             the dao exception
	 */
	Type getType(int tId) throws DaoException;

}
