/**********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005, 2006 The Sakai Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 * 
 *      http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *
 **********************************************************************************/

package org.sakaiproject.tool.tasklist.api;

import java.util.Map;


/**
 * This is the interface for the Manager for our tasklist tool, 
 * it handles the data access functionality of the tool, we currently
 * have 2 implementations (memory and hibernate)
 * @author az
 *
 */
public interface TaskListManager {

	/**
	 * Puts the task in the tasklist
	 * @param t - the task object to save
	 * @return - true for success, false if failure
	 */
	public boolean saveTask(Task t);

	/**
	 * Removes the task from the tasklist
	 * @param t - the task object to remove
	 * @return - true for success, false if failure
	 */
	public boolean deleteTask(Task t);


	/**
	 * Gets all the task objects for the site
	 * @param siteId - the siteId of the site
	 * @return - a collection of task objects (empty collection if none found)
	 */
	public Map findAllTasks(String siteId);

}
