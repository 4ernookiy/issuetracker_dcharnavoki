package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.training.dcharnavoki.issuetracker.beans.Bean;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.GenericDAO;

/**
 * The Class GenericDAOXml.
 * @param <T>
 *            the generic type
 */
public abstract class GenericDAOXml<T extends Bean> extends DefaultParser implements
		GenericDAO<T, Integer> {

	/** The class. */
	private Class<T> klass;

	/** The users. */
	private Map<Integer, T> entities = new HashMap<Integer, T>();

	/**
	 * Instantiates a new generic dao xml.
	 * @param filename
	 *            the filename
	 * @param klass
	 *            the klass
	 * @throws DaoException
	 *             the dao exception
	 */
	public GenericDAOXml(String filename, Class<T> klass) throws DaoException {
		super(filename);
		this.klass = klass;
	}

	/**
	 * Gets the klass.
	 * @return the klass
	 */
	public Class<T> getKlass() {
		return klass;
	}

	/**
	 * Gets the entities.
	 * @return the entities
	 */
	protected Map<Integer, T> getEntities() {
		return entities;
	}

	/**
	 * Gets the id for new entity.
	 * @return the id for new entity
	 * @throws DaoException
	 *             the dao exception
	 */
	private Integer getIdForNewEntity() throws DaoException {
		waitCompete();
		int maxId = 0;
		List<T> list = new ArrayList<T>(getEntities().values());
		for (T tmpT : list) {
			if (tmpT.getId() > maxId) {
				maxId = tmpT.getId();
			}
		}
		return maxId + 1;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.GenericDAO#save(java.lang.Object
	 * )
	 */
	@Override
	public Integer save(T entity) throws DaoException {
		waitCompete();
		Integer id = getIdForNewEntity();
		entity.setId(id);
		getEntities().put(id, entity);
		return id;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.GenericDAO#update(java.lang
	 * .Object)
	 */
	@Override
	public void update(T entity) throws DaoException {
		if (entity.getId() == 0) {
			throw new DaoException("id = 0, not update!!");
		}
		waitCompete();
		getEntities().put(entity.getId(), entity);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.GenericDAO#deleteEntity(java
	 * .lang.Object)
	 */
	@Override
	public void deleteEntity(T entity) throws DaoException {
		waitCompete();
		getEntities().remove(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.GenericDAO#findAll()
	 */
	@Override
	public List<T> findAll() throws DaoException {
		waitCompete();
		return new ArrayList<T>(getEntities().values());
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.GenericDAO#findByID(java.io
	 * .Serializable)
	 */
	@Override
	public T findByID(Integer id) throws DaoException {
		waitCompete();
		return getEntities().get(id);
	}

}
