
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

