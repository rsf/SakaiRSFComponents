// Define a namespace to use in our application to avoid polluting the global namespace
var AjaxAutoComplete = function() {

   // example of a private variable
   var privateVar = "AZ private value";

   // example of a private method that can only be called in our namespace
   function privateMethod (param) {
      return param + " Private!";
   }

   // this defines any functions inside our JS namespace that we allow to be called
   return {
      // handles the initialization of the autocomplete objects
      initAutocomplete: function (fieldId, elBinding) {
         var inputField = document.getElementById(fieldId); // get the field we are working with
         var ajaxUrl = inputField.form.action; // get the URL from the form
         // create the autocomplete object with empty array by default
         var autoCompleteObj = ACTB.actb(inputField, []);

         var callback = function(results) {
            // this function (callback) defines what to do when the ajax response is received,
            // the response will be placed in the "results" variable

            // results.EL is a map of ELbinding -> JS Object
            // get out the object (which is a string array in this case) and decode it
            var resultArray = RSF.decodeRSFStringArray(results.EL[elBinding]);
            // assign the new set of autocomplete results
            autoCompleteObj.actb_keyword = resultArray;
            ACTB.actb_generate();
         }

         // setup the function which initiates the AJAX request
         var updater = RSF.getAJAXUpdater([inputField], ajaxUrl, [elBinding], callback);
         // setup the input field event to trigger the ajax request function
         // NOTE: onkeydown, onkeypress, onblur are used by ACTB
         inputField.onkeyup = updater; // for when the user is typing in the field
      },
      // example of a public function
      publicMethod: function (param) {
         var someVar = param + " Public! : privateVar=" + privateVar;
         return someVar;
      }
   }; // end return

}(); // end namespace definition