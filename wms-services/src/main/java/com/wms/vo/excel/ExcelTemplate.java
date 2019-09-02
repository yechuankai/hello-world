package com.wms.vo.excel;

public class ExcelTemplate<T> {

	private String template;
	private T clz;
	
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public T getClz() {
		return clz;
	}
	public void setClz(T clz) {
		this.clz = clz;
	}
	
	
	
}
