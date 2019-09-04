package com.wms.services.sys.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.enums.FileTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.ExampleUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.key.KeyUtils;
import com.wms.dao.auto.ISysFileTDao;
import com.wms.dao.example.SysFileTExample;
import com.wms.entity.auto.SysFileTEntity;
import com.wms.services.sys.ISysFileService;

@Service
public class SysFileServiceImpl implements ISysFileService {

	private static final Logger log = LoggerFactory.getLogger(SysFileServiceImpl.class);
	
	@Autowired
	ISysFileTDao fileDao ;
	
	@Override
	public List<SysFileTEntity> findAll() throws BusinessServiceException {
		SysFileTExample TExample = new SysFileTExample();
		TExample.createCriteria()
		.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		List<SysFileTEntity> configs = fileDao.selectByExample(TExample);
		
		return configs;
	}

	@Override
	public List<SysFileTEntity> find(PageRequest request) throws BusinessServiceException {
		SysFileTExample TExample = new SysFileTExample();
		SysFileTExample.Criteria TExampleCriteria = TExample.createCriteria();
		
		//转换查询方法
		ExampleUtils.create(SysFileTEntity.Column.class, SysFileTExample.Criterion.class)
				.criteria(TExampleCriteria)
				.data(request)
				.build(request)
				.orderby(TExample);
		
		TExampleCriteria.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		return fileDao.selectByExample(TExample);
	}


	@Override
	@Transactional
	public Boolean add(SysFileTEntity file) throws BusinessServiceException {
		file.setFileId(KeyUtils.getUID());
		int row = fileDao.insertSelective(file);
		if (row == 0) {
			throw new BusinessServiceException("record update error.");
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean delete(SysFileTEntity file) throws BusinessServiceException {
		
		SysFileTExample example = new SysFileTExample();
		example.createCriteria()
		.andWarehouseIdEqualTo(file.getWarehouseId())
		.andCompanyIdEqualTo(file.getCompanyId())
		.andFileIdEqualTo(file.getFileId());
		
		SysFileTEntity selectFile = fileDao.selectOneByExample(example);
		if (selectFile == null) 
			throw new BusinessServiceException("SysFileServiceImpl", "file.not.exists", new Object[] { file.getFileName() });
		
		
		SysFileTEntity update = SysFileTEntity.builder()
		.updateBy(file.getUpdateBy())
		.updateTime(new Date())
		.delFlag(YesNoEnum.Yes.getCode())
		.build();
			
		example.clear();
		example.createCriteria()
		.andWarehouseIdEqualTo(file.getWarehouseId())
		.andCompanyIdEqualTo(file.getCompanyId())
		.andFileIdEqualTo(file.getFileId());
			
		int row = fileDao.updateWithVersionByExampleSelective(selectFile.getUpdateVersion(), update, example);
		if (row == 0) {
			throw new BusinessServiceException("delete update error.");
		}
		return Boolean.TRUE;
	}

	@Override
	public Boolean modify(SysFileTEntity file) throws BusinessServiceException {
		
		SysFileTEntity selectFile = find(file);
		
		SysFileTExample example = new SysFileTExample();
		example.createCriteria()
		.andWarehouseIdEqualTo(file.getWarehouseId())
		.andCompanyIdEqualTo(file.getCompanyId())
		.andFileIdEqualTo(file.getFileId());
			
		int row = fileDao.updateWithVersionByExampleSelective(selectFile.getUpdateVersion(), file, example);
		if (row == 0) {
			throw new BusinessServiceException("update update error.");
		}
		return Boolean.TRUE;
		
	}

	@Override
	public SysFileTEntity find(SysFileTEntity file) throws BusinessServiceException {
		SysFileTExample example = new SysFileTExample();
		example.createCriteria()
		.andWarehouseIdEqualTo(file.getWarehouseId())
		.andCompanyIdEqualTo(file.getCompanyId())
		.andFileIdEqualTo(file.getFileId());
		
		SysFileTEntity selectFile = fileDao.selectOneByExample(example);
		if (selectFile == null) 
			throw new BusinessServiceException("SysFileServiceImpl", "file.not.exists", new Object[] { file.getFileId() });
		
		return selectFile;
	}

	@Override
	public SysFileTEntity findMobileApp() {
		SysFileTExample example = new SysFileTExample();
		example.createCriteria()
		.andFileTypeEqualTo(FileTypeEnum.Mobile.getCode())
		.andDelFlagEqualTo(YesNoEnum.No.getCode());
		example.orderBy(SysFileTEntity.Column.template.getValue());
		
		List<SysFileTEntity> list = fileDao.selectByExample(example);
		SysFileTEntity entity = null;
		if (list.size() > 0) {
			entity = list.get(list.size() - 1);
		}
		return entity;
	}

	@Override
	public boolean delete(AjaxRequest<List<SysFileTEntity>> request) throws BusinessServiceException {
		if (CollectionUtils.isEmpty(request.getData())) {
			throw new BusinessServiceException("no record delete.");
		}
		List<SysFileTEntity> list = request.getData();
		
		list.forEach(h -> {
			SysFileTEntity update = SysFileTEntity.builder()
						.updateBy(request.getUserName())
						.updateTime(new Date())
						.delFlag(YesNoEnum.Yes.getCode())
						.build();
	
			SysFileTExample  example= new SysFileTExample();
			example.createCriteria().andFileIdEqualTo(h.getFileId());

			int rowcount = fileDao.updateWithVersionByExampleSelective(h.getUpdateVersion(), update, example);
			if (rowcount == 0) {
				throw new BusinessServiceException("record delete error.");
			}
		});
		
		return Boolean.TRUE;
	}
	
	@Override
	public List<SysFileTEntity> find(FileTypeEnum type, String template) throws BusinessServiceException {
		SysFileTExample example = new SysFileTExample();
		SysFileTExample.Criteria criteria = example.createCriteria()
		.andFileTypeEqualTo(type.getCode())
		.andDelFlagEqualTo(YesNoEnum.No.getCode());
		
		if (StringUtils.isNotEmpty(template))
			criteria.andTemplateEqualTo(template);
		
		List<SysFileTEntity> list = fileDao.selectByExample(example);
		return list;
	}

}
