@echo off
set basedir=D:\develop\wms\workspace\wms
echo basedir=%basedir%

echo ----------------------deploy prd1--------------------
call ant -Denv=prd1 -f wms.xml wms-service-deploy

echo ----------------------deploy prd2--------------------
call ant -Denv=prd2 -f wms.xml wms-service-deploy


pause
