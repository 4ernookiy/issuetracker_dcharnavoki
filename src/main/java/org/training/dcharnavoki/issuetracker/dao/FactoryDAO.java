package org.training.dcharnavoki.issuetracker.dao;

import java.util.HashMap;
import java.util.Map;

import org.training.dcharnavoki.issuetracker.constant.ConstErr;
import org.training.dcharnavoki.issuetracker.constant.Constant;
import org.training.dcharnavoki.issuetracker.dao.impl.ParserXML;

// TODO: Auto-generated Javadoc
/**
 * The Class FactoryDAO.
 */
public class FactoryDAO {
	
	/** The implementation. */
	private static IDAO implementation=null;
	
	/** The map. */
	private static Map<String,IDAO> map = new HashMap<String, IDAO>();
	
	static{
		map.put(Constant.DAO_XML_IMPLEMENTATION, new ParserXML());
	}

	/**
	 * Gets the implementation.
	 *
	 * @return the implementation
	 */
	public static IDAO getImplementation() {
		return implementation;
	}

	/**
	 * Sets the implementation.
	 *
	 * @param className the new implementation
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public static void setImplementation(String className) throws IllegalArgumentException{
		FactoryDAO.implementation = map.get(className);
		if (FactoryDAO.implementation == null ) throw new IllegalArgumentException(ConstErr.NO_IMPLEMENTATION);
	}

}
