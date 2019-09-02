package com.wms.vo.allocate;

import com.wms.vo.InventoryOnhandVO;

public class AllocateInventoryVO extends InventoryOnhandVO{

	private String allocateType;
	private Long allocateStrategyId;
    private String allocateStrategyCode;
    private String allocateStrategyType;
	
	public AllocateInventoryVO(){
		super();
	}

	public String getAllocateType() {
		return allocateType;
	}

	public void setAllocateType(String allocateType) {
		this.allocateType = allocateType;
	}

	public Long getAllocateStrategyId() {
		return allocateStrategyId;
	}

	public void setAllocateStrategyId(Long allocateStrategyId) {
		this.allocateStrategyId = allocateStrategyId;
	}

	public String getAllocateStrategyCode() {
		return allocateStrategyCode;
	}

	public void setAllocateStrategyCode(String allocateStrategyCode) {
		this.allocateStrategyCode = allocateStrategyCode;
	}

	public String getAllocateStrategyType() {
		return allocateStrategyType;
	}

	public void setAllocateStrategyType(String allocateStrategyType) {
		this.allocateStrategyType = allocateStrategyType;
	}
	
	
	
}
