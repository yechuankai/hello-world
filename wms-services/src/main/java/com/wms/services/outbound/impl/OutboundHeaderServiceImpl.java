package com.wms.services.outbound.impl;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.InboundStatusEnum;
import com.wms.common.enums.OperatorTypeEnum;
import com.wms.common.enums.OrderNumberTypeEnum;
import com.wms.common.enums.OutboundProcessStatusEnum;
import com.wms.common.enums.OutboundStatusEnum;
import com.wms.common.enums.TaskStatusEnum;
import com.wms.common.enums.TaskTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.DateUtils;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.IOutboundHeaderTDao;
import com.wms.dao.example.OutboundHeaderTExample;
import com.wms.entity.auto.CarrierTEntity;
import com.wms.entity.auto.CustomerTEntity;
import com.wms.entity.auto.EntCarrierTEntity;
import com.wms.entity.auto.EntCustomerTEntity;
import com.wms.entity.auto.EntInventoryOnhandTEntity;
import com.wms.entity.auto.OutboundDetailTEntity;
import com.wms.entity.auto.OutboundHeaderTEntity;
import com.wms.entity.auto.OwnerTEntity;
import com.wms.entity.auto.PackTEntity;
import com.wms.entity.auto.StatusHistoryTEntity;
import com.wms.entity.auto.SysWarehousesTEntity;
import com.wms.entity.auto.TaskDetailTEntity;
import com.wms.entity.auto.WaveBuildDetailTEntity;
import com.wms.services.base.ICarrierService;
import com.wms.services.base.ICustomerService;
import com.wms.services.base.IEnterpriseService;
import com.wms.services.base.IOwnerService;
import com.wms.services.base.IPackService;
import com.wms.services.inventory.ITaskService;
import com.wms.services.outbound.IOutboundDetailService;
import com.wms.services.outbound.IOutboundHeaderService;
import com.wms.services.sys.IStatusHistoryService;
import com.wms.services.sys.ISysWarehouseService;
import com.wms.shiro.utils.WaveBuildUtils;
import com.wms.vo.inventory.EntInventoryOnhandVO;
import com.wms.vo.outbound.OutboundDetailVO;
import com.wms.vo.outbound.OutboundVO;
import com.wms.vo.outbound.WaveBuildVO;

@Service
public class OutboundHeaderServiceImpl implements IOutboundHeaderService {

	public static final String NO_WAVE = "NO_WAVE";
	@Autowired
	private IOutboundHeaderTDao outboundHeaderDao;
	@Autowired
	private IOutboundDetailService outboundDetailService;
	@Autowired
	private IOwnerService ownerService;
	@Autowired
	private ICustomerService customerService;
	@Autowired
	IStatusHistoryService statusHistoryService;
	@Autowired
	private IPackService packService;
	@Autowired
	private ICarrierService carrierService;
	@Autowired
	private IEnterpriseService enterpriseService;
	@Autowired
	private ISysWarehouseService warehouseService;
	@Autowired
	private ITaskService taskService;

	@Override
	public List<OutboundHeaderTEntity> find(PageRequest request) throws BusinessServiceException {
		OutboundHeaderTExample example = new OutboundHeaderTExample();
		OutboundHeaderTExample.Criteria exampleCriteria = example.createCriteria();

		//转换查询方法
		ExampleUtils.create(OutboundHeaderTEntity.Column.class, OutboundHeaderTExample.Criterion.class)
				.criteria(exampleCriteria)
				.data(request)
				.betweenDate(OutboundHeaderTEntity.Column.createTime.getJavaProperty(),
						OutboundHeaderTEntity.Column.expectedOutboundDate.getJavaProperty(),
						OutboundHeaderTEntity.Column.outboundDate.getJavaProperty()
				)
				.build(request)
				.orderby(example);

		if (StringUtils.isBlank(request.getString("from")) 
				&& StringUtils.isBlank(request.getString("status"))) {
			//WMS自己查询排除草稿、待接单、待出货三个状态的订单
			List<String> excludeStatus = Lists.newArrayList();
			excludeStatus.add(OutboundStatusEnum.Draft.getCode());
			excludeStatus.add(OutboundStatusEnum.WaitingReview.getCode());
			exampleCriteria.andStatusNotIn(excludeStatus);
		}
		
		if (YesNoEnum.Yes.getCode().equals(request.getString(NO_WAVE))) {
			exampleCriteria.andSourceWaveNumberIsNull();
		}
		
		
		exampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());

		List<OutboundHeaderTEntity> inboundList = outboundHeaderDao.selectByExample(example);

