package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Issue;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.dao.IIssueDAO;
import org.training.dcharnavoki.issuetracker.dao.IUserDAO;

/**
 * The Class IssueImplSql.
 */
public class IssueImplSql extends GenericDAOSql<Issue> implements IIssueDAO {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(IssueImplSql.class);

	/**
	 * Instantiates a new issue impl sql.
	 *
	 * @throws DaoException the dao exception
	 */
	public IssueImplSql() throws DaoException {
		super(Issue.class);
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IIssueDAO#getIssuesForUser(org.training.dcharnavoki.issuetracker.beans.User)
	 */
	@Override
	public List<Issue> getIssuesForUser(User user) throws DaoException {
		List<Issue> issues = new ArrayList<Issue>();
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM Issue WHERE assigned = ?;";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, user.getId());
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				issues.add(getEntity(resultSet));
			}
		} catch (SQLException e) {
			LOG.error(query, e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
		return issues;
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getInstance()
	 */
	@Override
	public Issue getInstance() {
		return new Issue();
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#saveEntity(java.sql.PreparedStatement, java.lang.Object)
	 */
	@Override
	protected PreparedStatement saveEntity(PreparedStatement pstm, Issue entity)
			throws SQLException {
		int id = 0;
		pstm.setDate(++id, new java.sql.Date(entity.getCreateDate().getTime()));
		pstm.setDate(++id, new java.sql.Date(entity.getModifyDate().getTime()));
		pstm.setInt(++id, entity.getCreatedBy().getId());
		pstm.setInt(++id, entity.getModifiedBy().getId());
		pstm.setString(++id, entity.getSummary());
		pstm.setString(++id, entity.getDescription());
		pstm.setInt(++id, entity.getStatus().getId());
		pstm.setInt(++id, entity.getResolution() != null ? entity.getResolution().getId() : 0);
		pstm.setInt(++id, entity.getType().getId());
		pstm.setInt(++id, entity.getPriority().getId());
		pstm.setInt(++id, entity.getProject().getId());
		pstm.setInt(++id, entity.getBuild().getId());
		pstm.setInt(++id, entity.getAssigned() != null ? entity.getAssigned().getId() : 0);
		return pstm;
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#updateEntity(java.sql.PreparedStatement, java.lang.Object)
	 */
	@Override
	protected PreparedStatement updateEntity(PreparedStatement pstm, Issue entity)
			throws SQLException {
		int id = 0;
		pstm.setDate(++id,  new java.sql.Date(entity.getModifyDate().getTime()));
		pstm.setInt(++id, entity.getModifiedBy().getId());
		pstm.setString(++id, entity.getSummary());
		pstm.setString(++id, entity.getDescription());
		pstm.setInt(++id, entity.getStatus().getId());
		pstm.setInt(++id, entity.getResolution() != null ? entity.getResolution().getId() : 0);
		pstm.setInt(++id, entity.getPriority().getId());
		pstm.setInt(++id, entity.getType().getId());
		pstm.setInt(++id, entity.getProject().getId());
		pstm.setInt(++id, entity.getBuild().getId());
		pstm.setInt(++id, entity.getAssigned() != null ? entity.getAssigned().getId() : 0);
		pstm.setInt(++id, entity.getId());
		return pstm;
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getEntity(java.sql.ResultSet)
	 */
	@Override
	protected Issue getEntity(ResultSet resultSet) throws SQLException {
		try {
			DaoFactory factory = DaoFactory.getFactory();
			IUserDAO userDAO = factory.getUserDAO();
			int id = 0;
			Issue issue = getInstance();
			int issueId = resultSet.getInt("id");
			issue.setId(issueId);
			issue.setCreateDate(resultSet.getDate("createDate"));
			id = resultSet.getInt("createdBy");
			issue.setCreatedBy(userDAO.findByID(id));
			issue.setModifyDate(resultSet.getDate("modifyDate"));
			id = resultSet.getInt("modifiedBy");
			issue.setModifiedBy(userDAO.findByID(id));
			issue.setSummary(resultSet.getString("summary"));
			issue.setDescription(resultSet.getString("description"));
			id = resultSet.getInt("status");
			issue.setStatus(factory.getStatusDAO().findByID(id));
			id = resultSet.getInt("resolution");
			issue.setResolution(factory.getResolutionDAO().findByID(id));
			id = resultSet.getInt("type");
			issue.setType(factory.getTypeDAO().findByID(id));
			id = resultSet.getInt("priority");
			issue.setPriority(factory.getPriorityDAO().findByID(id));
			id = resultSet.getInt("project");
			issue.setProject(factory.getProjectDAO().findByID(id));
			id = resultSet.getInt("build");
			issue.setBuild(factory.getBuildDAO().findByID(id));
			id = resultSet.getInt("assigned");
			issue.setAssigned(userDAO.findByID(id));
			return issue;
		} catch (DaoException e) {
			throw new SQLException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getSqlInsert()
	 */
	@Override
	public String getSqlInsert() throws DaoException {
		return "INSERT INTO " + getKlass().getSimpleName()
				+ " VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getSqlUpdate()
	 */
	@Override
	public String getSqlUpdate() throws DaoException {
		return "UPDATE "
				+ getKlass().getSimpleName()
				+ " SET modifyDate = ?, modifiedBy = ?, summary = ?, description = ?, status = ?, resolution = ?, "
				+ "priority = ?, type = ?, project = ?, build = ?, assigned = ? WHERE id = ?;";
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getSqlDelete()
	 */
	@Override
	public String getSqlDelete() throws DaoException {
		return null;
	}

}
