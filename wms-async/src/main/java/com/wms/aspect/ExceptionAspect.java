package com.wms.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.wms.async.manager.AsyncManager;
import com.wms.async.manager.factory.AsyncFactory;
import com.wms.common.constants.ConfigConstants;
import com.wms.common.enums.MonitorStatusEnum;
import com.wms.common.enums.MonitorTypeEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.exception.SystemException;
import com.wms.common.utils.IpUtils;
import com.wms.common.utils.ServletUtils;
import com.wms.common.utils.cache.ConfigUtils;
import com.wms.entity.auto.SysMonitorLogTEntity;

@Aspect
@Configuration
public class ExceptionAspect {

	private static final Logger log = LoggerFactory.getLogger(ExceptionAspect.class);
	private static final String SERVICE_NAME_LIKE = "ServiceImpl";
	private static final String SERVICE_NAME_NOT_LIKE = "$$";
	
	@Pointcut("execution(public * com.wms..*services..*(..))")
    public void exceptionCutMethod() {}
	
	// 声明环绕通知
    @Around("exceptionCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
    	boolean monitorFlag = ConfigUtils.getBooleanValue(ConfigConstants.CONFIG_SYSTEM_MONITOR_EXCEPTION);
    	long starttime = new Date().getTime();
    	String services = null;
    	String method = null;
        try {
        	services = pjp.getTarget().getClass().getSimpleName();
            method = pjp.getSignature().getName();
            Object ret = pjp.proceed();
            return ret;
		} catch (Exception e) {
			//不是顶层的类
			if (!isTopService()) {
				throw e;
			}
			if (e instanceof BusinessServiceException) {
				log.warn(((BusinessServiceException)e).getParams().toString());
				log.warn(e.getMessage(), e);
				throw e;
			}else {
				log.error(e.getMessage(), e);
				//是否记录日志
				if (monitorFlag) {
					long endtime = new Date().getTime();
	            	SysMonitorLogTEntity monitor = SysMonitorLogTEntity.builder()
	            			.createTime(new Date())
	            			.updateTime(new Date())
	            			.createBy("ExceptionAspect")
	            			.updateBy("ExceptionAspect")
	            			.clz(services)
	            			.method(method)
	            			.error(e.getMessage())
	            			.type(MonitorTypeEnum.Exception.getCode())
	            			.time(endtime - starttime)
	            			.ip(IpUtils.getHostIp())
	            			.status(MonitorStatusEnum.Fail.getCode())
	            			.build();
	            	try {
	            		//设置当前URL
						if (ServletUtils.getRequest() != null) {
							String url = ServletUtils.getRequest().getRequestURL().toString();
							monitor.setUrl(url);
						}
					} catch (Exception e2) {}
	            	
	            	AsyncManager.me().execute(AsyncFactory.recordMonitor(monitor, Boolean.FALSE, 0));
				}
				throw new SystemException(e);
			}
			
		}
    }
    
    //服务类嵌套调用，仅获取最上层的错误监控
    private Boolean isTopService() {
    	Throwable ex = new Throwable();
    	StackTraceElement[] stackElements = ex.getStackTrace();
    	int serviceCount = 0;
    	for (StackTraceElement s : stackElements) {
    		String className = s.getClassName();
			if (className.indexOf(SERVICE_NAME_LIKE) > 0 
					&& className.indexOf(SERVICE_NAME_NOT_LIKE) == -1) {
				serviceCount ++;
			}
			if (serviceCount > 1)
				return Boolean.FALSE;
		}
    	return serviceCount <= 1;
    }

}