		return inboundList;
	}

	@Override
	public List<OutboundVO> findFromOms(PageRequest request) throws BusinessServiceException {
		OutboundHeaderTExample example = new OutboundHeaderTExample();
		OutboundHeaderTExample.Criteria exampleCriteria = example.createCriteria();

		//转换查询方法
		ExampleUtils.create(OutboundHeaderTEntity.Column.class, OutboundHeaderTExample.Criterion.class)
				.criteria(exampleCriteria)
				.data(request)
				.betweenDate(OutboundHeaderTEntity.Column.createTime.getJavaProperty(),
						OutboundHeaderTEntity.Column.expectedOutboundDate.getJavaProperty(),
						OutboundHeaderTEntity.Column.outboundDate.getJavaProperty()
				)
				.build(request)
				.orderby(example);
		exampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());

		List<OutboundHeaderTEntity> outboundList = outboundHeaderDao.selectByExample(example);

		if(CollectionUtils.isEmpty(outboundList)){
			return Lists.newArrayList();
		}
		//根据id补足warehouseCode
		List<OutboundVO> returnList = Lists.newArrayList();

		Set<Long> ids = Sets.newHashSet(outboundList.stream().map(OutboundHeaderTEntity::getWarehouseId).collect(Collectors.toSet()));
		List<SysWarehousesTEntity> selectList =warehouseService.findByIds(ids);

		outboundList.forEach(d ->{
			OutboundVO outboundVO =new OutboundVO(d);
			selectList.forEach(v ->{
				if(d.getWarehouseId().longValue()==v.getWarehouseId().longValue()){
					outboundVO.setWarehouseCode(v.getCode());
					returnList.add(outboundVO);
				}
			});
		});
		return returnList;
	}

	@Override
	public OutboundVO find(OutboundVO outbound) throws BusinessServiceException {
		OutboundHeaderTEntity header = find((OutboundHeaderTEntity) outbound);
		
		/*InboundDetailTEntity detail = InboundDetailTEntity.builder()
				.warehouseId(inbound.getWarehouseId())
				.companyId(inbound.getCompanyId())
				.inboundHeaderId(inbound.getInboundHeaderId())
				.build();*/
		
		
		OutboundVO outboundVO = new OutboundVO(header);
		
		return outboundVO;
	}

	@Override
	public OutboundHeaderTEntity find(OutboundHeaderTEntity outbound) throws BusinessServiceException {
		
		OutboundHeaderTExample TExample = new OutboundHeaderTExample();
		OutboundHeaderTExample.Criteria criteria = TExample.createCriteria();
		
		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
		  .andCompanyIdEqualTo(outbound.getCompanyId());
		  
		  if(null != outbound.getWarehouseId()){
		   criteria.andWarehouseIdEqualTo(outbound.getWarehouseId());
		  }
		
		int conditionCount = 0;
		if (StringUtils.isNotEmpty(outbound.getOutboundNumber())) {
			criteria.andOutboundNumberEqualTo(outbound.getOutboundNumber());
			conditionCount ++;
		}
		if (outbound.getOutboundHeaderId() != null) {
			criteria.andOutboundHeaderIdEqualTo(outbound.getOutboundHeaderId());
			conditionCount ++;
		}
		if (conditionCount == 0) {
			return null;
		}
		OutboundHeaderTEntity selectoutbound = outboundHeaderDao.selectOneByExample(TExample);
		if (selectoutbound == null) {
			throw new BusinessServiceException("OutboundHeaderServiceImpl", "outbound.record.exists" , new Object[] {outbound.getOutboundNumber()}); 
		}
		return selectoutbound;
	}
	
	@Override
	public List<OutboundHeaderTEntity> find(OutboundHeaderTEntity outbound, Set<Long> ids) throws BusinessServiceException {
		if(CollectionUtils.isEmpty(ids))
			return Lists.newArrayList();
		
		OutboundHeaderTExample TExample = new OutboundHeaderTExample();
		OutboundHeaderTExample.Criteria criteria = TExample.createCriteria();
		
		criteria.andDelFlagEqualTo(YesNoEnum.No.getCode())
		.andWarehouseIdEqualTo(outbound.getWarehouseId())
		.andCompanyIdEqualTo(outbound.getCompanyId())
		.andOutboundHeaderIdIn(Lists.newArrayList(ids));
		
		List<OutboundHeaderTEntity> outbounds = outboundHeaderDao.selectByExample(TExample);
		if (outbounds == null) {
			return Lists.newArrayList();
		}
		return outbounds;
	}

	@Override
	public OutboundVO save(AjaxRequest<OutboundVO> request) throws BusinessServiceException {
		OutboundVO outboundVO=request.getData();
		outboundVO.setCompanyId(request.getCompanyId());
		outboundVO.setWarehouseId(request.getWarehouseId());
		outboundVO.setUpdateBy(request.getUserName());
		outboundVO.setUpdateTime(new Date());

		validate(outboundVO);
		switch (outboundVO.getOperatorType()) {
			case Add:
				outboundVO.setCreateBy(request.getUserName());
				outboundVO.setCreateTime(new Date());
				add(outboundVO);
				//产生装车任务
				loadTask(outboundVO, OperatorTypeEnum.Add);
				break;
			case Modify:
				modify(outboundVO);
				break;
			default:
				throw new BusinessServiceException("OutboundHeaderServiceImpl", "opertiontype.not.exists" , null );
		}
		//process detail
		if (CollectionUtils.isNotEmpty(outboundVO.getDetail())) {
			outboundDetailService.save(request);
		}
		return outboundVO;
	}

	@Override
	@Transactional
	public Boolean modify(OutboundVO outbound) throws BusinessServiceException {
		OutboundHeaderTEntity selectHeader = find((OutboundHeaderTEntity)outbound);

		if (OutboundStatusEnum.Cancel.getCode().equals(selectHeader.getStatus())) {
			throw new BusinessServiceException("OutboundHeaderServiceImpl", "outbound.status.not.process" , new Object[] {selectHeader.getOutboundNumber()});
		}
		OutboundStatusEnum status = null;
		if(!StringUtils.equals(selectHeader.getStatus(),OutboundStatusEnum.Draft.getCode())&&!StringUtils.equals(selectHeader.getStatus(),OutboundStatusEnum.WaitingReview.getCode())){
			//非草稿,待接单，待出货状态就自动计算状态
			status= outboundStatus(selectHeader,Boolean.FALSE);
		}


		OutboundHeaderTEntity updateHeader = new OutboundHeaderTEntity();
		BeanUtils.copyBeanProp(updateHeader, outbound, Boolean.FALSE);
		updateHeader.setUpdateBy(outbound.getUpdateBy());
		updateHeader.setUpdateTime(new Date());

		if(null != status){
			updateHeader.setStatus(status.getCode());
		}else {
			updateHeader.setStatus(outbound.getStatus());
		}


		OutboundHeaderTExample example = new OutboundHeaderTExample();
		example.createCriteria()
				.andWarehouseIdEqualTo(outbound.getWarehouseId())
				.andCompanyIdEqualTo(outbound.getCompanyId())
				.andOutboundHeaderIdEqualTo(outbound.getOutboundHeaderId());

		//采用多线程并发时，不可按最后更新版本来更新数据
		//int rowcount = outboundHeaderDao.updateWithVersionByExampleSelective(selectHeader.getUpdateVersion(), updateHeader, example);
		int rowcount = outboundHeaderDao.updateByExampleSelective( updateHeader, example);
		if (rowcount == 0) {
			throw new BusinessServiceException("record update error.");
		}
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean modify(OutboundHeaderTEntity outbound) throws BusinessServiceException {
        OutboundHeaderTEntity updateHeader = OutboundHeaderTEntity.builder()
                .sourceWaveNumber(outbound.getSourceWaveNumber())
        		.status(outbound.getStatus())
                .updateBy(outbound.getUpdateBy())
                .updateTime(new Date())
                .build();

		OutboundHeaderTExample example = new OutboundHeaderTExample();
		example.createCriteria()
				.andWarehouseIdEqualTo(outbound.getWarehouseId())
				.andCompanyIdEqualTo(outbound.getCompanyId())
				.andOutboundHeaderIdEqualTo(outbound.getOutboundHeaderId());

		int rowcount = outboundHeaderDao.updateWithVersionByExampleSelective(outbound.getUpdateVersion(), updateHeader, example);
		if (rowcount == 0) {
			throw new BusinessServiceException("record update error.");
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean add(OutboundVO outbound) throws BusinessServiceException {
		outbound.setOutboundHeaderId(KeyUtils.getUID());
		OutboundHeaderTEntity  header = new OutboundHeaderTEntity();
        BeanUtils.copyBeanProp(header, outbound, Boolean.FALSE);
		header.setCreateBy(outbound.getCreateBy());
		header.setCreateTime(new Date());
		header.setUpdateBy(outbound.getUpdateBy());
		header.setUpdateTime(new Date());

        int rowcount = outboundHeaderDao.insertSelective(header);
        if (rowcount == 0) {
            throw new BusinessServiceException("record add error.");
        }
        return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean delete(AjaxRequest<List<OutboundHeaderTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<OutboundHeaderTEntity> list = request.getData();

		list.forEach(h -> {
			
			OutboundHeaderTEntity header = OutboundHeaderTEntity.builder()
			.companyId(request.getCompanyId())
			.outboundHeaderId(h.getOutboundHeaderId())
			.build();
			
			OutboundHeaderTExample  example = new OutboundHeaderTExample();
			OutboundHeaderTExample.Criteria criteria = example.createCriteria();
			criteria.andCompanyIdEqualTo(request.getCompanyId())
					.andOutboundHeaderIdEqualTo(header.getOutboundHeaderId());
			
			if (h.getWarehouseId() != null && 0L != h.getWarehouseId()) {
				criteria.andWarehouseIdEqualTo(request.getWarehouseId());
				header = find(OutboundHeaderTEntity.builder()
						.warehouseId(request.getWarehouseId())
						.companyId(request.getCompanyId())
						.outboundHeaderId(h.getOutboundHeaderId())
						.build());
				OutboundStatusEnum status = outboundStatus(header, Boolean.FALSE);
				if (OutboundStatusEnum.New != status) {
					throw new BusinessServiceException("OutboundHeaderServiceImpl", "outbound.status.not.process" , new Object[] {header.getOutboundNumber()});
				}
			}else {//不存在仓库id，外部系统新建订单
				header = find(header);
				if(!StringUtils.equals(InboundStatusEnum.Draft.getCode(),header.getStatus())){
					throw new BusinessServiceException("OutboundHeaderServiceImpl", "outbound.status.not.process" , new Object[] {header.getOutboundNumber()});
				}
			}

			outboundDetailService.delete(header);

			OutboundHeaderTEntity update = OutboundHeaderTEntity.builder()
					.updateBy(request.getUserName())
					.updateTime(new Date())
					.delFlag(YesNoEnum.Yes.getCode())
					.build();

			int rowcount = outboundHeaderDao.updateWithVersionByExampleSelective(header.getUpdateVersion(), update, example);
			if (rowcount == 0) {
				throw new BusinessServiceException("record delete error.");
			}
			
			if (0L != request.getWarehouseId()) {
				//取消装车任务
				loadTask(header, OperatorTypeEnum.Cancel);
			}
			
		});

		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public OutboundStatusEnum outboundStatus(OutboundHeaderTEntity header, Boolean updateFlag)
			throws BusinessServiceException {
		OutboundStatusEnum status = null;
		List<OutboundDetailTEntity> detailList = outboundDetailService.findByHeaderId(OutboundDetailTEntity.builder()
											.warehouseId(header.getWarehouseId())
											.companyId(header.getCompanyId())
											.outboundHeaderId(header.getOutboundHeaderId())
											.build());

		Set<OutboundStatusEnum> detailStatusSet = Sets.newHashSet();
		detailList.forEach(d -> {
			OutboundStatusEnum detailStatus = outboundDetailService.outboundDetailStatus(d, Boolean.FALSE);
			detailStatusSet.add(detailStatus);
		});
		if (detailStatusSet.size() == 0)
			status = OutboundStatusEnum.New;
		else if (detailStatusSet.size() == 1) {
			status = detailStatusSet.iterator().next();
		}
		else if (detailStatusSet.size() > 1) {
			if (detailStatusSet.contains(OutboundStatusEnum.Shiped)) {
				status = OutboundStatusEnum.PartShiped;
			}else if (detailStatusSet.contains(OutboundStatusEnum.Picked)) {
				status = OutboundStatusEnum.PartPicked;
			}else if (detailStatusSet.contains(OutboundStatusEnum.Allocated)) {
				status = OutboundStatusEnum.PartAllocated;
			}else {
				status = detailStatusSet.stream().max(new Comparator<OutboundStatusEnum>() {
					@Override
					public int compare(OutboundStatusEnum o1, OutboundStatusEnum o2) {
						return o1.ordinal() - o2.ordinal();
					}
				}).get();
			}
		}

		if (!updateFlag)
			return status;

		OutboundHeaderTEntity selectHeader = find(OutboundHeaderTEntity.builder()
												.warehouseId(header.getWarehouseId())
												.companyId(header.getCompanyId())
												.outboundHeaderId(header.getOutboundHeaderId())
												.status(status.getCode())
												.build());

		OutboundHeaderTEntity updateHeader = OutboundHeaderTEntity.builder()
												.status(status.getCode())
												.updateBy(header.getUpdateBy())
												.updateTime(new Date())
												.build();
		OutboundHeaderTExample example = new OutboundHeaderTExample();
		example.createCriteria()
		.andWarehouseIdEqualTo(header.getWarehouseId())
		.andCompanyIdEqualTo(header.getCompanyId())
		.andOutboundHeaderIdEqualTo(header.getOutboundHeaderId());
		//多线程并发问题，不按版本进行更新
		//int rowcount = outboundHeaderDao.updateWithVersionByExampleSelective(selectHeader.getUpdateVersion(), updateHeader, example);
		int rowcount = outboundHeaderDao.updateByExampleSelective(updateHeader, example);
		if (rowcount == 0)
			throw new BusinessServiceException("record update error.");

		header.setStatus(status.getCode());

		return status;
	}
	
	@Override
	//@Transactional 此方法无需事务处理
	public Boolean processStatus(OutboundHeaderTEntity header, OutboundProcessStatusEnum processStatus)
			throws BusinessServiceException {
		OutboundHeaderTEntity updateHeader = OutboundHeaderTEntity.builder()
												.processStatus(processStatus.getCode())
												.updateBy(header.getUpdateBy())
												.updateTime(new Date())
												.build();
		OutboundHeaderTExample example = new OutboundHeaderTExample();
		example.createCriteria()
		.andWarehouseIdEqualTo(header.getWarehouseId())
		.andCompanyIdEqualTo(header.getCompanyId())
		.andOutboundHeaderIdEqualTo(header.getOutboundHeaderId());
		int rowcount = outboundHeaderDao.updateByExampleSelective(updateHeader, example);
		if (rowcount == 0)
			throw new BusinessServiceException("record update error.");

		return Boolean.TRUE;
	}
	
	private List<OutboundDetailVO> getOutboundDetail(AjaxRequest<List<OutboundVO>> request){
		Set<Long> headerIds = request.getData().stream().map(OutboundVO::getOutboundHeaderId).collect(Collectors.toSet());
		
		OutboundDetailTEntity outboundDetail = OutboundDetailTEntity.builder()
												.warehouseId(request.getWarehouseId())
												.companyId(request.getCompanyId())
												.build();
		
		List<OutboundDetailTEntity> detail = outboundDetailService.findByHeaderIds(outboundDetail, headerIds);
		if (CollectionUtils.isEmpty(detail))
			return Lists.newArrayList();
		
		List<OutboundDetailVO> detailVO = Lists.newArrayList();
		detail.forEach(d -> {
			OutboundDetailVO vo = new OutboundDetailVO(d);
			vo.setUpdateBy(request.getUserName());
			vo.setCreateBy(request.getUserName());
			detailVO.add(vo);
		});
		return detailVO;
	}

	@Override
	public Boolean allocate(AjaxRequest<List<OutboundVO>> request) throws BusinessServiceException {
		List<OutboundVO> list = request.getData();
		if (CollectionUtils.isEmpty(list))
			throw new BusinessServiceException("no data allocate.");
		List<OutboundDetailVO> detailVO = getOutboundDetail(request);
		boolean allocateFlag = outboundDetailService.allocate(detailVO);
		return allocateFlag;
	}

	@Override
	public Boolean unAllocate(AjaxRequest<List<OutboundVO>> request) throws BusinessServiceException {
		List<OutboundVO> list = request.getData();
		if (CollectionUtils.isEmpty(list))
			throw new BusinessServiceException("no data unAllocate.");
		
		List<OutboundDetailVO> detailVO = getOutboundDetail(request);
		
		boolean unAllocateFlag = outboundDetailService.unAllocate(detailVO);

		return unAllocateFlag;
	}

	@Override
	public Boolean pick(AjaxRequest<List<OutboundVO>> request) throws BusinessServiceException {
		List<OutboundVO> list = request.getData();
		if (CollectionUtils.isEmpty(list))
			throw new BusinessServiceException("no data pick.");
		
		List<OutboundDetailVO> detailVO = getOutboundDetail(request);
		
		boolean pickFlag = outboundDetailService.pick(detailVO);

		return pickFlag;
	}

	@Override
	public Boolean unPick(AjaxRequest<List<OutboundVO>> request) throws BusinessServiceException {
		List<OutboundVO> list = request.getData();
		if (CollectionUtils.isEmpty(list))
			throw new BusinessServiceException("no data unAllocate.");
		
		List<OutboundDetailVO> detailVO = getOutboundDetail(request);
		
		boolean unPickFlag = outboundDetailService.unPick(detailVO);

		return unPickFlag;
	}

	@Override
	public Boolean ship(AjaxRequest<List<OutboundVO>> request) throws BusinessServiceException {
		List<OutboundVO> list = request.getData();
		if (CollectionUtils.isEmpty(list))
			throw new BusinessServiceException("no data Ship.");
		
		List<OutboundDetailVO> detailVO = getOutboundDetail(request);
		
		boolean shipFlag = outboundDetailService.ship(detailVO);
		
		//发运完成更新装车任务为完成
		list.forEach(h -> {
			h.setWarehouseId(request.getWarehouseId());
			h.setCompanyId(request.getCompanyId());
			OutboundHeaderTEntity header = find(h);
			if (OutboundStatusEnum.Shiped.getCode().equals(header.getStatus())) {
				loadTask(header, OperatorTypeEnum.Complate);
			}
		});
		return shipFlag;
	}

    @Override
	@Transactional
    public Boolean release(AjaxRequest<List<OutboundVO>> request) throws BusinessServiceException {
		List<OutboundVO> list = request.getData();
		if (CollectionUtils.isEmpty(list)) {
			throw new BusinessServiceException("no data release.");
		}
		List<OutboundDetailVO> detailList = getOutboundDetail(request);

        boolean releaseFlag = outboundDetailService.release(detailList);
		if (releaseFlag) {
			//发放生成任务明细成功修改出库单状态
			list.forEach(v -> {
				//判断明细是否全部都已发放
				OutboundDetailTEntity selectDetail = OutboundDetailTEntity.builder()
						.companyId(request.getCompanyId())
						.warehouseId(request.getWarehouseId())
						.outboundHeaderId(v.getOutboundHeaderId())
						.build();
				List<OutboundDetailTEntity> details = outboundDetailService.findByHeaderId(selectDetail);
				boolean flag = false;
				for (OutboundDetailTEntity detail : details) {
					if (!StringUtils.equals(OutboundStatusEnum.Release.getCode(), detail.getStatus())) {
						flag = true;
					}
				}
				if (flag) {
					return;
				}
				v.setCompanyId(request.getCompanyId());
				v.setWarehouseId(request.getWarehouseId());
				v.setUpdateBy(request.getUserName());
				v.setUpdateTime(new Date());
				v.setStatus(OutboundStatusEnum.Release.getCode());
				OutboundHeaderTEntity outbound = v;
				modify(outbound);
			});
		}
        return Boolean.TRUE;
    }

    @Override
	@Transactional
    public Boolean cancel(AjaxRequest<List<OutboundHeaderTEntity>> request) throws BusinessServiceException {
		List<OutboundHeaderTEntity> list = request.getData();

		list.forEach(d ->{
			OutboundDetailTEntity detail = OutboundDetailTEntity.builder()
					.warehouseId(d.getWarehouseId())
					.companyId(d.getCompanyId())
					.outboundHeaderId(d.getOutboundHeaderId())
					.build();
			List<OutboundDetailTEntity> detailList = outboundDetailService.findByHeaderId(detail);

			if(CollectionUtils.isNotEmpty(detailList)){
				detailList.forEach(v ->{
					if(!StringUtils.equals(v.getStatus(),InboundStatusEnum.New.getCode())){
						//明细有不为新建状态的不能取消
						throw new BusinessServiceException("OutboundHeaderServiceImpl", "outbound.line.status.not.process" , new Object[] {v.getLineNumber()});
					}
					v.setUpdateBy(request.getUserName());
					v.setStatus(OutboundStatusEnum.Cancel.getCode());
					outboundDetailService.modify(v);
				});
			}

			d.setStatus(OutboundStatusEnum.Cancel.getCode());
			d.setUpdateBy(request.getUserName());
			modify(d);
			
			//取消装车任务
			loadTask(d, OperatorTypeEnum.Cancel);
		});

		return Boolean.TRUE;
    }

	@Override
	@Transactional
	public OutboundVO saveFromOms(AjaxRequest<OutboundVO> request) throws BusinessServiceException {
		OutboundVO outboundVO = request.getData();
		outboundVO.setCompanyId(request.getCompanyId());
		outboundVO.setUpdateBy(request.getUserName());
		outboundVO.setUpdateTime(new Date());

		StatusHistoryTEntity statusHistory = StatusHistoryTEntity.builder()
				.companyId(request.getCompanyId())
				.createBy(request.getUserName())
				.updateBy(request.getUserName())
				.createTime(new Date())
				.updateTime(new Date())
				.operTime(new Date())
				.build();
		List<OutboundVO> addList = Lists.newArrayList();
		switch (outboundVO.getOperatorType()) {
			case Add:
				addList = deal(outboundVO);
				addList.forEach(d ->{
					d.setStatus(OutboundStatusEnum.Draft.getCode());
					validate(d);
					add(d);
				});
				break;
			case Modify:
				modify(outboundVO);
				addList.add(outboundVO);
				break;
			case Reject:
				statusHistory.setOldStatus(outboundVO.getStatus());
				statusHistory.setNewStatus(OutboundStatusEnum.Draft.getCode());
				statusHistory.setRemark(outboundVO.getDescription());
				outboundVO.setStatus(InboundStatusEnum.Draft.getCode());
				modify(outboundVO);
				addList.add(outboundVO);
				break;
			case Submit:
				if(null == outboundVO.getOutboundHeaderId()){
					addList = deal(outboundVO);
					addList.forEach(d ->{
						d.setStatus(OutboundStatusEnum.WaitingReview.getCode());
						validate(d);
						add(d);
					});
				}else {
					outboundVO.setStatus(OutboundStatusEnum.WaitingReview.getCode());
					modify(outboundVO);
					addList.add(outboundVO);
				}
				statusHistory.setOldStatus(OutboundStatusEnum.Draft.getCode());
				statusHistory.setNewStatus(OutboundStatusEnum.WaitingReview.getCode());
				break;
			case Review:
				outboundVO.setStatus(OutboundStatusEnum.New.getCode());
				modify(outboundVO);
				addList.add(outboundVO);
				statusHistory.setOldStatus(OutboundStatusEnum.WaitingReview.getCode());
				statusHistory.setNewStatus(OutboundStatusEnum.New.getCode());
				//产生装车任务
				loadTask(outboundVO, OperatorTypeEnum.Add);
				break;
			default:
				throw new BusinessServiceException("OutboundHeaderServiceImpl", "opertiontype.not.exists" , null );
		}

		if(null != outboundVO.getOutboundHeaderId()){
			statusHistory.setSourceNumber(outboundVO.getOutboundHeaderId());
		}
		if(null != outboundVO.getOutboundNumber()){
			statusHistory.setSourceBillNumber(outboundVO.getOutboundNumber());
		}
		if(null != statusHistory.getNewStatus()){
			//有更新状态就插入记录
			statusHistoryService.add(statusHistory);
		}

		//process detail
		if (CollectionUtils.isNotEmpty(addList)){
			addList.forEach(d ->{
				if(CollectionUtils.isNotEmpty(d.getDetail())){
					outboundDetailService.saveFromOms(d);
				}
			});
		}

		return outboundVO;
	}

	/** 
	* @Description: 对从OMS进来新增的单进行拆分处理
	* @Param: [outbound] 
	* @return: java.util.List<com.wms.vo.outbound.OutboundVO> 
	* @Author: pengzhen@cmhit.com 
	* @Date: 2019/8/29 
	*/ 
	private List<OutboundVO> deal(OutboundVO outbound){

		List<OutboundVO> returnList = Lists.newArrayList();
		List<EntInventoryOnhandVO> inventoryOnhandList = outbound.getEntInventoryOnhandList();
		//根据仓库Id和货主分组
		Map<Long, Map<String,List<EntInventoryOnhandVO>>> headerMap = inventoryOnhandList.stream().filter(v -> null != v && null != v.getWarehouseId()).collect(Collectors.groupingBy(EntInventoryOnhandTEntity::getWarehouseId,Collectors.groupingBy(EntInventoryOnhandTEntity::getOwnerCode)));
		headerMap.forEach((k, v) -> {
			List<OutboundDetailVO> detailList = Lists.newArrayList();
			v.forEach((ownerCode, d) ->{
				OutboundVO outboundVO = new OutboundVO(outbound);
				outboundVO.setWarehouseId(k);
				outboundVO.setCreateBy(outbound.getUpdateBy());
				outboundVO.setStatus(OutboundStatusEnum.Draft.getCode());
				outboundVO.setOwnerCode(ownerCode);
				for (int i = 0; i < d.size(); i++){
					//生成明细
					EntInventoryOnhandVO inventoryOnhand =d.get(i);

					OutboundDetailVO outboundDetailVO = new OutboundDetailVO();
					BeanUtils.copyBeanProp(outboundDetailVO,inventoryOnhand,Boolean.FALSE);
					//把不需要的属性设为空
					outboundDetailVO.setSkuId(null);
					outboundDetailVO.setOwnerId(null);

					outboundDetailVO.setWarehouseId(k);
					outboundDetailVO.setCompanyId(outbound.getCompanyId());
					outboundDetailVO.setLineNumber((i+1)*10L);
					outboundDetailVO.setUpdateBy(outbound.getUpdateBy());
					PackTEntity pack = packService.find(PackTEntity.builder()
							.warehouseId(k)
							.companyId(outbound.getCompanyId())
							.packCode(inventoryOnhand.getPackCode())
							.build());
					outboundDetailVO.setPackCode(pack.getPackCode());
					outboundDetailVO.setPackId(pack.getPackId());
					outboundDetailVO.setUom(pack.getUomCase());
					outboundDetailVO.setUomQuantityExpected(inventoryOnhand.getQuantityExpected());
					outboundDetailVO.setUomQuantityOrder(inventoryOnhand.getQuantityExpected());

					detailList.add(outboundDetailVO);
				}
				outboundVO.setDetail(detailList);

				if(StringUtils.isNotBlank(outboundVO.getCarrierCode())){
					//承运人新增判断
					CarrierTEntity carrier = carrierService.findByCode(CarrierTEntity.builder()
							.warehouseId(outboundVO.getWarehouseId())
							.companyId(outboundVO.getCompanyId())
							.carrierCode(outboundVO.getCarrierCode())
							.build());
					if(null == carrier){
						EntCarrierTEntity enCarrier = EntCarrierTEntity.builder()
								.companyId(outboundVO.getCompanyId())
								.carrierCode(outboundVO.getCarrierCode())
								.build();
						EntCarrierTEntity select = enterpriseService.findCarrier(enCarrier);
						List<CarrierTEntity> carriers = Lists.newArrayList();
						CarrierTEntity insert = new CarrierTEntity();
						if(null != select){
							BeanUtils.copyBeanProp(insert, select, Boolean.FALSE);
							insert.setWarehouseId(outboundVO.getWarehouseId());
							insert.setCompanyId(outboundVO.getCompanyId());
							insert.setUpdateBy(outboundVO.getUpdateBy());
						}else {
							insert.setActive(YesNoEnum.Yes.getCode());
							insert.setCompanyId(outboundVO.getCompanyId());
							insert.setWarehouseId(outboundVO.getWarehouseId());
							insert.setCarrierCode(outboundVO.getCarrierCode());
							insert.setCarrierDescr(outboundVO.getCarrierCode());
						}
						carriers.add(insert);
						carrierService.add(carriers);
					}
				}
				if(StringUtils.isNotBlank(outboundVO.getCustomerCode())){
					//客户新增判断
					CustomerTEntity customer =customerService.findByCode(CustomerTEntity.builder()
							.warehouseId(outboundVO.getWarehouseId())
							.companyId(outboundVO.getCompanyId())
							.customerCode(outboundVO.getCustomerCode())
							.build());
					if(null == customer){
						EntCustomerTEntity enCustomer = EntCustomerTEntity.builder()
								.companyId(outboundVO.getCompanyId())
								.customerCode(outboundVO.getCustomerCode())
								.build();
						EntCustomerTEntity select = enterpriseService.findCustomer(enCustomer);
						List<CustomerTEntity> customers = Lists.newArrayList();
						CustomerTEntity insert = new CustomerTEntity();
						if(null != select){
							BeanUtils.copyBeanProp(insert, select, Boolean.FALSE);
							insert.setWarehouseId(outboundVO.getWarehouseId());
							insert.setCompanyId(outboundVO.getCompanyId());
							insert.setUpdateBy(outboundVO.getUpdateBy());
						}else {
							insert.setActive(YesNoEnum.Yes.getCode());
							insert.setCompanyId(outboundVO.getCompanyId());
							insert.setWarehouseId(outboundVO.getWarehouseId());
							insert.setCustomerCode(outboundVO.getCustomerCode());
							insert.setCustomerDescr(outboundVO.getCustomerCode());
							insert.setContact1(outboundVO.getContact1());
							insert.setContact2(outboundVO.getContact2());
							insert.setShipLabel(outboundVO.getShipLabel());
							insert.setAddress1(outboundVO.getAddress1());
							insert.setAddress2(outboundVO.getAddress2());
							insert.setPhone1(outboundVO.getPhone1());
							insert.setPhone2(outboundVO.getPhone2());
							insert.setFax(outboundVO.getFax());
							insert.setEmail1(outboundVO.getEmail1());
							insert.setEmail2(outboundVO.getEmail2());
						}
						customers.add(insert);
						customerService.add(customers);
					}
				}
				returnList.add(outboundVO);
			});
		});

		return returnList;
	}

	private Boolean validate(OutboundVO outboundVO) {
		if (outboundVO.getOwnerId() == null  && StringUtils.isEmpty(outboundVO.getOwnerCode())){
			throw new BusinessServiceException("OutboundHeaderServiceImpl", "outbound.owner.isnull" , new Object[] {outboundVO.getOutboundNumber()});
		}
		if (StringUtils.isEmpty(outboundVO.getType())) {
			throw new BusinessServiceException("OutboundHeaderServiceImpl", "outbound.type.isnull", new Object[]{outboundVO.getOutboundNumber()});
		}
		if (StringUtils.isEmpty(outboundVO.getStatus())) {
			throw new BusinessServiceException("OutboundHeaderServiceImpl", "outbound.status.isnull", new Object[]{outboundVO.getOutboundNumber()});
		}
		
		if (outboundVO.getExpectedOutboundDate() != null
				&& outboundVO.getExpectedOutboundDate().compareTo(DateUtils.parseDate(DateUtils.getDate())) < 0)
			throw new BusinessServiceException("InboundHeaderServiceImpl", "outbound.expected.date.less.now" , new Object[] {outboundVO.getOutboundDate()}); 
		
		if (outboundVO.getOperatorType() == OperatorTypeEnum.Modify) {
			return Boolean.TRUE;
		}
		OwnerTEntity owner = ownerService.find(OwnerTEntity.builder()
				.warehouseId(outboundVO.getWarehouseId())
				.companyId(outboundVO.getCompanyId())
				.ownerId(outboundVO.getOwnerId())
				.ownerCode(outboundVO.getOwnerCode())
				.build());
		outboundVO.setOwnerCode(owner.getOwnerCode());
		outboundVO.setOwnerId(owner.getOwnerId());

		if(null != outboundVO.getCustomerId() || StringUtils.isNotEmpty(outboundVO.getCustomerCode())){
			CustomerTEntity customer =customerService.find(CustomerTEntity.builder()
					.warehouseId(outboundVO.getWarehouseId())
					.companyId(outboundVO.getCompanyId())
					.customerId(outboundVO.getCustomerId())
					.customerCode(outboundVO.getCustomerCode())
					.build());
			outboundVO.setCustomerId(customer.getCustomerId());
			outboundVO.setCustomerCode(customer.getCustomerCode());
			outboundVO.setCustomerCode(customer.getCustomerDescr());
		}

		if (StringUtils.isEmpty(outboundVO.getOutboundNumber())) {
			outboundVO.setOutboundNumber(KeyUtils.getOrderNumber(outboundVO.getCompanyId(), outboundVO.getWarehouseId(), OrderNumberTypeEnum.Outbound));
		} 
		
		try {
			//新增判断重复
			OutboundHeaderTEntity header = find(OutboundHeaderTEntity.builder()
					.warehouseId(outboundVO.getWarehouseId())
					.companyId(outboundVO.getCompanyId())
					.outboundNumber(outboundVO.getOutboundNumber())
					.build());
			if (header != null) {
				throw new BusinessServiceException("OutboundHeaderServiceImpl", "outbound.number.exists", new Object[]{outboundVO.getOutboundNumber()});
			}
		} catch (BusinessServiceException e) {}
		
		return Boolean.TRUE;
	}

	@Override
	public List<OutboundHeaderTEntity> findByWaveTemplate(AjaxRequest<WaveBuildVO> request) {
		WaveBuildVO vo = request.getData();
		List<WaveBuildDetailTEntity> list = vo.getDetail();
		OutboundHeaderTExample headerExample = new OutboundHeaderTExample();
		OutboundHeaderTExample.Criteria headerCriteria = headerExample.createCriteria();
		headerCriteria.andCompanyIdEqualTo(request.getCompanyId())
				.andWarehouseIdEqualTo(request.getWarehouseId())
				.andDelFlagEqualTo(YesNoEnum.No.getCode())
				.andSourceWaveNumberIsNull()
				.andStatusEqualTo(OutboundStatusEnum.New.getCode());
		WaveBuildUtils.addTableCondition(WaveBuildUtils.ConditionType.Header, headerCriteria, list);
		List<OutboundHeaderTEntity> outbounds = outboundHeaderDao.selectByExample(headerExample);
		
		//无记录直接退出
		if (CollectionUtils.isEmpty(outbounds))
			return Lists.newArrayList();
		
		/*
		//判断是否包含明细条件 / 暂无需求不实现
		boolean filterDetaillFlag = false;
		for (WaveBuildDetailTEntity d : list) {
			if (!WaveBuildUtils.ConditionType.Header.getCode().equals(d.getTableName())) {
				filterDetaillFlag = true;
				break;
			}
		}
		*/
		
		//判断是否包含聚合条件
		List<WaveBuildDetailTEntity> otherFiler = Lists.newArrayList();
		for (WaveBuildDetailTEntity d : list) {
			if (WaveBuildUtils.ConditionType.Aggregate.getCode().equals(d.getTableName())) {
				otherFiler.add(d);
			}
		}
		
		if (CollectionUtils.isEmpty(otherFiler)){
			return outbounds;
		}
		
		//按有效的出库订单查询所有明细
		Set<Long> headerIds = outbounds.stream().map(OutboundHeaderTEntity::getOutboundHeaderId).collect(Collectors.toSet());
		List<OutboundDetailTEntity> detail = outboundDetailService.findByHeaderIds(OutboundDetailTEntity.builder()
																					.warehouseId(request.getWarehouseId())
																					.companyId(request.getCompanyId())
																					.build(), headerIds);
		if (CollectionUtils.isEmpty(detail))
			return Lists.newArrayList();
		
		
		Set<Long> availableHeaderId = Sets.newHashSet();
		//分组转换map
		Map<Long, List<OutboundDetailTEntity>> detailMap = detail.stream().collect(Collectors.groupingBy(OutboundDetailTEntity::getOutboundHeaderId));
		
		//循环验证
		detailMap.forEach((k, v) -> {
			if (CollectionUtils.isEmpty(v))
				return;
			
			boolean isOK = true;
			for (WaveBuildDetailTEntity d : otherFiler) {
				//货品包含，只有有一个货品不在限制范围，则不满足条件
				if (WaveBuildUtils.Aggregate.SkuCodeIn.getCode().equals(d.getPropertyCode() + d.getPropertyExp())) {
					String [] filterSkus = d.getPropertyValue1().split(WaveBuildUtils.SEPERATOR);
					Set<String> skus = v.stream().map(OutboundDetailTEntity::getSkuCode).collect(Collectors.toSet());
					if (skus.size() > filterSkus.length) {  //出库单货品比限制的货品多，则直接失败
						//存在一个条件不满足则退出循环
						isOK = false;
						break;
					}
					Set<String> filterSkuSet = Sets.newHashSet(filterSkus);
					for (String sku : skus) {
						if (!filterSkuSet.contains(sku)) {//出库单中其中一个货品不满足限制条件，则直接失败
							//存在一个条件不满足则退出循环
							isOK = false;
							break;
						}
					}
					//承接上个循环的逻辑，退出大循环
					if (!isOK) {
						break;
					}
				}
				
				//数量介于
				//优先计算订单总数量
				BigDecimal totalQuantity = BigDecimal.ZERO;
				for (OutboundDetailTEntity od : v) {
					totalQuantity = totalQuantity.add(od.getQuantityOrder());
				}
				//订单数量大于等于（最小数量）
				if (WaveBuildUtils.Aggregate.QuantityOrderGreaterThanOrEqualTo.getCode().equals(d.getPropertyCode() + d.getPropertyExp())) {
					//限制最小数量大于订单数量，匹配失败
					if (new BigDecimal(d.getPropertyValue1()).compareTo(totalQuantity) > 0) {
						isOK = false;
						break;
					}
				}
				//订单数量小于等于（最大数量）
				if (WaveBuildUtils.Aggregate.QuantityOrderLessThanOrEqualTo.getCode().equals(d.getPropertyCode() + d.getPropertyExp())) {
					//限制最大数量小于订单数量，匹配失败
					if (new BigDecimal(d.getPropertyValue1()).compareTo(totalQuantity) < 0) {
						isOK = false;
						break;
					}
				}
				
			}
			
			//满足条件
			if (isOK) {
				availableHeaderId.add(k);
			}
			
		});
		
		//与初始查询结果一致，无需再次查询
		if (availableHeaderId.size() == outbounds.size())
			return outbounds;
		
		List<OutboundHeaderTEntity> filter = outbounds.stream().filter(v -> availableHeaderId.contains(v.getOutboundHeaderId())).collect(Collectors.toList());
		return filter;
	}
	
	
	/**
	 * 装车任务
	 * @param header
	 * @param operator
	 * @return
	 */
	private Boolean loadTask(OutboundHeaderTEntity header, OperatorTypeEnum operator) {
		switch (operator) {
		//单据创建时增加任务
		case Add:
			TaskDetailTEntity task = TaskDetailTEntity.builder()
			.warehouseId(header.getWarehouseId())
			.companyId(header.getCompanyId())
			.createBy(header.getUpdateBy())
			.createTime(new Date())
			.updateBy(header.getUpdateBy())
			.updateTime(new Date())
			.taskType(TaskTypeEnum.Load.getCode())
			.sourceBillNumber(header.getOutboundNumber())
			.ownerCode(header.getOwnerCode())
			.releaseTime(new Date())
			.build();
			taskService.add(task);
			break;
		//单据取消/删除时取消任务
		case Cancel:
			List<TaskDetailTEntity> cancelTasks = taskService.findBySourceBillNumber(TaskDetailTEntity.builder()
													.warehouseId(header.getWarehouseId())
													.companyId(header.getCompanyId())
													.sourceBillNumber(header.getOutboundNumber())
													.build());
			cancelTasks = cancelTasks.stream().filter(v->TaskStatusEnum.New.getCode().equals(v.getStatus())).collect(Collectors.toList());
			AjaxRequest<List<TaskDetailTEntity>> cancelRequest = new AjaxRequest<List<TaskDetailTEntity>>(cancelTasks);
			cancelRequest.setWarehouseId(header.getWarehouseId());
			cancelRequest.setCompanyId(header.getCompanyId());
			cancelRequest.setUserName(header.getUpdateBy());
			taskService.cancel(cancelRequest);
			break;
		//单据关闭时完成任务
		case Complate: 
			List<TaskDetailTEntity> tasks = taskService.findBySourceBillNumber(TaskDetailTEntity.builder()
					.warehouseId(header.getWarehouseId())
					.companyId(header.getCompanyId())
					.sourceBillNumber(header.getOutboundNumber())
					.build());
			tasks = tasks.stream().filter(v->TaskStatusEnum.New.getCode().equals(v.getStatus())).collect(Collectors.toList());
			tasks.forEach(t -> {
				t.setStatus(TaskStatusEnum.Completed.getCode());
			});
			AjaxRequest<List<TaskDetailTEntity>> request = new AjaxRequest<List<TaskDetailTEntity>>(tasks);
			request.setWarehouseId(header.getWarehouseId());
			request.setCompanyId(header.getCompanyId());
			request.setUserName(header.getUpdateBy());
			taskService.modify(request);
			break;
		default:
			break;
		}
		
		return Boolean.TRUE;
	}

}
