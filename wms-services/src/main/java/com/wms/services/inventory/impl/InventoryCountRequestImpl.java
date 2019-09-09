package com.wms.services.inventory.impl;

import com.google.common.collect.Lists;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.CountStatusEnum;
import com.wms.common.enums.CountTypeEnum;
import com.wms.common.enums.OrderNumberTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IInventoryCountRequestTDao;
import com.wms.dao.auto.IInventoryOnhandTDao;
import com.wms.dao.auto.ILocationTDao;
import com.wms.dao.example.InventoryCountRequestTExample;
import com.wms.dao.example.InventoryOnhandTExample;
import com.wms.dao.example.LocationTExample;
import com.wms.entity.auto.*;
import com.wms.services.inventory.IInventoryCountDetailService;
import com.wms.services.inventory.IInventoryCountHeaderService;
import com.wms.services.inventory.IInventoryCountRequestService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

	@Autowired
	private IInventoryCountHeaderService countHeaderService;

	@Autowired
	private IInventoryCountDetailService countDetailService;

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
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record update.");
		}

		List<InventoryCountRequestTEntity> list = request.getData();

		for (InventoryCountRequestTEntity w : list) {

			InventoryCountRequestTEntity update = InventoryCountRequestTEntity.builder()
					.requestDescr(w.getRequestDescr())
					.requestType(w.getRequestType())
					.quantityShowFlag(w.getQuantityShowFlag())
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.build();

			InventoryCountRequestTExample example = new InventoryCountRequestTExample();
			example.createCriteria()
					.andWarehouseIdEqualTo(w.getWarehouseId())
					.andCompanyIdEqualTo(w.getCompanyId())
					.andInventoryCountRequestIdEqualTo(w.getInventoryCountRequestId());

			int row = inventoryCountRequestDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean add(AjaxRequest<List<InventoryCountRequestTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record add.");
		}

		List<InventoryCountRequestTEntity> list = request.getData();

		for (InventoryCountRequestTEntity w : list) {
			InventoryCountRequestTEntity update = new InventoryCountRequestTEntity();
			BeanUtils.copyBeanProp(update,w,Boolean.FALSE);
			update.setInventoryCountRequestId(KeyUtils.getUID());
			update.setRequestNumber(KeyUtils.getOrderNumber(request.getCompanyId(),request.getWarehouseId(), OrderNumberTypeEnum.RequestNumber));
			//新建默认盘点类型为按明细
			update.setRequestType(CountTypeEnum.Detail.getCode());
			update.setCreateBy(request.getUserName());
			update.setCreateTime(new Date());
			update.setUpdateBy(request.getUserName());
			update.setUpdateTime(new Date());
			update.setWarehouseId(request.getWarehouseId());
			update.setCompanyId(request.getCompanyId());

			int row = inventoryCountRequestDao.insertSelective(update);
			if (row == 0) {
				throw new BusinessServiceException("record update error.");
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(AjaxRequest<List<InventoryCountRequestTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<InventoryCountRequestTEntity> list = request.getData();

		for (InventoryCountRequestTEntity w : list) {
			InventoryCountRequestTEntity update = InventoryCountRequestTEntity.builder()
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.delFlag(YesNoEnum.Yes.getCode())
					.build();

			InventoryCountRequestTExample example = new InventoryCountRequestTExample();
			example.createCriteria()
					.andWarehouseIdEqualTo(w.getWarehouseId())
					.andCompanyIdEqualTo(w.getCompanyId())
					.andInventoryCountRequestIdEqualTo(w.getInventoryCountRequestId());


			int row = inventoryCountRequestDao.updateWithVersionByExampleSelective(w.getUpdateVersion(), update, example);
			if (row == 0) {
				throw new BusinessServiceException("delete update error.");
			}
		}
		return Boolean.TRUE;
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

	@Override
	public Boolean createCount(AjaxRequest<List<InventoryCountRequestTEntity>> request) throws BusinessServiceException {

		List<InventoryCountRequestTEntity> list = request.getData();
		InventoryCountRequestTEntity countRequest= list.get(0);
		if(null == countRequest){
			throw new BusinessServiceException("no record add.");
		}
		//query inventory
		List<InventoryOnhandTEntity> onhandList = findInventory(countRequest);
		if (CollectionUtils.isEmpty(onhandList)) {
			throw new BusinessServiceException("InventoryCountRequestImpl", "inventory.not.exists" , new Object[] {countRequest.getInventoryCountRequestId() + "/" + countRequest.getRequestNumber()});
		}
		InventoryCountHeaderTEntity countHeader = InventoryCountHeaderTEntity.builder()
				.companyId(request.getCompanyId())
				.warehouseId(request.getWarehouseId())
				.inventoryCountRequestId(countRequest.getInventoryCountRequestId())
				.type(CountTypeEnum.Normal.getCode())
				.replayFlag(YesNoEnum.No.getCode())
				.status(CountStatusEnum.New.getCode())
				.build();
		//add header
		countHeaderService.add(new AjaxRequest<InventoryCountHeaderTEntity>(countHeader, request));

		//create countDetail
		InventoryCountHeaderTEntity select=countHeaderService.find(countHeader);
		List<InventoryCountDetailTEntity> detailList = Lists.newArrayList();

		onhandList.forEach(d ->{
			InventoryCountDetailTEntity detail =new InventoryCountDetailTEntity();
			BeanUtils.copyBeanProp(detail , d ,Boolean.FALSE);
			detail.setInventoryCountHeaderId(select.getInventoryCountHeaderId());
			//明盘时库存现有量填充到系统数量，否则默认为0
			if(StringUtils.equals(YesNoEnum.Yes.getCode(),countRequest.getQuantityShowFlag())){
				detail.setQuantitySystem(d.getQuantityOnhand());
			}else {
				detail.setQuantitySystem(BigDecimal.ZERO);
			}
			detailList.add(detail);
		});

		countDetailService.add(new AjaxRequest<List<InventoryCountDetailTEntity>>(detailList, request));

		countRequest.setRequestDate(new Date());
		modify(new AjaxRequest<List<InventoryCountRequestTEntity>>(Lists.newArrayList(countRequest), request));
		//todo 提示新增了多少行盘点明细
		System.out.println("查到了多少库存++"+onhandList.size());
		return Boolean.TRUE;
	}
}
