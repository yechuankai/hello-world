package com.wms.vo.inbound;

import java.util.List;

import com.google.common.collect.Lists;
import com.wms.common.enums.OperatorTypeEnum;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.InboundHeaderTEntity;

public class InboundVO extends InboundHeaderTEntity{

	private OperatorTypeEnum operatorType;
	private List<InboundDetailVO> detail = Lists.newArrayList();
	private Boolean forceUpdate = Boolean.FALSE;
	
	public void setDetail(List<InboundDetailVO> detail) {
		this.detail = detail;
	}
	
	public List<InboundDetailVO> getDetail() {
		return detail;
	}
	
	public void setOperatorType(OperatorTypeEnum opertionType) {
		this.operatorType = opertionType;
	}
	
	public OperatorTypeEnum getOperatorType() {
		return operatorType;
	}
	
	public InboundVO() {}
	
	public InboundVO(InboundHeaderTEntity inboundHeader) {
		BeanUtils.copyBeanProp(this, inboundHeader, Boolean.TRUE);
	}
	
	public InboundVO(InboundHeaderTEntity inboundHeader, List<InboundDetailVO> detail) {
		BeanUtils.copyBeanProp(this, inboundHeader, Boolean.TRUE);
		this.detail = detail;
	}

	public Boolean getForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(Boolean forceUpdate) {
		this.forceUpdate = forceUpdate;
	}

}
