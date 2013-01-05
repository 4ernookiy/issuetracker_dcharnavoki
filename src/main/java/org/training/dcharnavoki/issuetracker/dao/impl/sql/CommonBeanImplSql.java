package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.training.dcharnavoki.issuetracker.beans.CommonBean;
import org.training.dcharnavoki.issuetracker.dao.DaoException;

/**
 * The Class CommonBeanImplSql.
 * @param <T>
 *            the generic type
 */
public abstract class CommonBeanImplSql<T extends CommonBean> extends GenericDAOSql<T> {

	/**
	 * Instantiates a new common bean impl sql.
	 * @param klass
	 *            the klass
	 * @throws DaoException
	 *             the dao exception
	 */
	public CommonBeanImplSql(Class<T> klass) throws DaoException {
		super(klass);
	}

	@Override
	protected PreparedStatement saveEntity(PreparedStatement pstm, T entity)
			throws SQLException {
		pstm.setString(1, entity.getDescription());
		return pstm;
	}

	@Override
	protected PreparedStatement updateEntity(PreparedStatement pstm, T entity)
			throws SQLException {
		pstm.setString(1, entity.getDescription());
		pstm.setInt(2, entity.getId());
		return pstm;
	}

	@Override
	protected T getEntity(ResultSet resultSet) throws SQLException {
		T t = getInstance();
		t.setId(resultSet.getInt("id"));
		t.setDescription(resultSet.getString("description"));
		return t;
	}

	@Override
	public String getSqlInsert() throws DaoException {
		return "INSERT INTO " + getKlass().getSimpleName() + " VALUES (NULL, ?);";
	}

	@Override
	public String getSqlUpdate() throws DaoException {
		return "UPDATE " + getKlass().getSimpleName() + " SET description = ? WHERE id = ?;";
	}

	@Override
	public String getSqlDelete() throws DaoException {
		return null; // not implementation
	}

}
