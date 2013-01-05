package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import org.training.dcharnavoki.issuetracker.beans.Priority;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.IPriorityDAO;

/**
 * The Class PriorityImplSql.
 */
public class PriorityImplSql extends CommonBeanImplSql<Priority> implements IPriorityDAO {

	/**
	 * Instantiates a new priority impl sql.
	 *
	 * @throws DaoException the dao exception
	 */
	public PriorityImplSql() throws DaoException {
		super(Priority.class);
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getInstance()
	 */
	@Override
	public Priority getInstance() {
		return new Priority();
	}
}
