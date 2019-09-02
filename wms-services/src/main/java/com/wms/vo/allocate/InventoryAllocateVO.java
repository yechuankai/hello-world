package com.wms.vo.allocate;

import java.util.List;

import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.OutboundHeaderTEntity;

public class InventoryAllocateVO extends OutboundHeaderTEntity {

	private List<InventoryAllocateDetailVO> detail;
	private Long allocateBatchId;

	public List<InventoryAllocateDetailVO> getDetail() {
		return detail;
	}

	public void setDetail(List<InventoryAllocateDetailVO> detail) {
		this.detail = detail;
	}
	
	public InventoryAllocateVO(){}
	
	public InventoryAllocateVO(OutboundHeaderTEntity header){
		BeanUtils.copyBeanProp(this, header);
	}

	public Long getAllocateBatchId() {
		return allocateBatchId;
	}

	public void setAllocateBatchId(Long allocateBatchId) {
		this.allocateBatchId = allocateBatchId;
	}
	
	
}
