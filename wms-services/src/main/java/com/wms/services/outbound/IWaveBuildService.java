package com.wms.services.outbound;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.entity.auto.WaveBuildTEntity;
import com.wms.vo.outbound.WaveBuildVo;

public interface IWaveBuildService {

	List<WaveBuildTEntity> find(PageRequest pageRequest);

	Boolean save(AjaxRequest<WaveBuildVo> request);

	List<OutboundHeaderTEntity> findOutbounds(AjaxRequest request);

}
