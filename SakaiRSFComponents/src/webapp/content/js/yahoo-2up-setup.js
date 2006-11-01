
    /* Begin German Calendar - _Cal is **IMPL** for Calendar */

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
      function(id,containerId,monthyear,selected) {
        var cal = new YAHOO.widget.Calendar2up_DE_Cal(id,containerId,monthyear,selected);
        return cal;
      };

    /* End German Calendar */

    YAHOO.namespace("example.calendar");

    var today,link1,selMonth1,selDay1;

    function initDE() {
      today = new Date();

      var thisMonth = today.getMonth();
      var thisDay = today.getDate();
      var thisYear = today.getFullYear();

      link1 = document.getElementById('dateLink1');

      selMonth1 = document.getElementById('selMonth1');
      selDay1 = document.getElementById('selDay1');

      selMonth1.selectedIndex = thisMonth;
      selDay1.selectedIndex = thisDay-1;

      YAHOO.example.calendar.cal1 = 
        new YAHOO.widget.Calendar2up_DE("YAHOO.example.calendar.cal1",
         "container1",(thisMonth+1)+"/"+thisYear,(thisMonth+1)+"/"+thisDay+"/"+thisYear);
      YAHOO.example.calendar.cal1.setChildFunction("onSelect",setDate1);
      YAHOO.example.calendar.cal1.title = "Waehle Dein Abflugsdatum";

//      YAHOO.example.calendar.cal1.addRenderer("1/1,1/6,5/1,8/15,10/3,10/31,12/25,12/26", YAHOO.example.calendar.cal1.pages[0].renderCellStyleHighlight1);

      YAHOO.example.calendar.cal1.render();

    }

    function showCalendar1() {
//      YAHOO.example.calendar.cal2.hide();
      
      var pos = YAHOO.util.Dom.getXY(link1);
      YAHOO.example.calendar.cal1.outerContainer.style.display='block';
      YAHOO.util.Dom.setXY(YAHOO.example.calendar.cal1.outerContainer, [pos[0],pos[1]+link1.offsetHeight+1]);
    }

    function setDate1() {
      var date1 = YAHOO.example.calendar.cal1.getSelectedDates()[0];
      selMonth1.selectedIndex=date1.getMonth();
      selDay1.selectedIndex=date1.getDate()-1;
      YAHOO.example.calendar.cal1.hide();
    }

    function changeDate1() {
      var month = selMonth1.selectedIndex;
      var day = selDay1.selectedIndex + 1;
      var year = today.getFullYear();

      YAHOO.example.calendar.cal1.select((month+1) + "/" + day + "/" + year);
      YAHOO.example.calendar.cal1.setMonth(month);
      YAHOO.example.calendar.cal1.render();
    }

    YAHOO.util.Event.addListener(window, "load", initDE); 
