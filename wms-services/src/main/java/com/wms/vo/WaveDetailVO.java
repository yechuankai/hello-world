package com.wms.vo;

import com.wms.entity.auto.OutboundHeaderTEntity;

public class WaveDetailVO extends OutboundHeaderTEntity {

	private Long waveDetailId;

	private Long waveId;
	
	private String waveNumber;
	
	public WaveDetailVO(){
		
	}

	public Long getWaveDetailId() {
		return waveDetailId;
	}

	public void setWaveDetailId(Long waveDetailId) {
		this.waveDetailId = waveDetailId;
	}

	public Long getWaveId() {
		return waveId;
	}

	public void setWaveId(Long waveId) {
		this.waveId = waveId;
	}

	public String getWaveNumber() {
		return waveNumber;
	}

	public void setWaveNumber(String waveNumber) {
		this.waveNumber = waveNumber;
	}
	
	

}
