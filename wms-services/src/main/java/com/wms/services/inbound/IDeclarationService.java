package com.wms.services.inbound;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.entity.auto.InboundHeaderTEntity;
import com.wms.vo.excel.CustomsClearanceImportVO;

/**
 * 报关操作
 * @author yechuankai.chnet
 *
 */
public interface IDeclarationService {

	List<InboundHeaderTEntity> findByDeclareNumber(InboundHeaderTEntity inbound);
	
	Boolean modifyCustomsClearance(AjaxRequest<CustomsClearanceImportVO> request);
	
}
