/*
 * Created on 22 Sep 2006
 */
package uk.ac.cam.caret.sakai.rsf.evolverimpl;

import uk.org.ponder.rsf.components.UIInput;
import uk.org.ponder.rsf.components.UIJointContainer;
import uk.org.ponder.rsf.components.UIVerbatim;
import uk.org.ponder.rsf.evolvers.TextInputEvolver;

public class SakaiFCKTextEvolver implements TextInputEvolver {
  public static final String COMPONENT_ID = "sakai-FCKEditor:";

  public UIJointContainer evolveTextInput(UIInput toevolve) {
    UIJointContainer joint = new UIJointContainer(toevolve.parent,
        toevolve.ID, COMPONENT_ID);
    toevolve.parent.remove(toevolve);
    toevolve.ID = "input"; // must change ID while unattached
    joint.addComponent(toevolve);
    UIVerbatim.make(joint, "textarea-js", "setupRSFFormattedTextarea('"
        + toevolve.getFullID() + "');");
    return joint;
  }

}
