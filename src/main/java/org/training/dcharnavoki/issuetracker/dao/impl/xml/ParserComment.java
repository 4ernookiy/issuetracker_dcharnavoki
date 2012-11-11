package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.training.dcharnavoki.issuetracker.beans.Comment;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.dao.ICommentDAO;
import org.training.dcharnavoki.issuetracker.dao.IUserDAO;
import org.xml.sax.SAXException;

/**
 * The Class ParserComment.
 */
public class ParserComment extends DefaultParser implements ICommentDAO {

	/** The Constant FILE_XML. */
	private static final String FILE_XML = "/xml/comment.xml";

	/** The user dao. */
	private final IUserDAO userDao = DaoFactory.getFactory().getUserDAO();

	/** The tag. */
	private Tags tag;

	/** The value tag. */
	private String valueTag;

	/** The comment. */
	private Comment comment;

	/** The id. */
	private int id;

	/** The tmp. */
	private String tmp;

	/** The map bean. */
	private Map<Integer, Comment> mapBean = new HashMap<Integer, Comment>();

	/**
	 * Instantiates a new parser comment.
	 * @throws DaoException
	 *             the dao exception
	 */
	public ParserComment() throws DaoException {
		super(FILE_XML);
	}

	/**
	 * The Enum Tags.
	 */
	private static enum Tags {

		/** The comment. */
		COMMENT,
		/** The id. */
		ID,
		/** The issueid. */
		ISSUEID,
		/** The addedby. */
		ADDEDBY,
		/** The adddate. */
		ADDDATE,

		/** The text. */
		TEXT;
		/** The Constant TO_ENUM. */
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
	 * @see org.xml.sax.helpers.DefaultHandler#startElement
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			org.xml.sax.Attributes attributes) throws SAXException {
		tag = Tags.fromString(qName);
		if (tag == null) {
			return;
		}

		switch (tag) {
		case COMMENT:
			tmp = attributes.getValue("id").trim();
			id = Integer.parseInt(tmp);
			comment = new Comment(id);
			tag = null;
			break;
		default:
		}

	}

	/*
	 * (non-Javadoc)
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
		case ISSUEID:
			id = Integer.parseInt(valueTag);
			comment.setIssueId(id);
			break;
		case ADDEDBY:
			id = Integer.parseInt(valueTag);
			try {
				comment.setUser(userDao.getUser(id));
			} catch (DaoException e) {
				throw new SAXException(e);
			}
			break;
		case ADDDATE:
			comment.setDate(getDateFromString(valueTag));
			break;
		case TEXT:
			comment.setText(valueTag);
			break;
		case COMMENT:
			mapBean.put(comment.getId(), comment);
			comment = null;
			break;
		default:
		}
		tag = null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (tag == null) {
			return;
		}

		switch (tag) {
		case COMMENT:
			break;
		default:
			valueTag = new String(ch, start, length).trim();
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.ICommentDAO#getCommentsForIssue
	 * (int)
	 */
	@Override
	public List<Comment> getCommentsForIssue(int issueId) throws DaoException {
		waitCompete();
		List<Comment> lists = new ArrayList<Comment>();
		Iterator<Comment> iter = mapBean.values().iterator();
		Comment commentTmp;
		while (iter.hasNext()) {
			commentTmp = iter.next();
			if (commentTmp.getIssueId() == issueId) {
				lists.add(commentTmp);
			}
		}
		return lists;
	}

}
