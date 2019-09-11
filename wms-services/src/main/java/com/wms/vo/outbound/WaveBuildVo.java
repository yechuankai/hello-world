package com.wms.vo.outbound;

import java.util.List;

import com.wms.entity.auto.WaveBuildDetailTEntity;
import com.wms.entity.auto.WaveBuildTEntity;

public class WaveBuildVo extends WaveBuildTEntity{
	private static final long serialVersionUID = 6380147483267332971L;
	
	private List<WaveBuildDetailTEntity> waveBuildDetailTEntities;

	public List<WaveBuildDetailTEntity> getWaveBuildDetailTEntities() {
		return waveBuildDetailTEntities;
	}

	public void setWaveBuildDetailTEntities(List<WaveBuildDetailTEntity> waveBuildDetailTEntities) {
		this.waveBuildDetailTEntities = waveBuildDetailTEntities;
	}
}
