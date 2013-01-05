package org.training.dcharnavoki.issuetracker.dao.impl.hibernate;

import org.training.dcharnavoki.issuetracker.beans.Resolution;
import org.training.dcharnavoki.issuetracker.dao.IResolutionDAO;
import org.training.dcharnavoki.issuetracker.util.HibernateUtil;

/**
 * The Class ResolutionImplHiber.
 */
public class ResolutionImplHiber extends GenericDAOHiber<Resolution, Integer> implements IResolutionDAO {

	/**
	 * Instantiates a new resolution impl hiber.
	 */
	public ResolutionImplHiber() {
		super(Resolution.class, HibernateUtil.getSessionFactory());
	}

}
