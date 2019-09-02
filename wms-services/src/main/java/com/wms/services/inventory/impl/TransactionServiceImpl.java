package com.wms.services.inventory.impl;

import com.google.common.collect.Lists;
import com.wms.common.core.domain.ExcelTemplate;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.services.IExcelService;
import com.wms.common.enums.ExcelTemplateEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IInventoryTransactionTDao;
import com.wms.dao.example.InventoryTransactionTExample;
import com.wms.entity.auto.InventoryTransactionTEntity;
import com.wms.services.inventory.ITransactionService;
import com.wms.vo.excel.InventoryTransactionImportVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService, IExcelService<InventoryTransactionImportVO> {

	@Autowired
	private IInventoryTransactionTDao tranDao;
	
	@Override
	@Transactional
	public Boolean add(InventoryTransactionTEntity tran) throws BusinessServiceException {
		tran.setTransactionId(KeyUtils.getUID());
		int rowcount = tranDao.insertSelective(tran);
		if (rowcount == 0)
			throw new BusinessServiceException("record update error.");
		return Boolean.TRUE;
	}

	@Override
	public List<InventoryTransactionTEntity> find(PageRequest request) throws BusinessServiceException {
		InventoryTransactionTExample TExample = new InventoryTransactionTExample();
		InventoryTransactionTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(InventoryTransactionTEntity.Column.class, InventoryTransactionTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.betweenDate(	InventoryTransactionTEntity.Column.transactionDate.getJavaProperty()
				 )
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		return tranDao.selectByExample(TExample);
	}

	@Override
	public ExcelTemplate getExcelTemplate() {
		return new ExcelTemplate<InventoryTransactionImportVO>(ExcelTemplateEnum.Transaction.getCode(), InventoryTransactionImportVO.class);
	}

	@Override
	public List<InventoryTransactionImportVO> exportData(PageRequest request) throws BusinessServiceException {
		List<InventoryTransactionImportVO> returnList = Lists.newArrayList();
		List<InventoryTransactionTEntity> transactions = find(request);
		if (CollectionUtils.isEmpty(transactions)) {
			return returnList;
		}
		transactions.forEach(d ->{
			InventoryTransactionImportVO transaction = new InventoryTransactionImportVO();
			BeanUtils.copyBeanProp(transaction, d);
			returnList.add(transaction);
		});
		return returnList;
	}
}
