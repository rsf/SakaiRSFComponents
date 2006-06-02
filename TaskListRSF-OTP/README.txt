Changes from JSF version:

* "TaskListService" and "TaskListServiceImpl" abolished. These were essentially
only an internal API for the use of JSF - replaced by setting dependencies
on the ViewProducer directly.
* TaskBeanWrapper abolished - not required in RSF - ViewProducer has sufficient
intelligence to figure out state of controls
* TaskListBean slimmed down considerably - JSF "DataModel" no longer required,
nor the wrapper inside it (see baove). 
* TaskListManager slightly altered to return a Map (keyed by ID) rather than a
Collection - a slight step towards the ultimate OTP version of this app. This
is actually needed within the handler bean to efficiently process deletes - recall
that the actual original Task objects have been destroyed by the time the POST
request is serviced.
* JSP is gone (obviously), replaced by a plain HTML template.
* JSF ResourceBundle replaced by a Spring MessageLocator (dynamically 
responsive to user's locale)
* JSF "convertDateTime" taglib replaced by a simple Java DateFormat - configured
by injected proxy for user Locale (improvement on original, I believe original
format was not Sakai-Locale-aware).
