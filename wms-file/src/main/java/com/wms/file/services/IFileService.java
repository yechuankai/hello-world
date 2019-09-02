package com.wms.file.services;

import java.io.OutputStream;
import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.exception.file.ExcelException;
import com.wms.file.vo.FileVO;


public interface IFileService {
	
	List<FileVO> upload(AjaxRequest<List<FileVO>> request) throws BusinessServiceException;
	
	FileVO download(AjaxRequest<FileVO> request, OutputStream os) throws BusinessServiceException;

	void importData(FileVO file) throws ExcelException;
	
	void exportData(PageRequest request, String template) throws ExcelException;
}
