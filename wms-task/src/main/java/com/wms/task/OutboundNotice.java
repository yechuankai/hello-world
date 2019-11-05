package com.wms.task;

import java.util.Set;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Sets;
import com.wms.common.enums.InboundStatusEnum;
import com.wms.common.enums.OutboundStatusEnum;

/**
 * 入库通知
 * @author yechuankai.chnet
 * 
 */
@RestController
@RequestMapping("/services/inner/schedule")
public class OutboundNotice extends BaseNotice{

	private static Set<String> NOTICE_STATUS = Sets.newHashSet(
			OutboundStatusEnum.New.getCode(), //审核完成
			OutboundStatusEnum.Shiped.getCode()); //发货完成
	
	

}
