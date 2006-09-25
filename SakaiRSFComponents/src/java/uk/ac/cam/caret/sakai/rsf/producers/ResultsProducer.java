/*
 * Created on 21 Sep 2006
 */
package uk.ac.cam.caret.sakai.rsf.producers;

import uk.org.ponder.rsf.components.UIContainer;
import uk.org.ponder.rsf.components.UIInternalLink;
import uk.org.ponder.rsf.components.UIOutput;
import uk.org.ponder.rsf.view.ComponentChecker;
import uk.org.ponder.rsf.view.ViewComponentProducer;
import uk.org.ponder.rsf.viewstate.SimpleViewParameters;
import uk.org.ponder.rsf.viewstate.ViewParameters;

public class ResultsProducer implements ViewComponentProducer {

  public static final String VIEW_ID = "results";

  public String getViewID() {
    return VIEW_ID;
  }

  public void fillComponents(UIContainer tofill, ViewParameters viewparamso,
      ComponentChecker checker) {
    UIOutput.make(tofill, "date1", null, "#{dataBean.date1}");
    UIOutput.make(tofill, "date2", null, "#{dataBean.date2}");
    UIOutput.make(tofill, "text", null, "#{dataBean.date1}");

    UIInternalLink.make(tofill, "back", new SimpleViewParameters(
        IndexProducer.VIEW_ID));
  }

}