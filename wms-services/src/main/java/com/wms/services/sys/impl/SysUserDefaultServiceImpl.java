package com.wms.services.sys.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ISysUserDefaultTDao;
import com.wms.dao.example.SysUserDefaultTExample;
import com.wms.entity.auto.SysUserDefaultTEntity;
import com.wms.services.sys.ISysLocaleService;
import com.wms.services.sys.ISysUserDefaultService;
import com.wms.services.sys.ISysWarehouseService;
import com.wms.shiro.utils.ShiroUtils;

@Service
public class SysUserDefaultServiceImpl implements ISysUserDefaultService {

	private static final Logger log = LoggerFactory.getLogger(SysUserDefaultServiceImpl.class);
	
	@Autowired
	ISysUserDefaultTDao userdefaultDao ;
	
	@Autowired
	ISysWarehouseService warehouseService;
	
	@Autowired
	ISysLocaleService localeService;

	@Override
	public List<SysUserDefaultTEntity> find(PageRequest request) throws BusinessServiceException {
		SysUserDefaultTExample TExample = new SysUserDefaultTExample();
		SysUserDefaultTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(SysUserDefaultTEntity.Column.class, SysUserDefaultTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		//非管理员只能查询本人数据
		if (!ShiroUtils.isAdmin())
			TExampleCriteria.andLoginNameEqualTo(ShiroUtils.getSysUser().getLoginName());

		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		return userdefaultDao.selectByExample(TExample);
	}

	@Override
	@Transactional
	public Boolean modify(AjaxRequest<List<SysUserDefaultTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}
		
		List<SysUserDefaultTEntity> list = request.getData();
		
		for (SysUserDefaultTEntity u : list) {
			
			SysUserDefaultTEntity update = SysUserDefaultTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.build();
			
			boolean updateFlag = false;
			if (StringUtils.isNotEmpty(u.getWarehouseCode())) {
				warehouseService.findByCode(u.getWarehouseCode());
				update.setWarehouseCode(u.getWarehouseCode());
				updateFlag = true;
			}
			if (StringUtils.isNotEmpty(u.getLocaleCode())) {
				localeService.findByLocaleCode(u.getLocaleCode());
				update.setLocaleCode(u.getLocaleCode());
				updateFlag = true;
			}
			if (!updateFlag) {
				continue;
			}
			
			SysUserDefaultTExample example = new SysUserDefaultTExample();
			example.createCriteria()
			.andDefaultIdEqualTo(u.getDefaultId());
			
			int row = userdefaultDao.updateWithVersionByExampleSelective(u.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean add(AjaxRequest<List<SysUserDefaultTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}
		
		List<SysUserDefaultTEntity> list = request.getData();
		
		for (SysUserDefaultTEntity u : list) {
			
			SysUserDefaultTExample TExample = new SysUserDefaultTExample();
			TExample.createCriteria()
			.andDelFlagEqualTo(YesNoEnum.No.getCode())
			.andLoginNameEqualTo(u.getLoginName());
			Long count = userdefaultDao.countByExample(TExample);
			if (count > 0) {
				throw new BusinessServiceException("SysConfigServiceImpl", "userdefault.record.exists" , new Object[] {u.getLoginName()}); 
			}
			
			SysUserDefaultTEntity update = SysUserDefaultTEntity.builder()
			.createBy(request.getUserName())
			.createTime(new Date())
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.loginName(u.getLoginName())
			.defaultId(KeyUtils.getUID())
			.build();
			
			if (StringUtils.isNotEmpty(u.getWarehouseCode())) {
				warehouseService.findByCode(u.getWarehouseCode());
				update.setWarehouseCode(u.getWarehouseCode());
			}
			if (StringUtils.isNotEmpty(u.getLocaleCode())) {
				localeService.findByLocaleCode(u.getLocaleCode());
				update.setLocaleCode(u.getLocaleCode());
			}
			
			int row = userdefaultDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(AjaxRequest<List<SysUserDefaultTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<SysUserDefaultTEntity> list = request.getData();
		
		for (SysUserDefaultTEntity u : list) {
			SysUserDefaultTEntity update = SysUserDefaultTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.delFlag(YesNoEnum.Yes.getCode())
			.build();
			
			SysUserDefaultTExample example = new SysUserDefaultTExample();
			example.createCriteria()
			.andDefaultIdEqualTo(u.getDefaultId());
			
			int row = userdefaultDao.updateWithVersionByExampleSelective(u.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
	}


}
