package com.wms.services.sys;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.SysCodelistTEntity;
import com.wms.entity.auto.SysCodelkupTEntity;

import java.util.List;

public interface ISysCodelkupService {

	void load();
	
    List<SysCodelkupTEntity> find(PageRequest request) throws BusinessServiceException;

    Boolean modify(AjaxRequest<List<SysCodelkupTEntity>> request) throws BusinessServiceException;

    Boolean add(AjaxRequest<List<SysCodelkupTEntity>> request) throws BusinessServiceException;

    Boolean delete(AjaxRequest<List<SysCodelkupTEntity>> request) throws BusinessServiceException;

    List<SysCodelkupTEntity> findByCodelist(SysCodelistTEntity codelist) throws BusinessServiceException;

    Boolean delete(List<SysCodelkupTEntity> codelkupList) throws BusinessServiceException;
}
