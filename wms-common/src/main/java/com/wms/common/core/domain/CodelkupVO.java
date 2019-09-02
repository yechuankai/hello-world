package com.wms.common.core.domain;

import java.io.Serializable;

public class CodelkupVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String descr;
	private Boolean selected;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	public CodelkupVO() {
		
	}
	
	public CodelkupVO(String code, String desc) {
		this.code = code;
		this.descr = desc;
	}
	
	public Boolean getSelected() {
		return selected;
	}
	
	public void setSelected(Boolean selected) {
		this.selected = selected;
	} 
	
	

}
