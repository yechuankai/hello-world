package com.wms.common.core.domain.mongodb;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class BaseDomain {

	@Id
	private String id;
	private Date createTime;
	
	
	public BaseDomain(){
		id = new ObjectId().toHexString();
		createTime = new Date();
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
