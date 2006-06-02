/*
 * Created on May 29, 2006
 */
package org.sakaiproject.tool.tasklist.rsf;


import java.util.Date;
import java.util.Map;

import org.sakaiproject.tool.tasklist.api.Task;
import org.sakaiproject.tool.tasklist.api.TaskListManager;
import org.sakaiproject.tool.tasklist.impl.TaskImpl;

public class TaskListBean {
  /** A holder for the single new task that may be in creation **/
  public Task newtask = new TaskImpl();
  public String siteID;
  
  public Long[] deleteids;
  
  private TaskListManager manager;
  public void setTaskListManager(TaskListManager manager) {
    this.manager = manager;
  }
  
  public String processActionAdd() {
    if (newtask.getTask() == null || newtask.getTask().equals("")) {
      return "error";
    }
    else {
      newtask.setCreationDate(new Date());
      newtask.setSiteId(siteID);
      manager.saveTask(newtask);
      return "added";
    }
  }
  
  public void processActionDelete() {
    Map tasks = manager.findAllTasks(siteID);
    for (int i = 0; i < deleteids.length; ++ i) {
      Task todelete = (Task) tasks.get(deleteids[i]);
      manager.deleteTask(todelete);
    }
  }
}
