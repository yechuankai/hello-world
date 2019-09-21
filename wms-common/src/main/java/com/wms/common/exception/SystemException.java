package com.wms.common.exception;

import com.wms.common.constants.ExceptionConstant;
import com.wms.common.utils.MessageUtils;

public class SystemException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SystemException(Throwable cause) {
        super(cause);
    }
	
	@Override
	public String getMessage() {
		String message = MessageUtils.message(ExceptionConstant.ERROR_DEFAULT);
		return message + " -> " + super.getMessage();
	}

}
