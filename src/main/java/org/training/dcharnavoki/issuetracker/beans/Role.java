package org.training.dcharnavoki.issuetracker.beans;

/**
 * The Enum Role.
 */
public enum Role {
	/** The admin. */
	ADMIN,
	/** The user. */
	USER,
	/** The guest. */
	GUEST;

	/**
	 * Gets the role.
	 *
	 * @param i the i
	 * @return the role
	 */
	public static Role getRole(int i) {
		  return values()[i];
		 }
}
