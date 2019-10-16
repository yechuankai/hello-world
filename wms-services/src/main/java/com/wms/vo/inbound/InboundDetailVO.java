package com.wms.vo.inbound;

import com.wms.common.enums.OperatorTypeEnum;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.InboundDetailTEntity;
import com.wms.entity.auto.LotValidateTEntity;
import com.wms.entity.auto.PackTEntity;
import com.wms.entity.auto.SkuTEntity;
import com.wms.vo.LotLabelVO;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_FLOOR;

public class InboundDetailVO extends InboundDetailTEntity {

	private String noSku;
	private String transactionCategory;
	private BigDecimal uomQuantityExpected = BigDecimal.ZERO;
    private BigDecimal uomQuantityReceive = BigDecimal.ZERO;
    private BigDecimal uomQuantityCancel = BigDecimal.ZERO;
    private BigDecimal tranQuantity = BigDecimal.ZERO;
    private PackTEntity pack;
    private SkuTEntity sku;
    private LotLabelVO lotLabel;
    private LotValidateTEntity lotv;
	private String inboundNumber;
	private OperatorTypeEnum operatorType;
	
	void initZero(){
		if (getWeightGross() == null)
			setWeightGross(BigDecimal.ZERO);
		
		if (getWeightNet() == null)
			setWeightNet(BigDecimal.ZERO);
		
		if (getWeightTare() == null)
			setWeightTare(BigDecimal.ZERO);
		
		if (getVolume() == null)
			setVolume(BigDecimal.ZERO);
	}

	public OperatorTypeEnum getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(OperatorTypeEnum operatorType) {
		this.operatorType = operatorType;
	}

	public InboundDetailVO(){
		initZero();
	}
	
	public InboundDetailVO(InboundDetailTEntity inboundDetail){
		BeanUtils.copyBeanProp(this, inboundDetail, Boolean.TRUE);
		if (getUomQuantity() != null && getUomQuantity().compareTo(BigDecimal.ZERO) > 0) {
			this.setUomQuantityCancel(this.getQuantityCancel().divide(this.getUomQuantity(),5,ROUND_FLOOR));
			this.setUomQuantityExpected(this.getQuantityExpected().divide(this.getUomQuantity(),5,ROUND_FLOOR));
			this.setUomQuantityReceive(this.getQuantityReceive().divide(this.getUomQuantity(),5,ROUND_FLOOR));
		}
		initZero();
	}
	
	public BigDecimal getUomQuantityExpected() {
		return uomQuantityExpected;
	}

	public void setUomQuantityExpected(BigDecimal uomQuantityExpected) {
		this.uomQuantityExpected = uomQuantityExpected;
	}

	public BigDecimal getUomQuantityReceive() {
		return uomQuantityReceive;
	}

	public void setUomQuantityReceive(BigDecimal uomQuantityReceive) {
		this.uomQuantityReceive = uomQuantityReceive;
	}

	public BigDecimal getUomQuantityCancel() {
		return uomQuantityCancel;
	}

	public void setUomQuantityCancel(BigDecimal uomQuantityCancel) {
		this.uomQuantityCancel = uomQuantityCancel;
	}

	public BigDecimal getTranQuantity() {
		return tranQuantity;
	}

	public void setTranQuantity(BigDecimal tranQuantity) {
		this.tranQuantity = tranQuantity;
	}

	public String getTransactionCategory() {
		return transactionCategory;
	}

	public void setTransactionCategory(String transactionCategory) {
		this.transactionCategory = transactionCategory;
	}

	public PackTEntity getPack() {
		return pack;
	}

	public void setPack(PackTEntity pack) {
		this.pack = pack;
	}

	public LotLabelVO getLotLabel() {
		return lotLabel;
	}

	public void setLotLabel(LotLabelVO lotLabel) {
		this.lotLabel = lotLabel;
	}

	public SkuTEntity getSku() {
		return sku;
	}

	public void setSku(SkuTEntity sku) {
		this.sku = sku;
	}

	public LotValidateTEntity getLotv() {
		return lotv;
	}

	public void setLotv(LotValidateTEntity lotv) {
		this.lotv = lotv;
	}

	public String getInboundNumber() {
		return inboundNumber;
	}

	public void setInboundNumber(String inboundNumber) {
		this.inboundNumber = inboundNumber;
	}

	public String getNoSku() {
		return noSku;
	}

	public void setNoSku(String noSku) {
		this.noSku = noSku;
	}

}
