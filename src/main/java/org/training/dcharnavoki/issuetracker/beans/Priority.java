package org.training.dcharnavoki.issuetracker.beans;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * The Class Priority.
 */
@Entity
public class Priority extends CommonBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 12L;

	/**
	 * Instantiates a new priority.
	 */
	public Priority() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new priority.
	 * @param id
	 *            the id
	 */
	public Priority(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new priority.
	 * @param id
	 *            the id
	 * @param description
	 *            the description
	 */
//	public Priority(int id, String description) {
//		super(id);
//		setDescription(description);
//	}


}
