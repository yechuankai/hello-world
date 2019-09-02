@echo off
@title 启动WMS CONFIG服务
echo.
echo 启动WMS CONFIG服务
echo.

cd %~dp0

set JAVA_OPTS=-Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -jar %JAVA_OPTS% ../wms-config-startup-tomcat.jar
