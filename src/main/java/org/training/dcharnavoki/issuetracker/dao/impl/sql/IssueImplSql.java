package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Issue;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.dao.IConfDAO;
import org.training.dcharnavoki.issuetracker.dao.IIssueDAO;
import org.training.dcharnavoki.issuetracker.dao.IProjectDAO;
import org.training.dcharnavoki.issuetracker.dao.IUserDAO;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp.ConfKeys;

/**
 * The Class IssueImplSql.
 */
public class IssueImplSql extends AbstractBaseDB implements IIssueDAO {
	private static final Logger LOG = Logger.getLogger(IssueImplSql.class);
	private DaoFactory factory = DaoFactory.getFactory();

	private ConfigApp config;

	/**
	 * Instantiates a new issue impl sql.
	 * @throws DaoException
	 *             the dao exception
	 */
	public IssueImplSql() throws DaoException {
		super();
		factory = DaoFactory.getFactory();
		config = factory.getConfigAplication();
	}

	private Issue getIssue(ResultSet resultSet) throws DaoException, SQLException {
		IUserDAO userDAO = factory.getUserDAO();
		IProjectDAO projectDAO = factory.getProjectDAO();
		IConfDAO confDAO = factory.getConfDAO();
		int id = 0;
		Issue issue = null;
		issue = new Issue(resultSet.getInt("id"));
		issue.setCreateDate(resultSet.getDate("createDate"));
		id = resultSet.getInt("createdBy");
		issue.setCreatedBy(userDAO.getUser(id));
		issue.setModifyDate(resultSet.getDate("modifyDate"));
		id = resultSet.getInt("modifyBy");
		issue.setModifiedBy(userDAO.getUser(id));
		issue.setSummary(resultSet.getString("summary"));
		issue.setDescription(resultSet.getString("description"));
		id = resultSet.getInt("statusId");
		issue.setStatus(confDAO.getStatus(id));
		id = resultSet.getInt("resolutionId");
		issue.setResolution(confDAO.getResolution(id));
		id = resultSet.getInt("typeId");
		issue.setType(confDAO.getType(id));
		id = resultSet.getInt("priorityId");
		issue.setPriority(confDAO.getPriority(id));
		id = resultSet.getInt("projectId");
		issue.setProject(projectDAO.getProject(id));
		id = resultSet.getInt("buildId");
		issue.setBuild(issue.getProject().getBuild(id));
		id = resultSet.getInt("assignedId");
		issue.setAssigned(userDAO.getUser(id));
		return issue;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IIssueDAO#getIssue(int)
	 */
	@Override
	public Issue getIssue(int id) throws DaoException {
		Issue issue = null;
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(config.get(ConfKeys.SQL_SELECT_ISSUE_FROM_ID));
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				issue = getIssue(resultSet);
			}
			if (resultSet.next()) {
				LOG.error("more than one instance of an object Issue with id:" + id);
			}
		} catch (SQLException e) {
			throw new DaoException(config.get(ConfKeys.SQL_SELECT_ISSUE_FROM_ID), e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
		return issue;
	}

	@Override
	public List<Issue> getAllIssues() throws DaoException {
		Connection conn = null;
		Statement stmt = null;
		List<Issue> issues = new ArrayList<Issue>();
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(config.get(ConfKeys.SQL_SELECT_ALL_ISSUES));
			while (resultSet.next()) {
				issues.add(getIssue(resultSet));
			}
		} catch (SQLException e) {
			LOG.error(e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(stmt);
			closeResource(conn);
		}
		return issues;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.IIssueDAO#getIssuesForUser(org
	 * .training.dcharnavoki.issuetracker.beans.User)
	 */
	@Override
	public List<Issue> getIssuesForUser(User user) throws DaoException {
		List<Issue> issues = new ArrayList<Issue>();
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(config.get(ConfKeys.SQL_SELECT_ISSUE_FROM_ID_ASSIGNED));
			pstmt.setInt(1, user.getId());
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				issues.add(getIssue(resultSet));
			}
		} catch (SQLException e) {
			throw new DaoException(config.get(ConfKeys.SQL_SELECT_ISSUE_FROM_ID_ASSIGNED), e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
		return issues;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.IIssueDAO#getIdForNewIssue()
	 */
	@Override
	public int getIdForNewIssue() throws DaoException {
		// TODO Auto-generated method stub SELECT MAX(id) FROM issues
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.IIssueDAO#addIssue(org.training
	 * .dcharnavoki.issuetracker.beans.Issue)
	 */
	@Override
	public void addIssue(Issue newIssue) throws DaoException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			int id = 0;
			conn = getConnection();
			pstmt = conn.prepareStatement(config.get(ConfKeys.SQL_INSERT_NEW_ISSUE));
			java.sql.Date sqlDate = new java.sql.Date(newIssue.getCreateDate().getTime());
			pstmt.setDate(++id, sqlDate);
			pstmt.setInt(++id, newIssue.getCreatedBy().getId());
			pstmt.setDate(++id, new java.sql.Date(newIssue.getModifyDate().getTime()));
			pstmt.setInt(++id, newIssue.getModifiedBy().getId());
			pstmt.setString(++id, newIssue.getSummary());
			pstmt.setString(++id, newIssue.getDescription());
			pstmt.setInt(++id, newIssue.getStatus().getId());
			pstmt.setInt(++id, newIssue.getResolution() != null ? newIssue.getResolution()
					.getId() : 0);
			pstmt.setInt(++id, newIssue.getPriority().getId());
			pstmt.setInt(++id, newIssue.getType().getId());
			pstmt.setInt(++id, newIssue.getProject().getId());
			pstmt.setInt(++id, newIssue.getBuild().getId());
			pstmt.setInt(++id, newIssue.getAssigned() != null ? newIssue.getAssigned().getId()
					: 0);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(config.get(ConfKeys.SQL_INSERT_NEW_ISSUE), e);
		} finally {
			closeResource(pstmt);
			closeResource(conn);
		}

	}

}
