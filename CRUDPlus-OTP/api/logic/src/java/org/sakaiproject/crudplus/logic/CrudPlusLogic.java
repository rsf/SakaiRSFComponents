/******************************************************************************
 * CrudPlusLogic.java - created by Sakai App Builder -AZ
 * 
 * Copyright (c) 2006 Sakai Project/Sakai Foundation
 * Licensed under the Educational Community License version 1.0
 * 
 * A copy of the Educational Community License has been included in this 
 * distribution and is available at: http://www.opensource.org/licenses/ecl1.php
 * 
 *****************************************************************************/

package org.sakaiproject.crudplus.logic;

import java.util.List;

import org.sakaiproject.crudplus.model.CrudPlusItem;

/**
 * This is the interface for the app Logic, 
 * @author Sakai App Builder -AZ
 */
public interface CrudPlusLogic {

	/**
	 * This returns an item based on an id
	 * @param id the id of the item to fetch
	 * @return a CrudPlusItem or null if none found
	 */
	public CrudPlusItem getItemById(Long id);

	/**
	 * Check if the current user can write this item in the current site
	 * @param item to be modifed or removed
	 * @return true if item can be modified, false otherwise
	 */
	public boolean canWriteItem(CrudPlusItem item);

	/**
	 * Check if a specified user can write this item in a specified site
	 * @param item to be modified or removed
	 * @param siteId the Sakai id of the site
	 * @param userId the Sakai id of the user
	 * @return true if item can be modified, false otherwise
	 */
	public boolean canWriteItem(CrudPlusItem item, String siteId, String userId);

	/**
	 * This returns a List of items for the current site that are
	 * visible to the current user
	 * @return a List of CrudPlusItem objects
	 */
	public List<CrudPlusItem> getAllVisibleItems();

	/**
	 * This returns a List of items for a specified site that are
	 * visible to the specified user
	 * @param siteId siteId of the site
	 * @param userId the Sakai id of the user
	 * @return a List of CrudPlusItem objects
	 */
	public List<CrudPlusItem> getAllVisibleItems(String siteId, String userId);

	/**
	 * Save (Create or Update) an item (uses the current site)
	 * @param item the CrudPlusItem to create or update
	 */
	public void saveItem(CrudPlusItem item);

	/**
	 * Remove an item
	 * @param item the CrudPlusItem to remove
	 */
	public void removeItem(CrudPlusItem item);

	/**
	 * Get the display name for the current user
	 * @return display name (probably firstname lastname)
	 */
	public String getCurrentUserDisplayName();
}
