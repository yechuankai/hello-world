package com.wms.services.inbound.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.*;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IInboundDetailTDao;
import com.wms.dao.auto.IInboundHeaderTDao;
import com.wms.dao.example.InboundDetailTExample;
import com.wms.dao.example.InboundHeaderTExample;
import com.wms.entity.auto.*;
import com.wms.services.base.ICarrierService;
import com.wms.services.base.IEnterpriseService;
import com.wms.services.base.IOwnerService;
import com.wms.services.base.ISupplierService;
import com.wms.services.inbound.IInboundDetailService;
import com.wms.services.inbound.IInboundHeaderService;
import com.wms.services.sys.IStatusHistoryService;
import com.wms.vo.inbound.InboundDetailVO;
import com.wms.vo.inbound.InboundVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InboundHeaderServiceImpl implements IInboundHeaderService {

	@Autowired
	private IInboundHeaderTDao inboundHeaderDao;
	@Autowired
	private IInboundDetailTDao inboundDetailDao;
	@Autowired
	private IInboundDetailService inboundDetailService;
	@Autowired
	private IOwnerService ownerService;
	@Autowired
	private ISupplierService supplierService;
	@Autowired
	private ICarrierService carrierService;
	@Autowired
	private IStatusHistoryService statusHistoryService;
	@Autowired
	IEnterpriseService enterpriseService;
	
	@Override
	public List<InboundHeaderTEntity> find(PageRequest request) throws BusinessServiceException {
		
		InboundHeaderTExample TExample = new InboundHeaderTExample();
		InboundHeaderTExample.Criteria TExampleCriteria  = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(InboundHeaderTEntity.Column.class, InboundHeaderTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.betweenDate(	InboundHeaderTEntity.Column.createTime.getJavaProperty() , 
						InboundHeaderTEntity.Column.expectedInboundDate.getJavaProperty() , 
						InboundHeaderTEntity.Column.inboundDate.getJavaProperty()
				 )
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());

		
		List<InboundHeaderTEntity> inboundList = inboundHeaderDao.selectByExample(TExample);
		
		return inboundList;
	}


	@Override
	@Transactional
	public InboundVO save(AjaxRequest<InboundVO> request) throws BusinessServiceException {
		
		InboundVO inboundVO = request.getData();
		inboundVO.setWarehouseId(request.getWarehouseId());
		inboundVO.setCompanyId(request.getCompanyId());
		inboundVO.setUpdateBy(request.getUserName());
		inboundVO.setUpdateTime(new Date());
		
		validate(inboundVO);
		
		switch (inboundVO.getOperatorType()) {
		case Add:
			inboundVO.setCreateBy(request.getUserName());
			inboundVO.setCreateTime(new Date());
			add(inboundVO);
			break;
		case Modify:
			modify(inboundVO);
			break;
		default:
			throw new BusinessServiceException("InboundHeaderServiceImpl", "opertiontype.not.exists" , null ); 
		}
		
		//process detail
		if (CollectionUtils.isNotEmpty(inboundVO.getDetail())) 
			inboundDetailService.save(request);
		
		//process status
		inboundStatus(inboundVO, Boolean.TRUE);
		
		return inboundVO;
		
	}


	@Override
	public InboundVO find(InboundVO inbound) throws BusinessServiceException {
		InboundHeaderTEntity header = find((InboundHeaderTEntity) inbound);
		if (header == null) 
			return null;
		
		/*InboundDetailTEntity detail = InboundDetailTEntity.builder()
				.warehouseId(inbound.getWarehouseId())
				.companyId(inbound.getCompanyId())
				.inboundHeaderId(inbound.getInboundHeaderId())
				.build();*/
		
		
		InboundVO inboundVO = new InboundVO(header);
		
		return inboundVO;
	}


	@Override
	public InboundVO findById(InboundVO inbound) throws BusinessServiceException {
		InboundHeaderTExample TExample = new InboundHeaderTExample();
		InboundHeaderTExample.Criteria criteria = TExample.createCriteria();

		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
				.andCompanyIdEqualTo(inbound.getCompanyId())
				.andInboundHeaderIdEqualTo(inbound.getInboundHeaderId());

		InboundHeaderTEntity selectInbound = inboundHeaderDao.selectOneByExample(TExample);
		if (selectInbound == null) {
			return null;
		}
		InboundVO inboundVO = new InboundVO(selectInbound);
		return inboundVO;
	}

	@Override
	public InboundHeaderTEntity find(InboundHeaderTEntity inbound) throws BusinessServiceException {
		InboundHeaderTExample TExample = new InboundHeaderTExample();
		InboundHeaderTExample.Criteria criteria = TExample.createCriteria();
		
		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andCompanyIdEqualTo(inbound.getCompanyId());
		if(null != inbound.getWarehouseId()){
			criteria.andWarehouseIdEqualTo(inbound.getWarehouseId());
		}

		int conditionCount = 0;
		if (StringUtils.isNotEmpty(inbound.getInboundNumber())) {
			criteria.andInboundNumberEqualTo(inbound.getInboundNumber());
			conditionCount ++;
		}
		if (inbound.getInboundHeaderId() != null) {
			criteria.andInboundHeaderIdEqualTo(inbound.getInboundHeaderId());
			conditionCount ++;
		}
		if (conditionCount == 0)
			return null;
		
		InboundHeaderTEntity selectInbound = inboundHeaderDao.selectOneByExample(TExample);
		if (selectInbound == null) {
			throw new BusinessServiceException("InboundHeaderServiceImpl", "inbound.record.not.exists" , new Object[] {inbound.getInboundNumber()});
		}
		return selectInbound;
	}
	
	@Override
	public List<InboundHeaderTEntity> find(InboundHeaderTEntity inbound, Set<Long> ids) throws BusinessServiceException {
		if(CollectionUtils.isEmpty(ids))
			return Lists.newArrayList();
		
		InboundHeaderTExample TExample = new InboundHeaderTExample();
		InboundHeaderTExample.Criteria criteria = TExample.createCriteria();
		
		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andCompanyIdEqualTo(inbound.getCompanyId())
		.andWarehouseIdEqualTo(inbound.getWarehouseId())
		.andInboundHeaderIdIn(Lists.newArrayList(ids));
		
		List<InboundHeaderTEntity> inbounds = inboundHeaderDao.selectByExample(TExample);
		if (inbounds == null) {
			return Lists.newArrayList();
		}
		return inbounds;
	}
	
	private Boolean validate(InboundVO inbound) {
		
		if (inbound.getOwnerId() == null && StringUtils.isEmpty(inbound.getOwnerCode()))
			throw new BusinessServiceException("InboundHeaderServiceImpl", "inbound.owner.isnull" , new Object[] {inbound.getInboundNumber()}); 
		
		if (StringUtils.isEmpty(inbound.getType()))
			throw new BusinessServiceException("InboundHeaderServiceImpl", "inbound.type.isnull" , new Object[] {inbound.getInboundNumber()}); 
		
		//if (StringUtils.isEmpty(inbound.getStatus()))
		//	throw new BusinessServiceException("InboundHeaderServiceImpl", "inbound.status.isnull" , new Object[] {inbound.getInboundNumber()}); 
		
		if (inbound.getOperatorType() == OperatorTypeEnum.Modify) {
			return Boolean.TRUE;
		}
		
		
		OwnerTEntity owner = ownerService.find(OwnerTEntity.builder()
													.warehouseId(inbound.getWarehouseId())
													.companyId(inbound.getCompanyId())
													.ownerId(inbound.getOwnerId())
													.ownerCode(inbound.getOwnerCode())
													.build());
		inbound.setOwnerCode(owner.getOwnerCode());
		inbound.setOwnerId(owner.getOwnerId());

		if (inbound.getSupplierId() != null || StringUtils.isNotEmpty(inbound.getSupplierCode())) {
			SupplierTEntity supplier = supplierService.find(SupplierTEntity.builder()
					.warehouseId(inbound.getWarehouseId())
					.companyId(inbound.getCompanyId())
					.supplierId(inbound.getSupplierId())
					.supplierCode(inbound.getSupplierCode())
					.build());
			inbound.setSupplierId(supplier.getSupplierId());
			inbound.setSupplierCode(supplier.getSupplierCode());
		}
		
		if (inbound.getCarrierId() != null || StringUtils.isNotEmpty(inbound.getCarrierCode()) ) {
			CarrierTEntity carrier = carrierService.find(CarrierTEntity.builder()
					.warehouseId(inbound.getWarehouseId())
					.companyId(inbound.getCompanyId())
					.carrierId(inbound.getCarrierId())
                    .carrierCode(inbound.getCarrierCode())
					.build());
			inbound.setCarrierId(carrier.getCarrierId());
			inbound.setCarrierCode(carrier.getCarrierCode());
		}
		
		if (StringUtils.isEmpty(inbound.getInboundNumber())) {
			inbound.setInboundNumber(KeyUtils.getOrderNumber(inbound.getCompanyId(), inbound.getWarehouseId(), OrderNumberTypeEnum.Inbound));
		} else if(inbound.getOperatorType() == OperatorTypeEnum.Add){
			//新增判断重复
			InboundHeaderTEntity header = find(InboundHeaderTEntity.builder()
					.warehouseId(inbound.getWarehouseId())
					.companyId(inbound.getCompanyId())
					.inboundNumber(inbound.getInboundNumber())
					.build());
			if (header != null)
				throw new BusinessServiceException("InboundHeaderServiceImpl", "inbound.number.exists" , new Object[] {inbound.getInboundNumber()});
		}
		return Boolean.TRUE;
	}


	@Override
	@Transactional
	public Boolean modify(InboundVO inbound) throws BusinessServiceException {
		InboundVO inboundVO = new InboundVO(inbound);
		if(OperatorTypeEnum.Confirm == inbound.getOperatorType()){
			//报关确认时查询要排除入库单号和仓库号
			inboundVO.setInboundNumber(null);
			inboundVO.setWarehouseId(null);
		}
		InboundHeaderTEntity selectHeader = find((InboundHeaderTEntity)inboundVO);
		
		notProcess(selectHeader);
		
		InboundHeaderTEntity updateHeader = new InboundHeaderTEntity();
		BeanUtils.copyBeanProp(updateHeader, inbound, Boolean.FALSE);
		updateHeader.setUpdateBy(inbound.getUpdateBy());
		updateHeader.setUpdateTime(new Date());
		
		InboundHeaderTExample example = new InboundHeaderTExample();
		InboundHeaderTExample.Criteria criteria =example.createCriteria()
				.andCompanyIdEqualTo(inbound.getCompanyId())
				.andInboundHeaderIdEqualTo(inbound.getInboundHeaderId());
		if(null != inbound.getWarehouseId()&&OperatorTypeEnum.Confirm != inbound.getOperatorType()){
			//报关确认不能传仓库Id
			criteria.andWarehouseIdEqualTo(inbound.getWarehouseId());
		}
		
		int rowcount = inboundHeaderDao.updateWithVersionByExampleSelective(selectHeader.getUpdateVersion(), updateHeader, example);
		if (rowcount == 0)
			throw new BusinessServiceException("record update error.");
		
		return Boolean.TRUE;
	}

    @Override
    @Transactional
    public Boolean modify(InboundHeaderTEntity inbound) throws BusinessServiceException {
		InboundHeaderTEntity updateHeader = InboundHeaderTEntity.builder()
				.status(inbound.getStatus())
				.updateBy(inbound.getUpdateBy())
				.updateTime(new Date())
				.build();
		if(null != inbound.getClosedDate()){
			updateHeader.setClosedDate(inbound.getClosedDate());
		}
        InboundHeaderTExample example = new InboundHeaderTExample();
        example.createCriteria()
                .andWarehouseIdEqualTo(inbound.getWarehouseId())
                .andCompanyIdEqualTo(inbound.getCompanyId())
                .andInboundHeaderIdEqualTo(inbound.getInboundHeaderId());

        int rowcount = inboundHeaderDao.updateWithVersionByExampleSelective(inbound.getUpdateVersion(), updateHeader, example);
        if (rowcount == 0) {
            throw new BusinessServiceException("record update error.");
        }
		return Boolean.TRUE;
    }


    @Override
	public Boolean add(InboundVO inbound) throws BusinessServiceException {
		inbound.setInboundHeaderId(KeyUtils.getUID());
		InboundHeaderTEntity header = new InboundHeaderTEntity();
		BeanUtils.copyBeanProp(header, inbound, Boolean.FALSE);
		header.setCreateBy(inbound.getCreateBy());
		header.setCreateTime(new Date());
		header.setUpdateBy(inbound.getUpdateBy());
		header.setUpdateTime(new Date());
		
		int rowcount = inboundHeaderDao.insertSelective(header);
		if (rowcount == 0)
			throw new BusinessServiceException("record add error.");
		
		return Boolean.TRUE;
	}


	@Override
	public Boolean delete(AjaxRequest<List<InboundHeaderTEntity>> request) throws BusinessServiceException {
		
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<InboundHeaderTEntity> list = request.getData();
		
		list.forEach(h -> {
			InboundHeaderTEntity header = new InboundHeaderTEntity();
			header.setCompanyId(request.getCompanyId());
			header.setInboundHeaderId(h.getInboundHeaderId());

			InboundHeaderTExample  example= new InboundHeaderTExample();
			example.createCriteria()
					.andCompanyIdEqualTo(header.getCompanyId())
					.andInboundHeaderIdEqualTo(header.getInboundHeaderId());
			if(0L != request.getWarehouseId()){
				header.setWarehouseId(request.getWarehouseId());
				header =find(header);
				boolean statusFlag = notProcess(header);
				if (statusFlag) {
					InboundStatusEnum status = inboundStatus(header, Boolean.FALSE);
					if (InboundStatusEnum.New != status) {
						throw new BusinessServiceException("InboundHeaderServiceImpl", "inbound.status.not.process" , new Object[] {header.getInboundNumber()});
					}
				}
				example.createCriteria().andWarehouseIdEqualTo(header.getWarehouseId());
			}else {//不存在仓库id，外部系统新建订单
				header =find(header);
				if(!StringUtils.equals(InboundStatusEnum.Draft.getCode(),header.getStatus())){
					throw new BusinessServiceException("InboundHeaderServiceImpl", "inbound.status.not.process" , new Object[] {header.getInboundNumber()});
				}
			}
			
			inboundDetailService.delete(header);

			InboundHeaderTEntity update = InboundHeaderTEntity.builder()
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.delFlag(YesNoEnum.Yes.getCode())
					.build();



			int rowcount = inboundHeaderDao.updateWithVersionByExampleSelective(header.getUpdateVersion(), update, example);
			if (rowcount == 0) {
				throw new BusinessServiceException("record delete error.");
			}
		});
		
		return Boolean.TRUE;
	}


	@Override
	@Transactional
	public InboundStatusEnum inboundStatus(InboundHeaderTEntity header, Boolean updateFlag)
			throws BusinessServiceException {
		InboundStatusEnum status = null;
		List<InboundDetailTEntity> detailList = inboundDetailService.findByHeaderId(InboundDetailTEntity.builder()
											.warehouseId(header.getWarehouseId())
											.companyId(header.getCompanyId())
											.inboundHeaderId(header.getInboundHeaderId())
											.build());
		
		Set<InboundStatusEnum> detailStatusSet = Sets.newHashSet();
		detailList.forEach(d -> {
			InboundStatusEnum detailStatus = inboundDetailService.inboundDetailStatus(d, Boolean.FALSE);
			detailStatusSet.add(detailStatus);
		});
		if (detailStatusSet.size() == 0)
			status = InboundStatusEnum.New;
		else if (detailStatusSet.size() == 1) {
			status = detailStatusSet.iterator().next();
		}
		else if (detailStatusSet.size() > 1) {
			status = InboundStatusEnum.InReceive;
		}
		
		//计算收货行与预收货行是否数量全部接收完成
		if (status == InboundStatusEnum.InReceive) {
			BigDecimal sumExpected = BigDecimal.ZERO;
			BigDecimal sumReceive = BigDecimal.ZERO;
			for (InboundDetailTEntity d : detailList) {
				sumExpected = sumExpected.add(d.getQuantityExpected());
				sumReceive = sumReceive.add(d.getQuantityReceive());
			}
			if (sumReceive.compareTo(sumExpected) >= 0)
				status = InboundStatusEnum.Receive;
		}
		
		if (!updateFlag)
			return status;
		
		InboundHeaderTEntity selectHeader = find(InboundHeaderTEntity.builder()
												.warehouseId(header.getWarehouseId())
												.companyId(header.getCompanyId())
												.inboundHeaderId(header.getInboundHeaderId())
												.status(status.getCode())
												.build());
		
		InboundHeaderTEntity updateHeader = InboundHeaderTEntity.builder()
												.status(status.getCode())
												.build();
		InboundHeaderTExample example = new InboundHeaderTExample();
		example.createCriteria()
		.andWarehouseIdEqualTo(header.getWarehouseId())
		.andCompanyIdEqualTo(header.getCompanyId())
		.andInboundHeaderIdEqualTo(header.getInboundHeaderId());
		int rowcount = inboundHeaderDao.updateWithVersionByExampleSelective(selectHeader.getUpdateVersion(), updateHeader, example);
		if (rowcount == 0)
			throw new BusinessServiceException("record update error.");
		
		header.setStatus(status.getCode());
		
		return status;
	}

	@Override
	@Transactional
	public Boolean cancel(AjaxRequest<List<InboundHeaderTEntity>> request) throws BusinessServiceException {
		List<InboundHeaderTEntity> list = request.getData();

		list.forEach(d ->{
			InboundDetailTEntity detail = InboundDetailTEntity.builder()
					.warehouseId(d.getWarehouseId())
					.companyId(d.getCompanyId())
					.inboundHeaderId(d.getInboundHeaderId())
					.build();
			List<InboundDetailTEntity> detailList = inboundDetailService.findByHeaderId(detail);

			if(CollectionUtils.isNotEmpty(detailList)){
				detailList.forEach(v ->{
					if(!StringUtils.equals(v.getStatus(),InboundStatusEnum.New.getCode())){
						//明细有不为新建状态的不能取消
						throw new BusinessServiceException("InboundHeaderServiceImpl", "inbound.line.status.not.process" , new Object[] {v.getLineNumber()});
					}
					v.setUpdateBy(request.getUserName());
					v.setStatus(InboundStatusEnum.Cancel.getCode());
					v.setUpdateTime(new Date());
					v.setWarehouseId(request.getWarehouseId());
					v.setCompanyId(request.getCompanyId());
					v.setCancelDate(new Date());
					inboundDetailService.modify(v);
				});

			}
			d.setUpdateBy(request.getUserName());
			d.setStatus(InboundStatusEnum.Cancel.getCode());
            modify(d);
		});

		return Boolean.TRUE;
	}

    @Override
    @Transactional
    public Boolean close(AjaxRequest<List<InboundHeaderTEntity>> request) throws BusinessServiceException {
        List<InboundHeaderTEntity> list = request.getData();
		list.forEach(d ->{
			InboundDetailTEntity detail = InboundDetailTEntity.builder()
					.warehouseId(d.getWarehouseId())
					.companyId(d.getCompanyId())
					.inboundHeaderId(d.getInboundHeaderId())
					.build();
			List<InboundDetailTEntity> detailList = inboundDetailService.findByHeaderId(detail);
			int closeCount = 0;
			if(CollectionUtils.isNotEmpty(detailList)) {
				for (InboundDetailTEntity v : detailList) {
					if (InboundStatusEnum.Closed.getCode().equals(v.getStatus())
							|| InboundStatusEnum.Cancel.getCode().equals(v.getStatus())) {
						throw new BusinessServiceException("InboundHeaderServiceImpl", "inbound.line.status.not.process" ,  new Object[] {v.getLineNumber()});
					}
					if (!(InboundStatusEnum.InReceive.getCode().equals(v.getStatus())
							|| InboundStatusEnum.Receive.getCode().equals(v.getStatus()))) {
						continue;
					}
					v.setUpdateBy(request.getUserName());
                    v.setStatus(InboundStatusEnum.Closed.getCode());
                    v.setUpdateTime(new Date());
                    v.setWarehouseId(request.getWarehouseId());
                    v.setCompanyId(request.getCompanyId());
					inboundDetailService.modify(v);
					closeCount ++;
				}
			}
			if (closeCount == 0)
				throw new BusinessServiceException("InboundHeaderServiceImpl", "inbound.close.no.line" ,  new Object[] {d.getInboundNumber()});

			d.setStatus(InboundStatusEnum.Closed.getCode());
			d.setUpdateBy(request.getUserName());
			d.setClosedDate(new Date());
			modify(d);
		});
        return Boolean.TRUE;
    }

	@Override
	public Long createPutaway(AjaxRequest<List<InboundVO>> request) throws BusinessServiceException {
		List<InboundVO> list = request.getData();
		if (CollectionUtils.isEmpty(list)) {
			throw new BusinessServiceException("no data release.");
		}
		List<InboundDetailVO> detailList = getInboundDetail(request);

		long taskCount = inboundDetailService.createPutaway(detailList);
		return taskCount;
	}

	@Override
	@Transactional
	public InboundVO saveFromOms(AjaxRequest<InboundVO> request) throws BusinessServiceException {
		InboundVO inboundVO = request.getData();
		inboundVO.setCompanyId(request.getCompanyId());
		inboundVO.setUpdateBy(request.getUserName());
		inboundVO.setUpdateTime(new Date());


		StatusHistoryTEntity statusHistory = StatusHistoryTEntity.builder()
				.companyId(request.getCompanyId())
				.createBy(request.getUserName())
				.updateBy(request.getUserName())
				.createTime(new Date())
				.updateTime(new Date())
				.operTime(new Date())
				.build();

		switch (inboundVO.getOperatorType()) {
			case Add:
				inboundVO.setCreateBy(request.getUserName());
				inboundVO.setCreateTime(new Date());
				inboundVO.setStatus(InboundStatusEnum.Draft.getCode());
				add(inboundVO);
				break;
			case Modify:
				modify(inboundVO);
				break;
			case Submit:
				inboundVO.setStatus(InboundStatusEnum.WaitingReview.getCode());
				if(null == inboundVO.getInboundHeaderId()|| 0L == inboundVO.getInboundHeaderId()){//提交时新增
					inboundVO.setCreateBy(request.getUserName());
					inboundVO.setCreateTime(new Date());
					add(inboundVO);
				}else {
					modify(inboundVO);
				}
				statusHistory.setOldStatus(InboundStatusEnum.Draft.getCode());
				statusHistory.setNewStatus(InboundStatusEnum.WaitingReview.getCode());
				break;
			case Review:
                deal(inboundVO);
				inboundVO.setStatus(InboundStatusEnum.WaitingDeclaration.getCode());
				modify(inboundVO);
				statusHistory.setOldStatus(InboundStatusEnum.WaitingReview.getCode());
				statusHistory.setNewStatus(InboundStatusEnum.WaitingDeclaration.getCode());
				break;
			case Confirm:
				inboundVO.setStatus(InboundStatusEnum.New.getCode());
				inboundVO.setWarehouseId(inboundVO.getToWarehouseId());
				validate(inboundVO);
				modify(inboundVO);
				statusHistory.setOldStatus(InboundStatusEnum.New.getCode());
				statusHistory.setNewStatus(InboundStatusEnum.WaitingDeclaration.getCode());
				break;
			default:
				throw new BusinessServiceException("InboundHeaderServiceImpl", "opertiontype.not.exists" , null );
		}

		if(null != inboundVO.getInboundHeaderId()){
			statusHistory.setSourceNumber(inboundVO.getInboundHeaderId());
		}
		if(null != inboundVO.getInboundNumber()){
			statusHistory.setSourceBillNumber(inboundVO.getInboundNumber());
		}
		if(null != statusHistory.getNewStatus()){
			//有更新状态就插入记录
			statusHistoryService.add(statusHistory);
		}

		//process detail
		if (CollectionUtils.isNotEmpty(inboundVO.getDetail()))
			inboundDetailService.saveFromOms(request);

		return inboundVO;
	}


	@Override
	public Boolean receiveAll(AjaxRequest<List<InboundVO>> request) throws BusinessServiceException {
		
		List<InboundVO> list = request.getData();
		if (CollectionUtils.isEmpty(list))
			throw new BusinessServiceException("no update data.");
		
		Set<Long> headerIds = list.stream().map(InboundVO::getInboundHeaderId).collect(Collectors.toSet());
		
		InboundDetailTExample example = new InboundDetailTExample(); 
		InboundDetailTExample.Criteria criteria = example.createCriteria();
		criteria
		.andCompanyIdEqualTo(request.getCompanyId())
		.andWarehouseIdEqualTo(request.getWarehouseId())
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andInboundHeaderIdIn(Lists.newArrayList(headerIds));
		
		List<InboundDetailTEntity> detail = inboundDetailDao.selectByExample(example);
		if (CollectionUtils.isEmpty(detail))
			return Boolean.FALSE;
		
		
		List<InboundDetailVO> detailVO = Lists.newArrayList();
		detail.forEach(d -> {
			InboundDetailVO vo = new InboundDetailVO(d);
			vo.setUpdateBy(request.getUserName());
			vo.setTransactionCategory(TransactionCategoryEnum.PCAllReceive.getCode());
			detailVO.add(vo);
		});
		
		boolean receiveFlag = inboundDetailService.receiveAll(new AjaxRequest(detailVO, request));
		
		return receiveFlag;
	}


	@Override
	public Boolean unReceive(AjaxRequest<List<InboundVO>> request) throws BusinessServiceException {
		List<InboundVO> list = request.getData();
		if (CollectionUtils.isEmpty(list))
			throw new BusinessServiceException("no update data.");

		Set<Long> headerIds = list.stream().map(InboundVO::getInboundHeaderId).collect(Collectors.toSet());

		InboundDetailTExample example = new InboundDetailTExample();
		InboundDetailTExample.Criteria criteria = example.createCriteria();
		criteria
		.andCompanyIdEqualTo(request.getCompanyId())
		.andWarehouseIdEqualTo(request.getWarehouseId())
		.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andInboundHeaderIdIn(Lists.newArrayList(headerIds));

		List<InboundDetailTEntity> detail = inboundDetailDao.selectByExample(example);
		if (CollectionUtils.isEmpty(detail))
			return Boolean.FALSE;


		List<InboundDetailVO> detailVO = Lists.newArrayList();
		detail.forEach(d -> {
			InboundDetailVO vo = new InboundDetailVO(d);
			vo.setUpdateBy(request.getUserName());
			vo.setCreateBy(request.getUserName());
			detailVO.add(vo);
		});

		boolean unReceiveFlag = inboundDetailService.unReceive(new AjaxRequest<List<InboundDetailVO>>(detailVO, request));

		return unReceiveFlag;
	}
	
	private Boolean notProcess(InboundHeaderTEntity header) {
		if (InboundStatusEnum.Closed.getCode().equals(header.getStatus())
				|| InboundStatusEnum.Cancel.getCode().equals(header.getStatus())) {
			throw new BusinessServiceException("InboundDetailServiceImpl", "inbound.status.not.process" , new Object[] {header.getInboundNumber()});
		}
		return Boolean.TRUE;
	}

	private List<InboundDetailVO> getInboundDetail(AjaxRequest<List<InboundVO>> request){
		List<InboundVO> inboundList =request.getData();
		List<InboundDetailVO> detailVO = Lists.newArrayList();

		inboundList.forEach(d -> {
			InboundDetailTEntity inboundDetail = InboundDetailTEntity.builder()
					.warehouseId(request.getWarehouseId())
					.companyId(request.getCompanyId())
					.inboundHeaderId(d.getInboundHeaderId())
					.build();
			List<InboundDetailTEntity> detail = inboundDetailService.findByHeaderId(inboundDetail);
			if (CollectionUtils.isEmpty(detail)) {
				return;
			}
			detail.forEach(v->{
				InboundDetailVO vo = new InboundDetailVO(v);
				vo.setInboundNumber(d.getInboundNumber());
				vo.setUpdateBy(request.getUserName());
				detailVO.add(vo);
			});
		});

		if (CollectionUtils.isEmpty(detailVO)) {
			return Lists.newArrayList();
		}
		return detailVO;
	}

	/** 
	* @Description: 完善货主，供应商和承运人
	* @Param: [inbound] 
	* @return: void 
	* @Author: pengzhen@cmhit.com 
	* @Date: 2019/8/28 
	*/ 
	private void deal(InboundVO inbound) {
		if(StringUtils.isNotBlank(inbound.getOwnerCode())){
			//根据仓库+code查询该仓库是否存在货主，不存在则新建
			Set<String> code = Sets.newHashSet();
			OwnerTEntity owner = OwnerTEntity.builder()
					.companyId(inbound.getCompanyId())
					.ownerCode(inbound.getOwnerCode())
					.warehouseId(inbound.getToWarehouseId())
					.build();
			code.add(inbound.getOwnerCode());
			List<OwnerTEntity> owners = ownerService.findByOwnerCodes(owner,code);
			if(CollectionUtils.isEmpty(owners)){
				EntOwnerTEntity enOwner = EntOwnerTEntity.builder()
						.companyId(inbound.getCompanyId())
						.ownerCode(inbound.getOwnerCode())
						.build();
				EntOwnerTEntity select = enterpriseService.findOwner(enOwner);
				BeanUtils.copyBeanProp(owner, select, Boolean.FALSE);
				owner.setWarehouseId(inbound.getToWarehouseId());
				owner.setCompanyId(inbound.getCompanyId());
				owner.setUpdateBy(inbound.getUpdateBy());
				owners.add(owner);

				ownerService.add(owners);
			}
		}
		if(StringUtils.isNotBlank(inbound.getCarrierCode())){
			//承运人
			CarrierTEntity carrier = carrierService.findByCode(CarrierTEntity.builder()
					.warehouseId(inbound.getToWarehouseId())
					.companyId(inbound.getCompanyId())
					.carrierCode(inbound.getCarrierCode())
					.build());
			if(null == carrier){
				EntCarrierTEntity enCarrier = EntCarrierTEntity.builder()
					.companyId(inbound.getCompanyId())
					.carrierCode(inbound.getCarrierCode())
					.build();
				EntCarrierTEntity select = enterpriseService.findCarrier(enCarrier);
				List<CarrierTEntity> carriers = Lists.newArrayList();
				CarrierTEntity insert = new CarrierTEntity();
				if(null != select){
					BeanUtils.copyBeanProp(insert, select, Boolean.FALSE);
					insert.setWarehouseId(inbound.getToWarehouseId());
					insert.setCompanyId(inbound.getCompanyId());
					insert.setUpdateBy(inbound.getUpdateBy());
				}else {
					insert.setActive(YesNoEnum.Yes.getCode());
					insert.setCompanyId(inbound.getCompanyId());
					insert.setWarehouseId(inbound.getToWarehouseId());
					insert.setCarrierCode(inbound.getCarrierCode());
					insert.setCarrierDescr(inbound.getCarrierCode());
					insert.setContact1(inbound.getCarrierDriver());
					insert.setAddress1(inbound.getShipAddress1());
					insert.setAddress2(inbound.getShipAddress2());
				}
				carriers.add(insert);
				carrierService.add(carriers);
			}
		}
		if(StringUtils.isNotBlank(inbound.getSupplierCode())){
			//供应商
			SupplierTEntity supplier = SupplierTEntity.builder()
					.companyId(inbound.getCompanyId())
					.warehouseId(inbound.getToWarehouseId())
					.supplierCode(inbound.getSupplierCode())
					.build();
			SupplierTEntity select = supplierService.findByCode(supplier);
			if(null == select){
				EntSupplierTEntity entSupplier = EntSupplierTEntity.builder()
						.companyId(inbound.getCompanyId())
						.supplierCode(inbound.getSupplierCode())
						.build();
				List<SupplierTEntity> suppliers = Lists.newArrayList();
				entSupplier = enterpriseService.findSupplier(entSupplier);
				BeanUtils.copyBeanProp(supplier, entSupplier, Boolean.FALSE);
				supplier.setWarehouseId(inbound.getToWarehouseId());
				supplier.setCompanyId(inbound.getCompanyId());
				supplier.setUpdateBy(inbound.getUpdateBy());
				supplier.setActive(YesNoEnum.Yes.getCode());
				suppliers.add(supplier);

				supplierService.add(suppliers);
			}

		}
	}
}
