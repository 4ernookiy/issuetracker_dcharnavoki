package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Build;
import org.training.dcharnavoki.issuetracker.beans.Project;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.IProjectDAO;
import org.training.dcharnavoki.issuetracker.dao.IUserDAO;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp.ConfKeys;

/**
 * The Class ProjectImplSql.
 */
public class ProjectImplSql extends AbstractBaseDB implements IProjectDAO {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(ProjectImplSql.class);

	/**
	 * Instantiates a new project impl sql.
	 * @throws DaoException
	 *             the dao exception
	 */
	public ProjectImplSql() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the builds.
	 * @param id
	 *            the id
	 * @return the builds
	 * @throws DaoException
	 *             the dao exception
	 */
	private List<Build> getBuilds(int id) throws DaoException {
		Build build = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Build> listItems = new ArrayList<Build>();
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(getConfig().get(ConfKeys.SQL_BUILD_SELECT_FROM_ID));
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				build = new Build(resultSet.getInt("id"));
				build.setDescription(resultSet.getString("description"));
				listItems.add(build);
			}
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_BUILD_SELECT_FROM_ID), e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
		return listItems;
	}

	/**
	 * Gets the project.
	 * @param rs
	 *            the rs
	 * @return the project
	 * @throws DaoException
	 *             the dao exception
	 * @throws SQLException
	 *             the sQL exception
	 */
	private Project getProject(ResultSet rs) throws DaoException, SQLException {
		IUserDAO userDAO = getFactory().getUserDAO();
		int id = rs.getInt("id");
		Project project = new Project(id);
		project.setName(rs.getString("name"));
		project.setDescription(rs.getString("description"));
		int userId = rs.getInt("userId");
		project.setManager(userDAO.getUser(userId));
		project.setBuilds(getBuilds(id));
		return project;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.IProjectDAO#getProject(int)
	 */
	@Override
	public Project getProject(int pId) throws DaoException {
		Project project = null;
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn
					.prepareStatement(getConfig().get(ConfKeys.SQL_PROJECT_SELECT_FROM_ID));
			pstmt.setInt(1, pId);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				project = getProject(resultSet);
			}
			if (resultSet.next()) {
				LOG.warn("more than one instance of an object " + getClass().getSimpleName()
						+ " with id:" + pId);
			}
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_PROJECT_SELECT_FROM_ID), e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
		return project;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IProjectDAO#getProjects()
	 */
	@Override
	public List<Project> getProjects() throws DaoException {
		Connection conn = null;
		Statement stmt = null;
		List<Project> listItems = new ArrayList<Project>();
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(getConfig().get(ConfKeys.SQL_PROJECT_SELECT_ALL));
			while (resultSet.next()) {
				listItems.add(getProject(resultSet));
			}
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_PROJECT_SELECT_ALL), e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(stmt);
			closeResource(conn);
		}
		return listItems;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.IProjectDAO#getIdForNewProjects
	 * ()
	 */
	@Override
	public int getIdForNewProjects() throws DaoException {
		// Connection conn = null;
		// Statement stmt = null;
		// ResultSet resultSet = null;
		// int pId = 0;
		// try {
		// conn = getConnection();
		// stmt = conn.createStatement();
		// resultSet =
		// stmt.executeQuery(getConfig().get(ConfKeys.SQL_PROJECT_SELECT_MAX_ID));
		// if (resultSet.next()) {
		// pId = resultSet.getInt("pId");
		// if (pId == 0) {
		// String s = getConfig().get(ConfKeys.SQL_PROJECT_SELECT_MAX_ID)
		// + " finished with id=" + pId;
		// LOG.error(s);
		// throw new DaoException(s);
		// }
		// }
		// } catch (SQLException e) {
		// LOG.error(getConfig().get(ConfKeys.SQL_PROJECT_SELECT_MAX_ID), e);
		// throw new DaoException(e);
		// } finally {
		// closeResource(resultSet);
		// closeResource(stmt);
		// closeResource(conn);
		// }
		// return pId + 1;
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.IProjectDAO#addProject(org.
	 * training.dcharnavoki.issuetracker.beans.Project)
	 */
	@Override
	public void addProject(Project newProject) throws DaoException {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(getConfig().get(ConfKeys.SQL_PROJECT_INSERT_NEW),
					Statement.RETURN_GENERATED_KEYS);
			int id = 0;
			pstmt.setString(++id, newProject.getName());
			pstmt.setString(++id, newProject.getDescription());
			pstmt.setInt(++id, newProject.getManager().getId());
			pstmt.executeUpdate();
			resultSet = pstmt.getGeneratedKeys();
			if (resultSet.next()) {
				int pId = resultSet.getInt(1);
				for (Build b : newProject.getBuilds()) {
					addBuild(b, pId);
				}
			} else {
				String s = getConfig().get("failed to obtain project id");
				LOG.error(s);
				throw new SQLException(s);
			}
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_PROJECT_INSERT_NEW), e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
	}

	/**
	 * Adds the build.
	 * @param newBuild
	 *            the new build
	 * @param projectId
	 *            the project id
	 * @throws DaoException
	 *             the dao exception
	 */
	public void addBuild(Build newBuild, int projectId) throws DaoException {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(getConfig().get(ConfKeys.SQL_BUILD_INSERT_NEW));
			int id = 0;
			pstmt.setInt(++id, projectId);
			pstmt.setString(++id, newBuild.getDescription());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_BUILD_INSERT_NEW), e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
	}

	@Override
	public void updateProject(Project update) throws DaoException {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			int id = 0;
			pstmt = conn.prepareStatement(getConfig().get(ConfKeys.SQL_PROJECT_UPDATE));
			pstmt.setString(++id, update.getName());
			pstmt.setString(++id, update.getDescription());
			pstmt.setInt(++id, update.getManager().getId());
			pstmt.setInt(++id, update.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_PROJECT_UPDATE), e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
	}

}
