package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.training.dcharnavoki.issuetracker.beans.Comment;
import org.training.dcharnavoki.issuetracker.dao.ICommentDAO;
import org.training.dcharnavoki.issuetracker.dao.IUserDAO;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO.Choice;
import org.xml.sax.SAXException;

/**
 * The Class ParserComment.
 */
public class ParserComment extends DefaultParser implements ICommentDAO {

	/** The Constant FILE_XML. */
	private static final String FILE_XML = "/xml/comment.xml";
	private final IUserDAO userDao = (IUserDAO) FactoryDAO
			.getImplementation(Choice.USER);


	private Tags tag;

	private String valueTag;

	private Comment comment;

	/** The id. */
	private int id;

	private String tmp;


	private Map<Integer, Comment> mapBean = new HashMap<Integer, Comment>();

	/**
	 * Instantiates a new parser comment.
	 */
	public ParserComment() {
		super(FILE_XML);
	}

	/**
	 * The Enum Tags.
	 */
	private static enum Tags {

		COMMENT,
		/** The id. */
		ID,
		ISSUEID,
		ADDEDBY,
		ADDDATE,
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
		 *
		 * @param string the string
		 * @return the tags
		 */
		public static Tags fromString(String string) {
			return TO_ENUM.get(string.toUpperCase());
		}
	}

	/* (non-Javadoc)
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

	/* (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
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
			comment.setUser(userDao.getUser(id));
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

	/* (non-Javadoc)
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

	@Override
	public List<Comment> getCommentsForIssue(int issueId) {
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
