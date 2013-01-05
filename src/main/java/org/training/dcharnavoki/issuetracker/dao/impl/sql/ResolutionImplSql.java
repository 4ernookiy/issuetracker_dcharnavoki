package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import org.training.dcharnavoki.issuetracker.beans.Resolution;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.IResolutionDAO;

/**
 * The Class ResolutionImplSql.
 */
public class ResolutionImplSql extends CommonBeanImplSql<Resolution> implements IResolutionDAO {

	/**
	 * Instantiates a new resolution impl sql.
	 * @throws DaoException
	 *             the dao exception
	 */
	public ResolutionImplSql() throws DaoException {
		super(Resolution.class);
	}

	@Override
	public Resolution getInstance() {
		return new Resolution();
	}

}
