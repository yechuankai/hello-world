# wms

#### 介绍
自研WMS

#### 软件架构
软件架构说明

wms-common           公共组件包，被依赖
wms-dao              数据库访问包，非自定义查询统一采用自动生成
wms-services         逻辑服务包，开放rest服务，未来可独立微服务
wms-web              web视图包，暂时未实现前后端分离
wms-file             文件操作，包括Excel导入导出、上传下载
wms-report           报表查询
wms-config-server    配置中心服务，独立发布
wms-startup-tomcat   应用启动服务，采用内置tomcat  java -jar *.jar
wms-startup-file-tomcat   文件应用启动服务，采用内置tomcat  java -jar *.jar
wms-startup-report-tomcat   报表应用启动服务，采用内置tomcat  java -jar *.jar

1
