package org.training.dcharnavoki.issuetracker.dao;

import java.util.List;

import org.training.dcharnavoki.issuetracker.beans.Build;
import org.training.dcharnavoki.issuetracker.beans.Project;

/**
 * The Interface IProjectDAO.
 */
public interface IProjectDAO {

	/**
	 * Gets the project.
	 * @param pId
	 *            the id
	 * @return the project
	 * @throws DaoException
	 *             the dao exception
	 */
	Project getProject(int pId) throws DaoException;

	/**
	 * Gets the projects.
	 * @return the projects
	 * @throws DaoException
	 *             the dao exception
	 */
	List<Project> getProjects() throws DaoException;

	/**
	 * Gets the id for new projects.
	 * @return the id for new projects
	 * @throws DaoException
	 *             the dao exception
	 */
	int getIdForNewProjects() throws DaoException;

	/**
	 * Adds the project.
	 * @param newProject
	 *            the new project
	 * @throws DaoException
	 *             the dao exception
	 */
	void addProject(Project newProject) throws DaoException;

	/**
	 * Adds the build.
	 * @param newBuild
	 *            the new build
	 * @param projectId
	 *            the project id
	 * @throws DaoException
	 *             the dao exception
	 */
	void addBuild(Build newBuild, int projectId) throws DaoException;

	/**
	 * Update project.
	 * @param update
	 *            the update
	 * @throws DaoException
	 *             the dao exception
	 */
	void updateProject(Project update) throws DaoException;
}
