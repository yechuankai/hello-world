package com.wms.vo;

import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.TaskDetailTEntity;

public class TaskDetailVO extends TaskDetailTEntity {

	//车牌
	private String carNumber;
	//集装箱号
	private String containerNumber;
	//泊位
	private String platFormCode;
	
	public TaskDetailVO() {}
	
	public TaskDetailVO(TaskDetailTEntity task) {
		BeanUtils.copyBeanProp(this, task);
	}
	
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getContainerNumber() {
		return containerNumber;
	}
	public void setContainerNumber(String containerNumber) {
		this.containerNumber = containerNumber;
	}
	public String getPlatFormCode() {
		return platFormCode;
	}
	public void setPlatFormCode(String platFormCode) {
		this.platFormCode = platFormCode;
	}
	
	
	
	
	
}
