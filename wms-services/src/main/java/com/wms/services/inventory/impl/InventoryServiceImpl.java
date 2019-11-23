package com.wms.services.inventory.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.wms.common.constants.ConfigConstants;
import com.wms.common.core.domain.ExcelTemplate;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.core.services.IExcelService;
import com.wms.common.enums.*;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.cache.ConfigUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IInventoryOnhandTDao;
import com.wms.dao.example.InventoryOnhandTExample;
import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.entity.auto.LotAttributeTEntity;
import com.wms.entity.auto.LpnTEntity;
import com.wms.entity.auto.PackTEntity;
import com.wms.entity.auto.SkuTEntity;
import com.wms.services.base.IPackService;
import com.wms.services.base.ISkuService;
import com.wms.services.core.IInventoryCoreService;
import com.wms.services.inventory.IInventoryService;
import com.wms.services.inventory.ILotService;
import com.wms.services.inventory.ILpnService;
import com.wms.vo.InventoryOnhandVO;
import com.wms.vo.InventoryTranDetailVO;
import com.wms.vo.InventoryTranVO;
import com.wms.vo.PackVO;
import com.wms.vo.excel.InventoryOnhandImportVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements IInventoryService , IExcelService<InventoryOnhandImportVO> {

	@Autowired
	private IInventoryOnhandTDao inventoryDao;
	@Autowired
	private IInventoryCoreService inventoryCoreService;
	@Autowired
	private ILpnService lpnService;
	@Autowired
	private ILotService lotService;
	@Autowired
	private ISkuService skuService;
	@Autowired
	private IPackService packService;
	
	private static final String QUANTITY_AVAILABLE_MORE_THAN_ZERO = "quantityAvailableMoreThanZero";
	public static final String QUANTITY_ONHAND_MORE_THAN_ZERO = "quantityOnhandMoreThanZero";
	private static final String CLEAR = "CLEAR";
	
	/**
	 * find by inventoryOnhandId
	 */
	@Override
	public InventoryOnhandTEntity find(InventoryOnhandTEntity inventory) throws BusinessServiceException {
		InventoryOnhandTExample example = new InventoryOnhandTExample();
		InventoryOnhandTExample.Criteria criteria = example.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(inventory.getWarehouseId())
		.andCompanyIdEqualTo(inventory.getCompanyId())
		.andInventoryOnhandIdEqualTo(inventory.getInventoryOnhandId());
		
		InventoryOnhandTEntity selectInventory = inventoryDao.selectOneByExample(example);
		if (selectInventory == null)
			throw new BusinessServiceException("InventoryServiceImpl", "inventory.not.exists" , new Object[] { inventory.getInventoryOnhandId() });
		
		return selectInventory;
	}
	
	@Override
	@Transactional
	public Boolean add(InventoryOnhandTEntity inventory) throws BusinessServiceException {
		validate(inventory);
		inventory.setInventoryOnhandId(KeyUtils.getUID());
		
		BigDecimal updateOnhandQty = BigDecimal.ZERO;
		BigDecimal updateAllocateQty = BigDecimal.ZERO;
		BigDecimal updateLockQty = BigDecimal.ZERO;
		if (inventory.getQuantityOnhand() != null) {
			updateOnhandQty = inventory.getQuantityOnhand();
		}
		
		if (inventory.getQuantityAllocated() != null) {
			updateAllocateQty = inventory.getQuantityAllocated();
		}
		
		if (inventory.getQuantityLocked() != null) {
			updateLockQty = inventory.getQuantityLocked();
		}
		
		BigDecimal newAvailableQty = updateOnhandQty
				.subtract(updateAllocateQty)
				.subtract(updateLockQty);
		
		if (newAvailableQty.compareTo(BigDecimal.ZERO) < 0)
			throw new BusinessServiceException("InventoryServiceImpl", "inventory.quantity.available.error" 
						, new Object[] { "", newAvailableQty, "" });
		
		int rowcount = inventoryDao.insertSelective(inventory);
		if (rowcount == 0)
			throw new BusinessServiceException("record add error.");
		return Boolean.TRUE;
	}
	
	@Override
	@Transactional
	public Boolean modify(InventoryOnhandTEntity inventory, Boolean replaceFlag) throws BusinessServiceException {
		InventoryOnhandTEntity selectInventory = find(inventory);
		
		InventoryOnhandTExample example = new InventoryOnhandTExample();
		InventoryOnhandTExample.Criteria criteria = example.createCriteria();
		criteria
		.andWarehouseIdEqualTo(inventory.getWarehouseId())
		.andCompanyIdEqualTo(inventory.getCompanyId())
		.andInventoryOnhandIdEqualTo(inventory.getInventoryOnhandId());
		
		BigDecimal updateOnhandQty = selectInventory.getQuantityOnhand();
		BigDecimal updateAllocateQty = selectInventory.getQuantityAllocated();
		BigDecimal updateLockQty = selectInventory.getQuantityLocked();
		
		boolean updateOnhandFlag = false;
		boolean updateAllocateFlag = false;
		boolean updateLockFlag = false;
		
		if (inventory.getQuantityOnhand() != null) {
			updateOnhandQty = inventory.getQuantityOnhand();
			updateOnhandFlag = true;
		}
		
		if (inventory.getQuantityAllocated() != null) {
			updateAllocateQty = inventory.getQuantityAllocated();
			updateAllocateFlag = true;
		}
		
		if (inventory.getQuantityLocked() != null) {
			updateLockQty = inventory.getQuantityLocked();
			updateLockFlag = true;
		}
		
		//不进行替换，进行累加
		if (!replaceFlag) {
			if (updateOnhandFlag)
				updateOnhandQty = selectInventory.getQuantityOnhand().add(updateOnhandQty);
			if (updateAllocateFlag)
				updateAllocateQty = selectInventory.getQuantityAllocated().add(updateAllocateQty);
			if (updateLockFlag)
				updateLockQty = selectInventory.getQuantityLocked().add(updateLockQty);
		}
		
		if(BigDecimal.ZERO.compareTo(updateOnhandQty) > 0)
			throw new BusinessServiceException("InventoryServiceImpl", "inventory.quantity.onhand.not.lessthan.zero" , new Object[] {selectInventory.getSkuCode(), updateOnhandQty, selectInventory.getInventoryOnhandId()});
		
		if(BigDecimal.ZERO.compareTo(updateAllocateQty) > 0)
			throw new BusinessServiceException("InventoryServiceImpl", "inventory.quantity.allocate.not.lessthan.zero" , new Object[] {selectInventory.getSkuCode(), updateAllocateQty, selectInventory.getInventoryOnhandId()});
		
		if(BigDecimal.ZERO.compareTo(updateLockQty) > 0)
			throw new BusinessServiceException("InventoryServiceImpl", "inventory.quantity.lock.not.lessthan.zero" , new Object[] {selectInventory.getSkuCode(), updateLockQty, selectInventory.getInventoryOnhandId()});
		
		BigDecimal availableQty = selectInventory.getQuantityOnhand()
									.subtract(selectInventory.getQuantityAllocated())
									.subtract(selectInventory.getQuantityLocked());
		
		BigDecimal newAvailableQty = updateOnhandQty
				.subtract(updateAllocateQty)
				.subtract(updateLockQty);
		
		if (newAvailableQty.compareTo(BigDecimal.ZERO) < 0)
			throw new BusinessServiceException("InventoryServiceImpl", "inventory.quantity.available.error" 
						, new Object[] { selectInventory.getSkuCode(), availableQty, newAvailableQty , selectInventory.getInventoryOnhandId()});
		
		InventoryOnhandTEntity updateInventory = InventoryOnhandTEntity.builder()
				.updateBy(inventory.getUpdateBy())
				.updateTime(new Date())
				.build();
		if (updateOnhandFlag)
			updateInventory.setQuantityOnhand(updateOnhandQty);
		if (updateAllocateFlag)
			updateInventory.setQuantityAllocated(updateAllocateQty);
		if (updateLockFlag)
			updateInventory.setQuantityLocked(updateLockQty);
		
		int rowcount = inventoryDao.updateWithVersionByExampleSelective(selectInventory.getUpdateVersion(), updateInventory, example);
		if (rowcount == 0)
			throw new BusinessServiceException("record update error.");
		
		//return 
		inventory.setQuantityOnhand(updateOnhandQty);
		inventory.setQuantityAllocated(updateAllocateQty);
		
		return Boolean.TRUE;
	}

	
	/**
	 * find by locationCode lotId lpnId   
	 */
	@Override
	public InventoryOnhandTEntity findByLogicalKey(InventoryOnhandTEntity inventory) throws BusinessServiceException {
		InventoryOnhandTExample example = new InventoryOnhandTExample();
		InventoryOnhandTExample.Criteria criteria = example.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(inventory.getWarehouseId())
		.andCompanyIdEqualTo(inventory.getCompanyId());
		
		if (inventory.getOwnerId() != null)
			criteria.andOwnerIdEqualTo(inventory.getOwnerId());
		else
			criteria.andOwnerCodeEqualTo(inventory.getOwnerCode());
		
		if (inventory.getSkuId() != null)
			criteria.andSkuIdEqualTo(inventory.getSkuId());
		else
			criteria.andSkuCodeEqualTo(inventory.getSkuCode());
		
		if (inventory.getLocationId() != null)
			criteria.andLocationIdEqualTo(inventory.getLocationId());
		else
			criteria.andLocationCodeEqualTo(inventory.getLocationCode());
		
		if (inventory.getLotId() != null)
			criteria.andLotIdEqualTo(inventory.getLotId());
		else
			criteria.andLotNumberEqualTo(inventory.getLotNumber());
		
		if (inventory.getLpnId() == null && StringUtils.isEmpty(inventory.getLpnNumber()))
			criteria.andLpnIdIsNull();
		else if (inventory.getLpnId() == null && StringUtils.isNotEmpty(inventory.getLpnNumber()))
			criteria.andLpnNumberEqualTo(inventory.getLpnNumber());
		else if (inventory.getLpnId() != null)	
			criteria.andLpnIdEqualTo(inventory.getLpnId());
		
		InventoryOnhandTEntity selectInventory = inventoryDao.selectOneByExample(example);
		if (selectInventory == null)
			throw new BusinessServiceException("InventoryServiceImpl", "inventory.not.exists" , new Object[] { inventory.getInventoryOnhandId() });
		
		return selectInventory;
	}
	
	private Boolean validate(InventoryOnhandTEntity inventory) {
		
		if (inventory.getOwnerId() == null)
			throw new BusinessServiceException("InventoryServiceImpl", "inventory.owner.isnull" , null); 
			
		if (inventory.getSkuId() == null)
			throw new BusinessServiceException("InventoryServiceImpl", "inventory.sku.isnull" , null); 
		
		if (inventory.getLotId() == null)
			throw new BusinessServiceException("InventoryServiceImpl", "inventory.lot.isnull" , null); 
		
		if (StringUtils.isEmpty(inventory.getLocationCode()))
			throw new BusinessServiceException("InventoryServiceImpl", "inventory.locationcode.isnull" , null);
		
		if (StringUtils.isEmpty(inventory.getLpnNumber()))
			inventory.setLpnNumber(null);
		
		return Boolean.TRUE;
	}

	@Override
	public PageResult<InventoryOnhandVO> find(PageRequest request) throws BusinessServiceException {
		InventoryOnhandTExample TExample = new InventoryOnhandTExample();
		InventoryOnhandTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		//取页面查询条件可用库存是否大于0,by Ella
		String quantityAvailableMoreThanZero = request.getString(QUANTITY_AVAILABLE_MORE_THAN_ZERO);
		
		//转换查询方法
		ExampleUtils eu = ExampleUtils.create(InventoryOnhandTEntity.Column.class, InventoryOnhandTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		
		
		//增加条件判断,查询条件可用库存是否大于0
		if (YesNoEnum.Yes.getCode().equals(quantityAvailableMoreThanZero)) {
			String condition = StringUtils.join( "(", 
					InventoryOnhandTEntity.Column.quantityOnhand.getValue() , 
					"-", 
					InventoryOnhandTEntity.Column.quantityAllocated.getValue(), 
					"-" , 
					InventoryOnhandTEntity.Column.quantityLocked.getValue() , 
				") > ");
		
		    eu.and(condition, BigDecimal.ZERO );
		}else if (YesNoEnum.No.getCode().equals(quantityAvailableMoreThanZero)) {
			String condition = StringUtils.join( "(", 
					InventoryOnhandTEntity.Column.quantityOnhand.getValue() , 
					"-", 
					InventoryOnhandTEntity.Column.quantityAllocated.getValue(), 
					"-" , 
					InventoryOnhandTEntity.Column.quantityLocked.getValue() , 
				") <= ");
		
		    eu.and(condition, BigDecimal.ZERO );
		}
		
		//增加条件判断,查询条件库存是否大于0
		if (YesNoEnum.Yes.getCode().equals(request.getString(QUANTITY_ONHAND_MORE_THAN_ZERO))) {
			TExampleCriteria.andQuantityOnhandGreaterThan(BigDecimal.ZERO);
		}
		
		//按容器查询
		String container = request.getString(LpnTEntity.Column.containerNumber.getJavaProperty());
		if (StringUtils.isNotEmpty(container)) {
			List<LpnTEntity> lpns = lpnService.findByContainerNumber(LpnTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.containerNumber(container)
					.build());
			if (CollectionUtils.isEmpty(lpns))
				return PageResult.create(Lists.newArrayList());
			
			Set<Long> lpnIds = lpns.stream().map(LpnTEntity::getLpnId).collect(Collectors.toSet());
			TExampleCriteria.andLpnIdIn(Lists.newArrayList(lpnIds));
		}
		
		//按批属性查询
		if (haveLotAttribute(request)) {
			List<LotAttributeTEntity> lots = lotService.find(request);
			if (CollectionUtils.isEmpty(lots))
				return PageResult.create(Lists.newArrayList());
			
			Set<Long> lotIds = lots.stream().map(LotAttributeTEntity::getLotAttributeId).collect(Collectors.toSet());
			TExampleCriteria.andLotIdIn(Lists.newArrayList(lotIds));
		}
		
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		Page page = PageHelper.startPage( request.getPageStart(),  request.getPageSize());
        List<InventoryOnhandTEntity> inventoryOnhandDetailList = inventoryDao.selectByExample(TExample);
		
		if (CollectionUtils.isEmpty(inventoryOnhandDetailList)) 
			return PageResult.create(Lists.newArrayList());
		
		//查询容器号
		Set<Long> lpnIds = inventoryOnhandDetailList.stream().filter(v -> v.getLpnId() != null).map(InventoryOnhandTEntity::getLpnId).collect(Collectors.toSet());
		List<LpnTEntity> lpnList = lpnService.findByLpnIds(LpnTEntity.builder().companyId(request.getCompanyId()).build(), lpnIds);
		Map<Long, LpnTEntity> lpnMap = lpnList.stream().collect(Collectors.toMap(LpnTEntity::getLpnId, v->v));
		
		//查询批属性
		Set<Long> lotIds = inventoryOnhandDetailList.stream().map(InventoryOnhandTEntity::getLotId).collect(Collectors.toSet());
		List<LotAttributeTEntity> lotList = lotService.findByIds(LotAttributeTEntity.builder().companyId(request.getCompanyId()).build(), lotIds);
		Map<Long, LotAttributeTEntity> lotMap = lotList.stream().collect(Collectors.toMap(LotAttributeTEntity::getLotAttributeId, v->v));
		
		//查询SKU/包装  获取规格信息
		//查询KSU
		Set<Long> skuIdSet = inventoryOnhandDetailList.stream().map(InventoryOnhandTEntity::getSkuId).collect(Collectors.toSet());
		List<SkuTEntity> skuList = skuService.findByIds(SkuTEntity.builder()
											.warehouseId(request.getWarehouseId())
											.companyId(request.getCompanyId())
											.build(), skuIdSet);
		Map<String, SkuTEntity> skuMap = skuList.stream().collect(Collectors.toMap(k -> k.getOwnerCode() + k.getSkuCode(), v -> v));
		
		//开启了复制包装，则从批属性获取
		boolean copyPacktoLot = ConfigUtils.getBooleanValue(request.getCompanyId(), request.getWarehouseId(), ConfigConstants.CONFIG_COPY_PACK_TO_LOT8X9);
		//查询包装
		Map<String, PackTEntity> packMap = null;
		if (copyPacktoLot) {
			Set<String> packCodeSet = lotList.stream().filter(v->StringUtils.isNotEmpty(v.getLotAttribute8())).map(LotAttributeTEntity::getLotAttribute8).collect(Collectors.toSet());
			List<PackTEntity> packList = packService.findByPackcodes(PackTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.build(), packCodeSet);
			packMap = packList.stream().collect(Collectors.toMap(PackTEntity::getPackCode, v -> v));
		}
		final Map<String, PackTEntity> packMapFinal = packMap;
		
		List<InventoryOnhandVO> returnList = Lists.newArrayList();
		inventoryOnhandDetailList.forEach(d -> {
			InventoryOnhandVO inventoryOnhandVO = new InventoryOnhandVO(d);
			if (d.getLpnId() != null && lpnMap.containsKey(d.getLpnId())) {
				inventoryOnhandVO.setContainerNumber(lpnMap.get(d.getLpnId()).getContainerNumber());
			}
			LotAttributeTEntity lot = lotMap.get(d.getLotId());
			if (lot != null) {
				inventoryOnhandVO.setLotAttribute1(lot.getLotAttribute1());
				inventoryOnhandVO.setLotAttribute2(lot.getLotAttribute2());
				inventoryOnhandVO.setLotAttribute3(lot.getLotAttribute3());
				inventoryOnhandVO.setLotAttribute4(lot.getLotAttribute4());
				inventoryOnhandVO.setLotAttribute5(lot.getLotAttribute5());
				inventoryOnhandVO.setLotAttribute6(lot.getLotAttribute6());
				inventoryOnhandVO.setLotAttribute7(lot.getLotAttribute7());
				inventoryOnhandVO.setLotAttribute8(lot.getLotAttribute8());
				inventoryOnhandVO.setLotAttribute9(lot.getLotAttribute9());
				inventoryOnhandVO.setLotAttribute10(lot.getLotAttribute10());
				inventoryOnhandVO.setLotAttribute11(lot.getLotAttribute11());
				inventoryOnhandVO.setLotAttribute12(lot.getLotAttribute12());
			}
			
			//计算重量/体积
			if (BigDecimal.ZERO.compareTo(inventoryOnhandVO.getQuantityOnhand()) < 0) {
				SkuTEntity sku = skuMap.get(d.getOwnerCode() + d.getSkuCode());
				if(sku != null) {
					if (!copyPacktoLot) { //没有将包装复制到批属性，以货品主数据为准
						inventoryOnhandVO.setVolume(sku.getVolume().multiply(d.getQuantityOnhand()));
						inventoryOnhandVO.setWeightGross(sku.getWeightGross().multiply(d.getQuantityOnhand()));
						inventoryOnhandVO.setWeightNet(sku.getWeightNet().multiply(d.getQuantityOnhand()));
						inventoryOnhandVO.setWeightTare(sku.getWeightTare().multiply(d.getQuantityOnhand()));
					}else if (packMapFinal != null && StringUtils.isNotEmpty(inventoryOnhandVO.getLotAttribute8())) {
						PackTEntity pack = packMapFinal.get(inventoryOnhandVO.getLotAttribute8());
						if (pack != null) {
							String uom = inventoryOnhandVO.getLotAttribute9();
							PackVO packvo = packService.getPack(pack, sku, uom);
							
							inventoryOnhandVO.setVolume(packvo.getVolume().multiply(d.getQuantityOnhand()));
							inventoryOnhandVO.setWeightGross(packvo.getWeightGross().multiply(d.getQuantityOnhand()));
							inventoryOnhandVO.setWeightNet(packvo.getWeightNet().multiply(d.getQuantityOnhand()));
							inventoryOnhandVO.setWeightTare(packvo.getWeightTare().multiply(d.getQuantityOnhand()));
						}
					}
					
					//设置计费吨
					if (BillingUOMEnum.Volume.getCode().equals(sku.getUomBilling())) {
						inventoryOnhandVO.setRevenueTon(inventoryOnhandVO.getVolume());
					}else { //默认重量
						inventoryOnhandVO.setRevenueTon(inventoryOnhandVO.getWeightGross());
					}
				}
			}
			
			returnList.add(inventoryOnhandVO);
		});
		
		if (CollectionUtils.isEmpty(returnList)) 
			return PageResult.create(Lists.newArrayList());
		
		PageResult pageResult = PageResult.create(page, returnList);
		return pageResult;
	 }
	
	private Boolean haveLotAttribute(PageRequest request) {
		final String lotAttribute = "lotAttribute";
		Set<String> values = request.keySet();
		for (String s : values) {
			if (s.startsWith(lotAttribute)) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	@Override
	public List<InventoryOnhandTEntity> findByLocationId(InventoryOnhandTEntity inventory, Set<Long> locations)
			throws BusinessServiceException {
		InventoryOnhandTExample example = new InventoryOnhandTExample();
		InventoryOnhandTExample.Criteria criteria = example.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(inventory.getWarehouseId())
		.andCompanyIdEqualTo(inventory.getCompanyId())
		.andQuantityOnhandGreaterThan(BigDecimal.ZERO)
		.andLocationIdIn(Lists.newArrayList(locations));
		return inventoryDao.selectByExample(example);
	}
	
	@Override
	public List<InventoryOnhandTEntity> findByOnhandId(InventoryOnhandTEntity inventory, Set<Long> ids)
			throws BusinessServiceException {
		InventoryOnhandTExample example = new InventoryOnhandTExample();
		InventoryOnhandTExample.Criteria criteria = example.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(inventory.getWarehouseId())
		.andCompanyIdEqualTo(inventory.getCompanyId())
		.andInventoryOnhandIdIn(Lists.newArrayList(ids));
		return inventoryDao.selectByExample(example);
	}
	
	@Override
	public List<InventoryOnhandTEntity> findByLpnId(InventoryOnhandTEntity inventory, Set<Long> lpns)
			throws BusinessServiceException {
		 Iterator<Long> ids = lpns.iterator();
		 while(ids.hasNext()) {
			 Long id = ids.next();
			 if (id == null)
				 ids.remove();
		 }
		 
		 if (CollectionUtils.isEmpty(lpns))
			 return Lists.newArrayList();
		
		InventoryOnhandTExample example = new InventoryOnhandTExample();
		InventoryOnhandTExample.Criteria criteria = example.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(inventory.getWarehouseId())
		.andCompanyIdEqualTo(inventory.getCompanyId())
		.andQuantityOnhandGreaterThan(BigDecimal.ZERO)
		.andLpnIdIn(Lists.newArrayList(lpns));
		return inventoryDao.selectByExample(example);
	}

	@Override
	public Boolean move(AjaxRequest<List<InventoryOnhandVO>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData()))
			throw new BusinessServiceException("no data move.");
		
		//validata
		request.getData().forEach(d -> {
			if (StringUtils.isEmpty(d.getToLocationCode()))
				throw new BusinessServiceException("InventoryServiceImpl", "inventory.move.tolocationcode.isnull" , null); 
			if (d.getToQuantity() == null || BigDecimal.ZERO.compareTo(d.getToQuantity()) == 0)
				throw new BusinessServiceException("InventoryServiceImpl", "inventory.move.toquantity.isnull" , null); 
		});
		
		
		//填充移动对象
		//生成移动单号
		String transferNumber = KeyUtils.getOrderNumber(request.getCompanyId(), request.getWarehouseId(), OrderNumberTypeEnum.Transfer);
		InventoryTranVO tran = new InventoryTranVO();
		tran.setTransactionType(TransactionTypeEnum.Move.getCode());
		tran.setSouceBillNumber(transferNumber);
		tran.setCompanyId(request.getCompanyId());
		tran.setWarehouseId(request.getWarehouseId());
		tran.setUserName(request.getUserName());
		
		List<InventoryTranDetailVO> tranDetail = Lists.newArrayList();
		request.getData().forEach(d -> {
			//设置来源库存信息
			InventoryTranDetailVO fromDetail = new InventoryTranDetailVO();
			BeanUtils.copyBeanProp(fromDetail, d);
			
			//设置模模板库存信息
			InventoryTranDetailVO toDetail = new InventoryTranDetailVO();
			BeanUtils.copyBeanProp(toDetail, d);
			toDetail.setLocationCode(d.getToLocationCode());
			
			//目标LPN为空，并且移动的数量相等时，整LPN移动
			if (CLEAR.equalsIgnoreCase(d.getToLpnNumber())) {
				toDetail.setLpnNumber(null);
			}else if (StringUtils.isEmpty(d.getToLpnNumber())
				&& d.getQuantityOnhand().compareTo(d.getToQuantity()) == 0) {
				toDetail.setLpnNumber(d.getLpnNumber());
			}else {
				toDetail.setLpnNumber(d.getToLpnNumber());
			}
			
			if (StringUtils.isNotEmpty(d.getToContainerNumber())) {
				LpnTEntity lpn = new LpnTEntity();
				lpn.setLpnNumber(toDetail.getLpnNumber());
				lpn.setContainerNumber(d.getToContainerNumber());
				toDetail.setLpn(lpn);
			}else {
				LpnTEntity lpn = new LpnTEntity();
				lpn.setLpnNumber(toDetail.getLpnNumber());
				lpn.setContainerNumber("");
				toDetail.setLpn(lpn);
			}
			
			toDetail.setTranQuantity(d.getToQuantity());
			toDetail.setSourceNumber(String.valueOf(d.getInventoryOnhandId()));
			toDetail.setSourceInventoryOnhand(fromDetail);
			if (StringUtils.isEmpty(d.getTransactionCategory())) {
				toDetail.setTransactionCategory(TransactionCategoryEnum.PCMove.getCode());
			}else {
				toDetail.setTransactionCategory(d.getTransactionCategory());
			}
			tranDetail.add(toDetail);
		});
		tran.setDetail(tranDetail);
		//执行移动
		inventoryCoreService.move(tran);
		return Boolean.TRUE;
	}
	
	@Override
	public Boolean find(SkuTEntity sku) {
		InventoryOnhandTExample example = new InventoryOnhandTExample();
		example.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(sku.getWarehouseId())
		.andCompanyIdEqualTo(sku.getCompanyId())
		.andQuantityOnhandGreaterThan(BigDecimal.ZERO)
		.andSkuIdEqualTo(sku.getSkuId());
		long count = inventoryDao.countByExample(example);
		return count > 0;
	}

    @Override
    public ExcelTemplate getExcelTemplate() {
        return new ExcelTemplate<InventoryOnhandImportVO>(ExcelTemplateEnum.InventoryOnhand.getCode(), InventoryOnhandImportVO.class);
    }

    @Override
    public List<InventoryOnhandImportVO> exportData(PageRequest request) throws BusinessServiceException {
        List<InventoryOnhandImportVO> returnList = Lists.newArrayList();
        PageResult page = find(request);
        List<InventoryOnhandVO> onhands = page.getRows();
        if (CollectionUtils.isEmpty(onhands)) {
            return returnList;
        }
        onhands.forEach(d ->{
            InventoryOnhandImportVO onhand = new InventoryOnhandImportVO();
            BeanUtils.copyBeanProp(onhand, d);
            returnList.add(onhand);
        });
        return returnList;
    }

	@Override
	public List<InventoryOnhandTEntity> findByOwner(InventoryOnhandTEntity inventory) throws BusinessServiceException {
		InventoryOnhandTExample example = new InventoryOnhandTExample();
		InventoryOnhandTExample.Criteria criteria = example.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(inventory.getWarehouseId())
		.andCompanyIdEqualTo(inventory.getCompanyId())
		.andQuantityOnhandGreaterThan(BigDecimal.ZERO);
		
		if (inventory.getOwnerId() == null && StringUtils.isEmpty(inventory.getOwnerCode()))
			return Lists.newArrayList();
		
		if (inventory.getOwnerId() != null)
			criteria.andOwnerIdEqualTo(inventory.getOwnerId());
		else
			criteria.andOwnerCodeEqualTo(inventory.getOwnerCode());
		
		List<InventoryOnhandTEntity> selectInventory = inventoryDao.selectByExample(example);
		return selectInventory;
	}

	@Override
	public List<InventoryOnhandTEntity> findAll(InventoryOnhandTEntity inventory) throws BusinessServiceException {
		InventoryOnhandTExample example = new InventoryOnhandTExample();
		InventoryOnhandTExample.Criteria criteria = example.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andQuantityOnhandGreaterThan(BigDecimal.ZERO);
		if (inventory.getWarehouseId() != null) {
			criteria.andWarehouseIdEqualTo(inventory.getWarehouseId());
		}
		
		if (inventory.getCompanyId() != null) {
			criteria.andCompanyIdEqualTo(inventory.getCompanyId());
		}
		List<InventoryOnhandTEntity> selectInventory = inventoryDao.selectByExample(example);
		return selectInventory;
	}

	/**
	 * 按批属性查询库存
	 */
	@Override
	public List<InventoryOnhandVO> findByLotAttribute(InventoryOnhandVO inventory)
			throws BusinessServiceException {
		InventoryOnhandTExample example = new InventoryOnhandTExample();
		InventoryOnhandTExample.Criteria criteria = example.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andQuantityOnhandGreaterThan(BigDecimal.ZERO)
		.andWarehouseIdEqualTo(inventory.getWarehouseId())
		.andCompanyIdEqualTo(inventory.getCompanyId());
		
		LotAttributeTEntity selectLotObj = new LotAttributeTEntity();
		BeanUtils.copyBeanProp(selectLotObj, inventory);
		List<LotAttributeTEntity> lots = lotService.findByAttribute(selectLotObj);
		if (CollectionUtils.isEmpty(lots))
			return Lists.newArrayList();
		
		List<Long> lotIds = lots.stream().map(LotAttributeTEntity::getLotAttributeId).collect(Collectors.toList());
		criteria.andLotIdIn(lotIds);
		
		List<InventoryOnhandTEntity> selectInventory = inventoryDao.selectByExample(example);
		if (selectInventory == null)
			return Lists.newArrayList();
		
		Map<Long, LotAttributeTEntity> lotMap = lots.stream().collect(Collectors.toMap(LotAttributeTEntity::getLotAttributeId, v->v));
		List<InventoryOnhandVO> returnList = Lists.newArrayList();
		selectInventory.forEach(i -> {
			InventoryOnhandVO onhand = new InventoryOnhandVO();
			BeanUtils.copyBeanProp(onhand, i);
			LotAttributeTEntity lot = lotMap.get(i.getLotId());
			if (lot != null) {
				BeanUtils.copyBeanProp(onhand, lot);
			}
			returnList.add(onhand);
		});
		return returnList;
	}
}
