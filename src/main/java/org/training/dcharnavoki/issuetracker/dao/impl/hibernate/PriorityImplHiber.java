package org.training.dcharnavoki.issuetracker.dao.impl.hibernate;


import org.training.dcharnavoki.issuetracker.beans.Priority;
import org.training.dcharnavoki.issuetracker.dao.IPriorityDAO;
import org.training.dcharnavoki.issuetracker.util.HibernateUtil;

/**
 * The Class PriorityImplHiber.
 */
public class PriorityImplHiber extends GenericDAOHiber<Priority, Integer> implements IPriorityDAO {

	/**
	 * Instantiates a new priority impl hiber.
	 *
	 */
	public PriorityImplHiber() {
		super(Priority.class, HibernateUtil.getSessionFactory());
	}

}
