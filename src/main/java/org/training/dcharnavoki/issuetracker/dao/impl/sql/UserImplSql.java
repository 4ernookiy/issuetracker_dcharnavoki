package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Role;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.IUserDAO;

/**
 * The Class UserImplSql.
 */
public class UserImplSql extends GenericDAOSql<User> implements IUserDAO {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(UserImplSql.class);

	/**
	 * Instantiates a new user impl sql.
	 *
	 * @throws DaoException the dao exception
	 */
	public UserImplSql() throws DaoException {
		super(User.class);
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IUserDAO#getUser(java.lang.String)
	 */
	@Override
	public User getUser(String email) throws DaoException {
		User user = null;
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		String query = "SELECT * FROM user WHERE email = ?;";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, email);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				user = getEntity(resultSet);
			}
			if (resultSet.next()) {
				LOG.warn("more than one instance of an object " + getClass().getSimpleName()
						+ " with email:" + email);
			}
		} catch (SQLException e) {
			LOG.error(query, e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
		return user;
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getInstance()
	 */
	@Override
	public User getInstance() {
		return new User();
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#saveEntity(java.sql.PreparedStatement, java.lang.Object)
	 */
	@Override
	protected PreparedStatement saveEntity(PreparedStatement pstm, User entity)
			throws SQLException {
		int id = 0;
		pstm.setString(++id, entity.getFirstName());
		pstm.setString(++id, entity.getLastName());
		pstm.setString(++id, entity.getEmail());
		pstm.setString(++id, entity.getRole().toString());
		pstm.setString(++id, entity.getPassword());
		return pstm;
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#updateEntity(java.sql.PreparedStatement, java.lang.Object)
	 */
	@Override
	protected PreparedStatement updateEntity(PreparedStatement pstm, User entity)
			throws SQLException {
		int id = 0;
		pstm.setString(++id, entity.getFirstName());
		pstm.setString(++id, entity.getLastName());
		pstm.setString(++id, entity.getEmail());
		pstm.setString(++id, entity.getRole().toString());
		pstm.setString(++id, entity.getPassword());
		pstm.setInt(++id, entity.getId());
		return pstm;
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getEntity(java.sql.ResultSet)
	 */
	@Override
	protected User getEntity(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		User user = getInstance();
		user.setId(id);
		user.setFirstName(resultSet.getString("firstName"));
		user.setLastName(resultSet.getString("lastName"));
		user.setEmail(resultSet.getString("email"));
		Role role = Role.valueOf(resultSet.getString("role"));
		user.setRole(role);
		user.setPassword(resultSet.getString("password"));
		return user;
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getSqlInsert()
	 */
	@Override
	public String getSqlInsert() throws DaoException {
		return "INSERT INTO " + getKlass().getSimpleName() + " VALUES (NULL, ?, ?, ?, ?, ?);";
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getSqlUpdate()
	 */
	@Override
	public String getSqlUpdate() throws DaoException {
		return "UPDATE "
				+ getKlass().getSimpleName()
				+ " SET firstName= ?, lastName= ?, email= ?, role= ?, password= ? WHERE id = ?;";
	}

	/* (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.impl.sql.GenericDAOSql#getSqlDelete()
	 */
	@Override
	public String getSqlDelete() throws DaoException {
		return null;
	}

}
