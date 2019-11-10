package com.wms.services.inventory.impl;

import com.google.common.collect.Lists;
import com.wms.common.core.domain.IBaseEntity;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.OrderNumberTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.common.utils.security.Md5Utils;
import com.wms.dao.auto.ILotAttributeTDao;
import com.wms.dao.example.LotAttributeTExample;
import com.wms.entity.auto.LotAttributeTEntity;
import com.wms.entity.auto.OwnerTEntity;
import com.wms.entity.auto.SkuTEntity;
import com.wms.services.base.IOwnerService;
import com.wms.services.base.ISkuService;
import com.wms.services.inventory.ILotService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class LotServiceImpl implements ILotService {
	
	@Autowired
	private ISkuService skuService;
	
	@Autowired
	private IOwnerService ownerService;
	
	@Autowired
	private ILotAttributeTDao lotDao;
	
	@Override
	public LotAttributeTEntity find(LotAttributeTEntity lot) throws BusinessServiceException {
		LotAttributeTExample example = new LotAttributeTExample();
		LotAttributeTExample.Criteria criteria = example.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(lot.getWarehouseId())
		.andCompanyIdEqualTo(lot.getCompanyId());
		
		int conditionCount = 0;
		if (StringUtils.isNotEmpty(lot.getMd5())) {
			criteria.andMd5EqualTo(lot.getMd5());
			conditionCount ++;
		}
		if (StringUtils.isNotEmpty(lot.getLotNumber())) {
			criteria.andLotNumberEqualTo(lot.getLotNumber());
			conditionCount ++;
		}
		if (lot.getLotAttributeId() != null) {
			criteria.andLotAttributeIdEqualTo(lot.getLotAttributeId());
			conditionCount ++;
		}
		
		if (conditionCount == 0)
			return null;
		
		LotAttributeTEntity selectLot = lotDao.selectOneByExample(example);
		if (selectLot == null)
			throw new BusinessServiceException("LotServiceImpl", "lot.not.exists" , new Object[] { lot.getLotAttributeId() == null ? lot.getLotNumber() : lot.getLotAttributeId() });
		
		return selectLot;
	}
	
	@Override
	public List<LotAttributeTEntity> findByAttribute(LotAttributeTEntity lot) throws BusinessServiceException {
		LotAttributeTExample example = new LotAttributeTExample();
		LotAttributeTExample.Criteria criteria = example.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(lot.getWarehouseId())
		.andCompanyIdEqualTo(lot.getCompanyId());
		
		int conditionCount = 0;
		if (StringUtils.isNotEmpty(lot.getLotAttribute1())) {
			criteria.andLotAttribute1EqualTo(lot.getLotAttribute1());
			conditionCount ++;
		}
		if (StringUtils.isNotEmpty(lot.getLotAttribute2())) {
			criteria.andLotAttribute2EqualTo(lot.getLotAttribute2());
			conditionCount ++;
		}
		if (StringUtils.isNotEmpty(lot.getLotAttribute3())) {
			criteria.andLotAttribute3EqualTo(lot.getLotAttribute3());
			conditionCount ++;
		}
		if (lot.getLotAttribute4() != null) {
			criteria.andLotAttribute4EqualTo(lot.getLotAttribute4());
			conditionCount ++;
		}
		if (lot.getLotAttribute5() != null) {
			criteria.andLotAttribute5EqualTo(lot.getLotAttribute5());
			conditionCount ++;
		}
		if (StringUtils.isNotEmpty(lot.getLotAttribute6())) {
			criteria.andLotAttribute6EqualTo(lot.getLotAttribute6());
			conditionCount ++;
		}
		if (StringUtils.isNotEmpty(lot.getLotAttribute7())) {
			criteria.andLotAttribute7EqualTo(lot.getLotAttribute7());
			conditionCount ++;
		}
		if (StringUtils.isNotEmpty(lot.getLotAttribute8())) {
			criteria.andLotAttribute8EqualTo(lot.getLotAttribute8());
			conditionCount ++;
		}
		if (StringUtils.isNotEmpty(lot.getLotAttribute9())) {
			criteria.andLotAttribute9EqualTo(lot.getLotAttribute9());
			conditionCount ++;
		}
		if (StringUtils.isNotEmpty(lot.getLotAttribute10())) {
			criteria.andLotAttribute10EqualTo(lot.getLotAttribute10());
			conditionCount ++;
		}
		if (lot.getLotAttribute11() != null) {
			criteria.andLotAttribute11EqualTo(lot.getLotAttribute11());
			conditionCount ++;
		}
		if (lot.getLotAttribute12() != null) {
			criteria.andLotAttribute5EqualTo(lot.getLotAttribute12());
			conditionCount ++;
		}
		if (conditionCount == 0)
			return null;
		
		List<LotAttributeTEntity> selectLot = lotDao.selectByExample(example);
		if (selectLot == null)
			return Lists.newArrayList();
		
		return selectLot;
	}
	
	@Override
	public List<LotAttributeTEntity> findBylotNumbers(LotAttributeTEntity lpn, Set<String> lotNumbers) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(lotNumbers))
			return Lists.newArrayList();
		
		LotAttributeTExample example = new LotAttributeTExample();
		LotAttributeTExample.Criteria criteria = example.createCriteria();
		
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(lpn.getWarehouseId())
		.andCompanyIdEqualTo(lpn.getCompanyId())
		.andLotNumberIn(Lists.newArrayList(lotNumbers));
		
		List<LotAttributeTEntity> lots = lotDao.selectByExample(example);
		if (CollectionUtils.isEmpty(lots))
			return Lists.newArrayList();
		
		return lots;
	}

	@Override
	@Transactional
	public Boolean add(LotAttributeTEntity lot) throws BusinessServiceException {
		
		validate(lot, Boolean.TRUE);
		
		lot.setLotAttributeId(KeyUtils.getUID());
		lot.setLotNumber(KeyUtils.getOrderNumber(lot.getCompanyId(), lot.getWarehouseId(), OrderNumberTypeEnum.Lot));
		
		int rowcount = lotDao.insertSelective(lot);
		if (rowcount == 0)
			throw new BusinessServiceException("record add error.");
		
		return Boolean.TRUE;
	}
	
	private String encodeMd5(LotAttributeTEntity lot) {
		StringBuilder sb = new StringBuilder();
		sb
		.append(lot.getOwnerId())
		.append(lot.getSkuId());
		
		if (StringUtils.isNotEmpty(lot.getLotAttribute1())){
			sb.append(lot.getLotAttribute1());
		}
		if (StringUtils.isNotEmpty(lot.getLotAttribute2())){
			sb.append(lot.getLotAttribute2());
		}
		if (StringUtils.isNotEmpty(lot.getLotAttribute3())){
			sb.append(lot.getLotAttribute3());
		}
		if (StringUtils.isNotNull(lot.getLotAttribute4())){
			sb.append(lot.getLotAttribute4().getTime());
		}
		if (StringUtils.isNotNull(lot.getLotAttribute5())){
			sb.append(lot.getLotAttribute5().getTime());
		}
		if (StringUtils.isNotEmpty(lot.getLotAttribute6())){
			sb.append(lot.getLotAttribute6());
		}
		if (StringUtils.isNotEmpty(lot.getLotAttribute7())){
			sb.append(lot.getLotAttribute7());
		}
		if (StringUtils.isNotEmpty(lot.getLotAttribute8())){
			sb.append(lot.getLotAttribute8());
		}
		if (StringUtils.isNotEmpty(lot.getLotAttribute9())){
			sb.append(lot.getLotAttribute9());
		}
		if (StringUtils.isNotEmpty(lot.getLotAttribute10())){
			sb.append(lot.getLotAttribute10());
		}
		if (StringUtils.isNotNull(lot.getLotAttribute11())){
			sb.append(lot.getLotAttribute11().getTime());
		}
		if (StringUtils.isNotNull(lot.getLotAttribute12())){
			sb.append(lot.getLotAttribute12().getTime());
		}
		String md5 = Md5Utils.hash(sb.toString());
		return md5;
	}
	
	@Override
	@Transactional
	public LotAttributeTEntity registLot(LotAttributeTEntity lot) throws BusinessServiceException {
		
		validate(lot, false);
		
		String md5 = encodeMd5(lot);
		
		LotAttributeTEntity selectLot = null;
		try {
			selectLot = find(LotAttributeTEntity.builder()
					.warehouseId(lot.getWarehouseId())
					.companyId(lot.getCompanyId())
					.md5(md5)
					.build());
		} catch (BusinessServiceException e) {}
		
		if (selectLot != null) 
			return selectLot;
		
		lot.setMd5(md5);
		add(lot);
		
		return lot;
	}
	
	
	private Boolean validate(LotAttributeTEntity lot, Boolean fillValue) {
		
		if (lot.getOwnerId() == null)
			throw new BusinessServiceException("LotServiceImpl", "lot.owner.isnull" , null); 
			
		if (lot.getSkuId() == null)
			throw new BusinessServiceException("LotServiceImpl", "lot.sku.isnull" , null); 
		
		if (!fillValue)
			return Boolean.TRUE;

		OwnerTEntity owner = ownerService.find(OwnerTEntity.builder()
				.warehouseId(lot.getWarehouseId())
				.companyId(lot.getCompanyId())
				.ownerId(lot.getOwnerId())
				.build());
		lot.setOwnerCode(owner.getOwnerCode());

		SkuTEntity sku = skuService.find(SkuTEntity.builder()
				.warehouseId(lot.getWarehouseId())
				.companyId(lot.getCompanyId())
				.skuId(lot.getSkuId())
				.ownerCode(lot.getOwnerCode())
				.build());
		lot.setSkuAlias(sku.getSkuAlias());
		lot.setSkuCode(sku.getSkuCode());
		
		return Boolean.TRUE;
	}

	@Override
	//查询
	public List<LotAttributeTEntity> find(PageRequest request) throws BusinessServiceException {
		LotAttributeTExample TExample = new LotAttributeTExample();
		LotAttributeTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(LotAttributeTEntity.Column.class, LotAttributeTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		return lotDao.selectByExample(TExample);
	}

	@Override
	public Boolean validateLotAttribute(IBaseEntity l1, IBaseEntity l2) throws BusinessServiceException {
		
		LotAttributeTEntity lot1 = new LotAttributeTEntity();
		LotAttributeTEntity lot2 = new LotAttributeTEntity();

		BeanUtils.copyBeanProp(lot1, l1, Boolean.FALSE);
		BeanUtils.copyBeanProp(lot2, l2, Boolean.FALSE);
		
		String l1Md5 = encodeMd5(lot1);
		String l2Md5 = encodeMd5(lot2);
		return (l1Md5.equals(l2Md5));
		
	}

	@Override
	public List<LotAttributeTEntity> findByIds(LotAttributeTEntity lpn, Set<Long> ids) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(ids))
			return Lists.newArrayList();
		
		LotAttributeTExample example = new LotAttributeTExample();
		LotAttributeTExample.Criteria criteria = example.createCriteria();
		
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andCompanyIdEqualTo(lpn.getCompanyId())
		.andLotAttributeIdIn(Lists.newArrayList(ids));
		if (lpn.getWarehouseId() != null) {
			criteria.andWarehouseIdEqualTo(lpn.getWarehouseId());
		}
		
		List<LotAttributeTEntity> lots = lotDao.selectByExample(example);
		if (CollectionUtils.isEmpty(lots))
			return Lists.newArrayList();
		
		return lots;
	}

	

}
