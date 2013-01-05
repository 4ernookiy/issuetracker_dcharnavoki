package org.training.dcharnavoki.issuetracker.dao.impl.hibernate;

import org.training.dcharnavoki.issuetracker.beans.Type;
import org.training.dcharnavoki.issuetracker.dao.ITypeDAO;
import org.training.dcharnavoki.issuetracker.util.HibernateUtil;

/**
 * The Class TypeImplHiber.
 */
public class TypeImplHiber extends GenericDAOHiber<Type, Integer> implements ITypeDAO {

	/**
	 * Instantiates a new type impl hiber.
	 */
	public TypeImplHiber() {
		super(Type.class,  HibernateUtil.getSessionFactory());
	}

}
