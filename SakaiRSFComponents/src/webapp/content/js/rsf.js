// RSF.js - primitive definitions for parsing RSF-rendered forms and bindings

// definitions placed in RSF namespace, following approach recommended in 
// http://www.dustindiaz.com/namespace-your-javascript/

var RSF = function() {
  return {
    // From FossilizedConverter.java 
    // key = componentid-fossil, value=[i|o]uitype-name#{bean.member}oldvalue 
    // and
    // key = [deletion|el]-binding, value = [e|o]#{el.lvalue}rvalue 

    parseFossil: function (fossil) {
      fossilex = /(.)(.*)#{(.*)}(.*)/;
      var matches = fossil.match(fossilex);
      var togo = new Object();
      togo.input = matches[1] == 'i';
      togo.uitype = matches[2];
      togo.rvalue = matches[3];
      togo.oldvalue = matches[4];
      return togo;
      },

    parseBinding: function (binding, deletion) {
      bindingex = /(.)#{(.*)}(.*)/;
      var matches = binding.match(bindingex);
      var togo = new Object();
      togo.EL = matches[1] == 'e';
      togo.lvalue = matches[2];
      togo.rvalue = matches[3];
      togo.isdeletion = deletion == "deletion";
      return togo;
      },

    encodeElement: function(key, value) {
      return encodeURIComponent(key) + "=" + encodeURIComponent(value);
      },

    renderBinding: function(lvalue, rvalue) {
      return encodeElement("el-binding", "o" + lvalue + "}" + rvalue);
      },
      
    renderUVBQuery: function(readEL) {
      return renderBinding("UVBBean.paths", readEL);
      },
    
    getUVBResponseID: function(readEL) {
      return ":"+readEL+":";
      },
    
    getUVBElement: function(readEL, responseDOM) {
      var rid = getUVBResponseID(readEL);
      var node = responseDOM.getElementById(rid)
      var nodes = node.childNodes;
      var text = "";
      for (var i in nodes) {
        var child = nodes[i];
        if (child.nodeType == 3) {
          text = text + child.nodeValue;
          }
        }
      return text; 
      },
      
    parseUVBResponse: function(responseDOM) {
      var togo = new Object();
      
      },
    /** Return the body of a "partial submission" POST corresponding to the
     * section of a form contained within argument "container" rooted at
     * the supplied "element", "as if" that form section were to be submitted
     * with element's value set to "value" */ 
    getPartialSubmissionBody: function(container, element, value) {
      var upstream = getUpstreamElements(container, element);
      var body = new Array();
      body.push(element.name, value);
      for (var i in upstream) {
        var upel = upstream[i];
        body.push(upel.name, upel.value);
        }
      return body.join("&");
      },
    /** Returns a set of DOM elements (currently of type <input>) 
     * corresponding to the set involved in the EL cascade formed by
     * submission of the supplied element.
     * @param container A DOM element (probably <div>) to be searched for
     * upstream bindings
     * @param element The primary submitting control initiating the cascade.
     */
    getUpstreamElements: function (container, element) {
      var inputs = container.getElementsByTagName("input");
      var name = element.name;
  
      var fossil;
      var bindings = new Array(); // an array of parsed bindings
  
      bindingex = /(.*)-binding/;
  
      for (var i in inputs) {
        var input = inputs[i];
        if (input.name == element.name + "-fossil") {
          fossil = input;
          }
          var matches = input.name.match(bindingex);
          if (matches != null) {
            var binding = parseBinding(input.value, matches[0]);
            binding.element = input;
            bindings.push(binding);
          }
        }
      // a map of EL expressions to DOM elements
      var invalidated = new Object();
      invalidated[fossil.rvalue] = fossil;
   
      // silly O(n^2) algorithm - writing graph algorithms in Javascript is a pain!
      while (true) {
        var expanded = false;
        for (var i in bindings) {
        var binding = bindings[i];
        if (invalidated[binding.lvalue] != null) {
          invalidated[binding.rvalue] = binding.element;
          expanded = true;
          }
        }
        if (!expanded) break;
      }
   
      var togo = new Array();
      for (var i in invalidated) {
        togo.push(invalided[i]);
        }
      return togo;
      }, // end getUpstreamElements
      
    /** Return the ID of another element in the same container as the
    * "base", only with the local ID (rsf:id) given by "targetiD"
    */
    getRelativeID: function(baseid, targetid) {
      colpos = baseid.lastIndexOf(':');
      return baseid.substring(0, colpos + 1) + targetid;
      }
      
      
    }; // end return internal "Object"
  }(); // end namespace RSF