package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import org.training.dcharnavoki.issuetracker.beans.Build;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.IBuildDAO;

/**
 * The Class BuildImplSql.
 */
public class BuildImplSql extends CommonBeanImplSql<Build> implements IBuildDAO {

	/**
	 * Instantiates a new builds the impl sql.
	 *
	 * @throws DaoException the dao exception
	 */
	public BuildImplSql() throws DaoException {
		super(Build.class);
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getInstance()
	 */
	@Override
	public Build getInstance() {
		return new Build();
	}

}
