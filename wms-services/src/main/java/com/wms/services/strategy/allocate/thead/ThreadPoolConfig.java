package com.wms.services.strategy.allocate.thead;

import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.google.common.collect.Maps;

public class ThreadPoolConfig {
	
	public static Map<Long, ThreadPoolTaskExecutor> threadPoolMap = Maps.newConcurrentMap();
	 
	// 核心线程池大小
	private static int corePoolSize = 1;

	// 最大可创建的线程数
	private static int maxPoolSize = 200;

	// 队列最大长度
	private static int queueCapacity = 1000;

	// 线程池维护线程所允许的空闲时间
	private static int keepAliveSeconds = 300;

	public static ThreadPoolTaskExecutor newThreadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.initialize();
		executor.setThreadNamePrefix("newAllocateTheadPool-");
		executor.setMaxPoolSize(maxPoolSize);
		executor.setCorePoolSize(corePoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setKeepAliveSeconds(keepAliveSeconds);
		// 线程池对拒绝任务(无线程可用)的处理策略
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}
	
	public static ThreadPoolTaskExecutor newAllocateThead(Long skuId) {
		if (threadPoolMap.containsKey(skuId)) {
			return threadPoolMap.get(skuId);
		}
		ThreadPoolTaskExecutor executor = newThreadPoolTaskExecutor();
		threadPoolMap.put(skuId, executor);
		return executor;
	}

}
