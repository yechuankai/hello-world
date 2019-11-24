package com.wms.vo;

import java.util.Date;

import com.wms.entity.auto.LotAttributeTEntity;

public class LotAttributeVO extends LotAttributeTEntity{

	private LotLabelVO lotLabel;

	public LotLabelVO getLotLabel() {
		return lotLabel;
	}

	public void setLotLabel(LotLabelVO lotLabel) {
		this.lotLabel = lotLabel;
	}
	
}
