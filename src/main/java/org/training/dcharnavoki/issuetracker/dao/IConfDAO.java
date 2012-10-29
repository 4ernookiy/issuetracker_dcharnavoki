package org.training.dcharnavoki.issuetracker.dao;

import org.training.dcharnavoki.issuetracker.beans.Priority;
import org.training.dcharnavoki.issuetracker.beans.Resolution;
import org.training.dcharnavoki.issuetracker.beans.Status;
import org.training.dcharnavoki.issuetracker.beans.Type;

/**
 * The Interface IConfDAO.
 */
public interface IConfDAO {

	  /**
	   * Gets the status.
	   *
	   * @param sId the s id
	   * @return the status
	   */
  	Status getStatus(int sId);

	/**
	 * Gets the priority.
	 *
	 * @param pId the id
	 * @return the priority
	 */
	Priority getPriority(int pId);
  /**
   * Gets the resolution.
   *
   * @param rId the r id
   * @return the resolution
   */
  Resolution getResolution(int rId);
	    /**
	     * Gets the type.
	     *
	     * @param tId the t id
	     * @return the type
	     */
    	Type getType(int tId);

}
