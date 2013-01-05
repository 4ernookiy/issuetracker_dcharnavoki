package org.training.dcharnavoki.issuetracker.dao;

import java.io.Serializable;
import java.util.List;

/**
 * The Interface GenericDAO.
 *
 * @param <T> the generic type
 * @param <ID> the generic type
 * @author dima
 * http://www.ibm.com/developerworks/ru/library/j-genericdao/
 * The Interface GenericDAO.
 */
public interface GenericDAO<T, ID extends Serializable> {

	/**
	 * Save.
	 *
	 * @param entity the entity
	 * @return the id
	 * @throws DaoException the dao exception
	 */
	ID save(T entity) throws DaoException;

	/**
	 * Update.
	 * @param entity
	 *            the entity
	 * @throws DaoException
	 *             the dao exception
	 */
	void update(T entity) throws DaoException;
	/**
	 * Delete.
	 * @param entity
	 *            the entity
	 * @throws DaoException
	 *             the dao exception
	 */
	void deleteEntity(T entity) throws DaoException;

	/**
	 * get All .
	 *            the clazz
	 * @return the list
	 * @throws DaoException
	 *             the dao exception
	 */
	List<T> findAll() throws DaoException;

	/**
	 * Gets the by id.
	 *            the clazz
	 * @param id
	 *            the id
	 * @return the by id
	 * @throws DaoException
	 *             the dao exception
	 */
	T findByID(ID id) throws DaoException;
}