package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.GenericDAO;

/**
 * The Class GenrericDAOSql.
 * @param <T>
 *            the generic type
 */
public abstract class GenericDAOSql<T> extends AbstractBaseDB implements
		GenericDAO<T, Integer> {
	/** The log. */
	private static final Logger LOG = Logger.getLogger(GenericDAOSql.class);
	/** The class. */
	private Class<T> klass;

	/**
	 * Instantiates a new genreric dao sql.
	 * @throws DaoException
	 *             the dao exception
	 */
	private GenericDAOSql() throws DaoException {
		super();
	}

	/**
	 * Instantiates a new genreric dao sql.
	 * @param klass
	 *            the klass
	 * @throws DaoException
	 *             the dao exception
	 */
	public GenericDAOSql(Class<T> klass) throws DaoException {
		this(); // !!! don't remove
		this.klass = klass;
	}

	/**
	 * Gets the klass.
	 * @return the klass
	 */
	public Class<T> getKlass() {
		return klass;
	}

	/**
	 * Gets the single instance of GenericDAOSql.
	 * @return single instance of GenericDAOSql
	 */
	public abstract T getInstance();

	/**
	 * Save entity.
	 * @param pstm
	 *            the pstm
	 * @param entity
	 *            the entity
	 * @return the prepared statement
	 * @throws SQLException
	 *             the sQL exception
	 */
	protected abstract PreparedStatement saveEntity(PreparedStatement pstm, T entity)
			throws SQLException;

	/**
	 * Update entity.
	 * @param pstm
	 *            the pstm
	 * @param entity
	 *            the entity
	 * @return the prepared statement
	 * @throws SQLException
	 *             the sQL exception
	 */
	protected abstract PreparedStatement updateEntity(PreparedStatement pstm, T entity)
			throws SQLException;

	/**
	 * Gets the entity.
	 * @param resultSet
	 *            the result set
	 * @return the entity
	 * @throws SQLException
	 *             the sQL exception
	 */
	protected abstract T getEntity(ResultSet resultSet) throws SQLException;

	/**
	 * Gets the sql insert.
	 * @return the sql insert
	 * @throws DaoException
	 *             the dao exception
	 */
	public abstract String getSqlInsert() throws DaoException;

	/**
	 * Gets the sql update.
	 * @return the sql update
	 * @throws DaoException
	 *             the dao exception
	 */
	public abstract String getSqlUpdate() throws DaoException;

	/**
	 * Gets the sql delete.
	 * @return the sql delete
	 * @throws DaoException
	 *             the dao exception
	 */
	public abstract String getSqlDelete() throws DaoException;

	/**
	 * Gets the sql select.
	 * @return the sql select
	 */
	public String getSqlSelect() {
		return "SELECT * FROM " + getKlass().getSimpleName();
	}

	/**
	 * Gets the sql select by id.
	 * @return the sql select by id
	 */
	public String getSqlSelectById() {
		return "SELECT * FROM " + getKlass().getSimpleName() + " WHERE id =?";
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.GenericDAO#save(java.lang.Object
	 * )
	 */
	@Override
	public Integer save(T entity) throws DaoException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int id = 0;
		try {
			conn = getConnection();
			String query = getSqlInsert(); // sql for save
			LOG.info(query);
			pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt = saveEntity(pstmt, entity);
			int added = pstmt.executeUpdate();
			if (added == 0) {
				throw new DaoException("failed querry");
			}
			resultSet = pstmt.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
				return id;
			} else {
				throw new SQLException("failed to obtain " + getKlass().getName() + " id");
			}
		} catch (SQLException e) {
			LOG.error(e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.GenericDAO#update(java.lang
	 * .Object)
	 */
	@Override
	public void update(T entity) throws DaoException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			connection = getConnection();
			String query = getSqlUpdate(); // sql for update
			LOG.info(query);
			pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			connection.setAutoCommit(false);
			pst = updateEntity(pst, entity);
			// int added =
			pst.executeUpdate();
			// if (added == 0) {
			// throw new DaoException("failed querry" + getSqlInsert());
			// result = true;
			// }
			connection.commit();
		} catch (SQLException e) {
			LOG.error(e);
			try {
				connection.rollback();
			} catch (SQLException er) {
				throw new DaoException(er);
			}
			throw new DaoException(e);
		} finally {
			closeResource(pst);
			closeResource(connection);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.GenericDAO#deleteEntity(java
	 * .lang.Object)
	 */
	@Override
	public void deleteEntity(T entity) throws DaoException {
		throw new DaoException("this metod not implements");
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.GenericDAO#findAll()
	 */
	@Override
	public List<T> findAll() throws DaoException {
		Connection conn = null;
		Statement stmt = null;
		List<T> listItems = new ArrayList<T>();
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			String query = getSqlSelect(); // sql for select all
			LOG.info(query);
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				listItems.add(getEntity(resultSet));
			}
		} catch (SQLException e) {
			LOG.error(getSqlSelect(), e);
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
	 * org.training.dcharnavoki.issuetracker.dao.GenericDAO#findByID(java.io
	 * .Serializable)
	 */
	@Override
	public T findByID(Integer id) throws DaoException {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		T t = null;
		try {
			conn = getConnection();
			String query = getSqlSelectById(); // sql for select by id
			LOG.info(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				t = getEntity(resultSet);
			}
			if (resultSet.next()) {
				LOG.warn("more than one instance of an object " + getKlass().getSimpleName()
						+ " with id:" + id);
			}
		} catch (SQLException e) {
			LOG.error(getSqlSelectById(), e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
		return t;
	}

}
