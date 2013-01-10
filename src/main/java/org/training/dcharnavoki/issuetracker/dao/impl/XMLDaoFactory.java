package org.training.dcharnavoki.issuetracker.dao.impl;

import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.BuildImplXml;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.CommentImplXml;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.IssueImplXml;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.PriorityImplXml;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.ProjectImplXml;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.ResolutionImplXml;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.StatusImplXml;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.TypeImplXml;
import org.training.dcharnavoki.issuetracker.dao.impl.xml.UserImplXml;

/**
 * DAO Factory that creates ConferenceDao for memory usage.
 */
public class XMLDaoFactory extends DaoFactory {

	/**
	 * Instantiates a new xML dao factory.
	 *
	 * @throws DaoException the dao exception
	 */
	public XMLDaoFactory() throws DaoException {
		super();
		setPriorityDAO(new PriorityImplXml());
		setResolutionDAO(new ResolutionImplXml());
		setStatusDAO(new StatusImplXml());
		setTypeDAO(new TypeImplXml());
		setBuildDAO(new BuildImplXml());
		setUserDAO(new UserImplXml());
		setProjectDAO(new ProjectImplXml());
		setCommentDAO(new CommentImplXml());
		setIssueDAO(new IssueImplXml());
		}
}