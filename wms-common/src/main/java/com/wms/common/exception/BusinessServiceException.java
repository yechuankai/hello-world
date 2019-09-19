package com.wms.common.exception;

import com.wms.common.exception.base.BaseException;

public class BusinessServiceException extends BaseException {
	
	private Object [] params;

	public BusinessServiceException(String module, String code, Object[] args, String defaultMessage) {
		super(module, code, args, defaultMessage);
		params = args;
	}

	public BusinessServiceException(String module, String code, Object[] args) {
		super(module, code, args);
		params = args;
	}

	public BusinessServiceException(String module, String defaultMessage) {
		super(module, defaultMessage);
	}

	public BusinessServiceException(String code, Object[] args) {
		super(code, args);
		params = args;
	}

	public BusinessServiceException(String defaultMessage) {
		super(defaultMessage);
	}

	public Object[] getParams() {
		if (params == null)
			return new Object[] {};
			
		return params;
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
