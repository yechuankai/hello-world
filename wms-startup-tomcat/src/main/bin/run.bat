@echo off
@title ����WMS����
echo.
echo ����WMS����
echo.

cd %~dp0

set JAVA_OPTS=-Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -jar %JAVA_OPTS% ../wms-startup-tomcat.jar
