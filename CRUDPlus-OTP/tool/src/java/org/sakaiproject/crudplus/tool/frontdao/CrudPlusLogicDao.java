/*
 * Created on Dec 3, 2006
 */
package org.sakaiproject.crudplus.tool.frontdao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.sakaiproject.crudplus.logic.CrudPlusLogic;
import org.sakaiproject.crudplus.model.CrudPlusItem;
import org.sakaiproject.genericdao.api.CoreGenericDao;

public class CrudPlusLogicDao implements CoreGenericDao {
  private CrudPlusLogic crudPlusLogic;

  public void setCrudPlusLogic(CrudPlusLogic crudPlusLogic) {
    this.crudPlusLogic = crudPlusLogic;
  }

  public boolean delete(Class entityClass, Serializable id) {
    CrudPlusItem item = crudPlusLogic.getItemById((Long) id);
    if (item != null) {
      crudPlusLogic.removeItem(item);
      return true;
    }
    else {
      return false;
    }
  }
  public void invokeTransactionalAccess(Runnable toinvoke) {
    
  }

  public void save(Object object) {
    crudPlusLogic.saveItem((CrudPlusItem) object);
  }
  
  public Object findById(Class entityClass, Serializable id) {
    return crudPlusLogic.getItemById((Long) id);
  }

  public String getIdProperty(Class entityClass) {
    return "id";
  }

  public List getPersistentClasses() {
    return Arrays.asList(new Class[] {CrudPlusItem.class});
  }


}
