@echo off

call build.bat

call ant -f tomcat.xml

pause
