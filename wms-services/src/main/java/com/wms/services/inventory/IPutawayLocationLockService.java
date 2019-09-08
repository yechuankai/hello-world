package com.wms.services.inventory;

import java.util.List;

import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.LpnTEntity;
import com.wms.entity.auto.PutawayLocationLockTEntity;

public interface IPutawayLocationLockService {

	List<PutawayLocationLockTEntity> find(PutawayLocationLockTEntity lock) throws BusinessServiceException;
	
	/**
	 * 根据容器获取锁定库位
	 */
	List<PutawayLocationLockTEntity> find(LpnTEntity lpn) throws BusinessServiceException;
	
	Boolean clean(PutawayLocationLockTEntity lock) throws BusinessServiceException;
	
	Boolean add(PutawayLocationLockTEntity lock) throws BusinessServiceException;
	
	Boolean delete(List<PutawayLocationLockTEntity> lock) throws BusinessServiceException;
	
}
