package com.wms.common.exception;

import com.wms.common.exception.base.BaseException;

public class BusinessServiceException extends BaseException {
	
	public BusinessServiceException(String module, String code, Object[] args, String defaultMessage) {
		super(module, code, args, defaultMessage);
	}

	public BusinessServiceException(String module, String code, Object[] args) {
		super(module, code, args);
	}

	public BusinessServiceException(String module, String defaultMessage) {
		super(module, defaultMessage);
	}

	public BusinessServiceException(String code, Object[] args) {
		super(code, args);
	}

	public BusinessServiceException(String defaultMessage) {
		super(defaultMessage);
	}

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
