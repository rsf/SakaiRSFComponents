/* *****************************************************************************
 * TaskListManagerJDBCImpl.java - created by az on May 23, 2006 at 3:46:20 PM
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

import java.sql.Types;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.sakaiproject.db.api.SqlService;
import org.sakaiproject.tool.tasklist.api.Task;
import org.sakaiproject.tool.tasklist.api.TaskListManager;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import uk.org.ponder.util.UniversalRuntimeException;

public class TaskListManagerJdbcImpl extends JdbcDaoSupport implements TaskListManager {

	private static Log log = LogFactory.getLog(TaskListManagerJdbcImpl.class);
	
	private boolean autoDdl = true; // assume true

	private static final String TASK_CREATE_TABLE_ORACLE = 
		"CREATE TABLE tasklist_tasks (" +
		"TASK_ID           	number(20) NOT NULL PRIMARY KEY," +
		"TASK_OWNER        	varchar(255) NOT NULL," +
		"TASK_SITE_ID      	varchar(255) NOT NULL," +
		"TASK_CREATION_DATE	date NOT NULL," +
		"TASK_TEXT         	clob NOT NULL )";
	private static final String TASK_CREATE_SEQ_ORACLE = 
		"create sequence seq_TASKLIST_TASKS";
	private static final String TASK_CREATE_TRIGGER_ORACLE = 
		"create trigger b_insert_TASKLIST " +
		"before insert on tasklist_tasks " +
		"for each row " +
		"begin " +
		"select seq_TASKLIST_TASKS.nextval " +
		"into :new.PK1 from dual; " +
		"end";
	private static final String TASK_CREATE_TABLE_MYSQL = 
		"CREATE TABLE tasklist_tasks ( " +
		"TASK_ID           	bigint(20) AUTO_INCREMENT NOT NULL," +
		"TASK_OWNER        	varchar(255) NOT NULL," +
		"TASK_SITE_ID      	varchar(255) NOT NULL," +
		"TASK_CREATION_DATE	datetime NOT NULL," +
		"TASK_TEXT         	text NOT NULL," +
		"PRIMARY KEY(TASK_ID) )";
	private static final String TASK_CREATE_TABLE_HSQLDB = 
		"CREATE TABLE tasklist_tasks ( " +
		"TASK_ID           	BIGINT NOT NULL IDENTITY," +
		"TASK_OWNER        	varchar(255) NOT NULL," +
		"TASK_SITE_ID      	varchar(255) NOT NULL," +
		"TASK_CREATION_DATE	date NOT NULL," +
		"TASK_TEXT         	LONGVARCHAR NOT NULL )";
	private static final String TASK_FETCH_QUERY = 
		"select * from TASKLIST_TASKS where TASK_SITE_ID = ?";
	private static final String TASK_INSERT_QUERY = 
		"insert into TASKLIST_TASKS " +
		"(TASK_OWNER, TASK_SITE_ID, TASK_CREATION_DATE, TASK_TEXT) " +
		"VALUES(?, ?, ?, ?)";
	private static final String TASK_UPDATE_QUERY = 
		"update TASKLIST_TASKS set TASK_OWNER=?, TASK_SITE_ID=?, " +
		"TASK_TEXT=? where TASK_ID = ?";
	private static final String TASK_DELETE_QUERY = 
		"delete from TASKLIST_TASKS where TASK_ID = ?";

	public void init() {
		// get the autoDdl value
		autoDdl = org.sakaiproject.component.cover.ServerConfigurationService.getInstance().getBoolean("auto.ddl", true);

		if (autoDdl) {
			/*
			 * this will create our table, this is fragile but functional,
			 * it will not be able to recreate the table if it changes,
			 * if you use something like this in production, it is likely that
			 * Glenn will have you killed - AZ
			 */
			try {
				SqlService sqlService = org.sakaiproject.db.cover.SqlService.getInstance();
				if (sqlService.getVendor().equals("hsqldb")) {
					getJdbcTemplate().execute(TASK_CREATE_TABLE_HSQLDB);
					log.info("Created tasklist table in hsqldb");
				} else if (sqlService.getVendor().equals("mysql")) {
					getJdbcTemplate().execute(TASK_CREATE_TABLE_MYSQL);
					log.info("Created tasklist table in mysql");
				} else if (sqlService.getVendor().equals("oracle")) {
					getJdbcTemplate().execute(TASK_CREATE_TABLE_ORACLE);
					getJdbcTemplate().execute(TASK_CREATE_SEQ_ORACLE);
					getJdbcTemplate().execute(TASK_CREATE_TRIGGER_ORACLE);
					log.info("Created tasklist table in oracle");
				} else {
					log.error("Unknown database type, cannot create task table");
				}
			} catch (DataAccessException e) {
				log.info("Tasklist JDBC exception: " + e.getMessage());
			}
		} else {
			log.warn("AUTO DDL IS OFF, tasklist table not created:");
		}
	}

	public boolean saveTask(Task t) {
		try {
			if (t.getId() == null) {
				// if the id is not set the we are inserting a new record
				getJdbcTemplate().update(TASK_INSERT_QUERY,
					new Object[] {t.getOwner(), t.getSiteId(), t.getCreationDate(), t.getTask()},
					new int[] {Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.CLOB});
			} else {
				getJdbcTemplate().update(TASK_UPDATE_QUERY,
					new Object[] {t.getOwner(), t.getSiteId(), t.getTask(), t.getId()},
					new int[] {Types.VARCHAR, Types.VARCHAR, Types.CLOB, Types.BIGINT});
			}
		} catch (DataAccessException e) {
			log.error("Exception: Could not add task:" + e.toString());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteTask(Task t) {
		try {
			getJdbcTemplate().update(TASK_DELETE_QUERY,
				new Object[] {t.getId()},
				new int[] {Types.BIGINT});
		} catch (DataAccessException e) {
			log.error("Exception: Could not delete task:" + t.getId() + ":" + e.toString());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Map findAllTasks(String siteId) {
		try {
			List l = getJdbcTemplate().query(TASK_FETCH_QUERY, new Object[] {siteId}, new TaskRowMapper());
			return TaskUtil.taskCollectionToMap(l);
		} catch (DataAccessException e) {
          throw UniversalRuntimeException.accumulate(e, "Could not fetch tasks for site:" + siteId);
		}
	}
}
