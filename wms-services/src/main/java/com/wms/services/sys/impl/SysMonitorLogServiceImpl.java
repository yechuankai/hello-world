package com.wms.services.sys.impl;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.core.domain.mongodb.RestContent;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.MonitorTimeEnum;
import com.wms.common.enums.MonitorTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.mongodb.MongoUtils;
import com.wms.dao.auto.ISysMonitorLogTDao;
import com.wms.dao.example.SysMonitorLogTExample;
import com.wms.entity.auto.SysMonitorLogTEntity;
import com.wms.services.sys.ISysMonitorLogService;

@Service
public class SysMonitorLogServiceImpl implements ISysMonitorLogService {

	@Autowired
	private ISysMonitorLogTDao monitorDao;
	
	private static final String MONGO_PRO_TYPE = "type";
	private static final String MONGO_PRO_CONTENT = "content";
	private static final String PRO_REQUEST = "requestParam";
	private static final String PRO_RESPONSE = "responseParam";
	private static final String PRO_ERROR = "errorParam";
	private static final String PRO_TIME = "timeParam";
	
	@Override
	public List<SysMonitorLogTEntity> find(PageRequest request) throws BusinessServiceException {
		SysMonitorLogTExample TExample = new SysMonitorLogTExample();
		SysMonitorLogTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(SysMonitorLogTEntity.Column.class, SysMonitorLogTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		String requestParam = request.getString(PRO_REQUEST);
		String responseParam = request.getString(PRO_RESPONSE);
		String errorParam = request.getString(PRO_ERROR);
		
		Set<String> requestIds = null;
		Set<String> responseIds = null;
		Set<String> errorIds = null;
		boolean mongoQuery = false;
		
		if (StringUtils.isNotEmpty(requestParam)) {
			requestIds = getId(MonitorTypeEnum.Request.getCode(), requestParam);
			if (CollectionUtils.isNotEmpty(requestIds)) {
				TExampleCriteria.andRequestIn(Lists.newArrayList(requestIds));
			}
			mongoQuery = true;
		}
		
		if (StringUtils.isNotEmpty(responseParam)) {
			responseIds = getId(MonitorTypeEnum.Response.getCode(), responseParam);
			if (CollectionUtils.isNotEmpty(requestIds)) {
				TExampleCriteria.andRequestIn(Lists.newArrayList(responseIds));
			}
			mongoQuery = true;
		}
		if (StringUtils.isNotEmpty(errorParam)) {
			errorIds = getId(MonitorTypeEnum.Error.getCode(), errorParam);
			if (CollectionUtils.isNotEmpty(errorIds)) {
				TExampleCriteria.andErrorIn(Lists.newArrayList(errorIds));
			}
			mongoQuery = true;
		}
		
		if (mongoQuery 
				&& CollectionUtils.isEmpty(requestIds)
				&& CollectionUtils.isEmpty(responseIds)
				&& CollectionUtils.isEmpty(errorIds))
			return Lists.newArrayList();
		
		String time = request.getString(PRO_TIME);
		if (StringUtils.isNotEmpty(time)) {
			if (MonitorTimeEnum.T_0_100.getCode().equals(time))
				TExampleCriteria.andTimeBetween(0L, 100L);
			else if(MonitorTimeEnum.T_100_500.getCode().equals(time))
				TExampleCriteria.andTimeBetween(100L, 500L);
			else if(MonitorTimeEnum.T_500_1000.getCode().equals(time))
				TExampleCriteria.andTimeBetween(500L, 1000L);
			else if(MonitorTimeEnum.T_1000_MORE.getCode().equals(time))
				TExampleCriteria.andTimeGreaterThan(1000L);
		}
		
		return monitorDao.selectByExample(TExample);
	}
	
	//从mongodb中获取数据
	private Set<String> getId(String type, String param) {
		
		Criteria c = new Criteria(MONGO_PRO_CONTENT);
		//模糊匹配
		Pattern pattern = Pattern.compile(StringUtils.join("^.*", param ,".*$"), Pattern.CASE_INSENSITIVE);
		c.regex(pattern);
		
		//按类型查询
		if (StringUtils.isNotEmpty(type)) {
			c.and(MONGO_PRO_TYPE).is(type);
		}
		Query query = new Query(c);
		
		List<RestContent> list = MongoUtils.find(query, RestContent.class);
		
		Set<String> ids = list.stream().map(RestContent :: getId ).collect(Collectors.toSet());
		
		if (ids.size() > 1000)
			throw new BusinessServiceException("SysMonitorLogServiceImpl", "data.exceed.1000", new Object[] {ids.size()});
		
		return ids;
	}

}
