package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import org.training.dcharnavoki.issuetracker.beans.Resolution;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.IResolutionDAO;

/**
 * The Class ResolutionImplXml.
 */
public class ResolutionImplXml extends CommonBeanImplXml<Resolution> implements IResolutionDAO {

	/**
	 * Instantiates a new resolution impl xml.
	 *
	 * @throws DaoException the dao exception
	 */
	public ResolutionImplXml() throws DaoException {
		super("/xml/Resolution.xml", Resolution.class);
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.xml.CommonBeanImplXml#getInstance(int)
	 */
	@Override
	public Resolution getInstance(int id) {
		return new Resolution(id);
	}

}
