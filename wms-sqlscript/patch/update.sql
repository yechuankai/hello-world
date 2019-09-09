--菜单
insert into UUMS.WEB_FUNC (func_id, func_name, menu_id, func_desc, func_url, func_id_seq, sys_code, is_valid, sys_url, real_sys_code, invalid_time, invalid_user, create_time, version, func_unique_id)
values ('wms:system:report', '报表管理', 'wms:system', '报表管理', 'report/report', 10, 'WMS', 'Y', null, null, null, null, to_date('16-08-2019 13:53:20', 'dd-mm-yyyy hh24:mi:ss'), '0', 1136869832319441001);

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


--菜单
delete uums.WEB_FUNC where func_id = 'wms:outbound:wave:template';
insert into UUMS.WEB_FUNC (func_id, func_name, menu_id, func_desc, func_url, func_id_seq, sys_code, is_valid, sys_url, real_sys_code, invalid_time, invalid_user, create_time, version, func_unique_id)
values ('wms:outbound:wave:template', '波次模板', 'wms:outbound', '波次模板', 'outbound/wareTemplate', 10, 'WMS', 'Y', null, null, null, null, to_date('16-08-2019 13:53:20', 'dd-mm-yyyy hh24:mi:ss'), '0', 1136869832319441001);

delete uums.sc_mappingconfig_lang where TABLE_NAME = 'WEB_FUNC' and table_id = 'wms:outbound:wave:template';
insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1301, 'WEB_FUNC', 'FUNC_NAME', 'en_US', 'Wave Template', 'Wave Template', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:outbound:wave:template', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1302, 'WEB_FUNC', 'FUNC_NAME', 'fr_FR', 'Rapport de gestion', 'Rapport de gestion', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:outbound:wave:template', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1303, 'WEB_FUNC', 'FUNC_NAME', 'zh_CN', '波次模板', '波次模板', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:outbound:wave:template', 'WMS');

--菜单
delete uums.WEB_FUNC where func_id = 'wms:outbound:wave';
insert into UUMS.WEB_FUNC (func_id, func_name, menu_id, func_desc, func_url, func_id_seq, sys_code, is_valid, sys_url, real_sys_code, invalid_time, invalid_user, create_time, version, func_unique_id)
values ('wms:outbound:wave', '波次', 'wms:outbound:wave', '波次', 'outbound/wave', 9, 'WMS', 'Y', null, null, null, null, to_date('16-08-2019 13:53:20', 'dd-mm-yyyy hh24:mi:ss'), '0', 1136869832319441001);

delete uums.sc_mappingconfig_lang where TABLE_NAME = 'WEB_FUNC' and table_id = 'wms:outbound:wave';
insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1301, 'WEB_FUNC', 'FUNC_NAME', 'en_US', 'Wave Template', 'Wave Template', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:outbound:wave', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1302, 'WEB_FUNC', 'FUNC_NAME', 'fr_FR', 'Rapport de gestion', 'Rapport de gestion', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:outbound:wave', 'WMS');

insert into uums.sc_mappingconfig_lang (LANG_ID, TABLE_NAME, COLUMN_NAME, LANGUAGENO, LANGUAGE_CONTENT, MEMO, CREATE_DATE, CREATE_USER, UPDATE_DATE, UPDATE_USER, TABLE_ID, SYS_CODE)
values (1303, 'WEB_FUNC', 'FUNC_NAME', 'zh_CN', '波次', '波次', to_date('19-08-2019 17:02:29', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, 'wms:outbound:wave', 'WMS');



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


--分配明细表增加来源库位，来源LPN
alter table WMS.ALLOCATE_T ADD (FROM_LOCATION_CODE varchar2(50));
alter table WMS.ALLOCATE_T ADD (FROM_LPN_NUMBER varchar2(50));
comment on column WMS.ALLOCATE_T.FROM_LOCATION_CODE   is '来源库位';
comment on column WMS.ALLOCATE_T.FROM_LPN_NUMBER   is '来源LPN';


grant select on DPSP.CARD_INFO_LABOR_LIST to wms;
grant select on DPSP.COMMON_ATTACHED_FILE to wms;
grant select on uums.Web_Sys_Tfp to wms;
