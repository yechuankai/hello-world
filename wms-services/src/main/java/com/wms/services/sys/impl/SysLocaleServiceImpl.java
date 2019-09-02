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
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.cache.LocaleUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ISysLocaleTDao;
import com.wms.dao.auto.ISysLocaleVDao;
import com.wms.dao.example.SysLocaleTExample;
import com.wms.dao.example.SysLocaleVExample;
import com.wms.entity.auto.SysLocaleTEntity;
import com.wms.entity.auto.SysLocaleVEntity;
import com.wms.services.sys.ISysLocaleService;

@Service
public class SysLocaleServiceImpl implements ISysLocaleService,InitializingBean {

	private static final Logger log = LoggerFactory.getLogger(SysLocaleServiceImpl.class);
	
	@Autowired
	private ISysLocaleTDao localeDao;
	
	//仅用于查询
	@Autowired
	private ISysLocaleVDao selectDao;
	
	@Override
	public List<SysLocaleVEntity> findAll() throws BusinessServiceException {
		SysLocaleVExample example = new SysLocaleVExample();
		example.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andActiveEqualTo(YesNoEnum.Yes.getCode());
		
		List<SysLocaleVEntity> locales = selectDao.selectByExample(example);
		
		return locales;
	}
	
	public void loadLocale() {
		LocaleUtils.clear();
		List<SysLocaleVEntity> list = this.findAll();
		for (SysLocaleVEntity localeObj : list) {
			String locale = localeObj.getLocaleCode();
			LocaleUtils.put(locale , localeObj.getLabelKey(), localeObj.getLabelValue());
			
			//表数据翻译
			if (StringUtils.isNotEmpty(localeObj.getTableName())) {
				StringBuilder tableSb = new StringBuilder();
				tableSb.append(localeObj.getTableName());
				tableSb.append(LocaleUtils.CONTACT);
				tableSb.append(localeObj.getJoinKey1());
				if (StringUtils.isNotEmpty(localeObj.getJoinKey2())) {
					tableSb.append(LocaleUtils.CONTACT);
					tableSb.append(localeObj.getJoinKey2());
				}
				if (StringUtils.isNotEmpty(localeObj.getJoinKey3())) {
					tableSb.append(LocaleUtils.CONTACT);
					tableSb.append(localeObj.getJoinKey3());
				}
				if (StringUtils.isNotEmpty(localeObj.getJoinKey4())) {
					tableSb.append(LocaleUtils.CONTACT);
					tableSb.append(localeObj.getJoinKey4());
				}
				if (StringUtils.isNotEmpty(localeObj.getJoinKey5())) {
					tableSb.append(LocaleUtils.CONTACT);
					tableSb.append(localeObj.getJoinKey5());
				}
				LocaleUtils.put(locale, tableSb.toString(), localeObj.getLabelValue());
			}
			
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			loadLocale();
		} catch (Exception e) {
			log.error("load codelkup error.", e);
		}
	}

	@Override
	public List<SysLocaleTEntity> find(PageRequest request) throws BusinessServiceException {
		SysLocaleTExample TExample = new SysLocaleTExample();
		SysLocaleTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(SysLocaleTEntity.Column.class, SysLocaleTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		return localeDao.selectByExample(TExample);
	}

	@Override
	@Transactional
	public Boolean modify(AjaxRequest<List<SysLocaleTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}
		
		List<SysLocaleTEntity> list = request.getData();
		
		for (SysLocaleTEntity l : list) {
			
			SysLocaleTEntity update = SysLocaleTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.localeCode(l.getLocaleCode())
			.labelValue(l.getLabelValue())
			.active(l.getActive())
			.tableName(l.getTableName())
			.joinKey1(l.getJoinKey1())
			.joinKey2(l.getJoinKey2())
			.joinKey3(l.getJoinKey3())
			.joinKey4(l.getJoinKey4())
			.joinKey5(l.getJoinKey5())
			.build();
			
			SysLocaleTExample example = new SysLocaleTExample();
			example.createCriteria()
			.andLocaleIdEqualTo(l.getLocaleId());
			
			int row = localeDao.updateWithVersionByExampleSelective(l.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean add(AjaxRequest<List<SysLocaleTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}
		
		List<SysLocaleTEntity> list = request.getData();
		
		for (SysLocaleTEntity l : list) {
			
			SysLocaleTExample example = new SysLocaleTExample();
			example.createCriteria()
			.andDelFlagEqualTo(YesNoEnum.No.getCode())
			.andLocaleCodeEqualTo(l.getLocaleCode())
			.andLabelKeyEqualTo(l.getLabelKey());
			Long count = localeDao.countByExample(example);
			if (count > 0) {
				throw new BusinessServiceException("SysLocaleServiceImpl", "locale.record.exists" , new Object[] {l.getLocaleCode(),l.getLabelKey()}); 
			}
			
			
			SysLocaleTEntity update = SysLocaleTEntity.builder()
			.createBy(request.getUserName())
			.createTime(new Date())
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.localeCode(l.getLocaleCode())
			.labelKey(l.getLabelKey())
			.labelValue(l.getLabelValue())
			.active(l.getActive())
			.tableName(l.getTableName())
			.joinKey1(l.getJoinKey1())
			.joinKey2(l.getJoinKey2())
			.joinKey3(l.getJoinKey3())
			.joinKey4(l.getJoinKey4())
			.joinKey5(l.getJoinKey5())
			.localeId(KeyUtils.getUID())
			.build();
			
			int row = localeDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(AjaxRequest<List<SysLocaleTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<SysLocaleTEntity> list = request.getData();
		
		for (SysLocaleTEntity l : list) {
			SysLocaleTEntity update = SysLocaleTEntity.builder()
			.updateBy(request.getUserName())
			.updateTime(new Date())
			.delFlag(YesNoEnum.Yes.getCode())
			.build();
			
			SysLocaleTExample example = new SysLocaleTExample();
			example.createCriteria()
			.andLocaleIdEqualTo(l.getLocaleId());
			
			int row = localeDao.updateWithVersionByExampleSelective(l.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public SysLocaleTEntity findByLocaleCode(String localeCode) throws BusinessServiceException {
		SysLocaleTExample example = new SysLocaleTExample();
		example.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andLocaleCodeEqualTo(localeCode);
		
		List<SysLocaleTEntity> locales = localeDao.selectByExample(example);
		if (CollectionUtils.isEmpty(locales)) {
			throw new BusinessServiceException("SysLocaleServiceImpl", "locale.record.not.exists", null);
		}
		
		return locales.get(0);
	}

}
