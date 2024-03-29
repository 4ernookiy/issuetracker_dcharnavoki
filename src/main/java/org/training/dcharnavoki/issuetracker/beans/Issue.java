package org.training.dcharnavoki.issuetracker.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The Class Issue.
 */
@Entity
public class Issue extends CommonBean {
	/** The create date. */
	private Date createDate;
	/** The modify date. */
	private Date modifyDate;
	/** The created by. */
	@ManyToOne
	@JoinColumn(name = "createdBy", nullable = false)
	private User createdBy;
	/** The modified by. */
	@ManyToOne
	@JoinColumn(name = "modifiedBy", nullable = false)
	private User modifiedBy;
	/** The summary. */
	private String summary;
	/** The status. */
	@ManyToOne
	@JoinColumn(name = "status", nullable = false)
	private Status status;
	/** The resolution. */
	@ManyToOne
	@JoinColumn(name = "resolution", nullable = true)
	private Resolution resolution;
	/** The type. */
	@ManyToOne
	@JoinColumn(name = "type", nullable = false)
	private Type type;
	/** The priority. */
	@ManyToOne
	@JoinColumn(name = "priority", nullable = false)
	private Priority priority;
	/** The project. */
	@ManyToOne
	@JoinColumn(name = "project", nullable = false)
	private Project project;
	/** The build. */
	@ManyToOne
	@JoinColumn(name = "build", nullable = false)
	private Build build;
	/** The assigned. */
	@ManyToOne
	@JoinColumn(name = "assigned", nullable = true)
	private User assigned;

	/**
	 * Instantiates a new issue.
	 */
	public Issue() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new issue.
	 * @param issueId
	 *            the id
	 */
	public Issue(int issueId) {
		super(issueId);
	}

	/**
	 * Gets the creates the date.
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the creates the date.
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Gets the modify date.
	 * @return the modifyDate
	 */
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * Sets the modify date.
	 * @param modifyDate
	 *            the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * Gets the created by.
	 * @return the createdBy
	 */
	public User getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the modified by.
	 * @return the modifiedBy
	 */
	public User getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Sets the modified by.
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Gets the summary.
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Sets the summary.
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * Gets the status.
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * Gets the resolution.
	 * @return the resolution
	 */
	public Resolution getResolution() {
		return resolution;
	}

	/**
	 * Sets the resolution.
	 * @param resolution
	 *            the resolution to set
	 */
	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}

	/**
	 * Gets the type.
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * @param type
	 *            the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Gets the priority.
	 * @return the priority
	 */
	public Priority getPriority() {
		return priority;
	}

	/**
	 * Sets the priority.
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	/**
	 * Gets the project.
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Sets the project.
	 * @param project
	 *            the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * Gets the builds the.
	 * @return the build
	 */
	public Build getBuild() {
		return build;
	}

	/**
	 * Sets the builds the.
	 * @param build
	 *            the build to set
	 */
	public void setBuild(Build build) {
		this.build = build;
	}

	/**
	 * Gets the assigned.
	 * @return the assigned
	 */
	public User getAssigned() {
		return assigned;
	}

	/**
	 * Sets the assigned.
	 * @param assigned
	 *            the assigned to set
	 */
	public void setAssigned(User assigned) {
		this.assigned = assigned;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Issue [id=" + getId() + ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", summary=" + summary + ", description=" + getDescription() + ", status="
				+ status + ", resolution=" + resolution + ", type=" + type + ", priority="
				+ priority + ", project=" + project + ", build=" + build + ", assigned="
				+ assigned + "]";
	}

}
