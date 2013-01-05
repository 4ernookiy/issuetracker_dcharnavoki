package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import org.training.dcharnavoki.issuetracker.beans.Status;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.IStatusDAO;

/**
 * The Class StatusImplSql.
 */
public class StatusImplSql extends CommonBeanImplSql<Status> implements IStatusDAO {

	/**
	 * Instantiates a new status impl sql.
	 * @throws DaoException
	 *             the dao exception
	 */
	public StatusImplSql() throws DaoException {
		super(Status.class);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getInstance
	 * ()
	 */
	@Override
	public Status getInstance() {
		return new Status();
	}

}
