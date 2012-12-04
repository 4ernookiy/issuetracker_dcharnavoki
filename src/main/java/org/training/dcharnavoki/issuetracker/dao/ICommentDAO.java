package org.training.dcharnavoki.issuetracker.dao;

import java.util.List;

import org.training.dcharnavoki.issuetracker.beans.Comment;

/**
 * The Interface ICommentDAO.
 */
public interface ICommentDAO {
	/**
	 * Gets the comments for issue.
	 *
	 * @param issueId the issue id
	 * @return the comments for issue
	 * @throws DaoException the dao exception
	 */
	List<Comment> getCommentsForIssue(int issueId) throws DaoException;

	/**
	 * Adds the comment.
	 *
	 * @param newComment the comment
	 * @throws DaoException the dao exception
	 */
	void addComment(Comment newComment) throws DaoException;
}
