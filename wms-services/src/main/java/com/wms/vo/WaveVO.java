package com.wms.vo;

import java.util.List;

import com.wms.entity.auto.WaveDetailTEntity;
import com.wms.entity.auto.WaveTEntity;

public class WaveVO extends WaveTEntity{

	private List<WaveDetailTEntity> detail;
	
	public void setDetail(List<WaveDetailTEntity> detail) {
		this.detail = detail;
	}
	
	public List<WaveDetailTEntity> getDetail() {
		return detail;
	}
}
