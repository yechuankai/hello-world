@echo off
@title 自动部署

set unzip=C:\Program Files\2345Soft\HaoZip
set path=%unzip%;%path%

:start

set fromDir=C:\tfs_release
set deployDir=C:\wms


@title 自动部署 %fromDir%

if not exist %deployDir% ( md "%deployDir%" )
if not exist %fromDir% ( md "%fromDir%" )

cd /d %fromDir%


set y=%date:~0,4%
set m=%date:~5,2%
set d=%date:~8,2%

set /a th=%time:~0,2%
if %th% LSS 10 (set hh=0%th%) else (set hh=%th%)
set /a tm=%time:~3,2%
if %tm% LSS 10 (set mm=0%tm%) else (set mm=%tm%)
set /a ts=%time:~6,2%
if %ts% LSS 10 (set ss=0%ts%) else (set ss=%ts%)
set backDir=C:\wms_backup\wms\%y%%m%%d%/%hh%%mm%%ss%

echo ----------------------------------------------------------------------
echo %date% %time% 检查 wms-startup-tomcat 部署包....

for /f %%i in ('dir /a-d /b wms-startup-tomcat*.zip') do (
 
 set backDir=%Y%
 echo 开始备份 %backDir%
 md "%backDir%"
 xcopy  %deployDir% %backDir% /S /E

 echo 停止服务
 net stop WMService
 
 set filename=%%i
 echo 开始解压 %filename%

 del /f /s /q %deployDir%\*.*
 rd /s /q %deployDir%
 md "%deployDir%"

 haozipc x %fromDir%/%filename% -o%deployDir%

 del del /f /s /q %fromDir%\*.*
 
 timeout /t 3
 echo 启动服务
 net start WMService

)

set deployDir=C:\wms-config-server
set backDir=c:\wms_backup\wms-config-server\%y%%m%%d%/%hh%%mm%%ss%

echo ----------------------------------------------------------------------
echo %date% %time% 检查 wms-config-startup-tomcat 部署包....

for /f %%i in ('dir /a-d /b wms-config-startup-tomcat*.zip') do (
 
 set backDir=%Y%
 echo 开始备份 %backDir%
 md "%backDir%"
 xcopy  %deployDir% %backDir% /S /E

 echo 停止服务
 net stop WMSConfigService
 
 set filename=%%i
 echo 开始解压 %filename%

 del /f /s /q %deployDir%\*.*
 rd /s /q %deployDir%
 md "%deployDir%"

 haozipc x %fromDir%/%filename% -o%deployDir%

 del del /f /s /q %fromDir%\*.*
 
 timeout /t 3
 echo 启动服务
 net start WMSConfigService

)

echo ----------------------------------------------------------------------
timeout /t 10
cls
goto start

pause