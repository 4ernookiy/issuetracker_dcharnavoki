package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.training.dcharnavoki.issuetracker.beans.Build;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.IBuildDAO;

/**
 * The Class BuildImplSql.
 */
public class BuildImplSql extends CommonBeanImplSql<Build> implements IBuildDAO {

	/**
	 * Instantiates a new builds the impl sql.
	 *
	 * @throws DaoException the dao exception
	 */
	public BuildImplSql() throws DaoException {
		super(Build.class);
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getInstance()
	 */
	@Override
	public Build getInstance() {
		return new Build();
	}

	@Override
	protected PreparedStatement saveEntity(PreparedStatement pstm, Build entity)
			throws SQLException {
		int id = 0;
		pstm.setInt(++id, entity.getProjectId());
		pstm.setString(++id, entity.getDescription());
		return pstm;
	}

	@Override
	protected Build getEntity(ResultSet resultSet) throws SQLException {
		Build build = getInstance();
		build.setId(resultSet.getInt("id"));
		build.setProjectId(resultSet.getInt("projectId"));
		build.setDescription(resultSet.getString("description"));
		return build;
	}

	@Override
	public String getSqlInsert() throws DaoException {
		return "INSERT INTO " + getKlass().getSimpleName() + " VALUES (NULL, ?, ?)";
	}

}
