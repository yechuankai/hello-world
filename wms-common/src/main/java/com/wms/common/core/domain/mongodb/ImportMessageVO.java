package com.wms.common.core.domain.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

import com.wms.common.core.domain.mongodb.BaseDomain;

@Document(collection = "SYS_IMPORT_MESSAGE_T")
public class ImportMessageVO extends BaseDomain {

	private Long fileId;

	private String fileName;

	private String message;
	
	private String template;

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

}
