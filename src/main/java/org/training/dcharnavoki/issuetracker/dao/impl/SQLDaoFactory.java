package org.training.dcharnavoki.issuetracker.dao.impl;

import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.dao.impl.hibernate.CommentDAOHiber;
import org.training.dcharnavoki.issuetracker.dao.impl.hibernate.IssueImplHiber;
import org.training.dcharnavoki.issuetracker.dao.impl.hibernate.ProjectImplHiber;
import org.training.dcharnavoki.issuetracker.dao.impl.hibernate.UserImplHiber;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.BuildImplSql;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.PriorityImplSql;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.ResolutionImplSql;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.StatusImplSql;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.TypeImplSql;

/**
 * A factory for creating SQLDao objects.
 */
public class SQLDaoFactory extends DaoFactory {

	/**
	 * Instantiates a new sQL dao factory.
	 *
	 * @throws DaoException the dao exception
	 */
	public SQLDaoFactory() throws DaoException {
		super();
		setPriorityDAO(new PriorityImplSql());
		setResolutionDAO(new ResolutionImplSql());
		setStatusDAO(new StatusImplSql());
		setTypeDAO(new TypeImplSql());
		setBuildDAO(new BuildImplSql());

//		setUserDAO(new UserImplHiber());
//		setCommentDAO(new CommentDAOHiber());
//		setProjectDAO(new ProjectImplHiber());
//		setIssueDAO(new IssueImplHiber());
		}

}