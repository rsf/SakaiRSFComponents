/* *****************************************************************************
 * TaskRowMapper.java - created by az on May 23, 2006 at 5:38:43 PM
 * 
 * Copyright (c) 2006 Virginia Polytechnic Institute and State University
 * http://www.vt.edu/ - https://content.cc.vt.edu/confluence/display/DEV/
 * Licensed under the Educational Community License version 1.0
 * 
 * A copy of the Educational Community License has been included in this 
 * distribution and is available at: http://www.opensource.org/licenses/ecl1.php
 * 
 * Contributors:
 * Aaron Zeckoski (aaronz@vt.edu) - Project Lead
 * 
 * ****************************************************************************/
package org.sakaiproject.tool.tasklist.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.sakaiproject.tool.tasklist.api.Task;
import org.springframework.jdbc.core.RowMapper;

/**
 * This maps the data fetched by JDBC to our task object
 * 
 * @author aaronz@vt.edu
 * @version $Revision$
 */
public class TaskRowMapper implements RowMapper {

	public static final String TASK_ID = "TASK_ID";
	public static final String TASK_OWNER = "TASK_OWNER";  
	public static final String TASK_SITE_ID = "TASK_SITE_ID";
	public static final String TASK_CREATION_DATE = "TASK_CREATION_DATE";
	public static final String TASK_TEXT = "TASK_TEXT";

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Task task = new TaskImpl();
		task.setId(new Long(rs.getLong(TASK_ID)));
		task.setOwner(rs.getString(TASK_OWNER));
		task.setSiteId(rs.getString(TASK_SITE_ID));
		task.setCreationDate(rs.getDate(TASK_CREATION_DATE));
		task.setTask(rs.getString(TASK_TEXT));
		return task;
	}

}
