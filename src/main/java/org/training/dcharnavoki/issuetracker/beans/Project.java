package org.training.dcharnavoki.issuetracker.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * The Class Project.
 */
@Entity
public class Project extends CommonBean {
	/** The name. */
	private String name;
	/** The builds. */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "projectId")
	private List<Build> builds;
	/** The manager. */
	@ManyToOne
	@JoinColumn(name = "manager", nullable = false)
	private User manager;

	/**
	 * Instantiates a new project.
	 */
	public Project() {
		super();
	}

	/**
	 * Instantiates a new project.
	 * @param projectId
	 *            the id
	 */
	public Project(int projectId) {
		super(projectId);
	}

	/**
	 * Gets the name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the builds.
	 * @return the builds
	 */
	public List<Build> getBuilds() {
		return builds;
	}

	/**
	 * Sets the builds.
	 * @param builds
	 *            the builds to set
	 */
	public void setBuilds(List<Build> builds) {
		this.builds = builds;
	}

	/**
	 * Gets the manager.
	 * @return the manager
	 */
	public User getManager() {
		return manager;
	}

	/**
	 * Sets the manager.
	 * @param manager
	 *            the manager to set
	 */
	public void setManager(User manager) {
		this.manager = manager;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.beans.CommonBean#toString()
	 */
	@Override
	public String toString() {
		return "Project [" + getId() + ", " + name + ", description=" + getDescription()
				+ ", builds=" + builds + ", manager=" + getManager() + "]";
	}

	/**
	 * Gets the builds the.
	 * @param buildId
	 *            the id
	 * @return the builds the
	 */
	public Build getBuild(int buildId) {
		for (Build build : builds) {
			if (build.getId() == buildId) {
				return build;
			}
		}
		return null;
	}
}
