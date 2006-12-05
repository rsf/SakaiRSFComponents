/******************************************************************************
 * PreloadDataImpl.java - created by Sakai App Builder -AZ
 * 
 * Copyright (c) 2006 Sakai Project/Sakai Foundation
 * Licensed under the Educational Community License version 1.0
 * 
 * A copy of the Educational Community License has been included in this 
 * distribution and is available at: http://www.opensource.org/licenses/ecl1.php
 * 
 *****************************************************************************/

package org.sakaiproject.crudplus.dao.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.sakaiproject.crudplus.dao.CrudPlusDao;
import org.sakaiproject.crudplus.model.CrudPlusItem;

/**
 * This checks and preloads any data that is needed for this app
 * @author Sakai App Builder -AZ
 */
public class PreloadDataImpl {

	private static Log log = LogFactory.getLog(PreloadDataImpl.class);

	private CrudPlusDao dao;
	public void setDao(CrudPlusDao dao) {
		this.dao = dao;
	}

	public void init() {
		preloadItems();
	}

	/**
	 * Preload some items into the database
	 */
	public void preloadItems() {

		// check if there are any items present, load some if not
		if(dao.findAll(CrudPlusItem.class).isEmpty()){

			// use the dao to preload some data here
			dao.save( new CrudPlusItem("Preload Title", 
					"Preload Owner", "Preload Site", Boolean.TRUE, new Date()) );

			log.info("Preloaded " + dao.countAll(CrudPlusItem.class) + " items");
		}
	}
}
