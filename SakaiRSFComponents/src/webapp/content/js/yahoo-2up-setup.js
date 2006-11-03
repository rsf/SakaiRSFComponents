
    /* _Cal is **IMPL** for Calendar */

    YAHOO.widget.Calendar2up_DE_Cal = function(id, containerId, monthyear, selected) {
      if (arguments.length > 0)
      {
        this.init(id, containerId, monthyear, selected);
      }
    }

    YAHOO.widget.Calendar2up_DE_Cal.prototype = new YAHOO.widget.Calendar2up_Cal();

    YAHOO.widget.Calendar2up_DE_Cal.prototype.customConfig = function() {
      this.Config.Locale.MONTHS_SHORT = PUC_MONTHS_SHORT;
      this.Config.Locale.MONTHS_LONG =  PUC_MONTHS_LONG;
      this.Config.Locale.WEEKDAYS_1CHAR = PUC_WEEKDAYS_1CHAR;
      this.Config.Locale.WEEKDAYS_SHORT = PUC_WEEKDAYS_SHORT;
      this.Config.Locale.WEEKDAYS_MEDIUM = PUC_WEEKDAYS_MEDIUM;
      this.Config.Locale.WEEKDAYS_LONG = PUC_WEEKDAYS_LONG;
      this.Config.Options.START_WEEKDAY = PUC_FIRST_DAY_OF_WEEK;
    }

    /** Plain object is **UI** for Calendar, of type YAHOO.widget **/

    YAHOO.widget.Calendar2up_DE = function(id, containerId, monthyear, selected) {
      if (arguments.length > 0)
      {
        this.buildWrapper(containerId);
        this.init(2, id, containerId, monthyear, selected);
      }
    }

    YAHOO.widget.Calendar2up_DE.prototype = new YAHOO.widget.Calendar2up();

    YAHOO.widget.Calendar2up_DE.prototype.constructChild = 
      function(id, containerId, monthyear, selected) {
        var cal = new YAHOO.widget.Calendar2up_DE_Cal(id, containerId, monthyear, selected);
        return cal;
      };

    /* End German Calendar */

    YAHOO.namespace("example.calendar");
    
    /* Start Calendar Context definition */
    
    function yahoo_calendar_context (cal, selMonth, selDay, dateLink) {
      this.cal = cal;
      this.selMonth = selMonth;
      this.selDay = selDay;
      this.dateLink = dateLink;
    }

    yahoo_calendar_context.prototype.show = function () {
//      YAHOO.example.calendar.cal2.hide();
      var pos = YAHOO.util.Dom.getXY(this.dateLink);
      this.cal.outerContainer.style.display='block';
      YAHOO.util.Dom.setXY(this.cal.outerContainer, [pos[0],pos[1]+this.dateLink.offsetHeight+1]);
    }

    yahoo_calendar_context.prototype.setDate = function () {
      var date1 = this.cal.getSelectedDates()[0];
      this.selMonth.selectedIndex = date1.getMonth();
      this.selDay.selectedIndex = date1.getDate()-1;
      this.cal.hide();
    }

    yahoo_calendar_context.prototype.changeDate = function() {
      var month = this.selMonth.selectedIndex;
      var day = this.selDay.selectedIndex + 1;
      // NB this line specific for 2up - uses undocumented list "pages" of Calendar
      var year = this.cal.pages[0].pageDate.getFullYear();

      alert("Year " + year);
      this.cal.select((month+1) + "/" + day + "/" + year);
      this.cal.setMonth(month);
      this.cal.setYear(year);
      this.cal.render();
    }

	yahoo_calendar_context.prototype.hide = function() {
	  this.cal.hide();
	  }


    function initYahooCalendar(containerID, dateLinkID, selMonthID, selDayID, title) {

      var today = new Date();

      var thisMonth = today.getMonth();
      var thisDay = today.getDate();
      var thisYear = today.getFullYear();
      
//      alert("initYahooCalendar: " + containerID + " " + dateLinkID + " " + selMonthID 
//        + " " + selDayID);

	  var dateLink = document.getElementById(dateLinkID);
	  var selMonth = document.getElementById(selMonthID);
	  var selDay = document.getElementById(selDayID);
	  var container = document.getElementById(containerID);

//      alert("initYahooCalendar: " + containerID + " " + dateLink + " " + selMonth 
//        + " " + selDay);

      newcal = 
        new YAHOO.widget.Calendar2up_DE("YAHOO.calendar."+containerID,
         containerID, (thisMonth+1)+"/"+thisYear, (thisMonth+1)+"/"+thisDay+"/"+thisYear);
         
      newcont = new yahoo_calendar_context(newcal, selMonth, selDay, dateLink);
      
      newcal.setChildFunction("onSelect", 
         (function(newcont) {return function() {newcont.setDate();};})(newcont)
          );
          
      selMonth.selectedIndex = thisMonth;
      selDay.selectedIndex = thisDay-1;

	  selMonth.onchange =  
	    (function(newcont) {return function() {newcont.changeDate();};})(newcont);
	    
	  selDay.onchange =
	    (function(newcont) {return function() {newcont.changeDate();};})(newcont);

	  dateLink.onclick = 
	    (function(newcont) {return function() {newcont.show();};})(newcont);
	    
      newcal.title = title;

//      YAHOO.example.calendar.cal1.addRenderer("1/1,1/6,5/1,8/15,10/3,10/31,12/25,12/26", YAHOO.example.calendar.cal1.pages[0].renderCellStyleHighlight1);
     
     newcal.render();
   
    }

	var oElement = document; 
	function fnCallback(e) { 
	  alert(e);
	  var target = e.getTarget();
	  
	  while(target) {
	    alert(target.id);
	    if (target.id) {
	      alert(target.id);
	      }
	    target = target.parentNode;
	  }
//	alert("click " + e); 
	} 
	YAHOO.util.Event.addListener(oElement, "click", fnCallback); 

