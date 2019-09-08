package com.wms.services.core.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.enums.TransactionTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.enums.allocate.AllocateStatusEnum;
import com.wms.common.enums.allocate.AllocateStrategyTypeEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.MessageUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.*;
import com.wms.services.base.*;
import com.wms.services.core.IInventoryCoreService;
import com.wms.services.inbound.IInboundDetailService;
import com.wms.services.inbound.IInboundHeaderService;
import com.wms.services.inventory.IInventoryService;
import com.wms.services.inventory.ILotService;
import com.wms.services.inventory.ILpnService;
import com.wms.services.inventory.ITransactionService;
import com.wms.services.outbound.IAllocateService;
import com.wms.vo.InventoryTranDetailVO;
import com.wms.vo.InventoryTranVO;
import com.wms.vo.allocate.AllocateVO;
import com.wms.vo.inbound.InboundDetailVO;
import com.wms.vo.inbound.InboundVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InventoryCoreServiceImpl implements IInventoryCoreService {
	
	private static final String SEPARATOR = ","; 
	
	@Autowired
	private ILpnService lpnService;
	@Autowired
	private IInventoryService inventoryService;
	@Autowired
	private ILocationService locationService;
	@Autowired
	private ILotService lotService;
	@Autowired
	private IPackService packService;
	@Autowired
	private IOwnerService ownerService;
	@Autowired
	private ISkuService skuService;
	@Autowired
	private IInboundDetailService inboundDetailService;
	@Autowired
	private IInboundHeaderService inboundHeaderService;
	@Autowired
	private ITransactionService transactionService;
	@Autowired
	private IAllocateService allocateService;
	@Autowired
	private ILotValidateService lotValidateService;

	@Override
	@Transactional
	public InventoryTranVO inbound(InventoryTranVO tran) {
		tran.setTransationTypeEnum(TransactionTypeEnum.Inbound);
		
		validate(tran);
		
		processInbound(tran);
		
		return tran;
	}

	@Override
	@Transactional
	public InventoryTranVO outbound(InventoryTranVO tran) {
		tran.setTransationTypeEnum(TransactionTypeEnum.Outbound);
		
		validate(tran);
		
		processOutbound(tran);
		
		return tran;
	}
	
	@Override
	@Transactional
	public InventoryTranVO move(InventoryTranVO tran) {
		tran.getDetail().forEach(d -> {
			 //获取来源库存信息
			 if (d.getSourceInventoryOnhand() == null)
				 throw new BusinessServiceException("InventoryCoreServiceImpl", "inventory.move.from.isnull" , new Object[] { tran.getSouceBillNumber() });
			 d.getSourceInventoryOnhand().setQuantityOnhand(d.getTranQuantity());
		});
		transfer(tran);
		return tran;
	}
	
	@Override
	@Transactional
	public InventoryTranVO adjustment(InventoryTranVO tran) {
		tran.getDetail().forEach(d -> {
			 //调用outbound进行调整库存，数量需要反向扣减
			BigDecimal tranQuantity = BigDecimal.ZERO.subtract(d.getTranQuantity());
			d.setTranQuantity(tranQuantity);
		});
		outbound(tran);
		return tran;
	}
	
	@Override
	@Transactional
	public InventoryTranVO transfer(InventoryTranVO tran) {
		
		tran.getDetail().forEach(d -> {
			 //获取来源库存信息
			 if (d.getSourceInventoryOnhand() == null)
				 throw new BusinessServiceException("InventoryCoreServiceImpl", "inventory.move.from.isnull" , new Object[] { tran.getSouceBillNumber() });
			 
			 //设置扣减库存相关数据
			 InventoryTranDetailVO detail = new InventoryTranDetailVO();
			 BeanUtils.copyBeanProp(detail, d.getSourceInventoryOnhand(), Boolean.FALSE);
			 detail.setAllocate(d.getAllocate());
			 detail.setTransactionCategory(d.getTransactionCategory());
			 detail.setUpdateBy(tran.getUserName());
			 detail.setWarehouseId(tran.getWarehouseId());
			 detail.setCompanyId(tran.getCompanyId());
			 //库存移动时该数量=交易数量
			 detail.setTranQuantity(d.getSourceInventoryOnhand().getQuantityOnhand());
			 
			 InventoryTranVO outboundTran = new InventoryTranVO();
			 BeanUtils.copyBeanProp(outboundTran, tran);
			 outboundTran.setDetail(Lists.newArrayList(detail));
			 
			 //执行库存扣减
			 outbound(outboundTran);
			 
			 //设置库存增加相关数据
			 d.setSourceInventoryOnhand(detail);
			 InventoryTranVO inboundTran = new InventoryTranVO();
			 BeanUtils.copyBeanProp(inboundTran, tran);
			 inboundTran.setDetail(Lists.newArrayList(d));
			 
			 //执行库存增加
			 inbound(inboundTran);
		});

		return tran;
	}
	
	/**
	 * 处理入库生成库存
	 * @param tran
	 * @return
	 */
	private Boolean processInbound(InventoryTranVO tran) {
		
		for (InventoryTranDetailVO detail : tran.getDetail()) {
			
			SkuTEntity sku = tran.getSkuMap().get(detail.getSkuCode());
			detail.setSkuId(sku.getSkuId());
			detail.setSkuAlias(sku.getSkuAlias());
			
			OwnerTEntity owner = tran.getOwnerMap().get(detail.getOwnerCode());
			detail.setOwnerId(owner.getOwnerId());
			
			LocationTEntity location = tran.getLocationMap().get(detail.getLocationCode());
			detail.setLocationId(location.getLocationId());
			
			
			if (StringUtils.isNotEmpty(detail.getLpnNumber()))
				updateLpn(tran, detail);
			
			if (StringUtils.isEmpty(detail.getLotNumber()))
				registLot(tran, detail);
			
			LotAttributeTEntity lot = tran.getLotMap().get(detail.getLotNumber());
			detail.setLotId(lot.getLotAttributeId());
			
			//是否处理包装
			if (tran.getPackFlag()) {
				//单位数量为0时，按包装单位进行转换
				if (BigDecimal.ZERO.compareTo(detail.getUomQuantity()) == 0
						&& StringUtils.isNotEmpty(detail.getUom()) ) {
					PackTEntity pack = tran.getPackMap().get(detail.getPackCode());
					BigDecimal uomQty = packService.getUOMQty(pack, detail.getUom());
					if (BigDecimal.ZERO.compareTo(uomQty) == 0) 
						throw new BusinessServiceException("InventoryCoreServiceImpl", "inventory.transation.uom.error" , new Object[] { detail.getSequence(), detail.getUom() });
					detail.setUomQuantity(uomQty);
				}
				detail.setTranQuantity(detail.getTranQuantity().multiply(detail.getUomQuantity()));
			}
			
			//处理分配
			if (tran.getAllocateFlag())
				detail.getAllocate().setAllocateStrategyType(AllocateStrategyTypeEnum.Hard.getCode());
			
			// update inventory
			updateInventory(tran, detail);
			
			//update inbound
			if (TransactionTypeEnum.Inbound.getCode().equals(tran.getTransationType())) {
				updateInbound(tran, detail);
			}
			
			//处理分配
			if (tran.getAllocateFlag())
				processInboundAllocate(tran, detail);
			
			
			insertTransaction(tran, detail);
		}
		
		
		return Boolean.TRUE;
	}
	
	/**
	 * 处理出库扣减库存
	 * @param tran
	 * @return
	 */
	private Boolean processOutbound(InventoryTranVO tran) {
		
		for (InventoryTranDetailVO detail : tran.getDetail()) {
			
			//扣减库存，将数量处理为负数
			BigDecimal outboundQuantity = BigDecimal.ZERO.subtract(detail.getTranQuantity());
			detail.setTranQuantity(outboundQuantity);
			//处理分配明细
			if (tran.getAllocateFlag()) {
				//处理分配明细
				AllocateVO allocate = detail.getAllocate();
				AllocateTEntity updateAllocate = AllocateTEntity.builder()
													.warehouseId(tran.getWarehouseId())
													.companyId(tran.getCompanyId())
													.allocateId(allocate.getAllocateId())
													.status(allocate.getStatus())
													.updateBy(tran.getUserName())
													.build();
				//发运状态需保留分配明细
				if (!AllocateStatusEnum.Ship.getCode().equals(allocate.getStatus()))
					updateAllocate.setQuantityAllocated(allocate.getQuantityAllocated().add(outboundQuantity));
				
				//短拣时删除明细
				if (YesNoEnum.Yes.getCode().equals(allocate.getShortFlag())) {
					updateAllocate.setQuantityAllocated(BigDecimal.ZERO);
					//记录短拣数量
					updateAllocate.setDescription(allocate.getQuantityAllocated().add(outboundQuantity).toString());
				}
				allocateService.modify(Lists.newArrayList(updateAllocate));
			}
			// update inventory
			updateInventory(tran, detail);
			
			insertTransaction(tran, detail);
		}
		
		
		return Boolean.TRUE;
	}
	
	/**
	 * 处理分配明细
	 * @param tran
	 * @return
	 */
	private Boolean processInboundAllocate(InventoryTranVO tran, InventoryTranDetailVO detail) {
		if (!tran.getAllocateFlag())
			return Boolean.FALSE;
		
		AllocateTEntity allocate = detail.getAllocate();
		AllocateTEntity updateAllocate = new AllocateTEntity();
		BeanUtils.copyBeanProp(updateAllocate, allocate);
		BeanUtils.copyBeanProp(updateAllocate, detail);
		updateAllocate.setWarehouseId(tran.getWarehouseId());
		updateAllocate.setCompanyId(tran.getCompanyId());
		updateAllocate.setCreateBy(tran.getUserName());
		updateAllocate.setUpdateBy(tran.getUserName());
		updateAllocate.setAllocateStrategyType(AllocateStrategyTypeEnum.Hard.getCode());
		updateAllocate.setQuantityAllocated(detail.getTranQuantity());
		updateAllocate.setLoadLpnNumber(detail.getLpnNumber());
		updateAllocate.setFromLocationCode(allocate.getFromLocationCode());
		updateAllocate.setFromLpnNumber(allocate.getFromLpnNumber());
		allocateService.add(Lists.newArrayList(updateAllocate));
		
		return Boolean.TRUE;
	}
	
	private Boolean validate(InventoryTranVO tran) {
		StringBuilder errorString = new StringBuilder();
		
		if (StringUtils.isEmpty(tran.getTransationType()))
			throw new BusinessServiceException("InventoryCoreServiceImpl", "inventory.transation.type.isnull" , null);
		
		if (StringUtils.isEmpty(tran.getSouceBillNumber()))
			throw new BusinessServiceException("InventoryCoreServiceImpl", "inventory.transation.sourcebillnumber.isnull" , null);
		
		tran.getDetail().forEach(d -> {
			if (BigDecimal.ZERO.compareTo(d.getTranQuantity()) == 0) 
				throw new BusinessServiceException("InventoryCoreServiceImpl", "inventory.transation.quantity.error" , new Object[] { d.getSequence() });
			if (tran.getAllocateFlag() && d.getAllocate() == null)
				throw new BusinessServiceException("InventoryCoreServiceImpl", "inventory.transation.allocate.isnull" , new Object[] { d.getSequence() });
		});
		
		switch (tran.getTransationTypeEnum()) {
			case Inbound:
				Set<String> owners = Sets.newHashSet();
				Set<String> skus = Sets.newHashSet();
				Set<String> lpns = Sets.newHashSet();
				Set<String> locations = Sets.newHashSet();
				Set<String> lots = Sets.newHashSet();
				Set<String> packs = Sets.newHashSet();
				tran.getDetail().forEach(d -> {
					if (StringUtils.isEmpty(d.getOwnerCode())) {
						String message = MessageUtils.message("inventory.transation.owner.isnull", d.getSequence());
						errorString.append(message).append(SEPARATOR);
						return;
					}else {
						owners.add(d.getOwnerCode());
					}
					if (StringUtils.isEmpty(d.getSkuCode())) {
						String message = MessageUtils.message("inventory.transation.sku.isnull", d.getSequence());
						errorString.append(message).append(SEPARATOR);
						return;
					}else {
						skus.add(d.getSkuCode());
					}
					
					if(StringUtils.isNotEmpty(d.getLpnNumber()))
						lpns.add(d.getLpnNumber());
					
					if (StringUtils.isEmpty(d.getLocationCode())) {
						String message = MessageUtils.message("inventory.transation.location.isnull", d.getSequence());
						errorString.append(message).append(SEPARATOR);
					}else {
						locations.add(d.getLocationCode());
					}
						
					if (StringUtils.isNotEmpty(d.getLotNumber()))
						lots.add(d.getLotNumber());
					if (StringUtils.isNotEmpty(d.getPackCode()))
						packs.add(d.getPackCode());
				});
				
				//owner
				Map<String, OwnerTEntity> ownerMap = findOwner(tran, owners);
				StringBuilder ownerNotExists = new StringBuilder();
				owners.forEach(o -> {
					if (!ownerMap.containsKey(o)) {
						ownerNotExists.append(o).append(SEPARATOR);
					}
				});
				if (StringUtils.isNotEmpty(ownerNotExists)) {
					String message = MessageUtils.message("inventory.transation.owner.not.exists", ownerNotExists.toString());
					errorString.append(message);
				}else {
					tran.getOwnerMap().putAll(ownerMap);
				}
				
				//sku
				Map<String, SkuTEntity> skuMap = findSku(tran, skus);
				StringBuilder skuNotExists = new StringBuilder();
				skus.forEach(s -> {
					if (!skuMap.containsKey(s)) {
						skuNotExists.append(s).append(SEPARATOR);
					}
				});
				if (StringUtils.isNotEmpty(skuNotExists)) {
					String message = MessageUtils.message("inventory.transation.sku.not.exists", skuNotExists.toString());
					errorString.append(message);
				}else {
					tran.getSkuMap().putAll(skuMap);
				}
				
				//lpn
				Map<String, LpnTEntity> lpnMap = findLpn(tran, lpns);
				tran.getLpnMap().putAll(lpnMap);
				
				//location
				Map<String, LocationTEntity> locationMap = findLocation(tran, locations);
				StringBuilder locationNotExists = new StringBuilder();
				locations.forEach(l -> {
					if (!locationMap.containsKey(l)) {
						locationNotExists.append(l).append(SEPARATOR);
					}
				});
				if (StringUtils.isNotEmpty(locationNotExists)) {
					String message = MessageUtils.message("inventory.transation.location.not.exists", locationNotExists.toString());
					errorString.append(message);
				}else {
					tran.getLocationMap().putAll(locationMap);
				}
				
				//lot
				Map<String, LotAttributeTEntity> lotMap = findLot(tran, lots);
				StringBuilder lotNotExists = new StringBuilder();
				lots.forEach(l -> {
					if (!lotMap.containsKey(l)) {
						lotNotExists.append(l).append(SEPARATOR);
					}
				});
				if (StringUtils.isNotEmpty(lotNotExists)) {
					String message = MessageUtils.message("inventory.transation.lot.not.exists", lotNotExists.toString());
					errorString.append(message);
				}else {
					tran.getLotMap().putAll(lotMap);
				}
				
				//pack
				Map<String, PackTEntity> packMap = findPack(tran, packs);
				StringBuilder packNotExists = new StringBuilder();
				packs.forEach(p -> {
					if (!packMap.containsKey(p)) {
						packNotExists.append(p).append(SEPARATOR);
					}
				});
				if (StringUtils.isNotEmpty(packNotExists)) {
					String message = MessageUtils.message("inventory.transation.pack.not.exists", packNotExists.toString());
					errorString.append(message);
				}else {
					tran.getPackMap().putAll(packMap);
				}
				
				if (StringUtils.isNotEmpty(errorString))
					throw new BusinessServiceException("InventoryCoreServiceImpl", errorString.toString());
				
				break;
			case Outbound:
				tran.getDetail().forEach(d -> {
					InventoryOnhandTEntity inventory = null;
					if (d.getInventoryOnhandId() != null) {
						inventory = inventoryService.find(d);
					}else {
						inventory = inventoryService.findByLogicalKey(d);
					}
					if (inventory == null) {
						throw new BusinessServiceException("InventoryCoreServiceImpl", "inventory.not.exists" , new Object[]{d.getInventoryOnhandId()});
					}
					BeanUtils.copyBeanProp(d, inventory, Boolean.FALSE);
				});
				break;
			default:
				throw new BusinessServiceException("InventoryCoreServiceImpl", "inventory.transation.type.isnull" , null);
		}
		
		return Boolean.TRUE;
		
	}
	
	private Map<String, LpnTEntity> findLpn(InventoryTranVO tran, Set<String> lpns ){
		List<LpnTEntity> list = lpnService.findByLpnNumbers(LpnTEntity.builder()
									.warehouseId(tran.getWarehouseId())
									.companyId(tran.getCompanyId())
									.build(), lpns);
		Map<String, LpnTEntity> lpnMap = list.stream().collect( 
			      Collectors.toMap(LpnTEntity::getLpnNumber, (l) -> l)); 
		return lpnMap;
	}
	
	private Map<String, LocationTEntity> findLocation(InventoryTranVO tran, Set<String> locations ){
		List<LocationTEntity> list = locationService.findByLocationCodes(LocationTEntity.builder()
									.warehouseId(tran.getWarehouseId())
									.companyId(tran.getCompanyId())
									.build(), locations);
		Map<String, LocationTEntity> locationMap = list.stream().collect( 
			      Collectors.toMap(LocationTEntity::getLocationCode, (l) -> l)); 
		return locationMap;
	}
	private Map<String, LotAttributeTEntity> findLot(InventoryTranVO tran, Set<String> lots ){
		List<LotAttributeTEntity> list = lotService.findBylotNumbers(LotAttributeTEntity.builder()
									.warehouseId(tran.getWarehouseId())
									.companyId(tran.getCompanyId())
									.build(), lots);
		Map<String, LotAttributeTEntity> lotMap = list.stream().collect( 
			      Collectors.toMap(LotAttributeTEntity::getLotNumber, (l) -> l)); 
		return lotMap;
	}
	private Map<String, SkuTEntity> findSku(InventoryTranVO tran, Set<String> skus ){
		List<SkuTEntity> list = skuService.findBySkuCodes(SkuTEntity.builder()
									.warehouseId(tran.getWarehouseId())
									.companyId(tran.getCompanyId())
									.build(), skus);
		Map<String, SkuTEntity> skuMap = list.stream().collect( 
			      Collectors.toMap(SkuTEntity::getSkuCode, (l) -> l)); 
		return skuMap;
	}
	private Map<String, OwnerTEntity> findOwner(InventoryTranVO tran, Set<String> owners ){
		List<OwnerTEntity> list = ownerService.findByOwnerCodes(OwnerTEntity.builder()
									.warehouseId(tran.getWarehouseId())
									.companyId(tran.getCompanyId())
									.build(), owners);
		Map<String, OwnerTEntity> ownerMap = list.stream().collect( 
			      Collectors.toMap(OwnerTEntity::getOwnerCode, (l) -> l)); 
		return ownerMap;
	}
	private Map<String, PackTEntity> findPack(InventoryTranVO tran, Set<String> packs ){
		List<PackTEntity> list = packService.findByPackcodes(PackTEntity.builder()
									.warehouseId(tran.getWarehouseId())
									.companyId(tran.getCompanyId())
									.build(), packs);
		Map<String, PackTEntity> packMap = list.stream().collect( 
			      Collectors.toMap(PackTEntity::getPackCode, (l) -> l)); 
		return packMap;
	}
	private LpnTEntity updateLpn(InventoryTranVO tran, InventoryTranDetailVO detail) {
		LpnTEntity existsLpn = tran.getLpnMap().get(detail.getLpnNumber());
		if (existsLpn == null) {
			LpnTEntity addLpn = detail.getLpn();
			if (addLpn == null) 
				addLpn = LpnTEntity.builder().lpnNumber(detail.getLpnNumber()).build();
			addLpn.setCompanyId(tran.getCompanyId());
			addLpn.setWarehouseId(tran.getWarehouseId());
			addLpn.setCreateBy(tran.getUserName());
			addLpn.setCreateTime(new Date());
			addLpn.setUpdateBy(tran.getUserName());
			addLpn.setUpdateTime(new Date());
			addLpn.setLpnNumber(detail.getLpnNumber());
			lpnService.add(addLpn);
			tran.getLpnMap().put(detail.getLpnNumber(), addLpn);
			detail.setLpnId(addLpn.getLpnId());
			return addLpn;
		}
		
		LpnTEntity updateLpn = new LpnTEntity();
		if (detail.getLpn() != null) {
			BeanUtils.copyBeanProp(updateLpn, detail.getLpn(), Boolean.FALSE);
		}
		updateLpn.setCompanyId(tran.getCompanyId());
		updateLpn.setWarehouseId(tran.getWarehouseId());
		updateLpn.setLpnId(existsLpn.getLpnId());
		updateLpn.setUpdateBy(tran.getUserName());
		updateLpn.setUpdateTime(new Date());
		lpnService.modify(updateLpn);
		detail.setLpnId(updateLpn.getLpnId());
		return updateLpn;
	}
	
	private LotAttributeTEntity registLot(InventoryTranVO tran, InventoryTranDetailVO detail) {
		LotAttributeTEntity lot = detail.getLotAttribute();
		if (lot == null)
			throw new BusinessServiceException("InventoryCoreServiceImpl", "inventory.transation.lotattribute.isnull" , null);
		
		lot.setCompanyId(tran.getCompanyId());
		lot.setWarehouseId(tran.getWarehouseId());
		lot.setCreateBy(tran.getUserName());
		lot.setCreateTime(new Date());
		lot.setUpdateBy(tran.getUserName());
		lot.setUpdateTime(new Date());
		lot.setOwnerId(detail.getOwnerId());
		lot.setOwnerCode(detail.getOwnerCode());
		lot.setSkuId(detail.getSkuId());
		lot.setSkuCode(detail.getSkuCode());
		
		//入库时验证批属性
		if (TransactionTypeEnum.Inbound.getCode().equals(tran.getTransationType())) {
			lotValidateService.validate(lot);
		}
		
		lot = lotService.registLot(lot);
		
		tran.getLotMap().put(lot.getLotNumber(), lot);
		
		detail.setLotNumber(lot.getLotNumber());
		detail.setLotId(lot.getLotAttributeId());
		
		return lot;
	}
	
	
	private InventoryOnhandTEntity updateInventory(InventoryTranVO tran, InventoryTranDetailVO detail) {
		InventoryOnhandTEntity inventory = new InventoryOnhandTEntity();
		BeanUtils.copyBeanProp(inventory, detail, Boolean.FALSE);
		InventoryOnhandTEntity selectInventory = null;
		try {
			selectInventory = inventoryService.findByLogicalKey(inventory);
		} catch (BusinessServiceException e) {}
		
		if (selectInventory == null) {
			inventory.setCreateBy(tran.getUserName());
			inventory.setCreateTime(new Date());
			inventory.setUpdateBy(tran.getUserName());
			inventory.setUpdateTime(new Date());
			inventory.setQuantityOnhand(detail.getTranQuantity());
			//处理扣减库存时硬分配库存分配量
			if (tran.getAllocateFlag()
					&& AllocateStrategyTypeEnum.Hard.getCode().equals(detail.getAllocate().getAllocateStrategyType()))
				inventory.setQuantityAllocated(detail.getTranQuantity());
			//增加库存
			inventoryService.add(inventory);
			detail.setInventoryOnhandId(inventory.getInventoryOnhandId());
			return inventory;
		}
		if (tran.getTransationTypeEnum() == TransactionTypeEnum.Outbound 
				&& selectInventory.getInventoryOnhandId().compareTo(detail.getInventoryOnhandId()) != 0)
			throw new BusinessServiceException("InventoryCoreServiceImpl", "inventory.transation.onhand.error", new Object[] {detail.getInventoryOnhandId(), selectInventory.getInventoryOnhandId()});
		
		InventoryOnhandTEntity updateInventory = InventoryOnhandTEntity.builder()
													.warehouseId(tran.getWarehouseId())
													.companyId(tran.getCompanyId())
													.updateBy(tran.getUserName())
													.updateTime(new Date())
													.quantityOnhand(detail.getTranQuantity())
													.inventoryOnhandId(selectInventory.getInventoryOnhandId())
													.build();
		//处理扣减库存时硬分配库存分配量
		if (tran.getAllocateFlag()
				&& AllocateStrategyTypeEnum.Hard.getCode().equals(detail.getAllocate().getAllocateStrategyType()))
			updateInventory.setQuantityAllocated(detail.getTranQuantity());
		
		if(StringUtils.isNotEmpty(detail.getSkuAlias()))
			updateInventory.setSkuAlias(detail.getSkuAlias());
		
		inventoryService.modify(updateInventory, Boolean.FALSE);
		detail.setInventoryOnhandId(updateInventory.getInventoryOnhandId());
		return updateInventory;
	}
	
	private InboundDetailVO updateInbound(InventoryTranVO tran, InventoryTranDetailVO detail) {
		InboundDetailVO inboundDetail = detail.getInboundDetail();
		if (inboundDetail == null)
			return null;
		if (inboundDetail.getInboundHeaderId() == null
				|| inboundDetail.getInboundDetailId() == null)
			return null;
		

		InboundDetailTEntity updateDetail = InboundDetailTEntity.builder()
				.warehouseId(tran.getWarehouseId())
				.companyId(tran.getCompanyId())
				.updateBy(tran.getUserName())
				.updateTime(new Date())
				.inboundDate(new Date())
				.lotNumber(detail.getLotNumber())
				.inboundDetailId(inboundDetail.getInboundDetailId())
				.build();
		inboundDetailService.modify(new InboundDetailVO(updateDetail));
		
		InboundHeaderTEntity updateHeader = InboundHeaderTEntity.builder()
												.warehouseId(tran.getWarehouseId())
												.companyId(tran.getCompanyId())
												.updateBy(tran.getUserName())
												.updateTime(new Date())
												.inboundDate(new Date())
												.inboundHeaderId(inboundDetail.getInboundHeaderId())
												.build();
		inboundHeaderService.modify(new InboundVO(updateHeader));
		
		return inboundDetail;
		
	}
	
	private Boolean insertTransaction(InventoryTranVO tran, InventoryTranDetailVO detail) {
		String userName = tran.getUserName();
		InventoryTransactionTEntity transaction = InventoryTransactionTEntity.builder()
													.warehouseId(tran.getWarehouseId())
													.companyId(tran.getCompanyId())
													.createTime(new Date())
													.updateTime(new Date())
													.transactionType(tran.getTransationType())
													.transactionDate(new Date())
													.sourceBillNumber(tran.getSouceBillNumber())
													.quantity(detail.getTranQuantity())
													.build();
					
		BeanUtils.copyBeanProp(transaction, detail, Boolean.FALSE);
		
		InventoryOnhandTEntity from = detail.getSourceInventoryOnhand();
		
		switch (tran.getTransationTypeEnum()) {
			case Inbound:
				transaction.setFromInventoryOnhandId(from.getInventoryOnhandId());
				transaction.setFromLocationCode(from.getLocationCode());
				transaction.setFromLotNumber(from.getLotNumber());
				transaction.setFromLpnNumber(from.getLpnNumber());
				transaction.setFromOwnerCode(from.getOwnerCode());
				transaction.setFromSkuAlias(from.getSkuAlias());
				transaction.setFromSkuCode(from.getSkuCode());
				break;
			case Outbound:
				break;
			default:
			break;
		}
		transaction.setUpdateBy(userName);
		transaction.setCreateBy(userName);
		transactionService.add(transaction);
		
		return Boolean.TRUE;
	}

}
