package org.training.dcharnavoki.issuetracker.dao.impl.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.training.dcharnavoki.issuetracker.beans.Build;
import org.training.dcharnavoki.issuetracker.beans.Project;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.dao.IProjectDAO;
import org.xml.sax.SAXException;

/**
 * The Class ProjectImplXml.
 */
public class ProjectImplXml extends GenericDAOXml<Project> implements IProjectDAO {

	private Tags tag;

	/** The value tag. */
	private String valueTag;

	/** The project. */
	private Project project;

	/**
	 * Instantiates a new project impl xml.
	 * @throws DaoException
	 *             the dao exception
	 */
	public ProjectImplXml() throws DaoException {
		super("/xml/Project.xml", Project.class);
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
		USERID;

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

		if (Tags.PROJECT == tag) {
			String m = attributes.getValue("id").trim();
			int pid = Integer.parseInt(m);
			project = new Project(pid);
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
		case NAME:
			project.setName(valueTag);
			break;
		case DESCRIPTION:
			project.setDescription(valueTag);
			break;
		case USERID:
			try {
				User user;
				int id = Integer.parseInt(valueTag);
				user = DaoFactory.getFactory().getUserDAO().findByID(id);
				project.setManager(user);
			} catch (DaoException e) {
				throw new SAXException(e);
			}
			break;
		case PROJECT:
			project.setBuilds(getBuilds(project.getId()));
			getEntities().put(project.getId(), project);
			break;
		default:
		}
		tag = null;
	}

	private List<Build> getBuilds(int id) throws SAXException {
		List<Build> builds = new ArrayList<Build>();
		List<Build> allBuild;
		try {
			allBuild = DaoFactory.getFactory().getBuildDAO().findAll();
			for (Build build : allBuild) {
				if (build.getProjectId() == id) {
					builds.add(build);
				}
			}
			return builds;
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

	@Override
	public Project findByID(Integer id) throws DaoException {
		Project proj = super.findByID(id);
		try {
			List<Build> builds = getBuilds(proj.getId());
			proj.setBuilds(builds);
		} catch (SAXException e) {
			throw new DaoException(e);
		}
		return proj;
	}

}
