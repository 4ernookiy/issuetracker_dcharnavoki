package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import org.training.dcharnavoki.issuetracker.beans.Type;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.ITypeDAO;

/**
 * The Class TypeImplXml.
 */
public class TypeImplXml extends CommonBeanImplXml<Type> implements ITypeDAO {

	/**
	 * Instantiates a new type impl xml.
	 *
	 * @throws DaoException the dao exception
	 */
	public TypeImplXml() throws DaoException {
		super("/xml/Type.xml", Type.class);
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.xml.CommonBeanImplXml#getInstance(int)
	 */
	@Override
	public Type getInstance(int id) {
		return new Type(id);
	}

}
