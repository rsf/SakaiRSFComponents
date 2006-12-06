/*
 * Created on Dec 3, 2006
 */
package org.sakaiproject.crudplus.tool;

import org.sakaiproject.crudplus.model.CrudPlusItem;

public class CrudPlusItemValidator  {
  public void setItem(CrudPlusItem newItem) {
  if (newItem.getTitle() == null || newItem.getTitle().equals("")) {
      throw new IllegalArgumentException("title_required");
    }
  }
}
