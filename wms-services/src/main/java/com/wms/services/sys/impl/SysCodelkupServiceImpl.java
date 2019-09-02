package com.wms.services.sys.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wms.common.core.domain.CodelkupVO;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.cache.CodelkUpUtils;
import com.wms.common.utils.cache.LocaleUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ISysCodelistTDao;
import com.wms.dao.auto.ISysCodelkupTDao;
import com.wms.dao.example.SysCodelistTExample;
import com.wms.dao.example.SysCodelkupTExample;
import com.wms.entity.auto.SysCodelistTEntity;
import com.wms.entity.auto.SysCodelkupTEntity;
import com.wms.services.sys.ISysCodelkupService;

@Service
public class SysCodelkupServiceImpl implements ISysCodelkupService,InitializingBean {
	
	private static final Logger log = LoggerFactory.getLogger(SysCodelkupServiceImpl.class);
	
	@Autowired
	private ISysCodelkupTDao codelkupDao;
	
	@Autowired
	private ISysCodelistTDao codeListDao;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			load();
		} catch (Exception e) {
			log.error("load codelkup error.", e);
		}
	}
	
	@Override
	public void load() {
		CodelkUpUtils.clear();
		//查询所有codelist
		SysCodelistTExample codelistExample = new SysCodelistTExample();
		codelistExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andActiveEqualTo(YesNoEnum.Yes.getCode());
		
		List<SysCodelistTEntity> codelists = codeListDao.selectByExample(codelistExample);
		
		Map<Long, SysCodelistTEntity> codelistMap = new HashMap<Long, SysCodelistTEntity>();
		List<List<Long>> multiIds = convertMultiArray(codelists, codelistMap); 
		
		List<SysCodelkupTEntity> codelkups = new ArrayList<SysCodelkupTEntity>();
		for (List<Long> list : multiIds) {
			if (CollectionUtils.isEmpty(list))
				continue;
			SysCodelkupTExample codelkupExample = new SysCodelkupTExample();
			codelkupExample.createCriteria()
			.andDelFlagEqualTo(YesNoEnum.No.getCode())
			.andActiveEqualTo(YesNoEnum.Yes.getCode())
			.andCodelistIdIn(list);
			codelkupExample.orderBy("SORT");
			List<SysCodelkupTEntity> codes = codelkupDao.selectByExample(codelkupExample);
			codelkups.addAll(codes);
		}
		
		for (SysCodelkupTEntity code : codelkups) {
			SysCodelistTEntity codelist = codelistMap.get(code.getCodelistId());
			StringBuilder codelistname = new StringBuilder(codelist.getCode());
			
			CodelkupVO vo = new CodelkupVO();
			vo.setCode(code.getCode());
			vo.setDescr(code.getDescr());
			vo.setSelected(YesNoEnum.Yes.getCode().equals(code.getIsDefault()));
			
			if (YesNoEnum.Yes.getCode().equals(codelist.getIsAll())) {
				CodelkUpUtils.put(codelistname.toString(), vo);
				continue;
			}
			codelistname.append(LocaleUtils.CONTACT);
			codelistname.append(codelist.getCompanyId());
			codelistname.append(LocaleUtils.CONTACT);
			codelistname.append(codelist.getWarehouseId());
			CodelkUpUtils.put(codelistname.toString(), vo);
		}
		
	}
	
	/**
	 * 转换多维数组， 使用 in 条件太多会造成数据库异常
	 * @return
	 */
	private List<List<Long>> convertMultiArray(List<SysCodelistTEntity> codelists, Map<Long, SysCodelistTEntity> codelistMap){
		
		List<Long> ids = new ArrayList<Long>();
		List<List<Long>> multiIds = new ArrayList<List<Long>>();
		multiIds.add(ids);
		
		int count = 0;
		for (SysCodelistTEntity cl : codelists) {
			count ++;
			ids.add(cl.getCodelistId());
			if (count == 999) {
				ids = new ArrayList<Long>();
				count = 0;
				multiIds.add(ids);
			}
			codelistMap.put(cl.getCodelistId(), cl);
		}
		
		return multiIds;
	}

	public List<SysCodelkupTEntity> queryAll(){
		return null;
	}


	@Override
	public List<SysCodelkupTEntity> find(PageRequest request) throws BusinessServiceException {
		SysCodelkupTExample codelkupTExample=new SysCodelkupTExample();
		SysCodelkupTExample.Criteria codelkupTExampleCriteria = codelkupTExample.createCriteria();

		//转换查询方法
		ExampleUtils.create(SysCodelkupTEntity.Column.class,SysCodelkupTExample.Criterion.class)
				.criteria(codelkupTExampleCriteria)
				.data(request)
				.build(request)
				.orderby(codelkupTExample);

		codelkupTExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		return codelkupDao.selectByExample(codelkupTExample);
	}

	@Override
	@Transactional
	public Boolean modify(AjaxRequest<List<SysCodelkupTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}

		List<SysCodelkupTEntity> list = request.getData();

		for (SysCodelkupTEntity codelkup : list) {

			SysCodelkupTEntity update = SysCodelkupTEntity.builder()
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.descr(codelkup.getDescr())
					.sort(codelkup.getSort())
					.remark(codelkup.getRemark())
					.active(codelkup.getActive())
					.type(codelkup.getType())
					.isDefault(codelkup.getIsDefault())
					.build();

			SysCodelkupTExample example = new SysCodelkupTExample();
			example.createCriteria()
					.andWarehouseIdEqualTo(codelkup.getWarehouseId())
					.andCompanyIdEqualTo(codelkup.getCompanyId())
					.andCodelistIdEqualTo(codelkup.getCodelistId())
					.andCodelkupIdEqualTo(codelkup.getCodelkupId());

			int row = codelkupDao.updateWithVersionByExampleSelective(codelkup.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean add(AjaxRequest<List<SysCodelkupTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}

		List<SysCodelkupTEntity> list = request.getData();

		for (SysCodelkupTEntity codelkup : list) {
			Long codelistId = codelkup.getCodelistId();
			String code = codelkup.getCode().toUpperCase();
			SysCodelkupTExample example = new SysCodelkupTExample();
			example.createCriteria()
					.andWarehouseIdEqualTo(request.getWarehouseId())
					.andCompanyIdEqualTo(request.getCompanyId())
					.andCodelistIdEqualTo(codelistId)
					.andCodeEqualTo(code);
			long count = codelkupDao.countByExample(example);
			if (count > 0) {
				throw new BusinessServiceException("SysCodelkupServiceImpl", "code.record.exists" , new Object[] {code});
			}

			SysCodelkupTEntity insert = SysCodelkupTEntity.builder()
					.createBy(request.getUserName())
					.createTime(new Date())
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.companyId(request.getCompanyId())
					.warehouseId(request.getWarehouseId())
					.codelkupId(KeyUtils.getUID())
					.codelistId(codelistId)
					.code(code)
					.descr(codelkup.getDescr())
					.sort(codelkup.getSort())
					.remark(codelkup.getRemark())
					.active(codelkup.getActive())
					.type(codelkup.getType())
					.isDefault(codelkup.getIsDefault())
					.build();

			int row = codelkupDao.insertSelective(insert);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean delete(AjaxRequest<List<SysCodelkupTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<SysCodelkupTEntity> list = request.getData();

		for (SysCodelkupTEntity codelkup : list) {

			SysCodelkupTEntity update = SysCodelkupTEntity.builder()
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.delFlag(YesNoEnum.Yes.getCode())
					.build();

			SysCodelkupTExample example = new SysCodelkupTExample();
			example.createCriteria()
					.andWarehouseIdEqualTo(codelkup.getWarehouseId())
					.andCompanyIdEqualTo(codelkup.getCompanyId())
					.andCodelistIdEqualTo(codelkup.getCodelistId())
					.andCodelkupIdEqualTo(codelkup.getCodelkupId());

			int row = codelkupDao.updateWithVersionByExampleSelective(codelkup.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public List<SysCodelkupTEntity> findByCodelist(SysCodelistTEntity codelist) throws BusinessServiceException {
		SysCodelkupTExample detailExample = new SysCodelkupTExample();
		detailExample.createCriteria()
				.andDelFlagEqualTo(YesNoEnum.No.getCode())
				.andWarehouseIdEqualTo(codelist.getWarehouseId())
				.andCompanyIdEqualTo(codelist.getCompanyId())
				.andCodelistIdEqualTo(codelist.getCodelistId());
		List<SysCodelkupTEntity> list =codelkupDao.selectByExample(detailExample);
		return list;
	}

	@Override
	@Transactional
	public Boolean delete(List<SysCodelkupTEntity> codelkupList) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(codelkupList)) {
			throw new BusinessServiceException("no record delete.");
		}

		for (SysCodelkupTEntity codelkup : codelkupList) {
			SysCodelkupTEntity update = SysCodelkupTEntity.builder()
					.updateBy(codelkup.getUpdateBy())
					.updateTime(new Date())
					.delFlag(YesNoEnum.Yes.getCode())
					.build();

			SysCodelkupTExample example = new SysCodelkupTExample();
			example.createCriteria()
					.andWarehouseIdEqualTo(codelkup.getWarehouseId())
					.andCompanyIdEqualTo(codelkup.getCompanyId())
					.andCodelistIdEqualTo(codelkup.getCodelistId())
					.andCodelkupIdEqualTo(codelkup.getCodelkupId());

			int row = codelkupDao.updateWithVersionByExampleSelective(codelkup.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
	}
}
