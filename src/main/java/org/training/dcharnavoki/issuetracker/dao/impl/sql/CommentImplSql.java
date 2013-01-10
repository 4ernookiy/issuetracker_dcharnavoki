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

/**
 * The Class CommentImplSql.
 */
public class CommentImplSql extends GenericDAOSql<Comment> implements ICommentDAO {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(CommentImplSql.class);

	/**
	 * Instantiates a new comment impl sql.
	 *
	 * @throws DaoException the dao exception
	 */
	public CommentImplSql() throws DaoException {
		super(Comment.class);
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getInstance()
	 */
	@Override
	public Comment getInstance() {
		return new Comment();
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#saveEntity(java.sql.PreparedStatement, java.lang.Object)
	 */
	@Override
	protected PreparedStatement saveEntity(PreparedStatement pstm, Comment entity)
			throws SQLException {
		int id = 0;
		pstm.setInt(++id, entity.getIssueId());
		pstm.setInt(++id, entity.getUser().getId());
		pstm.setDate(++id, new java.sql.Date(entity.getDate().getTime()));
		pstm.setString(++id, entity.getText());
		return pstm;
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#updateEntity(java.sql.PreparedStatement, java.lang.Object)
	 */
	@Override
	protected PreparedStatement updateEntity(PreparedStatement pstm, Comment entity)
			throws SQLException {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getEntity(java.sql.ResultSet)
	 */
	@Override
	protected Comment getEntity(ResultSet resultSet) throws SQLException {
		try {
			Comment comment = getInstance();
			int id = resultSet.getInt("id");
			comment.setId(id);
			comment.setIssueId(resultSet.getInt("issueId"));
			comment.setDate(resultSet.getDate("date"));
			int userId = resultSet.getInt("user");
			User user;
			user = DaoFactory.getFactory().getUserDAO().findByID(userId);
			comment.setUser(user);
			comment.setText(resultSet.getString("text"));
			return comment;
		} catch (DaoException e) {
			throw new SQLException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getSqlInsert()
	 */
	@Override
	public String getSqlInsert() throws DaoException {
		return "INSERT INTO " + getKlass().getSimpleName() + " VALUES (NULL, ?, ?, ?, ?);";
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getSqlUpdate()
	 */
	@Override
	public String getSqlUpdate() throws DaoException {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getSqlDelete()
	 */
	@Override
	public String getSqlDelete() throws DaoException {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.ICommentDAO#getCommentsForIssue(java.lang.Integer)
	 */
	@Override
	public List<Comment> getCommentsForIssue(Integer id) throws DaoException {
		List<Comment> comments = new ArrayList<Comment>();
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM comment WHERE issueId =?;";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				comments.add(getEntity(resultSet));
			}
		} catch (SQLException e) {
			LOG.error(query, e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
		return comments;
	}

}
