package com.wms.common.core.services;

import java.util.List;

import com.wms.common.core.domain.ExcelTemplate;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;

public interface IExcelService<T> {
	
	default ExcelTemplate getExcelTemplate() {
		return null;
	}
	
	default void importData(AjaxRequest<List<T>> request) throws BusinessServiceException {}
	
	default List<T> exportData(PageRequest request) throws BusinessServiceException{
		return null;
	}
}
