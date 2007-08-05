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
 * There are 3 METHODs to trigger an action in this bean using RSF and UVB,
 * each method is explained in the comments in the source for this file
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
	/**
	 * Set the search value and call the handleAjaxCall method
	 * METHOD 2<br/>
	 * You could also trigger this method when each value is inserted into this bean 
	 * by the RSF EL by defining a setter which calls the method as shown in the commented out code below<br/>
	 * WARNING: You do not need to write a setter here if you are not intending to trigger the method,
	 * hence the setSearch is currently commented out (RSF can set public fields without a setter)
	 */
//	public void setSearch(String search) {
//		this.search = search;
//		handleAjaxCall();
//	}


	private String[] results;
	public String[] getResults() {
		return results;
	}

	/**
	 * Runs when this bean is initialized by spring
	 * <br/>
	 * METHOD 3<br/>
	 * Simply execute this method whenever this bean is initialized,
	 * this will be initialized by the UVB whenever there is an AJAX request related to it
	 * (defined by the "myAjaxBean.results" elBinding value which is passed into the Javascript
	 * by the producer using an emitJavascriptCall method)<br/>
	 * WARNING: This method should really only be used if you are passing no values
	 * in your AJAX request (and hence doing no writes to this bean) 
	 */
	public void init() {
		if (log.isDebugEnabled()) log.debug("INIT");
//		handleAjaxCall();
	}

	/**
	 * This method executes some code when it is triggered by the AJAX request
	 * <br/>
	 * METHOD 1 (Currently used method)<br/>
	 * the handleAjaxCall method is triggered by the ajaxExecuter writeGuard
	 * defined in the applicationContext whenever any of the fields are updated.
	 * <xmp><property name="guardedPath" value="myAjaxBean.*" /></xmp> - 
	 * this defines the property writes which should trigger the method
	 * (the .* means writes to any field of the bean with id myAjaxBean)
	 * <xmp><property name="guardMethod" value="myAjaxBean.handleAjaxCall" /></xmp> -
	 * this defines the method which should be executed when there is a write
	 */
	public void handleAjaxCall() {
		// NOTE: if you have multiple inputs and are using METHOD 1 or 2, you will need to ensure that you check here 
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
