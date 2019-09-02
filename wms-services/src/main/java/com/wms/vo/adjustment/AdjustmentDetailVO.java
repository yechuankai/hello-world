package com.wms.vo.adjustment;

import static java.math.BigDecimal.ROUND_FLOOR;

import java.math.BigDecimal;

import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.InboundDetailTEntity;
import com.wms.entity.auto.InventoryAdjustmentDetailTEntity;

public class AdjustmentDetailVO extends InventoryAdjustmentDetailTEntity {

	private String skuDescr;
	private String transactionCategory;
	private BigDecimal uomQuantityAdjustment = BigDecimal.ZERO;
    private BigDecimal uomQuantityOnhand = BigDecimal.ZERO;
	
	public String getSkuDescr() {
		return skuDescr;
	}

	public void setSkuDescr(String skuDescr) {
		this.skuDescr = skuDescr;
	}

	public String getTransactionCategory() {
		return transactionCategory;
	}

	public void setTransactionCategory(String transactionCategory) {
		this.transactionCategory = transactionCategory;
	}

	public BigDecimal getUomQuantityAdjustment() {
		return uomQuantityAdjustment;
	}

	public void setUomQuantityAdjustment(BigDecimal uomQuantityAdjustment) {
		this.uomQuantityAdjustment = uomQuantityAdjustment;
	}

	public BigDecimal getUomQuantityOnhand() {
		return uomQuantityOnhand;
	}

	public void setUomQuantityOnhand(BigDecimal uomQuantityOnhand) {
		this.uomQuantityOnhand = uomQuantityOnhand;
	}
	
	
	public AdjustmentDetailVO(){}
	
	public AdjustmentDetailVO(InventoryAdjustmentDetailTEntity adjustmentDetail){
		BeanUtils.copyBeanProp(this, adjustmentDetail, Boolean.TRUE);
		if (getUomQuantity() != null && getUomQuantity().compareTo(BigDecimal.ZERO) > 0) {
			this.setUomQuantityOnhand(this.getQuantityOnhand().divide(this.getUomQuantity(),5,ROUND_FLOOR));
			this.setUomQuantityAdjustment(this.getQuantityAdjustment().divide(this.getUomQuantity(),5,ROUND_FLOOR));
		}
	}
	
	
}
