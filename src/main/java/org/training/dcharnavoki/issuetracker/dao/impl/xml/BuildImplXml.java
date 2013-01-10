package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import java.util.HashMap;
import java.util.Map;

import org.training.dcharnavoki.issuetracker.beans.Build;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.IBuildDAO;
import org.xml.sax.SAXException;

/**
 * The Class BuildImplXml.
 */
public class BuildImplXml extends GenericDAOXml<Build> implements IBuildDAO {

	/** The tag. */
	private TagsWithAttr tag;
	/** The value tag. */
	private String valueTag;
	/** The build. */
	private Build build;
	/**
	 * Instantiates a new builds the impl xml.
	 *
	 * @throws DaoException the dao exception
	 */
	public BuildImplXml() throws DaoException {
		super("/xml/Build.xml", Build.class);
	}

	private static enum TagsWithAttr {
		BUILD, PROJECTID, DESCRIPTION;
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

		if (TagsWithAttr.BUILD == tag) {
			String m = attributes.getValue("id").trim();
			int id = Integer.parseInt(m);
			build = new Build(id);
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
		case PROJECTID:
			int projectId = Integer.parseInt(valueTag);
			build.setProjectId(projectId);
			break;
		case DESCRIPTION:
			build.setDescription(valueTag);
			getEntities().put(build.getId(), build);
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
