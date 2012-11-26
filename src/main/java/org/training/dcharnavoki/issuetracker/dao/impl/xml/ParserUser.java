package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.training.dcharnavoki.issuetracker.beans.Role;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.IUserDAO;
import org.xml.sax.SAXException;

/**
 * The Class ParserUser.
 */
public class ParserUser extends DefaultParser implements IUserDAO {

	/** The Constant FILE_XML. */
	private static final String FILE_XML = "/xml/user.xml";

	/** The tag. */
	private Tags tag;

	/** The value tag. */
	private String valueTag;

	/** The user. */
	private User user;

	/** The users. */
	private Map<Integer, User> users = new HashMap<Integer, User>();

	/**
	 * Instantiates a new parser user.
	 * @throws DaoException
	 *             the dao exception
	 */
	public ParserUser() throws DaoException {
		super(FILE_XML);
	}

	/**
	 * The Enum Tags.
	 */
	private static enum Tags {

		/** The user. */
		USER,
		/** The firstname. */
		FIRSTNAME,
		/** The lastname. */
		LASTNAME,
		/** The email. */
		EMAIL,
		/** The role. */
		ROLE,
		/** The password. */
		PASSWORD;

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
		case USER:
			String m = attributes.getValue("id").trim();
			int id = Integer.parseInt(m);
			user = new User(id);
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
		case FIRSTNAME:
			user.setFirstName(valueTag);
			break;
		case LASTNAME:
			user.setLastName(valueTag);
			break;
		case EMAIL:
			user.setEmail(valueTag);
			break;
		case ROLE:
			int id = Integer.parseInt(valueTag);
			user.setRole(Role.getRole(id));
			break;
		case PASSWORD:
			user.setPassword(valueTag);
			break;
		case USER:
			users.put(user.getId(), user);
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
		case USER:
			break;
		default:
			valueTag = new String(ch, start, length).trim();
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IUserDAO#getUser(int)
	 */
	@Override
	public User getUser(int uId) throws DaoException {
		waitCompete();
		return users.get(uId);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.IUserDAO#getUser(java.lang.
	 * String)
	 */
	@Override
	public User getUser(String email) throws DaoException {
		waitCompete();
		List<User> usersList = new ArrayList<User>(users.values());
		for (User userTmp : usersList) {
			if (userTmp.getEmail().equals(email)) {
				return userTmp;
			}
		}
		return null;
	}

	@Override
	public int getIdForNewUser() throws DaoException {
		// TODO Auto-generated method stub
		waitCompete();
		int maxId = 0;
		List<User> usersList = new ArrayList<User>(users.values());
		for (User userTmp : usersList) {
			if (userTmp.getId() > maxId) {
				maxId = userTmp.getId();
			}
		}
		return maxId + 1;
	}

	@Override
	public void addUser(User newUser) throws DaoException {
		users.put(newUser.getId(), newUser);
	}

	@Override
	public List<User> getAllUsers() throws DaoException {
		waitCompete();
		return new ArrayList<User>(users.values());
	}

}
