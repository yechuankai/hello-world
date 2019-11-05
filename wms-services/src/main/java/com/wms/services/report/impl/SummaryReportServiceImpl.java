package com.wms.services.report.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.enums.InboundStatusEnum;
import com.wms.common.enums.OutboundStatusEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.utils.DateUtils;
import com.wms.common.utils.StringUtils;
import com.wms.dao.auto.IInboundHeaderTDao;
import com.wms.dao.auto.IOutboundHeaderTDao;
import com.wms.dao.example.InboundHeaderTExample;
import com.wms.dao.example.OutboundHeaderTExample;
import com.wms.entity.auto.InboundHeaderTEntity;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.entity.auto.OwnerTEntity;
import com.wms.services.report.ISummaryReportService;
import com.wms.vo.report.OrderSummaryByMonthVO;
import com.wms.vo.report.OwnerOrderSummaryVO;

@Service
public class SummaryReportServiceImpl implements ISummaryReportService {

	@Autowired
	private IInboundHeaderTDao inboundDao;
	@Autowired
	private IOutboundHeaderTDao outboundDao;
	
	@Override
	public List<OwnerOrderSummaryVO> getOwnerOrderSumarry(AjaxRequest<OwnerTEntity> request) {
		if (request.getData() == null)
			return Lists.newArrayList();
		
		if (StringUtils.isEmpty(request.getData().getOwnerCode()))
			return Lists.newArrayList();
		
		String [] owners = request.getData().getOwnerCode().split(",");
		
		
		//查询所有已完成的入库单
		InboundHeaderTExample inboundexample = new InboundHeaderTExample();
		inboundexample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andStatusEqualTo(InboundStatusEnum.Closed.getCode())
		.andOwnerCodeIn(Lists.newArrayList(owners))
		.andCompanyIdEqualTo(request.getCompanyId());
		
		List<InboundHeaderTEntity> inboundList = inboundDao.selectByExample(inboundexample);
		
		//查询所有已完成的入库单
		OutboundHeaderTExample outboundexample = new OutboundHeaderTExample();
		outboundexample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andStatusIn(Lists.newArrayList(OutboundStatusEnum.Shiped.getCode(), OutboundStatusEnum.PartShiped.getCode()))
		.andOwnerCodeIn(Lists.newArrayList(owners))
		.andCompanyIdEqualTo(request.getCompanyId());
		
		List<OutboundHeaderTEntity> outboundList = outboundDao.selectByExample(outboundexample);
		
		//按货主进行分组
		Set<String> ownerCodes = Sets.newHashSet();
		Map<String, List<InboundHeaderTEntity>> inboundMap = Maps.newHashMap();
		Map<String, List<OutboundHeaderTEntity>> outboundMap = Maps.newHashMap();
		if (CollectionUtils.isNotEmpty(inboundList)) {
			inboundMap = inboundList.stream().collect(Collectors.groupingBy(InboundHeaderTEntity::getOwnerCode));
			ownerCodes.addAll(inboundMap.keySet());
		}
		if (CollectionUtils.isNotEmpty(outboundList)) {
			outboundMap = outboundList.stream().collect(Collectors.groupingBy(OutboundHeaderTEntity::getOwnerCode));
			ownerCodes.addAll(outboundMap.keySet());
		}
		
		List<OwnerOrderSummaryVO> returnList = Lists.newArrayList();
		
		for (String k : ownerCodes) {
			OwnerOrderSummaryVO summary = new OwnerOrderSummaryVO();
			summary.setOwnerCode(k);
			if (inboundMap.get(k) != null) {
				long count = inboundMap.get(k).size();
				summary.setInboundCount(count);
			}else {
				summary.setInboundCount(0L);
			}
			if (outboundMap.get(k) != null) {
				long count = outboundMap.get(k).size();
				summary.setOutboundCount(count);
			}else {
				summary.setOutboundCount(0L);
			}
			returnList.add(summary);
		}
		return returnList;
	}

	@Override
	public List<OrderSummaryByMonthVO> getOrderSumarryByMonth(AjaxRequest<OwnerTEntity> request) {
		if (request.getData() == null)
			return Lists.newArrayList();
		
		if (StringUtils.isEmpty(request.getData().getOwnerCode()))
			return Lists.newArrayList();
		
		//设置6个月前的时间
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -6);
	    c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号
	    c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		String [] owners = request.getData().getOwnerCode().split(",");
		
		//查询所有已完成的入库单
		InboundHeaderTExample inboundexample = new InboundHeaderTExample();
		inboundexample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andStatusEqualTo(InboundStatusEnum.Closed.getCode())
		.andOwnerCodeIn(Lists.newArrayList(owners))
		.andInboundDateGreaterThanOrEqualTo(c.getTime())
		.andCompanyIdEqualTo(request.getCompanyId());
		
		List<InboundHeaderTEntity> inboundList = inboundDao.selectByExample(inboundexample);
		
		//查询所有已完成的出单
		OutboundHeaderTExample outboundexample = new OutboundHeaderTExample();
		outboundexample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andStatusIn(Lists.newArrayList(OutboundStatusEnum.Shiped.getCode(), OutboundStatusEnum.PartShiped.getCode()))
		.andOwnerCodeIn(Lists.newArrayList(owners))
		.andOutboundDateGreaterThanOrEqualTo(c.getTime())
		.andCompanyIdEqualTo(request.getCompanyId());
		
		List<OutboundHeaderTEntity> outboundList = outboundDao.selectByExample(outboundexample);
		
		//按货主进行分组
		Set<String> mons = Sets.newHashSet();
		Map<String, List<InboundHeaderTEntity>> inboundMap = Maps.newHashMap();
		Map<String, List<OutboundHeaderTEntity>> outboundMap = Maps.newHashMap();
		if (CollectionUtils.isNotEmpty(inboundList)) {
			inboundMap = inboundList.stream().collect(Collectors.groupingBy(v -> DateUtils.parseDateToStr(DateUtils.YYYY_MM, v.getInboundDate())));
			mons.addAll(inboundMap.keySet());
		}
		if (CollectionUtils.isNotEmpty(outboundList)) {
			outboundMap = outboundList.stream().collect(Collectors.groupingBy(v -> DateUtils.parseDateToStr(DateUtils.YYYY_MM, v.getOutboundDate())));
			mons.addAll(outboundMap.keySet());
		}
		
		List<OrderSummaryByMonthVO> returnList = Lists.newArrayList();
		
		for (String k : mons) {
			OrderSummaryByMonthVO summary = new OrderSummaryByMonthVO();
			summary.setMon(k);
			if (inboundMap.get(k) != null) {
				long count = inboundMap.get(k).size();
				summary.setInboundCount(count);
			}else {
				summary.setInboundCount(0L);
			}
			if (outboundMap.get(k) != null) {
				long count = outboundMap.get(k).size();
				summary.setOutboundCount(count);
			}else {
				summary.setOutboundCount(0L);
			}
			returnList.add(summary);
		}
		return returnList;
	}

}
