/*
 * Created on Jun 2, 2006
 */
package org.sakaiproject.tool.tasklist.rsf;

import java.util.Date;

import org.sakaiproject.tool.tasklist.api.Task;

public class TaskTransit {
  private Task task;

  public void setTask(Task task) {
    if (task.getTask() == null || task.getTask().equals("")) {
      throw new IllegalArgumentException("Task name cannot be empty");
    }
    
    task.setCreationDate(new Date());
    this.task = task;
  }
  
  public Task getTask() {
    return task;
  }
}
