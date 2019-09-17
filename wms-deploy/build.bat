@echo off
set basedir=D:\develop\wms\workspace\wms
echo basedir=%basedir%

echo -----------------------install--------------------
call mvn -f %basedir%/pom.xml clean install

echo ----------------------package--------------------
call mvn -f %basedir%/wms-startup-report-tomcat/pom.xml clean package

call mvn -f %basedir%/wms-startup-file-tomcat/pom.xml clean package

call mvn -f %basedir%/wms-startup-tomcat/pom.xml clean package

copy %basedir%/wms-startup-report-tomcat/target/wms-report.war %basedir%/wms
copy %basedir%/wms-startup-file-tomcat/target/wms-file.war %basedir%/wms
copy %basedir%/wms-startup-tomcat/target/WMS-JBT.war %basedir%/wms

pause
