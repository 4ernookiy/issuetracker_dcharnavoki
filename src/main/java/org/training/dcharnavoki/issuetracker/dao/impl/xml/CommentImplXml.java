package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.training.dcharnavoki.issuetracker.beans.Comment;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.dao.ICommentDAO;
import org.xml.sax.SAXException;

/**
 * The Class CommentImplXml.
 */
public class CommentImplXml extends GenericDAOXml<Comment> implements ICommentDAO {

	/** The tag. */
	private Tags tag;

	/** The value tag. */
	private String valueTag;
	/** The comment. */
	private Comment comment;

	/** The id. */
	private int id;

	/**
	 * Instantiates a new comment impl xml.
	 * @throws DaoException
	 *             the dao exception
	 */
	public CommentImplXml() throws DaoException {
		super("/xml/Comment.xml", Comment.class);
	}

	/**
	 * The Enum Tags.
	 */
	private static enum Tags {

		/** The comment. */
		COMMENT,
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
			String tmp = attributes.getValue("id").trim();
			int bid = Integer.parseInt(tmp);
			comment = new Comment(bid);
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
	public void endElement(String uri, String localName, String qName) throws SAXException {
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
				comment.setUser(DaoFactory.getFactory().getUserDAO().findByID(id));
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
			getEntities().put(comment.getId(), comment);
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
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (tag == null || Tags.COMMENT == tag) {
			return;
		}
		valueTag = new String(ch, start, length).trim();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.ICommentDAO#getCommentsForIssue
	 * (java.lang.Integer)
	 */
	@Override
	public List<Comment> getCommentsForIssue(Integer issueId) throws DaoException {
		List<Comment> comments = new ArrayList<Comment>();
		List<Comment> all;
		all = DaoFactory.getFactory().getCommentDAO().findAll();
		for (Comment comt : all) {
			if (comt.getIssueId() == issueId) {
				comments.add(comt);
			}
		}
		return comments;
	}

}
