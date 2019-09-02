package com.wms.common.core.domain.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="SYS_CONTENT_T")
public class RestContent extends BaseDomain{

	private String content;
	private String type;
	private String ip;
	private String url;
	
	public RestContent(){
		super();
	}
	
	public RestContent(String url, String content, String type, String ip){
		super();
		this.url = url;
		this.content = content;
		this.type = type;
		this.ip = ip;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
}
