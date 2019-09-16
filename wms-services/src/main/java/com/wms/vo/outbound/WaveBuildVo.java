package com.wms.vo.outbound;

import java.util.List;

import com.wms.entity.auto.WaveBuildDetailTEntity;
import com.wms.entity.auto.WaveBuildTEntity;

public class WaveBuildVO extends WaveBuildTEntity{
	private static final long serialVersionUID = 6380147483267332971L;
	
	private List<WaveBuildDetailTEntity> detail;

	public List<WaveBuildDetailTEntity> getDetail() {
		return detail;
	}

	public void setDetail(List<WaveBuildDetailTEntity> detail) {
		this.detail = detail;
	}

	
}
