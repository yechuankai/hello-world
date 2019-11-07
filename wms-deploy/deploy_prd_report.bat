@echo off
set basedir=D:\develop\wms\workspace\wms
echo basedir=%basedir%

rem call mvn -f %basedir%/wms-startup-report-tomcat/pom.xml clean package

echo ----------------------deploy prd1--------------------
call ant -Denv=prd1 -f wms.xml wms-report-deploy

echo ----------------------deploy prd2--------------------
call ant -Denv=prd2 -f wms.xml wms-report-deploy


pause

