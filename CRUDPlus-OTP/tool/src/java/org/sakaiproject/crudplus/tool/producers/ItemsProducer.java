/******************************************************************************
 * ItemsProducer.java - created by Sakai App Builder -AZ
 * 
 * Copyright (c) 2006 Sakai Project/Sakai Foundation
 * Licensed under the Educational Community License version 1.0
 * 
 * A copy of the Educational Community License has been included in this 
 * distribution and is available at: http://www.opensource.org/licenses/ecl1.php
 * 
 *****************************************************************************/

package org.sakaiproject.crudplus.tool.producers;

import java.text.DateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.sakaiproject.crudplus.logic.CrudPlusLogic;
import org.sakaiproject.crudplus.model.CrudPlusItem;
import org.sakaiproject.crudplus.tool.params.AddItemViewParameters;

import uk.org.ponder.rsf.components.UIBoundBoolean;
import uk.org.ponder.rsf.components.UIBranchContainer;
import uk.org.ponder.rsf.components.UICommand;
import uk.org.ponder.rsf.components.UIContainer;
import uk.org.ponder.rsf.components.UIForm;
import uk.org.ponder.rsf.components.UIInternalLink;
import uk.org.ponder.rsf.components.UIOutput;
import uk.org.ponder.rsf.view.ComponentChecker;
import uk.org.ponder.rsf.view.DefaultView;
import uk.org.ponder.rsf.view.ViewComponentProducer;
import uk.org.ponder.rsf.viewstate.ViewParameters;

/**
 * This is the view producer for the Items html template
 * @author Sakai App Builder -AZ
 */
public class ItemsProducer implements ViewComponentProducer, DefaultView {

	// The VIEW_ID must match the html template (without the .html)
	public static final String VIEW_ID = "Items";
	public String getViewID() {
		return VIEW_ID;
	}

	private CrudPlusLogic logic;
	public void setLogic(CrudPlusLogic logic) {
		this.logic = logic;
	}

	private Locale locale;
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public void fillComponents(UIContainer tofill, ViewParameters viewparams,
			ComponentChecker checker) {

		UIInternalLink.make(tofill, "create-item", 
				new AddItemViewParameters(AddItemProducer.VIEW_ID, null) );

		UIOutput.make(tofill, "current-user-name", logic
				.getCurrentUserDisplayName());

		UIForm listform = UIForm.make(tofill, "listItemsForm");

		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.SHORT, locale);

		List l = logic.getAllVisibleItems();
		for (Iterator iter = l.iterator(); iter.hasNext();) {
			CrudPlusItem item = (CrudPlusItem) iter.next();
			UIBranchContainer itemrow = UIBranchContainer.make(listform, 
					"item-row:", item.getId().toString() );
			if (logic.canWriteItem(item)) { // check for current user and site
				UIBoundBoolean.make(itemrow, "select-item", 
						"itemsBean.selectedIds." + item.getId(), Boolean.FALSE);
				UIInternalLink.make(itemrow, "item-update", item.getTitle(), 
						new AddItemViewParameters(AddItemProducer.VIEW_ID, item.getId()) );
			} else {
				UIOutput.make(itemrow, "item-title", item.getTitle() );
			}
			UIBoundBoolean.make(itemrow, "item-hidden", item.getHidden() );
			UIOutput.make(itemrow, "item-dateCreated", df.format(item.getDateCreated()) );
		}

		UICommand.make(listform, "delete-items", "itemsBean.processActionDelete");
	}

}
