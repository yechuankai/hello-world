package com.wms.services.core.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.dao.auto.IInventoryOnhandTDao;
import com.wms.dao.example.InventoryOnhandTExample;
import com.wms.entity.auto.*;
import com.wms.services.base.ILocationService;
import com.wms.services.base.IPutawayStrategyDetailService;
import com.wms.services.base.ISkuService;
import com.wms.services.core.IPutawayCoreService;
import com.wms.services.inventory.ILpnService;
import com.wms.services.inventory.IPutawayLocationLockService;
import com.wms.services.strategy.putaway.IPutawayService;
import com.wms.vo.InventoryOnhandVO;
import com.wms.vo.PutawayVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PutawayCoreServiceImpl implements IPutawayCoreService, InitializingBean{

	//默认UNKNOWN库位
	public static final String UNKNOWN = "UNKNOWN";
	
	private Map<String, IPutawayService> strategyServiceMap = Maps.newHashMap();
	
	@Autowired
	private IPutawayStrategyDetailService putawayStrategyDetailService;
	@Autowired
	private ISkuService skuService;
	@Autowired
	private IInventoryOnhandTDao inventoryDao;
	@Autowired
	private ILpnService lpnService;
	@Autowired
	private IPutawayLocationLockService lockService;
	
	/**
	 * 加载策略逻辑类
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, IPutawayService> strategyServices = SpringUtils.getBeansOfType(IPutawayService.class);
		strategyServices.forEach((k, v) -> {
			strategyServiceMap.put(v.getPutawayType(), v);
		});
	}

	@Override
	public PutawayVO lpnPutaway(LpnTEntity lpn) throws BusinessServiceException {
		return lpnPutaway(lpn, null);
	}

	@Override
	public PutawayVO inventoryPutaway(InventoryOnhandVO inventory) throws BusinessServiceException {
		return inventoryPutaway(inventory, null);
	}

	@Override
	public PutawayVO lpnPutaway(LpnTEntity lpn, PutawayStrategyTEntity strategy) throws BusinessServiceException {
		//查询是否存在锁定库存
		List<PutawayLocationLockTEntity> locks = lockService.find(lpn);
		if (CollectionUtils.isNotEmpty(locks)) {
			PutawayLocationLockTEntity lock = locks.get(0);
			
			PutawayVO putaway = new PutawayVO();
			putaway.setWarehouseId(lpn.getWarehouseId());
			putaway.setCompanyId(lpn.getCompanyId());
			putaway.setLpnNumber(lpn.getLpnNumber());
			putaway.setContainerNumber(lpn.getContainerNumber());
			putaway.setToLocationCode(lock.getLocationCode());
			
			//目前未存储库存行ID，无法获取更多信息
			InventoryOnhandVO onhand = new InventoryOnhandVO();
			onhand.setSkuCode(lock.getSkuCode());
			onhand.setLocationCode(lock.getLocationCode());
			putaway.setInventoryOnhand(onhand);
			
			return putaway;
		}
		
		//查询库存
		InventoryOnhandVO inventory = new InventoryOnhandVO();
		BeanUtils.copyBeanProp(inventory, lpn);
		List<InventoryOnhandTEntity> inventoryList = getInventory(inventory);
		if (CollectionUtils.isEmpty(inventoryList))
			throw new BusinessServiceException("PutawayCoreServiceImpl", "inventory.not.exists", new Object[] {lpn.getLpnNumber()});
		
		InventoryOnhandTEntity onhand = inventoryList.get(0);
		InventoryOnhandVO onhandVo = new InventoryOnhandVO(onhand);
		PutawayVO putaway = new PutawayVO();
		putaway.setWarehouseId(inventory.getWarehouseId());
		putaway.setCompanyId(inventory.getCompanyId());
		putaway.setInventoryOnhand(onhandVo);
		putaway.setLpnNumber(lpn.getLpnNumber());
		putaway.setContainerNumber(lpn.getContainerNumber());
		putaway.setCreateBy(lpn.getCreateBy());
		if (strategy == null) {
			SkuTEntity sku = getSku(onhandVo);
			strategy = PutawayStrategyTEntity.builder()
							.warehouseId(sku.getWarehouseId())
							.companyId(sku.getCompanyId())
							.putawayStrategyId(sku.getPutawayStrategyId())
							.putawayStrategyCode(sku.getPutawayZoneCode())
							.build();
		}
		return putaway(putaway, strategy);
	}

	@Override
	public PutawayVO inventoryPutaway(InventoryOnhandVO inventory, PutawayStrategyTEntity strategy)
			throws BusinessServiceException {
		if (strategy == null) {
			SkuTEntity sku = getSku(inventory);
			strategy = PutawayStrategyTEntity.builder()
							.warehouseId(sku.getWarehouseId())
							.companyId(sku.getCompanyId())
							.putawayStrategyId(sku.getPutawayStrategyId())
							.putawayStrategyCode(sku.getPutawayZoneCode())
							.build();
		}
		PutawayVO putaway = new PutawayVO();
		putaway.setWarehouseId(inventory.getWarehouseId());
		putaway.setCompanyId(inventory.getCompanyId());
		putaway.setInventoryOnhand(inventory);
		putaway.setCreateBy(inventory.getCreateBy());
		return putaway(putaway, strategy);
	}
	
	protected PutawayVO putaway(PutawayVO putaway, PutawayStrategyTEntity strategy) {
		//获取上架策略明细
		List<PutawayStrategyDetailTEntity> strategyDetail = getStrategyDetail(strategy);
		if (CollectionUtils.isEmpty(strategyDetail)) {
			putaway.setToLocationCode(UNKNOWN);
			return putaway;
		}
		
		Iterator<PutawayStrategyDetailTEntity> itertor = strategyDetail.iterator();
		while(itertor.hasNext()) {
			PutawayStrategyDetailTEntity detail = itertor.next();
			putaway.setStrategy(detail);
			
			IPutawayService putawayService = strategyServiceMap.get(detail.getType());
			if (putawayService == null)
				continue;
			
			putawayService.findPutawayLoc(putaway);
			if (StringUtils.isNotEmpty(putaway.getToLocationCode()))
				return putaway;
		}
		
		if (StringUtils.isEmpty(putaway.getToLocationCode()))
			putaway.setToLocationCode(UNKNOWN);
		
		return putaway;
	}
	
	
	//获取上架策略
	protected List<PutawayStrategyDetailTEntity> getStrategyDetail(PutawayStrategyTEntity strategy) {
		List<PutawayStrategyDetailTEntity> strategyList = putawayStrategyDetailService.findByPutawayStrategy(PutawayStrategyTEntity.builder()
																				.companyId(strategy.getCompanyId())
																				.warehouseId(strategy.getWarehouseId())
																				.putawayStrategyId(strategy.getPutawayStrategyId())
																				.putawayStrategyCode(strategy.getPutawayStrategyCode())
																				.build());
		strategyList = strategyList.stream().filter(v-> 
						YesNoEnum.Yes.getCode().equals(v.getActive())
					).sorted(Comparator.comparing(PutawayStrategyDetailTEntity::getLineNumber)).collect(Collectors.toList());
		return strategyList;
	}
	
	protected SkuTEntity getSku(InventoryOnhandVO inventory) {
		SkuTEntity sku = skuService.find(SkuTEntity.builder()
				.warehouseId(inventory.getWarehouseId())
				.companyId(inventory.getCompanyId())
				.skuCode(inventory.getSkuCode())
				.skuId(inventory.getSkuId())
				.ownerCode(inventory.getOwnerCode())
				.build());
		return sku;
	}
	
	protected List<InventoryOnhandTEntity> getInventory(InventoryOnhandVO inventory) {
		
		InventoryOnhandTExample example = new InventoryOnhandTExample();
		InventoryOnhandTExample.Criteria criteria = example.createCriteria();
		criteria
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(inventory.getWarehouseId())
		.andCompanyIdEqualTo(inventory.getCompanyId());
		
		if (inventory.getOwnerId() != null)
			criteria.andOwnerIdEqualTo(inventory.getOwnerId());
		
		if (StringUtils.isNotEmpty(inventory.getOwnerCode()))
			criteria.andOwnerCodeEqualTo(inventory.getOwnerCode());
		
		if (inventory.getSkuId() != null)
			criteria.andSkuIdEqualTo(inventory.getSkuId());

		if (StringUtils.isNotEmpty(inventory.getOwnerCode()))
			criteria.andSkuCodeEqualTo(inventory.getSkuCode());
		
		if (inventory.getLocationId() != null)
			criteria.andLocationIdEqualTo(inventory.getLocationId());

		if (StringUtils.isNotEmpty(inventory.getLocationCode()))
			criteria.andLocationCodeEqualTo(inventory.getLocationCode());
		
		if (inventory.getLotId() != null)
			criteria.andLotIdEqualTo(inventory.getLotId());

		if (StringUtils.isNotEmpty(inventory.getLotNumber()))
			criteria.andLotNumberEqualTo(inventory.getLotNumber());
		
		if (inventory.getLpnId() != null)
			criteria.andLpnIdEqualTo(inventory.getLpnId());
		
		if (StringUtils.isNotEmpty(inventory.getLpnNumber()))	
			criteria.andLpnNumberEqualTo(inventory.getLpnNumber());

		if (StringUtils.isNotEmpty(inventory.getContainerNumber()))	{
			//根据容器号获取LPN
			List<LpnTEntity> lpns = lpnService.findByContainerNumber(LpnTEntity.builder()
																		.warehouseId(inventory.getWarehouseId())
																		.companyId(inventory.getCompanyId())
																		.containerNumber(inventory.getContainerNumber())
																		.build());
			
			if (CollectionUtils.isNotEmpty(lpns)) {
				Set<Long> lpnIds = lpns.stream().map(LpnTEntity::getLpnId).collect(Collectors.toSet());
				criteria.andLpnIdIn(Lists.newArrayList(lpnIds));
			}
		}
		//库存大于0
		criteria.andQuantityOnhandGreaterThan(BigDecimal.ZERO);
		
		List<InventoryOnhandTEntity> selectInventory = inventoryDao.selectByExample(example);
		
		return selectInventory;
	}

}
