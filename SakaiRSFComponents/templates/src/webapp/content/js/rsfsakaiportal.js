// Very long function name, since this can't be safely put in the RSF namespace!
function addSakaiRSFDomModifyHook(frameID) {
  if (RSF && RSF.getDOMModifyFirer) {
    var firer = RSF.getDOMModifyFirer();
    firer.addListener(
      function() {
        console.log("Updating frame height for " + frameID);
        setMainFrameHeight(frameID);
        });
    }
  }