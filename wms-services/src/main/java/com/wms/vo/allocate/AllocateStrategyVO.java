package com.wms.vo.allocate;

import com.wms.entity.auto.AllocateStrategyDetailTEntity;
import com.wms.entity.auto.AllocateStrategyTEntity;

public class AllocateStrategyVO extends AllocateStrategyTEntity{

	private AllocateStrategyDetailTEntity detail;

	public AllocateStrategyDetailTEntity getDetail() {
		return detail;
	}

	public void setDetail(AllocateStrategyDetailTEntity detail) {
		this.detail = detail;
	}
}
