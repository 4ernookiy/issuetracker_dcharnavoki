package org.training.dcharnavoki.issuetracker.dao.impl.hibernate;

import org.training.dcharnavoki.issuetracker.beans.Build;
import org.training.dcharnavoki.issuetracker.dao.IBuildDAO;
import org.training.dcharnavoki.issuetracker.util.HibernateUtil;

/**
 * The Class BuildImplHiber.
 */
public class BuildImplHiber extends GenericDAOHiber<Build, Integer> implements IBuildDAO {

	/**
	 * Instantiates a new builds the impl hiber.
	 */
	public BuildImplHiber() {
		super(Build.class, HibernateUtil.getSessionFactory());
	}

}
