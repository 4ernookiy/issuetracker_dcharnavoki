package org.training.dcharnavoki.issuetracker.beans;

import javax.persistence.Entity;

/**
 * The Class Build.
 */
@Entity
public class Build extends CommonBean {

	/** The project id. */
	private int projectId;

	/**
	 * Instantiates a new builds the.
	 */
	public Build() {
		super();
	}

	/**
	 * Instantiates a new builds the.
	 * @param beanId
	 *            the bean id
	 */
	public Build(int beanId) {
		super(beanId);
	}

	/**
	 * Gets the project id.
	 * @return the project id
	 */
	public int getProjectId() {
		return projectId;
	}

	/**
	 * Sets the project id.
	 * @param projectId
	 *            the new project id
	 */
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return super.toString() + "projectId=" + projectId + "]";
	}

}
