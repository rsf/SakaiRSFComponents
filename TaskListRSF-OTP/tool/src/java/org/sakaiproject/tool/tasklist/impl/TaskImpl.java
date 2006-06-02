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

import java.util.Date;

import org.sakaiproject.tool.tasklist.api.Task;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class TaskImpl implements Task {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String owner;
	private String siteId;
	private Date creationDate;
	private String task;
	
	public TaskImpl() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	/*
	 * Basic comparison functions for objects
	 * Uses commons-lang to make it so we can be sure about comparisons as long
	 * as the data in the object is the same
	 */

	public boolean equals(Object obj) {
    	if (obj == null) return false;
		if (obj instanceof Task == false) return false;
		if (this == obj) return true;
		Task castObj = (Task) obj;
		return new EqualsBuilder()
			.append(this.getId(), castObj.getId())
			.append(this.getOwner(), castObj.getOwner())
			.append(this.getSiteId(), castObj.getSiteId())
			.isEquals();
    }

    public int hashCode() {
    	// pick 2 hard-coded, odd, >0 ints as args
    	return new HashCodeBuilder(1, 31)
    		.append(this.id)
    		.append(this.owner)
    		.append(this.siteId)
    		.toHashCode();
	}

	public int compareTo(Object obj) {
		Task castObj = (Task) obj;
		return new CompareToBuilder()
			.append(this.getId(), castObj.getId())
			.append(this.getOwner(), castObj.getOwner())
			.toComparison();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append(this.id)
			.append(this.owner)
			.append(this.siteId)
			.append(this.creationDate)
			.append(this.task)
			.toString();
	}


	/*
	 * Lightweight comparison functions, these do not use
	 * commons-lang but they are also not as safe when objects
	 * are copies (contain the same data) but are not the same object
	 */
/**
	public boolean equals(Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof Task)) return false;
		else {
			Task castObj = (Task) obj;
			if (null == this.getId() || null == castObj.getId()) return false;
			else return (this.getId().equals(castObj.getId()));
		}
	}

	public int hashCode() {
		if (null == this.getId()) return Integer.MIN_VALUE;
		String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
		return hashStr.hashCode();
	}

	public int compareTo(Object obj) {
		if (obj.hashCode() > hashCode()) return 1;
		else if (obj.hashCode() < hashCode()) return -1;
		else return 0;
	}

	public String toString() {
		return super.toString();
	}
**/
}
