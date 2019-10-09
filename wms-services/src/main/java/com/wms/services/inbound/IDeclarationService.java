package com.wms.services.inbound;

import java.util.List;

import com.wms.entity.auto.InboundHeaderTEntity;

/**
 * 报关操作
 * @author yechuankai.chnet
 *
 */
public interface IDeclarationService {

	List<InboundHeaderTEntity> findByDeclareNumber(InboundHeaderTEntity inbound);
}
