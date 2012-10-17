package org.training.dcharnavoki.issuetracker.beans;
/**
 * The Class Build.
 */
public class Build {
	/** The id. */
	private final int id;
	/** The name. */
	private String name;
	/**
	 * Instantiates a new builds the.
	 *
	 * @param buildId the id
	 */
	public Build(final int buildId) {
		super();
		this.id = buildId;
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Build [id=" + id + ", name=" + name + "]";
	}

}
