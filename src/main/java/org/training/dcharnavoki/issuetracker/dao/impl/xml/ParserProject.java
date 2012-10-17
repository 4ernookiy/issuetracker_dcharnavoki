package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.training.dcharnavoki.issuetracker.beans.Build;
import org.training.dcharnavoki.issuetracker.beans.Project;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.dao.IProjectDAO;
import org.training.dcharnavoki.issuetracker.dao.IUserDAO;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO;
import org.training.dcharnavoki.issuetracker.dao.impl.FactoryDAO.Choice;
import org.xml.sax.SAXException;

/**
 * The Class ParserProject.
 */
public class ParserProject extends DefaultParser implements IProjectDAO {
	/** The Constant FILE_XML. */
	private static final String FILE_XML = "/xml/project.xml";

	/** The tag. */
	private Tags tag;

	/** The value tag. */
	private String valueTag;

	/** The project. */
	private Project project;

	/** The build. */
	private Build build;

	/** The id. */
	private int id;

	/** The tmp. */
	private String tmp;

	/** The builds. */
	private List<Build> builds;

	/** The projects. */
	private Map<Integer, Project> projects = new HashMap<Integer, Project>();

	/**
	 * Instantiates a new parser project.
	 */
	public ParserProject() {
		super(FILE_XML);
	}

	/**
	 * The Enum Tags.
	 */
	private static enum Tags {

		/** The project. */
		PROJECT,
		/** The id. */
		ID,
		/** The name. */
		NAME,
		/** The description. */
		DESCRIPTION,
		/** The userid. */
		USERID,
		/** The builds. */
		BUILDS,
		/** The build. */
		BUILD;

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
		case PROJECT:
			tmp = attributes.getValue("id").trim();
			id = Integer.parseInt(tmp);
			project = new Project(id);
			builds = new ArrayList<Build>();

			tag = null;
			break;
		case BUILD:
			tmp = attributes.getValue("id").trim();
			id = Integer.parseInt(tmp);
			build = new Build(id);
			break;
		default:
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
		case NAME:
			project.setName(valueTag);
			break;
		case DESCRIPTION:
			project.setDescription(valueTag);
			break;
		case USERID:
			id = Integer.parseInt(valueTag);
			IUserDAO userDao = (IUserDAO) FactoryDAO
					.getImplementation(Choice.USER);
			User user = userDao.getUser(id);
			project.setManager(user);
			break;
		case BUILDS:
			project.setBuilds(builds);
			break;
		case BUILD:
			build.setName(valueTag);
			builds.add(build);
			break;
		case PROJECT:
			projects.put(project.getId(), project);
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

		switch (tag) {
		case PROJECT:
			break;
		default:
			valueTag = new String(ch, start, length).trim();
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.IProjectDAO#getProject(int)
	 */
	@Override
	public Project getProject(int pId) {
		waitCompete();
		return projects.get(pId);
	}

}
