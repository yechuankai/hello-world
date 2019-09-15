package com.wms.services.outbound;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.entity.auto.WaveDetailTEntity;
import com.wms.entity.auto.WaveTEntity;
import com.wms.vo.WaveDetailVO;

/**
 *  波次明细
 * @author yechuankai.chnet
 *
 */
public interface IWaveDetailService {
	
	/**
	 * 波次明細查詢
	 * @param request
	 * @return
	 * @throws BusinessServiceException
	 */
	List<WaveDetailVO> find(PageRequest request)  throws BusinessServiceException;
	
	/**
	 * 波次查询明细
	 * @param request
	 * @return
	 * @throws BusinessServiceException
	 */
	List<WaveDetailTEntity> findDetail(WaveDetailTEntity waveDetail)  throws BusinessServiceException;
	
	
	/**
	 * 波次删除
	 * @param request
	 * @return
	 * @throws BusinessServiceException
	 */
	Boolean delete(AjaxRequest<List<WaveDetailTEntity>> request)  throws BusinessServiceException;

	/**
	 * 创建波次明細
	 * @param request
	 * @return
	 * @throws BusinessServiceException
	 */
	Boolean add(AjaxRequest<List<WaveDetailTEntity>> request) throws BusinessServiceException;
	
}
