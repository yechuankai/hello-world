package com.wms.vo;

import java.util.Date;

import com.wms.common.enums.YesNoEnum;
import com.wms.entity.auto.SysOperLogTEntity;

public class OperLogVO extends SysOperLogTEntity {

	public OperLogVO(){}
	
	public OperLogVO(String title, String businessType, String operatorType, String method, String userName){
		this.setTitle(title);
		this.setBusinessType(businessType);
		this.setOperatorType(operatorType);
		this.setMethod(method);
		this.setOperName(userName);
		this.setStatus(YesNoEnum.Yes.getCode());
		this.setCreateBy(userName);
		this.setUpdateBy(userName);
		Date date = new Date();
		this.setCreateTime(date);
		this.setUpdateTime(date);
		this.setOperTime(date);
	}
	
	public OperLogVO(String title, String businessType, String operatorType, String method, String userName, String operUrl, String operIp, String operParam, String operLocation){
		this.setTitle(title);
		this.setBusinessType(businessType);
		this.setOperatorType(operatorType);
		this.setMethod(method);
		this.setStatus(YesNoEnum.Yes.getCode());
		this.setOperUrl(operUrl);
		this.setOperIp(operIp);
		this.setOperParam(operParam);
		this.setOperLocation(operLocation);
		this.setOperName(userName);
		this.setCreateBy(userName);
		this.setUpdateBy(userName);
		Date date = new Date();
		this.setCreateTime(date);
		this.setUpdateTime(date);
		this.setOperTime(date);
	}
	
	public OperLogVO(String title, String businessType, String operatorType, String method, String userName, String errmsg){
		this.setTitle(title);
		this.setBusinessType(businessType);
		this.setOperatorType(operatorType);
		this.setMethod(method);
		this.setErrorMsg(errmsg);
		this.setStatus(YesNoEnum.No.getCode());
		this.setOperName(userName);
		this.setCreateBy(userName);
		this.setUpdateBy(userName);
		Date date = new Date();
		this.setCreateTime(date);
		this.setUpdateTime(date);
		this.setOperTime(date);
	}
}
