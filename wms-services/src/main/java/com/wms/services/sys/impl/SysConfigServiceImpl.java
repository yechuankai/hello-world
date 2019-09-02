package com.wms.services.sys.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.cache.ConfigUtils;
import com.wms.common.utils.cache.LocaleUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ISysConfigTDao;
import com.wms.dao.example.SysConfigTExample;
import com.wms.entity.auto.SysConfigTEntity;
import com.wms.services.sys.ISysConfigService;

@Service
public class SysConfigServiceImpl implements ISysConfigService,InitializingBean {

	private static final Logger log = LoggerFactory.getLogger(SysConfigServiceImpl.class);
	
	@Autowired
	ISysConfigTDao configDao ;
	
	@Override
	public List<SysConfigTEntity> findAll() throws BusinessServiceException {
		SysConfigTExample TExample = new SysConfigTExample();
		TExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andActiveEqualTo(YesNoEnum.Yes.getCode());
		
		List<SysConfigTEntity> configs = configDao.selectByExample(TExample);
		
		return configs;
	}
	
	public void loadConfig() {
		ConfigUtils.clear();
		List<SysConfigTEntity> list = this.findAll();
		for (SysConfigTEntity config : list) {
			if(config.getWarehouseId() != null && config.getWarehouseId() > 0) {
				StringBuilder sb = new StringBuilder();
				sb.append(config.getWarehouseId());
				sb.append(LocaleUtils.CONTACT);
				sb.append(config.getCompanyId());
				sb.append(LocaleUtils.CONTACT);
				sb.append(config.getConfigCode());
				ConfigUtils.put(sb.toString(), config.getConfigValue());
				continue;
			}
			ConfigUtils.put(config.getConfigCode(), config.getConfigValue());
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			loadConfig();
		} catch (Exception e) {
			log.error("load codelkup error.", e);
		}
	}

	@Override
	public List<SysConfigTEntity> find(PageRequest request) throws BusinessServiceException {
		SysConfigTExample TExample = new SysConfigTExample();
		SysConfigTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(SysConfigTEntity.Column.class, SysConfigTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		return configDao.selectByExample(TExample);
	}

	@Override
	@Transactional
	public Boolean modify(AjaxRequest<List<SysConfigTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}
		
		List<SysConfigTEntity> list = request.getData();
		
		for (SysConfigTEntity c : list) {
			
			SysConfigTEntity update = SysConfigTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.configValue(c.getConfigValue())
			.build();
			
			SysConfigTExample example = new SysConfigTExample();
			example.createCriteria()
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andCompanyIdEqualTo(request.getCompanyId())
			.andConfigIdEqualTo(c.getConfigId());
			
			int row = configDao.updateWithVersionByExampleSelective(c.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean add(AjaxRequest<List<SysConfigTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}
		
		List<SysConfigTEntity> list = request.getData();
		
		for (SysConfigTEntity c : list) {
			
			SysConfigTExample TExample = new SysConfigTExample();
			TExample.createCriteria()
			.andDelFlagEqualTo(YesNoEnum.No.getCode())
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andCompanyIdEqualTo(request.getCompanyId())
			.andConfigCodeEqualTo(c.getConfigCode());
			Long count = configDao.countByExample(TExample);
			if (count > 0) {
				throw new BusinessServiceException("SysConfigServiceImpl", "config.record.exists" , new Object[] {c.getConfigCode()}); 
			}
			
			
			SysConfigTEntity update = SysConfigTEntity.builder()
			.createBy(request.getUserName())
			.createTime(new Date())
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.configCode(c.getConfigCode())
			.configDescr(c.getConfigDescr())
			.configValue(c.getConfigValue())
			.configType(c.getConfigType())
			.warehouseId(request.getWarehouseId())
			.companyId(request.getCompanyId())
			.active(c.getActive())
			.configId(KeyUtils.getUID())
			.build();
			
			int row = configDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(AjaxRequest<List<SysConfigTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<SysConfigTEntity> list = request.getData();
		
		for (SysConfigTEntity c : list) {
			SysConfigTEntity update = SysConfigTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.delFlag(YesNoEnum.Yes.getCode())
			.build();
			
			SysConfigTExample example = new SysConfigTExample();
			example.createCriteria()
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andCompanyIdEqualTo(request.getCompanyId())
			.andConfigIdEqualTo(c.getConfigId());
			
			int row = configDao.updateWithVersionByExampleSelective(c.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
	}
	
}
