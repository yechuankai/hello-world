package com.wms.vo;


public class WarehouseActiveVO extends WarehouseVO{

	private Long activeId;

    private String active;

    private String init;

	public Long getActiveId() {
		return activeId;
	}

	public void setActiveId(Long activeId) {
		this.activeId = activeId;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getInit() {
		return init;
	}

	public void setInit(String init) {
		this.init = init;
	}
    
	
}
