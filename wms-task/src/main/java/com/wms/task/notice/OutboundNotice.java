package com.wms.task.notice;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Sets;
import com.wms.common.core.domain.CodelkupVO;
import com.wms.common.enums.InboundStatusEnum;
import com.wms.common.enums.OutboundStatusEnum;
import com.wms.common.enums.StatusHistoryTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.utils.MessageUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.cache.CodelkUpUtils;
import com.wms.common.utils.cache.ConfigUtils;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.entity.auto.InboundHeaderTEntity;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.entity.auto.OwnerTEntity;
import com.wms.entity.auto.StatusHistoryTEntity;
import com.wms.services.inbound.IInboundHeaderService;
import com.wms.services.outbound.IOutboundHeaderService;
import com.wms.services.sys.IStatusHistoryService;
import com.wms.vo.inbound.InboundVO;
import com.wms.vo.mail.RequestVO;

/**
 * 入库通知
 * @author yechuankai.chnet
 * 
 */
@RestController
@RequestMapping("/services/public/schedule")
public class OutboundNotice extends BaseNotice{

	private static Set<String> NOTICE_STATUS = Sets.newHashSet(
			OutboundStatusEnum.New.getCode(), //审核完成
			OutboundStatusEnum.Shiped.getCode()); //发货完成
	
	private static final String url = "/report/html/NOTICEOUTBOUND?__codelkup=OUTBOUNDTYPE,OUTBOUNDSTATUS&id=%s&warehouseId=%s&companyId=%s&userId=0&userName=System&locale=en_US";
	
	@Scheduled(cron = "0 0 8-22 * * ?") //每天8-10点的0分0秒触发
	@RequestMapping("/outbound/notice")
	public void notice() {
		//获取根url
		final String rootUrl = getReportRootUrl();
		//获取配置
		List<CodelkupVO> statusCodelkp = CodelkUpUtils.getCodelkup("OUTBOUNDSTATUS");
		Map<String, CodelkupVO> statusCodelkpMap = statusCodelkp.stream().collect(Collectors.toMap(CodelkupVO::getCode, v->v));
		//获取标题
		String title = ConfigUtils.getValue("NOTIC_SUBJECT_OUTBOUND");
		if (StringUtils.isEmpty(title))
			return;
		
		IOutboundHeaderService outboundHeaderService = SpringUtils.getBean(IOutboundHeaderService.class);
		IStatusHistoryService statusService = SpringUtils.getBean(IStatusHistoryService.class);
		
		List<Long> companys = getCompanys();
		companys.forEach(companyId -> {
			List<StatusHistoryTEntity> list = getData(StatusHistoryTEntity.builder()
											.companyId(companyId)
											.type(StatusHistoryTypeEnum.Outbound.getCode())
											.notice1(YesNoEnum.No.getCode())
											.build(), NOTICE_STATUS);
			
			if (CollectionUtils.isEmpty(list))
				return;
			
			for (int i = 0; i < list.size(); i++) {
				StatusHistoryTEntity h = list.get(i);
				try {
					//再次获取确认数据
					StatusHistoryTEntity selectStatus = statusService.find(h);
					if (selectStatus == null || !YesNoEnum.No.getCode().equals(selectStatus.getNotice1()))
						continue;
					
					//获取出库单
					OutboundHeaderTEntity outbound = new OutboundHeaderTEntity();
					outbound.setCompanyId(h.getCompanyId());
					outbound.setOutboundHeaderId(h.getSourceNumber());
					OutboundHeaderTEntity header = outboundHeaderService.find(outbound);
					
					if (StringUtils.isEmpty(header.getSourceNumber())) {
						throw new RuntimeException("source bill Number is empty");
					}
					
					//构造标题
					String sendTitle = MessageUtils.message(title, header.getSourceNumber(), statusCodelkpMap.get(header.getStatus()).getDescr());
					if (StringUtils.isEmpty(sendTitle)) {
						throw new RuntimeException("subject is empty");
					}
					
					//获取收件人
					List<String> recipients = getRecipient(OwnerTEntity.builder()
														.companyId(header.getCompanyId())
														.warehouseId(header.getWarehouseId())
														.ownerId(header.getOwnerId())
														.build());
					
					if (CollectionUtils.isEmpty(recipients)) {
						throw new RuntimeException("recipient content is empty");
					}
		
					//构造报表URL
					String reportUrl = rootUrl + String.format(url, header.getOutboundHeaderId(), header.getWarehouseId(), header.getCompanyId() );
					if (StringUtils.isEmpty(reportUrl)) {
						throw new RuntimeException("report url is empty");
					}
					
					RequestVO sendObj = new RequestVO();
					
					//请求报表
					sendObj = generatorReport(reportUrl, sendObj);
					if (StringUtils.isEmpty(sendObj.getContent())) {
						throw new RuntimeException("report content is empty");
					}
					
					//开始发送邮件
					sendObj.setTo(recipients);
					sendObj.setSubject(sendTitle);
					sendObj.setEmailType("WMS");
					sendObj.setOperateUser("autoMailSend");
					send(sendObj, h);
				} catch (Exception e) {
					StatusHistoryTEntity updateStatus = new StatusHistoryTEntity();
					updateStatus.setHistoryId(h.getHistoryId());
					updateStatus.setNotice1(e.getMessage());
					updateNoticFlag(updateStatus);
				}
			}
			
		});
	}
	

}
