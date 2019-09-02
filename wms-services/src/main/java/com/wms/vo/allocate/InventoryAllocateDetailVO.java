package com.wms.vo.allocate;

import java.math.BigDecimal;
import java.util.List;

import com.google.common.collect.Lists;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.AllocateStrategyDetailTEntity;
import com.wms.entity.auto.OutboundDetailTEntity;
import com.wms.vo.InventoryOnhandVO;

public class InventoryAllocateDetailVO extends OutboundDetailTEntity{

	private List<AllocateStrategyDetailTEntity> strategys;
	private AllocateStrategyDetailTEntity strategy;
	private String allocateStrategyType;
	private List<InventoryOnhandVO> inventoryList = Lists.newArrayList();
	private String error;
	private Long allocateBatchId;
	
	public String getAllocateStrategyType() {
		return allocateStrategyType;
	}
	public void setAllocateStrategyType(String allocateType) {
		this.allocateStrategyType = allocateType;
	}
	public List<InventoryOnhandVO> getInventoryList() {
		return inventoryList;
	}
	public void setInventoryList(List<InventoryOnhandVO> inventoryList) {
		this.inventoryList = inventoryList;
	}
	public List<AllocateStrategyDetailTEntity> getStrategys() {
		return strategys;
	}
	public void setStrategys(List<AllocateStrategyDetailTEntity> strategys) {
		this.strategys = strategys;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getError() {
		return error;
	}
	public AllocateStrategyDetailTEntity getStrategy() {
		return strategy;
	}
	public void setStrategy(AllocateStrategyDetailTEntity strategy) {
		this.strategy = strategy;
	}
	
	public InventoryAllocateDetailVO(){
		this.setQuantityAllocated(BigDecimal.ZERO);
		this.setQuantityExpected(BigDecimal.ZERO);
		this.setQuantityOrder(BigDecimal.ZERO);
		this.setQuantityPicked(BigDecimal.ZERO);
		this.setQuantityShiped(BigDecimal.ZERO);
	}
	
	public InventoryAllocateDetailVO(OutboundDetailTEntity detail){
		BeanUtils.copyBeanProp(this, detail);
	}
	public Long getAllocateBatchId() {
		return allocateBatchId;
	}
	public void setAllocateBatchId(Long allocateBatchId) {
		this.allocateBatchId = allocateBatchId;
	}
	
}
