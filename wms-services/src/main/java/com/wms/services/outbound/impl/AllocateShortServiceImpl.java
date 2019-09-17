package com.wms.services.outbound.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IAllocateShortTDao;
import com.wms.dao.example.AllocateShortTExample;
import com.wms.entity.auto.AllocateShortTEntity;
import com.wms.entity.auto.AllocateTEntity;
import com.wms.services.outbound.IAllocateShortService;

@Service
public class AllocateShortServiceImpl implements IAllocateShortService {

	@Autowired
	private IAllocateShortTDao allocateShortDao;
	
	@Override
	public List<AllocateShortTEntity> find(PageRequest request) throws BusinessServiceException {
		AllocateShortTExample example = new AllocateShortTExample();
        AllocateShortTExample.Criteria exampleCriteria = example.createCriteria();

        //转换查询方法
        ExampleUtils.create(AllocateTEntity.Column.class, AllocateShortTExample.Criterion.class)
                .criteria(exampleCriteria)
                .data(request)
                .build(request)
                .orderby(example);
        exampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
        return allocateShortDao.selectByExample(example);
	}

	@Override
	public Boolean add(AjaxRequest<AllocateShortTEntity> request) throws BusinessServiceException {
		AllocateShortTEntity allShort = request.getData();
		allShort.setAllocateShortId(KeyUtils.getUID());
		allShort.setCreateBy(request.getUserName());
		allShort.setUpdateBy(request.getUserName());
		allShort.setCreateTime(new Date());
		allShort.setUpdateTime(new Date());
		int rowcount = allocateShortDao.insertSelective(allShort);
		if (rowcount == 0)
			throw new BusinessServiceException("add error.");
		return Boolean.TRUE;
	}

}
