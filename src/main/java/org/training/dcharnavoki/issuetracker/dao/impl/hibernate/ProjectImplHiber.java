package org.training.dcharnavoki.issuetracker.dao.impl.hibernate;

import org.training.dcharnavoki.issuetracker.beans.Project;
import org.training.dcharnavoki.issuetracker.dao.IProjectDAO;
import org.training.dcharnavoki.issuetracker.util.HibernateUtil;

/**
 * The Class ProjectImplHiber.
 */
public class ProjectImplHiber extends GenericDAOHiber<Project, Integer>  implements IProjectDAO {

	/**
	 * Instantiates a new project impl hiber.
	 */
	public ProjectImplHiber() {
		super(Project.class, HibernateUtil.getSessionFactory());
	}

}
