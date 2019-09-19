package com.wms.services.inventory.impl;

import com.google.common.collect.Lists;
import com.wms.common.constants.DefaultConstants;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.AdjustmentStatusEnum;
import com.wms.common.enums.TransactionTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IInventoryAdjustmentDetailTDao;
import com.wms.dao.example.InventoryAdjustmentDetailTExample;
import com.wms.entity.auto.*;
import com.wms.services.base.ILocationService;
import com.wms.services.base.IOwnerService;
import com.wms.services.base.IPackService;
import com.wms.services.base.ISkuService;
import com.wms.services.core.IInventoryCoreService;
import com.wms.services.inventory.*;
import com.wms.vo.InventoryTranDetailVO;
import com.wms.vo.InventoryTranVO;
import com.wms.vo.adjustment.AdjustmentDetailVO;
import com.wms.vo.adjustment.AdjustmentVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdjustmentDetailServiceImpl implements IAdjustmentDetailService {

	@Autowired
	private IInventoryAdjustmentDetailTDao adjustmentDetailDao;
	
	@Autowired
	private IAdjustmentService adjustmentHeaderService;
	
	@Autowired
	private ISkuService skuService;
	
	@Autowired
	private IPackService packService;
	
	@Autowired
	private ILocationService locationService;
	
	@Autowired
	private ILotService lotService;
	
	@Autowired
	private IOwnerService ownerService;
	
	@Autowired
	private ILpnService lpnService;
	
	@Autowired
	private IInventoryService inventoryService;
	
	@Autowired
	private IInventoryCoreService inventoryCoreService;
	
	@Override
	public List<AdjustmentDetailVO> find(PageRequest request) throws BusinessServiceException {
		InventoryAdjustmentDetailTExample TExample = new InventoryAdjustmentDetailTExample();
		InventoryAdjustmentDetailTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(InventoryAdjustmentDetailTEntity.Column.class, InventoryAdjustmentDetailTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		List<InventoryAdjustmentDetailTEntity> adjustmentDetailList = adjustmentDetailDao.selectByExample(TExample);
		
		if (CollectionUtils.isEmpty(adjustmentDetailList)) 
			return null;
		
		Set<Long> skuids = adjustmentDetailList.stream().map(InventoryAdjustmentDetailTEntity::getSkuId).collect(Collectors.toSet());
		
		SkuTEntity selectSku = SkuTEntity.builder()
				.warehouseId(request.getWarehouseId())
				.companyId(request.getCompanyId())
				.build();
		
		List<SkuTEntity> skuList = skuService.findByIds(selectSku, skuids);
		Map<Long, SkuTEntity> skuMaps = skuList.stream().collect( 
			      Collectors.toMap(SkuTEntity::getSkuId, (s) -> s)); 
		
		List<AdjustmentDetailVO> returnList = Lists.newArrayList();
		adjustmentDetailList.forEach(d -> {
			
			AdjustmentDetailVO adjustmentDetailVO = new AdjustmentDetailVO(d);
			
			SkuTEntity sku = skuMaps.get(d.getSkuId());
			if (sku != null) 
				adjustmentDetailVO.setSkuDescr(sku.getSkuDescr());
			
			returnList.add(adjustmentDetailVO);
			
		});
		
		return returnList;
	}

	@Override
	public InventoryAdjustmentDetailTEntity find(InventoryAdjustmentDetailTEntity adjustment)
			throws BusinessServiceException {
		InventoryAdjustmentDetailTExample TExample = new InventoryAdjustmentDetailTExample();
		InventoryAdjustmentDetailTExample.Criteria criteria = TExample.createCriteria();
		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(adjustment.getWarehouseId())
		.andCompanyIdEqualTo(adjustment.getCompanyId());
		
		int conditionCount = 0;
		
		if (adjustment.getLineNumber() != null) {
			criteria.andLineNumberEqualTo(adjustment.getLineNumber());
			conditionCount ++;
		}
		if (adjustment.getInventoryAdjustmentId() != null) {
			criteria.andInventoryAdjustmentIdEqualTo(adjustment.getInventoryAdjustmentId());
			conditionCount ++;
		}
		if (adjustment.getInventoryAdjustmentDetailId() != null) {
			criteria.andInventoryAdjustmentDetailIdEqualTo(adjustment.getInventoryAdjustmentDetailId());
			conditionCount ++;
		}
		if (conditionCount == 0)
			return null;
		
		InventoryAdjustmentDetailTEntity adjustmentDetail = adjustmentDetailDao.selectOneByExample(TExample);
		if (adjustmentDetail == null) 
			throw new BusinessServiceException("AdjustmentDetailServiceImpl", "adjustment.line.not.exists" , new Object[] {adjustment.getInventoryAdjustmentDetailId()});
		
		return adjustmentDetail;
	}

	@Override
	@Transactional
	public AdjustmentVO save(AjaxRequest<AdjustmentVO> request) throws BusinessServiceException {
		AdjustmentVO adjustment = request.getData();
		
		adjustment.setWarehouseId(request.getWarehouseId());
		adjustment.setCompanyId(request.getCompanyId());
		adjustment.setCreateBy(request.getUserName());
		adjustment.setUpdateBy(request.getUserName());
		
		//为空时新建调整单
		if (adjustment.getInventoryAdjustmentId() == null) {
			adjustmentHeaderService.add(request);
		}else {
			InventoryAdjustmentHeaderTEntity header = adjustmentHeaderService.find(adjustment);
			BeanUtils.copyBeanProp(adjustment, header, Boolean.FALSE);
		}
			
		
		List<AdjustmentDetailVO> detail = adjustment.getDetail();
		
		List<InventoryTranDetailVO> tranDetail = Lists.newArrayList();
		
		detail.forEach(d -> {
			validate(adjustment, d);
			
			d.setCreateBy(request.getUserName());
			d.setCreateBy(request.getUserName());
			d.setCreateTime(new Date());
			d.setUpdateTime(new Date());
			d.setInventoryAdjustmentDetailId(KeyUtils.getUID());
			
			int rowcount = adjustmentDetailDao.insertSelective(d);
			if (rowcount == 0)
				throw new BusinessServiceException("record add error.");
			
			InventoryTranDetailVO tranDetailVo = new InventoryTranDetailVO();
			BeanUtils.copyBeanProp(tranDetailVo, d, Boolean.FALSE);
			tranDetailVo.setSourceNumber(String.valueOf(d.getInventoryAdjustmentDetailId()));
			tranDetailVo.setSourceLineNumber(String.valueOf(d.getLineNumber()));
			tranDetailVo.setTransactionCategory(d.getTransactionCategory());
			tranDetailVo.setTranQuantity(d.getQuantityAdjustment());
			tranDetail.add(tranDetailVo);
		});
		
		//扣减库存
		InventoryTranVO tran = new InventoryTranVO();
		tran.setSouceBillNumber(adjustment.getInventoryAdjustmentNumber());
		tran.setTransationType(TransactionTypeEnum.Adjustment.getCode());
		tran.setUserName(request.getUserName());
		tran.setWarehouseId(request.getWarehouseId());
		tran.setCompanyId(request.getCompanyId());
		tran.setDetail(tranDetail);
		
		//执行调整
		inventoryCoreService.adjustment(tran);
		
		//更新表头状态
		InventoryAdjustmentHeaderTEntity updateHeader = InventoryAdjustmentHeaderTEntity.builder()
															.warehouseId(request.getWarehouseId())
															.companyId(request.getCompanyId())
															.updateBy(request.getUserName())
															.inventoryAdjustmentId(adjustment.getInventoryAdjustmentId())
															.status(AdjustmentStatusEnum.Adjustmented.getCode())
															.build();
		adjustmentHeaderService.modify(updateHeader);
		
		return adjustment;
	}
	
	private Boolean validate(AdjustmentVO adjustment, AdjustmentDetailVO detail) {
		
		detail.setWarehouseId(adjustment.getWarehouseId());
		detail.setCompanyId(adjustment.getCompanyId());
		
		if (adjustment.getInventoryAdjustmentId() == null)
			throw new BusinessServiceException("AdjustmentDetailServiceImpl", "adjustment.header.isnull" , new Object[] {adjustment.getInventoryAdjustmentNumber()});
		else 
			detail.setInventoryAdjustmentId(adjustment.getInventoryAdjustmentId());
		
		if (detail.getLineNumber() == null)
			throw new BusinessServiceException("AdjustmentDetailServiceImpl", "adjustment.line.isnull" , new Object[] {adjustment.getInventoryAdjustmentNumber()});
		
		if (StringUtils.isEmpty(detail.getReason())) {
			throw new BusinessServiceException("AdjustmentDetailServiceImpl", "adjustment.reason.isnull" , new Object[] {detail.getLineNumber()});
		}
		
		if (detail.getOwnerId() == null && StringUtils.isEmpty(detail.getOwnerCode()))
			throw new BusinessServiceException("AdjustmentDetailServiceImpl", "adjustment.owner.isnull" , new Object[] {detail.getLineNumber()}); 
			
		if (detail.getSkuId() == null && StringUtils.isEmpty(detail.getSkuCode()))
			throw new BusinessServiceException("AdjustmentDetailServiceImpl", "adjustment.sku.isnull" , new Object[] {detail.getLineNumber()}); 
		
		if (StringUtils.isEmpty(detail.getLocationCode())) {
			throw new BusinessServiceException("AdjustmentDetailServiceImpl", "adjustment.location.isnull" , new Object[] {detail.getLineNumber()});
		} 
		
		if (detail.getLotId() == null && StringUtils.isEmpty(detail.getLotNumber())) {
			throw new BusinessServiceException("AdjustmentDetailServiceImpl", "adjustment.lot.isnull" , new Object[] {detail.getLineNumber()});
		}
		
		InventoryAdjustmentDetailTEntity selectDetail = null;
		try {
			selectDetail = find(detail);
		} catch (BusinessServiceException e) {}
		if (selectDetail != null)
			throw new BusinessServiceException("InboundDetailServiceImpl", "adjustment.linenumber.exists" , new Object[] {adjustment.getInventoryAdjustmentNumber(), detail.getLineNumber()});
		
		/*
		if (detail.getPackId() == null && StringUtils.isEmpty(detail.getPackCode()))
			throw new BusinessServiceException("AdjustmentDetailServiceImpl", "adjustment.pack.isnull" , new Object[] {detail.getLineNumber()}); 
		
		if (StringUtils.isEmpty(detail.getUom()))
			throw new BusinessServiceException("AdjustmentDetailServiceImpl", "adjustment.uom.isnull" , new Object[] {detail.getLineNumber()});
		*/
		
		if (detail.getPackId() != null || StringUtils.isNotEmpty(detail.getPackCode())) {
			PackTEntity pack = packService.find(PackTEntity.builder()
					.warehouseId(detail.getWarehouseId())
					.companyId(detail.getCompanyId())
					.packId(detail.getPackId())
					.packCode(detail.getPackCode())
					.build());
			detail.setPackCode(pack.getPackCode());
			detail.setPackId(pack.getPackId());
			
			BigDecimal uomQuantity = packService.getUOMQty(pack, detail.getUom());
			if (uomQuantity.compareTo(BigDecimal.ZERO) == 0)
				throw new BusinessServiceException("AdjustmentDetailServiceImpl", "adjustment.uom.error" , new Object[] {detail.getLineNumber(), detail.getUom()});
			
			detail.setUomQuantity(uomQuantity);
			
			if (detail.getUomQuantityAdjustment() != null && detail.getUomQuantityAdjustment().compareTo(BigDecimal.ZERO) > 0)
				detail.setQuantityAdjustment(detail.getUomQuantityAdjustment().multiply(uomQuantity));
		}
		
		if (detail.getQuantityAdjustment() == null)
			detail.setQuantityAdjustment(BigDecimal.ZERO);
		
		if (BigDecimal.ZERO.compareTo(detail.getQuantityAdjustment()) == 0)
			throw new BusinessServiceException("AdjustmentDetailServiceImpl", "adjustment.quantity.not.zero" , new Object[] {detail.getLineNumber()});

		OwnerTEntity owner = ownerService.find(OwnerTEntity.builder()
				.warehouseId(adjustment.getWarehouseId())
				.companyId(adjustment.getCompanyId())
				.ownerId(detail.getOwnerId())
				.ownerCode(detail.getOwnerCode())
				.build());
		detail.setOwnerCode(owner.getOwnerCode());
		detail.setOwnerId(owner.getOwnerId());

		SkuTEntity sku = skuService.find(SkuTEntity.builder()
				.warehouseId(adjustment.getWarehouseId())
				.companyId(adjustment.getCompanyId())
				.skuId(detail.getSkuId())
				.ownerCode(owner.getOwnerCode())
				.skuCode(detail.getSkuCode())
				.build());
		detail.setSkuId(sku.getSkuId());
		detail.setSkuAlias(sku.getSkuAlias());
		detail.setSkuCode(sku.getSkuCode());
		if (StringUtils.isEmpty(detail.getPackCode())) {
			detail.setPackCode(sku.getPackCode());
			detail.setUom(sku.getUom());
			detail.setUomQuantity(BigDecimal.ONE);
		}
		
		LotAttributeTEntity lot = lotService.find(LotAttributeTEntity.builder()
										.warehouseId(detail.getWarehouseId())
										.companyId(detail.getCompanyId())
										.lotAttributeId(detail.getLotId())
										.lotNumber(detail.getLotNumber())
										.build());
		detail.setLotId(lot.getLotAttributeId());
		detail.setLotNumber(lot.getLotNumber());
		
		LocationTEntity loc = locationService.find(LocationTEntity.builder()
									.warehouseId(detail.getWarehouseId())
									.companyId(detail.getCompanyId())
									.locationId(detail.getLocationId())
									.locationCode(detail.getLocationCode())
									.build());
		detail.setLocationId(loc.getLocationId());
		detail.setLocationCode(loc.getLocationCode());
		
		if (StringUtils.isNotEmpty(detail.getLpnNumber())) {
			detail.setLpnNumber(detail.getLpnNumber().toUpperCase());
			LpnTEntity lpn = lpnService.find(LpnTEntity.builder()
									.warehouseId(detail.getWarehouseId())
									.companyId(detail.getCompanyId())
									.lpnNumber(detail.getLpnNumber())
									.lpnId(detail.getLpnId())
									.build());
			detail.setLpnNumber(lpn.getLpnNumber());
			detail.setLpnId(lpn.getLpnId());
		}
		
		//验证库存
		InventoryOnhandTEntity onhand = new InventoryOnhandTEntity();
		BeanUtils.copyBeanProp(onhand, detail, Boolean.FALSE);
		InventoryOnhandTEntity selectOnhand = inventoryService.findByLogicalKey(onhand);
		detail.setInventoryOnhandId(selectOnhand.getInventoryOnhandId());
		
		detail.setQuantityOnhand(selectOnhand.getQuantityOnhand());
		
		return Boolean.TRUE;
	}

	@Override
	public Long findMaxLine(InventoryAdjustmentDetailTEntity detail) throws BusinessServiceException {
		if (detail.getInventoryAdjustmentId() == null)
			return DefaultConstants.LINE_INCREMENT;
		
		List<InventoryAdjustmentDetailTEntity> allDetail = findByHeaderId(detail);
		if (CollectionUtils.isEmpty(allDetail))
			return DefaultConstants.LINE_INCREMENT;
		
		InventoryAdjustmentDetailTEntity maxLine = allDetail.stream().max(new Comparator<InventoryAdjustmentDetailTEntity>() {
			@Override
			public int compare(InventoryAdjustmentDetailTEntity o1, InventoryAdjustmentDetailTEntity o2) {
				return o1.getLineNumber().compareTo(o2.getLineNumber());
			}
		}).get();
		
		long maxLineNumber = maxLine.getLineNumber() + DefaultConstants.LINE_INCREMENT;
		
		return maxLineNumber;
	}

	@Override
	public List<InventoryAdjustmentDetailTEntity> findByHeaderId(InventoryAdjustmentDetailTEntity detail)
			throws BusinessServiceException {
		InventoryAdjustmentDetailTExample TExample = new InventoryAdjustmentDetailTExample();
		InventoryAdjustmentDetailTExample.Criteria criteria = TExample.createCriteria();
		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(detail.getWarehouseId())
		.andCompanyIdEqualTo(detail.getCompanyId())
		.andInventoryAdjustmentIdEqualTo(detail.getInventoryAdjustmentId());
		
		List<InventoryAdjustmentDetailTEntity> adjustmentDetail = adjustmentDetailDao.selectByExample(TExample);
		
		return  adjustmentDetail;
	}

}
