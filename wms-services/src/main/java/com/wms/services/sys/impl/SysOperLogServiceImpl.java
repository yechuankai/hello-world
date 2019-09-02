package com.wms.services.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wms.common.constants.ExceptionConstant;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.services.IKeyGeneratorService;
import com.wms.common.enums.YesNoEnum;
import com.wms.dao.auto.ISysOperLogTDao;
import com.wms.dao.example.SysOperLogTExample;
import com.wms.dao.example.SysUserOnlineTExample;
import com.wms.entity.auto.SysOperLogTEntity;
import com.wms.entity.auto.SysUserOnlineTEntity;
import com.wms.services.sys.ISysOperLogService;

@Service
public class SysOperLogServiceImpl implements ISysOperLogService {

	@Autowired
	ISysOperLogTDao operLogDao;
	
	@Override
	public List<SysOperLogTEntity> find(PageRequest request) throws BusinessServiceException {
		SysOperLogTExample TExample = new SysOperLogTExample();
		SysOperLogTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(SysOperLogTEntity.Column.class, SysOperLogTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		return operLogDao.selectByExample(TExample);
	}
	
	@Override
	public Boolean save(SysOperLogTEntity operLog) throws BusinessServiceException {
		
		long uid = KeyUtils.getUID();
		operLog.setOperLogId(uid);
		
		int rowcount = operLogDao.insertSelective(operLog);
		if (rowcount == 0) {
			throw new BusinessServiceException("SysUserOnlineServiceImpl", ExceptionConstant.ERROR_DEFAULT, null, "insert error.");
		}
		return true;
	}

}
