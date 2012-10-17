package org.training.dcharnavoki.issuetracker.beans;

/**
 * The Class CommonBean.
 */
public class CommonBean {
	/** The id. */
	private int id;
	/** The description. */
	private String description;
	/**
	 * Instantiates a new common bean.
	 */
	public CommonBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Instantiates a new common bean.
	 *
	 * @param beanId the id
	 * @param beanDescription the description
	 */
	public CommonBean(int beanId, String beanDescription) {
		super();
		this.id = beanId;
		this.description = beanDescription;
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
	 * Sets the id.
	 *
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + ":[id=" + id + ", description=" + description + "]";
	}
}
