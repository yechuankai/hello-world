???prompt Importing table wms.sys_codelkup_t...
set feedback off
set define off
insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('3', '2', 'Y', '0', '是', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('14-06-2019 16:33:58', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('14-06-2019 16:33:58', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('4', '2', 'N', '1', '否', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('14-06-2019 16:33:58', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('14-06-2019 16:33:58', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('5', '3', 'O', '0', '开关', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('14-06-2019 16:36:01', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('14-06-2019 16:36:01', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('6', '3', 'V', '1', '值', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('14-06-2019 16:36:01', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('14-06-2019 16:36:01', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('7', '4', 'M', '0', '菜单', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('14-06-2019 16:36:59', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('14-06-2019 16:36:59', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('8', '4', 'F', '1', '功能', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('14-06-2019 16:36:59', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('14-06-2019 16:36:59', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('21', '1', 'fr_FR', '2', 'Français', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('21-06-2019 09:36:45', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('21-08-2019 16:24:09', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146227369232551936', '1146227246771458048', 'I', '0', '入库', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 09:21:15', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 09:21:15', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146227441794011136', '1146227246771458048', 'M', '1', '移动', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 09:21:32', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 09:21:32', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144075095491842048', '1144075018941599744', '10', '0', '硬分配', null, 'Y', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 10:48:53', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 10:48:53', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144153774683181056', '1144153235069194240', 'PCS', '0', '按件', null, 'Y', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 16:01:31', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 16:01:31', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144153840588279808', '1144153235069194240', 'LPN', '1', '按LPN', null, 'Y', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 16:01:47', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 16:01:47', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150639106059575297', '1150638869735710720', '500-1000', '3', '500-1000', null, '10', 'Y', 'N', '0', '0', 'N', 'Y', to_date('15-07-2019 13:31:55', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:31:55', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144154239030382592', '1144153938701438976', 'LOTATTRIBUTE4', '1', '按批属性4  生产日期', null, 'Y', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 16:03:22', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 16:04:11', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144154572909563904', '1144153938701438976', 'LOTATTRIBUTE5', '3', '按批属性11', null, 'Y', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 16:04:42', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 16:04:42', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146227898977341440', '1146227246771458048', 'O', '2', '出库', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 09:23:21', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 09:23:21', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146612408738783232', '1146612218820698112', '20', '1', '托盘', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('04-07-2019 10:51:15', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 10:51:15', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146612470642515968', '1146612218820698112', '30', '2', '容器', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('04-07-2019 10:51:30', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 10:51:30', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147032874997354496', '1146717773715066880', '10', '0', '新建', null, '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('05-07-2019 14:42:02', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('05-07-2019 14:42:02', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150638981279031296', '1150638869735710720', '0-100', '0', '0-100', null, '10', 'Y', 'N', '0', '0', 'N', 'Y', to_date('15-07-2019 13:31:25', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('15-07-2019 15:32:45', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150639027743531009', '1150638869735710720', '100-500', '2', '100-500', null, '10', 'Y', 'N', '0', '0', 'N', 'Y', to_date('15-07-2019 13:31:36', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:31:36', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150641582540234753', '1150641508552712193', '10', '0', '成功', null, '10', 'Y', 'N', '0', '0', 'N', 'Y', to_date('15-07-2019 13:41:45', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:41:45', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152146908918681600', '1152146764932419584', '10', '0', '新建', null, '20', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('19-07-2019 17:23:23', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('19-07-2019 17:23:30', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152146977940148224', '1152146764932419584', '20', '1', '已完成', null, '20', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('19-07-2019 17:23:40', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('19-07-2019 17:23:40', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153554631350456320', '1151024877213286400', 'EA', '0', '件', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('23-07-2019 14:37:10', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('23-07-2019 14:37:10', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153554687533158400', '1151024877213286400', 'CA', '2', '箱', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('23-07-2019 14:37:24', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('23-07-2019 14:37:24', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1157167865692581888', '1157167788097957888', 'NORMAL', '0', '普通调整', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('02-08-2019 13:54:52', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('02-08-2019 13:54:52', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1157167910651326465', '1157167788097957888', 'UNRECEIVE', '1', '撤销收货', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('02-08-2019 13:55:03', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('02-08-2019 13:55:03', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149237470356316160', '1149237260691447808', '10', '0', '普通出库', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:42:19', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('15-07-2019 16:12:24', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144075149640306688', '1144075018941599744', '20', '1', '软分配', null, 'Y', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 10:49:06', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 10:49:06', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144154393179443200', '1144153938701438976', 'LOTATTRIBUTE11', '2', '按批属性5  到期日期', null, 'Y', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 16:03:59', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 16:04:16', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144154638130991104', '1144153938701438976', 'LOTATTRIBUTE12', '4', '按批属性12', null, 'Y', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 16:04:57', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('27-06-2019 16:04:57', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149237803421802496', '1149237723692277760', '10', '0', '新建', null, '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:43:38', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:47:17', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149238246550020096', '1149237723692277760', '20', '1', '部分分配', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:45:24', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:45:24', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149238305056366592', '1149237723692277760', '30', '2', '已全部分配', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:45:38', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:45:38', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149238386757214208', '1149237723692277760', '40', '3', '已下发', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:45:57', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:45:57', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149238451735371776', '1149237723692277760', '50', '4', '部分拣货', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:46:13', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:46:13', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149238506928218112', '1149237723692277760', '60', '5', '全部拣货', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:46:26', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:46:26', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150639141711159297', '1150638869735710720', '>1000', '4', '>1000', null, '10', 'Y', 'N', '0', '0', 'N', 'Y', to_date('15-07-2019 13:32:03', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:32:03', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150641616094666753', '1150641508552712193', '20', '1', '处理中', null, '10', 'Y', 'N', '0', '0', 'N', 'Y', to_date('15-07-2019 13:41:53', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:41:53', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150641642967572481', '1150641508552712193', '30', '3', '失败', null, '10', 'Y', 'N', '0', '0', 'N', 'Y', to_date('15-07-2019 13:42:00', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:42:00', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150641897180143617', '1150641803856879617', '10', '0', 'Rest', null, '10', 'Y', 'N', '0', '0', 'N', 'Y', to_date('15-07-2019 13:43:00', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:43:00', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150641923985940481', '1150641803856879617', '20', '1', 'Exception', null, '10', 'Y', 'N', '0', '0', 'N', 'Y', to_date('15-07-2019 13:43:07', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('15-07-2019 13:43:07', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153578551583621120', '1148778625205497856', '10', '0', '普通', null, '10', 'Y', 'Y', '0', '0', 'N', '叶传开', to_date('23-07-2019 16:12:13', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('23-07-2019 16:12:13', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1155755151623598080', '1151024877213286400', 'BG', '10', '袋', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('29-07-2019 16:21:15', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('29-07-2019 16:21:15', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1157168028288970753', '1157167788097957888', 'CC', '2', '盘点差异', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('02-08-2019 13:55:31', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('02-08-2019 13:55:31', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('13', '8', '10', '0', '新建', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('14', '8', '20', '1', '收货中', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('15', '8', '30', '2', '收货完成', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('16', '8', '40', '3', '已关闭', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('17', '8', '50', '4', '已取消', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('20-06-2019 17:02:17', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('18', '7', '30', '0', '退货入库', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('20-06-2019 17:03:05', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('20-06-2019 17:03:05', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('19', '7', '20', '0', '杂项入库', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('20-06-2019 17:03:05', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('20-06-2019 17:03:05', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('20', '7', '10', '0', '普通入库', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('20-06-2019 17:03:05', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('20-06-2019 17:03:05', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1145535788132954112', '1145535582284902400', 'PK', '1', '拣货', null, '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('01-07-2019 11:33:09', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('21-08-2019 16:14:39', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146331456418181120', '1146327813816143872', '10', '0', '从指定库位移出时将库存上架到目标库位', null, '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 16:14:51', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 16:14:51', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1145535716238389248', '1145535582284902400', 'RP', '0', '补货', null, '10', 'N', 'N', '0', '0', 'N', 'ADMIN', to_date('01-07-2019 11:32:52', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('21-08-2019 16:14:39', 'dd-mm-yyyy hh24:mi:ss'), '4', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146331508557574144', '1146327813816143872', '20', '1', '从指定库位移出时将库存上架到目标库区中空库位', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 16:15:04', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 16:15:04', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146331581211308032', '1146327813816143872', '30', '2', '从指定库区移出时将库存上架到目标库位', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 16:15:21', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 16:15:21', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146331685590757376', '1146327813816143872', '40', '3', '从指定库区移出时将库存上架到目标库区中空库位', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 16:15:46', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 16:15:46', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146331747083448320', '1146327813816143872', '50', '4', '上架到货品主数据配置的上架库区中的空库位', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 16:16:00', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('05-08-2019 13:59:06', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146586216097513472', '1146585958147817473', '10', '0', '已分配', null, '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('04-07-2019 09:07:11', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 09:07:11', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146586263547674625', '1146585958147817473', '20', '2', '已拣货', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('04-07-2019 09:07:22', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('18-07-2019 13:48:57', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146586322989350912', '1146585958147817473', '30', '3', '已装载', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('04-07-2019 09:07:36', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('18-07-2019 13:48:57', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146586467034333185', '1146585958147817473', '40', '4', '已发运', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('04-07-2019 09:08:10', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('18-07-2019 13:48:57', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147032925173813248', '1146717773715066880', '20', '1', '执行中', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('05-07-2019 14:42:14', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('05-07-2019 14:42:14', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147032972175183872', '1146717773715066880', '30', '2', '已完成', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('05-07-2019 14:42:26', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('05-07-2019 14:42:26', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147033029750394880', '1146717773715066880', '40', '3', '等待', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('05-07-2019 14:42:39', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('05-07-2019 14:42:39', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147033079276736512', '1146717773715066880', '50', '4', '已取消', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('05-07-2019 14:42:51', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('05-07-2019 14:42:51', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147033217160286208', '1147032585254834176', 'PA', '0', '上架', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('05-07-2019 14:43:24', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('05-07-2019 14:43:24', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147033263394099200', '1147032585254834176', 'PK', '1', '拣货', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('05-07-2019 14:43:35', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('05-07-2019 14:43:35', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1154252580585930752', '1153988103054729217', '50', '50', '处理失败', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('25-07-2019 12:50:34', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('25-07-2019 12:50:34', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1154667715167342592', '1154209172483121153', 'OUTBOUND', '20', '出库单', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('26-07-2019 16:20:10', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('26-07-2019 16:20:10', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1', '1', 'zh_CN', '0', '中文', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('11-06-2019 16:06:03', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('11-06-2019 16:06:03', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('2', '1', 'en_US', '1', 'English', null, null, 'Y', 'N', '0', '0', 'N', null, to_date('11-06-2019 16:06:19', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('11-06-2019 16:06:19', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146332052764323840', '1146331992131465216', 'LOCATIONLOGICAL', '0', '按库位路线顺序', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 16:17:13', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 16:17:13', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146332145252921344', '1146331992131465216', 'LOCATIONCODE', '1', '按库位顺序', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 16:17:35', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 16:17:35', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146586060383977473', '1146585844247298049', '10', '0', '硬分配', null, '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('04-07-2019 09:06:33', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 09:06:33', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146586129459970049', '1146585844247298049', '20', '1', '软分配', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('04-07-2019 09:06:50', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 09:06:50', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1148477468012085248', '1144153120686329856', 'NORMAL', '1', '普通分配', null, '10', 'Y', 'N', '0', '0', 'N', 'Y', to_date('09-07-2019 14:22:20', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('09-07-2019 14:22:20', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1148557329069416449', '1144153316644212736', 'LOCATIONCODE', '1', '按库位代码顺序', null, '10', 'Y', 'N', '0', '0', 'N', 'Y', to_date('09-07-2019 19:39:41', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('09-07-2019 19:39:41', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1148557771174223873', '1144153316644212736', 'CLEAN', '3', '清空库存', null, '10', 'Y', 'N', '0', '0', 'N', 'Y', to_date('09-07-2019 19:41:26', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('09-07-2019 19:41:26', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149237530066427904', '1149237260691447808', '20', '1', '杂项出库', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:42:33', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('15-07-2019 16:12:24', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149237605672951808', '1149237260691447808', '30', '2', '退货出库', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:42:51', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('15-07-2019 16:12:24', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149238563832340480', '1149237723692277760', '70', '6', '部分发运', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:46:40', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:46:40', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149238610217148416', '1149237723692277760', '80', '7', '全部发运', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:46:51', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:46:51', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1149238691070746624', '1149237723692277760', '90', '8', '已取消', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('11-07-2019 16:47:10', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('11-07-2019 16:47:10', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1151025098756423680', '1151024877213286400', 'CM', '0', '长度-厘米', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('16-07-2019 15:05:43', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('16-07-2019 15:06:27', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1151025248874758144', '1151024877213286400', 'KM', '1', '长度-千米', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('16-07-2019 15:06:19', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('16-07-2019 15:41:56', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152094950001467393', '6', '10', '10', '存储位', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('19-07-2019 13:56:55', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('19-07-2019 14:04:47', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152095026547515393', '6', '20', '20', '拣选位', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('19-07-2019 13:57:13', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('19-07-2019 14:04:47', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152095108860731393', '5', '10', '10', '平面库区', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('19-07-2019 13:57:33', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('19-07-2019 14:04:22', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152095155916627969', '5', '20', '20', '高架库区', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('19-07-2019 13:57:44', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('19-07-2019 14:04:22', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152095186195308545', '5', '30', '30', '自动化库区', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('19-07-2019 13:57:51', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('19-07-2019 14:04:22', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152096124410781696', '6', '30', '30', '虚拟', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('19-07-2019 14:01:35', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('19-07-2019 14:04:47', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152096775379345409', '5', '0', '0', '普通', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('19-07-2019 14:04:10', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('19-07-2019 14:04:10', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1152096878404034561', '6', '0', '0', '普通', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('19-07-2019 14:04:35', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('19-07-2019 14:04:35', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153988063997370369', '1153986585106751488', 'EXPORT', '3', '导出', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:19:29', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:19:29', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1154209574679126017', '1154209172483121153', 'SKU', '30', '货品', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('25-07-2019 09:59:41', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('26-07-2019 16:19:46', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1154209623895089153', '1154209172483121153', 'LOCATION', '40', '库位', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('25-07-2019 09:59:53', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('26-07-2019 16:19:46', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1154209662105198593', '1154209172483121153', 'PACK', '50', '包装', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('25-07-2019 10:00:02', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('26-07-2019 16:19:46', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1154209699195428865', '1154209172483121153', 'ZONE', '60', '库区', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('25-07-2019 10:00:11', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('26-07-2019 16:19:46', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1158256133168717824', '1146327813816143872', '60', '5', '上架到货品主数据配置的上架库位', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('05-08-2019 13:59:16', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('05-08-2019 13:59:16', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1158256180551770113', '1146327813816143872', '100', '100', '上架到指定库位', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('05-08-2019 13:59:27', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('05-08-2019 13:59:27', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144080021152440320', '1143796303359733760', '10', '0', '普通', null, '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 11:08:27', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('23-07-2019 14:41:30', 'dd-mm-yyyy hh24:mi:ss'), '4', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144080081885962240', '1143796303359733760', '20', '1', '状态', null, '10', 'Y', 'Y', '0', '0', 'N', 'ADMIN', to_date('27-06-2019 11:08:42', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('23-07-2019 14:41:30', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146227982884392960', '1146227246771458048', 'A', '3', '调整', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('03-07-2019 09:23:41', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('03-07-2019 09:23:41', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146612351855632384', '1146612218820698112', '10', '0', '纸箱', null, '10', 'Y', 'N', '0', '0', 'N', 'ADMIN', to_date('04-07-2019 10:51:02', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('04-07-2019 10:51:02', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1148557658649436160', '1144153316644212736', 'LOCATIONLOGICAL', '2', '按库位路线顺序', null, '10', 'Y', 'N', '0', '0', 'N', 'Y', to_date('09-07-2019 19:40:59', 'dd-mm-yyyy hh24:mi:ss'), 'Y', to_date('09-07-2019 19:40:59', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1151730513189691392', '1146585958147817473', '15', '1', '已发放', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('18-07-2019 13:48:47', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('18-07-2019 13:48:47', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153988003377094656', '1153986585106751488', 'SAVE', '0', '存储', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:19:14', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:19:14', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153988033651580929', '1153986585106751488', 'IMPORT', '2', '导入', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:19:21', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:19:21', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153988191726510081', '1153988103054729217', '0', '0', '初始化', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:19:59', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:19:59', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153988262308257793', '1153988103054729217', '10', '2', '上传失败', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:20:16', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:20:16', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153988295405510657', '1153988103054729217', '20', '4', '上传成功', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:20:24', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:20:24', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153988376733065217', '1153988103054729217', '30', '6', '处理中', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:20:43', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:20:43', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1153988404260282368', '1153988103054729217', '40', '8', '处理完成', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('24-07-2019 19:20:50', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('24-07-2019 19:20:50', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1154209532144689153', '1154209172483121153', 'INBOUND', '10', '入库单', null, '10', 'Y', 'N', '0', '0', 'N', '叶传开', to_date('25-07-2019 09:59:31', 'dd-mm-yyyy hh24:mi:ss'), '叶传开', to_date('25-07-2019 09:59:31', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1164430938014900224', '1164430737871101952', 'A01', '1', 'YE', null, '10', 'Y', 'N', '2', '2', 'N', 'YECHUANKAI', to_date('22-08-2019 14:55:44', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('22-08-2019 14:55:44', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1164372674120245248', '1144153235069194240', 'LOCATION', '2', '按库位', null, '10', 'Y', 'N', '0', '0', 'N', 'YECHUANKAI', to_date('22-08-2019 11:04:13', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('22-08-2019 11:04:13', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1164460417118531584', '1164460273333596160', 'Y', '0', '冻结', null, '10', 'Y', 'N', '0', '0', 'N', 'YECHUANKAI', to_date('22-08-2019 16:52:52', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('22-08-2019 16:52:52', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1164460478984515585', '1164460273333596160', 'N', '2', '普通', null, '10', 'Y', 'N', '0', '0', 'N', 'YECHUANKAI', to_date('22-08-2019 16:53:07', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('22-08-2019 16:53:07', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1164460542687604737', '1164460341621059585', 'BAD', '1', '损坏', null, '10', 'Y', 'N', '0', '0', 'N', 'YECHUANKAI', to_date('22-08-2019 16:53:22', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('22-08-2019 16:53:22', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_codelkup_t (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1164460578536321025', '1164460341621059585', 'LOST', '2', '丢失', null, '10', 'Y', 'N', '0', '0', 'N', 'YECHUANKAI', to_date('22-08-2019 16:53:31', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('22-08-2019 16:53:31', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

prompt Done.
