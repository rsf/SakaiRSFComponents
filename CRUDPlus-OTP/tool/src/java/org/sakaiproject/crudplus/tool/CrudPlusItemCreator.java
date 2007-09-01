/*
 * Created on Dec 3, 2006
 */
package org.sakaiproject.crudplus.tool;

import org.sakaiproject.crudplus.model.CrudPlusItem;

public class CrudPlusItemCreator {

  public static final Boolean DEFAULT_HIDDEN = Boolean.TRUE;
  public static final String DEFAULT_TITLE = "";
  public CrudPlusItem create() {
    CrudPlusItem togo = new CrudPlusItem();
    togo.setHidden(DEFAULT_HIDDEN);
    return togo;
  }
}
