package com.wms.aspect;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wms.async.manager.AsyncManager;
import com.wms.async.manager.factory.AsyncFactory;
import com.wms.common.constants.ConfigConstants;
import com.wms.common.enums.MonitorStatusEnum;
import com.wms.common.enums.MonitorTypeEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.IpUtils;
import com.wms.common.utils.ServletUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.cache.ConfigUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.entity.auto.SysMonitorLogTEntity;

@Aspect
@Configuration
public class RestAspect {
	
	private static final Logger log = LoggerFactory.getLogger(RestAspect.class);
	
	public RestAspect() {}
	
    @Pointcut("execution(public * com.wms.pub..*(..))")
    public void restCutMethod() {}
    
    // 声明环绕通知
    @Around("restCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
    	Boolean monitorFlag = ConfigUtils.getBooleanValue(ConfigConstants.CONFIG_SYSTEM_MONITOR_REST);
    	Long uuid = null;
    	long starttime = new Date().getTime();
        try {
        	String controller = pjp.getTarget().getClass().getSimpleName();
            String method = pjp.getSignature().getName();
            StringBuilder args = new StringBuilder();
            for (Object arg : pjp.getArgs()) {
            	if (arg instanceof String)
            		args.append(arg);
            	else
            		args.append(JSONObject.toJSONString(arg, SerializerFeature.DisableCircularReferenceDetect));
            }
            
            if(log.isDebugEnabled())
            	log.debug("<{}.{}>,request params:{}", controller, method, args);
            
            //是否记录日志
            if (monitorFlag) {
            	uuid = KeyUtils.getUID();
            	SysMonitorLogTEntity monitor = SysMonitorLogTEntity.builder()
            			.createTime(new Date())
            			.updateTime(new Date())
            			.createBy("RestAspect")
            			.updateBy("RestAspect")
            			.clz(controller)
            			.method(method)
            			.request(args.toString())
            			.type(MonitorTypeEnum.Rest.getCode())
            			.ip(IpUtils.getHostIp())
            			.status(MonitorStatusEnum.Processing.getCode())
            			.monitorLogId(uuid)
            			.build();
            	//设置当前URL
				if (ServletUtils.getRequest() != null) {
					String url = ServletUtils.getRequest().getRequestURL().toString();
					monitor.setUrl(url);
				}
            	AsyncManager.me().execute(AsyncFactory.recordMonitor(monitor, Boolean.FALSE));
            }
            Object ret = pjp.proceed();
            if(log.isDebugEnabled())
            	log.debug("<{}.{}>,response params:{}", controller, method, JSON.toJSONString(ret, SerializerFeature.DisableCircularReferenceDetect));
            
            long endtime = new Date().getTime();
            
            if(log.isDebugEnabled())
            	log.debug("<{}.{}>,request time:{}", controller, method, (endtime - starttime));
            
            //是否记录日志
            if (monitorFlag && uuid != null) {
            	SysMonitorLogTEntity monitor = SysMonitorLogTEntity.builder()
            			.updateTime(new Date())
            			.status(MonitorStatusEnum.Success.getCode())
            			.response(JSON.toJSONString(ret))
            			.time(endtime - starttime)
            			.monitorLogId(uuid)
            			.build();
            	AsyncManager.me().execute(AsyncFactory.recordMonitor(monitor, true));
            }
            
            return ret;
		} catch (Exception e) {
			if (!(e instanceof BusinessServiceException)) 
				log.error(e.getMessage(), e);
			
			if (monitorFlag && uuid != null) {
				long endtime = new Date().getTime();
				SysMonitorLogTEntity monitor = SysMonitorLogTEntity.builder()
            			.updateTime(new Date())
            			.updateBy("RestAspect")
            			.status(MonitorStatusEnum.Fail.getCode())
            			.time(endtime - starttime)
            			.monitorLogId(uuid)
            			.build();
            	AsyncManager.me().execute(AsyncFactory.recordMonitor(monitor, true));
			}
			throw e;
		}
    }
}
