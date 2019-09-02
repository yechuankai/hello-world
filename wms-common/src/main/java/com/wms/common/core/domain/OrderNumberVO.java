package com.wms.common.core.domain;

import com.wms.common.utils.StringUtils;

public class OrderNumberVO {

	private String code;
	
	private String prefix = "";
	
	private Long length = 10L;
	
	private String dateFormat;
	
	private Long sequence = 0L;
	
	private Long cacheSequence = 5L;
	
	private Long currentSequence = 1L;
	
	private Long increment = 1L;
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		if (StringUtils.isNotEmpty(prefix))
			this.prefix = prefix;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

	public Long getCacheSequence() {
		return cacheSequence;
	}

	public void setCacheSequence(Long cacheSequence) {
		this.cacheSequence = cacheSequence;
	}
	
	public void setCurrentSequence(Long currentSequence) {
		this.currentSequence = currentSequence;
	}
	
	public Long getCurrentSequence() {
		return currentSequence;
	}
	
	public void setIncrement(Long increment) {
		this.increment = increment;
	}
	
	public Long getIncrement() {
		return increment;
	}
	
	public Long increment() {
		return (currentSequence = (currentSequence + increment));
	}
	
	
}
