-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
insert into sys_user_t values(0,   'ADMIN', 'ADMIN', '00', 'admin@wms.com', '18888888888', '1', '', '75de51b4f56bd8e46b1ec5ffa3b0dae4', '111111', 'Y', 'N', '127.0.0.1', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'System', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'), 'System', TO_DATE('2018-03-16 11-33-00', 'YYYY-MM-DD HH24:MI:SS'),1, 'System');


-- ----------------------------
-- 初始化-配置信息表数据
-- ----------------------------
insert into sys_config_t values(1, 'SYSTEM_MONITOR_REST', '接口服务监控', 'Y', 'O', 'Y', 0, 0, 'N', 'System', SYSDATE, 'System', SYSDATE,1, '');
insert into sys_config_t values(2, 'SYSTEM_MONITOR_EXCEPTION', '系统异常监控', 'Y', 'O', 'Y', 0, 0, 'N', 'System', SYSDATE, 'System', SYSDATE,1,'');
insert into sys_config_t values(3, 'INBOUND_RECEIVE_EXCEED', '超收开关', 'Y', 'O', 'Y', 0, 0, 'N', 'System', SYSDATE, 'System', SYSDATE,1, '');
insert into sys_config_t values(4, 'ALLOCATE_MONITOR', '分配监控', 'Y', 'O', 'Y', 0, 0, 'N', 'System', SYSDATE, 'System', SYSDATE,1,'');



-- ----------------------------
-- 初始化-权限信息表数据
-- ----------------------------

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1136869832319440000', '用户默认', '1', '1', 'system/userdefault', 'M', 'N', 'Y', 'system:userDefaut', '#', 'N', 'System', to_date('07-06-2019 13:37:44', 'dd-mm-yyyy hh24:mi:ss'), 'System', to_date('05-08-2019 15:59:32', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1145588257408294912', '批属性', '5', '5', 'inventory/lot', 'M', 'Y', 'Y', 'inventory:lot', '#', 'N', 'System', to_date('01-07-2019 15:01:39', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1145983752110854144', '交易', '5', '3', 'inventory/transaction', 'M', 'Y', 'Y', 'inventory:transaction', '#', 'N', 'System', to_date('02-07-2019 17:13:12', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '4', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146243315199909888', '上架策略', '1143804214605545472', '1', 'base/putawayStrategy', 'M', 'Y', 'Y', 'base:putawayStrategy', '#', 'N', 'System', to_date('03-07-2019 10:24:37', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146601287818551296', 'LPN', '5', '4', 'inventory/lpn', 'M', 'Y', 'Y', 'inventory:lpn', '#', 'N', 'System', to_date('04-07-2019 10:07:04', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150595273686958081', '在线用户', '1', '6', 'system/useronline', 'M', 'N', 'Y', 'monitory:onlineUser', '#', 'N', 'System', to_date('15-07-2019 10:37:44', 'dd-mm-yyyy hh24:mi:ss'), 'System', to_date('05-08-2019 15:59:32', 'dd-mm-yyyy hh24:mi:ss'), '4', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1155716768084799488', '客户维护', '154', '4', 'base/customer', 'M', 'Y', 'Y', 'base:customer', '#', 'N', 'System', to_date('29-07-2019 13:48:44', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1155716945172508672', '供应商维护', '154', '5', 'base/supplier', 'M', 'Y', 'Y', 'base:supplier', '#', 'N', 'System', to_date('29-07-2019 13:49:26', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1155717222596358144', '承运人维护', '154', '6', 'base/carrier', 'M', 'Y', 'Y', 'base:carrier', '#', 'N', 'System', to_date('29-07-2019 13:50:32', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156048115105980416', '发货', '1156046792318976000', '2', '#', 'F', 'Y', 'Y', 'mobile:ship', '#', 'N', '叶传开', to_date('30-07-2019 11:45:23', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 11:54:27', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156048316814254080', '库存', '1156046792318976000', '3', '#', 'F', 'Y', 'Y', 'mobile:inventory', '#', 'N', '叶传开', to_date('30-07-2019 11:46:11', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 11:54:27', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156048406123569152', '查询', '1156046792318976000', '4', '#', 'F', 'Y', 'Y', 'mobile:query', '#', 'N', '叶传开', to_date('30-07-2019 11:46:33', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 11:54:27', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156050725481406464', '按单收货', '1156047970155028480', '1', '#', 'F', 'Y', 'Y', 'mobile:receive:inboundReceive', '#', 'N', '叶传开', to_date('30-07-2019 11:55:45', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 11:55:45', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156050862710644736', '无单收货', '1156047970155028480', '2', '#', 'F', 'Y', 'Y', 'mobile:receive:noInboundReceive', '#', 'N', '叶传开', to_date('30-07-2019 11:56:18', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 13:39:54', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156050987587657728', '上架', '1156047970155028480', '3', '#', 'F', 'Y', 'Y', 'mobile:receive:putaway', '#', 'N', '叶传开', to_date('30-07-2019 11:56:48', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 13:39:54', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1157189806253010944', '批属性验证', '1143804214605545472', '3', 'base/lotValidate', 'M', 'Y', 'Y', 'base:lotValidate', '#', 'N', 'System', to_date('02-08-2019 15:22:04', 'dd-mm-yyyy hh24:mi:ss'), 'System', to_date('02-08-2019 15:22:04', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1158286175712718848', '单号规则维护', '1', '4', 'system/orderNumber', 'M', 'Y', 'Y', 'system:orderNumber', '#', 'N', 'System', to_date('05-08-2019 15:58:38', 'dd-mm-yyyy hh24:mi:ss'), 'System', to_date('05-08-2019 16:11:45', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1', '系统管理', '0', '100', '#', 'M', 'N', 'Y', 'system', null, 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '2', '系统管理目录');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('2', '基础数据', '0', '1', '#', 'M', 'Y', 'Y', 'base', null, 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '2', '系统管理目录');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('3', '入库', '0', '2', '#', 'M', 'Y', 'Y', 'inbound', null, 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '2', '系统管理目录');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('4', '出库', '0', '3', '#', 'M', 'Y', 'Y', 'outbound', null, 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '2', '系统管理目录');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('5', '库存', '0', '4', '#', 'M', 'Y', 'Y', 'inventory', null, 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '2', '系统管理目录');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('6', '报表', '0', '5', '#', 'M', 'Y', 'Y', 'report', null, 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '2', '系统管理目录');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('100', '用户维护', '1', '2', 'system/user', 'M', 'N', 'Y', 'system:user:view', '#', 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), 'System', to_date('05-08-2019 15:59:32', 'dd-mm-yyyy hh24:mi:ss'), '2', '用户管理权限');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('101', '角色维护', '1', '5', 'system/role', 'M', 'N', 'Y', 'system:role:view', '#', 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), 'System', to_date('05-08-2019 15:59:32', 'dd-mm-yyyy hh24:mi:ss'), '2', '角色管理权限');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('102', '权限维护', '1', '7', 'system/permission', 'M', 'N', 'Y', 'system:menu:view', '#', 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), 'System', to_date('05-08-2019 15:59:32', 'dd-mm-yyyy hh24:mi:ss'), '2', '权限管理权限');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('105', '字典维护', '1', '8', 'system/codeList', 'M', 'N', 'Y', 'system:dict:view', '#', 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), 'System', to_date('05-08-2019 15:59:32', 'dd-mm-yyyy hh24:mi:ss'), '2', '字典管理权限');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('106', '参数维护', '1', '9', 'system/config', 'M', 'N', 'Y', 'system:config:view', '#', 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), 'System', to_date('05-08-2019 15:59:32', 'dd-mm-yyyy hh24:mi:ss'), '2', '参数设置权限');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('109', '语言维护', '1', '0', 'system/locale', 'M', 'N', 'Y', 'system:locale', '#', 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '2', '用户管理权限');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('150', '公司维护', '2', '1', 'system/company', 'M', 'N', 'Y', 'base:company', '#', 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '3', '日志管理权限');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('151', '仓库维护', '2', '2', 'system/warehouse', 'M', 'N', 'Y', 'base:warehouse', '#', 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '3', '日志管理权限');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('153', '仓库设置', '2', '3', '#', 'M', 'Y', 'Y', 'base:warehouseConfig', '#', 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '3', '日志管理权限');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1531', '库区维护', '153', '1', 'base/zone', 'M', 'Y', 'Y', 'base:zone', '#', 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '4', '日志管理权限');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1532', '库位维护', '153', '2', 'base/location', 'M', 'Y', 'Y', 'base:location', '#', 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '9', '日志管理权限');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('154', '主数据', '2', '4', '#', 'M', 'Y', 'Y', 'base:master', '#', 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '3', '日志管理权限');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1541', '货主维护', '154', '1', 'base/owner', 'M', 'Y', 'Y', 'base:owner', '#', 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '4', '日志管理权限');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1542', '货品维护', '154', '2', 'base/sku', 'M', 'Y', 'Y', 'base:sku', '#', 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '3', '日志管理权限');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1543', '包装维护', '154', '3', 'base/pack', 'M', 'Y', 'Y', 'base:pack', '#', 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '4', '日志管理权限');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('500', '操作日志', '1', '3', 'system/operlog', 'M', 'N', 'Y', 'monitor:operlog:view', '#', 'N', 'System', to_date('16-03-2018 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), 'System', to_date('05-08-2019 15:59:32', 'dd-mm-yyyy hh24:mi:ss'), '3', '操作日志权限');

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156047970155028480', '收货', '1156046792318976000', '1', '#', 'F', 'Y', 'Y', 'mobile:receive', '#', 'N', '叶传开', to_date('30-07-2019 11:44:49', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 11:54:27', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156051145851330560', '按单拣货', '1156048115105980416', '1', '#', 'F', 'Y', 'Y', 'mobile:ship:outboundPick', '#', 'N', '叶传开', to_date('30-07-2019 11:57:26', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 11:57:26', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156051322632855552', '任务拣货', '1156048115105980416', '2', '#', 'F', 'Y', 'Y', 'mobile:ship:taskPick', '#', 'N', '叶传开', to_date('30-07-2019 11:58:08', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 13:39:54', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156051482595221504', '按单发运', '1156048115105980416', '3', '#', 'F', 'Y', 'Y', 'mobile:ship:inboundShip', '#', 'N', '叶传开', to_date('30-07-2019 11:58:46', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 13:39:54', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156051710811496448', '移动', '1156048316814254080', '1', '#', 'F', 'Y', 'Y', 'mobile:inventory:move', '#', 'N', '叶传开', to_date('30-07-2019 11:59:40', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 11:59:40', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156060522167590912', '盘点', '1156048316814254080', '2', '#', 'F', 'Y', 'Y', 'mobile:inventory:count', '#', 'N', '叶传开', to_date('30-07-2019 12:34:41', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 13:39:31', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156060677126152192', '库存', '1156048406123569152', '1', '#', 'F', 'Y', 'Y', 'mobile:query:inventory', '#', 'N', '叶传开', to_date('30-07-2019 12:35:18', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 12:35:18', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1143804214605545472', '策略设置', '2', '5', '#', 'M', 'Y', 'Y', 'base:strategy', '#', 'N', 'System', to_date('26-06-2019 16:52:30', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1143808813794099200', '分配策略', '1143804214605545472', '2', 'base/allocateStrategy', 'M', 'Y', 'Y', 'base:allocateStrategy', '#', 'N', 'System', to_date('26-06-2019 17:10:46', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149120938778460160', '出库单', '4', '1', 'outbound/list', 'M', 'Y', 'Y', 'outbound:list', '#', 'N', 'System', to_date('11-07-2019 08:59:16', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150643342189174784', '监控日志', '1', '10', 'system/monitor', 'M', 'N', 'Y', 'monitor', '#', 'N', 'System', to_date('15-07-2019 13:48:45', 'dd-mm-yyyy hh24:mi:ss'), 'System', to_date('15-07-2019 13:48:45', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156046792318976000', '移动端', '0', '999', '#', 'F', 'Y', 'Y', 'mobile', '#', 'N', '叶传开', to_date('30-07-2019 11:40:08', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 11:43:43', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146253875228352512', '入库单创建', '3', '0', 'inbound/edit', 'M', 'Y', 'Y', 'inbound:add', '#', 'N', 'System', to_date('03-07-2019 11:06:34', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1148867632803246080', '移动', '5', '2', 'inventory/move', 'M', 'Y', 'Y', 'inventory:move', '#', 'N', 'System', to_date('10-07-2019 16:12:43', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '4', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1145933700348649472', '余额', '5', '1', 'inventory/onhand', 'M', 'Y', 'Y', 'inventory:onhand', '#', 'N', 'System', to_date('02-07-2019 13:54:19', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '4', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146587386706788353', '分配明细', '4', '2', 'outbound/allocate', 'M', 'Y', 'Y', 'outbound:allocate', '#', 'N', 'System', to_date('04-07-2019 09:11:50', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '5', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146684462510755840', '拣货任务', '4', '3', 'inventory/task?type=PK', 'M', 'Y', 'Y', 'outbound:pickTask', '#', 'N', 'System', to_date('04-07-2019 15:37:34', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '4', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152096992107421697', '调整单', '5', '6', 'inventory/adjustmentList', 'M', 'Y', 'Y', 'inventory:adjustment', '#', 'N', 'System', to_date('19-07-2019 14:05:02', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153187688251785216', 'LPN打印', '6', '1', 'report/lpnprint', 'M', 'Y', 'Y', 'report:lpnprint', '#', 'N', '叶传开', to_date('22-07-2019 14:19:04', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('22-07-2019 14:19:04', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1141628144074506240', '入库单', '3', '1', 'inbound/list', 'M', 'Y', 'Y', 'inbound:list', '#', 'N', 'System', to_date('20-06-2019 16:45:34', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1141897778896142336', '区域维护', '153', '3', 'base/area', 'M', 'Y', 'Y', 'base:area', '#', 'N', 'System', to_date('21-06-2019 10:37:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '5', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146659834248704000', '上架任务', '3', '2', 'inventory/task?type=PA', 'M', 'Y', 'Y', 'inbound:putaway', '#', 'N', 'System', to_date('04-07-2019 13:59:43', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('30-07-2019 14:03:57', 'dd-mm-yyyy hh24:mi:ss'), '4', null);

insert into sys_permission_t (PERMISSION_ID, PERMISSION_NAME, PARENT_ID, SORT, URL, PERMISSION_TYPE, WAREHOUSE_FLAG, VISIBLE, PERMS, ICON, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1154212113025769473', '文件日志', '1', '100', 'system/files', 'M', 'Y', 'Y', 'file:list', '#', 'N', '叶传开', to_date('25-07-2019 10:09:46', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('25-07-2019 10:09:46', 'dd-mm-yyyy hh24:mi:ss'), '1', null);


-- ----------------------------
-- 初始化-数据字典信息表数据
-- ----------------------------

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('2', 'YESNO', '是/否', '10', 'Y', 'Y', '0', '0', 'N', null, null, '叶传开', to_date('23-07-2019 14:40:29', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('3', 'CONFIGTYPE', '参数类型', '10', 'Y', 'Y', '0', '0', 'N', null, null, '叶传开', to_date('23-07-2019 14:40:30', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('4', 'PERMISSIONTYPE', '权限类型', '10', 'Y', 'Y', '0', '0', 'N', null, null, '叶传开', to_date('23-07-2019 14:40:30', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('5', 'ZONETYPE', '库区类型', '10', 'Y', 'Y', '0', '0', 'N', null, null, '叶传开', to_date('23-07-2019 14:40:30', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('6', 'LOCATIONTYPE', '库位类型', '10', 'Y', 'Y', '0', '0', 'N', null, null, '叶传开', to_date('23-07-2019 14:40:30', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144153938701438976', 'ALLOCATEFIFO', '分配策略先进先出规则', '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 16:02:10', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('09-07-2019 19:47:38', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147032672559271936', 'SOURCETYPE', '来源状态', '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('05-07-2019 14:41:14', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('05-07-2019 14:41:39', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150641508552712193', 'MONITORSTATUS', '监控状态', '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'Y', to_date('15-07-2019 13:41:28', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:41:28', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1155729545159589888', 'SHIPLABEL', '发货标签', '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('29-07-2019 14:39:30', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('29-07-2019 14:39:30', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1157167788097957888', 'ADJUSTMENTREASON', '调整原因', '10', 'Y', 'N', '1136860898699380000', '1', 'N', '叶传开', to_date('02-08-2019 13:54:34', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('02-08-2019 13:54:34', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144153316644212736', 'ALLOCATESORT', '分配策略排序规则', '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 15:59:42', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('09-07-2019 19:47:19', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144153120686329856', 'ALLOCATETYPE', '分配策略明细类型', '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 15:58:55', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 15:58:55', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144153235069194240', 'ALLOCATEUOM', '单位', '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 15:59:23', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 15:59:23', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149237260691447808', 'OUTBOUNDTYPE', '出库单据类型', '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:41:29', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:41:29', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149237723692277760', 'OUTBOUNDSTATUS', '出库单状态', '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:43:19', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:43:19', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150638869735710720', 'REQUESTIME', '请求时间', '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'Y', to_date('15-07-2019 13:30:59', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:30:59', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150641803856879617', 'MONITORTYPE', '监控类型', '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'Y', to_date('15-07-2019 13:42:38', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:42:38', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152146764932419584', 'ADJUSTMENTSTATUS', '库存调整状态', '20', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('19-07-2019 17:22:49', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('19-07-2019 17:22:49', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('7', 'INBOUNDTYPE', '入库类型', '10', 'Y', 'Y', '0', '0', 'N', null, null, '叶传开', to_date('23-07-2019 14:40:30', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('8', 'INBOUNDSTATUS', '入库状态', '10', 'Y', 'Y', '0', '0', 'N', null, null, '叶传开', to_date('23-07-2019 14:40:30', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1143796303359733760', 'CODETYPE', '字典类型', '1', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('26-06-2019 16:21:04', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('01-07-2019 11:26:38', 'dd-mm-yyyy hh24:mi:ss'), '5', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146227246771458048', 'TRANSACTIONTYPE', '交易类型', '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('03-07-2019 09:20:46', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 09:20:46', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146331992131465216', 'PUTAWAYSORT', '上架策略排序规则', '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 16:16:59', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 16:16:59', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146585844247298049', 'ALLOCATESTRATEGYTYPE', '分配类型', '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('04-07-2019 09:05:42', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 09:05:42', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146585958147817473', 'ALLOCATESTATUS', '分配明细状态', '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('04-07-2019 09:06:09', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 09:06:09', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146717773715066880', 'TASKSTATUS', '任务状态', '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('04-07-2019 17:49:56', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 17:49:56', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147032585254834176', 'TASKTYPE', '任务类型', '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('05-07-2019 14:40:53', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('05-07-2019 14:40:53', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1', 'LOCALE', '语言', '10', 'Y', 'Y', '0', '0', 'N', null, null, '叶传开', to_date('23-07-2019 14:40:30', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146327813816143872', 'PUTAWAYTYPE', '上架策略类型', '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 16:00:23', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 16:00:23', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1151024877213286400', 'UOM', '单位', '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('16-07-2019 15:04:50', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('23-07-2019 14:40:05', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153988103054729217', 'FILESTATUS', '文件状态', '10', 'Y', 'Y', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:19:38', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:19:38', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1154209172483121153', 'TEMPLATE', '模板', '10', 'Y', 'Y', '0', '0', 'N', '叶传开', to_date('25-07-2019 09:58:05', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('25-07-2019 09:58:05', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153986585106751488', 'FILETYPE', '文件操作类型', '10', 'Y', 'Y', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:13:36', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:13:36', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1145535582284902400', 'AREATYPE', '区域类型', '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('01-07-2019 11:32:20', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('01-07-2019 11:32:20', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146230863901478912', 'TRANSACTIONCATEGORY', '交易类别', '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('03-07-2019 09:35:08', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 09:35:08', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146612218820698112', 'LPNTYPE', 'LPN类型', '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('04-07-2019 10:50:30', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 10:50:30', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1148778625205497856', 'SKUTYPE', '货品类型', '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('10-07-2019 10:19:02', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('10-07-2019 10:19:02', 'dd-mm-yyyy hh24:mi:ss'), '1', null);


insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('3', '2', 'Y', '0', '是', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('14-06-2019 16:33:58', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('14-06-2019 16:33:58', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('4', '2', 'N', '1', '否', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('14-06-2019 16:33:58', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('14-06-2019 16:33:58', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('5', '3', 'O', '0', '开关', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('14-06-2019 16:36:01', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('14-06-2019 16:36:01', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('6', '3', 'V', '1', '值', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('14-06-2019 16:36:01', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('14-06-2019 16:36:01', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('7', '4', 'M', '0', '菜单', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('14-06-2019 16:36:59', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('14-06-2019 16:36:59', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('8', '4', 'F', '1', '功能', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('14-06-2019 16:36:59', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('14-06-2019 16:36:59', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('21', '1', 'fr', '2', 'Français', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('21-06-2019 09:36:45', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-06-2019 09:36:45', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146227369232551936', '1146227246771458048', 'I', '0', '入库', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('03-07-2019 09:21:15', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 09:21:15', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146227441794011136', '1146227246771458048', 'M', '1', '移动', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('03-07-2019 09:21:32', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 09:21:32', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144075095491842048', '1144075018941599744', '10', '0', '硬分配', null, 'Y', 'Y', 'Y', '1136860898699380000', '1', 'N', 'ADMIN', to_date('27-06-2019 10:48:53', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 10:48:53', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144153774683181056', '1144153235069194240', 'PCS', '0', '按件', null, 'Y', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 16:01:31', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 16:01:31', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144153840588279808', '1144153235069194240', 'LPN', '1', '按LPN', null, 'Y', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 16:01:47', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 16:01:47', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150639106059575297', '1150638869735710720', '500-1000', '3', '500-1000', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'Y', to_date('15-07-2019 13:31:55', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:31:55', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144154239030382592', '1144153938701438976', 'LOTATTRIBUTE4', '1', '按批属性4  生产日期', null, 'Y', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 16:03:22', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 16:04:11', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144154572909563904', '1144153938701438976', 'LOTATTRIBUTE5', '3', '按批属性11', null, 'Y', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 16:04:42', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 16:04:42', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146227898977341440', '1146227246771458048', 'O', '2', '出库', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('03-07-2019 09:23:21', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 09:23:21', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146612408738783232', '1146612218820698112', '20', '1', '托盘', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('04-07-2019 10:51:15', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 10:51:15', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146612470642515968', '1146612218820698112', '30', '2', '容器', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('04-07-2019 10:51:30', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 10:51:30', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147032874997354496', '1146717773715066880', '10', '0', '新建', null, '10', 'Y', 'Y', '1136860898699380000', '1', 'N', 'ADMIN', to_date('05-07-2019 14:42:02', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('05-07-2019 14:42:02', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150638981279031296', '1150638869735710720', '0-100', '0', '0-100', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'Y', to_date('15-07-2019 13:31:25', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('15-07-2019 15:32:45', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150639027743531009', '1150638869735710720', '100-500', '2', '100-500', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'Y', to_date('15-07-2019 13:31:36', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:31:36', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150641582540234753', '1150641508552712193', '10', '0', '成功', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'Y', to_date('15-07-2019 13:41:45', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:41:45', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152146908918681600', '1152146764932419584', '10', '0', '新建', null, '20', 'Y', 'Y', '1136860898699380000', '1', 'N', 'ADMIN', to_date('19-07-2019 17:23:23', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('19-07-2019 17:23:30', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152146977940148224', '1152146764932419584', '20', '1', '已完成', null, '20', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('19-07-2019 17:23:40', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('19-07-2019 17:23:40', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153554631350456320', '1151024877213286400', 'EA', '0', '件', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('23-07-2019 14:37:10', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('23-07-2019 14:37:10', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153554687533158400', '1151024877213286400', 'CA', '2', '箱', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('23-07-2019 14:37:24', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('23-07-2019 14:37:24', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1157167865692581888', '1157167788097957888', 'NORMAL', '0', '普通调整', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', '叶传开', to_date('02-08-2019 13:54:52', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('02-08-2019 13:54:52', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1157167910651326465', '1157167788097957888', 'UNRECEIVE', '1', '撤销收货', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', '叶传开', to_date('02-08-2019 13:55:03', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('02-08-2019 13:55:03', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149237470356316160', '1149237260691447808', '10', '0', '普通出库', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:42:19', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('15-07-2019 16:12:24', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144075149640306688', '1144075018941599744', '20', '1', '软分配', null, 'Y', 'Y', 'Y', '1136860898699380000', '1', 'N', 'ADMIN', to_date('27-06-2019 10:49:06', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 10:49:06', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144154393179443200', '1144153938701438976', 'LOTATTRIBUTE11', '2', '按批属性5  到期日期', null, 'Y', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 16:03:59', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 16:04:16', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144154638130991104', '1144153938701438976', 'LOTATTRIBUTE12', '4', '按批属性12', null, 'Y', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 16:04:57', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 16:04:57', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149237803421802496', '1149237723692277760', '10', '0', '新建', null, '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:43:38', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:47:17', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149238246550020096', '1149237723692277760', '20', '1', '部分分配', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:45:24', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:45:24', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149238305056366592', '1149237723692277760', '30', '2', '已全部分配', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:45:38', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:45:38', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149238386757214208', '1149237723692277760', '40', '3', '已下发', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:45:57', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:45:57', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149238451735371776', '1149237723692277760', '50', '4', '部分拣货', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:46:13', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:46:13', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149238506928218112', '1149237723692277760', '60', '5', '全部拣货', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:46:26', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:46:26', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150639141711159297', '1150638869735710720', '>1000', '4', '>1000', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'Y', to_date('15-07-2019 13:32:03', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:32:03', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150641616094666753', '1150641508552712193', '20', '1', '处理中', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'Y', to_date('15-07-2019 13:41:53', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:41:53', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150641642967572481', '1150641508552712193', '30', '3', '失败', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'Y', to_date('15-07-2019 13:42:00', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:42:00', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150641897180143617', '1150641803856879617', '10', '0', 'Rest', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'Y', to_date('15-07-2019 13:43:00', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:43:00', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150641923985940481', '1150641803856879617', '20', '1', 'Exception', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'Y', to_date('15-07-2019 13:43:07', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:43:07', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153578551583621120', '1148778625205497856', '10', '0', '普通', null, '10', 'Y', 'Y', '1136860898699380000', '1', 'N', '叶传开', to_date('23-07-2019 16:12:13', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('23-07-2019 16:12:13', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1155755151623598080', '1151024877213286400', 'BG', '10', '袋', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('29-07-2019 16:21:15', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('29-07-2019 16:21:15', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1157168028288970753', '1157167788097957888', 'CC', '2', '盘点差异', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', '叶传开', to_date('02-08-2019 13:55:31', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('02-08-2019 13:55:31', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('13', '8', '10', '0', '新建', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('14', '8', '20', '1', '收货中', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('15', '8', '30', '2', '收货完成', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('16', '8', '40', '3', '已关闭', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('17', '8', '50', '4', '已取消', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('18', '7', '30', '0', '退货入库', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('20-06-2019 17:03:05', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('20-06-2019 17:03:05', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('19', '7', '20', '0', '杂项入库', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('20-06-2019 17:03:05', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('20-06-2019 17:03:05', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('20', '7', '10', '0', '普通入库', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('20-06-2019 17:03:05', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('20-06-2019 17:03:05', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1145535788132954112', '1145535582284902400', 'PK', '1', '拣货', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('01-07-2019 11:33:09', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('01-07-2019 11:33:09', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146331456418181120', '1146327813816143872', '10', '0', '从指定库位移出时将库存上架到目标库位', null, '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 16:14:51', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 16:14:51', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1145535716238389248', '1145535582284902400', 'RP', '0', '补货', null, '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('01-07-2019 11:32:52', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('01-07-2019 11:33:00', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146331508557574144', '1146327813816143872', '20', '1', '从指定库位移出时将库存上架到目标库区中空库位', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 16:15:04', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 16:15:04', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146331581211308032', '1146327813816143872', '30', '2', '从指定库区移出时将库存上架到目标库位', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 16:15:21', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 16:15:21', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146331685590757376', '1146327813816143872', '40', '3', '从指定库区移出时将库存上架到目标库区中空库位', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 16:15:46', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 16:15:46', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146331747083448320', '1146327813816143872', '50', '4', '上架到货品主数据配置的上架库区中的空库位', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 16:16:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('05-08-2019 13:59:06', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146586216097513472', '1146585958147817473', '10', '0', '已分配', null, '10', 'Y', 'Y', '1136860898699380000', '1', 'N', 'ADMIN', to_date('04-07-2019 09:07:11', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 09:07:11', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146586263547674625', '1146585958147817473', '20', '2', '已拣货', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('04-07-2019 09:07:22', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('18-07-2019 13:48:57', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146586322989350912', '1146585958147817473', '30', '3', '已装载', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('04-07-2019 09:07:36', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('18-07-2019 13:48:57', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146586467034333185', '1146585958147817473', '40', '4', '已发运', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('04-07-2019 09:08:10', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('18-07-2019 13:48:57', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147032925173813248', '1146717773715066880', '20', '1', '执行中', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('05-07-2019 14:42:14', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('05-07-2019 14:42:14', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147032972175183872', '1146717773715066880', '30', '2', '已完成', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('05-07-2019 14:42:26', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('05-07-2019 14:42:26', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147033029750394880', '1146717773715066880', '40', '3', '等待', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('05-07-2019 14:42:39', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('05-07-2019 14:42:39', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147033079276736512', '1146717773715066880', '50', '4', '已取消', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('05-07-2019 14:42:51', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('05-07-2019 14:42:51', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147033217160286208', '1147032585254834176', 'PA', '0', '上架', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('05-07-2019 14:43:24', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('05-07-2019 14:43:24', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147033263394099200', '1147032585254834176', 'PK', '1', '拣货', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('05-07-2019 14:43:35', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('05-07-2019 14:43:35', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1154252580585930752', '1153988103054729217', '50', '50', '处理失败', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('25-07-2019 12:50:34', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('25-07-2019 12:50:34', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1154667715167342592', '1154209172483121153', 'OUTBOUND', '20', '出库单', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('26-07-2019 16:20:10', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('26-07-2019 16:20:10', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1', '1', 'zh_CN', '0', '中文', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('11-06-2019 16:06:03', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('11-06-2019 16:06:03', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('2', '1', 'en_US', '1', 'English', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('11-06-2019 16:06:19', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('11-06-2019 16:06:19', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146332052764323840', '1146331992131465216', '1', '0', '按库位路线顺序', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 16:17:13', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 16:17:13', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146332145252921344', '1146331992131465216', '2', '1', '按库位顺序', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 16:17:35', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 16:17:35', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146586060383977473', '1146585844247298049', '10', '0', '硬分配', null, '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('04-07-2019 09:06:33', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 09:06:33', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146586129459970049', '1146585844247298049', '20', '1', '软分配', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('04-07-2019 09:06:50', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 09:06:50', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1148477468012085248', '1144153120686329856', 'NORMAL', '1', '普通分配', null, '10', 'Y', 'N', '0', '0', 'N', 'Y', to_date('09-07-2019 14:22:20', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('09-07-2019 14:22:20', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1148557329069416449', '1144153316644212736', 'LOCATIONCODE', '1', '按库位代码顺序', null, '10', 'Y', 'N', '0', '0', 'N', 'Y', to_date('09-07-2019 19:39:41', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('09-07-2019 19:39:41', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1148557771174223873', '1144153316644212736', 'CLEAN', '3', '清空库存', null, '10', 'Y', 'N', '0', '0', 'N', 'Y', to_date('09-07-2019 19:41:26', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('09-07-2019 19:41:26', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149237530066427904', '1149237260691447808', '20', '1', '杂项出库', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:42:33', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('15-07-2019 16:12:24', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149237605672951808', '1149237260691447808', '30', '2', '退货出库', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:42:51', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('15-07-2019 16:12:24', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149238563832340480', '1149237723692277760', '70', '6', '部分发运', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:46:40', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:46:40', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149238610217148416', '1149237723692277760', '80', '7', '全部发运', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:46:51', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:46:51', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149238691070746624', '1149237723692277760', '90', '8', '已取消', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:47:10', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:47:10', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1151025098756423680', '1151024877213286400', 'CM', '0', '长度-厘米', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('16-07-2019 15:05:43', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('16-07-2019 15:06:27', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1151025248874758144', '1151024877213286400', 'KM', '1', '长度-千米', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('16-07-2019 15:06:19', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('16-07-2019 15:41:56', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152094950001467393', '6', '10', '10', '存储位', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('19-07-2019 13:56:55', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('19-07-2019 14:04:47', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152095026547515393', '6', '20', '20', '拣选位', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('19-07-2019 13:57:13', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('19-07-2019 14:04:47', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152095108860731393', '5', '10', '10', '平面库区', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('19-07-2019 13:57:33', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('19-07-2019 14:04:22', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152095155916627969', '5', '20', '20', '高架库区', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('19-07-2019 13:57:44', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('19-07-2019 14:04:22', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152095186195308545', '5', '30', '30', '自动化库区', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('19-07-2019 13:57:51', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('19-07-2019 14:04:22', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152096124410781696', '6', '30', '30', '虚拟', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('19-07-2019 14:01:35', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('19-07-2019 14:04:47', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152096775379345409', '5', '0', '0', '普通', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('19-07-2019 14:04:10', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('19-07-2019 14:04:10', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152096878404034561', '6', '0', '0', '普通', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('19-07-2019 14:04:35', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('19-07-2019 14:04:35', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153988063997370369', '1153986585106751488', 'EXPORT', '3', '导出', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:19:29', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:19:29', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1154209574679126017', '1154209172483121153', 'SKU', '30', '货品', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('25-07-2019 09:59:41', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('26-07-2019 16:19:46', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1154209623895089153', '1154209172483121153', 'LOCATION', '40', '库位', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('25-07-2019 09:59:53', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('26-07-2019 16:19:46', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1154209662105198593', '1154209172483121153', 'PACK', '50', '包装', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('25-07-2019 10:00:02', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('26-07-2019 16:19:46', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1154209699195428865', '1154209172483121153', 'ZONE', '60', '库区', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('25-07-2019 10:00:11', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('26-07-2019 16:19:46', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1158256133168717824', '1146327813816143872', '60', '5', '上架到货品主数据配置的上架库位', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('05-08-2019 13:59:16', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('05-08-2019 13:59:16', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1158256180551770113', '1146327813816143872', '100', '100', '上架到指定库位', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('05-08-2019 13:59:27', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('05-08-2019 13:59:27', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144080021152440320', '1143796303359733760', '10', '0', '普通', null, '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 11:08:27', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('23-07-2019 14:41:30', 'dd-mm-yyyy hh24:mi:ss'), '4', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144080081885962240', '1143796303359733760', '20', '1', '状态', null, '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 11:08:42', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('23-07-2019 14:41:30', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146227982884392960', '1146227246771458048', 'A', '3', '调整', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('03-07-2019 09:23:41', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 09:23:41', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146612351855632384', '1146612218820698112', '10', '0', '纸箱', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', 'ADMIN', to_date('04-07-2019 10:51:02', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 10:51:02', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1148557658649436160', '1144153316644212736', 'LOCATIONLOGICAL', '2', '按库位路线顺序', null, '10', 'Y', 'N', '0', '0', 'N', 'Y', to_date('09-07-2019 19:40:59', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('09-07-2019 19:40:59', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1151730513189691392', '1146585958147817473', '15', '1', '已发放', null, '10', 'Y', 'N', '1136860898699380000', '1', 'N', '叶传开', to_date('18-07-2019 13:48:47', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('18-07-2019 13:48:47', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153988003377094656', '1153986585106751488', 'SAVE', '0', '存储', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:19:14', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:19:14', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153988033651580929', '1153986585106751488', 'IMPORT', '2', '导入', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:19:21', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:19:21', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153988191726510081', '1153988103054729217', '0', '0', '初始化', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:19:59', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:19:59', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153988262308257793', '1153988103054729217', '10', '2', '上传失败', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:20:16', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:20:16', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153988295405510657', '1153988103054729217', '20', '4', '上传成功', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:20:24', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:20:24', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153988376733065217', '1153988103054729217', '30', '6', '处理中', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:20:43', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:20:43', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153988404260282368', '1153988103054729217', '40', '8', '处理完成', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:20:50', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:20:50', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1154209532144689153', '1154209172483121153', 'INBOUND', '10', '入库单', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('25-07-2019 09:59:31', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('25-07-2019 09:59:31', 'dd-mm-yyyy hh24:mi:ss'), '1', null);


