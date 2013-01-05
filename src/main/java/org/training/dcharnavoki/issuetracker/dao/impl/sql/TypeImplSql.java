package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import org.training.dcharnavoki.issuetracker.beans.Type;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.ITypeDAO;

/**
 * The Class TypeImplSql.
 */
public class TypeImplSql extends CommonBeanImplSql<Type> implements ITypeDAO {

	/**
	 * Instantiates a new type impl sql.
	 *
	 * @throws DaoException the dao exception
	 */
	public TypeImplSql() throws DaoException {
		super(Type.class);
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getInstance()
	 */
	@Override
	public Type getInstance() {
		return new Type();
	}

}
