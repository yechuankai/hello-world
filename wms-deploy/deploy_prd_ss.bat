@echo off
set basedir=D:\develop\wms\workspace\wms
echo basedir=%basedir%

echo ----------------------deploy prd1--------------------
call ant  -Denv=prd1 -f swp.xml swp-ss

echo ----------------------deploy prd1--------------------
call ant  -Denv=prd2 -f swp.xml swp-ss


pause
