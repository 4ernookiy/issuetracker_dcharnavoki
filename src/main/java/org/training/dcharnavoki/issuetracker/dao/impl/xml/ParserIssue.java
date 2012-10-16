package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.training.dcharnavoki.issuetracker.beans.Issue;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.dao.IConfDAO;
import org.training.dcharnavoki.issuetracker.dao.IIssueDAO;
import org.training.dcharnavoki.issuetracker.dao.IProjectDAO;
import org.training.dcharnavoki.issuetracker.dao.IUserDAO;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO.Choice;
import org.xml.sax.SAXException;

/**
 * The Class ParserIssue.
 */
public class ParserIssue extends DefaultParser implements IIssueDAO {
	/** The Constant FILE_XML. */
	private static final String FILE_XML = "/xml/base.xml";
	/** The user dao. */
	private final IUserDAO userDao = (IUserDAO) FactoryDAO
			.getImplementation(Choice.USER);
	/** The conf dao. */
	private final IConfDAO confDao = (IConfDAO) FactoryDAO
			.getImplementation(Choice.CONFIG);
	/** The project dao. */
	private final IProjectDAO projectDao = (IProjectDAO) FactoryDAO
			.getImplementation(Choice.PROJECT);
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
	 */
	public ParserIssue() {
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
			User user = userDao.getUser(id);
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
			issue.getProject();
			break;
		case ASSIGNED:
			int id = Integer.parseInt(valueTag);
			issue.setassigned(userDao.getUser(id));
			break;
		case ISSUE:
			issues.put(issue.getId(), issue);
			break;
		default:
		}
		tag = null;
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
	public Issue getIssue(int id) {
		waitCompete();
		return issues.get(id);
	}

	/**
	 * Gets the date from string.
	 *
	 * @param dateStr
	 *            the date str
	 * @return the date from string
	 */
	java.util.Date getDateFromString(String dateStr) {
		Calendar calendar = DatatypeConverter.parseDateTime(dateStr);
		return calendar.getTime();
	}

}
