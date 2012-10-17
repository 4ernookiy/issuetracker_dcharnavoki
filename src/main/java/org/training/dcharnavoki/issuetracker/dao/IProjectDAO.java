package org.training.dcharnavoki.issuetracker.dao;

import org.training.dcharnavoki.issuetracker.beans.Project;

/**
 * The Interface IProjectDAO.
 */
public interface IProjectDAO extends IDAO {

	/**
	 * Gets the project.
	 *
	 * @param pId the id
	 * @return the project
	 */
	Project getProject(int pId);
}
