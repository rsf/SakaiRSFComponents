/******************************************************************************
 * CrudPlusBean.java - created by Sakai App Builder -AZ
 * 
 * Copyright (c) 2006 Sakai Project/Sakai Foundation
 * Licensed under the Educational Community License version 1.0
 * 
 * A copy of the Educational Community License has been included in this 
 * distribution and is available at: http://www.opensource.org/licenses/ecl1.php
 * 
 *****************************************************************************/

package org.sakaiproject.crudplus.tool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.crudplus.logic.CrudPlusLogic;
import org.sakaiproject.crudplus.model.CrudPlusItem;

import uk.org.ponder.beanutil.entity.EntityBeanLocator;
import uk.org.ponder.messageutil.TargettedMessage;
import uk.org.ponder.messageutil.TargettedMessageList;

/**
 * This is the backing bean for actions related to CrudPlusItems
 * 
 * @author Sakai App Builder -AZ
 */
public class ItemsBean {
  private static Log log = LogFactory.getLog(ItemsBean.class);

  private Map<String, CrudPlusItem> OTPMap;

  public Map selectedIds = new HashMap();

  private CrudPlusLogic logic;

  public void setEntityBeanLocator(EntityBeanLocator entityBeanLocator) {
    this.OTPMap = entityBeanLocator.getDeliveredBeans();
  }

  public void setLogic(CrudPlusLogic logic) {
    this.logic = logic;
  }

  private TargettedMessageList messages;

  public void setMessages(TargettedMessageList messages) {
    this.messages = messages;
  }

  public void init() {
    log.debug("init");
  }

  public ItemsBean() {
    log.debug("constructor");
  }

  public String processActionAdd() {
    for (String key: OTPMap.keySet()) {
      CrudPlusItem item = OTPMap.get(key);
      if (key.startsWith(EntityBeanLocator.NEW_PREFIX)) {
        messages.addMessage(new TargettedMessage("item_added",
            new Object[] { item.getTitle() }, TargettedMessage.SEVERITY_INFO));
      }
      else {
        messages.addMessage(new TargettedMessage("item_updated",
            new Object[] { item.getTitle() }, TargettedMessage.SEVERITY_INFO));
      }
      logic.saveItem(item);
    }
    return "added";
  }

  public String processActionDelete() {
    log.debug("in process action delete...");
    List<CrudPlusItem> items = logic.getAllVisibleItems();
    int itemsRemoved = 0;
    for (CrudPlusItem item: items) {
      log.debug("Checking to remove item:" + item.getId());
      if (selectedIds.get(item.getId().toString()) == Boolean.TRUE) {
        logic.removeItem(item);
        itemsRemoved++;
        log.debug("Removing item:" + item.getId());
      }
    }
    messages.addMessage(new TargettedMessage("items_removed",
        new Object[] { new Integer(itemsRemoved) },
        TargettedMessage.SEVERITY_INFO));
    return "deleteItems";
  }

}
