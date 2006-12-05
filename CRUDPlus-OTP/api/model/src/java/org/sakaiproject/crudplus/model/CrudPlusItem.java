/******************************************************************************
 * CrudPlusItem.java - created by Sakai App Builder -AZ
 * 
 * Copyright (c) 2006 Sakai Project/Sakai Foundation
 * Licensed under the Educational Community License version 1.0
 * 
 * A copy of the Educational Community License has been included in this 
 * distribution and is available at: http://www.opensource.org/licenses/ecl1.php
 * 
 *****************************************************************************/

package org.sakaiproject.crudplus.model;

import java.util.Date;

/**
 * This is a sample POJO (data storage object) 
 * @author Sakai App Builder -AZ
 */
public class CrudPlusItem {
	
	private Long id;
	private String title;
	private String ownerId; // Sakai userId
	private String siteId; // Sakai siteId
	private Boolean hidden; // only visible to owner if true
	private Date dateCreated;

	/**
	 * Default constructor
	 */
	public CrudPlusItem() {
	}

	/**
	 * Minimal constructor
	 */
	public CrudPlusItem(String title,
			String ownerId, String siteId) {
		this.title = title;
		this.ownerId = ownerId;
		this.siteId = siteId;
	}

	/**
	 * Full constructor
	 */
	public CrudPlusItem(String title,
			String ownerId, String siteId,
			Boolean hidden, Date dateCreated) {
		this.title = title;
		this.ownerId = ownerId;
		this.siteId = siteId;
		this.hidden = hidden;
		this.dateCreated = dateCreated;
	}

	/**
	 * Getters and Setters
	 */
	public Boolean getHidden() {
		return hidden;
	}
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

}
