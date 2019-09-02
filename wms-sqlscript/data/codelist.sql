???prompt Importing table wms.sys_codelist_t...
set feedback off
set define off
insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('2', 'YESNO', '是/否', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', null, null, '叶传开', to_date('23-07-2019 14:40:29', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('3', 'CONFIGTYPE', '参数类型', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', null, null, '叶传开', to_date('23-07-2019 14:40:30', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('4', 'PERMISSIONTYPE', '权限类型', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', null, null, '叶传开', to_date('23-07-2019 14:40:30', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('5', 'ZONETYPE', '库区类型', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', null, null, '叶传开', to_date('23-07-2019 14:40:30', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('6', 'LOCATIONTYPE', '库位类型', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', null, null, '叶传开', to_date('23-07-2019 14:40:30', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144153938701438976', 'ALLOCATEFIFO', '分配策略先进先出规则', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 16:02:10', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('09-07-2019 19:47:38', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147032672559271936', 'SOURCETYPE', '来源状态', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('05-07-2019 14:41:14', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('05-07-2019 14:41:39', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150641508552712193', 'MONITORSTATUS', '监控状态', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'Y', to_date('15-07-2019 13:41:28', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:41:28', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1155729545159589888', 'SHIPLABEL', '发货标签', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('29-07-2019 14:39:30', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('29-07-2019 14:39:30', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1157167788097957888', 'ADJUSTMENTREASON', '调整原因', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', '叶传开', to_date('02-08-2019 13:54:34', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('02-08-2019 13:54:34', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144153316644212736', 'ALLOCATESORT', '分配策略排序规则', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 15:59:42', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('09-07-2019 19:47:19', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144153120686329856', 'ALLOCATETYPE', '分配策略明细类型', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 15:58:55', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 15:58:55', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144153235069194240', 'ALLOCATEUOM', '单位', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 15:59:23', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 15:59:23', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149237260691447808', 'OUTBOUNDTYPE', '出库单据类型', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:41:29', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:41:29', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149237723692277760', 'OUTBOUNDSTATUS', '出库单状态', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:43:19', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:43:19', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150638869735710720', 'REQUESTIME', '请求时间', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'Y', to_date('15-07-2019 13:30:59', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:30:59', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150641803856879617', 'MONITORTYPE', '监控类型', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'Y', to_date('15-07-2019 13:42:38', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:42:38', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152146764932419584', 'ADJUSTMENTSTATUS', '库存调整状态', '20', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('19-07-2019 17:22:49', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('19-07-2019 17:22:49', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('7', 'INBOUNDTYPE', '入库类型', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', null, null, '叶传开', to_date('23-07-2019 14:40:30', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('8', 'INBOUNDSTATUS', '入库状态', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', null, null, '叶传开', to_date('23-07-2019 14:40:30', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1143796303359733760', 'CODETYPE', '字典类型', '1', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('26-06-2019 16:21:04', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('01-07-2019 11:26:38', 'dd-mm-yyyy hh24:mi:ss'), '5', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146227246771458048', 'TRANSACTIONTYPE', '交易类型', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 09:20:46', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 09:20:46', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146331992131465216', 'PUTAWAYSORT', '上架策略排序规则', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 16:16:59', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 16:16:59', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146585844247298049', 'ALLOCATESTRATEGYTYPE', '分配类型', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('04-07-2019 09:05:42', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 09:05:42', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146585958147817473', 'ALLOCATESTATUS', '分配明细状态', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('04-07-2019 09:06:09', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 09:06:09', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146717773715066880', 'TASKSTATUS', '任务状态', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('04-07-2019 17:49:56', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 17:49:56', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147032585254834176', 'TASKTYPE', '任务类型', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('05-07-2019 14:40:53', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('05-07-2019 14:40:53', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1', 'LOCALE', '语言', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', null, null, '叶传开', to_date('23-07-2019 14:40:30', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146327813816143872', 'PUTAWAYTYPE', '上架策略类型', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 16:00:23', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 16:00:23', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1151024877213286400', 'UOM', '单位', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('16-07-2019 15:04:50', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('23-07-2019 14:40:05', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153988103054729217', 'FILESTATUS', '文件状态', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:19:38', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:19:38', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1154209172483121153', 'TEMPLATE', '模板', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', '叶传开', to_date('25-07-2019 09:58:05', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('25-07-2019 09:58:05', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153986585106751488', 'FILETYPE', '文件操作类型', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:13:36', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:13:36', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1145535582284902400', 'AREATYPE', '区域类型', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('01-07-2019 11:32:20', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('01-07-2019 11:32:20', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146230863901478912', 'TRANSACTIONCATEGORY', '交易类别', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 09:35:08', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 09:35:08', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146612218820698112', 'LPNTYPE', 'LPN类型', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('04-07-2019 10:50:30', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 10:50:30', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1148778625205497856', 'SKUTYPE', '货品类型', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'ADMIN', to_date('10-07-2019 10:19:02', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('10-07-2019 10:19:02', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1164430737871101952', 'SHIPLABEL', '发货标签', null, 'Y', 'N                                                                                                   ', '2', '2', 'N', 'YECHUANKAI', to_date('22-08-2019 14:54:56', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('22-08-2019 14:54:56', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1164460273333596160', 'LOCKFLAG', '冻结标识', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'YECHUANKAI', to_date('22-08-2019 16:52:18', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('22-08-2019 16:52:18', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1164460341621059585', 'LOCKREASON', '冻结原因', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'YECHUANKAI', to_date('22-08-2019 16:52:34', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('22-08-2019 16:52:34', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

prompt Done.
