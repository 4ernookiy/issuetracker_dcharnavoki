package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import org.training.dcharnavoki.issuetracker.beans.Priority;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.IPriorityDAO;

/**
 * The Class PriorityImplXml.
 */
public class PriorityImplXml extends CommonBeanImplXml<Priority> implements IPriorityDAO {

	/**
	 * Instantiates a new priority impl xml.
	 *
	 * @throws DaoException the dao exception
	 */
	public PriorityImplXml() throws DaoException {
		super("/xml/Priority.xml", Priority.class);	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.xml.CommonBeanImplXml#getInstance(int)
	 */
	@Override
	public Priority getInstance(int id) {
		return new Priority(id);
	}

}
