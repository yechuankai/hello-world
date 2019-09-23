package com.wms.vo.inventory;

import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.EntInventoryOnhandTEntity;

import java.math.BigDecimal;

/**
 * @description: 企业级库存VO
 * @author: pengzhen@cmhit.com
 * @create: 2019-08-29 16:40
 **/

public class EntInventoryOnhandVO extends EntInventoryOnhandTEntity {
	private BigDecimal quantityExpected;

	private BigDecimal quantityAvailable;

	private String warehouseCode;

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public BigDecimal getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(BigDecimal quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	public EntInventoryOnhandVO() {
	}

	public EntInventoryOnhandVO(EntInventoryOnhandTEntity entInventoryOnhandTEntity) {
		BeanUtils.copyBeanProp(this, entInventoryOnhandTEntity);
		BigDecimal quantityAvailable = entInventoryOnhandTEntity.getQuantityOnhand()
				.subtract(entInventoryOnhandTEntity.getQuantityAllocated())
				.subtract(entInventoryOnhandTEntity.getQuantityLocked());
		this.quantityAvailable = quantityAvailable;
	}

	public BigDecimal getQuantityExpected() {
		return quantityExpected;
	}

	public void setQuantityExpected(BigDecimal quantityExpected) {
		this.quantityExpected = quantityExpected;
	}
}
