package com.wms.services.sys.impl;

import com.google.common.collect.Lists;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IStatusHistoryTDao;
import com.wms.dao.example.StatusHistoryTExample;
import com.wms.entity.auto.StatusHistoryTEntity;
import com.wms.services.sys.IStatusHistoryService;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: pengzhen@cmhit.com
 * @create: 2019-08-20 15:58
 **/
@Service
public class StatusHistoryServiceImpl implements IStatusHistoryService {

    @Autowired
    IStatusHistoryTDao statusHistoryDao;

    @Override
    public Boolean add(StatusHistoryTEntity statusHistory) throws BusinessServiceException {
        statusHistory.setHistoryId(KeyUtils.getUID());
        int rowcount = statusHistoryDao.insertSelective(statusHistory);
        if (rowcount == 0) {
            throw new BusinessServiceException("record add error.");
        }
        return Boolean.TRUE;
    }

	@Override
	public List<StatusHistoryTEntity> findBySourceNumber(StatusHistoryTEntity statusHistory)
			throws BusinessServiceException {
		StatusHistoryTExample example = new StatusHistoryTExample();
		example.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andCompanyIdEqualTo(statusHistory.getCompanyId())
		//.andWarehouseIdEqualTo(statusHistory.getWarehouseId())
		.andSourceNumberEqualTo(statusHistory.getSourceNumber());
		
		//按主键升序
		example.orderBy(StatusHistoryTEntity.Column.historyId.getValue());
		
		return statusHistoryDao.selectByExample(example);
	}
	
	@Override
	public StatusHistoryTEntity find(StatusHistoryTEntity statusHistory)
			throws BusinessServiceException {
		StatusHistoryTExample example = new StatusHistoryTExample();
		example.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andCompanyIdEqualTo(statusHistory.getCompanyId())
		//.andWarehouseIdEqualTo(statusHistory.getWarehouseId())
		.andHistoryIdEqualTo(statusHistory.getHistoryId());
		
		return statusHistoryDao.selectOneByExample(example);
	}

	@Override
	public List<StatusHistoryTEntity> findByNotice1(StatusHistoryTEntity statusHistory, Set<String> status)
			throws BusinessServiceException {
		StatusHistoryTExample example = new StatusHistoryTExample();
		StatusHistoryTExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andCompanyIdEqualTo(statusHistory.getCompanyId())
		.andNewStatusIn(Lists.newArrayList(status))
		.andNotice1EqualTo(statusHistory.getNotice1());
		
		if (StringUtils.isNotEmpty(statusHistory.getType())) {
			criteria.andTypeEqualTo(statusHistory.getType());
		}
		
		//按主键升序
		example.orderBy(StatusHistoryTEntity.Column.historyId.getValue());
		
		return statusHistoryDao.selectByExample(example);
	}

	@Override
	public Boolean modify(StatusHistoryTEntity statusHistory) throws BusinessServiceException {
		StatusHistoryTExample example = new StatusHistoryTExample();
		example.createCriteria().andHistoryIdEqualTo(statusHistory.getHistoryId());
		int rowcount = statusHistoryDao.updateByExampleSelective(statusHistory, example);
		if (rowcount == 0) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
}
