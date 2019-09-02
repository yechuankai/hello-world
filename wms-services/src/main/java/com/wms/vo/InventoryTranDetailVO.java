package com.wms.vo;

import java.math.BigDecimal;

import com.wms.entity.auto.InventoryOnhandTEntity;
import com.wms.entity.auto.LotAttributeTEntity;
import com.wms.entity.auto.LpnTEntity;
import com.wms.vo.allocate.AllocateVO;
import com.wms.vo.inbound.InboundDetailVO;

public class InventoryTranDetailVO extends InventoryOnhandTEntity {

	private String transactionCategory;
	private Long sequence;
	private InboundDetailVO inboundDetail;
	private AllocateVO allocate;
	private LotAttributeTEntity lotAttribute;
	private LpnTEntity lpn;
	private InventoryOnhandTEntity sourceInventoryOnhand = new InventoryOnhandTEntity();
	private BigDecimal tranQuantity = BigDecimal.ZERO;
	private String packCode;
	private String uom;
	private BigDecimal uomQuantity = BigDecimal.ZERO;
	private String sourceNumber;
	private String sourceLineNumber;
	
	public InboundDetailVO getInboundDetail() {
		return inboundDetail;
	}
	public void setInboundDetail(InboundDetailVO inboundDetail) {
		this.inboundDetail = inboundDetail;
	}
	public AllocateVO getAllocate() {
		return allocate;
	}
	public void setAllocate(AllocateVO allocate) {
		this.allocate = allocate;
	}
	public LotAttributeTEntity getLotAttribute() {
		return lotAttribute;
	}
	public void setLotAttribute(LotAttributeTEntity lotAttribute) {
		this.lotAttribute = lotAttribute;
	}
	public InventoryOnhandTEntity getSourceInventoryOnhand() {
		return sourceInventoryOnhand;
	}
	public void setSourceInventoryOnhand(InventoryOnhandTEntity sourceInventoryOnhand) {
		this.sourceInventoryOnhand = sourceInventoryOnhand;
	}
	public BigDecimal getTranQuantity() {
		return tranQuantity;
	}
	public void setTranQuantity(BigDecimal tranQuantity) {
		this.tranQuantity = tranQuantity;
	}
	public String getPackCode() {
		return packCode;
	}
	public void setPackCode(String packCode) {
		this.packCode = packCode;
	}
	public LpnTEntity getLpn() {
		return lpn;
	}
	public void setLpn(LpnTEntity lpn) {
		this.lpn = lpn;
	}
	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}
	public Long getSequence() {
		return sequence;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public BigDecimal getUomQuantity() {
		return uomQuantity;
	}
	public void setUomQuantity(BigDecimal uomQuantity) {
		this.uomQuantity = uomQuantity;
	}
	public String getSourceNumber() {
		return sourceNumber;
	}
	public void setSourceNumber(String sourceNumber) {
		this.sourceNumber = sourceNumber;
	}
	public String getSourceLineNumber() {
		return sourceLineNumber;
	}
	public void setSourceLineNumber(String sourceLineNumber) {
		this.sourceLineNumber = sourceLineNumber;
	}
	public String getTransactionCategory() {
		return transactionCategory;
	}
	public void setTransactionCategory(String transactionCategory) {
		this.transactionCategory = transactionCategory;
	}
	
}
