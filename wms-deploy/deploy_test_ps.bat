@echo off
set basedir=D:\develop\wms\workspace\wms
echo basedir=%basedir%

echo ----------------------deploy test--------------------
call ant -Denv=test -f swp.xml swp-ps

pause
