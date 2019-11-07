@echo off
set basedir=D:\develop\wms\workspace\wms
echo basedir=%basedir%

echo ----------------------package--------------------
call mvn -f %basedir%/wms-startup-config-server/pom.xml clean package
call ant  -Denv=prd1 -f wms.xml wms-config-deploy
