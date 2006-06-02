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

package org.sakaiproject.tool.tasklist.api;

import java.util.Date;

/**
 * @author az
 * This is the interface for our value object that holds a task
 * It is a basic POJO with only getters and setters
 */
public interface Task extends java.io.Serializable {

	public Long getId();
	
	public void setId(Long id);
	
	public String getOwner();
	
	public void setOwner(String owner);
	
	public String getSiteId();
	
	public void setSiteId(String siteId);
	
	public Date getCreationDate();
	
	public void setCreationDate(Date creationDate);
	
	public String getTask();
	
	public void setTask(String task);
}
