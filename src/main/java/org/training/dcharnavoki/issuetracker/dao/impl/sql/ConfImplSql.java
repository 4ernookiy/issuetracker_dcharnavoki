package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import java.util.List;

import org.training.dcharnavoki.issuetracker.beans.Priority;
import org.training.dcharnavoki.issuetracker.beans.Resolution;
import org.training.dcharnavoki.issuetracker.beans.Status;
import org.training.dcharnavoki.issuetracker.beans.Type;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.IConfDAO;

/**
 * The Class ConfSql.
 */
public class ConfImplSql extends AbstractBaseDB implements IConfDAO {

	/**
	 * Instantiates a new conf sql.
	 * @throws DaoException
	 *            the dao exception
	 */
	public ConfImplSql() throws DaoException {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getStatus(int)
	 */
	@Override
	public Status getStatus(int sId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getStatuses()
	 */
	@Override
	public List<Status> getStatuses() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getPriority(int)
	 */
	@Override
	public Priority getPriority(int pId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getPriorities()
	 */
	@Override
	public List<Priority> getPriorities() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getResolution(int)
	 */
	@Override
	public Resolution getResolution(int rId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getResolutions()
	 */
	@Override
	public List<Resolution> getResolutions() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getType(int)
	 */
	@Override
	public Type getType(int tId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getTypes()
	 */
	@Override
	public List<Type> getTypes() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
