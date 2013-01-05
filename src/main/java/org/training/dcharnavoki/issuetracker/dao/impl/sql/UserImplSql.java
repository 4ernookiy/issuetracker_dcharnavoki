package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.Role;
import org.training.dcharnavoki.issuetracker.beans.User;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.IUserDAO;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp.ConfKeys;

/**
 * The Class UserImplSql.
 */
public class UserImplSql extends AbstractBaseDB implements IUserDAO {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(UserImplSql.class);

	/**
	 * Instantiates a new user impl sql.
	 * @throws DaoException
	 *             the dao exception
	 */
	public UserImplSql() throws DaoException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the user.
	 * @param rs
	 *            the rs
	 * @return the user
	 * @throws DaoException
	 *             the dao exception
	 * @throws SQLException
	 *             the sQL exception
	 */
	private User getUser(ResultSet rs) throws DaoException, SQLException {
		int id = rs.getInt("id");
		User user = new User(id);
		user.setFirstName(rs.getString("firstName"));
		user.setLastName(rs.getString("lastName"));
		user.setEmail(rs.getString("email"));
		Role role = Role.getRole(rs.getInt("role"));
		user.setRole(role);
		user.setPassword(rs.getString("password"));
		return user;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IUserDAO#getUser(int)
	 */
	@Override
	public User getUser(int uId) throws DaoException {
		User user = null;
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(getConfig().get(ConfKeys.SQL_USER_SELECT_FROM_ID));
			pstmt.setInt(1, uId);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				user = getUser(resultSet);
			}
			if (resultSet.next()) {
				LOG.warn("more than one instance of an object " + getClass().getSimpleName()
						+ " with id:" + uId);
			}
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_USER_SELECT_FROM_ID), e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.IUserDAO#getUser(java.lang.
	 * String)
	 */
	@Override
	public User getUser(String email) throws DaoException {
		User user = null;
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn
					.prepareStatement(getConfig().get(ConfKeys.SQL_USER_SELECT_FROM_EMAIL));
			pstmt.setString(1, email);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				user = getUser(resultSet);
			}
			if (resultSet.next()) {
				LOG.warn("more than one instance of an object " + getClass().getSimpleName()
						+ " with email:" + email);
			}
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_USER_SELECT_FROM_EMAIL), e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.IUserDAO#addUser(org.training
	 * .dcharnavoki.issuetracker.beans.User)
	 */
	@Override
	public void addUser(User newUser) throws DaoException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			int id = 0;
			conn = getConnection();
			pstmt = conn.prepareStatement(getConfig().get(ConfKeys.SQL_USER_INSERT_NEW));
			pstmt.setString(++id, newUser.getFirstName());
			pstmt.setString(++id, newUser.getLastName());
			pstmt.setString(++id, newUser.getEmail());
			pstmt.setInt(++id, newUser.getRole().ordinal());
			pstmt.setString(++id, newUser.getPassword());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_ISSUE_INSERT_NEW), e);
			throw new DaoException(e);
		} finally {
			closeResource(pstmt);
			closeResource(conn);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IUserDAO#getAllUsers()
	 */
	@Override
	public List<User> getAllUsers() throws DaoException {
		Connection conn = null;
		Statement stmt = null;
		List<User> listItems = new ArrayList<User>();
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(getConfig().get(ConfKeys.SQL_USER_SELECT_ALL));
			while (resultSet.next()) {
				listItems.add(getUser(resultSet));
			}
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_USER_SELECT_ALL), e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(stmt);
			closeResource(conn);
		}
		return listItems;
	}

	@Override
	public void updateUser(User update) throws DaoException {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			int id = 0;
			pstmt = conn.prepareStatement(getConfig().get(ConfKeys.SQL_USER_UPDATE));
			pstmt.setString(++id, update.getFirstName());
			pstmt.setString(++id, update.getLastName());
			pstmt.setString(++id, update.getEmail());
			pstmt.setInt(++id, update.getRole().ordinal());
			pstmt.setString(++id, update.getPassword());
			pstmt.setInt(++id, update.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_USER_UPDATE), e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
	}

}
