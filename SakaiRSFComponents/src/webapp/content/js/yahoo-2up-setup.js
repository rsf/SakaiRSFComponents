
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

    function initYahooCalendar(containerID, dateLinkID, selMonthID, selDayID, title) {
      var today = new Date();

      var thisMonth = today.getMonth();
      var thisDay = today.getDate();
      var thisYear = today.getFullYear();
      
      alert("initYahooCalendar: " + containerID + " " + dateLinkID + " " + selMonthID 
        + " " + selDayID);

	  var dateLink = document.getElementById(dateLinkID);
	  var selMonth = document.getElementById(selMonthID);
	  var selDay = document.getElementById(selDayID);

      alert("initYahooCalendar: " + containerID + " " + dateLink + " " + selMonth 
        + " " + selDay);

      newcal = 
        new YAHOO.widget.Calendar2up_DE("YAHOO.calendar."+containerID,
         containerID, (thisMonth+1)+"/"+thisYear, (thisMonth+1)+"/"+thisDay+"/"+thisYear);
      newcal.setChildFunction("onSelect", 
        function() {
          yahoo_setDate(newcal, selMonth, selDay);
          });
          
      selMonth.selectedIndex = thisMonth;
      selDay.selectedIndex = thisDay-1;

	  selMonth.onChange = function() {
	    yahoo_changeDate(newcal, selMonth, selDay);
	    };
	  selDay.onChange = function() {
	    yahoo_changeDate(newcal, selMonth, selDay);
	    };

	  dateLink.onclick = function() {
	    yahoo_showCalendar(newcal, dateLink);
	    };
      newcal.title = title;

//      YAHOO.example.calendar.cal1.addRenderer("1/1,1/6,5/1,8/15,10/3,10/31,12/25,12/26", YAHOO.example.calendar.cal1.pages[0].renderCellStyleHighlight1);
     
     newcal.render();

    }

    function yahoo_showCalendar(cal, dateLink) {
//      YAHOO.example.calendar.cal2.hide();
      
      var pos = YAHOO.util.Dom.getXY(dateLink);
      cal.outerContainer.style.display='block';
      YAHOO.util.Dom.setXY(cal.outerContainer, [pos[0],pos[1]+dateLink.offsetHeight+1]);
    }

    function yahoo_setDate(cal, selMonth, selDay) {
      var date1 = cal.getSelectedDates()[0];
      selMonth1.selectedIndex = date1.getMonth();
      selDay1.selectedIndex = date1.getDate()-1;
      cal.hide();
    }

    function yahoo_changeDate(cal, selMonth, selDay) {
      var month = selMonth.selectedIndex;
      var day = selDay.selectedIndex + 1;
      var year = today.getFullYear();

      cal.select((month+1) + "/" + day + "/" + year);
      cal.setMonth(month);
      cal.render();
    }

