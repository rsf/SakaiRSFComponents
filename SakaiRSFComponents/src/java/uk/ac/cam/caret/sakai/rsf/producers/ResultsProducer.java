/*
 * Created on 21 Sep 2006
 */
package uk.ac.cam.caret.sakai.rsf.producers;

import java.text.DateFormat;

import uk.ac.cam.caret.sakai.rsf.beans.DataBean;
import uk.org.ponder.rsf.components.UIContainer;
import uk.org.ponder.rsf.components.UIInternalLink;
import uk.org.ponder.rsf.components.UIOutput;
import uk.org.ponder.rsf.view.ComponentChecker;
import uk.org.ponder.rsf.view.ViewComponentProducer;
import uk.org.ponder.rsf.viewstate.SimpleViewParameters;
import uk.org.ponder.rsf.viewstate.ViewParameters;
import uk.org.ponder.stringutil.LocaleGetter;

public class ResultsProducer implements ViewComponentProducer {

  public static final String VIEW_ID = "results";
  private LocaleGetter localegetter;
  private DataBean databean;

  public String getViewID() {
    return VIEW_ID;
  }

  public void setLocaleGetter(LocaleGetter localegetter) {
    this.localegetter = localegetter;
  }

  // This would not be injected in an OTP implementation
  public void setDataBean(DataBean databean) {
    this.databean = databean;
  }

  public void fillComponents(UIContainer tofill, ViewParameters viewparamso,
      ComponentChecker checker) {
    DateFormat format = DateFormat.getDateTimeInstance(DateFormat.LONG,
        DateFormat.LONG, localegetter.get());
    UIOutput.make(tofill, "date1", format.format(databean.date1));
    UIOutput.make(tofill, "date2", format.format(databean.date2));
    UIOutput.make(tofill, "text", null, "#{dataBean.text}");

    UIInternalLink.make(tofill, "back", new SimpleViewParameters(
        IndexProducer.VIEW_ID));
  }

}