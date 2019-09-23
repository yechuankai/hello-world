package com.wms.services.sys.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.constants.DefaultConstants;
import com.wms.common.constants.ExceptionConstant;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.OnlineStatusEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.dao.auto.ISysUserOnlineTDao;
import com.wms.dao.example.SysUserOnlineTExample;
import com.wms.entity.auto.SysUserOnlineTEntity;
import com.wms.services.sys.ISysUserOnlineService;

@Service
public class SysUserOnlineServiceImpl implements ISysUserOnlineService{

	
	@Autowired
	ISysUserOnlineTDao onlineDao;
	
	@Override
	@Transactional
	public Boolean save(SysUserOnlineTEntity useronline) {
		
		SysUserOnlineTExample example = new SysUserOnlineTExample();
		example.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andSessionidEqualTo(useronline.getSessionid());
		
		int rowcount = 0;
		SysUserOnlineTEntity exists = onlineDao.selectOneByExample(example);
		if ( exists != null ) {
			rowcount = onlineDao.updateWithVersionByExampleSelective(exists.getUpdateVersion()	, useronline, example);
		}else {
			rowcount = onlineDao.insertSelective(useronline);
		}
		//if (rowcount == 0) {
		//	throw new BusinessServiceException("SysUserOnlineServiceImpl", ExceptionConstant.ERROR_DEFAULT, null, "insert error.");
		//}
		return true;
	}

	@Override
	public List<SysUserOnlineTEntity> findOnlineByExpired(Date expiredDate) {
		
		SysUserOnlineTExample example = new SysUserOnlineTExample();
		example.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andStatusEqualTo(OnlineStatusEnum.OnLine.getCode())
		.andLastAccessTimeLessThan(expiredDate);
		
		List<SysUserOnlineTEntity> list = onlineDao.selectByExample(example);
		
		return list;
	}

	@Override
	public SysUserOnlineTEntity findOnlineById(String sessionid) {
		SysUserOnlineTEntity userOnline = find(sessionid);
		if (userOnline == null)
			return null;
		
		if (OnlineStatusEnum.OnLine.getCode().equals(userOnline.getStatus()))
			return userOnline;
		
		return null;
	}
	
	@Override
	public SysUserOnlineTEntity find(String sessionid) {
		
		SysUserOnlineTExample example = new SysUserOnlineTExample();
		example.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andSessionidEqualTo(sessionid);
		
		SysUserOnlineTEntity userOnline = onlineDao.selectOneByExample(example);
		return userOnline;
	}

	@Override
	public List<SysUserOnlineTEntity> findOnline(SysUserOnlineTEntity userOnline) {
		
		return null;
	}

	@Override
	public Boolean offline(List<SysUserOnlineTEntity> userOnlines) {
		if (CollectionUtils.isEmpty(userOnlines)) {
			return false;
		}
		String updateBy = null;
		Set<String> ids = Sets.newHashSet();
		for (SysUserOnlineTEntity userOnline : userOnlines) {
			ids.add(userOnline.getSessionid());
			updateBy = userOnline.getUpdateBy();
		}
		if (StringUtils.isEmpty(updateBy))
			updateBy = DefaultConstants.SYSTEM_USER;
		
		SysUserOnlineTExample example = new SysUserOnlineTExample();
		example.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andSessionidIn(Lists.newArrayList(ids));
		
		SysUserOnlineTEntity updateUserOnline = new SysUserOnlineTEntity();
		updateUserOnline.setUpdateBy(updateBy);
		updateUserOnline.setUpdateTime(new Date());
		updateUserOnline.setStatus(OnlineStatusEnum.OffLine.getCode());
		
		int rowcount = onlineDao.updateByExampleSelective(updateUserOnline, example);
		if (rowcount == 0) {
			throw new BusinessServiceException("SysUserOnlineServiceImpl", ExceptionConstant.ERROR_DEFAULT, null, "batch delete error.");
		}
		
		return rowcount > 0;
	}

	@Override
	public List<SysUserOnlineTEntity> find(PageRequest request) throws BusinessServiceException {
		SysUserOnlineTExample TExample = new SysUserOnlineTExample();
		SysUserOnlineTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(SysUserOnlineTEntity.Column.class, SysUserOnlineTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		return onlineDao.selectByExample(TExample);
	}

}
