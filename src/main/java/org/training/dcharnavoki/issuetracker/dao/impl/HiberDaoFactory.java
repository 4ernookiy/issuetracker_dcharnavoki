package org.training.dcharnavoki.issuetracker.dao.impl;

import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.dao.impl.hibernate.BuildImplHiber;
import org.training.dcharnavoki.issuetracker.dao.impl.hibernate.CommentDAOHiber;
import org.training.dcharnavoki.issuetracker.dao.impl.hibernate.IssueImplHiber;
import org.training.dcharnavoki.issuetracker.dao.impl.hibernate.PriorityImplHiber;
import org.training.dcharnavoki.issuetracker.dao.impl.hibernate.ProjectImplHiber;
import org.training.dcharnavoki.issuetracker.dao.impl.hibernate.ResolutionImplHiber;
import org.training.dcharnavoki.issuetracker.dao.impl.hibernate.StatusImplHiber;
import org.training.dcharnavoki.issuetracker.dao.impl.hibernate.TypeImplHiber;
import org.training.dcharnavoki.issuetracker.dao.impl.hibernate.UserImplHiber;

/**
 * A factory for creating HiberDao objects.
 */
public class HiberDaoFactory extends DaoFactory {
	/**
	 * Instantiates a new hiber dao factory.
	 */
	public HiberDaoFactory() {
		super();
		setPriorityDAO(new PriorityImplHiber());
		setResolutionDAO(new ResolutionImplHiber());
		setStatusDAO(new StatusImplHiber());
		setTypeDAO(new TypeImplHiber());
		setUserDAO(new UserImplHiber());
		setCommentDAO(new CommentDAOHiber());
		setBuildDAO(new BuildImplHiber());
		setProjectDAO(new ProjectImplHiber());
		setIssueDAO(new IssueImplHiber());
		}

}
