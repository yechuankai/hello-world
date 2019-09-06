package com.wms.services.inventory.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.dao.auto.IInventoryCountRequestTDao;
import com.wms.dao.auto.IInventoryOnhandTDao;
import com.wms.dao.auto.ILocationTDao;
import com.wms.dao.example.InventoryCountRequestTExample;
import com.wms.dao.example.InventoryOnhandTExample;
import com.wms.dao.example.LocationTExample;
import com.wms.entity.auto.InventoryCountRequestTEntity;
import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.entity.auto.LocationTEntity;
import com.wms.services.inventory.IInventoryCountRequestService;

@Service
public class InventoryCountRequestImpl implements IInventoryCountRequestService {
	
	private final static String SEPARATORCHARS = ",";
	private final static String FROM_ALL = "0";
	private final static String TO_ALL = "ZZZZZZZZZZ";
	
	@Autowired
	private IInventoryCountRequestTDao inventoryCountRequestDao;
	
	@Autowired
	private ILocationTDao locationDao;
	
	@Autowired
	private IInventoryOnhandTDao onhandDao;

	@Override
	public List<InventoryCountRequestTEntity> find(PageRequest request) throws BusinessServiceException {
		InventoryCountRequestTExample TExample = new InventoryCountRequestTExample();
		InventoryCountRequestTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(InventoryCountRequestTEntity.Column.class, InventoryCountRequestTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		List<InventoryCountRequestTEntity> requestList = inventoryCountRequestDao.selectByExample(TExample);
		
		return requestList;
	}

	@Override
	public InventoryCountRequestTEntity find(InventoryCountRequestTEntity countRequest) throws BusinessServiceException {
		InventoryCountRequestTExample TExample = new InventoryCountRequestTExample();
		InventoryCountRequestTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		TExampleCriteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(countRequest.getWarehouseId())
		.andCompanyIdEqualTo(countRequest.getCompanyId());
		
		if (StringUtils.isNotEmpty(countRequest.getRequestNumber()))
			TExampleCriteria.andRequestNumberEqualTo(countRequest.getRequestNumber());
		if (countRequest.getInventoryCountRequestId() != null)
			TExampleCriteria.andInventoryCountRequestIdEqualTo(countRequest.getInventoryCountRequestId());
		
		InventoryCountRequestTEntity request = inventoryCountRequestDao.selectOneByExample(TExample);
		if (request == null)
			throw new BusinessServiceException("InventoryCountRequestImpl", "countrequest.not.exists" , new Object[] {countRequest.getInventoryCountRequestId() + "/" + countRequest.getRequestNumber()});
			
		return request;
		
	}

	@Override
	public Boolean modify(AjaxRequest<List<InventoryCountRequestTEntity>> request) throws BusinessServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean add(AjaxRequest<List<InventoryCountRequestTEntity>> request) throws BusinessServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(AjaxRequest<List<InventoryCountRequestTEntity>> request) throws BusinessServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<InventoryOnhandTEntity> findInventory(InventoryCountRequestTEntity request) throws BusinessServiceException {
		//InventoryCountRequestTEntity request = find(countRequest);
		
		Set<Long> locSets = null;
		//库区指定了部分条件
		if (!FROM_ALL.equals(request.getFromZoneCode()) 
				|| !TO_ALL.equals(request.getToZoneCode())) {
			LocationTExample example = new LocationTExample();
			example.createCriteria()
			.andDelFlagEqualTo(YesNoEnum.No.getCode())
			.andCompanyIdEqualTo(request.getCompanyId())
			.andWarehouseIdEqualTo(request.getWarehouseId())
			.andZoneCodeGreaterThanOrEqualTo(request.getFromZoneCode())
			.andZoneCodeLessThanOrEqualTo(request.getToZoneCode());
			List<LocationTEntity> locList = locationDao.selectByExample(example);
			if (CollectionUtils.isNotEmpty(locList))
				locSets = locList.stream().map(LocationTEntity::getLocationId).collect(Collectors.toSet());
		}
		
		InventoryOnhandTExample example = new InventoryOnhandTExample();
		InventoryOnhandTExample.Criteria criteria = example.createCriteria();
		
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andQuantityOnhandGreaterThan(BigDecimal.ZERO)
		.andLocationCodeLessThanOrEqualTo(request.getFromLocationCode())
		.andLocationCodeGreaterThanOrEqualTo(request.getToLocationCode())
		.andSkuCodeLessThanOrEqualTo(request.getFromSkuCode())
		.andSkuCodeGreaterThanOrEqualTo(request.getToSkuCode())
		.andLpnNumberLessThanOrEqualTo(request.getFromLpnNumber())
		.andLpnNumberGreaterThanOrEqualTo(request.getToLpnNumber());
		
		if (StringUtils.isNotEmpty(request.getSkuCodeIn())) {
			String [] skuCodes = request.getSkuCodeIn().split(SEPARATORCHARS);
			criteria.andSkuCodeIn(Lists.newArrayList(skuCodes));
		}
		
		if (StringUtils.isNotEmpty(request.getLpnNumberIn())) {
			String [] lpnNumbers = request.getLpnNumberIn().split(SEPARATORCHARS);
			criteria.andLpnNumberIn(Lists.newArrayList(lpnNumbers));
		}
		
		boolean filterLoc = false;
		if (CollectionUtils.isNotEmpty(locSets)) {
			filterLoc = true;
			if (locSets.size() < 1000) {
				criteria.andLocationIdIn(Lists.newArrayList(locSets));
				filterLoc = false;
			}
		}
		
		List<InventoryOnhandTEntity> onhandList = onhandDao.selectByExample(example);
		if (CollectionUtils.isEmpty(onhandList))
			return Lists.newArrayList();
		
		final Set<Long> filterLocSets = locSets;
		if (filterLoc) {
			onhandList = onhandList.stream().filter(onhand -> filterLocSets.contains(onhand.getLocationId())).collect(Collectors.toList());
		}

		return onhandList;
	}

}
