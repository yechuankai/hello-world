@echo off
set basedir=D:\develop\wms\workspace\wms
echo basedir=%basedir%

echo ----------------------package--------------------
call mvn -f %basedir%/wms-startup-file-tomcat/pom.xml clean package
call ant -f tomcat.xml wms-file-deploy
