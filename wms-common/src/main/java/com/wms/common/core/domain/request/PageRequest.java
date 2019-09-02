package com.wms.common.core.domain.request;

import com.wms.common.utils.StringUtils;

import java.util.Map;

public class PageRequest extends AjaxRequest {

	private static final String [] PRO_PAGE_START = {"page", "pageNum"};
	private static final String [] PRO_PAGE_SIZE = {"rows","pageSize"};
	private static final String [] PRO_PAGE_SORT = {"sort"};
	private static final String [] PRO_PAGE_ORDER = {"order"};
	
	public PageRequest(){}
	
	public PageRequest(Map<String, String> map) {
		this.putAll(map);
	}
	
	public int getPageStart() {
		int pageStart = 0;
		for (String s : PRO_PAGE_START) {
			pageStart = this.getInt(s);
			if (pageStart != 0)
				break;
		}
		return pageStart;
	}
	
	public int getPageSize() {
		int pageSize = 0;
		for (String s : PRO_PAGE_SIZE) {
			pageSize = this.getInt(s);
			if (pageSize != 0)
				break;
		}
		return pageSize;
	}
	
	public String getSort() {
		String sort = null;
		for (String s : PRO_PAGE_SORT) {
			sort = this.getString(s);
			if (StringUtils.isNotEmpty(sort))
				break;
		}
		return sort;
	}
	
	public String getOrder() {
		String order = null;
		for (String s : PRO_PAGE_ORDER) {
			order = this.getString(s);
			if (StringUtils.isNotEmpty(order))
				break;
		}
		return order;
	}
	
	public void setSort(String value) {
		this.put(PRO_PAGE_SORT[0], value);
	}
	
	public void setOrder(String value) {
		this.put(PRO_PAGE_ORDER[0], value);
	}
	
}
