@echo off
set basedir=D:\develop\wms\workspace\wms
echo basedir=%basedir%

echo ----------------------package--------------------
call ant -Denv=test -f wms.xml wms-service-deploy

pause
