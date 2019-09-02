package com.wms.vo.allocate;

import java.math.BigDecimal;
import java.util.Date;

import com.wms.common.enums.YesNoEnum;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.AllocateShortTEntity;
import com.wms.entity.auto.AllocateTEntity;
import com.wms.entity.auto.LocationTEntity;
import com.wms.entity.auto.LotValidateTEntity;
import com.wms.entity.auto.PackTEntity;
import com.wms.entity.auto.SkuTEntity;
import com.wms.vo.LotLabelVO;

/**
 * @description: 分配明细VO
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-16 16:35
 **/

public class AllocateVO extends AllocateTEntity {
    private String uom;

    private Long packId;

    private String packCode;
    
    //拣货数量，前台提交时使用
    private BigDecimal quantityPick;
    
    //发货数量，前台提交时使用
    private BigDecimal quantityShip;
    
    //短拣标识
    private String shortFlag = YesNoEnum.No.getCode();
    
    //短拣对象
    private AllocateShortTEntity allocateShort;
    
    //处理任务标识
    private String processTaskFlag = YesNoEnum.Yes.getCode();
    
    //交易类别
    private String transactionCategory;
    
    private PackTEntity pack;
    private SkuTEntity sku;
    private LotLabelVO lotLabel;
    private LotValidateTEntity lotv;
    private LocationTEntity loc;
    
    private String lotAttribute1;

    private String lotAttribute2;

    private String lotAttribute3;

    private Date lotAttribute4;

    private Date lotAttribute5;

    private String lotAttribute6;

    private String lotAttribute7;

    private String lotAttribute8;

    private String lotAttribute9;

    private String lotAttribute10;

    private Date lotAttribute11;

    private Date lotAttribute12;

    public AllocateVO() {
    }

    public AllocateVO(AllocateTEntity allocate) {
        BeanUtils.copyBeanProp(this, allocate, Boolean.TRUE);
    }
    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public Long getPackId() {
        return packId;
    }

    public void setPackId(Long packId) {
        this.packId = packId;
    }

    public String getPackCode() {
        return packCode;
    }

    public void setPackCode(String packCode) {
        this.packCode = packCode;
    }

	public BigDecimal getQuantityPick() {
		return quantityPick;
	}

	public void setQuantityPick(BigDecimal quantityPick) {
		this.quantityPick = quantityPick;
	}

	public String getShortFlag() {
		return shortFlag;
	}

	public void setShortFlag(String shortFlag) {
		this.shortFlag = shortFlag;
	}

	public String getProcessTaskFlag() {
		return processTaskFlag;
	}

	public void setProcessTaskFlag(String processTaskFlag) {
		this.processTaskFlag = processTaskFlag;
	}

	public String getTransactionCategory() {
		return transactionCategory;
	}

	public void setTransactionCategory(String transactionCategory) {
		this.transactionCategory = transactionCategory;
	}

	public BigDecimal getQuantityShip() {
		return quantityShip;
	}

	public void setQuantityShip(BigDecimal quantityShip) {
		this.quantityShip = quantityShip;
	}

	public PackTEntity getPack() {
		return pack;
	}

	public void setPack(PackTEntity pack) {
		this.pack = pack;
	}

	public SkuTEntity getSku() {
		return sku;
	}

	public void setSku(SkuTEntity sku) {
		this.sku = sku;
	}

	public LotLabelVO getLotLabel() {
		return lotLabel;
	}

	public void setLotLabel(LotLabelVO lotLabel) {
		this.lotLabel = lotLabel;
	}

	public LotValidateTEntity getLotv() {
		return lotv;
	}

	public void setLotv(LotValidateTEntity lotv) {
		this.lotv = lotv;
	}

	public LocationTEntity getLoc() {
		return loc;
	}

	public void setLoc(LocationTEntity loc) {
		this.loc = loc;
	}

	public String getLotAttribute1() {
		return lotAttribute1;
	}

	public void setLotAttribute1(String lotAttribute1) {
		this.lotAttribute1 = lotAttribute1;
	}

	public String getLotAttribute2() {
		return lotAttribute2;
	}

	public void setLotAttribute2(String lotAttribute2) {
		this.lotAttribute2 = lotAttribute2;
	}

	public String getLotAttribute3() {
		return lotAttribute3;
	}

	public void setLotAttribute3(String lotAttribute3) {
		this.lotAttribute3 = lotAttribute3;
	}

	public Date getLotAttribute4() {
		return lotAttribute4;
	}

	public void setLotAttribute4(Date lotAttribute4) {
		this.lotAttribute4 = lotAttribute4;
	}

	public Date getLotAttribute5() {
		return lotAttribute5;
	}

	public void setLotAttribute5(Date lotAttribute5) {
		this.lotAttribute5 = lotAttribute5;
	}

	public String getLotAttribute6() {
		return lotAttribute6;
	}

	public void setLotAttribute6(String lotAttribute6) {
		this.lotAttribute6 = lotAttribute6;
	}

	public String getLotAttribute7() {
		return lotAttribute7;
	}

	public void setLotAttribute7(String lotAttribute7) {
		this.lotAttribute7 = lotAttribute7;
	}

	public String getLotAttribute8() {
		return lotAttribute8;
	}

	public void setLotAttribute8(String lotAttribute8) {
		this.lotAttribute8 = lotAttribute8;
	}

	public String getLotAttribute9() {
		return lotAttribute9;
	}

	public void setLotAttribute9(String lotAttribute9) {
		this.lotAttribute9 = lotAttribute9;
	}

	public String getLotAttribute10() {
		return lotAttribute10;
	}

	public void setLotAttribute10(String lotAttribute10) {
		this.lotAttribute10 = lotAttribute10;
	}

	public Date getLotAttribute11() {
		return lotAttribute11;
	}

	public void setLotAttribute11(Date lotAttribute11) {
		this.lotAttribute11 = lotAttribute11;
	}

	public Date getLotAttribute12() {
		return lotAttribute12;
	}

	public void setLotAttribute12(Date lotAttribute12) {
		this.lotAttribute12 = lotAttribute12;
	}

	public AllocateShortTEntity getAllocateShort() {
		return allocateShort;
	}

	public void setAllocateShort(AllocateShortTEntity allocateShort) {
		this.allocateShort = allocateShort;
	}
    
	
	
    
}
