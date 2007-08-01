RSF Sakai AJAX Sample - AutoComplete Simple

Description:
This sample tool demonstrates using AJAX and UVB in RSF within Sakai,
it does not use any external javascript libraries or frameworks (very simple example)
URL for this sample app will be (for tomcat running on local machine on default port):
http://localhost:8080/rsf-sakai-ajax-autocomplete-simple-M2/faces/
(you will probably want to access this via Sakai)

Requirements:
Java 1.5 or better (http://java.sun.com/)
Apache Tomcat 5.5.x (http://tomcat.apache.org/)
Maven (http://maven.apache.org/)
Sakai 2.2 or better (and the relevant Sakai tomcat setup)
- Sakai Development Environment Setup link: 
- http://confluence.sakaiproject.org/confluence/x/zzs


MAVEN 1 build: (Sakai 2.2 - 2.4.x)

Build this project and deploy it in tomcat by running the following from this directory (the one with the readme):
mvn cln bld dpl


MAVEN 2.x build:

Make sure you have a settings.xml file in $YOUR_USER_HOME/.m2 directory, here is a sample one:
http://www2.caret.cam.ac.uk/rsfwiki/attach/ContainerSetupGuide/settings.xml

Set the <appserver.home>c:\opt\tomcat</appserver.home> to point to your $TOMCAT_HOME directory.

Build this project and deploy it in tomcat by running the following from this directory (the one with the readme):
mvn install cargo:deploy


More info on RSF:
http://www2.caret.cam.ac.uk/rsfwiki/

-Aaron Zeckoski (azeckoski@gmail.com)
