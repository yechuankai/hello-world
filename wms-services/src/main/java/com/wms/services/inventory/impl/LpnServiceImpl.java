package com.wms.services.inventory.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IInventoryOnhandTDao;
import com.wms.dao.auto.ILpnTDao;
import com.wms.dao.example.InventoryOnhandTExample;
import com.wms.dao.example.LpnTExample;
import com.wms.entity.auto.InventoryTransactionTEntity;
import com.wms.entity.auto.LpnTEntity;
import com.wms.services.inventory.ILpnService;

@Service
public class LpnServiceImpl implements ILpnService {

	@Autowired
	private ILpnTDao lpnDao;
	
	@Autowired
	private IInventoryOnhandTDao inventoryDao;
	
	@Override
	public List<LpnTEntity> findByLpnNumbers(LpnTEntity lpn, Set<String> lpnNumbers) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(lpnNumbers))
			return Lists.newArrayList();
		
		LpnTExample example = new LpnTExample();
		LpnTExample.Criteria criteria = example.createCriteria();
		
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(lpn.getWarehouseId())
		.andCompanyIdEqualTo(lpn.getCompanyId())
		.andLpnNumberIn(Lists.newArrayList(lpnNumbers));
		
		List<LpnTEntity> lpns = lpnDao.selectByExample(example);
		
		return lpns;
	}
	
	@Override
	public List<LpnTEntity> findByLpnIds(LpnTEntity lpn, Set<Long> ids) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(ids))
			return Lists.newArrayList();
		
		LpnTExample example = new LpnTExample();
		LpnTExample.Criteria criteria = example.createCriteria();
		
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andCompanyIdEqualTo(lpn.getCompanyId())
		.andLpnIdIn(Lists.newArrayList(ids));
		
		if (lpn.getWarehouseId() != null) {
			criteria.andWarehouseIdEqualTo(lpn.getWarehouseId());
		}
		
		List<LpnTEntity> lpns = lpnDao.selectByExample(example);
		
		return lpns;
	}
	
	@Override
	public List<LpnTEntity> findByContainerNumber(LpnTEntity lpn) throws BusinessServiceException {
		LpnTExample example = new LpnTExample();
		LpnTExample.Criteria criteria = example.createCriteria();
		
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(lpn.getWarehouseId())
		.andCompanyIdEqualTo(lpn.getCompanyId())
		.andContainerNumberEqualTo(lpn.getContainerNumber());
		
		List<LpnTEntity> lpns = lpnDao.selectByExample(example);
		
		return lpns;
	}
	
	
	@Override
	public LpnTEntity find(LpnTEntity lpn) throws BusinessServiceException {
		LpnTExample example = new LpnTExample();
		LpnTExample.Criteria criteria = example.createCriteria();
		
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(lpn.getWarehouseId())
		.andCompanyIdEqualTo(lpn.getCompanyId());
		
		int conditionCount = 0;
		if (StringUtils.isNotEmpty(lpn.getLpnNumber())) {
			criteria.andLpnNumberEqualTo(lpn.getLpnNumber());
			conditionCount ++;
		}
		if (lpn.getLpnId() != null) {
			criteria.andLpnIdEqualTo(lpn.getLpnId());
			conditionCount ++;
		}
		if (conditionCount == 0)
			return null;
		
		LpnTEntity selectLpn = lpnDao.selectOneByExample(example);
		if (selectLpn == null) 
			throw new BusinessServiceException("LpnServiceImpl", "lpn.record.not.exists" , new Object[] {lpn.getLpnId() == null ? lpn.getLpnNumber() : lpn.getLpnId()});
		
		return selectLpn;
	}

	@Override
	@Transactional
	public Boolean add(LpnTEntity lpn) throws BusinessServiceException {
		validate(lpn);
		lpn.setLpnId(KeyUtils.getUID());
		int rowcount = lpnDao.insertSelective(lpn);
		if (rowcount == 0)
			throw new BusinessServiceException("record add error.");
		
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean Delete(LpnTEntity lpn) throws BusinessServiceException {
		
		LpnTEntity selectLpn = find(lpn);
		
		InventoryOnhandTExample inventoryExample = new InventoryOnhandTExample();
		inventoryExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(lpn.getWarehouseId())
		.andCompanyIdEqualTo(lpn.getCompanyId())
		.andLpnIdEqualTo(lpn.getLpnId());
		
		long inventoryCount = inventoryDao.countByExample(inventoryExample);
		if (inventoryCount > 0) 
			throw new BusinessServiceException("LpnServiceImpl", "lpn.exists.inventory" , new Object[] {lpn.getLpnNumber()});
		
		LpnTExample example = new LpnTExample();
		example.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(lpn.getWarehouseId())
		.andCompanyIdEqualTo(lpn.getCompanyId())
		.andLpnIdEqualTo(lpn.getLpnId());
		
		LpnTEntity updateLpn = LpnTEntity.builder()
								.delFlag(YesNoEnum.Yes.getCode())
								.updateBy(lpn.getUpdateBy())
								.updateTime(new Date())
								.build();
		
		int rowcount = lpnDao.updateWithVersionByExampleSelective(selectLpn.getUpdateVersion(), updateLpn, example);
		if (rowcount == 0)
			throw new BusinessServiceException("record update error.");
		
		return Boolean.TRUE;
	}
	
	private Boolean validate(LpnTEntity lpn) {
		if (StringUtils.isEmpty(lpn.getLpnNumber()))
			throw new BusinessServiceException("LpnServiceImpl", "lpn.lpnnumber.isnull" , null); 
		
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean modify(LpnTEntity lpn) throws BusinessServiceException {
		LpnTEntity selectLpn = find(lpn);
		
		LpnTExample example = new LpnTExample();
		example.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(lpn.getWarehouseId())
		.andCompanyIdEqualTo(lpn.getCompanyId())
		.andLpnIdEqualTo(lpn.getLpnId());
		
		int rowcount = lpnDao.updateWithVersionByExampleSelective(selectLpn.getUpdateVersion(), lpn, example);
		if (rowcount == 0)
			throw new BusinessServiceException("record update error.");
		
		return Boolean.TRUE;
	}

	@Override
	public List<LpnTEntity> find(PageRequest request) throws BusinessServiceException {
		LpnTExample TExample = new LpnTExample();
		LpnTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(LpnTEntity.Column.class, LpnTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		return lpnDao.selectByExample(TExample);
	}


}
