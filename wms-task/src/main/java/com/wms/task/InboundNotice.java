package com.wms.task;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Sets;
import com.wms.common.enums.InboundStatusEnum;
import com.wms.common.enums.StatusHistoryTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.entity.auto.StatusHistoryTEntity;
import com.wms.entity.auto.WarehouseActiveTEntity;
import com.wms.services.sys.IStatusHistoryService;
import com.wms.services.sys.IWarehouseActiveService;

/**
 * 入库通知
 * @author yechuankai.chnet
 * 
 */
@RestController
@RequestMapping("/services/inner/schedule")
public class InboundNotice extends BaseNotice{

	private static Set<String> NOTICE_STATUS = Sets.newHashSet(
			InboundStatusEnum.WaitingDeclaration.getCode(), //审核完成待报关
			InboundStatusEnum.New.getCode(), //报关完成待收货
			InboundStatusEnum.Closed.getCode()); //报关完成

	@Scheduled(cron = "0 0 8-22 * * ?") //每天8-10点的0分0秒触发
	@RequestMapping("/inboud/notice")
	public void notice() {
		List<Long> companys = getCompanys();
		companys.forEach(companyId -> {
			List<StatusHistoryTEntity> list = getData(StatusHistoryTEntity.builder()
											.companyId(companyId)
											.type(StatusHistoryTypeEnum.Inbound.getCode())
											.notice1(YesNoEnum.No.getCode())
											.build());
			
			if (CollectionUtils.isEmpty(list))
				return;
			
			
			
			
			
		});
	}
	
}
