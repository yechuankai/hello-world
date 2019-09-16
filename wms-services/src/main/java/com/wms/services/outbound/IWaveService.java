package com.wms.services.outbound;

import java.util.List;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.OutboundProcessStatusEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.entity.auto.WaveTEntity;
import com.wms.vo.WaveVO;
import com.wms.vo.outbound.OutboundVO;

/**
 * 波次
 * @author yechuankai.chnet
 *
 */
public interface IWaveService {
	
	/**
	 * 波次查询
	 * @param request
	 * @return
	 * @throws BusinessServiceException
	 */
	List<WaveTEntity> find(PageRequest request)  throws BusinessServiceException;
	
	/**
	 * 波次修改
	 * @param request
	 * @return
	 * @throws BusinessServiceException
	 */
	Boolean modify(AjaxRequest<List<WaveTEntity>> request)  throws BusinessServiceException;
	
	/**
	 * 更新处理状态
	 * @param wave
	 * @param processStatus
	 * @return
	 */
	Boolean processStatus(WaveTEntity wave, OutboundProcessStatusEnum processStatus);
	
	/**
	 * 波次删除
	 * @param request
	 * @return
	 * @throws BusinessServiceException
	 */
	Boolean delete(AjaxRequest<List<WaveTEntity>> request)  throws BusinessServiceException;

	/**
	 * 创建波次
	 * @param request
	 * @return
	 * @throws BusinessServiceException
	 */
	Boolean createWave(AjaxRequest<WaveVO> request) throws BusinessServiceException;
	
	Boolean allocate(AjaxRequest<List<WaveVO>> request) throws BusinessServiceException;
	
	Boolean unAllocate(AjaxRequest<List<WaveVO>> request) throws BusinessServiceException;
	
	Boolean pick(AjaxRequest<List<WaveVO>> request) throws BusinessServiceException;
	
	Boolean unPick(AjaxRequest<List<WaveVO>> request) throws BusinessServiceException;

	Boolean ship(AjaxRequest<List<WaveVO>> request) throws BusinessServiceException;


}
