package com.wms.services.inventory.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.AdjustmentStatusEnum;
import com.wms.common.enums.OrderNumberTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IInventoryAdjustmentHeaderTDao;
import com.wms.dao.example.InventoryAdjustmentHeaderTExample;
import com.wms.entity.auto.InventoryAdjustmentHeaderTEntity;
import com.wms.services.inventory.IAdjustmentService;
import com.wms.vo.adjustment.AdjustmentVO;

@Service
public class AdjustmentServiceImpl implements IAdjustmentService {

	@Autowired
	private IInventoryAdjustmentHeaderTDao adjustmentDao;
	
	@Override
	public List<InventoryAdjustmentHeaderTEntity> find(PageRequest request) throws BusinessServiceException {
		InventoryAdjustmentHeaderTExample TExample = new InventoryAdjustmentHeaderTExample();
		InventoryAdjustmentHeaderTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(InventoryAdjustmentHeaderTEntity.Column.class, InventoryAdjustmentHeaderTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		List<InventoryAdjustmentHeaderTEntity> adjustmentList = adjustmentDao.selectByExample(TExample);
		
		return adjustmentList;
		
	}

	@Override
	public InventoryAdjustmentHeaderTEntity find(InventoryAdjustmentHeaderTEntity adjustment)
			throws BusinessServiceException {
		InventoryAdjustmentHeaderTExample TExample = new InventoryAdjustmentHeaderTExample();
		InventoryAdjustmentHeaderTExample.Criteria criteria = TExample.createCriteria();
		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(adjustment.getWarehouseId())
		.andCompanyIdEqualTo(adjustment.getCompanyId());
		
		int conditionCount = 0;
		
		if (adjustment.getInventoryAdjustmentNumber() != null) {
			criteria.andInventoryAdjustmentNumberEqualTo(adjustment.getInventoryAdjustmentNumber());
			conditionCount ++;
		}
		if (adjustment.getInventoryAdjustmentId() != null) {
			criteria.andInventoryAdjustmentIdEqualTo(adjustment.getInventoryAdjustmentId());
			conditionCount ++;
		}
		if (conditionCount == 0)
			return null;
		
		InventoryAdjustmentHeaderTEntity adjustmentDetail = adjustmentDao.selectOneByExample(TExample);
		if (adjustmentDetail == null) 
			throw new BusinessServiceException("AdjustmentServiceImpl", "adjustment.not.exists" , new Object[] {adjustment.getInventoryAdjustmentId() + "/" + adjustment.getInventoryAdjustmentNumber()});
		
		return adjustmentDetail;
	}

	@Override
	@Transactional
	public Boolean modify(InventoryAdjustmentHeaderTEntity adjustment) throws BusinessServiceException {
		
		InventoryAdjustmentHeaderTEntity selectAdjustment = find(adjustment);
		
		InventoryAdjustmentHeaderTExample example = new InventoryAdjustmentHeaderTExample();
		example.createCriteria()
		.andWarehouseIdEqualTo(adjustment.getWarehouseId())
		.andCompanyIdEqualTo(adjustment.getCompanyId())
		.andInventoryAdjustmentIdEqualTo(adjustment.getInventoryAdjustmentId());
		
		adjustment.setUpdateTime(new Date());
		
		int rowcount = adjustmentDao.updateWithVersionByExampleSelective(selectAdjustment.getUpdateVersion(), adjustment, example);
		if (rowcount == 0)
			throw new BusinessServiceException("record add error.");
		
		return Boolean.TRUE;
	}

	@Override
	public Boolean add(AjaxRequest<AdjustmentVO> request) throws BusinessServiceException {
		AdjustmentVO adjustment = request.getData();
		adjustment.setCompanyId(request.getCompanyId());
		adjustment.setWarehouseId(request.getWarehouseId());
		adjustment.setCreateBy(request.getUserName());
		adjustment.setUpdateBy(request.getUserName());
		adjustment.setCreateTime(new Date());
		adjustment.setUpdateTime(new Date());
		adjustment.setInventoryAdjustmentId(KeyUtils.getUID());
		String adjustmentNumber = KeyUtils.getOrderNumber(request.getCompanyId(), request.getWarehouseId(), OrderNumberTypeEnum.Adjustment);
		adjustment.setInventoryAdjustmentNumber(adjustmentNumber);
		
		int rowcount = adjustmentDao.insertSelective(adjustment);
		if (rowcount == 0)
			throw new BusinessServiceException("record add error.");
		
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean delete(AjaxRequest<List<AdjustmentVO>> request) throws BusinessServiceException {
		List<AdjustmentVO> list = request.getData();
		if (CollectionUtils.isEmpty(list))
			throw new BusinessServiceException("no data.");
		
		list.forEach(adjustment -> {
			
			adjustment.setCompanyId(request.getCompanyId());
			adjustment.setWarehouseId(request.getWarehouseId());
			
			InventoryAdjustmentHeaderTEntity header = find(adjustment);
			
			if (AdjustmentStatusEnum.Adjustmented.getCode().equals(header.getStatus()))
				throw new BusinessServiceException("AdjustmentServiceImpl", "adjustment.status.not.process" ,new Object[] {adjustment.getInventoryAdjustmentNumber()});
				
			InventoryAdjustmentHeaderTExample example = new InventoryAdjustmentHeaderTExample();
			example.createCriteria()
			.andWarehouseIdEqualTo(adjustment.getWarehouseId())
			.andCompanyIdEqualTo(adjustment.getCompanyId())
			.andInventoryAdjustmentIdEqualTo(adjustment.getInventoryAdjustmentId());
			
			InventoryAdjustmentHeaderTEntity updateHeader = InventoryAdjustmentHeaderTEntity.builder()
																.warehouseId(request.getWarehouseId())
																.companyId(request.getCompanyId())
																.delFlag(YesNoEnum.Yes.getCode())
																.updateBy(request.getUserName())
																.updateTime(new Date())
																.build();
			
			int rowcount = adjustmentDao.updateWithVersionByExampleSelective(header.getUpdateVersion(), updateHeader, example);
			if (rowcount == 0)
				throw new BusinessServiceException("record delete error.");
			
		});
		return Boolean.TRUE;
	}
	
	@Override
	public Boolean modify(AjaxRequest<List<AdjustmentVO>> request) throws BusinessServiceException {
		List<AdjustmentVO> list = request.getData();
		if (CollectionUtils.isEmpty(list))
			throw new BusinessServiceException("no data.");
		
		list.forEach(adjustment -> {
			
			//验证是否存在单据
			find(adjustment);
			
			InventoryAdjustmentHeaderTEntity updateHeader = InventoryAdjustmentHeaderTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.inventoryAdjustmentId(adjustment.getInventoryAdjustmentId())
					.sourceNumber(adjustment.getSourceNumber())
					.referenceNumber(adjustment.getReferenceNumber())
					.remark(adjustment.getRemark())
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.build();
			modify(updateHeader);
		});
		
		return Boolean.TRUE;
	}

}
