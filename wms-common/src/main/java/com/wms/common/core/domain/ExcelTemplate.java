package com.wms.common.core.domain;

import com.wms.common.core.services.IExcelService;

public class ExcelTemplate<T> {

	private String template;
	private Class<T> clz;
	private IExcelService<T> excelService;
	
	public ExcelTemplate() {}
	
	public ExcelTemplate(String template, Class<T> clz) {
		this.template = template;
		this.clz = clz;
	}
	
	public String getTemplate() {
		return template;
	}
	
	public void setTemplate(String template) {
		this.template = template;
	}
	
	public Class<T> getClz() {
		return clz;
	}
	
	public void setClz(Class<T> clz) {
		this.clz = clz;
	}
	
	public IExcelService<T> getExcelService() {
		return excelService;
	}
	
	public void setExcelService(IExcelService<T> excelService) {
		this.excelService = excelService;
	}
	
}
