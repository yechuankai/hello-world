package com.wms.task;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;
import com.wms.common.enums.StatusHistoryTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.entity.auto.StatusHistoryTEntity;
import com.wms.entity.auto.WarehouseActiveTEntity;
import com.wms.services.sys.IStatusHistoryService;
import com.wms.services.sys.IWarehouseActiveService;

/**
 * 通知 邮件方式
 * @author yechuankai.chnet
 *
 */
public class BaseNotice {
	
	protected List<Long> getCompanys(){
		//获取所有仓库
		IWarehouseActiveService warehouseService = SpringUtils.getBean(IWarehouseActiveService.class);
		List<WarehouseActiveTEntity> wareList = warehouseService.findAll();
		if (CollectionUtils.isEmpty(wareList))
			return Lists.newArrayList();
		
		return wareList.stream().map(WarehouseActiveTEntity::getCompanyId).collect(Collectors.toList());
	}
	
	/**
	 * 获取状态数据，一个单多个状态时按最新状态进行发送
	 * @param status
	 * @return
	 */
	protected List<StatusHistoryTEntity> getData(StatusHistoryTEntity status){
		IStatusHistoryService statusHistory = SpringUtils.getBean(IStatusHistoryService.class);
		List<StatusHistoryTEntity> list = statusHistory.findByNotice1(status);
		
		if (CollectionUtils.isEmpty(list))
			return Lists.newArrayList();
		
		//按单号分组
		Map<Long, List<StatusHistoryTEntity>> groupMap = list.stream().collect(Collectors.groupingBy(StatusHistoryTEntity::getSourceNumber));
		
		List<StatusHistoryTEntity> listByOrder = Lists.newArrayList();
		
		//分组后按时间排序获取最新数据
		groupMap.forEach((k, v) -> {
			if (CollectionUtils.isEmpty(v))
				return;
			
			v.sort(new Comparator<StatusHistoryTEntity>() {
				@Override
				public int compare(StatusHistoryTEntity o1, StatusHistoryTEntity o2) {
					return o2.getCreateTime().compareTo(o1.getCreateTime());
				}
			});
			listByOrder.add(v.get(0));
		});
		
		return listByOrder;
	}
}
