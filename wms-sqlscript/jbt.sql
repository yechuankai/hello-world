grant select on uums.company to wms;
grant select on dpsp.base_warehouse_info to wms;
grant select on uums.web_userinfo to wms;
grant select on uums.web_role to wms;
grant select on uums.web_func to wms;
grant select on uums.web_funcmenu to wms;
grant select on uums.web_rolegrant to wms;
grant select on uums.web_userrole to wms; 
grant select on uums.SC_MAPPINGCONFIG_LANG to wms;



-- ----------------------------
-- 公司信息视图
-- ----------------------------
create or replace view wms.sys_companys_v as (
select 
      company_unique_id company_id,
      company_id code,
      name descr,
      id_type type,
      'Y' active,
      'N' del_Flag,
      create_user create_By,
      CREATE_TIME create_Time,
      LASTUPDATE_USER update_By,
      LASTUPDATE_TIME update_Time,
      1 update_Version,
      STATUS description
from uums.company t );

-- ----------------------------
-- 仓库信息视图
-- ----------------------------
create or replace view wms.sys_warehouses_v as (
select 
      warehouse_unique_id warehouse_id,
      WAREHOUSE_NO code,
      WAREHOUSE_NAME descr,
      '1' type,
      'Y' active,
      'N' del_Flag,
      create_user create_By,
      CREATE_TIME create_Time,
      LAST_UPDATE_USER update_By,
      LAST_UPDATE_TIME update_Time,
      1 update_Version,
      '' description
from dpsp.base_warehouse_info t );


-- ----------------------------
-- 用户信息视图
-- ----------------------------
create or replace view wms.sys_user_v as (
select
       u.user_unique_id user_id,
       u.user_id login_name,
       nvl(u.user_name, u.user_id) user_name,
       c.company_unique_id company_id,
       c.name company_descr,
       u.user_type user_type,
       user_email email,
       user_tel phonenumber,
       '' sex,
       head_portrait avatar,
       user_pwd password,
       '' salt,
       '' login_ip,
       to_date('1999-01-01','YYYY-MM-DD') login_date,
      ISVALID active,
      'N' del_Flag,
      u.CREATE_USER create_By,
      u.CREATE_TIME create_Time,
      u.LASTUPDATE_USER update_By,
      u.LASTUPDATE_TIME update_Time,
      1 update_Version,
      '' description
      from uums.web_userinfo u 
           left join uums.company c on u.company_id = c.company_id
);



-- ----------------------------
-- 角色信息视图
-- ----------------------------
create or replace view wms.sys_role_v as (
select
      role_unique_id role_id,
      role_id code,
      role_name descr, 
      IS_VALID active,
      'N' del_Flag,
      '' create_By,
      CREATE_TIME create_Time,
      '' update_By,
      to_date('1999-01-01','YYYY-MM-DD') update_Time,
      1 update_Version,
      '' description
      from uums.web_role
);


-- ----------------------------
-- 权限信息视图
-- ----------------------------
create or replace view sys_permission_v as
(
select
      f.func_unique_id permission_id,
      f.func_name permission_name,
      m.funcmenu_unique_id parent_id,
      f.func_id_seq sort,
      f.func_url url,
      DECODE(f.sys_code, 'WMS', 'M', 'F') permission_type,
      case 
         when f.func_id = 'wms:system:orderNumber' then 'Y' 
         when f.func_id = 'wms:base:warehouseactive' then 'N'
         when f.menu_id = 'wms:system' then 'N'
         when f.sys_code = 'WMS' then 'Y'
         else 'N'
      end warehouse_flag,
      f.IS_VALID visible,
      f.func_id perms,
      '' icon,
      'N' del_Flag,
      '' create_By,
      f.CREATE_TIME create_Time,
      '' update_By,
      to_date('1999-01-01','YYYY-MM-DD') update_Time,
      1 update_Version,
      '' description
      from uums.web_func f
           inner join uums.web_funcmenu m on m.menu_id = f.menu_id
    where
      f.sys_code like 'WMS%'
    and f.IS_VALID = 'Y'
union all
select
      m.funcmenu_unique_id permission_id,
      m.menu_name permission_name,
      0 parent_id,
      m.menu_id_seq sort,
      '#' url,
      DECODE(m.sys_code, 'WMS', 'M', 'F') permission_type,
      case 
         when m.menu_id = 'wms:system' then 'N'
         when m.menu_id = 'wms:base' then 'N'
         when m.sys_code = 'WMS' then 'Y'
         else 'N'
      end warehouse_flag,
      m.IS_VALID visible,
      m.menu_id perms,
      '' icon,
      'N' del_Flag,
      '' create_By,
      m.CREATE_TIME create_Time,
      '' update_By,
      to_date('1999-01-01','YYYY-MM-DD') update_Time,
      1 update_Version,
      '' description
      from uums.web_funcmenu m
    where
      m.sys_code like 'WMS%'
    and m.IS_VALID = 'Y'
);

