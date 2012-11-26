package org.training.dcharnavoki.issuetracker.beans;

import java.util.Date;

/**
 * The Class Comment.
 */
public class Comment {
	/** The id. */
	private final int id;
	/** The issue id. */
	private int issueId;
	/** The user. */
	private User user;
	/** The date. */
	private Date date;
	/** The comment. */
	private String text;

	/**
	 * Instantiates a new comment.
	 * @param id
	 *            the id
	 */
	public Comment(int id) {
		super();
		this.id = id;
	}

	/**
	 * Gets the issue id.
	 * @return the issueId
	 */
	public int getIssueId() {
		return issueId;
	}

	/**
	 * Sets the issue id.
	 * @param issueId
	 *            the issueId to set
	 */
	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	/**
	 * Gets the user.
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the date.
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Gets the id.
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", for issue=" + issueId + ", user.email=" + user.getEmail()
				+ ", text=" + text + "]\n";
	}

}
