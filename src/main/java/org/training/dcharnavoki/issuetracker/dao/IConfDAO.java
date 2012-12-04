package org.training.dcharnavoki.issuetracker.dao;

import java.util.List;

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
	 *           the s id
	 * @return the status
	 * @throws DaoException
	 *            the dao exception
	 */
	Status getStatus(int sId) throws DaoException;

	/**
	 * Gets the statuses.
	 * @return the statuses
	 * @throws DaoException
	 *            the dao exception
	 */
	List<Status> getStatuses() throws DaoException;

	/**
	 * Gets the priority.
	 * @param pId
	 *           the id
	 * @return the priority
	 * @throws DaoException
	 *            the dao exception
	 */
	Priority getPriority(int pId) throws DaoException;

	/**
	 * Gets the priorities.
	 * @return the priorities
	 * @throws DaoException
	 *            the dao exception
	 */
	List<Priority> getPriorities() throws DaoException;

	/**
	 * Gets the resolution.
	 * @param rId
	 *           the r id
	 * @return the resolution
	 * @throws DaoException
	 *            the dao exception
	 */
	Resolution getResolution(int rId) throws DaoException;

	/**
	 * Gets the resolutions.
	 * @return the resolutions
	 * @throws DaoException
	 *            the dao exception
	 */
	List<Resolution> getResolutions() throws DaoException;

	/**
	 * Gets the type.
	 * @param tId
	 *           the t id
	 * @return the type
	 * @throws DaoException
	 *            the dao exception
	 */
	Type getType(int tId) throws DaoException;

	/**
	 * Gets the types.
	 * @return the types
	 * @throws DaoException
	 *            the dao exception
	 */
	List<Type> getTypes() throws DaoException;

	/**
	 * Adds the resolution.
	 * @param newResolution
	 *           the new resolution
	 * @throws DaoException
	 *            the dao exception
	 */
	void addResolution(Resolution newResolution) throws DaoException;

	/**
	 * Update resolution.
	 * @param update
	 *           the update
	 * @throws DaoException
	 *            the dao exception
	 */
	void updateResolution(Resolution update) throws DaoException;

	/**
	 * Update priority.
	 * @param priority
	 *           the priority
	 * @throws DaoException
	 *            the dao exception
	 */
	void updatePriority(Priority priority) throws DaoException;

	/**
	 * Adds the priority.
	 * @param newItem
	 *           the new item
	 * @throws DaoException
	 *            the dao exception
	 */
	void addPriority(Priority newItem) throws DaoException;

	/**
	 * Adds the type.
	 * @param newItem
	 *           the new item
	 * @throws DaoException
	 *            the dao exception
	 */
	void addType(Type newItem) throws DaoException;

	/**
	 * Update type.
	 * @param type
	 *           the type
	 * @throws DaoException
	 *            the dao exception
	 */
	void updateType(Type type) throws DaoException;

	/**
	 * Update type.
	 * @param update
	 *           the status
	 * @throws DaoException
	 *            the dao exception
	 */
	void updateType(Status update) throws DaoException;
}
