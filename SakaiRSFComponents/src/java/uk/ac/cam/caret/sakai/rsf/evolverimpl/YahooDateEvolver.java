/*
 * Created on 24 Oct 2006
 */
package uk.ac.cam.caret.sakai.rsf.evolverimpl;

import java.util.Date;

import uk.org.ponder.htmlutil.DateSymbolJSEmitter;
import uk.org.ponder.htmlutil.HTMLUtil;
import uk.org.ponder.rsf.components.UIForm;
import uk.org.ponder.rsf.components.UIInput;
import uk.org.ponder.rsf.components.UIJointContainer;
import uk.org.ponder.rsf.components.UIOutput;
import uk.org.ponder.rsf.components.UIVerbatim;
import uk.org.ponder.rsf.evolvers.DateInputEvolver;
import uk.org.ponder.stringutil.StringGetter;
import uk.org.ponder.stringutil.StringHolder;

public class YahooDateEvolver implements DateInputEvolver {
  public static final String COMPONENT_ID = "yahoo-date-field:";
  
  private DateSymbolJSEmitter jsemitter;

  private StringGetter title = new StringHolder("Select Date");
  
  public void setJSemitter(DateSymbolJSEmitter jsemitter) {
    this.jsemitter = jsemitter;
  }
  
  public void setTitle(StringGetter title) {
    this.title  = title;
  }

  public UIJointContainer evolveDateInput(UIInput toevolve, Date value) {
    UIJointContainer togo = new UIJointContainer(toevolve.parent, toevolve.ID, 
        COMPONENT_ID);
    
    toevolve.parent.remove(toevolve);
    
    String jsblock = jsemitter.emitDateSymbols();
    UIVerbatim.make(togo, "datesymbols", jsblock);
   
    UIForm form = UIForm.make(togo, "basic-form");
    
    UIOutput selectday = UIOutput.make(form, "select-day");
    UIOutput selectmonth = UIOutput.make(form, "select-month");
    UIOutput datelink = UIOutput.make(form, "date-link");
    UIOutput datecontainer = UIOutput.make(form, "date-container");
    
    String initdate = HTMLUtil.emitJavascriptCall("initYahooCalendar", 
        new String[] {datecontainer.getFullID(), datelink.getFullID(), 
        selectmonth.getFullID(), selectday.getFullID(), title.get()});
    
    UIVerbatim.make(togo, "init-date", initdate);
    
    return togo;
  }

}
