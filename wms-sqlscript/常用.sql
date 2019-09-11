
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

