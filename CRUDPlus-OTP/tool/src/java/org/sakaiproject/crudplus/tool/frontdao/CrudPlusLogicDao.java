/*
 * Created on Dec 3, 2006
 */
package org.sakaiproject.crudplus.tool.frontdao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.sakaiproject.crudplus.logic.CrudPlusLogic;
import org.sakaiproject.crudplus.model.CrudPlusItem;
import org.sakaiproject.crudplus.tool.CrudPlusItemCreator;
import org.sakaiproject.genericdao.api.InitializingCoreGenericDAO;


public class CrudPlusLogicDao implements InitializingCoreGenericDAO {
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
    // Do not implement this for this application
    // Existing DAO methods are already adequately transactional
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

  public Object instantiate() {
    return CrudPlusItemCreator.create();
  }


}
