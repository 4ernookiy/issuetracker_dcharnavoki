package org.training.dcharnavoki.issuetracker.dao;

import org.training.dcharnavoki.issuetracker.beans.Priority;
import org.training.dcharnavoki.issuetracker.beans.Resolution;
import org.training.dcharnavoki.issuetracker.beans.Status;
import org.training.dcharnavoki.issuetracker.beans.Type;

/**
 * The Interface IConfDAO.
 */
public interface IConfDAO extends IDAO {
  	/**
  	 * Gets the status.
  	 *
  	 * @param id the id
  	 * @return the status
  	 */
  	Status getStatus(int id);
	/**
	 * Gets the priority.
	 *
	 * @param id the id
	 * @return the priority
	 */
	Priority getPriority(int id);
  /**
   * Gets the resolution.
   *
   * @param id the id
   * @return the resolution
   */
  Resolution getResolution(int id);
    	/**
    	 * Gets the type.
    	 *
    	 * @param id the id
    	 * @return the type
    	 */
    	Type getType(int id);

}
