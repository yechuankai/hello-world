package com.wms.file.services.impl;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wms.async.manager.AsyncManager;
import com.wms.async.manager.factory.AsyncFactory;
import com.wms.common.core.domain.ExcelTemplate;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.services.IExcelService;
import com.wms.common.enums.ExcelTypeEnum;
import com.wms.common.enums.FileStatusEnum;
import com.wms.common.enums.FileTypeEnum;
import com.wms.common.enums.LogBusinessTypeEnum;
import com.wms.common.enums.OperatorTypeEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.exception.file.ExcelException;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.mongodb.MongoUtils;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.entity.auto.SysFileTEntity;
import com.wms.file.async.ExcelAsync;
import com.wms.file.services.IFileService;
import com.wms.file.vo.FileVO;
import com.wms.services.sys.ISysFileService;
import com.wms.vo.OperLogVO;

@Service
public class FileServiceImpl implements IFileService, InitializingBean {

	private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
	private static Map<String, ExcelTemplate> importService = Maps.newHashMap();
	
	@Autowired
	private ISysFileService fileService;
	
	@Override
	public List<FileVO> upload(AjaxRequest<List<FileVO>> request) throws BusinessServiceException{
		List<FileVO> files = request.getData();
		if (log.isDebugEnabled())
			log.debug("upload files: " + files.size());
		
		StringBuilder errmsg = new StringBuilder();
		List<FileVO> success = Lists.newArrayList(); 
		files.forEach(file -> {
			if (log.isDebugEnabled())
				log.debug("start upload file: " + file.getFileName());
			
			try {
				file.setCreateBy(request.getUserName());
				file.setUpdateBy(request.getUserName());
				file.setCreateTime(new Date());
				file.setUpdateTime(new Date());
				file.setUserId(request.getUserId());
				file.setWarehouseId(request.getWarehouseId());
				file.setCompanyId(request.getCompanyId());
				file.setContentType(file.getFile().getContentType());
				file.setFileName(file.getFile().getOriginalFilename());
				file.setFileSize(file.getFile().getSize());
				
				ObjectId id = MongoUtils.store(file.getFile().getInputStream(), file.getFileName(), file.getContentType());
				file.setObjectId(id.toHexString());
				
				//异步记录日志
				OperLogVO oper = new OperLogVO(file.getFileName(), LogBusinessTypeEnum.File.getCode(), OperatorTypeEnum.Other.getCode(), "upload", request.getUserName());
				AsyncManager.me().execute(AsyncFactory.recordOper(oper));
				
			} catch (Exception e) {
				errmsg.append(StringUtils.join(file.getFileName() , " : " , e.getMessage()));
			} finally {
				try {
					if (file.getFile() != null) {
						file.getFile().getInputStream().close();
					}
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			}
			if (StringUtils.isNotEmpty(errmsg))
				file.setStatus(FileStatusEnum.Fail.getCode());
			else
				file.setStatus(FileStatusEnum.Upload.getCode());
			
			fileService.add(file);
			success.add(file);
			
			
		});
		
		//rollback
		if (StringUtils.isNotEmpty(errmsg)) {
			success.forEach(file -> {
				MongoUtils.delete(new ObjectId(file.getObjectId()));
				file.setStatus(FileStatusEnum.Fail.getCode());
				file.setRemark(errmsg.toString());
				fileService.modify(file);
			});
			throw new BusinessServiceException("FileServiceImpl", errmsg.toString(), new Object[] {});
		}
		return files;
	}
	
	public FileVO download(AjaxRequest<FileVO> request, OutputStream os) throws BusinessServiceException {
		FileVO file = request.getData();
		MongoUtils.getResource(new ObjectId(file.getObjectId()), os);
		//异步记录日志
		OperLogVO oper = new OperLogVO(file.getFileName(), LogBusinessTypeEnum.File.getCode(), OperatorTypeEnum.Other.getCode(), "download", request.getUserName());
		AsyncManager.me().execute(AsyncFactory.recordOper(oper));
		return file;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, IExcelService> map = SpringUtils.getBeansOfType(IExcelService.class);
		map.forEach((k, v) -> {
			ExcelTemplate template = v.getExcelTemplate();
			template.setExcelService(v);
			if (StringUtils.isNotEmpty(template.getTemplate())) 
				importService.put(template.getTemplate(), template);
		});
	}

	@Override
	public void importData(FileVO file) throws ExcelException {
		if(log.isDebugEnabled())
			log.debug("import file:" + file);
		
		if (!FileTypeEnum.Import.getCode().equals(file.getFileType()))
			throw new ExcelException("not.import.file", new Object[] { file.getFileName() });
		
		String fileName = file.getFileName().toLowerCase();
		if (!(fileName.endsWith(ExcelTypeEnum.XLS.getCode()) || 
				fileName.endsWith(ExcelTypeEnum.XLSX.getCode())))
			throw new ExcelException("import.file.not.excel", new Object[] { file.getFileName() });
		
		ExcelTemplate template = importService.get(file.getTemplate());
		if (template == null)
			throw new ExcelException("import.file.not.templete", new Object[] { file.getFileName() });
		
		//异步执行导入
		AsyncManager.me().execute(ExcelAsync.importData(file, template));
		
	}
	
	
	@Override
	public void exportData(PageRequest request, String exportTemplate) throws ExcelException {
		
		ExcelTemplate template = importService.get(exportTemplate);
		if (template == null)
			throw new ExcelException("export.file.not.templete", new Object[] { exportTemplate });
		
		FileVO fileVo = new FileVO();
		fileVo.setUserId(request.getLong(SysFileTEntity.Column.userId.getJavaProperty()));
		fileVo.setCreateBy(request.getUserName());
		fileVo.setUpdateBy(request.getUserName());
		fileVo.setUserId(request.getUserId());
		fileVo.setWarehouseId(request.getWarehouseId());
		fileVo.setCompanyId(request.getCompanyId());
		fileVo.setTemplate(exportTemplate);
		fileVo.setFileType(FileTypeEnum.Export.getCode());
		fileVo.setContentType(ExcelTypeEnum.XLSX.getCode());
		fileVo.setFileName(StringUtils.join(exportTemplate, "_", new Date().getTime(), ExcelTypeEnum.XLSX.getCode()));
		fileVo.setStatus(FileStatusEnum.Init.getCode());
		fileVo.setCreateTime(new Date());
		fileVo.setUpdateTime(new Date());
		
		fileService.add(fileVo);
		
		fileVo.setData(request);
		
		//异步执行导入
		AsyncManager.me().execute(ExcelAsync.exportData(fileVo, template));
		
	}

	
}
