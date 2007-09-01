/******************************************************************************
 * CrudPlusLogicImpl.java - created by Sakai App Builder -AZ
 * 
 * Copyright (c) 2006 Sakai Project/Sakai Foundation
 * Licensed under the Educational Community License version 1.0
 * 
 * A copy of the Educational Community License has been included in this 
 * distribution and is available at: http://www.opensource.org/licenses/ecl1.php
 * 
 *****************************************************************************/

package org.sakaiproject.crudplus.logic.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.authz.api.AuthzGroup;
import org.sakaiproject.authz.api.AuthzGroupService;
import org.sakaiproject.authz.api.AuthzPermissionException;
import org.sakaiproject.authz.api.FunctionManager;
import org.sakaiproject.authz.api.GroupNotDefinedException;
import org.sakaiproject.authz.api.Role;
import org.sakaiproject.authz.api.SecurityService;
import org.sakaiproject.crudplus.dao.CrudPlusDao;
import org.sakaiproject.crudplus.logic.CrudPlusLogic;
import org.sakaiproject.crudplus.model.CrudPlusItem;
import org.sakaiproject.site.api.SiteService;
import org.sakaiproject.thread_local.cover.ThreadLocalManager;
import org.sakaiproject.tool.api.Session;
import org.sakaiproject.tool.api.SessionManager;
import org.sakaiproject.tool.api.ToolManager;
import org.sakaiproject.user.api.UserDirectoryService;

/**
 * This is the implementation of the business logic interface
 * @author Sakai App Builder -AZ
 */
public class CrudPlusLogicImpl implements CrudPlusLogic {

	private static Log log = LogFactory.getLog(CrudPlusLogicImpl.class);

	public final static String ITEM_WRITE_ANY = "crudplus.write.any";
	public final static String ITEM_READ_HIDDEN = "crudplus.read.hidden";

	private final static String SITE_TEMPLATE = "!site.template";
	private final static String COURSE_TEMPLATE = "!site.template.course";

	private CrudPlusDao dao;
	public void setDao(CrudPlusDao dao) {
		this.dao = dao;
	}

	private AuthzGroupService authzGroupService;
	public void setAuthzGroupService(AuthzGroupService authzGroupService) {
		this.authzGroupService = authzGroupService;
	}

	private FunctionManager functionManager;
	public void setFunctionManager(FunctionManager functionManager) {
		this.functionManager = functionManager;
	}

	private SecurityService securityService;
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

