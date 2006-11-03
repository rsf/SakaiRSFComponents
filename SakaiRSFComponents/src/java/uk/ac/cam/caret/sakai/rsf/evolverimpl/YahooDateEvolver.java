/*
 * Created on 24 Oct 2006
 */
package uk.ac.cam.caret.sakai.rsf.evolverimpl;

import java.util.Date;

import uk.org.ponder.dateutil.LocalSDF;
import uk.org.ponder.htmlutil.DateSymbolJSEmitter;
import uk.org.ponder.htmlutil.HTMLUtil;
import uk.org.ponder.rsf.components.UIForm;
import uk.org.ponder.rsf.components.UIInput;
import uk.org.ponder.rsf.components.UIJointContainer;
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
    
    String truedateval = value == null? null : LocalSDF.w3cformat.format(value);
    
    UIInput.make(form, "true-date", toevolve.valuebinding.value, 
        truedateval);
     
    
    String initdate = HTMLUtil.emitJavascriptCall("initYahooCalendar_Dropdowns", 
        new String[] {togo.getFullID(), title.get()});
    
    UIVerbatim.make(togo, "init-date", initdate);
    
    return togo;
  }

}
