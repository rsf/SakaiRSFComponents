/******************************************************************************
 * AddItemViewParameters.java - created by Sakai App Builder -AZ
 * 
 * Copyright (c) 2006 Sakai Project/Sakai Foundation
 * Licensed under the Educational Community License version 1.0
 * 
 * A copy of the Educational Community License has been included in this 
 * distribution and is available at: http://www.opensource.org/licenses/ecl1.php
 * 
 *****************************************************************************/

package org.sakaiproject.crudplus.tool.params;

import uk.org.ponder.rsf.viewstate.SimpleViewParameters;

/**
 * This is a view parameters class which defines the variables that are
 * passed from one page to another
 * @author Sakai App Builder -AZ
 */
public class AddItemViewParameters extends SimpleViewParameters {

	public Long id; // an identifier for an item

	/**
	 * Basic empty constructor
	 */
	public AddItemViewParameters() {
	}

	/**
	 * Constructor for a ViewParameters object for Adding Items
	 * @param viewID the target view for these parameters
	 * @param id a unique identifier for a CrudPlusItem object
	 */
	public AddItemViewParameters(String viewID, Long id) {
		this.id = id;
		this.viewID = viewID;
	}

	/* (non-Javadoc)
	 * @see uk.org.ponder.rsf.viewstate.ViewParameters#getParseSpec()
	 */
	public String getParseSpec() {
		// include a comma delimited list of the public properties in this class
		return super.getParseSpec() + ",id";
	}
}
