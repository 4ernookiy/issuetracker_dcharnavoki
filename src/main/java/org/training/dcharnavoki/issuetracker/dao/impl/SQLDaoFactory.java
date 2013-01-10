package org.training.dcharnavoki.issuetracker.dao.impl;

import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.BuildImplSql;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.CommentImplSql;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.IssueImplSql;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.PriorityImplSql;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.ProjectImplSql;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.ResolutionImplSql;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.StatusImplSql;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.TypeImplSql;
import org.training.dcharnavoki.issuetracker.dao.impl.sql.UserImplSql;

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
		setCommentDAO(new CommentImplSql());
		setUserDAO(new UserImplSql());
		setProjectDAO(new ProjectImplSql());
		setIssueDAO(new IssueImplSql());
		}

}