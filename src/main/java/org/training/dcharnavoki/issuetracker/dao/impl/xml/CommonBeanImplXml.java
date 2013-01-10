package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import java.util.HashMap;
import java.util.Map;

import org.training.dcharnavoki.issuetracker.beans.CommonBean;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.GenericDAO;
import org.xml.sax.SAXException;

/**
 * The Class CommonBeanImplXml.
 * @param <T>
 *            the generic type
 */
public abstract class CommonBeanImplXml<T extends CommonBean> extends GenericDAOXml<T>
		implements GenericDAO<T, Integer> {

	/** The tag. */
	private TagsWithAttr tag;
	/** The value tag. */
	private String valueTag;

	private T t;

	/**
	 * Instantiates a new common bean impl xml.
	 * @param filename
	 *            the filename
	 * @param klass
	 *            the klass
	 * @throws DaoException
	 *             the dao exception
	 */
	public CommonBeanImplXml(String filename, Class<T> klass) throws DaoException {
		super(filename, klass);
	}

	/**
	 * Gets the single instance of GenericDAOXml.
	 * @param id
	 *            the id
	 * @return single instance of GenericDAOXml
	 */
	public abstract T getInstance(int id);

	private static enum TagsWithAttr {
		BEAN, DESCRIPTION;
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
		 *            the string
		 * @return the tags with attr
		 */
		public static TagsWithAttr fromString(String string) {
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
		tag = TagsWithAttr.fromString(qName);
		if (tag == null) {
			return;
		}

		if (TagsWithAttr.BEAN == tag) {
			String m = attributes.getValue("id").trim();
			int id = Integer.parseInt(m);
			t = getInstance(id);
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
		if (tag == null) {
			return;
		}

		switch (tag) {
		case DESCRIPTION:
			t.setDescription(valueTag);
			getEntities().put(t.getId(), t);
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

}
