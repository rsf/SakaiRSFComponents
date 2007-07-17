RSF Sakai AJAX Sample - AutoComplete Simple

Description:
This sample tool demonstrates using AJAX and UVB in RSF within Sakai,
it does not use any external javascript libraries or frameworks (very simple example)
URL for this sample app will be (for tomcat running on local machine on default port):
http://localhost:8080/RSFtasklist/rsf/

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


MAVEN 2 build: (Sakai 2.5+)

Build this project and deploy it in tomcat by running the following from this directory (the one with the readme):
mvn clean install sakai:deploy


More info on RSF:
http://www2.caret.cam.ac.uk/rsfwiki/

-Aaron Zeckoski (azeckoski@gmail.com)
