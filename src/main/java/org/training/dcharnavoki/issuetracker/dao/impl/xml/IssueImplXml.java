package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.training.dcharnavoki.issuetracker.beans.Issue;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.dao.IIssueDAO;
import org.xml.sax.SAXException;

/**
 * The Class IssueImplXml.
 */
public class IssueImplXml extends GenericDAOXml<Issue> implements IIssueDAO {
	private Tags tag;
	/** The value tag. */
	private String valueTag;
	/** The issue. */
	private Issue issue;
	/** The id. */
	private int id;

	/**
	 * Instantiates a new issue impl xml.
	 * @throws DaoException
	 *             the dao exception
	 */
	public IssueImplXml() throws DaoException {
		super("/xml/Issue.xml", Issue.class);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.IIssueDAO#getIssuesForUser(
	 * org.training.dcharnavoki.issuetracker.beans.User)
	 */
	@Override
	public List<Issue> getIssuesForUser(User user) throws DaoException {
		List<Issue> issues = new ArrayList<Issue>();
		List<Issue> all;
		all = findAll();
		for (Issue is : all) {
			if (is.getAssigned().getId() == user.getId()) {
				issues.add(is);
			}
		}
		return issues;
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
			String tmp = attributes.getValue("id").trim();
			int issueid = Integer.parseInt(tmp);
			issue = new Issue(issueid);
			tag = null;
			break;
		default:
			break;
		}

	}

	/*
	 * (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
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
				user = DaoFactory.getFactory().getUserDAO().findByID(id);
				issue.setCreatedBy(user);
				break;
			case MODIFYDATE:
				issue.setModifyDate(getDateFromString(valueTag));
				break;
			case MODIFYBY:
				id = Integer.parseInt(valueTag);
				issue.setModifiedBy(DaoFactory.getFactory().getUserDAO().findByID(id));
				break;
			case SUMMARY:
				issue.setSummary(valueTag);
				break;
			case DESCRIPTION:
				issue.setDescription(valueTag);
				break;
			case STATUS:
				id = Integer.parseInt(valueTag);
				issue.setStatus(DaoFactory.getFactory().getStatusDAO().findByID(id));
				break;
			case RESOLUTION:
				id = Integer.parseInt(valueTag);
				issue.setResolution(DaoFactory.getFactory().getResolutionDAO().findByID(id));
				break;
			case PRIORITY:
				id = Integer.parseInt(valueTag);
				issue.setPriority(DaoFactory.getFactory().getPriorityDAO().findByID(id));
				break;
			case TYPE:
				id = Integer.parseInt(valueTag);
				issue.setType(DaoFactory.getFactory().getTypeDAO().findByID(id));
				break;
			case PROJECT:
				id = Integer.parseInt(valueTag);
				issue.setProject(DaoFactory.getFactory().getProjectDAO().findByID(id));
				break;
			case BUILDFOUND:
				id = Integer.parseInt(valueTag);
				issue.setBuild(DaoFactory.getFactory().getBuildDAO().findByID(id));
				break;
			case ASSIGNED:
				id = Integer.parseInt(valueTag);
				issue.setAssigned(DaoFactory.getFactory().getUserDAO().findByID(id));
				break;
			case ISSUE:
				getEntities().put(issue.getId(), issue);
				break;
			default:
			}
			tag = null;
		} catch (DaoException e) {
			throw new SAXException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (tag == null) {
			return;
		}
		valueTag = new String(ch, start, length).trim();
	}

}
