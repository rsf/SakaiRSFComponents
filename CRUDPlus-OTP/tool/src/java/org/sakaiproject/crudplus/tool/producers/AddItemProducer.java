/******************************************************************************
 * AddItemProducer.java - created by Sakai App Builder -AZ
 * 
 * Copyright (c) 2006 Sakai Project/Sakai Foundation
 * Licensed under the Educational Community License version 1.0
 * 
 * A copy of the Educational Community License has been included in this 
 * distribution and is available at: http://www.opensource.org/licenses/ecl1.php
 * 
 *****************************************************************************/

package org.sakaiproject.crudplus.tool.producers;

import java.util.List;

import org.sakaiproject.crudplus.logic.CrudPlusLogic;
import org.sakaiproject.crudplus.model.CrudPlusItem;
import org.sakaiproject.crudplus.tool.CrudPlusItemCreator;
import org.sakaiproject.crudplus.tool.params.AddItemViewParameters;

import uk.org.ponder.arrayutil.ListUtil;
import uk.org.ponder.rsf.components.UIBoundBoolean;
import uk.org.ponder.rsf.components.UICommand;
import uk.org.ponder.rsf.components.UIContainer;
import uk.org.ponder.rsf.components.UIForm;
import uk.org.ponder.rsf.components.UIInput;
import uk.org.ponder.rsf.components.UIInternalLink;
import uk.org.ponder.rsf.components.UIOutput;
import uk.org.ponder.rsf.flow.jsfnav.NavigationCase;
import uk.org.ponder.rsf.flow.jsfnav.NavigationCaseReporter;
import uk.org.ponder.rsf.view.ComponentChecker;
import uk.org.ponder.rsf.view.ViewComponentProducer;
import uk.org.ponder.rsf.viewstate.SimpleViewParameters;
import uk.org.ponder.rsf.viewstate.ViewParameters;
import uk.org.ponder.rsf.viewstate.ViewParamsReporter;

/**
 * This is the view producer for the AddItem html template
 * 
 * @author Sakai App Builder -AZ
 */
public class AddItemProducer implements ViewComponentProducer,
    NavigationCaseReporter, ViewParamsReporter {

  // The VIEW_ID must match the html template (without the .html)
  public static final String VIEW_ID = "AddItem";

  public String getViewID() {
    return VIEW_ID;
  }

  private CrudPlusLogic logic;

  public void setLogic(CrudPlusLogic logic) {
    this.logic = logic;
  }

  public void fillComponents(UIContainer tofill, ViewParameters viewparams,
      ComponentChecker checker) {

    UIInternalLink.make(tofill, "list-items", new SimpleViewParameters(
        ItemsProducer.VIEW_ID));

    UIOutput.make(tofill, "current-user-name", logic
        .getCurrentUserDisplayName());

    UIForm addupdateitem = UIForm.make(tofill, "addUpdateItemForm");

    AddItemViewParameters aivp = (AddItemViewParameters) viewparams;
    CrudPlusItem item = null;
    if (aivp.id != null) {
      // passed in an id so we should be modifying an item if we can find it
      item = logic.getItemById(aivp.id);
    }

    if (item == null) {
      // we are creating a new item
      UIInput.make(addupdateitem, "item-title", "CrudPlusItem.new 1.title",
          CrudPlusItemCreator.DEFAULT_TITLE);
      UIBoundBoolean.make(addupdateitem, "item-hidden",
          "CrudPlusItem.new 1.hidden", CrudPlusItemCreator.DEFAULT_HIDDEN);
    }
    else {
      // we are modifying an existing item
      String basepath = "CrudPlusItem." + item.getId() + ".";
      UIInput.make(addupdateitem, "item-title", basepath + "title");
      UIBoundBoolean.make(addupdateitem, "item-hidden", basepath + "hidden");
    }
    UICommand.make(addupdateitem, "add-update-item",
        "#{itemsBean.processActionAdd}");
  }

  public List reportNavigationCases() {
    return ListUtil.instance(new NavigationCase("added", new SimpleViewParameters(
        ItemsProducer.VIEW_ID)));
  }

  public ViewParameters getViewParameters() {
    return new AddItemViewParameters();
  }
}
