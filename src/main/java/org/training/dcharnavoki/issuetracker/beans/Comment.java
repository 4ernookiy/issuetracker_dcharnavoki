package org.training.dcharnavoki.issuetracker.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The Class Comment.
 */
@Entity
public class Comment extends Bean {
	/** The issue id. */
	private int issueId;
	/** The user. */
	@ManyToOne
	@JoinColumn(name = "user", nullable = false)
	private User user;
	/** The date. */
	private Date date;
	/** The comment. */
	private String text;

	/**
	 * Instantiates a new comment.
	 */
	public Comment() {
		super();
	}

	/**
	 * Instantiates a new comment.
	 * @param id
	 *            the id
	 */
	public Comment(int id) {
		super(id);
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
	 * Gets the text.
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text.
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Comment [id=" + getId() + ", date:" + getDate() + ", for issue=" + issueId
				+ ", user.email=" + user.getEmail() + ", text=" + text + "]\n";
	}

}
