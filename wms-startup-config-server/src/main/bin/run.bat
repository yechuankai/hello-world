@echo off
@title ����WMS CONFIG����
echo.
echo ����WMS CONFIG����
echo.

cd %~dp0

set JAVA_OPTS=-Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -jar %JAVA_OPTS% ../wms-config-startup-tomcat.jar
