package com.wms.vo.adjustment;

import java.util.List;

import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.InventoryAdjustmentHeaderTEntity;

public class AdjustmentVO extends InventoryAdjustmentHeaderTEntity {

	private List<AdjustmentDetailVO> detail;

	public List<AdjustmentDetailVO> getDetail() {
		return detail;
	}

	public void setDetail(List<AdjustmentDetailVO> detail) {
		this.detail = detail;
	}
	
	public AdjustmentVO() {}
	
	public AdjustmentVO(InventoryAdjustmentHeaderTEntity header) {
		BeanUtils.copyBeanProp(this, header);
	}
	
	
}
