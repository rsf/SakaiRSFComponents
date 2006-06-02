package org.sakaiproject.tool.tasklist.impl;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import org.sakaiproject.tool.tasklist.api.Task;
import org.sakaiproject.tool.tasklist.api.TaskListManager;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TaskListManagerDaoImpl extends HibernateDaoSupport implements TaskListManager {

	//	 use commons logger
	private static Log log = LogFactory.getLog(TaskListManagerDaoImpl.class);

	public boolean saveTask(Task t) {
		try {
			getHibernateTemplate().save(t);
		} catch (DataAccessException e) {
			log.error("Hibernate could not save: " + e.toString());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteTask(Task t) {
		try {
			getHibernateTemplate().delete(t);
		} catch (DataAccessException e) {
			log.error("Hibernate could not delete: " + e.toString());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Map findAllTasks(String siteId) {
		DetachedCriteria d = DetachedCriteria.forClass(Task.class)
			.add( Restrictions.eq("siteId", siteId) )
			.addOrder( Order.desc("creationDate") );
		return TaskUtil.taskCollectionToMap(getHibernateTemplate().findByCriteria(d));
	}
}
