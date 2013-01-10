package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Build;
import org.training.dcharnavoki.issuetracker.beans.Project;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.DaoFactory;
import org.training.dcharnavoki.issuetracker.dao.IProjectDAO;

/**
 * The Class ProjectImplSql.
 */
public class ProjectImplSql extends GenericDAOSql<Project> implements IProjectDAO {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(ProjectImplSql.class);

	/**
	 * Instantiates a new project impl sql.
	 *
	 * @throws DaoException the dao exception
	 */
	public ProjectImplSql() throws DaoException {
		super(Project.class);
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getInstance()
	 */
	@Override
	public Project getInstance() {
		return new Project();
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#saveEntity(java.sql.PreparedStatement, java.lang.Object)
	 */
	@Override
	protected PreparedStatement saveEntity(PreparedStatement pstm, Project entity)
			throws SQLException {
		int id = 0;
		pstm.setString(++id, entity.getName());
		pstm.setString(++id, entity.getDescription());
		pstm.setInt(++id, entity.getManager().getId());
		return pstm;
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#updateEntity(java.sql.PreparedStatement, java.lang.Object)
	 */
	@Override
	protected PreparedStatement updateEntity(PreparedStatement pstm, Project entity)
			throws SQLException {
		int id = 0;
		pstm.setString(++id, entity.getName());
		pstm.setString(++id, entity.getDescription());
		pstm.setInt(++id, entity.getManager().getId());
		pstm.setInt(++id, entity.getId());
		return pstm;
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getEntity(java.sql.ResultSet)
	 */
	@Override
	protected Project getEntity(ResultSet resultSet) throws SQLException {
		try {
			int id = resultSet.getInt("id");
			Project project = getInstance();
			project.setId(id);
			project.setName(resultSet.getString("name"));
			project.setDescription(resultSet.getString("description"));
			int userId = resultSet.getInt("manager");
			project.setManager(DaoFactory.getFactory().getUserDAO().findByID(userId));
			project.setBuilds(getBuldsByProject(id));
			return project;
		} catch (DaoException e) {
			throw new SQLException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getSqlInsert()
	 */
	@Override
	public String getSqlInsert() throws DaoException {
		return "INSERT INTO " + getKlass().getSimpleName() + " VALUES (NULL, ?, ?, ?);";
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getSqlUpdate()
	 */
	@Override
	public String getSqlUpdate() throws DaoException {
		return "UPDATE "
				+ getKlass().getSimpleName()
				+ " SET name = ?, description= ?, manager= ? WHERE id = ?;";
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getSqlDelete()
	 */
	@Override
	public String getSqlDelete() throws DaoException {
		return null;
	}

	/**
	 * Gets the bulds by project.
	 *
	 * @param projectId the project id
	 * @return the bulds by project
	 * @throws DaoException the dao exception
	 */
	private List<Build> getBuldsByProject(int projectId) throws DaoException {
		Build build = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Build> listItems = new ArrayList<Build>();
		ResultSet resultSet = null;
		String query = "SELECT * FROM Build WHERE projectId=?;";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, projectId);
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				build = new Build(resultSet.getInt("id"));
				build.setDescription(resultSet.getString("description"));
				listItems.add(build);
			}
		} catch (SQLException e) {
			LOG.error(query, e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
		return listItems;
	}
}
