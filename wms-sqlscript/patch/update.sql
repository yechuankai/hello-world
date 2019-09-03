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