	private SessionManager sessionManager;
	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	private SiteService siteService;
	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}

	private ToolManager toolManager;
	public void setToolManager(ToolManager toolManager) {
		this.toolManager = toolManager;
	}

	private UserDirectoryService userDirectoryService;
	public void setUserDirectoryService(UserDirectoryService userDirectoryService) {
		this.userDirectoryService = userDirectoryService;
	}

	/**
	 * Place any code that should run when this class is initialized by spring here
	 */
	public void init() {
		log.debug("init");
		// register Sakai permissions for this tool
		functionManager.registerFunction(ITEM_WRITE_ANY);
		functionManager.registerFunction(ITEM_READ_HIDDEN);

		// add the permissions to the maintain role of new sites by default
		try {
			// setup the admin user so we can do the stuff below
			Session s = sessionManager.getCurrentSession();
			if (s != null) {
				s.setUserId("admin");
			} else {
				log.warn("no CurrentSession, cannot set to admin user");
			}
			AuthzGroup ag = authzGroupService.getAuthzGroup(SITE_TEMPLATE);
			if (authzGroupService.allowUpdate(ag.getId())) {
				Role r = ag.getRole(ag.getMaintainRole());
				r.allowFunction(ITEM_READ_HIDDEN);
				r.allowFunction(ITEM_WRITE_ANY);
				authzGroupService.save(ag);
				log.info("Added Permissions to group:" + SITE_TEMPLATE);
			} else {
				log.warn("Cannot update authz group: " + SITE_TEMPLATE);
			}
			ag = authzGroupService.getAuthzGroup(COURSE_TEMPLATE);
			if (authzGroupService.allowUpdate(ag.getId())) {
				Role r = ag.getRole(ag.getMaintainRole());
				r.allowFunction(ITEM_READ_HIDDEN);
				r.allowFunction(ITEM_WRITE_ANY);
				authzGroupService.save(ag);
				log.info("Added Permissions to group:" + COURSE_TEMPLATE);
			} else {
				log.warn("Cannot update authz group: " + COURSE_TEMPLATE);
			}
		} catch (GroupNotDefinedException e) {
			log.error("Could not find group: " + SITE_TEMPLATE + ", default perms will not be assigned");
		} catch (AuthzPermissionException e) {
			log.error("Could not save group: " + SITE_TEMPLATE);
		} finally {
			// wipe out the admin session
			ThreadLocalManager.clear();
		}
	}

	/* (non-Javadoc)
	 * @see org.sakaiproject.crudplus.logic.CrudPlusLogic#getItemById(java.lang.Long)
	 */
	public CrudPlusItem getItemById(Long id) {
		log.debug("Getting item by id: " + id);
		return (CrudPlusItem) dao.findById(CrudPlusItem.class, id);
	}

	/* (non-Javadoc)
	 * @see org.sakaiproject.crudplus.logic.CrudPlusLogic#canWriteItem(org.sakaiproject.crudplus.model.CrudPlusItem)
	 */
	public boolean canWriteItem(CrudPlusItem item) {
		return canWriteItem(item, getCurrentContext(), getCurrentUserId() );
	}

	/* (non-Javadoc)
	 * @see org.sakaiproject.crudplus.logic.CrudPlusLogic#canWriteItem(org.sakaiproject.crudplus.model.CrudPlusItem, java.lang.String, java.lang.String)
	 */
	public boolean canWriteItem(CrudPlusItem item, String siteId, String userId) {
		log.debug("checking if can write for: " + userId + ", " + siteId + ": and item=" + item.getTitle() );
		String siteRef = siteService.siteReference(siteId);
		if (item.getOwnerId().equals( userId ) ) {
			// owner can always modify an item
			return true;
		} else if ( securityService.isSuperUser(userId) ) {
			// the system super user can modify any item
			return true;
		} else if ( siteId.equals(item.getSiteId()) &&
				securityService.unlock(userId, ITEM_WRITE_ANY, siteRef) ) {
			// users with permission in the specified site can modify items from that site
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.sakaiproject.crudplus.logic.CrudPlusLogic#getAllVisibleItems()
	 */
	public List<CrudPlusItem> getAllVisibleItems() {
		return getAllVisibleItems( getCurrentContext(), getCurrentUserId() );
	}

	/* (non-Javadoc)
	 * @see org.sakaiproject.crudplus.logic.CrudPlusLogic#getAllVisibleItems(java.lang.String, java.lang.String)
	 */
	public List<CrudPlusItem> getAllVisibleItems(String siteId, String userId) {
		log.debug("Fetching visible items for " + userId + " in site: " + siteId);
		String siteRef = siteService.siteReference( siteId );
		List<CrudPlusItem> l = dao.findByProperties(CrudPlusItem.class, 
				new String[] {"siteId"}, new Object[] {siteId});
		// check if the current user can see all items (or is super user)
		if ( securityService.isSuperUser(userId) || 
				securityService.unlock(userId, ITEM_READ_HIDDEN, siteRef) ) {
			log.debug("Security override: " + userId + " able to view all items");
		} else {
			// go backwards through the loop to avoid hitting the "end" early
			for (int i=l.size()-1; i >= 0; i--) {
				CrudPlusItem item = l.get(i);
				if ( item.getHidden().booleanValue() &&
						!item.getOwnerId().equals(userId) ) {
					l.remove(item);
				}
			}
		}
		return l;
	}

	/* (non-Javadoc)
	 * @see org.sakaiproject.crudplus.logic.CrudPlusLogic#removeItem(org.sakaiproject.crudplus.model.CrudPlusItem)
	 */
	public void removeItem(CrudPlusItem item) {
		log.debug("In removeItem with item:" + item.getId() + ":" + item.getTitle());
		// check if current user can remove this item
		if ( canWriteItem(item) ) {
			dao.delete(item);
			log.info("Removing item: " + item.getId() + ":" + item.getTitle());
		} else {
			throw new SecurityException("Current user cannot remove item " + 
					item.getId() + " because they do not have permission");
		}
	}

	/* (non-Javadoc)
	 * @see org.sakaiproject.crudplus.logic.CrudPlusLogic#saveItem(org.sakaiproject.crudplus.model.CrudPlusItem)
	 */
	public void saveItem(CrudPlusItem item) {
		log.debug("In saveItem with item:" + item.getTitle());
		// set the owner and site to current if they are not set
		if (item.getOwnerId() == null) {
			item.setOwnerId( getCurrentUserId() );
		}
		if (item.getSiteId() == null) {
			item.setSiteId( getCurrentContext() );
		}
		if (item.getDateCreated() == null) {
			item.setDateCreated( new Date() );
		}
		// save item if new OR check if the current user can update the existing item
		if ( (item.getId() == null) || canWriteItem(item) ) {
			dao.save(item);
			log.info("Saving item: " + item.getId() + ":" + item.getTitle());
		} else {
			throw new SecurityException("Current user cannot update item " + 
					item.getId() + " because they do not have permission");
		}
	}

	/* (non-Javadoc)
	 * @see org.sakaiproject.crudplus.logic.CrudPlusLogic#getCurrentUserDisplayName()
	 */
	public String getCurrentUserDisplayName() {
		return userDirectoryService.getCurrentUser().getDisplayName();
	}

	/**
	 * Get the context of the location the current user is in
	 * <br/>Note: This is only public so it can be tested and should not be used outside the impl
	 * @return a context
	 */
	public String getCurrentContext() {
		return toolManager.getCurrentPlacement().getContext();
	}

	/**
	 * Get the internal Sakai userId of the current user
	 * <br/>Note: This is only public so it can be tested and should not be used outside the impl
	 * @return a userId (not necessarily username)
	 */
	public String getCurrentUserId() {
		return userDirectoryService.getCurrentUser().getId();
	}

}
