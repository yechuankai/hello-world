---Create the tablespace 
create tablespace DBOWMS 
logging  
datafile '+DATADG/diftzer/datafile/dbowms.dbf'  
size 64m  
autoextend on  
next 64m  
extent management local;

-- Create the user 
create user WMS 
  identified by wms
  default tablespace DBOWMS
  temporary tablespace TEMP
  profile DEFAULT;
-- Grant/Revoke role privileges 
grant connect to WMS;
grant resource to WMS;
-- Grant/Revoke system privileges 
grant create database link to WMS;
grant create job to WMS;
grant create view to WMS;
grant debug connect session to WMS;
grant unlimited tablespace to WMS;



-- ----------------------------
-- 用户信息表
-- ----------------------------

drop table sys_user_t cascade constraints;
create table sys_user_t (
  user_id 			      number 		      not null,
  login_name 		      varchar2(30) 	  not null,
  user_name           varchar2(30)    not null,
  user_type           varchar2(2)     default '00',
  email               varchar2(50)    default '',
  phonenumber         varchar2(11)    default '',
  sex                 char(1)         default '0',
  avatar              varchar2(100)   default '',
  password            varchar2(50)    default '',
  salt                varchar2(20)    default '',
  active              char(1)         default 'Y',
  company_id        	number,
  company_descr			varchar2(100)   default '',
  del_flag            char(1)         default 'N',
  login_ip            varchar2(50)    default '',
  login_date          date,
  create_by           varchar2(64)    default '-1',
  create_time         date            default sysdate,
  update_by           varchar2(64)    default '-1',
  update_time         date            default sysdate,
  update_version      number          default '1',
  description         varchar2(500)   default ''
);

alter table sys_user add constraint pk_sys_user primary key (user_id);

comment on table  sys_user_t                      is '用户信息表';
comment on column sys_user_t.user_id              is '用户主键';
comment on column sys_user_t.login_name           is '登录账号';
comment on column sys_user_t.user_name            is '用户昵称';
comment on column sys_user_t.user_type            is '用户类型（00系统用户）';
comment on column sys_user_t.email                is '用户邮箱';
comment on column sys_user_t.phonenumber          is '手机号码';
comment on column sys_user_t.sex                  is '用户性别（0男 1女 2未知）';
comment on column sys_user_t.avatar               is '头像路径';
comment on column sys_user_t.password             is '密码';
comment on column sys_user_t.salt                 is '盐加密';
comment on column sys_user_t.company_id                 is '公司ID';
comment on column sys_user_t.company_descr                 is '公司描述';
comment on column sys_user_t.active               is '帐号状态（Y正常 N停用）';
comment on column sys_user_t.del_flag             is '删除标志（N代表存在 Y代表删除）';
comment on column sys_user_t.login_ip             is '最后登陆IP';
comment on column sys_user_t.login_date           is '最后登陆时间';
comment on column sys_user_t.create_by            is '创建者';
comment on column sys_user_t.create_time          is '创建时间';
comment on column sys_user_t.update_by            is '更新者';
comment on column sys_user_t.update_time          is '更新时间';
comment on column sys_user_t.update_version       is '更新版本';
comment on column sys_user_t.description          is '备注';



-- ----------------------------
-- 角色信息表
-- ----------------------------
drop table sys_role_t cascade constraints;
create table sys_role_t (
  role_id             number          not null,
  code           	  varchar2(30)     not null,
  descr               varchar2(100)   not null,
  active               char(1)     default 'Y',
  del_flag            char(1)         default 'N',
  create_by           varchar2(64)    default '',
  create_time         date            default sysdate,
  update_by           varchar2(64)    default '',
  update_time         date            default sysdate,
  update_version      number          default '1',
  description         varchar2(500)   default null
);

alter table sys_role_t add constraint pk_sys_role_t primary key (role_id);

comment on table  sys_role_t                      is '角色信息表';
comment on column sys_role_t.role_id              is '角色主键';
comment on column sys_role_t.descr            	is '角色名称';
comment on column sys_role_t.code             	is '角色权限字符串';
comment on column sys_role_t.active               is '角色状态（Y正常 N停用）';
comment on column sys_role_t.del_flag             is '删除标志（N代表存在 Y代表删除）';
comment on column sys_role_t.create_by            is '创建者';
comment on column sys_role_t.create_time          is '创建时间';
comment on column sys_role_t.update_by            is '更新者';
comment on column sys_role_t.update_time          is '更新时间';
comment on column sys_role_t.update_version       is '更新版本';
comment on column sys_role_t.description          is '备注';


-- ----------------------------
-- 权限表
-- ----------------------------

drop table sys_permission_t cascade constraints;
create table sys_permission_t (
  permission_id       number           not null,
  permission_name     varchar2(50)     not null,
  parent_id           number          default 0,
  sort               number          default 0,
  url                 varchar2(200)   default '#',
  permission_type     varchar2(50)         default '',
  warehouse_flag       char(1)         default 'N',
  visible             char(1)         default 'Y',
  perms               varchar2(100)   default null,
  icon                 varchar2(100)   default '#',
  del_flag            char(1)         default 'N',
  create_by           varchar2(64)    default '',
  create_time         date            default sysdate,
  update_by           varchar2(64)    default '',
  update_time         date            default sysdate,
  update_version      number          default '1',
  description         varchar2(500)   default null
);

alter table sys_permission_t add constraint pk_sys_permission_t primary key (permission_id);

comment on table  sys_permission_t                    is '权限表';
comment on column sys_permission_t.permission_id      is '权限主键';
comment on column sys_permission_t.permission_name    is '权限名称';
comment on column sys_permission_t.parent_id          is '父权限ID';
comment on column sys_permission_t.sort              is '显示顺序';
comment on column sys_permission_t.url                is '请求地址';
comment on column sys_permission_t.permission_type    is '权限类型(M权限 F功能按钮)';
comment on column sys_permission_t.warehouse_flag     is '仓库权限';
comment on column sys_permission_t.visible            is '权限状态（Y显示 N隐藏）';
comment on column sys_permission_t.perms              is '权限标识';
comment on column sys_permission_t.icon               is '权限图标';
comment on column sys_permission_t.del_flag           is '删除标志（N代表存在 Y代表删除）';
comment on column sys_permission_t.create_by          is '创建者';
comment on column sys_permission_t.create_time        is '创建时间';
comment on column sys_permission_t.update_by          is '更新者';
comment on column sys_permission_t.update_time        is '更新时间';
comment on column sys_permission_t.update_version     is '更新版本';
comment on column sys_permission_t.description        is '备注';







-- ----------------------------
-- 用户和角色关联表  用户N-1角色
-- ----------------------------
drop table sys_user_role_t cascade constraints;
create table sys_user_role_t (
  user_role_id             number          not null,
  user_id                  number          not null,
  role_id                  number          not null,
  del_flag                 char(1)         default 'N',
  create_by                varchar2(64)    default '',
  create_time              date            default sysdate,
  update_by                varchar2(64)    default '',
  update_time              date            default sysdate,
  update_version           number          default '1',
  description              varchar2(500)   default null
);

alter table sys_user_role_t add constraint pk_sys_user_role_t primary key (user_role_id);

comment on table  sys_user_role_t                    is '用户和角色关联表';
comment on column sys_user_role_t.user_role_id       is '用户角色ID';
comment on column sys_user_role_t.user_id            is '用户ID';
comment on column sys_user_role_t.role_id            is '角色ID';
comment on column sys_user_role_t.del_flag           is '删除标志（N代表存在 Y代表删除）';
comment on column sys_user_role_t.create_by          is '创建者';
comment on column sys_user_role_t.create_time        is '创建时间';
comment on column sys_user_role_t.update_by          is '更新者';
comment on column sys_user_role_t.update_time        is '更新时间';
comment on column sys_user_role_t.update_version     is '更新版本';
comment on column sys_user_role_t.description        is '备注';  


-- ----------------------------
-- 角色和权限关联表  角色1-N权限
-- ----------------------------
drop table sys_role_permission_t cascade constraints;
create table sys_role_permission_t (
  role_permission_id             number  not null,
  role_id                        number  not null,
  permission_id                  number  not null,
  del_flag                       char(1)         default 'N',
  create_by                      varchar2(64)    default '',
  create_time                    date            default sysdate,
  update_by                      varchar2(64)    default '',
  update_time                    date            default sysdate,
  update_version                 number          default '1',
  description                    varchar2(500)   default null
);

alter table sys_role_permission_t add constraint pk_sys_role_permission_t primary key (role_permission_id);

comment on table  sys_role_permission_t              is '角色和权限关联表';
comment on column sys_role_permission_t.role_id      is '角色ID';
comment on column sys_role_permission_t.permission_id      is '权限ID';
comment on column sys_role_permission_t.del_flag           is '删除标志（N代表存在 Y代表删除）';
comment on column sys_role_permission_t.create_by          is '创建者';
comment on column sys_role_permission_t.create_time        is '创建时间';
comment on column sys_role_permission_t.update_by          is '更新者';
comment on column sys_role_permission_t.update_time        is '更新时间';
comment on column sys_role_permission_t.update_version     is '更新版本';
comment on column sys_role_permission_t.description        is '备注';  


