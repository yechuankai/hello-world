@echo off
set basedir=D:\develop\wms\workspace\wms
echo basedir=%basedir%

echo ----------------------package--------------------
call mvn -f %basedir%/wms-startup-file-tomcat/pom.xml clean package

echo ----------------------deploy prd1--------------------
call ant -Denv=prd1 -f tomcat.xml wms-file-deploy

echo ----------------------deploy prd1--------------------
call ant -Denv=prd2 -f tomcat.xml wms-file-deploy

pause
