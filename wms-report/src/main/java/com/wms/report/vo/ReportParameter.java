package com.wms.report.vo;

import java.util.Map;

import com.google.common.collect.Maps;

public class ReportParameter {
	private String reportName;
	private Map<String, String> params = null;
	private String format;

	public ReportParameter(String reportName, String format) {
		this.reportName = reportName;
		this.format = format;
		params = Maps.newHashMap();
	}
	
	public ReportParameter(String reportName, String format, Map<String, String> params) {
		this.reportName = reportName;
		this.format = format;
		this.params = params;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setParameter(String key, String val) {
		this.params.put(key, val);
	}

	public String getParameter(String key) {
		return this.params.get(key);
	}

	public Map<String, String> getParameter() {
		return this.params;
	}
	
	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public Map<String, String> getParams() {
		return params;
	}
	
	

}