package com.wms.file.vo;

import org.springframework.web.multipart.MultipartFile;

import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.SysFileTEntity;

public class FileVO extends SysFileTEntity{

	private MultipartFile file;
	private PageRequest data;
	
	public MultipartFile getFile() {
		return file;
	}
	
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public PageRequest getData() {
		return data;
	}
	
	public void setData(PageRequest data) {
		this.data = data;
	}
	
	public FileVO() {}
	
	public FileVO(SysFileTEntity file) {
		BeanUtils.copyBeanProp(this, file, Boolean.TRUE);
	}
	
}
