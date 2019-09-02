package com.wms.vo.outbound;

import com.wms.common.enums.OperatorTypeEnum;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.AllocateTEntity;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.vo.inventory.EntInventoryOnhandVO;

import java.util.List;

public class OutboundVO extends OutboundHeaderTEntity{

	private OperatorTypeEnum operatorType;
	private List<OutboundDetailVO> detail;
	private List<AllocateTEntity> allocateList;
	private List<EntInventoryOnhandVO> entInventoryOnhandList;

	public List<EntInventoryOnhandVO> getEntInventoryOnhandList() {
		return entInventoryOnhandList;
	}

	public void setEntInventoryOnhandList(List<EntInventoryOnhandVO> entInventoryOnhandList) {
		this.entInventoryOnhandList = entInventoryOnhandList;
	}

	public void setDetail(List<OutboundDetailVO> detail) {
		this.detail = detail;
	}
	
	public List<OutboundDetailVO> getDetail() {
		return detail;
	}
	
	public void setOperatorType(OperatorTypeEnum opertionType) {
		this.operatorType = opertionType;
	}
	
	public OperatorTypeEnum getOperatorType() {
		return operatorType;
	}
	
	public List<AllocateTEntity> getAllocateList() {
		return allocateList;
	}

	public void setAllocateList(List<AllocateTEntity> allocateList) {
		this.allocateList = allocateList;
	}

	public OutboundVO() {}
	
	public OutboundVO(OutboundHeaderTEntity outboundHeader) {
		BeanUtils.copyBeanProp(this, outboundHeader, Boolean.TRUE);
	}
	
	public OutboundVO(OutboundHeaderTEntity outboundHeader, List<OutboundDetailVO> detail) {
		BeanUtils.copyBeanProp(this, outboundHeader, Boolean.TRUE);
		this.detail = detail;
	}

}
