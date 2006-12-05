/******************************************************************************
 * CrudPlusDaoImpl.java - created by Sakai App Builder -AZ
 * 
 * Copyright (c) 2006 Sakai Project/Sakai Foundation
 * Licensed under the Educational Community License version 1.0
 * 
 * A copy of the Educational Community License has been included in this 
 * distribution and is available at: http://www.opensource.org/licenses/ecl1.php
 * 
 *****************************************************************************/

package org.sakaiproject.crudplus.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.sakaiproject.genericdao.hibernate.HibernateCompleteGenericDao;

import org.sakaiproject.crudplus.dao.CrudPlusDao;

/**
 * Implementations of any specialized DAO methods from the specialized DAO 
 * that allows the developer to extend the functionality of the generic dao package
 * @author Sakai App Builder -AZ
 */
public class CrudPlusDaoImpl 
	extends HibernateCompleteGenericDao 
		implements CrudPlusDao {

	private static Log log = LogFactory.getLog(CrudPlusDaoImpl.class);

	public void init() {
	}

}
