package org.training.dcharnavoki.issuetracker.dao.impl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.training.dcharnavoki.issuetracker.beans.CommonBean;
import org.training.dcharnavoki.issuetracker.beans.Priority;
import org.training.dcharnavoki.issuetracker.beans.Resolution;
import org.training.dcharnavoki.issuetracker.beans.Status;
import org.training.dcharnavoki.issuetracker.beans.Type;
import org.training.dcharnavoki.issuetracker.dao.DaoException;
import org.training.dcharnavoki.issuetracker.dao.IConfDAO;
import org.training.dcharnavoki.issuetracker.start.preparing.ConfigApp.ConfKeys;

/**
 * The Class ConfSql.
 */
public class ConfImplSql extends AbstractBaseDB implements IConfDAO {
	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(ConfImplSql.class);

	/**
	 * Instantiates a new conf sql.
	 * @throws DaoException
	 *            the dao exception
	 */
	public ConfImplSql() throws DaoException {
		super();
	}

	/**
	 * Gets the from id.
	 * @param query
	 *           the query
	 * @param id
	 *           the id
	 * @return the from id
	 * @throws DaoException
	 *            the dao exception
	 */
	private CommonBean getFromId(String query, int id) throws DaoException {
		CommonBean cb = null;
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				cb = new CommonBean(resultSet.getInt("id"));
				cb.setDescription(resultSet.getString("description"));
			}
			if (resultSet.next()) {
				LOG.warn("more than one instance of an object " + getClass().getSimpleName()
						+ " with id:" + id);
			}
		} catch (SQLException e) {
			LOG.error(query, e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
		return cb;
	}

	/**
	 * Gets the list.
	 * @param <T>
	 *           the generic type
	 * @param query
	 *           the query
	 * @return the list
	 * @throws DaoException
	 *            the dao exception
	 */
	private <T extends CommonBean> List<T> getList(String query) throws DaoException {
		T t = null;
		Connection conn = null;
		ResultSet resultSet = null;
		Statement stmt = null;
		List<T> listT = new ArrayList<T>();
		int id = 0;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				id = resultSet.getInt("id");
				t = (T) new CommonBean(id);
				t.setDescription(resultSet.getString("description"));
				listT.add(t);
			}
		} catch (SQLException e) {
			LOG.error(query, e);
			throw new DaoException(query, e);
		} finally {
			closeResource(resultSet);
			closeResource(stmt);
			closeResource(conn);
		}
		return listT;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getStatus(int)
	 */
	@Override
	public Status getStatus(int sId) throws DaoException {
		CommonBean cb = getFromId(getConfig().get(ConfKeys.SQL_CONF_STATUS_SELECT_FROM_ID), sId);
		if (null == cb) {
			return null;
		}
		Status variable = new Status(cb.getId());
		variable.setDescription(cb.getDescription());
		return variable;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getStatuses()
	 */
	@Override
	public List<Status> getStatuses() throws DaoException {
		return getList(getConfig().get(ConfKeys.SQL_CONF_STATUS_SELECT_ALL));
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getPriority(int)
	 */
	@Override
	public Priority getPriority(int pId) throws DaoException {
		CommonBean cb = getFromId(getConfig().get(ConfKeys.SQL_CONF_PRIORITY_SELECT_FROM_ID),
				pId);
		if (null == cb) {
			return null;
		}
		Priority variable = new Priority(cb.getId());
		variable.setDescription(cb.getDescription());
		return variable;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getPriorities()
	 */
	@Override
	public List<Priority> getPriorities() throws DaoException {
		return getList(getConfig().get(ConfKeys.SQL_CONF_PRIORITY_SELECT_ALL));
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getResolution(int)
	 */
	@Override
	public Resolution getResolution(int rId) throws DaoException {
		CommonBean cb = getFromId(getConfig().get(ConfKeys.SQL_CONF_RESOLUTION_SELECT_FROM_ID),
				rId);
		if (null == cb) {
			return null;
		}
		Resolution variable = new Resolution(cb.getId());
		variable.setDescription(cb.getDescription());
		return variable;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getResolutions()
	 */
	@Override
	public List<Resolution> getResolutions() throws DaoException {
		return getList(getConfig().get(ConfKeys.SQL_CONF_RESOLUTION_SELECT_ALL));
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getType(int)
	 */
	@Override
	public Type getType(int tId) throws DaoException {
		CommonBean cb = getFromId(getConfig().get(ConfKeys.SQL_CONF_TYPE_SELECT_FROM_ID), tId);
		if (null == cb) {
			return null;
		}
		Type variable = new Type(cb.getId());
		variable.setDescription(cb.getDescription());
		return variable;
	}

	/*
	 * (non-Javadoc)
	 * @see org.training.dcharnavoki.issuetracker.dao.IConfDAO#getTypes()
	 */
	@Override
	public List<Type> getTypes() throws DaoException {
		return getList(getConfig().get(ConfKeys.SQL_CONF_TYPE_SELECT_ALL));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.training.dcharnavoki.issuetracker.dao.IConfDAO#addResolution(org.training
	 * .dcharnavoki.issuetracker.beans.Resolution)
	 */
	@Override
	public void addResolution(Resolution newResolution) throws DaoException {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(getConfig()
					.get(ConfKeys.SQL_CONF_RESOLUTION_INSERT_NEW));
			int id = 0;
			pstmt.setString(++id, newResolution.getDescription());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_CONF_RESOLUTION_INSERT_NEW), e);
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
	 * org.training.dcharnavoki.issuetracker.dao.IConfDAO#updateResolution(org
	 * .training.dcharnavoki.issuetracker.beans.Resolution)
	 */
	@Override
	public void updateResolution(Resolution update) throws DaoException {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			int id = 0;
			pstmt = conn.prepareStatement(getConfig().get(ConfKeys.SQL_CONF_RESOLUTION_UPDATE));
			pstmt.setString(++id, update.getDescription());
			pstmt.setInt(++id, update.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_CONF_RESOLUTION_UPDATE), e);
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
	 * org.training.dcharnavoki.issuetracker.dao.IConfDAO#addPriority(org.training
	 * .dcharnavoki.issuetracker.beans.Priority)
	 */
	@Override
	public void addPriority(Priority newItem) throws DaoException {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(getConfig().get(ConfKeys.SQL_CONF_PRIORITY_INSERT_NEW));
			int id = 0;
			pstmt.setString(++id, newItem.getDescription());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_CONF_PRIORITY_INSERT_NEW), e);
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
	 * org.training.dcharnavoki.issuetracker.dao.IConfDAO#updatePriority(org.
	 * training.dcharnavoki.issuetracker.beans.Priority)
	 */
	@Override
	public void updatePriority(Priority update) throws DaoException {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			int id = 0;
			pstmt = conn.prepareStatement(getConfig().get(ConfKeys.SQL_CONF_PRIORITY_UPDATE));
			pstmt.setString(++id, update.getDescription());
			pstmt.setInt(++id, update.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_CONF_PRIORITY_UPDATE), e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
	}

	@Override
	public void addType(Type newItem) throws DaoException {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(getConfig().get(ConfKeys.SQL_CONF_TYPE_INSERT_NEW));
			int id = 0;
			pstmt.setString(++id, newItem.getDescription());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_CONF_TYPE_INSERT_NEW), e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
	}

	@Override
	public void updateType(Type update) throws DaoException {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			int id = 0;
			pstmt = conn.prepareStatement(getConfig().get(ConfKeys.SQL_CONF_TYPE_UPDATE));
			pstmt.setString(++id, update.getDescription());
			pstmt.setInt(++id, update.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_CONF_TYPE_UPDATE), e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
	}

	@Override
	public void updateType(Status update) throws DaoException {
		Connection conn = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			int id = 0;
			pstmt = conn.prepareStatement(getConfig().get(ConfKeys.SQL_CONF_STATUS_UPDATE));
			pstmt.setString(++id, update.getDescription());
			pstmt.setInt(++id, update.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			LOG.error(getConfig().get(ConfKeys.SQL_CONF_STATUS_UPDATE), e);
			throw new DaoException(e);
		} finally {
			closeResource(resultSet);
			closeResource(pstmt);
			closeResource(conn);
		}
	}

}
