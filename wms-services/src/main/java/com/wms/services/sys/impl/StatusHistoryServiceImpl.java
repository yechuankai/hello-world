package com.wms.services.sys.impl;

import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IStatusHistoryTDao;
import com.wms.entity.auto.StatusHistoryTEntity;
import com.wms.services.sys.IStatusHistoryService;
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
}