-- ----------------------------
-- 用户角色信息视图
-- ----------------------------
create or replace view wms.sys_user_role_v as (
select
      ur.userrole_unique_id user_role_id,
      u.user_unique_id user_id,
      r.role_unique_id role_id,
      'N' del_Flag,
      '' create_By,
      ur.CREATE_TIME create_Time,
      '' update_By,
      to_date('1999-01-01','YYYY-MM-DD') update_Time,
      1 update_Version,
      '' description
      from uums.web_userrole ur 
           inner join uums.web_userinfo u on ur.user_id = u.user_id
           inner join uums.web_role r on ur.role_id = r.role_id
);



-- ----------------------------
-- 用户角色信息视图
-- ----------------------------
create or replace view wms.sys_role_permission_v as
(
select
      rf.rolegrant_unique_id role_permission_id,
      r.role_unique_id role_id,
      f.func_unique_id permission_id,
      'N' del_Flag,
      '' create_By,
      rf.CREATE_TIME create_Time,
      '' update_By,
      to_date('1999-01-01','YYYY-MM-DD') update_Time,
      1 update_Version,
      '' description
      from uums.web_rolegrant rf
           inner join uums.web_func f on rf.func_id = f.func_id and f.sys_code like 'WMS%'
           inner join uums.web_role r on rf.role_id = r.role_id
union all
select
      rownum role_permission_id,
      t.role_id,
      t.permission_id,
      'N' del_Flag,
      '' create_By,
      null create_Time,
      '' update_By,
      to_date('1999-01-01','YYYY-MM-DD') update_Time,
      1 update_Version,
      '' description
      from (
        select distinct
              r.role_unique_id role_id,
              fm.funcmenu_unique_id permission_id
              from uums.web_rolegrant rf
                   inner join uums.web_func f on rf.func_id = f.func_id and f.sys_code like 'WMS%'
                   inner join uums.web_funcmenu fm on f.menu_id = fm.menu_id
                   inner join uums.web_role r on rf.role_id = r.role_id ) t
);


-- ----------------------------
-- 用户数据信息视图
-- ----------------------------
create or replace view wms.sys_role_data_v as
(
   select
      rw.userrole_unique_id role_data_id,
      '20' data_type,
      w.warehouse_unique_id data_id,
      r.role_unique_id role_id,
      'Y' active,
      'N' del_Flag,
      rw.CREATE_USER create_By,
      rw.CREATE_TIME create_Time,
      ' ' update_By,
      to_date('1999-01-01','YYYY-MM-DD') update_Time,
      1 update_Version,
      '' description
   from
    uums.WEB_ROLEWAREHOUSE rw
     inner join uums.web_role r on rw.role_id = r.role_id
     inner join dpsp.base_warehouse_info w on rw.warehouse_id = w.warehouse_no
);


-- ----------------------------
-- 多语言数据信息视图
-- ----------------------------
create or replace view sys_locale_v as (
 select LOCALE_ID,
        LOCALE_CODE,
        LABEL_KEY,
        LABEL_VALUE,
        JOIN_KEY1,
        JOIN_KEY2,
        JOIN_KEY3,
        JOIN_KEY4,
        JOIN_KEY5,
        TABLE_NAME,
        ACTIVE,
        DEL_FLAG,
        CREATE_BY,
        CREATE_TIME,
        UPDATE_BY,
        UPDATE_TIME,
        UPDATE_VERSION,
        DESCRIPTION
   from 
        sys_locale_t
union all
 select (100000+lang_id) LOCALE_ID,
        LANGUAGENO LOCALE_CODE,
        TABLE_ID LABEL_KEY,
        LANGUAGE_CONTENT LABEL_VALUE,
        TABLE_ID JOIN_KEY1,
        '' JOIN_KEY2,
        '' JOIN_KEY3,
        '' JOIN_KEY4,
        '' JOIN_KEY5,
        'PERMISSION' TABLE_NAME,
        'Y' ACTIVE,
        'N' DEL_FLAG,
        CREATE_USER CREATE_BY,
        CREATE_DATE CREATE_TIME,
        UPDATE_USER UPDATE_BY,
        UPDATE_DATE UPDATE_TIME,
        0 UPDATE_VERSION,
        MEMO DESCRIPTION
   from 
        UUMS.SC_MAPPINGCONFIG_LANG 
   WHERE SYS_CODE LIKE 'WMS%'
);






