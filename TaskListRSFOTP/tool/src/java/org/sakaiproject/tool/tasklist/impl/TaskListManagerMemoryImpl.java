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

package org.sakaiproject.tool.tasklist.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.tool.tasklist.api.Task;
import org.sakaiproject.tool.tasklist.api.TaskListManager;

public class TaskListManagerMemoryImpl implements TaskListManager {

	//	 use commons logger
	private static Log log = LogFactory.getLog(TaskListManagerMemoryImpl.class);

	private List savedTasks = new LinkedList();
	private Long index = new Long(0);

	public TaskListManagerMemoryImpl() {
		log.debug("Constructor");
	}

	public boolean saveTask(Task task) {
		log.debug("saveTask");
		
		// Set id
		index = new Long(index.longValue() + 1);
		task.setId(index);
		
		return savedTasks.add(task);
	}

	public boolean deleteTask(Task task) {
		log.debug("deleteTask");

		return savedTasks.remove(task);
	}

	public Map findAllTasks(String siteId) {
		log.debug("findAllTasks");
		
		Task task = null;

		List tasks = new LinkedList();
		Iterator iter = savedTasks.iterator();
		while(iter.hasNext()) {
			task = (Task)iter.next();
			if(task.getSiteId().equals(siteId)) {
				tasks.add(task);
			}
		}
		return TaskUtil.taskCollectionToMap(tasks);
	}
}
