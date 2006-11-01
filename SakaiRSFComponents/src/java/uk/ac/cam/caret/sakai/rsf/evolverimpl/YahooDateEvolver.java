/*
 * Created on 24 Oct 2006
 */
package uk.ac.cam.caret.sakai.rsf.evolverimpl;

import java.util.Date;

import uk.org.ponder.htmlutil.DateSymbolJSEmitter;
import uk.org.ponder.rsf.components.UIForm;
import uk.org.ponder.rsf.components.UIInput;
import uk.org.ponder.rsf.components.UIJointContainer;
import uk.org.ponder.rsf.components.UIVerbatim;
import uk.org.ponder.rsf.evolvers.DateInputEvolver;

public class YahooDateEvolver implements DateInputEvolver {
  public static final String COMPONENT_ID = "yahoo-date-field:";
  
  private DateSymbolJSEmitter jsemitter;
  
  public void setJSemitter(DateSymbolJSEmitter jsemitter) {
    this.jsemitter = jsemitter;
  }

  public UIJointContainer evolveDateInput(UIInput toevolve, Date value) {
    UIJointContainer togo = new UIJointContainer(toevolve.parent, toevolve.ID, 
        COMPONENT_ID);
    
    toevolve.parent.remove(toevolve);
    
    String jsblock = jsemitter.emitDateSymbols();
    UIVerbatim datesymbols = UIVerbatim.make(togo, "datesymbols", jsblock);
   
    UIForm form = UIForm.make(togo, "basic-form");
    
    return togo;
  }

}
