/*
 * Created on May 29, 2006
 */
package org.sakaiproject.tool.tasklist.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.sakaiproject.tool.tasklist.api.Task;

public class TaskUtil {
  public static final Map taskCollectionToMap(Collection c) {
    TreeMap togo = new TreeMap();
    for (Iterator tit = c.iterator(); tit.hasNext();) {
      Task task = (Task) tit.next();
      togo.put(task.getId(), task);
    }
    return togo;
  }
}