-- ----------------------------
-- 操作日志记录
-- ----------------------------
drop table sys_oper_log_t cascade constraints;
create table sys_oper_log_t (
  oper_log_id             number          not null ,
  title                   varchar2(50)    default '',
  business_type           varchar2(50) ,
  method                  varchar2(100)   default '',
  operator_type           varchar2(50) ,
  oper_name               varchar2(50)    default '',
  oper_url                 varchar2(2000)   default '',
  oper_ip                 varchar2(50)   default '',
  oper_location           varchar2(255)   default '',
  oper_param               varchar2(2000)   default '',
  status                   varchar2(50)   default 'N',
  error_msg               varchar2(2000)   default '' ,
  oper_time               date,
  company_id              number        default '0',
  warehouse_id            number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table sys_oper_log_t add constraint pk_sys_oper_log_t primary key (oper_log_id);

comment on table  sys_oper_log_t               is '操作日志记录';
comment on column sys_oper_log_t.oper_log_id       is '日志主键';
comment on column sys_oper_log_t.title         is '模块标题';
comment on column sys_oper_log_t.business_type is '业务类型（INBOUND 入库 OUTBOUND 出库 OTHER其他）';
comment on column sys_oper_log_t.method        is '方法名称';
comment on column sys_oper_log_t.operator_type is '操作类别（OTHER其它 ADD新增 MODIFY修改 DELETE删除）';
comment on column sys_oper_log_t.oper_name     is '操作人员';
comment on column sys_oper_log_t.oper_url      is '请求URL';
comment on column sys_oper_log_t.oper_ip       is '主机地址';
comment on column sys_oper_log_t.oper_location is '操作地点';
comment on column sys_oper_log_t.oper_param    is '请求参数';
comment on column sys_oper_log_t.status        is '操作状态（Y正常 N异常）';
comment on column sys_oper_log_t.error_msg     is '错误消息';
comment on column sys_oper_log_t.oper_time     is '操作时间';
comment on column sys_oper_log_t.company_id      is '公司ID';
comment on column sys_oper_log_t.warehouse_id    is '仓库ID';
comment on column sys_oper_log_t.del_flag           is '删除标志（N代表存在 Y代表删除）';
comment on column sys_oper_log_t.create_by          is '创建者';
comment on column sys_oper_log_t.create_time        is '创建时间';
comment on column sys_oper_log_t.update_by          is '更新者';
comment on column sys_oper_log_t.update_time        is '更新时间';
comment on column sys_oper_log_t.update_version     is '更新版本';
comment on column sys_oper_log_t.description        is '备注';  




-- ----------------------------
-- 字典类型表
-- ----------------------------
drop table sys_codelist_t_back cascade constraints;
create table sys_codelist_t_back
(
  codelist_id      number          not null,
  code             varchar2(100)   default '',
  descr            varchar2(500)   default '',
  type             varchar2(100)   default '',
  active            char(1)          default 'Y',
  is_all           	char(1)   default 'N',
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag         char(1)         default 'N',
  create_by        varchar2(64)    default '',
  create_time      date,
  update_by        varchar2(64)    default '',
  update_time      date,
  update_version   number          default '1',
  description            varchar2(500)    default null
);

alter table sys_codelist_t_back add constraint pk_sys_codelist_t_back primary key (codelist_id);
create index sys_codelist_t_back_index1 on sys_codelist_t_back (code);

comment on table  sys_codelist_t_back               is '字典类型表';
comment on column sys_codelist_t_back.codelist_id   is '字典主键';
comment on column sys_codelist_t_back.code          is '字典代码';
comment on column sys_codelist_t_back.descr          is '字典描述';
comment on column sys_codelist_t_back.type          is '字典类型';
comment on column sys_codelist_t_back.active        is '状态（Y正常 N停用）';
comment on column sys_codelist_t_back.is_all        is '应用所有维度,不匹配仓库（Y所有 N按仓库）';
comment on column sys_codelist_t_back.company_id      is '公司ID';
comment on column sys_codelist_t_back.warehouse_id    is '仓库ID';
comment on column sys_codelist_t_back.del_flag      is '删除标志（N代表存在 Y代表删除）';
comment on column sys_codelist_t_back.create_by     is '创建者';
comment on column sys_codelist_t_back.create_time   is '创建时间';
comment on column sys_codelist_t_back.update_by     is '更新者';
comment on column sys_codelist_t_back.update_time   is '更新时间';
comment on column sys_codelist_t_back.update_version   is '更新时间';
comment on column sys_codelist_t_back.description        is '备注';


-- ----------------------------
-- 字典数据表
-- ----------------------------
drop table sys_codelkup_t cascade constraints;
create table sys_codelkup_t
(
  codelkup_id      number          not null,    
  codelist_id      number          not null,    
  code             varchar2(100)   not null,
  sort             number          default 0,
  descr            varchar2(500)   default '',
  remark            varchar2(500)   default '',
  type             varchar2(100)   default '',
  active           varchar2(100)   default 'Y',
  is_default       char(1)         default 'N',
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table sys_codelkup_t add constraint pk_sys_codelkup_t primary key (codelkup_id);

comment on table  sys_codelkup_t               is '字典数据表';
comment on column sys_codelkup_t.codelkup_id     is '字典主键';
comment on column sys_codelkup_t.codelist_id     is '关联主表';
comment on column sys_codelkup_t.code     is '字典代码';
comment on column sys_codelkup_t.sort     is '字典排序';
comment on column sys_codelkup_t.descr    is '字典标签';
comment on column sys_codelkup_t.remark    is '字典描述';
comment on column sys_codelkup_t.type     is '字典类型';
comment on column sys_codelkup_t.is_default    is '是否默认（Y是 N否）';
comment on column sys_codelkup_t.active        is '状态（Y正常 N停用）';
comment on column sys_codelkup_t.company_id      is '公司ID';
comment on column sys_codelkup_t.warehouse_id    is '仓库ID';
comment on column sys_codelkup_t.del_flag           is '删除标志（N代表存在 Y代表删除）';
comment on column sys_codelkup_t.create_by     is '创建者';
comment on column sys_codelkup_t.create_time   is '创建时间';
comment on column sys_codelkup_t.update_by     is '更新者';
comment on column sys_codelkup_t.update_time   is '更新时间';
comment on column sys_codelkup_t.update_version     is '更新版本';
comment on column sys_codelkup_t.description        is '备注';



-- ----------------------------
-- 文件表
-- ----------------------------

drop table sys_file_t cascade constraints;
create table sys_file_t (
  file_id          number        not null,
  object_id        varchar2(200) default '',
  file_type        varchar2(200),
  file_name        varchar2(200) default '',
  template        varchar2(200) default '',
  file_size            number default 0,
  content_type     varchar2(200) default '',
  status          varchar2(100),
  start_time        date,
  end_time      date,
  user_id      number,
  remark       varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table sys_file_t add constraint pk_sys_file_t primary key (file_id);

comment on table  sys_file_t               is '文件列表';
comment on column sys_file_t.file_id     is '文件ID';
comment on column sys_file_t.object_id   is 'mangodb 对应ID';
comment on column sys_file_t.file_name    is '文件名';
comment on column sys_file_t.template    is '模板';
comment on column sys_file_t.file_size    is '大小（B）';
comment on column sys_file_t.content_type    is '文件类型';
comment on column sys_file_t.file_type  is '文件类型  SAVE 存储  IMPORT 导入 EXPORT 导出';
comment on column sys_file_t.status  is '状态 0 初始化 10 上传失败  20 上传成功  30 处理中 40 处理完成 50 处理失败';
comment on column sys_file_t.start_time  is '开始处理时间 ';
comment on column sys_file_t.end_time  is '结束处理时间 ';
comment on column sys_file_t.user_id  is '用户ID ';
comment on column sys_file_t.remark  is '备注 ';
comment on column sys_file_t.company_id      is '公司ID';
comment on column sys_file_t.warehouse_id    is '仓库ID';
comment on column sys_file_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column sys_file_t.create_by     is '创建者';
comment on column sys_file_t.create_time   is '创建时间';
comment on column sys_file_t.update_by     is '更新者';
comment on column sys_file_t.update_time   is '更新时间';
comment on column sys_file_t.update_version   is '更新时间';
comment on column sys_file_t.description   is '数据描述';


-- ----------------------------
-- 参数配置表
-- ----------------------------

drop table sys_config_t cascade constraints;
create table sys_config_t (
  config_id          number        not null,
  config_code        varchar2(100) default '',
  config_descr       varchar2(100) default '',
  config_value       varchar2(100) default '',
  config_type        char(1)       default 'N',
  active             varchar2(100)   default 'Y',
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table sys_config_t add constraint pk_sys_config_t primary key (config_id);

comment on table  sys_config_t               is '参数配置表';
comment on column sys_config_t.config_id     is '参数主键';
comment on column sys_config_t.config_code   is '参数代码';
comment on column sys_config_t.config_descr    is '参数描述';
comment on column sys_config_t.config_value  is '参数键值';
comment on column sys_config_t.config_type   is '参数类型 O开关 V值';
comment on column sys_config_t.active      is '状态（Y正常 N停用）';
comment on column sys_config_t.company_id      is '公司ID';
comment on column sys_config_t.warehouse_id    is '仓库ID';
comment on column sys_config_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column sys_config_t.create_by     is '创建者';
comment on column sys_config_t.create_time   is '创建时间';
comment on column sys_config_t.update_by     is '更新者';
comment on column sys_config_t.update_time   is '更新时间';
comment on column sys_config_t.update_version   is '更新时间';
comment on column sys_config_t.description   is '数据描述';




-- ----------------------------
-- 仓库列表
-- ----------------------------
drop table sys_warehouses_t cascade constraints;
create table sys_warehouses_t
(
  warehouse_id      number          not null,
  code              varchar2(100)   default '',
  descr             varchar2(500)   default '',
  type              varchar2(100)   default '',
  active            char(1)          default 'Y',
  del_flag          char(1)         default 'N',
  create_by         varchar2(64)    default '',
  create_time       date,
  update_by         varchar2(64)    default '',
  update_time       date,
  update_version    number          default '1',
  description             varchar2(500)    default null
);

alter table sys_warehouses_t add constraint pk_sys_warehouses_t primary key (warehouse_id);

comment on table  sys_warehouses_t               is '仓库列表';
comment on column sys_warehouses_t.warehouse_id  is '仓库主键';
comment on column sys_warehouses_t.code          is '仓库代码';
comment on column sys_warehouses_t.descr          is '仓库描述';
comment on column sys_warehouses_t.type          is '仓库类型';
comment on column sys_warehouses_t.active        is '状态（Y正常 N停用）';
comment on column sys_warehouses_t.del_flag      is '删除标志（N代表存在 Y代表删除）';
comment on column sys_warehouses_t.create_by     is '创建者';
comment on column sys_warehouses_t.create_time   is '创建时间';
comment on column sys_warehouses_t.update_by     is '更新者';
comment on column sys_warehouses_t.update_time   is '更新时间';
comment on column sys_warehouses_t.update_version   is '更新时间';
comment on column sys_warehouses_t.description        is '备注';


-- ----------------------------
-- 公司列表
-- ----------------------------
drop table sys_companys_t cascade constraints;
create table sys_companys_t
(
  company_id        number          not null,
  code              varchar2(100)   default '',
  descr             varchar2(500)   default '',
  type              varchar2(100)   default '',
  active             char(1)          default 'Y',
  del_flag          char(1)         default 'N',
  create_by         varchar2(64)    default '',
  create_time       date,
  update_by         varchar2(64)    default '',
  update_time       date,
  update_version    number          default '1',
  description             varchar2(500)    default null
);

alter table sys_companys_t add constraint pk_sys_companys_t primary key (company_id);

comment on table  sys_companys_t               is '公司列表';
comment on column sys_companys_t.company_id    is '公司主键';
comment on column sys_companys_t.code          is '公司代码';
comment on column sys_companys_t.descr          is '公司名称';
comment on column sys_companys_t.type          is '公司类型';
comment on column sys_companys_t.active        is '状态（Y正常 N停用）';
comment on column sys_companys_t.del_flag      is '删除标志（N代表存在 Y代表删除）';
comment on column sys_companys_t.create_by     is '创建者';
comment on column sys_companys_t.create_time   is '创建时间';
comment on column sys_companys_t.update_by     is '更新者';
comment on column sys_companys_t.update_time   is '更新时间';
comment on column sys_companys_t.update_version   is '更新时间';
comment on column sys_companys_t.description        is '备注';




-- ----------------------------
-- 数据和角色关联表  数据N-1角色
-- ----------------------------
drop table sys_role_data_t cascade constraints;
create table sys_role_data_t (
  role_data_id             number          not null,
  data_type                varchar2(2)     not null,
  data_id                  number          not null,
  role_id                  number          not null,
  del_flag                 char(1)         default 'N',
  create_by                varchar2(64)    default '',
  create_time              date            default sysdate,
  update_by                varchar2(64)    default '',
  update_time              date            default sysdate,
  update_version           number          default '1',
  description              varchar2(500)   default null
);

alter table sys_role_data_t add constraint pk_sys_role_data_t primary key (role_data_id);

comment on table  sys_role_data_t                    is '数据和角色关联表';
comment on column sys_role_data_t.role_data_id       is '数据角色ID';
comment on column sys_role_data_t.data_type          is '数据类型 10公司 20仓库 ';
comment on column sys_role_data_t.data_id            is '数据ID';
comment on column sys_role_data_t.role_id            is '角色ID';
comment on column sys_role_data_t.del_flag           is '删除标志（N代表存在 Y代表删除）';
comment on column sys_role_data_t.create_by          is '创建者';
comment on column sys_role_data_t.create_time        is '创建时间';
comment on column sys_role_data_t.update_by          is '更新者';
comment on column sys_role_data_t.update_time        is '更新时间';
comment on column sys_role_data_t.update_version     is '更新版本';
comment on column sys_role_data_t.description        is '备注';  




-- ----------------------------
-- 在线用户记录
-- ----------------------------
drop table sys_user_online_t cascade constraints;
create table sys_user_online_t (
  sessionId                  varchar2(50),
  login_name                 varchar2(50)  default '',
  ipaddr                     varchar2(50)  default '',
  login_location             varchar2(255) default '',
  browser                    varchar2(50)  default '',
  os                         varchar2(50)  default '',
  status                     varchar2(10)  default '',
  start_timestamp            date,
  last_access_time           date,
  expire_time                number      default 0,
  del_flag                   char(1)         default 'N',
  create_by                  varchar2(64)    default '',
  create_time                date            default sysdate,
  update_by                  varchar2(64)    default '',
  update_time                date            default sysdate,
  update_version             number          default '1',
  description                varchar2(500)   default null
);

alter table sys_user_online_t add constraint pk_sys_user_online_t primary key (sessionId);

comment on table  sys_user_online_t                   is '在线用户记录';
comment on column sys_user_online_t.sessionId         is '用户会话id';
comment on column sys_user_online_t.login_name        is '登录账号';
comment on column sys_user_online_t.ipaddr            is '登录IP地址';
comment on column sys_user_online_t.login_location    is '登录地点';
comment on column sys_user_online_t.browser           is '浏览器类型';
comment on column sys_user_online_t.os                is '操作系统';
comment on column sys_user_online_t.status            is '在线状态Y在线N离线';
comment on column sys_user_online_t.start_timestamp   is 'session创建时间';
comment on column sys_user_online_t.last_access_time  is 'session最后访问时间';
comment on column sys_user_online_t.expire_time       is '超时时间，单位为分钟';
comment on column sys_user_online_t.del_flag           is '删除标志（N代表存在 Y代表删除）';
comment on column sys_user_online_t.create_by          is '创建者';
comment on column sys_user_online_t.create_time        is '创建时间';
comment on column sys_user_online_t.update_by          is '更新者';
comment on column sys_user_online_t.update_time        is '更新时间';
comment on column sys_user_online_t.update_version     is '更新版本';
comment on column sys_user_online_t.description        is '备注';  



-- ----------------------------
-- 系统访问记录
-- ----------------------------

drop table sys_login_infor_t cascade constraints;
create table sys_login_infor_t (
  login_info_id                   number        not null,
  login_name                varchar2(50)   default '',
  ip_address                    varchar2(50)   default '',
  login_location            varchar2(255)  default '',
  browser                   varchar2(50)   default '',
  os                        varchar2(50)   default '',
  status                    char(1)      default 'N',
  msg                       varchar2(255)  default '',
  login_time                date,
  del_flag                   char(1)         default 'N',
  create_by                  varchar2(64)    default '',
  create_time                date            default sysdate,
  update_by                  varchar2(64)    default '',
  update_time                date            default sysdate,
  update_version             number          default '1',
  description                varchar2(500)   default null
);

alter table sys_login_infor_t add constraint pk_sys_login_infor_t primary key (login_info_id);

comment on table  sys_login_infor_t                is '系统访问记录';
comment on column sys_login_infor_t.login_info_id        is '访问主键';
comment on column sys_login_infor_t.login_name     is '登录账号';
comment on column sys_login_infor_t.ip_address         is '登录IP地址';
comment on column sys_login_infor_t.login_location is '登录地点';
comment on column sys_login_infor_t.browser        is '浏览器类型';
comment on column sys_login_infor_t.os             is '操作系统';
comment on column sys_login_infor_t.status         is '登录状态（Y成功 N失败）';
comment on column sys_login_infor_t.msg            is '提示消息';
comment on column sys_login_infor_t.login_time     is '访问时间';
comment on column sys_login_infor_t.del_flag           is '删除标志（N代表存在 Y代表删除）';
comment on column sys_login_infor_t.create_by          is '创建者';
comment on column sys_login_infor_t.create_time        is '创建时间';
comment on column sys_login_infor_t.update_by          is '更新者';
comment on column sys_login_infor_t.update_time        is '更新时间';
comment on column sys_login_infor_t.update_version     is '更新版本';
comment on column sys_login_infor_t.description        is '备注';  




-- ----------------------------
-- 语言配置表
-- ----------------------------

drop table sys_locale_t cascade constraints;
create table sys_locale_t (
  locale_id          number        not null,
  locale_code        varchar2(100) default '',
  label_key          varchar2(100) default '',
  label_value        varchar2(100) default '',
  join_key1          varchar2(100) default '',
  join_key2          varchar2(100) default '',
  join_key3          varchar2(100) default '',
  join_key4          varchar2(100) default '',
  join_key5          varchar2(100) default '',
  table_name         varchar2(100) default '',
  active             varchar2(100) default 'Y',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table sys_locale_t add constraint pk_sys_locale_t primary key (locale_id);

comment on table  sys_locale_t               is '语言配置表';
comment on column sys_locale_t.locale_id     is '语言主键';
comment on column sys_locale_t.locale_code   is '语言代码';
comment on column sys_locale_t.label_key     is '标签代码';
comment on column sys_locale_t.label_value   is '标签';
comment on column sys_locale_t.join_key1     is '关联主键1';
comment on column sys_locale_t.join_key2     is '关联主键2';
comment on column sys_locale_t.join_key3     is '关联主键3';
comment on column sys_locale_t.join_key4     is '关联主键4';
comment on column sys_locale_t.join_key5     is '关联主键5';
comment on column sys_locale_t.table_name    is '关联表';
comment on column sys_locale_t.active      is '状态（Y正常 N停用）';
comment on column sys_locale_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column sys_locale_t.create_by     is '创建者';
comment on column sys_locale_t.create_time   is '创建时间';
comment on column sys_locale_t.update_by     is '更新者';
comment on column sys_locale_t.update_time   is '更新时间';
comment on column sys_locale_t.update_version   is '更新时间';
comment on column sys_locale_t.description   is '数据描述';


-- ----------------------------
-- 用户默认表
-- ----------------------------

drop table sys_user_default_t cascade constraints;
create table sys_user_default_t (
  default_id                   number        not null,
  login_name                   varchar2(100),
  warehouse_code               varchar2(100),
  locale_code                  varchar2(100),
  del_flag                   char(1)         default 'N',
  create_by                  varchar2(64)    default '',
  create_time                date            default sysdate,
  update_by                  varchar2(64)    default '',
  update_time                date            default sysdate,
  update_version             number          default '1',
  description                varchar2(500)   default null
);

alter table sys_user_default_t add constraint pk_sys_user_default_t primary key (default_id);

comment on table  sys_user_default_t                is '用户默认值';
comment on column sys_user_default_t.default_id     is '主键';
comment on column sys_user_default_t.login_name      is '登录名';
comment on column sys_user_default_t.warehouse_code   is '仓库';
comment on column sys_user_default_t.locale_code         is '语言';
comment on column sys_user_default_t.del_flag           is '删除标志（N代表存在 Y代表删除）';
comment on column sys_user_default_t.create_by          is '创建者';
comment on column sys_user_default_t.create_time        is '创建时间';
comment on column sys_user_default_t.update_by          is '更新者';
comment on column sys_user_default_t.update_time        is '更新时间';
comment on column sys_user_default_t.update_version     is '更新版本';
comment on column sys_user_default_t.description        is '备注';  

create or replace view user_default_v as
select userd.*,w.descr warehouse_descr from sys_user_default_t userd left join sys_warehouses_t w on userd.warehouse_code = w.code and userd.del_flag = 'N' and w.del_flag = 'N' 




-- ----------------------------
-- 监控日志记录
-- ----------------------------
drop table sys_monitor_log_t cascade constraints;
create table sys_monitor_log_t (
  monitor_log_id             number          not null ,
  url						 varchar2(500)    default '',
  clz                        varchar2(500)    default '',
  method                     varchar2(50),
  type                       varchar2(50),
  request                    varchar2(50),
  response                   varchar2(50),
  error                      varchar2(50),
  status                     varchar2(50),
  time                       number         default 0,
  ip                         varchar2(50)    default '',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table sys_monitor_log_t add constraint pk_sys_monitor_log_t primary key (monitor_log_id);

comment on table  sys_monitor_log_t               is '操作日志记录';
comment on column sys_monitor_log_t.monitor_log_id       is '日志主键';
comment on column sys_monitor_log_t.url         is '方法';
comment on column sys_monitor_log_t.clz         is '类';
comment on column sys_monitor_log_t.method        is '方法';
comment on column sys_monitor_log_t.type          is '业务类型（10 Rest请求 20 异常记录）';
comment on column sys_monitor_log_t.request       is 'Rest请求参数';
comment on column sys_monitor_log_t.response      is 'Rest响应参数';
comment on column sys_monitor_log_t.error         is '异常信息';
comment on column sys_monitor_log_t.ip            is '主机地址';
comment on column sys_monitor_log_t.status        is '操作状态（00处理中 10正常 20异常）';
comment on column sys_monitor_log_t.time          is '操作时长';
comment on column sys_monitor_log_t.del_flag           is '删除标志（N代表存在 Y代表删除）';
comment on column sys_monitor_log_t.create_by          is '创建者';
comment on column sys_monitor_log_t.create_time        is '创建时间';
comment on column sys_monitor_log_t.update_by          is '更新者';
comment on column sys_monitor_log_t.update_time        is '更新时间';
comment on column sys_monitor_log_t.update_version     is '更新版本';
comment on column sys_monitor_log_t.description        is '备注';  




-- ----------------------------
-- 单号生成表
-- ----------------------------

drop table sys_order_number_t cascade constraints;
create table sys_order_number_t (
  order_number_id                   number        not null,
  code                              varchar2(100),
  descr                             varchar2(100),
  prefix                            varchar2(100),
  length                            number        default 10,
  data_format                       varchar2(100),
  sequence                          number        default 1,
  sequence_cache                     number        default 5,
  sequence_increment                number        default 1,
  company_id                        number        default '0',
  warehouse_id                      number        default '0',
  del_flag                char(1)         default 'N',
  create_by                  varchar2(64)    default '',
  create_time                date            default sysdate,
  update_by                  varchar2(64)    default '',
  update_time                date            default sysdate,
  update_version             number          default '1',
  description                varchar2(500)   default null
);

alter table sys_order_number_t add constraint pk_sys_order_number_t primary key (order_number_id);

comment on table  sys_order_number_t                     is '用户默认值';
comment on column sys_order_number_t.order_number_id     is '主键';
comment on column sys_order_number_t.code                is '代码';
comment on column sys_order_number_t.descr               is '描述';
comment on column sys_order_number_t.prefix              is '前缀';
comment on column sys_order_number_t.data_format         is '日期转换(YYMMDD)';
comment on column sys_order_number_t.sequence            is '当前值';
comment on column sys_order_number_t.sequence_cache       is '缓存值';
comment on column sys_order_number_t.sequence_increment  is '自增值';
comment on column sys_order_number_t.company_id          is '公司';
comment on column sys_order_number_t.warehouse_id        is '仓库';
comment on column sys_order_number_t.del_flag           is '删除标志（N代表存在 Y代表删除）';
comment on column sys_order_number_t.create_by          is '创建者';
comment on column sys_order_number_t.create_time        is '创建时间';
comment on column sys_order_number_t.update_by          is '更新者';
comment on column sys_order_number_t.update_time        is '更新时间';
comment on column sys_order_number_t.update_version     is '更新版本';
comment on column sys_order_number_t.description        is '备注';  




-- ----------------------------
-- 货主
-- ----------------------------

drop table owner_t cascade constraints;
create table owner_t (
  owner_id           number not null,
  owner_code         varchar2(50) not null,
  owner_descr        varchar2(200),
  contact1           varchar2(50),
  contact2           varchar2(50),
  phone1             varchar2(50),
  phone2             varchar2(50),
  address1           varchar2(200),
  address2           varchar2(200),
  fax                varchar2(50),
  email              varchar2(100),
  barcode_prefix              varchar2(50),
  barcode_length              number default 10,
  barcode_start              number default 1,
  active             char(1)  default 'Y',
  remark             varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table owner_t add constraint pk_owner_t primary key (owner_id);

comment on table  owner_t               is '货主';
comment on column owner_t.owner_id is '货主ID';
comment on column owner_t.owner_code is '货主代码';
comment on column owner_t.owner_descr is '描述';
comment on column owner_t.contact1 is '联系人1';
comment on column owner_t.contact2 is '联系人2';
comment on column owner_t.phone1 is '联系电话1';
comment on column owner_t.phone2 is '联系电话2';
comment on column owner_t.address1 is '地址1';
comment on column owner_t.address2 is '地址2';
comment on column owner_t.fax is 'FAX';
comment on column owner_t.email is 'EMAIL';
comment on column owner_t.barcode_prefix is '条码前缀';
comment on column owner_t.barcode_length is '条码长度';
comment on column owner_t.barcode_start is '条码起始值';
comment on column owner_t.remark is '备注';
comment on column owner_t.active      is '状态（Y正常 N停用）';
comment on column owner_t.company_id      is '公司ID';
comment on column owner_t.warehouse_id    is '仓库ID';
comment on column owner_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column owner_t.create_by     is '创建者';
comment on column owner_t.create_time   is '创建时间';
comment on column owner_t.update_by     is '更新者';
comment on column owner_t.update_time   is '更新时间';
comment on column owner_t.update_version   is '更新时间';
comment on column owner_t.description   is '数据描述';




-- ----------------------------
-- 条码打印
-- ----------------------------

drop table barcode_t cascade constraints;
create table barcode_t (
  barcode_id           number not null,
  batch_id         	number not null,
  type         		varchar2(50),
  barcode        	varchar2(200),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table barcode_t add constraint pk_barcode_t primary key (barcode_id);

comment on table  barcode_t               is '条码';
comment on column barcode_t.barcode_id is '条码ID';
comment on column barcode_t.batch_id is '条码批次ID';
comment on column barcode_t.type is '条码类型';
comment on column barcode_t.barcode is '货主条码';
comment on column barcode_t.company_id      is '公司ID';
comment on column barcode_t.warehouse_id    is '仓库ID';
comment on column barcode_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column barcode_t.create_by     is '创建者';
comment on column barcode_t.create_time   is '创建时间';
comment on column barcode_t.update_by     is '更新者';
comment on column barcode_t.update_time   is '更新时间';
comment on column barcode_t.update_version   is '更新时间';
comment on column barcode_t.description   is '数据描述';



-- ----------------------------
-- 客户（送货地点）
-- ----------------------------

drop table customer_t cascade constraints;
create table customer_t (
  customer_id number not null,
customer_code   varchar2(50) not null,
customer_descr  varchar2(200),
contact1  varchar2(50),
contact2  varchar2(50),
phone1  varchar2(50),
phone2  varchar2(50),
address1  varchar2(200),
address2  varchar2(200),
fax   varchar2(50),
email varchar2(100),
ship_label varchar2(100),
active    char(1)  default 'Y',
remark  varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table customer_t add constraint pk_customer_t primary key (customer_id);

comment on table  customer_t               is '客户（送货地点）';
comment on column customer_t.customer_id is '客户ID';
comment on column customer_t.customer_code is '客户代码';
comment on column customer_t.customer_descr is '描述';
comment on column customer_t.contact1 is '联系人1';
comment on column customer_t.contact2 is '联系人2';
comment on column customer_t.phone1 is '联系电话1';
comment on column customer_t.phone2 is '联系电话2';
comment on column customer_t.address1 is '地址1';
comment on column customer_t.address2 is '地址2';
comment on column customer_t.fax is 'FAX';
comment on column customer_t.email is 'EMAIL';
comment on column customer_t.ship_label is '发货标签';
comment on column customer_t.remark is '备注';
comment on column customer_t.active      is '状态（Y正常 N停用）';
comment on column customer_t.company_id      is '公司ID';
comment on column customer_t.warehouse_id    is '仓库ID';
comment on column customer_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column customer_t.create_by     is '创建者';
comment on column customer_t.create_time   is '创建时间';
comment on column customer_t.update_by     is '更新者';
comment on column customer_t.update_time   is '更新时间';
comment on column customer_t.update_version   is '更新时间';
comment on column customer_t.description   is '数据描述';


-- ----------------------------
-- 供应商
-- ----------------------------

drop table supplier_t cascade constraints;
create table supplier_t (
  supplier_id number not null,
supplier_code   varchar2(50) not null,
supplier_descr  varchar2(200),
contact1  varchar2(50),
contact2  varchar2(50),
phone1  varchar2(50),
phone2  varchar2(50),
address1  varchar2(200),
address2  varchar2(200),
fax   varchar2(50),
email varchar2(100),
active    char(1)  default 'Y',
remark  varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table supplier_t add constraint pk_supplier_t primary key (supplier_id);

comment on table  supplier_t               is '供应商';
comment on column supplier_t.supplier_id is '供应商ID';
comment on column supplier_t.supplier_code is '供应商代码';
comment on column supplier_t.supplier_descr is '描述';
comment on column supplier_t.contact1 is '联系人1';
comment on column supplier_t.contact2 is '联系人2';
comment on column supplier_t.phone1 is '联系电话1';
comment on column supplier_t.phone2 is '联系电话2';
comment on column supplier_t.address1 is '地址1';
comment on column supplier_t.address2 is '地址2';
comment on column supplier_t.fax is 'FAX';
comment on column supplier_t.email is 'EMAIL';
comment on column supplier_t.remark is '备注';
comment on column supplier_t.active      is '状态（Y正常 N停用）';
comment on column supplier_t.company_id      is '公司ID';
comment on column supplier_t.warehouse_id    is '仓库ID';
comment on column supplier_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column supplier_t.create_by     is '创建者';
comment on column supplier_t.create_time   is '创建时间';
comment on column supplier_t.update_by     is '更新者';
comment on column supplier_t.update_time   is '更新时间';
comment on column supplier_t.update_version   is '更新时间';
comment on column supplier_t.description   is '数据描述';



-- ----------------------------
-- 承运人
-- ----------------------------

drop table carrier_t cascade constraints;
create table carrier_t (
  carrier_id  number not null,
carrier_code    varchar2(50) not null,
carrier_descr varchar2(200),
contact1  varchar2(50),
contact2  varchar2(50),
phone1  varchar2(50),
phone2  varchar2(50),
address1  varchar2(200),
address2  varchar2(200),
fax   varchar2(50),
email varchar2(100),
active    char(1)  default 'Y',
remark  varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table carrier_t add constraint pk_carrier_t primary key (carrier_id);

comment on table  carrier_t               is '承运人';
comment on column carrier_t.carrier_id is '承运人ID';
comment on column carrier_t.carrier_code is '承运人代码';
comment on column carrier_t.carrier_descr is '描述';
comment on column carrier_t.contact1 is '联系人1';
comment on column carrier_t.contact2 is '联系人2';
comment on column carrier_t.phone1 is '联系电话1';
comment on column carrier_t.phone2 is '联系电话2';
comment on column carrier_t.address1 is '地址1';
comment on column carrier_t.address2 is '地址2';
comment on column carrier_t.fax is 'FAX';
comment on column carrier_t.email is 'EMAIL';
comment on column carrier_t.remark is '备注';
comment on column carrier_t.active      is '状态（Y正常 N停用）';
comment on column carrier_t.company_id      is '公司ID';
comment on column carrier_t.warehouse_id    is '仓库ID';
comment on column carrier_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column carrier_t.create_by     is '创建者';
comment on column carrier_t.create_time   is '创建时间';
comment on column carrier_t.update_by     is '更新者';
comment on column carrier_t.update_time   is '更新时间';
comment on column carrier_t.update_version   is '更新时间';
comment on column carrier_t.description   is '数据描述';




-- ----------------------------
-- 上架策略头
-- ----------------------------

drop table putaway_strategy_t cascade constraints;
create table putaway_strategy_t (
putaway_strategy_id number,
putaway_strategy_code varchar2(50),
putaway_strategy_descr varchar2(200),
active		char(1)  default 'Y',
remark	varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table putaway_strategy_t add constraint pk_putaway_strategy_t primary key (putaway_strategy_id);

comment on table  putaway_strategy_t               is '上架策略头';
comment on column putaway_strategy_t.putaway_strategy_id is '上架策略ID';
comment on column putaway_strategy_t.putaway_strategy_code is '上架策略代码';
comment on column putaway_strategy_t.putaway_strategy_descr is '描述';
comment on column putaway_strategy_t.remark is '备注';
comment on column putaway_strategy_t.active      is '状态（Y正常 N停用）';
comment on column putaway_strategy_t.company_id      is '公司ID';
comment on column putaway_strategy_t.warehouse_id    is '仓库ID';
comment on column putaway_strategy_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column putaway_strategy_t.create_by     is '创建者';
comment on column putaway_strategy_t.create_time   is '创建时间';
comment on column putaway_strategy_t.update_by     is '更新者';
comment on column putaway_strategy_t.update_time   is '更新时间';
comment on column putaway_strategy_t.update_version   is '更新时间';
comment on column putaway_strategy_t.description   is '数据描述';


-- ----------------------------
-- 上架策略明细
-- ----------------------------

drop table putaway_strategy_detail_t cascade constraints;
create table putaway_strategy_detail_t (
putaway_strategy_detail_id number,
putaway_strategy_id number,
line_number number,
type varchar2(50),
from_location_code varchar2(50),
from_zone_code varchar2(50),
to_location_code varchar2(50),
to_zone_code varchar2(50),
sort_rule varchar2(50),
active		char(1)  default 'Y',
remark	varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table putaway_strategy_detail_t add constraint pk_putaway_strategy_detail_t primary key (putaway_strategy_detail_id);

comment on table  putaway_strategy_detail_t               is '上架策略明细';
comment on column putaway_strategy_detail_t.putaway_strategy_detail_id is '上架策略明细ID';
comment on column putaway_strategy_detail_t.putaway_strategy_id is '上架策略ID';
comment on column putaway_strategy_detail_t.line_number is '行号';
comment on column putaway_strategy_detail_t.type is '类型';
comment on column putaway_strategy_detail_t.from_location_code is '来源库位';
comment on column putaway_strategy_detail_t.from_zone_code is '来源库区';
comment on column putaway_strategy_detail_t.to_location_code is '来源库位';
comment on column putaway_strategy_detail_t.to_zone_code is '来源库区';
comment on column putaway_strategy_detail_t.sort_rule is '排序规则';
comment on column putaway_strategy_detail_t.remark is '备注';
comment on column putaway_strategy_detail_t.active      is '状态（Y正常 N停用）';
comment on column putaway_strategy_detail_t.company_id      is '公司ID';
comment on column putaway_strategy_detail_t.warehouse_id    is '仓库ID';
comment on column putaway_strategy_detail_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column putaway_strategy_detail_t.create_by     is '创建者';
comment on column putaway_strategy_detail_t.create_time   is '创建时间';
comment on column putaway_strategy_detail_t.update_by     is '更新者';
comment on column putaway_strategy_detail_t.update_time   is '更新时间';
comment on column putaway_strategy_detail_t.update_version   is '更新时间';
comment on column putaway_strategy_detail_t.description   is '数据描述';



-- ----------------------------
-- 上架库位锁定
-- ----------------------------

drop table putaway_location_lock_t cascade constraints;
create table putaway_location_lock_t (
putaway_location_lock_id number,
location_code varchar2(50),
lpn_number varchar2(50),
container_number varchar2(50),
sku_code varchar2(50),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table putaway_location_lock_t add constraint pk_putaway_location_lock_t primary key (putaway_location_lock_id);

comment on table  putaway_location_lock_t               is '上架库位锁定';
comment on column putaway_location_lock_t.putaway_location_lock_id is '上架库位锁定ID';
comment on column putaway_location_lock_t.lpn_number is 'LPN';
comment on column putaway_location_lock_t.container_number is '容器号';
comment on column putaway_location_lock_t.sku_code is 'SKU';
comment on column putaway_location_lock_t.company_id      is '公司ID';
comment on column putaway_location_lock_t.warehouse_id    is '仓库ID';
comment on column putaway_location_lock_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column putaway_location_lock_t.create_by     is '创建者';
comment on column putaway_location_lock_t.create_time   is '创建时间';
comment on column putaway_location_lock_t.update_by     is '更新者';
comment on column putaway_location_lock_t.update_time   is '更新时间';
comment on column putaway_location_lock_t.update_version   is '更新时间';
comment on column putaway_location_lock_t.description   is '数据描述';


-- ----------------------------
-- 分配策略
-- ----------------------------

drop table allocate_strategy_t cascade constraints;
create table allocate_strategy_t (
allocate_strategy_id number,
allocate_strategy_code varchar2(50),
allocate_strategy_descr varchar2(200),
allocate_strategy_type varchar2(50),
active		char(1)  default 'Y',
remark	varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table allocate_strategy_t add constraint pk_allocate_strategy_t primary key (allocate_strategy_id);

comment on table  allocate_strategy_t               is '分配策略头';
comment on column allocate_strategy_t.allocate_strategy_id is '分配策略ID';
comment on column allocate_strategy_t.allocate_strategy_code is '分配策略代码';
comment on column allocate_strategy_t.allocate_strategy_descr is '描述';
comment on column allocate_strategy_t.allocate_strategy_type is '分配策略类型';
comment on column allocate_strategy_t.remark is '备注';
comment on column allocate_strategy_t.active      is '状态（Y正常 N停用）';
comment on column allocate_strategy_t.company_id      is '公司ID';
comment on column allocate_strategy_t.warehouse_id    is '仓库ID';
comment on column allocate_strategy_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column allocate_strategy_t.create_by     is '创建者';
comment on column allocate_strategy_t.create_time   is '创建时间';
comment on column allocate_strategy_t.update_by     is '更新者';
comment on column allocate_strategy_t.update_time   is '更新时间';
comment on column allocate_strategy_t.update_version   is '更新时间';
comment on column allocate_strategy_t.description   is '数据描述';



-- ----------------------------
-- 分配策略明细
-- ----------------------------

drop table allocate_strategy_detail_t cascade constraints;
create table allocate_strategy_detail_t (
allocate_strategy_detail_id number,
allocate_strategy_id number,
line_number number,
type varchar2(50),
area_id number,
area_code varchar2(50),
uom varchar2(50),
sort_rule varchar2(50),
fifo_rule varchar2(50),
active		char(1)  default 'Y',
remark	varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table allocate_strategy_detail_t add constraint pk_allocate_strategy_detail_t primary key (allocate_strategy_detail_id);

comment on table  allocate_strategy_detail_t               is '分配策略明细';
comment on column allocate_strategy_detail_t.allocate_strategy_detail_id is '分配策略明细ID';
comment on column allocate_strategy_detail_t.allocate_strategy_id is '分配策略ID';
comment on column allocate_strategy_detail_t.line_number is '行号';
comment on column allocate_strategy_detail_t.type is '类型';
comment on column allocate_strategy_detail_t.area_id is '区域ID';
comment on column allocate_strategy_detail_t.area_code is '区域代码';
comment on column allocate_strategy_detail_t.uom is '单位 PCS 按件  LPN 按LPN';
comment on column allocate_strategy_detail_t.sort_rule is '排序规则';
comment on column allocate_strategy_detail_t.fifo_rule is '先进先出规则';
comment on column allocate_strategy_detail_t.remark is '备注';
comment on column allocate_strategy_detail_t.active      is '状态（Y正常 N停用）';
comment on column allocate_strategy_detail_t.company_id      is '公司ID';
comment on column allocate_strategy_detail_t.warehouse_id    is '仓库ID';
comment on column allocate_strategy_detail_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column allocate_strategy_detail_t.create_by     is '创建者';
comment on column allocate_strategy_detail_t.create_time   is '创建时间';
comment on column allocate_strategy_detail_t.update_by     is '更新者';
comment on column allocate_strategy_detail_t.update_time   is '更新时间';
comment on column allocate_strategy_detail_t.update_version   is '更新时间';
comment on column allocate_strategy_detail_t.description   is '数据描述';



-- ----------------------------
-- 批属性验证
-- ----------------------------
drop table lot_validate_t cascade constraints;
create table lot_validate_t (
lot_validate_id number,
lot_validate_code varchar2(50),
lot_validate_descr varchar2(200),
lot_attribute1_view char(1) default 'N',
lot_attribute2_view char(1) default 'N',
lot_attribute3_view char(1) default 'N',
lot_attribute4_view char(1) default 'N',
lot_attribute5_view char(1) default 'N',
lot_attribute6_view char(1) default 'N',
lot_attribute7_view char(1) default 'N',
lot_attribute8_view char(1) default 'N',
lot_attribute9_view char(1) default 'N',
lot_attribute10_view char(1) default 'N',
lot_attribute11_view char(1) default 'N',
lot_attribute12_view char(1) default 'N',
lot_attribute1_required char(1) default 'N',
lot_attribute2_required char(1) default 'N',
lot_attribute3_required char(1) default 'N',
lot_attribute4_required char(1) default 'N',
lot_attribute5_required char(1) default 'N',
lot_attribute6_required char(1) default 'N',
lot_attribute7_required char(1) default 'N',
lot_attribute8_required char(1) default 'N',
lot_attribute9_required char(1) default 'N',
lot_attribute10_required char(1) default 'N',
lot_attribute11_required char(1) default 'N',
lot_attribute12_required char(1) default 'N',
active char(1) default 'Y',
remark varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table lot_validate_t add constraint pk_lot_validate_t primary key (lot_validate_id);
comment on table  lot_validate_t               is '批属性验证';
comment on column lot_validate_t.lot_validate_id is '货品ID';
comment on column lot_validate_t.lot_validate_code is '货主代码';
comment on column lot_validate_t.lot_validate_descr is '必填';
comment on column lot_validate_t.lot_attribute1_view is '批属性1显示';
comment on column lot_validate_t.lot_attribute2_view is '批属性2显示';
comment on column lot_validate_t.lot_attribute3_view is '批属性3显示';
comment on column lot_validate_t.lot_attribute4_view is '批属性4显示';
comment on column lot_validate_t.lot_attribute5_view is '批属性5显示';
comment on column lot_validate_t.lot_attribute6_view is '批属性6显示';
comment on column lot_validate_t.lot_attribute7_view is '批属性7显示';
comment on column lot_validate_t.lot_attribute8_view is '批属性8显示';
comment on column lot_validate_t.lot_attribute9_view is '批属性9显示';
comment on column lot_validate_t.lot_attribute10_view is '批属性10显示';
comment on column lot_validate_t.lot_attribute11_view is '批属性11显示';
comment on column lot_validate_t.lot_attribute12_view is '批属性12显示';
comment on column lot_validate_t.lot_attribute1_required is '批属性1必填';
comment on column lot_validate_t.lot_attribute2_required is '批属性2必填';
comment on column lot_validate_t.lot_attribute3_required is '批属性3必填';
comment on column lot_validate_t.lot_attribute4_required is '批属性4必填';
comment on column lot_validate_t.lot_attribute5_required is '批属性5必填';
comment on column lot_validate_t.lot_attribute6_required is '批属性6必填';
comment on column lot_validate_t.lot_attribute7_required is '批属性7必填';
comment on column lot_validate_t.lot_attribute8_required is '批属性8必填';
comment on column lot_validate_t.lot_attribute9_required is '批属性9必填';
comment on column lot_validate_t.lot_attribute10_required is '批属性10必填';
comment on column lot_validate_t.lot_attribute11_required is '批属性11必填';
comment on column lot_validate_t.lot_attribute12_required is '批属性12必填';
comment on column lot_validate_t.active is '是否有效';
comment on column lot_validate_t.remark is '备注';
comment on column lot_validate_t.company_id is '公司ID';
comment on column lot_validate_t.warehouse_id is '仓库ID';
comment on column lot_validate_t.del_flag is '删除标记';
comment on column lot_validate_t.create_by is '创建人';
comment on column lot_validate_t.create_time is '创建时间';
comment on column lot_validate_t.update_by is '更新人';
comment on column lot_validate_t.update_time is '更新时间';
comment on column lot_validate_t.update_version is '更新版本';
comment on column lot_validate_t.description is '数据必填';



-- ----------------------------
-- 包装
-- ----------------------------

drop table pack_t cascade constraints;
create table pack_t (
pack_id number,
pack_code varchar2(50),
pack_descr varchar2(200),
uom varchar2(50),
qty number(20,5) default 0,
uom_inner varchar2(50),
qty_inner number(20,5) default 0,
uom_case varchar2(50),
qty_case number(20,5) default 0,
active		char(1)  default 'Y',
remark	varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table pack_t add constraint pk_pack_t primary key (pack_id);

comment on table  pack_t               is '包装';
comment on column pack_t.pack_id is '包装ID';
comment on column pack_t.pack_code is '包装代码';
comment on column pack_t.pack_descr is '描述';
comment on column pack_t.uom is '最小单位';
comment on column pack_t.qty is '最小数量';
comment on column pack_t.uom_inner is '内包装单位';
comment on column pack_t.qty_inner is '内包装数量';
comment on column pack_t.uom_case is '想包装单位';
comment on column pack_t.qty_case is '箱包装数量';
comment on column pack_t.remark is '备注';
comment on column pack_t.active      is '状态（Y正常 N停用）';
comment on column pack_t.company_id      is '公司ID';
comment on column pack_t.warehouse_id    is '仓库ID';
comment on column pack_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column pack_t.create_by     is '创建者';
comment on column pack_t.create_time   is '创建时间';
comment on column pack_t.update_by     is '更新者';
comment on column pack_t.update_time   is '更新时间';
comment on column pack_t.update_version   is '更新时间';
comment on column pack_t.description   is '数据描述';




-- ----------------------------
-- 库区
-- ----------------------------

drop table zone_t cascade constraints;
create table zone_t (
zone_id number,
zone_code varchar2(50),
type varchar2(50),
pick_to_location varchar2(50),
active             varchar2(100)   default 'Y',
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table zone_t add constraint pk_zone_t primary key (zone_id);
comment on table zone_t is '库区';
comment on column zone_t.zone_id is '库区ID';
comment on column zone_t.zone_code is '库区代码';
comment on column zone_t.type is '库区类型';
comment on column zone_t.pick_to_location is '拣货到库位';
comment on column zone_t.active      is '状态（Y正常 N停用）';
comment on column zone_t.company_id      is '公司ID';
comment on column zone_t.warehouse_id    is '仓库ID';
comment on column zone_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column zone_t.create_by     is '创建者';
comment on column zone_t.create_time   is '创建时间';
comment on column zone_t.update_by     is '更新者';
comment on column zone_t.update_time   is '更新时间';
comment on column zone_t.update_version   is '更新时间';
comment on column zone_t.description   is '数据描述';



-- ----------------------------
-- 库位
-- ----------------------------

drop table location_t cascade constraints;
create table location_t (
location_id number,
location_code varchar2(50),
location_type varchar2(50),
location_logical varchar2(50),
zone_id number,
zone_code varchar2(50),
x number,
y number,
z number,
sku_mix char(1),
lot_mix char(1),
active             varchar2(100)   default 'Y',
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table location_t add constraint pk_location_t primary key (location_id);
comment on table location_t is '库位';
comment on column location_t.location_id is '库位ID';
comment on column location_t.location_code is '库位代码';
comment on column location_t.location_type is '库位类型';
comment on column location_t.location_logical is '路线顺序';
comment on column location_t.zone_id is '库区ID';
comment on column location_t.zone_code is '库区代码';
comment on column location_t.x is 'x';
comment on column location_t.y is 'y';
comment on column location_t.z is 'z';
comment on column location_t.sku_mix is '混放货品';
comment on column location_t.lot_mix is '混放批次';
comment on column location_t.active is '是否有效';
comment on column location_t.active      is '状态（Y正常 N停用）';
comment on column location_t.company_id      is '公司ID';
comment on column location_t.warehouse_id    is '仓库ID';
comment on column location_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column location_t.create_by     is '创建者';
comment on column location_t.create_time   is '创建时间';
comment on column location_t.update_by     is '更新者';
comment on column location_t.update_time   is '更新时间';
comment on column location_t.update_version   is '更新时间';
comment on column location_t.description   is '数据描述';




-- ----------------------------
-- 区域
-- ----------------------------

drop table area_t cascade constraints;
create table area_t (
area_id number,
area_code varchar2(50),
area_descr varchar2(200),
type varchar2(50),
active		char(1)  default 'Y',
remark	varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table area_t add constraint pk_area_t primary key (area_id);

comment on table  area_t               is '区域';
comment on column area_t.area_id is '区域ID';
comment on column area_t.area_code is '区域代码';
comment on column area_t.area_descr is '描述';
comment on column area_t.type is '区域类型';
comment on column area_t.remark is '备注';
comment on column area_t.active      is '状态（Y正常 N停用）';
comment on column area_t.company_id      is '公司ID';
comment on column area_t.warehouse_id    is '仓库ID';
comment on column area_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column area_t.create_by     is '创建者';
comment on column area_t.create_time   is '创建时间';
comment on column area_t.update_by     is '更新者';
comment on column area_t.update_time   is '更新时间';
comment on column area_t.update_version   is '更新时间';
comment on column area_t.description   is '数据描述';





-- ----------------------------
-- 区域明细
-- ----------------------------

drop table area_detail_t cascade constraints;
create table area_detail_t (
area_detail_id number,
area_id number,
location_id number,
location_code varchar2(200),
active    char(1)  default 'Y',
remark  varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table area_detail_t add constraint pk_area_detail_t primary key (area_detail_id);

comment on table  area_detail_t               is '区域明细';
comment on column area_detail_t.area_detail_id is '区域明细ID';
comment on column area_detail_t.area_id is '区域ID';
comment on column area_detail_t.location_id is '库位ID';
comment on column area_detail_t.location_code is '库位代码';
comment on column area_detail_t.remark is '备注';
comment on column area_detail_t.active      is '状态（Y正常 N停用）';
comment on column area_detail_t.company_id      is '公司ID';
comment on column area_detail_t.warehouse_id    is '仓库ID';
comment on column area_detail_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column area_detail_t.create_by     is '创建者';
comment on column area_detail_t.create_time   is '创建时间';
comment on column area_detail_t.update_by     is '更新者';
comment on column area_detail_t.update_time   is '更新时间';
comment on column area_detail_t.update_version   is '更新时间';
comment on column area_detail_t.description   is '数据描述';




-- ----------------------------
-- 货品
-- ----------------------------

drop table sku_t cascade constraints;
create table sku_t (
sku_id number,
owner_id number,
owner_code varchar2(50),
sku_code varchar2(50),
sku_alias varchar2(50),
barcode  varchar2(50),
sku_descr varchar2(200),
sku_type varchar2(200),
pack_id number,
pack_code varchar2(50),
uom varchar2(50),
volume number(20,5) default 0,
length number(20,5) default 0,
width number(20,5) default 0,
height number(20,5) default 0,
weight_gross number(20,5) default 0,
weight_net number(20,5) default 0,
weight_tare number(20,5) default 0,
lot_validate_id number,
lot_validate_code varchar2(50),
putaway_strategy_id number,
putaway_strategy_code varchar2(50),
putaway_zone_code varchar2(50),
putaway_location_code varchar2(50),
allocate_strategy_id number,
allocate_strategy_code varchar2(50),
lot_attribute1_label varchar2(50),
lot_attribute2_label varchar2(50),
lot_attribute3_label varchar2(50),
lot_attribute4_label varchar2(50),
lot_attribute5_label varchar2(50),
lot_attribute6_label varchar2(50),
lot_attribute7_label varchar2(50),
lot_attribute8_label varchar2(50),
lot_attribute9_label varchar2(50),
lot_attribute10_label varchar2(50),
lot_attribute11_label varchar2(50),
lot_attribute12_label varchar2(50),
active		char(1)  default 'Y',
remark	varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table sku_t add constraint pk_sku_t primary key (sku_id);

comment on table  sku_t               is '货品';
comment on column sku_t.sku_id is '货品ID';
comment on column sku_t.owner_id is '货主ID';
comment on column sku_t.owner_code is '货主代码';
comment on column sku_t.sku_code is '货品代码';
comment on column sku_t.sku_alias is '货品别称';
comment on column sku_t.barcode is '条码';
comment on column sku_t.sku_descr is '描述';
comment on column sku_t.sku_type is '货品类型';
comment on column sku_t.pack_id is '包装ID';
comment on column sku_t.pack_code is '包装代码';
comment on column sku_t.uom is '单位';
comment on column sku_t.volume is '体积';
comment on column sku_t.length is '货品长';
comment on column sku_t.width is '货品宽';
comment on column sku_t.height is '货品高';
comment on column sku_t.weight_gross is '毛重';
comment on column sku_t.weight_net is '净重';
comment on column sku_t.weight_tare is '皮重';
comment on column sku_t.lot_validate_id is '批属性验证ID';
comment on column sku_t.lot_validate_code is '批属性验证代码';
comment on column sku_t.putaway_strategy_id is '上架策略ID';
comment on column sku_t.putaway_strategy_code is '上架策略代码';
comment on column sku_t.putaway_location_code is '上架库区';
comment on column sku_t.putaway_location_code is '上架库位';
comment on column sku_t.allocate_strategy_id is '分配策略ID';
comment on column sku_t.allocate_strategy_code is '分配策略代码';
comment on column sku_t.lot_attribute1_label is '批属性1描述';
comment on column sku_t.lot_attribute2_label is '批属性2描述';
comment on column sku_t.lot_attribute3_label is '批属性3描述';
comment on column sku_t.lot_attribute4_label is '批属性4描述';
comment on column sku_t.lot_attribute5_label is '批属性5描述';
comment on column sku_t.lot_attribute6_label is '批属性6描述';
comment on column sku_t.lot_attribute7_label is '批属性7描述';
comment on column sku_t.lot_attribute8_label is '批属性8描述';
comment on column sku_t.lot_attribute9_label is '批属性9描述';
comment on column sku_t.lot_attribute10_label is '批属性10描述';
comment on column sku_t.lot_attribute11_label is '批属性11描述';
comment on column sku_t.lot_attribute12_label is '批属性12描述';
comment on column sku_t.active      is '状态（Y正常 N停用）';
comment on column sku_t.remark      is '状态（Y正常 N停用）';
comment on column sku_t.company_id      is '公司ID';
comment on column sku_t.warehouse_id    is '仓库ID';
comment on column sku_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column sku_t.create_by     is '创建者';
comment on column sku_t.create_time   is '创建时间';
comment on column sku_t.update_by     is '更新者';
comment on column sku_t.update_time   is '更新时间';
comment on column sku_t.update_version   is '更新时间';
comment on column sku_t.description   is '数据描述';


-- ----------------------------
-- 入库单头
-- ----------------------------

drop table inbound_header_t cascade constraints;
create table inbound_header_t (
inbound_header_id number,
inbound_number varchar2(50),
source_number varchar2(100),
reference_number varchar2(100),
type varchar2(50),
owner_id number,
owner_code varchar2(50),
supplier_id number,
supplier_code varchar2(50),
inbound_date date,
expected_inbound_date date,
closed_date date,
status varchar2(5),
carrier_id number,
carrier_code varchar2(50),
carrier_car_number varchar2(50),
carrier_driver varchar2(50),
ship_address1 varchar2(200),
ship_address2 varchar2(200),
remark  varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table inbound_header_t add constraint pk_inbound_header_t primary key (inbound_header_id);

comment on table  inbound_header_t               is '入库单头';
comment on column inbound_header_t.inbound_header_id is '入库单头ID';
comment on column inbound_header_t.inbound_number is '入库单号';
comment on column inbound_header_t.source_number is '来源单号';
comment on column inbound_header_t.reference_number is '参考单号';
comment on column inbound_header_t.type is '单据类型';
comment on column inbound_header_t.owner_id is '货主ID';
comment on column inbound_header_t.owner_code is '货主代码';
comment on column inbound_header_t.supplier_id is '供应商ID';
comment on column inbound_header_t.supplier_code is '供应商代码';
comment on column inbound_header_t.inbound_date is '入库日期';
comment on column inbound_header_t.expected_inbound_date is '预期入库日期';
comment on column inbound_header_t.closed_date is '关闭日期';
comment on column inbound_header_t.status is '状态 10 新建 20 收货中 30 收货完成 40 已关闭 50 已取消';
comment on column inbound_header_t.carrier_id is '承运人ID';
comment on column inbound_header_t.carrier_code is '承运人代码';
comment on column inbound_header_t.carrier_car_number is '车牌号';
comment on column inbound_header_t.carrier_driver is '司机';
comment on column inbound_header_t.ship_address1 is '地址1';
comment on column inbound_header_t.ship_address2 is '地址2';
comment on column inbound_header_t.remark      is '备注';
comment on column inbound_header_t.company_id      is '公司ID';
comment on column inbound_header_t.warehouse_id    is '仓库ID';
comment on column inbound_header_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column inbound_header_t.create_by     is '创建者';
comment on column inbound_header_t.create_time   is '创建时间';
comment on column inbound_header_t.update_by     is '更新者';
comment on column inbound_header_t.update_time   is '更新时间';
comment on column inbound_header_t.update_version   is '更新时间';
comment on column inbound_header_t.description   is '数据描述';




-- ----------------------------
-- 入库单明细
-- ----------------------------

drop table inbound_detail_t cascade constraints;
create table inbound_detail_t (
inbound_detail_id number,
inbound_header_id number,
line_number number,
parent_line_number number,
source_line_number number,
po_number varchar2(50),
po_line_number varchar2(50),
owner_id number,
owner_code varchar2(50),
sku_id number,
sku_code varchar2(50),
sku_alias varchar2(50),
putaway_strategy_id number,
putaway_strategy_code varchar2(50),
uom varchar2(50),
uom_quantity number(20,5) default 0,
pack_id number,
pack_code varchar2(50),
quantity_expected number(20,5) default 0,
quantity_receive number(20,5) default 0,
quantity_cancel number(20,5) default 0,
inbound_date date,
cancel_date date,
location_code varchar2(50),
container_number varchar2(50),
lpn_number varchar2(50),
inventory_onhand_id number,
lot_number varchar2(50),
status varchar2(5),
lot_attribute1 varchar2(50),
lot_attribute2 varchar2(50),
lot_attribute3 varchar2(50),
lot_attribute4 date,
lot_attribute5 date,
lot_attribute6 varchar2(50),
lot_attribute7 varchar2(50),
lot_attribute8 varchar2(50),
lot_attribute9 varchar2(50),
lot_attribute10 varchar2(50),
lot_attribute11 date,
lot_attribute12 date,
remark  varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table inbound_detail_t add constraint pk_inbound_detail_t primary key (inbound_detail_id);

comment on table  inbound_detail_t               is '入库单明细';
comment on column inbound_detail_t.inbound_detail_id is '入库单明细ID';
comment on column inbound_detail_t.inbound_header_id is '入库单头ID';
comment on column inbound_detail_t.line_number is '行号';
comment on column inbound_detail_t.parent_line_number is '父行号';
comment on column inbound_detail_t.source_line_number is '来源行号';
comment on column inbound_detail_t.po_number is 'PO单号';
comment on column inbound_detail_t.po_line_number is 'PO行号';
comment on column inbound_detail_t.owner_id is '货主ID';
comment on column inbound_detail_t.owner_code is '货主代码';
comment on column inbound_detail_t.sku_id is '货品ID';
comment on column inbound_detail_t.sku_code is '货品代码';
comment on column inbound_detail_t.sku_alias is '货品别称';
comment on column inbound_detail_t.putaway_strategy_id is '上架策略ID';
comment on column inbound_detail_t.putaway_strategy_code is '上架策略代码';
comment on column inbound_detail_t.uom is '单位';
comment on column inbound_detail_t.uom_quantity is '单位数量';
comment on column inbound_detail_t.pack_id is '包装ID';
comment on column inbound_detail_t.pack_code is '包装代码';
comment on column inbound_detail_t.quantity_expected is '预期数量';
comment on column inbound_detail_t.quantity_receive is '接收数量';
comment on column inbound_detail_t.quantity_cancel is '调整数量';
comment on column inbound_detail_t.inbound_date is '入库日期';
comment on column inbound_detail_t.cancel_date is '取消日期';
comment on column inbound_detail_t.location_code is '库位';
comment on column inbound_detail_t.container_number is '容器号';
comment on column inbound_detail_t.lpn_number is 'LPN';
comment on column inbound_detail_t.inventory_onhand_id is '库存ID';
comment on column inbound_detail_t.lot_number is '批次号';
comment on column inbound_detail_t.status is '状态 状态 10 新建 20 收货中 30 收货完成 40 已关闭 50 已取消';
comment on column inbound_detail_t.lot_attribute1 is '批属性1';
comment on column inbound_detail_t.lot_attribute2 is '批属性2';
comment on column inbound_detail_t.lot_attribute3 is '批属性3';
comment on column inbound_detail_t.lot_attribute4 is '批属性4';
comment on column inbound_detail_t.lot_attribute5 is '批属性5';
comment on column inbound_detail_t.lot_attribute6 is '批属性6';
comment on column inbound_detail_t.lot_attribute7 is '批属性7';
comment on column inbound_detail_t.lot_attribute8 is '批属性8';
comment on column inbound_detail_t.lot_attribute9 is '批属性9';
comment on column inbound_detail_t.lot_attribute10 is '批属性10';
comment on column inbound_detail_t.lot_attribute11 is '批属性11';
comment on column inbound_detail_t.lot_attribute12 is '批属性12';
comment on column inbound_detail_t.remark      is '备注';
comment on column inbound_detail_t.company_id      is '公司ID';
comment on column inbound_detail_t.warehouse_id    is '仓库ID';
comment on column inbound_detail_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column inbound_detail_t.create_by     is '创建者';
comment on column inbound_detail_t.create_time   is '创建时间';
comment on column inbound_detail_t.update_by     is '更新者';
comment on column inbound_detail_t.update_time   is '更新时间';
comment on column inbound_detail_t.update_version   is '更新时间';
comment on column inbound_detail_t.description   is '数据描述';







-- ----------------------------
-- 入库单来源明细
-- ----------------------------

drop table inbound_source_t cascade constraints;
create table inbound_source_t (
inbound_source_id number,
inbound_header_id number,
line_number number,
container_code varchar2(50),
lpn_code varchar2(50),
container_type varchar2(50),
container_size varchar2(50),
seal1 varchar2(50),
seal2 varchar2(50),
status varchar2(5),
remark	varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table inbound_source_t add constraint pk_inbound_source_t primary key (inbound_source_id);

comment on table  inbound_source_t               is '入库单来源明细';
comment on column inbound_source_t.inbound_source_id is '入库单来源ID';
comment on column inbound_source_t.inbound_header_id is '入库单头ID';
comment on column inbound_source_t.line_number is '行号';
comment on column inbound_source_t.container_code is '容器号';
comment on column inbound_source_t.lpn_code is 'lpn';
comment on column inbound_source_t.container_type is '容器类型';
comment on column inbound_source_t.container_size is '容器尺寸';
comment on column inbound_source_t.seal1 is '封条号';
comment on column inbound_source_t.seal2 is '封条号';
comment on column inbound_source_t.status is '状态';
comment on column inbound_source_t.remark      is '备注';
comment on column inbound_source_t.company_id      is '公司ID';
comment on column inbound_source_t.warehouse_id    is '仓库ID';
comment on column inbound_source_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column inbound_source_t.create_by     is '创建者';
comment on column inbound_source_t.create_time   is '创建时间';
comment on column inbound_source_t.update_by     is '更新者';
comment on column inbound_source_t.update_time   is '更新时间';
comment on column inbound_source_t.update_version   is '更新时间';
comment on column inbound_source_t.description   is '数据描述';




-- ----------------------------
-- 撤销收货头
-- ----------------------------

drop table inbound_cancel_header_t cascade constraints;
create table inbound_cancel_header_t (
inbound_cancel_id number,
inbound_cancel_number varchar2(50),
inbound_header_id varchar2(100),
status varchar2(5),
remark  varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table inbound_cancel_header_t add constraint pk_inbound_cancel_header_t primary key (inbound_cancel_id);

comment on table  inbound_cancel_header_t               is '撤销收货头';
comment on column inbound_cancel_header_t.inbound_cancel_id is '入库取消头ID';
comment on column inbound_cancel_header_t.inbound_cancel_number is '入库取消单号';
comment on column inbound_cancel_header_t.inbound_header_id is '入库单ID';
comment on column inbound_cancel_header_t.status is '状态';
comment on column inbound_cancel_header_t.remark      is '备注';
comment on column inbound_cancel_header_t.company_id      is '公司ID';
comment on column inbound_cancel_header_t.warehouse_id    is '仓库ID';
comment on column inbound_cancel_header_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column inbound_cancel_header_t.create_by     is '创建者';
comment on column inbound_cancel_header_t.create_time   is '创建时间';
comment on column inbound_cancel_header_t.update_by     is '更新者';
comment on column inbound_cancel_header_t.update_time   is '更新时间';
comment on column inbound_cancel_header_t.update_version   is '更新时间';
comment on column inbound_cancel_header_t.description   is '数据描述';



-- ----------------------------
-- 撤销收货明细
-- ----------------------------

drop table inbound_cancel_detail_t cascade constraints;
create table inbound_cancel_detail_t (
inbound_cancel_detail_id number,
inbound_cancel_id number,
inbound_detail_id number,
line_number number,
quantity_cancel number(20,5) default 0,
inventory_onhand_id number,
status varchar2(5),
reason varchar2(50),
remark	varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table inbound_cancel_detail_t add constraint pk_inbound_cancel_detail_t primary key (inbound_cancel_id);

comment on table  inbound_cancel_detail_t               is '撤销收货明细';
comment on column inbound_cancel_detail_t.inbound_cancel_detail_id is '入库取消明细ID';
comment on column inbound_cancel_detail_t.inbound_cancel_id is '入库取消头ID';
comment on column inbound_cancel_detail_t.inbound_detail_id is '入库单明细ID';
comment on column inbound_cancel_detail_t.line_number is '行号';
comment on column inbound_cancel_detail_t.quantity_cancel is '取消数量';
comment on column inbound_cancel_detail_t.inventory_onhand_id is '库存行ID';
comment on column inbound_cancel_detail_t.status is '状态';
comment on column inbound_cancel_detail_t.reason is '原因';
comment on column inbound_cancel_detail_t.remark      is '备注';
comment on column inbound_cancel_detail_t.company_id      is '公司ID';
comment on column inbound_cancel_detail_t.warehouse_id    is '仓库ID';
comment on column inbound_cancel_detail_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column inbound_cancel_detail_t.create_by     is '创建者';
comment on column inbound_cancel_detail_t.create_time   is '创建时间';
comment on column inbound_cancel_detail_t.update_by     is '更新者';
comment on column inbound_cancel_detail_t.update_time   is '更新时间';
comment on column inbound_cancel_detail_t.update_version   is '更新时间';
comment on column inbound_cancel_detail_t.description   is '数据描述';



-- ----------------------------
-- 任务明细
-- ----------------------------

drop table task_detail_t cascade constraints;
create table task_detail_t (
task_detail_id number,
task_detail_number varchar2(50),
task_type varchar2(5),
source_type varchar2(10),
owner_id number,
owner_code varchar2(50),
sku_id number,
sku_code varchar2(50),
uom varchar2(50),
pack_id number,
pack_code varchar2(50),
lot_number varchar2(50),
from_lpn_number varchar2(50),
from_lpn_type varchar2(50),
from_location_code varchar2(50),
from_zone_code varchar2(50),
to_lpn_number varchar2(50),
to_location_code varchar2(50),
to_zone_code varchar2(50),
from_location_logical varchar2(50),
to_location_logical varchar2(50),
user_name varchar2(50),
release_time date,
complete_time date,
start_time date,
end_time date,
quantity number(20,5) default 0,
reason varchar2(50),
source_number number,
source_line_number number,
source_bill_number varchar2(50),
status varchar2(5),
remark	varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table task_detail_t add constraint pk_task_detail_t primary key (task_detail_id);

comment on table  task_detail_t               is '任务明细';
comment on column task_detail_t.task_detail_id is '任务明细ID';
comment on column task_detail_t.task_detail_number is '任务号';
comment on column task_detail_t.task_type is '任务类型';
comment on column task_detail_t.source_type is '来源类型';
comment on column task_detail_t.owner_id is '货主ID';
comment on column task_detail_t.owner_code is '货主代码';
comment on column task_detail_t.sku_id is '货品ID';
comment on column task_detail_t.sku_code is '货品代码';
comment on column task_detail_t.uom is '单位';
comment on column task_detail_t.pack_id is '包装ID';
comment on column task_detail_t.pack_code is '包装代码';
comment on column task_detail_t.lot_number is '批次号';
comment on column task_detail_t.from_lpn_number is '来源LPN';
comment on column task_detail_t.from_lpn_type is '来源LPN类型';
comment on column task_detail_t.from_location_code is '来源库位';
comment on column task_detail_t.from_zone_code is '来源库区';
comment on column task_detail_t.to_lpn_number is '目标LPN';
comment on column task_detail_t.to_location_code is '目标库位';
comment on column task_detail_t.to_zone_code is '目标库区';
comment on column task_detail_t.from_location_logical is '来源库位顺序';
comment on column task_detail_t.to_location_logical is '目标库位顺序';
comment on column task_detail_t.user_name is '操作用户';
comment on column task_detail_t.release_time is '下发时间';
comment on column task_detail_t.complete_time is '完成时间';
comment on column task_detail_t.start_time is '开始时间';
comment on column task_detail_t.end_time is '接收时间';
comment on column task_detail_t.quantity is '操作数量';
comment on column task_detail_t.reason is '操作原因';
comment on column task_detail_t.source_number is '来源主键';
comment on column task_detail_t.source_line_number is '来源行号';
comment on column task_detail_t.source_bill_number is '来源单号';
comment on column task_detail_t.status is '状态';
comment on column task_detail_t.remark      is '备注';
comment on column task_detail_t.company_id      is '公司ID';
comment on column task_detail_t.warehouse_id    is '仓库ID';
comment on column task_detail_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column task_detail_t.create_by     is '创建者';
comment on column task_detail_t.create_time   is '创建时间';
comment on column task_detail_t.update_by     is '更新者';
comment on column task_detail_t.update_time   is '更新时间';
comment on column task_detail_t.update_version   is '更新时间';
comment on column task_detail_t.description   is '数据描述';






-- ----------------------------
-- 批属性
-- ----------------------------

drop table lot_attribute_t cascade constraints;
create table lot_attribute_t (
lot_attribute_id number,
lot_number varchar2(50),
owner_id number,
owner_code varchar2(50),
sku_id number,
sku_code varchar2(50),
sku_alias varchar2(50),
lot_attribute1 varchar2(50),
lot_attribute2 varchar2(50),
lot_attribute3 varchar2(50),
lot_attribute4 date,
lot_attribute5 date,
lot_attribute6 varchar2(50),
lot_attribute7 varchar2(50),
lot_attribute8 varchar2(50),
lot_attribute9 varchar2(50),
lot_attribute10 varchar2(50),
lot_attribute11 date,
lot_attribute12 date,
md5 varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table lot_attribute_t add constraint pk_lot_attribute_t primary key (lot_attribute_id);

comment on table  lot_attribute_t               is '批属性';
comment on column lot_attribute_t.lot_attribute_id is '批属性ID';
comment on column lot_attribute_t.lot_number is '批次号';
comment on column lot_attribute_t.owner_id is '货主ID';
comment on column lot_attribute_t.owner_code is '货主代码';
comment on column lot_attribute_t.sku_id is '货品ID';
comment on column lot_attribute_t.sku_code is '货品代码';
comment on column lot_attribute_t.sku_alias is '货品别称';
comment on column lot_attribute_t.lot_attribute1 is '批属性1';
comment on column lot_attribute_t.lot_attribute2 is '批属性2';
comment on column lot_attribute_t.lot_attribute3 is '批属性3';
comment on column lot_attribute_t.lot_attribute4 is '批属性4';
comment on column lot_attribute_t.lot_attribute5 is '批属性5';
comment on column lot_attribute_t.lot_attribute6 is '批属性6';
comment on column lot_attribute_t.lot_attribute7 is '批属性7';
comment on column lot_attribute_t.lot_attribute8 is '批属性8';
comment on column lot_attribute_t.lot_attribute9 is '批属性9';
comment on column lot_attribute_t.lot_attribute10 is '批属性10';
comment on column lot_attribute_t.lot_attribute11 is '批属性11';
comment on column lot_attribute_t.lot_attribute12 is '批属性12';
comment on column lot_attribute_t.md5 is 'MD5密文';
comment on column lot_attribute_t.company_id      is '公司ID';
comment on column lot_attribute_t.warehouse_id    is '仓库ID';
comment on column lot_attribute_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column lot_attribute_t.create_by     is '创建者';
comment on column lot_attribute_t.create_time   is '创建时间';
comment on column lot_attribute_t.update_by     is '更新者';
comment on column lot_attribute_t.update_time   is '更新时间';
comment on column lot_attribute_t.update_version   is '更新时间';
comment on column lot_attribute_t.description   is '数据描述';




-- ----------------------------
-- LPN
-- ----------------------------

drop table lpn_t cascade constraints;
create table lpn_t (
lpn_id number,
lpn_number varchar2(50),
lpn_type varchar2(50),
lpn_size varchar2(50),
volume number(20,5),
length number(20,5),
width number(20,5),
height number(20,5),
weight_gross number(20,5),
weight_net number(20,5),
weight_tare number(20,5),
parent_lpn_id number,
parent_lpn_code varchar2(50),
container_number varchar2(50),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table lpn_t add constraint pk_lpn_t primary key (lpn_id);

comment on table  lpn_t               is 'LPN';
comment on column lpn_t.lpn_id is 'LPN ID';
comment on column lpn_t.lpn_number is '批次号';
comment on column lpn_t.lpn_type is '类型';
comment on column lpn_t.lpn_size is '尺寸';
comment on column lpn_t.volume is '体积';
comment on column lpn_t.length is '货品长';
comment on column lpn_t.width is '货品宽';
comment on column lpn_t.height is '货品高';
comment on column lpn_t.weight_gross is '毛重';
comment on column lpn_t.weight_net is '净重';
comment on column lpn_t.weight_tare is '皮重';
comment on column lpn_t.parent_lpn_id is '父项LPN ID';
comment on column lpn_t.parent_lpn_code is '父项LPN CODE';
comment on column lpn_t.container_number is '容器号';
comment on column lpn_t.company_id      is '公司ID';
comment on column lpn_t.warehouse_id    is '仓库ID';
comment on column lpn_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column lpn_t.create_by     is '创建者';
comment on column lpn_t.create_time   is '创建时间';
comment on column lpn_t.update_by     is '更新者';
comment on column lpn_t.update_time   is '更新时间';
comment on column lpn_t.update_version   is '更新时间';
comment on column lpn_t.description   is '数据描述';




-- ----------------------------
-- 库存
-- ----------------------------

drop table inventory_onhand_t cascade constraints;
create table inventory_onhand_t (
inventory_onhand_id number,
owner_id number,
owner_code varchar2(50),
sku_id number,
sku_code varchar2(50),
sku_alias varchar2(50),
quantity_onhand number(20,5) default 0,
quantity_allocated number(20,5) default 0,
quantity_locked number(20,5) default 0,
location_id number,
location_code varchar2(50),
lpn_id number,
lpn_number varchar2(50),
lot_id number,
lot_number varchar2(50),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table inventory_onhand_t add constraint pk_inventory_onhand_t primary key (inventory_onhand_id);

comment on table  inventory_onhand_t               is '库存';
comment on column inventory_onhand_t.inventory_onhand_id is '入库单明细ID';
comment on column inventory_onhand_t.owner_id is '货主ID';
comment on column inventory_onhand_t.owner_code is '货主代码';
comment on column inventory_onhand_t.sku_id is '货品ID';
comment on column inventory_onhand_t.sku_code is '货品代码';
comment on column inventory_onhand_t.sku_alias is '货品别称';
comment on column inventory_onhand_t.quantity_onhand is '现有量';
comment on column inventory_onhand_t.quantity_allocated is '分配量';
comment on column inventory_onhand_t.quantity_locked is '锁定量';
comment on column inventory_onhand_t.location_id is '库位ID';
comment on column inventory_onhand_t.location_code is '库位';
comment on column inventory_onhand_t.lpn_id is 'LPN ID';
comment on column inventory_onhand_t.lpn_number is 'LPN';
comment on column inventory_onhand_t.lot_id is '批次号 ID';
comment on column inventory_onhand_t.lot_number is '批次号';
comment on column inventory_onhand_t.company_id      is '公司ID';
comment on column inventory_onhand_t.warehouse_id    is '仓库ID';
comment on column inventory_onhand_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column inventory_onhand_t.create_by     is '创建者';
comment on column inventory_onhand_t.create_time   is '创建时间';
comment on column inventory_onhand_t.update_by     is '更新者';
comment on column inventory_onhand_t.update_time   is '更新时间';
comment on column inventory_onhand_t.update_version   is '更新时间';
comment on column inventory_onhand_t.description   is '数据描述';


-- ----------------------------
-- 出库单头
-- ----------------------------

drop table outbound_header_t cascade constraints;
create table outbound_header_t (
outbound_header_id number,
outbound_number varchar2(50),
source_number varchar2(100),
reference_number varchar2(100),
type varchar2(50),
owner_id number,
owner_code varchar2(50),
customer_id number,
customer_code varchar2(50),
customer_descr varchar2(200),
contact1 varchar2(50),
contact2 varchar2(50),
phone1 varchar2(50),
phone2 varchar2(50),
address1 varchar2(200),
address2 varchar2(200),
fax varchar2(50),
email varchar2(100),
ship_label varchar2(100),
car_number varchar2(100),
driver varchar2(100),
container_number varchar2(100),
outbound_date date,
expected_outbound_date date,
status varchar2(5),
process_status varchar2(5),
remark varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table outbound_header_t add constraint pk_outbound_header_t primary key (outbound_header_id);

comment on table  outbound_header_t               is '出库单头';
comment on column outbound_header_t.outbound_header_id is '出库单头ID';
comment on column outbound_header_t.outbound_number is '出库单号';
comment on column outbound_header_t.source_number is '来源单号';
comment on column outbound_header_t.reference_number is '参考单号';
comment on column outbound_header_t.type is '单据类型';
comment on column outbound_header_t.owner_id is '货主ID';
comment on column outbound_header_t.owner_code is '货主代码';
comment on column outbound_header_t.customer_id is '客户ID';
comment on column outbound_header_t.customer_code is '客户代码';
comment on column outbound_header_t.customer_descr is '客户描述';
comment on column outbound_header_t.contact1 is '客户联系人1';
comment on column outbound_header_t.contact2 is '客户联系人2';
comment on column outbound_header_t.phone1 is '客户联系电话1';
comment on column outbound_header_t.phone2 is '客户联系电话2';
comment on column outbound_header_t.address1 is '客户地址1';
comment on column outbound_header_t.address2 is '客户地址2';
comment on column outbound_header_t.fax is '客户FAX';
comment on column outbound_header_t.email is '客户EMAIL';
comment on column outbound_header_t.ship_label is '发货标签';
comment on column outbound_header_t.car_number is '车牌号';
comment on column outbound_header_t.driver is '司机';
comment on column outbound_header_t.container_number is '集装箱号';
comment on column outbound_header_t.outbound_date is '出库日期';
comment on column outbound_header_t.expected_outbound_date is '预期出库日期';
comment on column outbound_header_t.status is '状态';
comment on column outbound_header_t.process_status is '处理状态';
comment on column outbound_header_t.remark is '备注';
comment on column outbound_header_t.company_id      is '公司ID';
comment on column outbound_header_t.warehouse_id    is '仓库ID';
comment on column outbound_header_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column outbound_header_t.create_by     is '创建者';
comment on column outbound_header_t.create_time   is '创建时间';
comment on column outbound_header_t.update_by     is '更新者';
comment on column outbound_header_t.update_time   is '更新时间';
comment on column outbound_header_t.update_version   is '更新时间';
comment on column outbound_header_t.description   is '数据描述';


-- ----------------------------
-- 出库单明细
-- ----------------------------

drop table outbound_detail_t cascade constraints;
create table outbound_detail_t (
outbound_detail_id number,
outbound_header_id number,
line_number number,
source_line_number number,
owner_id number,
owner_code varchar2(50),
sku_id number,
sku_code varchar2(50),
sku_alias varchar2(50),
allocate_strategy_id number,
allocate_strategy_code varchar2(50),
uom varchar2(50),
uom_quantity number(20,5) default 0,
pack_id number,
pack_code varchar2(50),
quantity_order number(20,5) default 0,
quantity_expected number(20,5) default 0,
quantity_allocated number(20,5) default 0,
quantity_picked number(20,5) default 0,
quantity_shiped number(20,5) default 0,
status varchar2(5),
lot_attribute1 varchar2(50),
lot_attribute2 varchar2(50),
lot_attribute3 varchar2(50),
lot_attribute4 date,
lot_attribute5 date,
lot_attribute6 varchar2(50),
lot_attribute7 varchar2(50),
lot_attribute8 varchar2(50),
lot_attribute9 varchar2(50),
lot_attribute10 varchar2(50),
lot_attribute11 date,
lot_attribute12 date,
remark varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table outbound_detail_t add constraint pk_outbound_detail_t primary key (outbound_detail_id);

comment on table  outbound_detail_t               is '出库单明细';
comment on column outbound_detail_t.outbound_detail_id is '出库单明细ID';
comment on column outbound_detail_t.outbound_header_id is '出库单头ID';
comment on column outbound_detail_t.line_number is '行号';
comment on column outbound_detail_t.source_line_number is '来源行号';
comment on column outbound_detail_t.owner_id is '货主ID';
comment on column outbound_detail_t.owner_code is '货主代码';
comment on column outbound_detail_t.sku_id is '货品ID';
comment on column outbound_detail_t.sku_code is '货品代码';
comment on column outbound_detail_t.sku_alias is '货品别称';
comment on column outbound_detail_t.allocate_strategy_id is '分配策略ID';
comment on column outbound_detail_t.allocate_strategy_code is '分配策略代码';
comment on column outbound_detail_t.uom is '单位';
comment on column outbound_detail_t.uom_quantity is '单位数量';
comment on column outbound_detail_t.pack_id is '包装ID';
comment on column outbound_detail_t.pack_code is '包装代码';
comment on column outbound_detail_t.quantity_order is '订单数量';
comment on column outbound_detail_t.quantity_expected is '预期数量';
comment on column outbound_detail_t.quantity_allocated is '分配数量';
comment on column outbound_detail_t.quantity_picked is '拣选数量';
comment on column outbound_detail_t.quantity_shiped is '发货数量';
comment on column outbound_detail_t.status is '状态';
comment on column outbound_detail_t.lot_attribute1 is '批属性1';
comment on column outbound_detail_t.lot_attribute2 is '批属性2';
comment on column outbound_detail_t.lot_attribute3 is '批属性3';
comment on column outbound_detail_t.lot_attribute4 is '批属性4';
comment on column outbound_detail_t.lot_attribute5 is '批属性5';
comment on column outbound_detail_t.lot_attribute6 is '批属性6';
comment on column outbound_detail_t.lot_attribute7 is '批属性7';
comment on column outbound_detail_t.lot_attribute8 is '批属性8';
comment on column outbound_detail_t.lot_attribute9 is '批属性9';
comment on column outbound_detail_t.lot_attribute10 is '批属性10';
comment on column outbound_detail_t.lot_attribute11 is '批属性11';
comment on column outbound_detail_t.lot_attribute12 is '批属性12';
comment on column outbound_detail_t.remark is '备注';
comment on column outbound_detail_t.company_id      is '公司ID';
comment on column outbound_detail_t.warehouse_id    is '仓库ID';
comment on column outbound_detail_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column outbound_detail_t.create_by     is '创建者';
comment on column outbound_detail_t.create_time   is '创建时间';
comment on column outbound_detail_t.update_by     is '更新者';
comment on column outbound_detail_t.update_time   is '更新时间';
comment on column outbound_detail_t.update_version   is '更新时间';
comment on column outbound_detail_t.description   is '数据描述';




-- ----------------------------
-- 分配明细
-- ----------------------------

drop table allocate_t cascade constraints;
create table allocate_t (
allocate_id number,
allocate_batch_id number,
source_number number,
source_line_number number,
source_bill_number varchar2(50),
source_wave_number varchar2(50),
allocate_type varchar2(50),
allocate_strategy_type varchar2(50),
allocate_strategy_id number,
allocate_strategy_code varchar2(50),
inventory_onhand_id number,
owner_id number,
owner_code varchar2(50),
sku_id number,
sku_code varchar2(50),
sku_alias varchar2(50),
quantity_allocated number(20,5) default 0,
location_id number,
location_code varchar2(50),
zone_code varchar2(50),
lpn_id number,
lpn_number varchar2(50),
lot_id number,
lot_number varchar2(50),
load_lpn_number varchar2(50),
status varchar2(5),
full_lpn_flag char(1) default 'N',
full_location_flag char(1) default 'N',
to_location_code varchar2(50),
remark varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table allocate_t add constraint pk_allocate_t primary key (allocate_id);
comment on table  allocate_t               is '分配明细';
comment on column allocate_t.allocate_id is '分配明细ID';
comment on column allocate_t.allocate_batch_id is '分配批次ID';
comment on column allocate_t.source_number is '来源主键';
comment on column allocate_t.source_line_number is '来源行号';
comment on column allocate_t.source_bill_number is '来源单据号';
comment on column allocate_t.source_wave_number is '来源波次号';
comment on column allocate_t.allocate_type is '分配类型';
comment on column allocate_t.allocate_strategy_type is '分配策略类型';
comment on column allocate_t.allocate_strategy_id is '分配策略ID';
comment on column allocate_t.allocate_strategy_code is '分配策略代码';
comment on column allocate_t.inventory_onhand_id is '库存ID';
comment on column allocate_t.owner_id is '货主ID';
comment on column allocate_t.owner_code is '货主代码';
comment on column allocate_t.sku_id is '货品ID';
comment on column allocate_t.sku_code is '货品代码';
comment on column allocate_t.sku_alias is '货品别称';
comment on column allocate_t.quantity_allocated is '分配量';
comment on column allocate_t.location_id is '库位ID';
comment on column allocate_t.location_code is '库位';
comment on column allocate_t.zone_code is '库位';
comment on column allocate_t.lpn_id is 'LPN ID';
comment on column allocate_t.lpn_number is 'LPN';
comment on column allocate_t.lot_id is '批次号 ID';
comment on column allocate_t.lot_number is '批次号';
comment on column allocate_t.load_lpn_number is '装载LPN号';
comment on column allocate_t.status is '状态';
comment on column allocate_t.full_lpn_flag is '整LPN标识';
comment on column allocate_t.full_location_flag is '整库位标识';
comment on column allocate_t.to_location_code is '目标库位';
comment on column allocate_t.remark is '备注';
comment on column allocate_t.company_id      is '公司ID';
comment on column allocate_t.warehouse_id    is '仓库ID';
comment on column allocate_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column allocate_t.create_by     is '创建者';
comment on column allocate_t.create_time   is '创建时间';
comment on column allocate_t.update_by     is '更新者';
comment on column allocate_t.update_time   is '更新时间';
comment on column allocate_t.update_version   is '更新时间';
comment on column allocate_t.description   is '数据描述';



-- ----------------------------
-- 调整单头
-- ----------------------------

drop table inventory_adjustment_header_t cascade constraints;
create table inventory_adjustment_header_t (
inventory_adjustment_id number,
inventory_adjustment_number varchar2(50),
source_number varchar2(100),
reference_number varchar2(100),
status varchar2(5),
remark varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table inventory_adjustment_header_t add constraint pk_inventory_adj_header_t primary key (inventory_adjustment_id);
comment on table  inventory_adjustment_header_t               is '调整单头';
comment on column inventory_adjustment_header_t.inventory_adjustment_id is '调整ID';
comment on column inventory_adjustment_header_t.inventory_adjustment_number is '调整单号';
comment on column inventory_adjustment_header_t.source_number is '来源单号';
comment on column inventory_adjustment_header_t.reference_number is '参考单号';
comment on column inventory_adjustment_header_t.status is '状态';
comment on column inventory_adjustment_header_t.remark is '备注';
comment on column inventory_adjustment_header_t.company_id      is '公司ID';
comment on column inventory_adjustment_header_t.warehouse_id    is '仓库ID';
comment on column inventory_adjustment_header_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column inventory_adjustment_header_t.create_by     is '创建者';
comment on column inventory_adjustment_header_t.create_time   is '创建时间';
comment on column inventory_adjustment_header_t.update_by     is '更新者';
comment on column inventory_adjustment_header_t.update_time   is '更新时间';
comment on column inventory_adjustment_header_t.update_version   is '更新时间';
comment on column inventory_adjustment_header_t.description   is '数据描述';




-- ----------------------------
-- 调整单明细
-- ----------------------------

drop table inventory_adjustment_detail_t cascade constraints;
create table inventory_adjustment_detail_t (
inventory_adjustment_detail_id number,
inventory_adjustment_id number,
line_number number,
source_line_number varchar2(50),
source_number varchar2(50),
inventory_onhand_id number,
owner_id number,
owner_code varchar2(50),
sku_id number,
sku_code varchar2(50),
sku_alias varchar2(50),
uom varchar2(50),
uom_quantity number(20,5) default 0,
pack_id number,
pack_code varchar2(50),
quantity_onhand number(20,5) default 0,
quantity_adjustment number(20,5) default 0,
location_id number,
location_code varchar2(50),
lpn_id number,
lpn_number varchar2(50),
lot_id number,
lot_number varchar2(50),
reason varchar2(50),
remark varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table inventory_adjustment_detail_t add constraint pk_inventory_adj_detail_t primary key (inventory_adjustment_detail_id);
comment on table  inventory_adjustment_detail_t               is '调整单头';
comment on column inventory_adjustment_detail_t.inventory_adjustment_detail_id is '调整单明细ID';
comment on column inventory_adjustment_detail_t.inventory_adjustment_id is '调整单ID';
comment on column inventory_adjustment_detail_t.line_number is '行号';
comment on column inventory_adjustment_detail_t.source_line_number is '来源行号';
comment on column inventory_adjustment_detail_t.source_number is '来源主键';
comment on column inventory_adjustment_detail_t.inventory_onhand_id is '库存行ID';
comment on column inventory_adjustment_detail_t.owner_id is '货主ID';
comment on column inventory_adjustment_detail_t.owner_code is '货主代码';
comment on column inventory_adjustment_detail_t.sku_id is '货品ID';
comment on column inventory_adjustment_detail_t.sku_code is '货品代码';
comment on column inventory_adjustment_detail_t.sku_alias is '货品别称';
comment on column inventory_adjustment_detail_t.uom is '单位';
comment on column inventory_adjustment_detail_t.uom_quantity is '单位数量';
comment on column inventory_adjustment_detail_t.pack_id is '包装ID';
comment on column inventory_adjustment_detail_t.pack_code is '包装代码';
comment on column inventory_adjustment_detail_t.quantity_onhand is '库存现有量';
comment on column inventory_adjustment_detail_t.quantity_adjustment is '调整数量';
comment on column inventory_adjustment_detail_t.location_id is '库位ID';
comment on column inventory_adjustment_detail_t.location_code is '库位';
comment on column inventory_adjustment_detail_t.lpn_id is 'LPN ID';
comment on column inventory_adjustment_detail_t.lpn_number is 'LPN';
comment on column inventory_adjustment_detail_t.lot_id is '批次号 ID';
comment on column inventory_adjustment_detail_t.lot_number is '批次号';
comment on column inventory_adjustment_detail_t.reason is '原因';
comment on column inventory_adjustment_detail_t.remark is '备注';
comment on column inventory_adjustment_detail_t.company_id      is '公司ID';
comment on column inventory_adjustment_detail_t.warehouse_id    is '仓库ID';
comment on column inventory_adjustment_detail_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column inventory_adjustment_detail_t.create_by     is '创建者';
comment on column inventory_adjustment_detail_t.create_time   is '创建时间';
comment on column inventory_adjustment_detail_t.update_by     is '更新者';
comment on column inventory_adjustment_detail_t.update_time   is '更新时间';
comment on column inventory_adjustment_detail_t.update_version   is '更新时间';
comment on column inventory_adjustment_detail_t.description   is '数据描述';






-- ----------------------------
-- 交易
-- ----------------------------

drop table inventory_transaction_t cascade constraints;
create table inventory_transaction_t (
transaction_id number,
transaction_type varchar2(50),
transaction_category varchar2(50),
transaction_date date,
inventory_onhand_id number,
owner_code varchar2(50),
sku_code varchar2(50),
sku_alias varchar2(50),
pack_code varchar2(50),
uom varchar2(50),
uom_quantity number(20,5) default 0,
quantity number(20,5) default 0,
location_code varchar2(50),
lpn_number varchar2(50),
lot_number varchar2(50),
source_number varchar2(50),
source_line_number varchar2(50),
source_bill_number varchar2(50),
from_inventory_onhand_id number,
from_owner_code varchar2(50),
from_sku_code varchar2(50),
from_sku_alias varchar2(50),
from_location_code varchar2(50),
from_lpn_number varchar2(50),
from_lot_number varchar2(50),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table inventory_transaction_t add constraint pk_inventory_transaction_t primary key (transaction_id);
comment on table  inventory_transaction_t               is '调整单头';
comment on column inventory_transaction_t.transaction_id is '交易ID';
comment on column inventory_transaction_t.transaction_type is '交易类型';
comment on column inventory_transaction_t.transaction_category is '交易类别';
comment on column inventory_transaction_t.transaction_date is '交易时间';
comment on column inventory_transaction_t.inventory_onhand_id is '库存行ID';
comment on column inventory_transaction_t.owner_code is '货主代码';
comment on column inventory_transaction_t.sku_code is '货品代码';
comment on column inventory_transaction_t.sku_alias is '货品别称';
comment on column inventory_transaction_t.pack_code is '包装';
comment on column inventory_transaction_t.uom is '单位';
comment on column inventory_transaction_t.uom_quantity is '单位数量';
comment on column inventory_transaction_t.quantity is '交易数量';
comment on column inventory_transaction_t.location_code is '库位';
comment on column inventory_transaction_t.lpn_number is 'LPN';
comment on column inventory_transaction_t.lot_number is '批次号';
comment on column inventory_transaction_t.source_number is '来源主键';
comment on column inventory_transaction_t.source_line_number is '来源行号';
comment on column inventory_transaction_t.source_bill_number is '来源单号';
comment on column inventory_transaction_t.from_inventory_onhand_id is '来源库存行ID';
comment on column inventory_transaction_t.from_owner_code is '来源货主代码';
comment on column inventory_transaction_t.from_sku_code is '来源货品代码';
comment on column inventory_transaction_t.from_sku_alias is '来源货品别称';
comment on column inventory_transaction_t.from_location_code is '来源来源库位';
comment on column inventory_transaction_t.from_lpn_number is '来源PN';
comment on column inventory_transaction_t.from_lot_number is '来源批次号';
comment on column inventory_transaction_t.company_id      is '公司ID';
comment on column inventory_transaction_t.warehouse_id    is '仓库ID';
comment on column inventory_transaction_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column inventory_transaction_t.create_by     is '创建者';
comment on column inventory_transaction_t.create_time   is '创建时间';
comment on column inventory_transaction_t.update_by     is '更新者';
comment on column inventory_transaction_t.update_time   is '更新时间';
comment on column inventory_transaction_t.update_version   is '更新时间';
comment on column inventory_transaction_t.description   is '数据描述';




-- ----------------------------
-- 冻结表
-- ----------------------------

drop table inventory_locked_t cascade constraints;
create table inventory_locked_t (
inventory_locked_id number,
inventory_onhand_id number,
quantity_locked number(20,5) default 0,
reason varchar2(50),
remark varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table inventory_locked_t add constraint pk_inventory_locked_t primary key (inventory_locked_id);
comment on table  inventory_locked_t               is '冻结表';
comment on column inventory_locked_t.inbound_locked_id is '调整单明细ID';
comment on column inventory_locked_t.inventory_onhand_id is '库存行ID';
comment on column inventory_locked_t.quantity_locked is '冻结数量';
comment on column inventory_locked_t.reason is '原因';
comment on column inventory_locked_t.remark is '备注';
comment on column inventory_locked_t.company_id      is '公司ID';
comment on column inventory_locked_t.warehouse_id    is '仓库ID';
comment on column inventory_locked_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column inventory_locked_t.create_by     is '创建者';
comment on column inventory_locked_t.create_time   is '创建时间';
comment on column inventory_locked_t.update_by     is '更新者';
comment on column inventory_locked_t.update_time   is '更新时间';
comment on column inventory_locked_t.update_version   is '更新时间';
comment on column inventory_locked_t.description   is '数据描述';



-- ----------------------------
-- 盘点请求
-- ----------------------------

drop table inventory_count_request_t cascade constraints;
create table inventory_count_request_t (
inventory_count_request_id number,
request_number  varchar2(50),
request_descr varchar2(500),
request_type varchar2(50),
quantity_show_flag char(1) default 'N',
from_zone_code varchar2(50),
to_zone_code varchar2(50),
from_location_code varchar2(50),
to_location_code varchar2(50),
from_sku_code varchar2(50),
to_sku_code varchar2(50),
sku_code_in varchar2(50),
from_lpn_number varchar2(50),
to_lpn_number varchar2(50),
lpn_number_in varchar2(50),
request_date date,
remark varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table inventory_count_request_t add constraint pk_inventory_count_request_t primary key (inventory_count_request_id);
comment on table inventory_count_request_t is '盘点请求';
comment on column inventory_count_request_t.inventory_count_request_id is '盘点请求ID';
comment on column inventory_count_request_t.request_number is '请求单号';
comment on column inventory_count_request_t.request_descr is '描述';
comment on column inventory_count_request_t.request_type is '请求类型';
comment on column inventory_count_request_t.quantity_show_flag is '明盘标识 Y 是 N 否';
comment on column inventory_count_request_t.from_zone_code is '来源库区';
comment on column inventory_count_request_t.to_zone_code is '目标库区';
comment on column inventory_count_request_t.from_location_code is '来源库位';
comment on column inventory_count_request_t.to_location_code is '目标库位';
comment on column inventory_count_request_t.from_sku_code is '来源货品';
comment on column inventory_count_request_t.to_sku_code is '目标货品';
comment on column inventory_count_request_t.sku_code_in is '货品包含';
comment on column inventory_count_request_t.from_lpn_number is '来源LPN';
comment on column inventory_count_request_t.to_lpn_number is '目标LPN';
comment on column inventory_count_request_t.lpn_number_in is 'LPN包含';
comment on column inventory_count_request_t.request_date is '请求日期';
comment on column inventory_count_request_t.remark is '备注';
comment on column inventory_count_request_t.company_id      is '公司ID';
comment on column inventory_count_request_t.warehouse_id    is '仓库ID';
comment on column inventory_count_request_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column inventory_count_request_t.create_by     is '创建者';
comment on column inventory_count_request_t.create_time   is '创建时间';
comment on column inventory_count_request_t.update_by     is '更新者';
comment on column inventory_count_request_t.update_time   is '更新时间';
comment on column inventory_count_request_t.update_version   is '更新时间';
comment on column inventory_count_request_t.description   is '数据描述';



-- ----------------------------
-- 盘点单头
-- ----------------------------

drop table inventory_count_header_t cascade constraints;
create table inventory_count_header_t (
inventory_count_header_id number,
count_number varchar2(100),
inventory_count_request_id number,
type varchar2(50),
replay_flag char(1) default 'N',
parent_count_header_id number,
parent_count_number varchar2(100),
status varchar2(5),
remark varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table inventory_count_header_t add constraint pk_inventory_count_header_t primary key (inventory_count_header_id);
comment on table inventory_count_header_t is '盘点单头';
comment on column inventory_count_header_t.inventory_count_header_id is '盘点头ID';
comment on column inventory_count_header_t.count_number is '盘点单号';
comment on column inventory_count_header_t.inventory_count_request_id is '请求ID';
comment on column inventory_count_header_t.type is '盘点类型';
comment on column inventory_count_header_t.replay_flag is '复盘标识';
comment on column inventory_count_header_t.parent_count_header_id is '上级盘点头ID';
comment on column inventory_count_header_t.parent_count_number is '上级盘点单号';
comment on column inventory_count_header_t.status is '状态';
comment on column inventory_count_header_t.remark is '备注';
comment on column inventory_count_header_t.company_id      is '公司ID';
comment on column inventory_count_header_t.warehouse_id    is '仓库ID';
comment on column inventory_count_header_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column inventory_count_header_t.create_by     is '创建者';
comment on column inventory_count_header_t.create_time   is '创建时间';
comment on column inventory_count_header_t.update_by     is '更新者';
comment on column inventory_count_header_t.update_time   is '更新时间';
comment on column inventory_count_header_t.update_version   is '更新时间';
comment on column inventory_count_header_t.description   is '数据描述';




-- ----------------------------
-- 盘点明细
-- ----------------------------

drop table inventory_count_detail_t cascade constraints;
create table inventory_count_detail_t (
inventory_count_detail_id number,
inventory_count_header_id number,
line_number number,
source_line_number number,
inventory_onhand_id number,
owner_id number,
owner_code varchar2(50),
sku_id number,
sku_code varchar2(50),
sku_alias varchar2(50),
location_id number,
location_code varchar2(50),
lpn_id number,
lpn_number varchar2(50),
lot_id number,
lot_number varchar2(50),
quantity_system number(20,5) default 0,
quantity_count number(20,5) default 0,
quantity_replay number(20,5) default 0,
quantity_difference number(20,5) default 0,
quantity_confirm number(20,5) default 0,
status varchar2(5),
reason varchar2(500),
remark varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table inventory_count_detail_t add constraint pk_inventory_count_detail_t primary key (inventory_count_detail_id);
comment on column inventory_count_detail_t.inventory_count_detail_id is '盘点明细';
comment on column inventory_count_detail_t.inventory_count_header_id is '盘点头ID';
comment on column inventory_count_detail_t.line_number is '行号';
comment on column inventory_count_detail_t.source_line_number is '来源行号';
comment on column inventory_count_detail_t.inventory_onhand_id is 'number';
comment on column inventory_count_detail_t.owner_id is '货主ID';
comment on column inventory_count_detail_t.owner_code is '货主代码';
comment on column inventory_count_detail_t.sku_id is '货品ID';
comment on column inventory_count_detail_t.sku_code is '货品代码';
comment on column inventory_count_detail_t.sku_alias is '货品别称';
comment on column inventory_count_detail_t.location_id is '库位ID';
comment on column inventory_count_detail_t.location_code is '库位';
comment on column inventory_count_detail_t.lpn_id is 'LPN ID';
comment on column inventory_count_detail_t.lpn_number is 'LPN';
comment on column inventory_count_detail_t.lot_id is '批次号 ID';
comment on column inventory_count_detail_t.lot_number is '批次号';
comment on column inventory_count_detail_t.quantity_system is '系统数量';
comment on column inventory_count_detail_t.quantity_count is '盘点数量';
comment on column inventory_count_detail_t.quantity_replay is '复盘数量';
comment on column inventory_count_detail_t.quantity_difference is '差异数量';
comment on column inventory_count_detail_t.quantity_confirm is '确认数量';
comment on column inventory_count_detail_t.status is '状态';
comment on column inventory_count_detail_t.reason is '原因';
comment on column inventory_count_detail_t.remark is '备注';
comment on column inventory_count_detail_t.company_id      is '公司ID';
comment on column inventory_count_detail_t.warehouse_id    is '仓库ID';
comment on column inventory_count_detail_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column inventory_count_detail_t.create_by     is '创建者';
comment on column inventory_count_detail_t.create_time   is '创建时间';
comment on column inventory_count_detail_t.update_by     is '更新者';
comment on column inventory_count_detail_t.update_time   is '更新时间';
comment on column inventory_count_detail_t.update_version   is '更新时间';
comment on column inventory_count_detail_t.description   is '数据描述';




-- ----------------------------
-- 波次模板
-- ----------------------------

drop table wave_build_t cascade constraints;
create table wave_build_t (
wave_build_id number,
build_code varchar2(50),
build_descr varchar2(200),
wave_type varchar2(50),
remark varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table wave_build_t add constraint pk_wave_build_t primary key (wave_build_id);
comment on table wave_build_t is '波次模板';
comment on column wave_build_t.wave_build_id is '波次模板ID';
comment on column wave_build_t.build_code is '代码';
comment on column wave_build_t.build_descr is '描述';
comment on column wave_build_t.wave_type is '波次类型';
comment on column wave_build_t.remark is '备注';
comment on column wave_build_t.company_id      is '公司ID';
comment on column wave_build_t.warehouse_id    is '仓库ID';
comment on column wave_build_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column wave_build_t.create_by     is '创建者';
comment on column wave_build_t.create_time   is '创建时间';
comment on column wave_build_t.update_by     is '更新者';
comment on column wave_build_t.update_time   is '更新时间';
comment on column wave_build_t.update_version   is '更新时间';
comment on column wave_build_t.description   is '数据描述';



-- ----------------------------
-- 波次模板明细
-- ----------------------------

drop table wave_build_detail_t cascade constraints;
create table wave_build_detail_t (
wave_build_detail_id number,
wave_build_id number,
table_name varchar2(50),
property_code varchar2(50),
property_exp varchar2(50),
property_value1 varchar2(50),
property_value2 varchar2(50),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table wave_build_detail_t add constraint pk_wave_build_detail_t primary key (wave_build_detail_id);
comment on table wave_build_detail_t is '波次模板明细';
comment on column wave_build_detail_t.wave_build_detail_id is '波次构建明细ID';
comment on column wave_build_detail_t.wave_build_id is '波次构建ID';
comment on column wave_build_detail_t.table_name is '表名';
comment on column wave_build_detail_t.property_code is '属性代码';
comment on column wave_build_detail_t.property_exp is '属性表达式';
comment on column wave_build_detail_t.property_value1 is '属性值1';
comment on column wave_build_detail_t.property_value2 is '属性值2';
comment on column wave_build_detail_t.company_id      is '公司ID';
comment on column wave_build_detail_t.warehouse_id    is '仓库ID';
comment on column wave_build_detail_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column wave_build_detail_t.create_by     is '创建者';
comment on column wave_build_detail_t.create_time   is '创建时间';
comment on column wave_build_detail_t.update_by     is '更新者';
comment on column wave_build_detail_t.update_time   is '更新时间';
comment on column wave_build_detail_t.update_version   is '更新时间';
comment on column wave_build_detail_t.description   is '数据描述';


-- ----------------------------
-- 波次
-- ----------------------------

drop table wave_t cascade constraints;
create table wave_t (
wave_id number,
wave_number varchar2(50),
wave_type varchar2(50),
remark varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table wave_t add constraint pk_wave_t primary key (wave_id);
comment on table wave_t is '波次';
comment on column wave_t.wave_id is '波次ID';
comment on column wave_t.wave_number is '波次号';
comment on column wave_t.wave_type is '波次类型';
comment on column wave_t.remark is '备注';
comment on column wave_t.company_id      is '公司ID';
comment on column wave_t.warehouse_id    is '仓库ID';
comment on column wave_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column wave_t.create_by     is '创建者';
comment on column wave_t.create_time   is '创建时间';
comment on column wave_t.update_by     is '更新者';
comment on column wave_t.update_time   is '更新时间';
comment on column wave_t.update_version   is '更新时间';
comment on column wave_t.description   is '数据描述';




-- ----------------------------
-- 波次明细
-- ----------------------------

drop table wave_detail_t cascade constraints;
create table wave_detail_t (
wave_detail_id number,
wave_id number,
outbound_header_id number,
process_number number,
remark varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table wave_detail_t add constraint pk_wave_detail_t primary key (wave_detail_id);
comment on table wave_detail_t is '波次明细';
comment on column wave_detail_t.wave_detail_id is '波次明细ID';
comment on column wave_detail_t.wave_id is '波次ID';
comment on column wave_detail_t.outbound_header_id is '出库单ID';
comment on column wave_detail_t.process_number is '处理标识';
comment on column wave_detail_t.remark is '备注';
comment on column wave_detail_t.company_id      is '公司ID';
comment on column wave_detail_t.warehouse_id    is '仓库ID';
comment on column wave_detail_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column wave_detail_t.create_by     is '创建者';
comment on column wave_detail_t.create_time   is '创建时间';
comment on column wave_detail_t.update_by     is '更新者';
comment on column wave_detail_t.update_time   is '更新时间';
comment on column wave_detail_t.update_version   is '更新时间';
comment on column wave_detail_t.description   is '数据描述';



-- ----------------------------
-- 状态历史
-- ----------------------------
drop table status_history_t cascade constraints;
create table status_history_t (
history_id number,
source_number number,
source_bill_number varchar2(50),
old_status  varchar2(50),
new_status  varchar2(50),
oper_time  date,
remark varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table status_history_t add constraint pk_status_history_t primary key (history_id);
comment on table status_history_t is '状态历史';
comment on column status_history_t.history_id is '状态历史ID';
comment on column status_history_t.source_number is '来源主键';
comment on column status_history_t.source_bill_number is '来源单号';
comment on column status_history_t.old_status is '上一次状态';
comment on column status_history_t.new_status is '新状态';
comment on column status_history_t.oper_time is '操作时间';
comment on column status_history_t.remark is '备注';
comment on column status_history_t.company_id      is '公司ID';
comment on column status_history_t.warehouse_id    is '仓库ID';
comment on column status_history_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column status_history_t.create_by     is '创建者';
comment on column status_history_t.create_time   is '创建时间';
comment on column status_history_t.update_by     is '更新者';
comment on column status_history_t.update_time   is '更新时间';
comment on column status_history_t.update_version   is '更新时间';
comment on column status_history_t.description   is '数据描述';



-- ----------------------------
-- 仓库激活
-- ----------------------------
drop table warehouse_active_t cascade constraints;
create table warehouse_active_t (
active_id number,
active char(1) default 'N',
init char(1) default 'N',
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table warehouse_active_t add constraint pk_warehouse_active_t primary key (active_id);
comment on table warehouse_active_t is '仓库激活';
comment on column warehouse_active_t.active_id is '激活ID';
comment on column warehouse_active_t.active is '启用 Y是 N否';
comment on column warehouse_active_t.init is '初始化 Y是 N否';
comment on column warehouse_active_t.company_id      is '公司ID';
comment on column warehouse_active_t.warehouse_id    is '仓库ID';
comment on column warehouse_active_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column warehouse_active_t.create_by     is '创建者';
comment on column warehouse_active_t.create_time   is '创建时间';
comment on column warehouse_active_t.update_by     is '更新者';
comment on column warehouse_active_t.update_time   is '更新时间';
comment on column warehouse_active_t.update_version   is '更新时间';
comment on column warehouse_active_t.description   is '数据描述';


-- ----------------------------
-- 缺量记录
-- ----------------------------
drop table allocate_short_t cascade constraints;
create table allocate_short_t (
allocate_short_id number,
source_number number,
source_line_number number,
source_bill_number varchar2(50),
reason varchar2(50),
file_id varchar2(50),
quantity_original number(20,5),
quantity_actual number(20,5),
owner_id number,
owner_code varchar2(50),
sku_id number,
sku_code varchar2(50),
sku_alias varchar2(50),
location_id number,
location_code varchar2(50),
lpn_id number,
lpn_number varchar2(50),
lot_id number,
lot_number varchar2(50),
remark varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table allocate_short_t add constraint pk_allocate_short_t primary key (allocate_short_id);
comment on table allocate_short_t is '缺量记录';
comment on column allocate_short_t.allocate_short_id is '缺量记录ID';
comment on column allocate_short_t.source_number is '来源主键';
comment on column allocate_short_t.source_line_number is '来源行号';
comment on column allocate_short_t.source_bill_number is '来源单号';
comment on column allocate_short_t.reason is '原因';
comment on column allocate_short_t.file_id is '文件ID';
comment on column allocate_short_t.quantity_original is '原始数量';
comment on column allocate_short_t.quantity_actual is '实际数量';
comment on column allocate_short_t.owner_id is '货主ID';
comment on column allocate_short_t.owner_code is '货主代码';
comment on column allocate_short_t.sku_id is '货品ID';
comment on column allocate_short_t.sku_code is '货品代码';
comment on column allocate_short_t.sku_alias is '货品别称';
comment on column allocate_short_t.location_id is '库位ID';
comment on column allocate_short_t.location_code is '库位';
comment on column allocate_short_t.lpn_id is 'LPN ID';
comment on column allocate_short_t.lpn_number is 'LPN';
comment on column allocate_short_t.lot_id is '批次号 ID';
comment on column allocate_short_t.lot_number is '批次号';
comment on column allocate_short_t.remark is '备注';
comment on column allocate_short_t.company_id      is '公司ID';
comment on column allocate_short_t.warehouse_id    is '仓库ID';
comment on column allocate_short_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column allocate_short_t.create_by     is '创建者';
comment on column allocate_short_t.create_time   is '创建时间';
comment on column allocate_short_t.update_by     is '更新者';
comment on column allocate_short_t.update_time   is '更新时间';
comment on column allocate_short_t.update_version   is '更新时间';
comment on column allocate_short_t.description   is '数据描述';


-- ----------------------------
-- 月台
-- ----------------------------
drop table platform_t cascade constraints;
create table platform_t (
platform_id number,
platform_code varchar2(50),
platform_type varchar2(50),
car_number varchar2(50),
car_driver varchar2(50),
car_driver_phone varchar2(50),
container_number varchar2(50),
status varchar2(50) default '10',
remark varchar2(500),
active char(1),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table platform_t add constraint pk_platform_t primary key (platform_id);
comment on table platform_t is '月台';
comment on column platform_t.platform_id is '泊位ID';
comment on column platform_t.platform_code is '泊位代码';
comment on column platform_t.platform_type is '泊位类型';
comment on column platform_t.car_number is '车牌号';
comment on column platform_t.car_driver is '司机';
comment on column platform_t.car_driver_phone is '司机电话';
comment on column platform_t.container_number is '柜号';
comment on column platform_t.status is '状态';
comment on column platform_t.remark is '备注';
comment on column platform_t.active is '启用 Y是 N否';
comment on column platform_t.company_id      is '公司ID';
comment on column platform_t.warehouse_id    is '仓库ID';
comment on column platform_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column platform_t.create_by     is '创建者';
comment on column platform_t.create_time   is '创建时间';
comment on column platform_t.update_by     is '更新者';
comment on column platform_t.update_time   is '更新时间';
comment on column platform_t.update_version   is '更新时间';
comment on column platform_t.description   is '数据描述';



-- ----------------------------
-- 预约
-- ----------------------------
drop table appointment_t cascade constraints;
create table appointment_t (
appointment_id number,
appointment_code varchar2(50),
type varchar2(50),
platform_id number,
platform_code varchar2(50),
source_bill_number varchar2(50),
car_number varchar2(50),
car_driver varchar2(50),
car_driver_phone varchar2(50),
container_number varchar2(50),
expected_date date,
arrival_date date,
status varchar2(50) default '20',
remark varchar2(500),
  company_id         number        default '0',
  warehouse_id       number        default '0',
  del_flag                char(1)         default 'N',
  create_by               varchar2(64)    default '',
  create_time             date            default sysdate,
  update_by               varchar2(64)    default '',
  update_time             date            default sysdate,
  update_version          number          default '1',
  description             varchar2(500)   default null
);

alter table appointment_t add constraint pk_appointment_t primary key (appointment_id);
comment on table appointment_t is '预约';
comment on column appointment_t.appointment_id is '预约ID';
comment on column appointment_t.appointment_code is '预约代码';
comment on column appointment_t.type is '类型 IN 入库 OUT 出库';
comment on column appointment_t.platform_id is '泊位ID';
comment on column appointment_t.platform_code is '泊位代码';
comment on column appointment_t.source_bill_number is '单据号';
comment on column appointment_t.car_number is '车牌号';
comment on column appointment_t.car_driver is '司机';
comment on column appointment_t.car_driver_phone is '司机电话';
comment on column appointment_t.container_number is '柜号';
comment on column appointment_t.expected_date is '预期日期';
comment on column appointment_t.arrival_date is '到达日期';
comment on column appointment_t.status is '状态';
comment on column appointment_t.remark is '备注';
comment on column appointment_t.company_id      is '公司ID';
comment on column appointment_t.warehouse_id    is '仓库ID';
comment on column appointment_t.del_flag    is '删除标志（N代表存在 Y代表删除）';
comment on column appointment_t.create_by     is '创建者';
comment on column appointment_t.create_time   is '创建时间';
comment on column appointment_t.update_by     is '更新者';
comment on column appointment_t.update_time   is '更新时间';
comment on column appointment_t.update_version   is '更新时间';
comment on column appointment_t.description   is '数据描述';




alter table ALLOCATE_T modify STATUS default '10';
alter table INBOUND_CANCEL_DETAIL_T modify STATUS default '10';
alter table INBOUND_CANCEL_HEADER_T modify STATUS default '10';
alter table INBOUND_DETAIL_T modify STATUS default '10';
alter table INBOUND_HEADER_T modify STATUS default '10';
alter table INBOUND_SOURCE_T modify STATUS default '10';
alter table INVENTORY_COUNT_DETAIL_T modify STATUS default '10';
alter table INVENTORY_COUNT_HEADER_T modify STATUS default '10';
alter table inventory_adjustment_header_t modify STATUS default '10';
alter table OUTBOUND_DETAIL_T modify STATUS default '10';
alter table OUTBOUND_HEADER_T modify STATUS default '10';
alter table TASK_DETAIL_T modify STATUS default '10';


alter table ALLOCATE_T modify QUANTITY_ALLOCATED default 0;
alter table DATA_PRODUCT_MGT modify PRO_LENGTH default 0;
alter table DATA_PRODUCT_MGT modify PRO_WIDTH default 0;
alter table DATA_PRODUCT_MGT modify PRO_HIGH default 0;
alter table DATA_PRODUCT_MGT modify PRO_GROSS default 0;
alter table DATA_PRODUCT_MGT modify PRO_NET default 0;
alter table DATA_PRODUCT_MGT modify PRO_VOL default 0;
alter table DATA_PRODUCT_MGT modify PRO_PRICE default 0;
alter table DATA_STOCK_MGT modify LOC_LENGTH default 0;
alter table DATA_STOCK_MGT modify LOC_WIDTH default 0;
alter table DATA_STOCK_MGT modify LOC_HIGHT default 0;
alter table INBOUND_CANCEL_DETAIL_T modify QUANTITY_CANCEL default 0;
alter table INBOUND_DETAIL_T modify UOM_QUANTITY default 0;
alter table INBOUND_DETAIL_T modify QUANTITY_EXPECTED default 0;
alter table INBOUND_DETAIL_T modify QUANTITY_RECEIVE default 0;
alter table INBOUND_DETAIL_T modify QUANTITY_CANCEL default 0;
alter table inventory_locked_t modify QUANTITY_LOCKED default 0;
alter table INVENTORY_COUNT_DETAIL_T modify QUANTITY_SYSTEM default 0;
alter table INVENTORY_COUNT_DETAIL_T modify QUANTITY_COUNT default 0;
alter table INVENTORY_COUNT_DETAIL_T modify QUANTITY_REPLAY default 0;
alter table INVENTORY_COUNT_DETAIL_T modify QUANTITY_DIFFERENCE default 0;
alter table INVENTORY_COUNT_DETAIL_T modify QUANTITY_CONFIRM default 0;
alter table INVENTORY_ONHAND_T modify QUANTITY_ONHAND default 0;
alter table INVENTORY_ONHAND_T modify QUANTITY_ALLOCATED default 0;
alter table INVENTORY_ONHAND_T modify QUANTITY_LOCKED default 0;
alter table INVENTORY_TRANSACTION_T modify QUANTITY default 0;
alter table inventory_adjustment_detail_t modify QUANTITY_ONHAND default 0;
alter table inventory_adjustment_detail_t modify QUANTITY_ADJUSTMENT default 0;
alter table LPN_T modify VOLUME default 0;
alter table LPN_T modify LENGTH default 0;
alter table LPN_T modify WIDTH default 0;
alter table LPN_T modify HEIGHT default 0;
alter table LPN_T modify WEIGHT_GROSS default 0;
alter table LPN_T modify WEIGHT_NET default 0;
alter table LPN_T modify WEIGHT_TARE default 0;
alter table OUTBOUND_DETAIL_T modify QUANTITY_ORDER default 0;
alter table OUTBOUND_DETAIL_T modify QUANTITY_EXPECTED default 0;
alter table OUTBOUND_DETAIL_T modify QUANTITY_ALLOCATED default 0;
alter table OUTBOUND_DETAIL_T modify QUANTITY_PICKED default 0;
alter table OUTBOUND_DETAIL_T modify QUANTITY_SHIPED default 0;
alter table PACK_T modify QTY default 0;
alter table PACK_T modify QTY_INNER default 0;
alter table PACK_T modify QTY_CASE default 0;
alter table SKU_T modify VOLUME default 0;
alter table SKU_T modify LENGTH default 0;
alter table SKU_T modify WIDTH default 0;
alter table SKU_T modify HEIGHT default 0;
alter table SKU_T modify WEIGHT_GROSS default 0;
alter table SKU_T modify WEIGHT_NET default 0;
alter table SKU_T modify WEIGHT_TARE default 0;
alter table TASK_DETAIL_T modify QUANTITY default 0;



--增加 毛重 净重 皮重 体积
alter table INBOUND_DETAIL_T ADD WEIGHT_GROSS number(20,5) default 0;
alter table INBOUND_DETAIL_T ADD WEIGHT_NET number(20,5) default 0;
alter table INBOUND_DETAIL_T ADD WEIGHT_TARE number(20,5) default 0;
alter table INBOUND_DETAIL_T ADD volume number(20,5) default 0;

comment on column INBOUND_DETAIL_T.WEIGHT_GROSS   is '毛重';
comment on column INBOUND_DETAIL_T.WEIGHT_NET   is '净重';
comment on column INBOUND_DETAIL_T.WEIGHT_TARE   is '皮重';
comment on column INBOUND_DETAIL_T.volume   is '体积';

alter table OUTBOUND_DETAIL_T ADD WEIGHT_GROSS number(20,5) default 0;
alter table OUTBOUND_DETAIL_T ADD WEIGHT_NET number(20,5) default 0;
alter table OUTBOUND_DETAIL_T ADD WEIGHT_TARE number(20,5) default 0;
alter table OUTBOUND_DETAIL_T ADD volume number(20,5) default 0;

comment on column OUTBOUND_DETAIL_T.WEIGHT_GROSS   is '毛重';
comment on column OUTBOUND_DETAIL_T.WEIGHT_NET   is '净重';
comment on column OUTBOUND_DETAIL_T.WEIGHT_TARE   is '皮重';
comment on column OUTBOUND_DETAIL_T.volume   is '体积';


--订单系统入库增加目标仓库、货品描述
alter table INBOUND_DETAIL_T ADD SKU_DESCR varchar2(200);
alter table INBOUND_HEADER_T ADD TO_WAREHOUSE_CODE varchar2(50);
alter table INBOUND_HEADER_T ADD TO_WAREHOUSE_ID number;
comment on column INBOUND_DETAIL_T.SKU_DESCR   is '货品描述';
comment on column INBOUND_HEADER_T.TO_WAREHOUSE_CODE   is '目标仓库代码';
comment on column INBOUND_HEADER_T.TO_WAREHOUSE_ID   is '目标仓库ID';


--订单表增加承运人
alter table OUTBOUND_HEADER_T ADD CARRIER_ID number;
alter table OUTBOUND_HEADER_T ADD CARRIER_CODE varchar2(50);
comment on column OUTBOUND_HEADER_T.CARRIER_ID   is '承运人ID';
comment on column OUTBOUND_HEADER_T.CARRIER_CODE   is '承运人代码';

--分配明细表增加来源库位，来源LPN
alter table ALLOCATE_T ADD (FROM_LOCATION_CODE varchar2(50));
alter table ALLOCATE_T ADD (FROM_LPN_NUMBER varchar2(50));
comment on column ALLOCATE_T.FROM_LOCATION_CODE   is '来源库位';
comment on column ALLOCATE_T.FROM_LOCATION_CODE   is '来源LPN';




--增加内包装规格
alter table PACK_T ADD (
volume_inner number(20,5) default 0,
length_inner number(20,5) default 0,
width_inner number(20,5) default 0,
height_inner number(20,5) default 0,
weight_gross_inner number(20,5) default 0,
weight_net_inner number(20,5) default 0,
weight_tare_inner number(20,5) default 0
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
volume_case number(20,5) default 0,
length_case number(20,5) default 0,
width_case number(20,5) default 0,
height_case number(20,5) default 0,
weight_gross_case number(20,5) default 0,
weight_net_case number(20,5) default 0,
weight_tare_case number(20,5) default 0
);
comment on column PACK_T.volume_case is '箱包装体积';
comment on column PACK_T.length_case is '箱包装长';
comment on column PACK_T.width_case is '箱包装宽';
comment on column PACK_T.height_case is '箱包装高';
comment on column PACK_T.weight_gross_case is '箱包装毛重';
comment on column PACK_T.weight_net_case is '箱包装净重';
comment on column PACK_T.weight_tare_case is '箱包装皮重';



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

alter table allocate_short_t add (SOURCE_WAVE_NUMBER varchar2(100));
comment on column allocate_short_t.SOURCE_WAVE_NUMBER is '来源波次号';

