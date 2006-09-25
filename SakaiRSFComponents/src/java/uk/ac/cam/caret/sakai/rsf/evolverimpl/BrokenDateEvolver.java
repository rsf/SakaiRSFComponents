/*
 * Created on 22 Sep 2006
 */
package uk.ac.cam.caret.sakai.rsf.evolverimpl;

import java.util.Date;

import uk.org.ponder.beanutil.BeanGetter;
import uk.org.ponder.dateutil.BrokenDateTransit;
import uk.org.ponder.dateutil.DateUtil;
import uk.org.ponder.dateutil.MonthBean;
import uk.org.ponder.rsf.components.UIInput;
import uk.org.ponder.rsf.components.UIJointContainer;
import uk.org.ponder.rsf.components.UIOutputMany;
import uk.org.ponder.rsf.components.UISelect;
import uk.org.ponder.rsf.evolvers.DateInputEvolver;
import uk.org.ponder.stringutil.StringList;

/**
 * A producer for a "Broken-up date" that will peer with three or more UISelect
 * controls, as per "old-style" Sakai date widget (using
 * chef_dateselectionwidgetpopup)
 * 
 * @author Antranig Basman (antranig@caret.cam.ac.uk)
 * 
 */

public class BrokenDateEvolver implements DateInputEvolver {
  public static final String COMPONENT_ID = "broken-date:";

  private String transitbase = "dateTransit";

  private String[] yearlist;

  private String[] minutelist = DateUtil.twoDigitList(0, 45, 15);
  
  private String[] hourlist = DateUtil.twoDigitList(1, 12, 1);
  
  private String[] daylist = DateUtil.dayList();

  private BeanGetter rbg;

  private String monthbeanname;

  public void setTransitBase(String transitbase) {
    this.transitbase = transitbase;
  }

  public void setYearList(String years) {
    yearlist = StringList.fromString(years).toStringArray();
  }

  public void setMonthBeanName(String monthbeanname) {
    this.monthbeanname = monthbeanname;
  }

  public void setMinutesList(String minutes) {
    minutelist = StringList.fromString(minutes).toStringArray();
  }

  public void setRequestBeanGetter(BeanGetter rbg) {
    this.rbg = rbg;
  }

  public UIJointContainer evolveDateInput(UIInput toevolve, Date date) {
    UIJointContainer togo = new UIJointContainer(toevolve.parent, COMPONENT_ID,
        toevolve.ID);

    toevolve.parent.move(toevolve, null);

    String ttb = transitbase + "." + togo.getFullID();
    BrokenDateTransit transit = null;
    if (date != null) {
      transit = (BrokenDateTransit) rbg.getBean(ttb);
      transit.setDate(date);
    }

    ttb += ".";

    UISelect.make(togo, "year", yearlist, ttb + "year",
        transit == null ? null: transit.year);

    UISelect monthselect = UISelect.make(togo, "month", MonthBean.indexarray,
        null, ttb + "month", transit == null ? null
            : transit.month);
    monthselect.optionnames = UIOutputMany.make(monthbeanname + ".indexes",
        monthbeanname);
    
    
    UISelect.make(togo, "day", daylist, ttb + "day", 
        transit == null ? null
            : transit.day);
    
    UISelect.make(togo, "hour", hourlist, ttb + "hour", 
        transit == null ? null
            : transit.hour);

    UISelect.make(togo, "minute", minutelist, ttb + "minute", 
        transit == null ? null
            : transit.minute);
    
    UISelect.make(togo, "ampm", new String[] {"0", "1"}, 
        new String[]{"AM", "PM"}, ttb + "ampm", transit == null ? null
            : transit.ampm);  
    
    
    return togo;
  }

}
