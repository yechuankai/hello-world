--菜单
delete UUMS.WEB_FUNC where func_id = 'wms:system:report';
insert into UUMS.WEB_FUNC (func_id, func_name, menu_id, func_desc, func_url, func_id_seq, sys_code, is_valid, sys_url, real_sys_code, invalid_time, invalid_user, create_time, version, func_unique_id)
values ('wms:system:report', '报表管理', 'wms:system', '报表管理', 'report/report', 10, 'WMS', 'Y', null, null, null, null, to_date('16-08-2019 13:53:20', 'dd-mm-yyyy hh24:mi:ss'), '0', 1136869832319441005);

delete uums.sc_mappingconfig_lang where table_id = 'wms:system:report';
insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1301, 'WEB_FUNC', 'FUNC_NAME', 'en_US', 'Report Management', 'Report Management', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:system:report', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1302, 'WEB_FUNC', 'FUNC_NAME', 'fr_FR', 'Rapport de gestion', 'Rapport de gestion', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:system:report', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1303, 'WEB_FUNC', 'FUNC_NAME', 'zh_CN', '报表管理', '报表管理', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:system:report', 'WMS');



--数据字典
insert into wms.sys_codelist_t (CODELIST_ID, CODE, DESCR, TYPE, ACTIVE, IS_ALL, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1168590157904478208', 'REPORT', 'REPORT', '10', 'Y', 'Y                                                                                                   ', '0', '0', 'N', 'yechuankai', to_date('02-09-2019 21:22:59', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('02-09-2019 21:22:59', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into WMS.SYS_CODELKUP_T (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1168590411789893632', '1168590157904478208', 'INBOUND', '1', 'Receive Peport', null, '10', 'Y', 'N', '0', '0', 'N', 'yechuankai', to_date('02-09-2019 21:24:00', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('02-09-2019 21:24:00', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into WMS.SYS_CODELKUP_T (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1168590489703284736', '1168590157904478208', 'INBOUNDCONFIRM', '2', 'Receive Summary Report', null, '10', 'Y', 'N', '0', '0', 'N', 'yechuankai', to_date('02-09-2019 21:24:18', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('02-09-2019 21:24:18', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into WMS.SYS_CODELKUP_T (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1168591847642759168', '1168590157904478208', 'PICKLIST', '3', 'Pick List Report', null, '10', 'Y', 'N', '0', '0', 'N', 'yechuankai', to_date('02-09-2019 21:29:42', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('02-09-2019 21:29:42', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into WMS.SYS_CODELKUP_T (CODELKUP_ID, CODELIST_ID, CODE, SORT, DESCR, REMARK, TYPE, ACTIVE, IS_DEFAULT, COMPANY_ID, WAREHOUSE_ID, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1168591938462023680', '1168590157904478208', 'A01', '4', 'Ship label', null, '10', 'Y', 'N', '0', '0', 'N', 'yechuankai', to_date('02-09-2019 21:30:04', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('02-09-2019 21:30:04', 'dd-mm-yyyy hh24:mi:ss'), '1', null);



--新增"出库单创建"的子菜单
insert into uums.WEB_FUNC (FUNC_ID, FUNC_NAME, MENU_ID, FUNC_DESC, FUNC_URL, FUNC_ID_SEQ, SYS_CODE, IS_VALID, SYS_URL, REAL_SYS_CODE, INVALID_TIME, INVALID_USER, CREATE_TIME, VERSION, FUNC_UNIQUE_ID)
values ('wms:outbound:add', '出库单创建', 'wms:outbound', '出库单创建', 'outbound/edit', 10, 'WMS', 'Y', null, null, null, null, to_date('21-08-2019 14:08:28', 'dd-mm-yyyy hh24:mi:ss'), '0', 1.15571722259636E18);



DELETE wms.sys_locale_t WHERE TABLE_NAME = 'CODELKUP' AND JOIN_KEY1 = 'ALLOCATESTATUS';
insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156046792318976051', 'en_US', 'ALLOCATESTATUS_10', 'Allocated', 'ALLOCATESTATUS', '10', null, null, null, 'CODELKUP', 'Y', 'N', null, to_date('22-08-2019 11:10:59', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('22-08-2019 11:10:59', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156046792318976052', 'en_US', 'ALLOCATESTATUS_20', 'Release', 'ALLOCATESTATUS', '15', null, null, null, 'CODELKUP', 'Y', 'N', null, to_date('22-08-2019 11:10:59', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('22-08-2019 11:10:59', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156046792318976053', 'en_US', 'ALLOCATESTATUS_30', 'Picked', 'ALLOCATESTATUS', '20', null, null, null, 'CODELKUP', 'Y', 'N', null, to_date('22-08-2019 11:10:59', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('22-08-2019 11:10:59', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156046792318976054', 'en_US', 'ALLOCATESTATUS_40', 'Load', 'ALLOCATESTATUS', '30', null, null, null, 'CODELKUP', 'Y', 'N', null, to_date('22-08-2019 11:10:59', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('22-08-2019 11:10:59', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156046792318976055', 'en_US', 'ALLOCATESTATUS_50', 'Ship', 'ALLOCATESTATUS', '40', null, null, null, 'CODELKUP', 'Y', 'N', null, to_date('22-08-2019 11:10:59', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('22-08-2019 11:10:59', 'dd-mm-yyyy hh24:mi:ss'), '1', null);


DELETE wms.sys_locale_t WHERE TABLE_NAME = 'CODELKUP' AND JOIN_KEY1 = 'ALLOCATEUOM';
insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156046792318976013', 'en_US', 'ALLOCATEUOM_PCS', 'Pcs', 'ALLOCATEUOM', 'PCS', null, null, null, 'CODELKUP', 'Y', 'N', null, to_date('21-08-2019 18:07:14', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-08-2019 18:07:14', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156046792318976014', 'en_US', 'ALLOCATEUOM_LPN', 'LPN', 'ALLOCATEUOM', 'LPN', null, null, null, 'CODELKUP', 'Y', 'N', null, to_date('21-08-2019 18:07:14', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-08-2019 18:07:14', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1156046792318976015', 'en_US', 'ALLOCATEUOM_PCS', 'Location', 'ALLOCATEUOM', 'LOCATION', null, null, null, 'CODELKUP', 'Y', 'N', null, to_date('21-08-2019 18:07:14', 'dd-mm-yyyy hh24:mi:ss'), null, to_date('21-08-2019 18:07:14', 'dd-mm-yyyy hh24:mi:ss'), '1', null);


DELETE wms.sys_locale_t where LOCALE_ID in (1146958002500808705, 1150589762343026688, 1150590036726005760);
insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146958002500808705', 'en_US', 'web.label.quantity', 'Quantity', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('05-07-2019 09:44:31', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('03-09-2019 17:06:30', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150589762343026688', 'en_US', 'web.label.uomquantitypicked', 'Picked Quantity', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('15-07-2019 10:15:50', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('03-09-2019 17:23:18', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150590036726005760', 'en_US', 'web.label.uomquantityshiped', 'Shiped Quantity', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('15-07-2019 10:16:56', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('03-09-2019 17:23:39', 'dd-mm-yyyy hh24:mi:ss'), '2', null);


--菜单波次模板
delete uums.WEB_FUNC where func_id = 'wms:outbound:wave:template';
insert into UUMS.WEB_FUNC (func_id, func_name, menu_id, func_desc, func_url, func_id_seq, sys_code, is_valid, sys_url, real_sys_code, invalid_time, invalid_user, create_time, version, func_unique_id)
values ('wms:outbound:wave:template', '波次模板', 'wms:outbound', '波次模板', 'outbound/waveTemplate', 9, 'WMS', 'Y', null, null, null, null, to_date('16-08-2019 13:53:20', 'dd-mm-yyyy hh24:mi:ss'), '0', 1136869832319441001);

delete uums.sc_mappingconfig_lang where TABLE_NAME = 'WEB_FUNC' and table_id = 'wms:outbound:wave:template';
insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1311, 'WEB_FUNC', 'FUNC_NAME', 'en_US', 'Wave Template', 'Wave Template', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:outbound:wave:template', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1312, 'WEB_FUNC', 'FUNC_NAME', 'fr_FR', 'Rapport de gestion', 'Rapport de gestion', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:outbound:wave:template', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1313, 'WEB_FUNC', 'FUNC_NAME', 'zh_CN', '波次模板', '波次模板', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:outbound:wave:template', 'WMS');

--菜单波次
delete uums.WEB_FUNC where func_id = 'wms:outbound:wave';
insert into UUMS.WEB_FUNC (func_id, func_name, menu_id, func_desc, func_url, func_id_seq, sys_code, is_valid, sys_url, real_sys_code, invalid_time, invalid_user, create_time, version, func_unique_id)
values ('wms:outbound:wave', '波次', 'wms:outbound', '波次', 'outbound/wave', 10, 'WMS', 'Y', null, null, null, null, to_date('16-08-2019 13:53:20', 'dd-mm-yyyy hh24:mi:ss'), '0', 1136869832319441002);

delete uums.sc_mappingconfig_lang where TABLE_NAME = 'WEB_FUNC' and table_id = 'wms:outbound:wave';
insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1314, 'WEB_FUNC', 'FUNC_NAME', 'en_US', 'Wave', 'Wave', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:outbound:wave', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1315, 'WEB_FUNC', 'FUNC_NAME', 'fr_FR', 'Vague', 'Vague', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:outbound:wave', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1316, 'WEB_FUNC', 'FUNC_NAME', 'zh_CN', '波次', '波次', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:outbound:wave', 'WMS');



--不启用发放功能
update uums.web_func set IS_VALID = 'N' where func_id = 'wms:outbound:pickTask';

DELETE wms.sys_locale_t where LOCALE_ID in (1144493236680257536, 1144493284944113664);
insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144493236680257536', 'en_US', 'web.label.fiforule', 'FIFO Rul', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('28-06-2019 14:30:25', 'dd-mm-yyyy hh24:mi:ss'), 'wh2admin', to_date('05-09-2019 22:13:24', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1144493284944113664', 'fr_FR', 'web.label.fiforule', 'Règle FIFO', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('28-06-2019 14:30:37', 'dd-mm-yyyy hh24:mi:ss'), 'wh2admin', to_date('05-09-2019 22:13:24', 'dd-mm-yyyy hh24:mi:ss'), '2', null);


DELETE wms.sys_locale_t where LOCALE_ID in (1169689289121464321, 1169688987018330112, 1169689033109536769);
insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1169689289121464321', 'fr_FR', 'create.task.success', 'Créer 5 tâches', null, null, null, null, null, null, 'Y', 'N', 'wh2admin', to_date('05-09-2019 22:10:32', 'dd-mm-yyyy hh24:mi:ss'), 'wh2admin', to_date('05-09-2019 22:10:32', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1169688987018330112', 'zh_CN', 'create.task.success', '创建 {0} 条任务', null, null, null, null, null, null, 'Y', 'N', 'wh2admin', to_date('05-09-2019 22:09:20', 'dd-mm-yyyy hh24:mi:ss'), 'wh2admin', to_date('05-09-2019 22:09:20', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1169689033109536769', 'en_US', 'create.task.success', 'Create {0} Tasks', null, null, null, null, null, null, 'Y', 'N', 'wh2admin', to_date('05-09-2019 22:09:31', 'dd-mm-yyyy hh24:mi:ss'), 'wh2admin', to_date('05-09-2019 22:11:03', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

DELETE wms.sys_locale_t where TABLE_NAME = 'CODELKUP' AND JOIN_KEY1 = 'ALLOCATEFIFO';
insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1169689289121464322', 'en_US', 'ALLOCATEFIFO_LOTATTRIBUTE4', 'Lot4', 'ALLOCATEFIFO', 'LOTATTRIBUTE4', null, null, null, 'CODELKUP', 'Y', 'Y', 'ADMIN', to_date('21-06-2019 12:57:45', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('21-06-2019 13:00:56', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1169689289121464323', 'en_US', 'ALLOCATEFIFO_LOTATTRIBUTE5', 'Lot5', 'ALLOCATEFIFO', 'LOTATTRIBUTE5', null, null, null, 'CODELKUP', 'Y', 'Y', 'ADMIN', to_date('21-06-2019 12:57:45', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('21-06-2019 13:00:56', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1169689289121464324', 'en_US', 'ALLOCATEFIFO_LOTATTRIBUTE11', 'Lot11', 'ALLOCATEFIFO', 'LOTATTRIBUTE11', null, null, null, 'CODELKUP', 'Y', 'Y', 'ADMIN', to_date('21-06-2019 12:57:45', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('21-06-2019 13:00:56', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1169689289121464325', 'en_US', 'ALLOCATEFIFO_LOTATTRIBUTE12', 'Lot12', 'ALLOCATEFIFO', 'LOTATTRIBUTE12', null, null, null, 'CODELKUP', 'Y', 'Y', 'ADMIN', to_date('21-06-2019 12:57:45', 'dd-mm-yyyy hh24:mi:ss'), 'ADMIN', to_date('21-06-2019 13:00:56', 'dd-mm-yyyy hh24:mi:ss'), '2', null);


DELETE wms.sys_locale_t where TABLE_NAME = 'CODELKUP' AND JOIN_KEY1 = 'LOCATIONTYPE';
insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1169688987018330113', 'en_US', 'LOCATIONTYPE_10', 'Storage', 'LOCATIONTYPE', '10', null, null, null, 'CODELKUP', 'Y', 'N', 'YECHUANKAI', to_date('21-08-2019 15:15:55', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('21-08-2019 15:15:55', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1169688987018330114', 'en_US', 'LOCATIONTYPE_20', 'Pick', 'LOCATIONTYPE', '20', null, null, null, 'CODELKUP', 'Y', 'N', 'YECHUANKAI', to_date('21-08-2019 15:15:55', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('21-08-2019 15:15:55', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1169688987018330115', 'en_US', 'LOCATIONTYPE_30', 'Virtual', 'LOCATIONTYPE', '30', null, null, null, 'CODELKUP', 'Y', 'N', 'YECHUANKAI', to_date('21-08-2019 15:15:55', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('21-08-2019 15:15:55', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1164073629514342400', 'en_US', 'LOCATIONTYPE_0', 'Normal', 'LOCATIONTYPE', '0', null, null, null, 'CODELKUP', 'Y', 'N', 'YECHUANKAI', to_date('21-08-2019 15:15:55', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('21-08-2019 15:15:55', 'dd-mm-yyyy hh24:mi:ss'), '1', null);


DELETE wms.sys_locale_t where TABLE_NAME = 'CODELKUP' AND JOIN_KEY1 = 'ZONETYPE';
insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1164072373274148865', 'en_US', 'ZONETYPE_10', 'Flat', 'ZONETYPE', '10', null, null, null, 'CODELKUP', 'Y', 'N', 'YECHUANKAI', to_date('21-08-2019 15:10:55', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('21-08-2019 15:11:57', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1164072373274148866', 'en_US', 'ZONETYPE_20', 'Overhead', 'ZONETYPE', '20', null, null, null, 'CODELKUP', 'Y', 'N', 'YECHUANKAI', to_date('21-08-2019 15:10:55', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('21-08-2019 15:11:57', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1164072373274148867', 'en_US', 'ZONETYPE_30', 'Asrs', 'ZONETYPE', '30', null, null, null, 'CODELKUP', 'Y', 'N', 'YECHUANKAI', to_date('21-08-2019 15:10:55', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('21-08-2019 15:11:57', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1164072373274148864', 'en_US', 'ZONETYPE_0', 'Normal', 'ZONETYPE', '0', null, null, null, 'CODELKUP', 'Y', 'N', 'YECHUANKAI', to_date('21-08-2019 15:10:55', 'dd-mm-yyyy hh24:mi:ss'), 'YECHUANKAI', to_date('21-08-2019 15:11:57', 'dd-mm-yyyy hh24:mi:ss'), '2', null);



--菜单盘点请求
delete uums.WEB_FUNC where func_id = 'wms:inventory:count:request';
insert into UUMS.WEB_FUNC (func_id, func_name, menu_id, func_desc, func_url, func_id_seq, sys_code, is_valid, sys_url, real_sys_code, invalid_time, invalid_user, create_time, version, func_unique_id)
values ('wms:inventory:count:request', '盘点请求', 'wms:inventory', '盘点请求', 'inventory/countRequest', 10, 'WMS', 'Y', null, null, null, null, to_date('16-08-2019 13:53:20', 'dd-mm-yyyy hh24:mi:ss'), '0', 1136869832319441003);

delete uums.sc_mappingconfig_lang where TABLE_NAME = 'WEB_FUNC' and table_id = 'wms:inventory:count:request';
insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1321, 'WEB_FUNC', 'FUNC_NAME', 'en_US', 'Count Request', 'Count Request', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:inventory:count:request', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1302, 'WEB_FUNC', 'FUNC_NAME', 'fr_FR', 'Demande de comptage', 'Demande de comptage', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:inventory:count:request', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1303, 'WEB_FUNC', 'FUNC_NAME', 'zh_CN', '盘点请求', '盘点请求', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:inventory:count:request', 'WMS');

--菜单盘点
delete uums.WEB_FUNC where func_id = 'wms:inventory:count';
insert into uums.web_func (FUNC_ID, FUNC_NAME, MENU_ID, FUNC_DESC, FUNC_URL, FUNC_ID_SEQ, SYS_CODE, IS_VALID, SYS_URL, REAL_SYS_CODE, INVALID_TIME, INVALID_USER, CREATE_TIME, VERSION, FUNC_UNIQUE_ID)
values ('wms:inventory:count', '盘点', 'wms:inventory', '盘点', 'inventory/count', '10', 'WMS', 'Y', null, null, null, null, to_date('16-08-2019 13:53:20', 'dd-mm-yyyy hh24:mi:ss'), '0', '1136869832319441004');

delete uums.sc_mappingconfig_lang where TABLE_NAME = 'WEB_FUNC' and table_id = 'wms:inventory:count';
insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values ('1324', 'WEB_FUNC', 'FUNC_NAME', 'en_US', 'Count', 'Count', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:inventory:count', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values ('1325', 'WEB_FUNC', 'FUNC_NAME', 'fr_FR', 'Inventaire', 'Inventaire', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:inventory:count', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values ('1326', 'WEB_FUNC', 'FUNC_NAME', 'zh_CN', '盘点', '盘点', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:inventory:count', 'WMS');



--分配明细表增加来源库位，来源LPN
alter table WMS.ALLOCATE_T ADD (FROM_LOCATION_CODE varchar2(50));
alter table WMS.ALLOCATE_T ADD (FROM_LPN_NUMBER varchar2(50));
comment on column WMS.ALLOCATE_T.FROM_LOCATION_CODE   is '来源库位';
comment on column WMS.ALLOCATE_T.FROM_LPN_NUMBER   is '来源LPN';


grant select on DPSP.CARD_INFO_LABOR_LIST to wms;
grant select on DPSP.COMMON_ATTACHED_FILE to wms;
grant select on uums.Web_Sys_Tfp to wms;

--增加内包装规格
alter table PACK_T ADD (
volume_inner number(15,5) default 0,
length_inner number(15,5) default 0,
width_inner number(15,5) default 0,
height_inner number(15,5) default 0,
weight_gross_inner number(15,5) default 0,
weight_net_inner number(15,5) default 0,
weight_tare_inner number(15,5) default 0
);
comment on column PACK_T.volume_inner is '内包装体积';
comment on column PACK_T.length_inner is '内包装长';
comment on column PACK_T.width_inner is '内包装宽';
comment on column PACK_T.height_inner is '内包装高';
comment on column PACK_T.weight_gross_inner is '内包装毛重';
comment on column PACK_T.weight_net_inner is '内包装净重';
comment on column PACK_T.weight_tare_inner is '内包装皮重';

--增加箱包装规格
alter table PACK_T ADD (
volume_case number(15,5) default 0,
length_case number(15,5) default 0,
width_case number(15,5) default 0,
height_case number(15,5) default 0,
weight_gross_case number(15,5) default 0,
weight_net_case number(15,5) default 0,
weight_tare_case number(15,5) default 0
);
comment on column PACK_T.volume_case is '箱包装体积';
comment on column PACK_T.length_case is '箱包装长';
comment on column PACK_T.width_case is '箱包装宽';
comment on column PACK_T.height_case is '箱包装高';
comment on column PACK_T.weight_gross_case is '箱包装毛重';
comment on column PACK_T.weight_net_case is '箱包装净重';
comment on column PACK_T.weight_tare_case is '箱包装皮重';




delete wms.sys_locale_t where locale_id in (
1150976044494921729,  1150977471871098881,  1146975366014820352,  1146975651558842368,  1146945875232829440,  1146975163522211840,  1146975721595330560,  1146976673798807552,  1146976759484243968,  1141880038042988544,  1141881211709911040,  1141881317846773760,  1151006203512029184,  1157198689428172800,  1157199014562230272,  1157199220892626944,  1151004622909538305,  1151005330371182593,  1151006688478429185,  1171027310500511745,  1171027432739307521,  1171027460862115841,  1171027567141584897,  1171027134012588032,  1171027169274101761
);
insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150976044494921729', 'en_US', 'web.labe.uom.least', 'UOM', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('16-07-2019 11:50:47', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 10:53:51', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150977471871098881', 'en_US', 'web.label.qty.least', 'Quantity', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('16-07-2019 11:56:28', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 10:54:08', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146975366014820352', 'en_US', 'web.label.contact2', 'Contact2', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('05-07-2019 10:53:31', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 20:51:52', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146975651558842368', 'fr_FR', 'web.label.contact1', 'Contact1', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('05-07-2019 10:54:39', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 20:50:59', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146945875232829440', 'en_US', 'web.label.packcode', 'Pack', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('05-07-2019 08:56:20', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 10:53:25', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146975163522211840', 'en_US', 'web.label.contact1', 'Contact1', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('05-07-2019 10:52:43', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 20:50:59', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146975721595330560', 'fr_FR', 'web.label.contact2', 'Contact2', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('05-07-2019 10:54:56', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 20:51:52', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146976673798807552', 'en_US', 'web.label.phone1', 'Phone1', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('05-07-2019 10:58:43', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 20:50:59', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146976759484243968', 'en_US', 'web.label.phone2', 'Phone2', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('05-07-2019 10:59:03', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 20:51:51', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1141880038042988544', 'en_US', 'web.label.pickToLocation', 'Pick to location', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('21-06-2019 09:26:30', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 10:55:16', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1141881211709911040', 'en_US', 'web.label.location.skuMix', 'Sku mix', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('21-06-2019 09:31:10', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 10:55:56', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1141881317846773760', 'en_US', 'web.label.location.lotMix', 'Lot Mix', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('21-06-2019 09:31:35', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 10:56:04', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1151006203512029184', 'en_US', 'web.label.uomCase', 'Case UOM', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('16-07-2019 13:50:38', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 10:56:12', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1157198689428172800', 'en_US', 'web.label.barcodestart', 'Barcode Start', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('02-08-2019 15:57:21', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 20:50:36', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1157199014562230272', 'en_US', 'web.label.barcodeprefix', 'Barcode Prefix', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('02-08-2019 15:58:39', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 20:50:12', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1157199220892626944', 'en_US', 'web.label.barcodelength', 'Barcode Length', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('02-08-2019 15:59:28', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 20:49:55', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1151004622909538305', 'en_US', 'web.label.uomInner', 'Inner UOM', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('16-07-2019 13:44:21', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 10:54:27', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1151005330371182593', 'en_US', 'web.label.qtyInner', 'Inner Quantity', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('16-07-2019 13:47:10', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 10:54:41', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1151006688478429185', 'en_US', 'web.label.qtyCase', 'Case Quantity', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('16-07-2019 13:52:33', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 10:56:30', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1171027310500511745', 'fr_FR', 'web.label.pack.inner', 'paquet intérieur', null, null, null, null, null, null, 'Y', 'N', 'yechuankai', to_date('09-09-2019 14:47:22', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 14:47:22', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1171027432739307521', 'en_US', 'web.label.pack.case', 'Case Pack', null, null, null, null, null, null, 'Y', 'N', 'yechuankai', to_date('09-09-2019 14:47:51', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 14:47:51', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1171027460862115841', 'zh_CN', 'web.label.pack.case', '箱包装', null, null, null, null, null, null, 'Y', 'N', 'yechuankai', to_date('09-09-2019 14:47:57', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 14:47:57', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1171027567141584897', 'fr_FR', 'web.label.pack.case', 'Pack de cas', null, null, null, null, null, null, 'Y', 'N', 'yechuankai', to_date('09-09-2019 14:48:23', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 14:48:23', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1171027134012588032', 'zh_CN', 'web.label.pack.inner', '内包装', null, null, null, null, null, null, 'Y', 'N', 'yechuankai', to_date('09-09-2019 14:46:40', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 14:46:40', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1171027169274101761', 'en_US', 'web.label.pack.inner', 'Inner Pack', null, null, null, null, null, null, 'Y', 'N', 'yechuankai', to_date('09-09-2019 14:46:48', 'dd-mm-yyyy hh24:mi:ss'), 'yechuankai', to_date('09-09-2019 14:46:48', 'dd-mm-yyyy hh24:mi:ss'), '1', null);


alter table wms.ALLOCATE_SHORT_T modify ( QUANTITY_ORIGINAL NUMBER(20,5) DEFAULT 0);
alter table wms.ALLOCATE_SHORT_T modify ( QUANTITY_ACTUAL NUMBER(20,5) DEFAULT 0);
alter table wms.ALLOCATE_T modify ( QUANTITY_ALLOCATED NUMBER(20,5) DEFAULT 0);
alter table wms.INBOUND_CANCEL_DETAIL_T modify ( QUANTITY_CANCEL NUMBER(20,5) DEFAULT 0);
alter table wms.INBOUND_DETAIL_T modify ( WEIGHT_GROSS NUMBER(20,5) DEFAULT 0);
alter table wms.INBOUND_DETAIL_T modify ( WEIGHT_NET NUMBER(20,5) DEFAULT 0);
alter table wms.INBOUND_DETAIL_T modify ( WEIGHT_TARE NUMBER(20,5) DEFAULT 0);
alter table wms.INBOUND_DETAIL_T modify ( VOLUME NUMBER(20,5) DEFAULT 0);
alter table wms.INBOUND_DETAIL_T modify ( QUANTITY_EXPECTED NUMBER(20,5) DEFAULT 0);
alter table wms.INBOUND_DETAIL_T modify ( QUANTITY_RECEIVE NUMBER(20,5) DEFAULT 0);
alter table wms.INBOUND_DETAIL_T modify ( QUANTITY_CANCEL NUMBER(20,5) DEFAULT 0);
alter table wms.INBOUND_DETAIL_T modify ( UOM_QUANTITY NUMBER(20,5) DEFAULT 0);
alter table wms.INVENTORY_ADJUSTMENT_DETAIL_T modify ( UOM_QUANTITY NUMBER(20,5) DEFAULT 0);
alter table wms.INVENTORY_ADJUSTMENT_DETAIL_T modify ( QUANTITY_ONHAND NUMBER(20,5) DEFAULT 0);
alter table wms.INVENTORY_ADJUSTMENT_DETAIL_T modify ( QUANTITY_ADJUSTMENT NUMBER(20,5) DEFAULT 0);
alter table wms.INVENTORY_COUNT_DETAIL_T modify ( QUANTITY_SYSTEM NUMBER(20,5) DEFAULT 0);
alter table wms.INVENTORY_COUNT_DETAIL_T modify ( QUANTITY_COUNT NUMBER(20,5) DEFAULT 0);
alter table wms.INVENTORY_COUNT_DETAIL_T modify ( QUANTITY_REPLAY NUMBER(20,5) DEFAULT 0);
alter table wms.INVENTORY_COUNT_DETAIL_T modify ( QUANTITY_DIFFERENCE NUMBER(20,5) DEFAULT 0);
alter table wms.INVENTORY_COUNT_DETAIL_T modify ( QUANTITY_CONFIRM NUMBER(20,5) DEFAULT 0);
alter table wms.INVENTORY_LOCKED_T modify ( QUANTITY_LOCKED NUMBER(20,5) DEFAULT 0);
alter table wms.INVENTORY_ONHAND_T modify ( QUANTITY_ONHAND NUMBER(20,5) DEFAULT 0);
alter table wms.INVENTORY_ONHAND_T modify ( QUANTITY_ALLOCATED NUMBER(20,5) DEFAULT 0);
alter table wms.INVENTORY_ONHAND_T modify ( QUANTITY_LOCKED NUMBER(20,5) DEFAULT 0);
alter table wms.INVENTORY_TRANSACTION_T modify ( UOM_QUANTITY NUMBER(20,5) DEFAULT 0);
alter table wms.INVENTORY_TRANSACTION_T modify ( QUANTITY NUMBER(20,5) DEFAULT 0);
alter table wms.LPN_T modify ( VOLUME NUMBER(20,5) DEFAULT 0);
alter table wms.LPN_T modify ( LENGTH NUMBER(20,5) DEFAULT 0);
alter table wms.LPN_T modify ( WIDTH NUMBER(20,5) DEFAULT 0);
alter table wms.LPN_T modify ( HEIGHT NUMBER(20,5) DEFAULT 0);
alter table wms.LPN_T modify ( WEIGHT_GROSS NUMBER(20,5) DEFAULT 0);
alter table wms.LPN_T modify ( WEIGHT_NET NUMBER(20,5) DEFAULT 0);
alter table wms.LPN_T modify ( WEIGHT_TARE NUMBER(20,5) DEFAULT 0);
alter table wms.OUTBOUND_DETAIL_T modify ( WEIGHT_GROSS NUMBER(20,5) DEFAULT 0);
alter table wms.OUTBOUND_DETAIL_T modify ( WEIGHT_NET NUMBER(20,5) DEFAULT 0);
alter table wms.OUTBOUND_DETAIL_T modify ( WEIGHT_TARE NUMBER(20,5) DEFAULT 0);
alter table wms.OUTBOUND_DETAIL_T modify ( VOLUME NUMBER(20,5) DEFAULT 0);
alter table wms.OUTBOUND_DETAIL_T modify ( UOM_QUANTITY NUMBER(20,5) DEFAULT 0);
alter table wms.OUTBOUND_DETAIL_T modify ( QUANTITY_ORDER NUMBER(20,5) DEFAULT 0);
alter table wms.OUTBOUND_DETAIL_T modify ( QUANTITY_EXPECTED NUMBER(20,5) DEFAULT 0);
alter table wms.OUTBOUND_DETAIL_T modify ( QUANTITY_ALLOCATED NUMBER(20,5) DEFAULT 0);
alter table wms.OUTBOUND_DETAIL_T modify ( QUANTITY_PICKED NUMBER(20,5) DEFAULT 0);
alter table wms.OUTBOUND_DETAIL_T modify ( QUANTITY_SHIPED NUMBER(20,5) DEFAULT 0);
alter table wms.PACK_T modify ( LENGTH_INNER NUMBER(20,5) DEFAULT 0);
alter table wms.PACK_T modify ( WIDTH_INNER NUMBER(20,5) DEFAULT 0);
alter table wms.PACK_T modify ( HEIGHT_INNER NUMBER(20,5) DEFAULT 0);
alter table wms.PACK_T modify ( WEIGHT_GROSS_INNER NUMBER(20,5) DEFAULT 0);
alter table wms.PACK_T modify ( WEIGHT_NET_INNER NUMBER(20,5) DEFAULT 0);
alter table wms.PACK_T modify ( WEIGHT_TARE_INNER NUMBER(20,5) DEFAULT 0);
alter table wms.PACK_T modify ( VOLUME_CASE NUMBER(20,5) DEFAULT 0);
alter table wms.PACK_T modify ( VOLUME_INNER NUMBER(20,5) DEFAULT 0);
alter table wms.PACK_T modify ( LENGTH_CASE NUMBER(20,5) DEFAULT 0);
alter table wms.PACK_T modify ( WIDTH_CASE NUMBER(20,5) DEFAULT 0);
alter table wms.PACK_T modify ( HEIGHT_CASE NUMBER(20,5) DEFAULT 0);
alter table wms.PACK_T modify ( WEIGHT_GROSS_CASE NUMBER(20,5) DEFAULT 0);
alter table wms.PACK_T modify ( WEIGHT_NET_CASE NUMBER(20,5) DEFAULT 0);
alter table wms.PACK_T modify ( WEIGHT_TARE_CASE NUMBER(20,5) DEFAULT 0);
alter table wms.PACK_T modify ( QTY NUMBER(20,5) DEFAULT 0);
alter table wms.PACK_T modify ( QTY_INNER NUMBER(20,5) DEFAULT 0);
alter table wms.PACK_T modify ( QTY_CASE NUMBER(20,5) DEFAULT 0);
alter table wms.SKU_T modify ( LENGTH NUMBER(20,5) DEFAULT 0);
alter table wms.SKU_T modify ( WIDTH NUMBER(20,5) DEFAULT 0);
alter table wms.SKU_T modify ( HEIGHT NUMBER(20,5) DEFAULT 0);
alter table wms.SKU_T modify ( WEIGHT_GROSS NUMBER(20,5) DEFAULT 0);
alter table wms.SKU_T modify ( WEIGHT_NET NUMBER(20,5) DEFAULT 0);
alter table wms.SKU_T modify ( WEIGHT_TARE NUMBER(20,5) DEFAULT 0);
alter table wms.SKU_T modify ( VOLUME NUMBER(20,5) DEFAULT 0);
alter table wms.TASK_DETAIL_T modify ( QUANTITY NUMBER(20,5) DEFAULT 0);


delete wms.sys_locale_t where LOCALE_ID in (1171521452147945472, 1171521599758086144, 1171521831476604928);
insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1171521452147945472', 'zh_CN', 'allocate.not.enough.inventory', '没有足够的库存可分配', null, null, null, null, null, null, 'Y', 'N', '管理员', to_date('10-09-2019 23:30:54', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('10-09-2019 23:31:43', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1171521599758086144', 'en_US', 'allocate.not.enough.inventory', 'Not enough Inventory for Allocate', null, null, null, null, null, null, 'Y', 'N', '管理员', to_date('10-09-2019 23:31:29', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('10-09-2019 23:31:29', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1171521831476604928', 'fr_FR', 'allocate.not.enough.inventory', 'Pas assez d''inventaire pour allouer', null, null, null, null, null, null, 'Y', 'N', '管理员', to_date('10-09-2019 23:32:25', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('10-09-2019 23:32:25', 'dd-mm-yyyy hh24:mi:ss'), '1', null);


ALTER TABLE sys_codelist_t MODIFY (IS_ALL VARCHAR2(100));
UPDATE sys_codelist_t SET IS_ALL = TRIM(IS_ALL);
ALTER TABLE sys_codelist_t MODIFY (IS_ALL CHAR(1));


delete wms.sys_locale_t where locale_id in (
1170952846332923904,1170953149065203712,1146249769705787392,1146654813626265600,1147075270846025728,1147075410193387520,1147075481458806784,1147075211165274112,1147075534734856192,1148870671123607552,1150696045859962880,1146249267874091008,1146326087637749760,1146327032023044096,1146619785827057664,1147076330733051905,1147076398559141889,1160859255531114496,1166956378836721664,1166956634550853632,1166956905976848384,1166957180116557824,1167323537974796288,1170949171380228096,1170954359885271040,1170954475266379776,1171595837837996032,1171599210779832320,1171613463356174336,1171850663966482432,1171850758380265472,1171850929780498432,1171521452147945472,1171521599758086144,1171521831476604928
);
insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146249769705787392', 'en_US', 'web.label.fromLotNumber', 'From Lot', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('03-07-2019 10:50:16', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 18:48:35', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146654813626265600', 'en_US', 'web.inventory.containernumber', 'Container', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('04-07-2019 13:39:46', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 18:48:35', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147075270846025728', 'fr_FR', 'web.inventory.endtimebegin', 'End Time  commence', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('05-07-2019 17:30:30', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 13:27:52', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147075410193387520', 'zh_CN', 'web.inventory.endtimeend', '结束时间-结束', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('05-07-2019 17:31:04', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 13:27:52', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147075481458806784', 'en_US', 'web.inventory.endtimeend', 'End Time End', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('05-07-2019 17:31:21', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 13:27:52', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147075211165274112', 'en_US', 'web.inventory.endtimebegin', 'End Time Begin', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('05-07-2019 17:30:16', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 13:27:52', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147075534734856192', 'fr_FR', 'web.inventory.endtimeend', 'End Time  fin', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('05-07-2019 17:31:33', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 13:27:52', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1148870671123607552', 'en_US', 'web.label.tolotnumber', 'To Lot', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('10-07-2019 16:24:47', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 18:48:35', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1150696045859962880', 'en_US', 'web.label.quantityavailablemorethanzero', 'Available Quantity Greater Than Zero', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('15-07-2019 17:18:10', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 20:02:39', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146249267874091008', 'en_US', 'web.label.fromLpnNumber', 'From LPN', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('03-07-2019 10:48:16', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 18:48:35', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146326087637749760', 'en_US', 'web.label.fromlocationcode', 'From Location', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('03-07-2019 15:53:31', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 21:14:33', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146327032023044096', 'en_US', 'web.label.tolocationcode', 'To Location', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('03-07-2019 15:57:16', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 21:14:33', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1146619785827057664', 'en_US', 'web.label.loadlpnnumber', 'Load LPN', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('04-07-2019 11:20:34', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 18:48:35', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147076330733051905', 'en_US', 'web.label.endtime', 'End Time', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('05-07-2019 17:34:43', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 13:26:04', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1147076398559141889', 'fr_FR', 'web.label.endtime', 'Heure de fin', null, null, null, null, null, null, 'Y', 'N', 'ADMIN', to_date('05-07-2019 17:34:59', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 13:26:22', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1160859255531114496', 'en_US', 'web.label.quantity.toquantitylocked', 'Expected lock Quantity', null, null, null, null, null, null, 'Y', 'N', '彭臻', to_date('12-08-2019 18:23:08', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 20:02:39', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1166956378836721664', 'en_US', 'web.label.inventory.search.containernumber', 'By Container', null, null, null, null, null, null, 'Y', 'N', '管理员', to_date('29-08-2019 14:10:56', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 20:00:27', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1166956634550853632', 'en_US', 'web.label.inventory.search.lpn', 'By LPN', null, null, null, null, null, null, 'Y', 'N', '管理员', to_date('29-08-2019 14:11:57', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 20:00:26', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1166956905976848384', 'en_US', 'web.label.inventory.search.location', 'By Location', null, null, null, null, null, null, 'Y', 'N', '管理员', to_date('29-08-2019 14:13:02', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 20:00:26', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1166957180116557824', 'en_US', 'web.label.inventory.search.sku', 'By SKU', null, null, null, null, null, null, 'Y', 'N', '管理员', to_date('29-08-2019 14:14:07', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 20:00:26', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1167323537974796288', 'en_US', 'web.label.quantity.softallocated', 'Soft Allocated Quantity', null, null, null, null, null, null, 'Y', 'N', '刘', to_date('30-08-2019 14:29:53', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 20:37:20', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1170949171380228096', 'en_US', 'web.label.quantityshowflag', 'Show Quantity', null, null, null, null, null, null, 'Y', 'N', '彭臻', to_date('09-09-2019 14:36:52', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 21:03:39', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1170954359885271040', 'en_US', 'web.label.lpnnumberin', 'Contains LPN (separator , )', null, null, null, null, null, null, 'Y', 'N', '彭臻', to_date('09-09-2019 14:57:29', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 21:17:06', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1170954475266379776', 'fr_FR', 'web.label.lpnnumberin', 'Contient LPN (separator ,)', null, null, null, null, null, null, 'Y', 'N', '彭臻', to_date('09-09-2019 14:57:56', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 21:20:46', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1171595837837996032', 'en_US', 'web.label.countnumber', 'CountNumber', null, null, null, null, null, null, 'Y', 'N', '彭臻', to_date('11-09-2019 09:26:29', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 21:13:53', 'dd-mm-yyyy hh24:mi:ss'), '4', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1171599210779832320', 'en_US', 'web.label.parentcountnumber', 'Parent Count Number', null, null, null, null, null, null, 'Y', 'N', '彭臻', to_date('11-09-2019 09:39:53', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 21:13:48', 'dd-mm-yyyy hh24:mi:ss'), '3', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1171613463356174336', 'en_US', 'countdetail.created', '{0} rows created', null, null, null, null, null, null, 'Y', 'N', '彭臻', to_date('11-09-2019 10:36:31', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 21:09:24', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1171850663966482432', 'en_US', 'web.label.skucodein', 'Contains SKU (separator , )', null, null, null, null, null, null, 'Y', 'N', '管理员', to_date('11-09-2019 21:19:04', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 21:19:04', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1171850758380265472', 'zh_CN', 'web.label.skucodein', '货品包含 （逗号 , 分隔）', null, null, null, null, null, null, 'Y', 'N', '管理员', to_date('11-09-2019 21:19:27', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 21:19:27', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1171850929780498432', 'fr_FR', 'web.label.skucodein', 'Contient SKU (separator,)', null, null, null, null, null, null, 'Y', 'N', '管理员', to_date('11-09-2019 21:20:08', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 21:20:08', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1171521452147945472', 'zh_CN', 'allocate.not.enough.inventory', '没有足够的库存可分配', null, null, null, null, null, null, 'Y', 'N', '管理员', to_date('10-09-2019 23:30:54', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('10-09-2019 23:31:43', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1171521599758086144', 'en_US', 'allocate.not.enough.inventory', 'Not enough Inventory for Allocate', null, null, null, null, null, null, 'Y', 'N', '管理员', to_date('10-09-2019 23:31:29', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('10-09-2019 23:31:29', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1171521831476604928', 'fr_FR', 'allocate.not.enough.inventory', 'Pas assez d''inventaire pour allouer', null, null, null, null, null, null, 'Y', 'N', '管理员', to_date('10-09-2019 23:32:25', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('10-09-2019 23:32:25', 'dd-mm-yyyy hh24:mi:ss'), '1', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1170952846332923904', 'en_US', 'web.label.fromskucode', 'From SKU', null, null, null, null, null, null, 'Y', 'N', '彭臻', to_date('09-09-2019 14:51:28', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 21:30:46', 'dd-mm-yyyy hh24:mi:ss'), '2', null);

insert into wms.sys_locale_t (LOCALE_ID, LOCALE_CODE, LABEL_KEY, LABEL_VALUE, JOIN_KEY1, JOIN_KEY2, JOIN_KEY3, JOIN_KEY4, JOIN_KEY5, TABLE_NAME, ACTIVE, DEL_FLAG, CREATE_BY, CREATE_TIME, UPDATE_BY, UPDATE_TIME, UPDATE_VERSION, DESCRIPTION)
values ('1170953149065203712', 'en_US', 'web.label.toskucode', 'To SKU', null, null, null, null, null, null, 'Y', 'N', '彭臻', to_date('09-09-2019 14:52:40', 'dd-mm-yyyy hh24:mi:ss'), '管理员', to_date('11-09-2019 21:30:46', 'dd-mm-yyyy hh24:mi:ss'), '2', null);


alter table wave_build_t rename column ware_type to wave_type;


alter table owner_t add (web_site varchar2(200));
comment on column owner_t.web_site is '公司网站';

alter table customer_t add (web_site varchar2(200));
comment on column customer_t.web_site is '公司网站';

alter table supplier_t add (web_site varchar2(200));
comment on column supplier_t.web_site is '公司网站';

alter table carrier_t add (web_site varchar2(200));
comment on column carrier_t.web_site is '公司网站';

alter table customer_t rename column email to email1;
alter table customer_t add (email2 varchar2(100));
comment on column customer_t.email2 is '邮箱2';

alter table supplier_t rename column email to email1;
alter table supplier_t add (email2 varchar2(100));
comment on column supplier_t.email2 is '邮箱2';

alter table owner_t rename column email to email1;
alter table owner_t add (email2 varchar2(100));
comment on column carrier_t.email2 is '邮箱2';

alter table carrier_t rename column email to email1;
alter table carrier_t add (email2 varchar2(100));
comment on column carrier_t.email2 is '邮箱2';

alter table outbound_header_t rename column email to email1;
alter table outbound_header_t add (email2 varchar2(100));
comment on column outbound_header_t.email2 is '邮箱2';

alter table outbound_header_t add (SOURCE_WAVE_NUMBER varchar2(100));
comment on column outbound_header_t.SOURCE_WAVE_NUMBER is '来源波次号';

alter table wave_t add (process_status varchar2(5) default '0');
comment on column wave_t.process_status is '处理状态 ';


alter table inventory_count_request_t add (owner_code varchar2(50));
comment on column inventory_count_request_t.owner_code is '货主 ';

alter table inbound_header_t add (carrier_driver_phone varchar2(50));
comment on column inbound_header_t.carrier_driver_phone is '司机电话 ';

alter table outbound_header_t add (driver_phone varchar2(50));
comment on column outbound_header_t.driver_phone is '司机电话 ';




--新增"缺量记录"的子菜单
delete uums.WEB_FUNC where func_id = 'wms:outbound:allocate:short';
insert into uums.WEB_FUNC (FUNC_ID, FUNC_NAME, MENU_ID, FUNC_DESC, FUNC_URL, FUNC_ID_SEQ, SYS_CODE, IS_VALID, SYS_URL, REAL_SYS_CODE, INVALID_TIME, INVALID_USER, CREATE_TIME, VERSION, FUNC_UNIQUE_ID)
values ('wms:outbound:allocate:short', '拣选缺量', 'wms:outbound', '拣选缺量', 'outbound/allocateShort', 10, 'WMS', 'Y', null, null, null, null, to_date('21-08-2019 14:08:28', 'dd-mm-yyyy hh24:mi:ss'), '0', 1173880490653638701);

delete uums.sc_mappingconfig_lang where TABLE_NAME = 'WEB_FUNC' and table_id = 'wms:outbound:allocate:short';
insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1321, 'WEB_FUNC', 'FUNC_NAME', 'en_US', 'Allocate Short', 'Allocate Short', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:outbound:allocate:short', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1322, 'WEB_FUNC', 'FUNC_NAME', 'fr_FR', 'Allouer court', 'Allouer court', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:outbound:allocate:short', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1323, 'WEB_FUNC', 'FUNC_NAME', 'zh_CN', '拣选缺量', '拣选缺量', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:outbound:allocate:short', 'WMS');



--新增一级菜单 预约
delete uums.web_funcmenu where menu_id = 'wms:appointment';
insert into uums.web_funcmenu (MENU_ID, MENU_NAME, MENU_DESC, SYS_CODE, MENU_ID_SEQ, IS_VALID, INVALID_TIME, INVALID_USER, CREATE_TIME, MENU_IMG_URL, VERSION, FUNCMENU_UNIQUE_ID)
values ('wms:appointment', '预约', '预约', 'WMS', '1', 'Y', null, null, to_date('05-12-2016 10:46:09', 'dd-mm-yyyy hh24:mi:ss'), null, '0', '1');

delete uums.sc_mappingconfig_lang where TABLE_NAME = 'WEB_FUNCMENU' and table_id = 'wms:appointment';
insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1324, 'WEB_FUNCMENU', 'MENU_NAME', 'en_US', 'Appointment', 'Appointment', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:appointment', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1325, 'WEB_FUNCMENU', 'MENU_NAME', 'fr_FR', 'Rendez-vous', 'Rendez-vous', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:appointment', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1326, 'WEB_FUNCMENU', 'MENU_NAME', 'zh_CN', '预约', '预约', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:appointment', 'WMS');



--新增"预约-泊位"的子菜单
delete uums.WEB_FUNC where func_id = 'wms:appointment:platform';
insert into uums.WEB_FUNC (FUNC_ID, FUNC_NAME, MENU_ID, FUNC_DESC, FUNC_URL, FUNC_ID_SEQ, SYS_CODE, IS_VALID, SYS_URL, REAL_SYS_CODE, INVALID_TIME, INVALID_USER, CREATE_TIME, VERSION, FUNC_UNIQUE_ID)
values ('wms:appointment:platform', '泊位', 'wms:appointment', '泊位', 'appointment/platform', 10, 'WMS', 'Y', null, null, null, null, to_date('21-08-2019 14:08:28', 'dd-mm-yyyy hh24:mi:ss'), '0', 1173880490653638702);

delete uums.sc_mappingconfig_lang where TABLE_NAME = 'WEB_FUNC' and table_id = 'wms:appointment:platform';
insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1327, 'WEB_FUNC', 'FUNC_NAME', 'en_US', 'Plat Form', 'Plat Form', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:appointment:platform', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1328, 'WEB_FUNC', 'FUNC_NAME', 'fr_FR', 'Plate-forme', 'Plate-forme', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:appointment:platform', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1329, 'WEB_FUNC', 'FUNC_NAME', 'zh_CN', '泊位', '泊位', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:appointment:platform', 'WMS');

--新增"预约-预约"的子菜单
delete uums.WEB_FUNC where func_id = 'wms:appointment:appointment';
insert into uums.WEB_FUNC (FUNC_ID, FUNC_NAME, MENU_ID, FUNC_DESC, FUNC_URL, FUNC_ID_SEQ, SYS_CODE, IS_VALID, SYS_URL, REAL_SYS_CODE, INVALID_TIME, INVALID_USER, CREATE_TIME, VERSION, FUNC_UNIQUE_ID)
values ('wms:appointment:appointment', '预约', 'wms:appointment', '预约', 'appointment/appointment', 10, 'WMS', 'Y', null, null, null, null, to_date('21-08-2019 14:08:28', 'dd-mm-yyyy hh24:mi:ss'), '0', 1173880490653638703);

delete uums.sc_mappingconfig_lang where TABLE_NAME = 'WEB_FUNC' and table_id = 'wms:appointment:appointment';
insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1331, 'WEB_FUNC', 'FUNC_NAME', 'en_US', 'Appointment', 'Appointment', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:appointment:appointment', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1332, 'WEB_FUNC', 'FUNC_NAME', 'fr_FR', 'Rendez-vous', 'Rendez-vous', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:appointment:appointment', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1333, 'WEB_FUNC', 'FUNC_NAME', 'zh_CN', '预约', '预约', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:appointment:appointment', 'WMS');


alter table wms.sys_order_number_t rename column DATA_FORMAT to DATE_FORMAT;


ALTER TABLE wms.TASK_DETAIL_T MODIFY (TASK_TYPE VARCHAR2(10));

--订单系统出库增加货品描述
alter table wms.OUTBOUND_DETAIL_T ADD SKU_DESCR varchar2(200);
comment on column wms.OUTBOUND_DETAIL_T.SKU_DESCR   is '货品描述';


--新增"卸车任务列表"的子菜单
delete uums.WEB_FUNC where func_id = 'wms:inbound:unloadtask';
insert into uums.WEB_FUNC (FUNC_ID, FUNC_NAME, MENU_ID, FUNC_DESC, FUNC_URL, FUNC_ID_SEQ, SYS_CODE, IS_VALID, SYS_URL, REAL_SYS_CODE, INVALID_TIME, INVALID_USER, CREATE_TIME, VERSION, FUNC_UNIQUE_ID)
values ('wms:inbound:unloadtask', '卸车任务', 'wms:inbound', '卸车任务', 'inbound/unloadtask', 10, 'WMS', 'Y', null, null, null, null, to_date('21-08-2019 14:08:28', 'dd-mm-yyyy hh24:mi:ss'), '0', 1173880490653638703);

delete uums.sc_mappingconfig_lang where TABLE_NAME = 'WEB_FUNC' and table_id = 'wms:inbound:unloadtask';
insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1334, 'WEB_FUNC', 'FUNC_NAME', 'en_US', 'UnLoad', 'UnLoad', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:inbound:unloadtask', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1335, 'WEB_FUNC', 'FUNC_NAME', 'fr_FR', 'Décharger', 'Décharger', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:inbound:unloadtask', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1336, 'WEB_FUNC', 'FUNC_NAME', 'zh_CN', '卸车任务', '卸车任务', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:inbound:unloadtask', 'WMS');


--新增"装车任务列表"的子菜单
delete uums.WEB_FUNC where func_id = 'wms:outbound:loadtask';
insert into uums.WEB_FUNC (FUNC_ID, FUNC_NAME, MENU_ID, FUNC_DESC, FUNC_URL, FUNC_ID_SEQ, SYS_CODE, IS_VALID, SYS_URL, REAL_SYS_CODE, INVALID_TIME, INVALID_USER, CREATE_TIME, VERSION, FUNC_UNIQUE_ID)
values ('wms:outbound:loadtask', '装车任务', 'wms:outbound', '装车任务', 'outbound/loadtask', 50, 'WMS', 'Y', null, null, null, null, to_date('21-08-2019 14:08:28', 'dd-mm-yyyy hh24:mi:ss'), '0', 1173880490653638703);

delete uums.sc_mappingconfig_lang where TABLE_NAME = 'WEB_FUNC' and table_id = 'wms:outbound:loadtask';
insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1337, 'WEB_FUNC', 'FUNC_NAME', 'en_US', 'Load', 'Load', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:outbound:loadtask', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1338, 'WEB_FUNC', 'FUNC_NAME', 'fr_FR', 'Charge', 'Charge', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:outbound:loadtask', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1339, 'WEB_FUNC', 'FUNC_NAME', 'zh_CN', '装车任务', '装车任务', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:outbound:loadtask', 'WMS');


alter table uums.web_log modify (LOGIN_IP varchar2(50) );




