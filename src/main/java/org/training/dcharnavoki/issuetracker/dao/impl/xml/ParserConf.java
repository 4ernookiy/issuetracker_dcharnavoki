package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.training.dcharnavoki.issuetracker.beans.Priority;
import org.training.dcharnavoki.issuetracker.beans.Resolution;
import org.training.dcharnavoki.issuetracker.beans.Status;
import org.training.dcharnavoki.issuetracker.beans.Type;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.IConfDAO;
import org.xml.sax.SAXException;

/**
 * The Class ParserConf.
 */
public class ParserConf extends DefaultParser implements IConfDAO {
	/** The Constant FILE_XML. */
	private static final String FILE_XML = "/xml/configuration.xml";
	/** The tag. */
	private TagsWithAttr tag;
	/** The value tag. */
	private String valueTag;
	/** The id. */
	private int id;
	/** The statuses. */
	private Map<Integer, Status> statuses = new HashMap<Integer, Status>();
	/** The resolutions. */
	private Map<Integer, Resolution> resolutions = new HashMap<Integer, Resolution>();
	/** The priorities. */
	private Map<Integer, Priority> priorities = new HashMap<Integer, Priority>();
	/** The types. */
	private Map<Integer, Type> types = new HashMap<Integer, Type>();

	/**
	 * The Enum TagsWithAttr.
	 */
	private static enum TagsWithAttr {
		/** The status. */
		STATUS,
		/** The resolution. */
		RESOLUTION,
		/** The priority. */
		PRIORITY,
		/** The type. */
		TYPE;
		/** The Constant stringToEnum. */
		private static final Map<String, TagsWithAttr> TO_ENUM = new HashMap<String, TagsWithAttr>();
		static {
			for (TagsWithAttr op : values()) {
				TO_ENUM.put(op.toString(), op);
			}
		}

		/**
		 * From string.
		 * @param string
		 *           the string
		 * @return the tags with attr
		 */
		public static TagsWithAttr fromString(String string) {
			return TO_ENUM.get(string.toUpperCase());
		}
	}

	/**
	 * Instantiates a new parser conf.
	 * @throws DaoException
	 *            the dao exception
	 */
	public ParserConf() throws DaoException {
		super(FILE_XML);
	}

	/*
	 * (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			org.xml.sax.Attributes attributes) throws SAXException {
		tag = TagsWithAttr.fromString(qName);
		if (tag == null) {
			return;
		}
		String m = attributes.getValue("id").trim();
		id = Integer.parseInt(m);
	}

	/*
	 * (non-Javadoc)
	 * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if (tag == null) {
			return;
		}

		switch (tag) {
		case STATUS:
			statuses.put(id, new Status(id, valueTag));
			break;
		case PRIORITY:
			priorities.put(id, new Priority(id, valueTag));
			break;
		case RESOLUTION:
			resolutions.put(id, new Resolution(id, valueTag));
			break;
		case TYPE:
			types.put(id, new Type(id, valueTag));
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
		if (tag == null) {
			return;
		}
		valueTag = new String(ch, start, length).trim();
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getStatus(int)
	 */
	@Override
	public Status getStatus(int sId) throws DaoException {
		waitCompete();
		return statuses.get(sId);
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getPriority(int)
	 */
	@Override
	public Priority getPriority(int pId) throws DaoException {
		waitCompete();
		return priorities.get(pId);
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getResolution(int)
	 */
	@Override
	public Resolution getResolution(int rId) throws DaoException {
		waitCompete();
		// if (resolutions.get(rId) != null) {
		// }
		// throw new DaoException("getResolution fail - null");
		return resolutions.get(rId);
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getType(int)
	 */
	@Override
	public Type getType(int tId) throws DaoException {
		waitCompete();
		return types.get(tId);
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getStatuses()
	 */
	@Override
	public List<Status> getStatuses() throws DaoException {
		waitCompete();
		return new ArrayList<Status>(statuses.values());
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getPriorities()
	 */
	@Override
	public List<Priority> getPriorities() throws DaoException {
		waitCompete();
		return new ArrayList<Priority>(priorities.values());
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getResolutions()
	 */
	@Override
	public List<Resolution> getResolutions() throws DaoException {
		waitCompete();
		return new ArrayList<Resolution>(resolutions.values());
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getTypes()
	 */
	@Override
	public List<Type> getTypes() throws DaoException {
		waitCompete();
		return new ArrayList<Type>(types.values());
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#addResolution(org.
	 * training.dcharnavoki.issuetracker.beans.Resolution)
	 */
	@Override
	public void addResolution(Resolution newResolution) throws DaoException {
		waitCompete();
		resolutions.put(newResolution.getId(), newResolution);
	}

	@Override
	public void updateResolution(Resolution update) throws DaoException {
		waitCompete();
		resolutions.get(update.getId()).setDescription(update.getDescription());
	}

	@Override
	public void updatePriority(Priority update) throws DaoException {
		waitCompete();
		priorities.get(update.getId()).setDescription(update.getDescription());
	}

	@Override
	public void addPriority(Priority newItem) throws DaoException {
		waitCompete();
		priorities.put(newItem.getId(), newItem);
	}

	@Override
	public void addType(Type newItem) throws DaoException {
		waitCompete();
		types.put(newItem.getId(), newItem);
	}

	@Override
	public void updateType(Type update) throws DaoException {
		waitCompete();
		types.get(update.getId()).setDescription(update.getDescription());
	}

	@Override
	public void updateType(Status update) throws DaoException {
		waitCompete();
		statuses.get(update.getId()).setDescription(update.getDescription());
	}

}
