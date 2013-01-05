package org.training.dcharnavoki.issuetracker.beans;

import javax.persistence.MappedSuperclass;

/**
 * The Class CommonBean.
 */
@MappedSuperclass
public class CommonBean extends Bean {

	/** The description. */
	private String description;

	/**
	 * Instantiates a new common bean.
	 */
	public CommonBean() {
		super();
	}

	/**
	 * Instantiates a new common bean.
	 * @param id
	 *            the id
	 */
	public CommonBean(int id) {
		super(id);
	}

	/**
	 * Gets the description.
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + ":[id=" + getId() + ", description="
				+ description + "]";
	}
}
