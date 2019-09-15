package com.wms.services.outbound;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.entity.auto.WaveBuildDetailTEntity;
import com.wms.entity.auto.WaveBuildTEntity;
import com.wms.vo.outbound.WaveBuildVO;

public interface IWaveBuildService {

	List<WaveBuildTEntity> find(PageRequest pageRequest);
	
	WaveBuildTEntity find(WaveBuildTEntity wb);
	
	List<WaveBuildDetailTEntity> findDetail(WaveBuildDetailTEntity wb);

	Boolean rowModify(AjaxRequest<List<WaveBuildVO>> request);
	
	Boolean modify(AjaxRequest<WaveBuildVO> request);
	
	Boolean delete(AjaxRequest<List<WaveBuildVO>> request);
	
	Boolean add(AjaxRequest<WaveBuildVO> request);

	List<OutboundHeaderTEntity> findOutbounds(AjaxRequest<WaveBuildVO> request);

}
