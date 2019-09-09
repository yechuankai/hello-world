package com.wms.vo.outbound;

import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.OutboundDetailTEntity;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_FLOOR;

public class OutboundDetailVO extends OutboundDetailTEntity {

	private String outboundNumber;
	private String skuDescr;
	private BigDecimal uomQuantityExpected = BigDecimal.ZERO;
	private BigDecimal uomQuantityOrder = BigDecimal.ZERO;
	private BigDecimal uomQuantityAllocated = BigDecimal.ZERO;
	private BigDecimal uomQuantityPicked = BigDecimal.ZERO;
	private BigDecimal uomQuantityShiped = BigDecimal.ZERO;
	
	public String getSkuDescr() {
		return skuDescr;
	}

	public void setSkuDescr(String skuDescr) {
		this.skuDescr = skuDescr;
	}
	
	public OutboundDetailVO(){}
	
	public OutboundDetailVO(OutboundDetailTEntity outboundDetail){
		BeanUtils.copyBeanProp(this, outboundDetail, Boolean.TRUE);
		if (getUomQuantity() != null && getUomQuantity().compareTo(BigDecimal.ZERO) > 0) {
			this.setUomQuantityOrder(this.getQuantityOrder().divide(this.getUomQuantity()));
			this.setUomQuantityExpected(this.getQuantityExpected().divide(this.getUomQuantity()));
			this.setUomQuantityAllocated(this.getQuantityAllocated().divide(this.getUomQuantity(),5,ROUND_FLOOR));
			this.setUomQuantityPicked(this.getQuantityPicked().divide(this.getUomQuantity(),5,ROUND_FLOOR));
			this.setUomQuantityShiped(this.getQuantityShiped().divide(this.getUomQuantity(),5,ROUND_FLOOR));
		}
	}

	public BigDecimal getUomQuantityExpected() {
		return uomQuantityExpected;
	}

	public void setUomQuantityExpected(BigDecimal uomQuantityExpected) {
		this.uomQuantityExpected = uomQuantityExpected;
	}

	public BigDecimal getUomQuantityOrder() {
		return uomQuantityOrder;
	}

	public void setUomQuantityOrder(BigDecimal uomQuantityOrder) {
		this.uomQuantityOrder = uomQuantityOrder;
	}

	public BigDecimal getUomQuantityAllocated() {
		return uomQuantityAllocated;
	}

	public void setUomQuantityAllocated(BigDecimal uomQuantityAllocated) {
		this.uomQuantityAllocated = uomQuantityAllocated;
	}

	public BigDecimal getUomQuantityPicked() {
		return uomQuantityPicked;
	}

	public void setUomQuantityPicked(BigDecimal uomQuantityPicked) {
		this.uomQuantityPicked = uomQuantityPicked;
	}

	public BigDecimal getUomQuantityShiped() {
		return uomQuantityShiped;
	}

	public void setUomQuantityShiped(BigDecimal uomQuantityShiped) {
		this.uomQuantityShiped = uomQuantityShiped;
	}

	public String getOutboundNumber() {
		return outboundNumber;
	}

	public void setOutboundNumber(String outboundNumber) {
		this.outboundNumber = outboundNumber;
	}

}
