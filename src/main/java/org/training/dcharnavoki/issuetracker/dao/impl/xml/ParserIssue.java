package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Build;
import org.training.dcharnavoki.issuetracker.beans.Issue;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.dao.IConfDAO;
import org.training.dcharnavoki.issuetracker.dao.IIssueDAO;
import org.training.dcharnavoki.issuetracker.dao.IProjectDAO;
import org.training.dcharnavoki.issuetracker.dao.IUserDAO;
import org.xml.sax.SAXException;

/**
 * The Class ParserIssue.
 */
public class ParserIssue extends DefaultParser implements IIssueDAO {
	/** The Constant FILE_XML. */
	private static final String FILE_XML = "/xml/base.xml";
	/** The user dao. */
	private final IUserDAO userDao = DaoFactory.getFactory().getUserDAO();
	/** The conf dao. */
	private final IConfDAO confDao = DaoFactory.getFactory().getConfDAO();
	/** The project dao. */
	private final IProjectDAO projectDao = DaoFactory.getFactory().getProjectDAO();
	/** The Constant LOGGER. */
	private final Logger log = Logger.getLogger(getClass());
	/** The tag. */
	private Tags tag;
	/** The value tag. */
	private String valueTag;
	/** The issue. */
	private Issue issue;
	/** The issues. */
	private Map<Integer, Issue> issues = new HashMap<Integer, Issue>();
	/** The tmp. */
	private String tmp;
	/** The id. */
	private int id;

	/**
	 * Instantiates a new parser issue.
	 *
	 * @throws DaoException the dao exception
	 */
	public ParserIssue() throws DaoException {
		super(FILE_XML);
	}

	/**
	 * The Enum Tags.
	 */
	private static enum Tags {
		/** The issue. */
		ISSUE,
		/** The id. */
		ID,
		/** The createdate. */
		CREATEDATE,
		/** The createby. */
		CREATEBY,
		/** The modifydate. */
		MODIFYDATE,
		/** The modifyby. */
		MODIFYBY,
		/** The summary. */
		SUMMARY,
		/** The description. */
		DESCRIPTION,

		/** The status. */
		STATUS,
		/** The resolution. */
		RESOLUTION,
		/** The priority. */
		PRIORITY,
		/** The type. */
		TYPE,
		/** The project. */
		PROJECT,
		/** The buildfound. */
		BUILDFOUND,
		/** The assigned. */
		ASSIGNED;
		/** The Constant stringToEnum. */
		private static final Map<String, Tags> TO_ENUM = new HashMap<String, Tags>();
		static {
			for (Tags op : values()) {
				TO_ENUM.put(op.toString(), op);
			}
		}

		/**
		 * From string.
		 *
		 * @param string
		 *            the string
		 * @return the tags
		 */
		public static Tags fromString(String string) {
			return TO_ENUM.get(string.toUpperCase());
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			org.xml.sax.Attributes attributes) throws SAXException {
		tag = Tags.fromString(qName);
		if (tag == null) {
			return;
		}

		switch (tag) {
		case ISSUE:
			tmp = attributes.getValue("id").trim();
			id = Integer.parseInt(tmp);
			issue = new Issue(id);
			tag = null;
			break;
		default:
			break;
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		try {
		tag = Tags.fromString(qName);
		if (tag == null) {
			return;
		}
		switch (tag) {
		case CREATEDATE:
			issue.setCreateDate(getDateFromString(valueTag));
			break;
		case CREATEBY:
			id = Integer.parseInt(valueTag);
			User user = null;
				user = userDao.getUser(id);
			issue.setCreatedBy(user);
			break;
		case MODIFYDATE:
			issue.setModifyDate(getDateFromString(valueTag));
			break;
		case MODIFYBY:
			id = Integer.parseInt(valueTag);
				issue.setModifiedBy(userDao.getUser(id));
			break;
		case SUMMARY:
			issue.setSummary(valueTag);
			break;
		case DESCRIPTION:
			issue.setDescription(valueTag);
			break;
		case STATUS:
			id = Integer.parseInt(valueTag);
				issue.setStatus(confDao.getStatus(id));
			break;
		case RESOLUTION:
			id = Integer.parseInt(valueTag);
				issue.setResolution(confDao.getResolution(id));
			break;
		case PRIORITY:
			id = Integer.parseInt(valueTag);
				issue.setPriority(confDao.getPriority(id));
			break;
		case TYPE:
			id = Integer.parseInt(valueTag);
				issue.setType(confDao.getType(id));
			break;
		case PROJECT:
			id = Integer.parseInt(valueTag);
				issue.setProject(projectDao.getProject(id));
			break;
		case BUILDFOUND:
			id = Integer.parseInt(valueTag);
			Build build = issue.getProject().getBuild(id);
			issue.setBuild(build);
			break;
		case ASSIGNED:
			id = Integer.parseInt(valueTag);
				issue.setAssigned(userDao.getUser(id));
			break;
		case ISSUE:
			issues.put(issue.getId(), issue);
			break;
		default:
		}
		tag = null;
		} catch (DaoException e) {
			if (log.isEnabledFor(Level.ERROR)) {
				log.error(e);
			}
			throw new SAXException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (tag == null) {
			return;
		}
		valueTag = new String(ch, start, length).trim();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.training.dcharnavoki.issuetracker.dao.IIssueDAO#getIssue(int)
	 */
	@Override
	public Issue getIssue(int idIssue) throws DaoException {
		waitCompete();
		return issues.get(idIssue);
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IIssueDAO#getAllIssues()
	 */
	@Override
	public List<Issue> getAllIssues() throws DaoException {
		waitCompete();
		return new ArrayList<Issue>(issues.values());
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IIssueDAO#getIssuesForUser(org.training.dcharnavoki.issuetracker.beans.User)
	 */
	@Override
	public List<Issue> getIssuesForUser(User user) throws DaoException {
		waitCompete();
		List<Issue> listForUser = new ArrayList<Issue>();
		for (Issue issueTmp : issues.values()) {
			if (issueTmp.getAssigned().getId() == user.getId()) {
				listForUser.add(issueTmp);
			}
		}
		return listForUser;
	}

}
