package org.training.dcharnavoki.issuetracker.dao.impl.hibernate;

import org.training.dcharnavoki.issuetracker.beans.Status;
import org.training.dcharnavoki.issuetracker.dao.IStatusDAO;
import org.training.dcharnavoki.issuetracker.util.HibernateUtil;

/**
 * The Class StatusImplHiber.
 */
public class StatusImplHiber extends GenericDAOHiber<Status, Integer> implements IStatusDAO {

	/**
	 * Instantiates a new status impl hiber.
	 */
	public StatusImplHiber() {
		super(Status.class,  HibernateUtil.getSessionFactory());
	}

}
