package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import org.training.dcharnavoki.issuetracker.beans.Status;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.IStatusDAO;

/**
 * The Class StatusImplXml.
 */
public class StatusImplXml extends CommonBeanImplXml<Status> implements IStatusDAO {

	/**
	 * Instantiates a new status impl xml.
	 * @throws DaoException
	 *             the dao exception
	 */
	public StatusImplXml() throws DaoException {
		super("/xml/Status.xml", Status.class);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.impl.xml.CommonBeanImplXml#
	 * getInstance(int)
	 */
	@Override
	public Status getInstance(int id) {
		return new Status(id);
	}

}
