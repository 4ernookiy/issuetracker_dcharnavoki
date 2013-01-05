package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Comment;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.dao.ICommentDAO;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp.ConfKeys;

/**
 * The Class CommentImplSql.
 */
public class CommentImplSql extends AbstractBaseDB implements ICommentDAO {
	private static final Logger LOG = Logger.getLogger(CommentImplSql.class);

	/**
	 * Instantiates a new comment impl sql.
	 * @throws DaoException
	 *             the dao exception
	 */
	public CommentImplSql() throws DaoException {
		super();
	}

	private Comment getComment(ResultSet rs) throws DaoException, SQLException {
		int id = rs.getInt("id");
		Comment comment = new Comment(id);
		comment.setIssueId(rs.getInt("issueId"));
		comment.setDate(rs.getDate("date"));
		int userId = rs.getInt("user");
		User user = DaoFactory.getFactory().getUserDAO().getUser(userId);
		comment.setUser(user);
		comment.setText(rs.getString("text"));
		return comment;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.ICommentDAO#getCommentsForIssue
	 * (int)
	 */
	@Override
	public List<Comment> getCommentsForIssue(int issueId) throws DaoException {
		List<Comment> comments = new ArrayList<Comment>();
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn
					.prepareStatement(getConfig().get(ConfKeys.SQL_COMMENT_SELECT_FROM_ID));
			pstmt.setInt(1, issueId);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				comments.add(getComment(resultSet));
			}
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_COMMENT_SELECT_FROM_ID), e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
		return comments;
	}

	@Override
	public void addComment(Comment newComment) throws DaoException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			int id = 0;
			conn = getConnection();
			pstmt = conn.prepareStatement(getConfig().get(ConfKeys.SQL_COMMENT_INSERT_NEW));
			pstmt.setInt(++id, newComment.getIssueId());
			pstmt.setInt(++id, newComment.getUser().getId());
			pstmt.setDate(++id, new java.sql.Date(newComment.getDate().getTime()));
			pstmt.setString(++id, newComment.getText());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_COMMENT_INSERT_NEW), e);
			throw new DaoException(e);
		} finally {
			closeResource(pstmt);
			closeResource(conn);
		}

	}

}
