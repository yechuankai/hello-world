package com.wms.vo;

import java.math.BigDecimal;

import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.InventoryOnhandTEntity;

public class InventoryLockedVO extends InventoryOnhandVO {
	
	private Long inventoryLockedId;

    private BigDecimal quantityLocked;

    private String lockFlag;

    private String reason;

    private String remark;

	
	public Long getInventoryLockedId() {
		return inventoryLockedId;
	}

	public void setInventoryLockedId(Long inventoryLockedId) {
		this.inventoryLockedId = inventoryLockedId;
	}

	public BigDecimal getQuantityLocked() {
		return quantityLocked;
	}

	public void setQuantityLocked(BigDecimal quantityLocked) {
		this.quantityLocked = quantityLocked;
	}

	public String getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	public InventoryLockedVO () {}
	
	public InventoryLockedVO (InventoryOnhandTEntity onhand) {
		BeanUtils.copyBeanProp(this, onhand);
	}
    
    
	
}
