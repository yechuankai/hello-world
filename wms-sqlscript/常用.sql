
1.以下SQL用于查看当前数据库登陆用户及正在执行的SQL
SELECT b.sid oracleID,  
           b.username 登录Oracle用户名,  
           b.serial#,  
           spid 操作系统ID,  
           paddr,  
           sql_text 正在执行的SQL,  
           b.machine 计算机名  
    FROM v$process a, v$session b, v$sqlarea c  
    WHERE a.addr = b.paddr  
       AND b.sql_hash_value = c.hash_value;

2.查看哪些表被锁
select b.owner,b.object_name,a.session_id,a.locked_mode from v$locked_object a,dba_objects b where b.object_id = a.object_id;

3.查看哪个session引起的表被锁住
select b.username,b.sid,b.serial#,logon_time from v$locked_object a,v$session b where a.session_id = b.sid order by b.logon_time; 

4.清楚锁表的session
--alter system kill SESSION '405,47249';


update wms.outbound_header_t set status = '60' where outbound_number = '201909150000082';
update wms.outbound_detail_t set status = '60',quantity_expected = '8', quantity_shiped = 0,quantity_picked = 8 where  OUTBOUND_HEADER_ID = '1173306474641469440';
update wms.allocate_t set status = '20' where source_bill_number = '201909150000082';
update wms.inventory_onhand_t set quantity_allocated = 8 where lpn_number = '201909150000082';


SELECT '','zh_CN', T1.CODE || '_' || T.CODE, T.DESCR, T1.CODE, T.CODE,'','','','CODELKUP'
  FROM WMS.SYS_CODELKUP_T T, WMS.SYS_CODELIST_T T1
 WHERE T.CODELIST_ID = T1.CODELIST_ID
   AND T.DEL_FLAG = 'N'
   AND T1.DEL_FLAG = 'N';
