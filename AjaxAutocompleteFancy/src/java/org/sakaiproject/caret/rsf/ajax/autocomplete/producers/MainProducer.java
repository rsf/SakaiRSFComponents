/**
 * MainProducer.java - 2007 Jul 13, 2007 3:14:06 PM - AZ
 */

package org.sakaiproject.caret.rsf.ajax.autocomplete.producers;

import uk.org.ponder.htmlutil.HTMLUtil;
import uk.org.ponder.rsf.builtin.UVBProducer;
import uk.org.ponder.rsf.components.UIContainer;
import uk.org.ponder.rsf.components.UIForm;
import uk.org.ponder.rsf.components.UIInput;
import uk.org.ponder.rsf.components.UIMessage;
import uk.org.ponder.rsf.components.UIVerbatim;
import uk.org.ponder.rsf.view.ComponentChecker;
import uk.org.ponder.rsf.view.DefaultView;
import uk.org.ponder.rsf.view.ViewComponentProducer;
import uk.org.ponder.rsf.viewstate.SimpleViewParameters;
import uk.org.ponder.rsf.viewstate.ViewParameters;


/**
 * Main View for the app (pretty much the only view)
 * 
 * @author Aaron Zeckoski (aaronz@caret.cam.ac.uk)
 */
public class MainProducer implements ViewComponentProducer, DefaultView {

	public static final String VIEW_ID = "main";
	public String getViewID() {
		return VIEW_ID;
	}

	public void fillComponents(UIContainer tofill, ViewParameters origviewparams, ComponentChecker checker) {
		// put in the title of the page (using UIMessage to internationalize)
		UIMessage.make(tofill, "page_title", "main.page.title");
		// put in the message on the page (also i18n)
		UIMessage.make(tofill, "message_field", "main.hello.message");
		// put in the instructions on the page (also i18n)
		UIMessage.make(tofill, "input_instructions", "main.input.instructions");

		// now do the bindings to actually do some functional ajax stuff
		UIForm form = UIForm.make(tofill, "input_form");
        // this puts the URL to the UVB producer in the form so we can get to it
		form.viewparams = new SimpleViewParameters(UVBProducer.VIEW_ID);
		UIInput inputfield = UIInput.make(form, "input_field", "myAjaxBean.search");
		UIVerbatim.make(tofill, "init_js", 
				HTMLUtil.emitJavascriptCall("AjaxAutoComplete.initAutocomplete", 
						new String[] {inputfield.getFullID(), "myAjaxBean.results"}) ); //, viewStateHandler.getFullURL(uvbparams)
	}

}
