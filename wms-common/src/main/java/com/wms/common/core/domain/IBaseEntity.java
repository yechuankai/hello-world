package com.wms.common.core.domain;

import java.io.Serializable;
import java.util.Date;

public interface IBaseEntity extends Serializable{
	
	default Long getCompanyId() {
        return null;
    }

    default void setCompanyId(Long companyId) {}

    default Long getWarehouseId() {
        return null;
    }

    default void setWarehouseId(Long warehouseId) {}
    
    default String getDelFlag() {
        return null;
    }

    default void setDelFlag(String delFlag) {}

    default String getCreateBy() {
    	return null;
    }

    default void setCreateBy(String createBy) {}

    default Date getCreateTime() {
        return null;
    }

    default void setCreateTime(Date createTime) {}

    default String getUpdateBy() {
        return null;
    }

    default void setUpdateBy(String updateBy) {}

    default Date getUpdateTime() {
        return null;
    }

    default void setUpdateTime(Date updateTime) {}

    default Long getUpdateVersion() {
        return null;
    }

    default void setUpdateVersion(Long updateVersion) {}

    default String getDescription() {
        return null;
    }

    default void setDescription(String description) {}
	

}
