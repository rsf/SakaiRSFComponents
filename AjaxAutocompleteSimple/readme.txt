RSF Sakai AJAX Sample - AutoComplete Simple

This project requires java 1.5 or better and tomcat 5.x
It also requires Sakai 2.2 or better (and the relevant Sakai tomcat setup)
Sakai Development Environment Setup link: 
http://confluence.sakaiproject.org/confluence/x/zzs


MAVEN 1: (Sakai 2.2 - 2.4.x)

Use maven 1 to build this project and deploy it in tomcat by running the following from this directory (the one with the readme):
mvn cln bld dpl


MAVEN 2: (Sakai 2.5+)

Use maven 2 to build this project and deploy it in tomcat by running the following from this directory (the one with the readme):
mvn clean install sakai:deploy
