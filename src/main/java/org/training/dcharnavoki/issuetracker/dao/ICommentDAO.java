package org.training.dcharnavoki.issuetracker.dao;

import java.util.List;

import org.training.dcharnavoki.issuetracker.beans.Comment;

/**
 * The Interface ICommentDAO.
 */
public interface ICommentDAO extends GenericDAO<Comment, Integer> {

	/**
	 * Gets the comments for issue.
	 *
	 * @param id the id
	 * @return the comments for issue
	 * @throws DaoException the dao exception
	 */
	List<Comment> getCommentsForIssue(Integer id) throws DaoException;

}
