package org.training.dcharnavoki.issuetracker.dao;

import org.training.dcharnavoki.issuetracker.beans.Project;

/**
 * The Interface IProjectDAO.
 */
public interface IProjectDAO {

	/**
	 * Gets the project.
	 *
	 * @param pId the id
	 * @return the project
	 * @throws DaoException the dao exception
	 */
	Project getProject(int pId) throws DaoException;
}
