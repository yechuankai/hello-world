
1.����SQL���ڲ鿴��ǰ���ݿ��½�û�������ִ�е�SQL
SELECT b.sid oracleID,  
           b.username ��¼Oracle�û���,  
           b.serial#,  
           spid ����ϵͳID,  
           paddr,  
           sql_text ����ִ�е�SQL,  
           b.machine �������  
    FROM v$process a, v$session b, v$sqlarea c  
    WHERE a.addr = b.paddr  
       AND b.sql_hash_value = c.hash_value;

2.�鿴��Щ����
select b.owner,b.object_name,a.session_id,a.locked_mode from v$locked_object a,dba_objects b where b.object_id = a.object_id;

3.�鿴�ĸ�session����ı���ס
select b.username,b.sid,b.serial#,logon_time from v$locked_object a,v$session b where a.session_id = b.sid order by b.logon_time; 

4.��������session
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
