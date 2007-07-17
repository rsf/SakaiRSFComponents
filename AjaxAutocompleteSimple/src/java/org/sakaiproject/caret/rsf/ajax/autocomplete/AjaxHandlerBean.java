/**
 * AjaxHandlerBean.java - 2007 Jul 13, 2007 4:14:06 PM - AZ
 */

package org.sakaiproject.caret.rsf.ajax.autocomplete;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.caret.DummySearchService;


/**
 * My bean which handles the AJAX processing
 * 
 * @author Aaron Zeckoski (aaronz@caret.cam.ac.uk)
 */
public class AjaxHandlerBean {

	private static Log log = LogFactory.getLog(AjaxHandlerBean.class);

	private DummySearchService searchService;
	public void setSearchService(DummySearchService searchService) {
		this.searchService = searchService;
	}

	public String search;
/*
 * Currently the handleAjaxCall method is triggered by the ajaxExecuter writeGuard
 * defined in the applicationContext, you could also trigger this method when each
 * value is inserted into this bean by the RSF EL as shown in the commented out code below
 */
//	public void setSearch(String search) {
//		this.search = search;
//		handleAjaxCall();
//	}
	

	private String[] results;
	public String[] getResults() {
		return results;
	}

	public void handleAjaxCall() {
		// NOTE: if you have multiple inputs, you will need to ensure that you check here 
		// to see if all inputs have been populated yet since they are inserted one at a time
		if (search == null || search.equals("")) {
			// if blank or empty then return an empty array
			log.warn("No search or blank search received...");
			results = new String[] {};
		} else {
			// call over to the search service
			results = searchService.doSearch(search);
		}
	}

}
