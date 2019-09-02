package com.wms.services.strategy.allocate.thead;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 确保应用退出时能关闭后台线程
 *
 */
@Component("AllocateShutdownManager")
public class ShutdownManager {
	private static final Logger log = LoggerFactory.getLogger(ShutdownManager.class);

	@PreDestroy
	public void destroy() {
		shutdownAllocateThead();
	}

	/**
	 * 停止异步执行任务
	 */
	private void shutdownAllocateThead() {
		try {
			ThreadPoolConfig.threadPoolMap.forEach((k, v) -> {
				log.info("====关闭{}分配任务线程池====", k);
				v.shutdown();
			});
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
