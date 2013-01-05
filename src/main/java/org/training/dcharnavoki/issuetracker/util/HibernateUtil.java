package org.training.dcharnavoki.issuetracker.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * The Class HibernateUtil.
 * @author dima
 *         http://stackoverflow.com/questions/8621906/is-buildsessionfactory-deprecated
 *         -in-hibernate-4
 */
public final class HibernateUtil {

	/** The Constant sessionFactory. */
	private static final SessionFactory SESSION_FACTORY = configureSessionFactory();

	/**
	 * Instantiates a new hibernate util.
	 */
	private HibernateUtil() {
		super();
	}

	/**
	 * Configure session factory.
	 *
	 * @return the session factory
	 * @throws HibernateException the hibernate exception
	 */
	private static SessionFactory configureSessionFactory() {
		ServiceRegistry serviceRegistry;
		Configuration configuration = new Configuration().configure();
		serviceRegistry = new ServiceRegistryBuilder().applySettings(
				configuration.getProperties()).buildServiceRegistry();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	/**
	 * Gets the session factory.
	 * @return {@link SessionFactory}
	 */
	public static SessionFactory getSessionFactory() {
		return SESSION_FACTORY;
	}

}
