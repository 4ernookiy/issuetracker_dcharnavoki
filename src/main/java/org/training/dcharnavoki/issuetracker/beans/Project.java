package org.training.dcharnavoki.issuetracker.beans;

import java.util.List;

/**
 * The Class Project.
 */
public class Project {
	/** The id. */
	private final int id;
	/** The name. */
	private String name;
	/** The description. */
	private String description;
	/** The builds. */
	private List<Build> builds;
	/** The manager. */
	private User manager;

	/**
	 * Instantiates a new project.
	 *
	 * @param id the id
	 */
	public Project(int id) {
		super();
		this.id = id;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the name.
	 *
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Sets the description.
	 *
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the builds.
	 *
	 * @return the builds
	 */
	public List<Build> getBuilds() {
		return builds;
	}

	/**
	 * Sets the builds.
	 *
	 * @param builds the builds to set
	 */
	public void setBuilds(List<Build> builds) {
		this.builds = builds;
	}

	/**
	 * Gets the manager.
	 *
	 * @return the manager
	 */
	public User getManager() {
		return manager;
	}
	/**
	 * Sets the manager.
	 *
	 * @param manager the manager to set
	 */
	public void setManager(User manager) {
		this.manager = manager;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Project [" + id + ", " + name + ", description="
				+ description + ", builds=" + builds + ", manager=" + manager
				+ "]";
	}
	/**
	 * Gets the builds the.
	 *
	 * @param id the id
	 * @return the builds the
	 */
	public Build getBuild(int id) {
		return builds.get(id);
	}
}
