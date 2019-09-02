package com.wms.services.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wms.common.constants.ExceptionConstant;
import com.wms.common.core.services.IKeyGeneratorService;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ISysLoginInforTDao;
import com.wms.entity.auto.SysLoginInforTEntity;
import com.wms.services.sys.ISysLoginInforService;

@Service
public class SysLoginInforServiceImpl implements ISysLoginInforService {

	@Autowired
	ISysLoginInforTDao loginInforDao;
	
	@Autowired
	IKeyGeneratorService keyGenerator;
	
	@Override
	public boolean save(SysLoginInforTEntity loginInfor) throws BusinessServiceException {
		
		long uid = KeyUtils.getUID();
		loginInfor.setLoginInfoId(uid);
		
		int rowcount = loginInforDao.insertSelective(loginInfor);
		if (rowcount == 0) {
			throw new BusinessServiceException("SysLogininforServiceImpl", ExceptionConstant.ERROR_DEFAULT, null, "insert error.");
		}
		return true;
	}

}
