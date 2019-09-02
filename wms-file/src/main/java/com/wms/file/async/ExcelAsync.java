package com.wms.file.async;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.apache.poi.ss.usermodel.Workbook;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.wms.async.manager.AsyncManager;
import com.wms.async.manager.factory.AsyncFactory;
import com.wms.common.core.domain.ExcelTemplate;
import com.wms.common.core.domain.IExcelModel;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.enums.ExcelTypeEnum;
import com.wms.common.enums.FileStatusEnum;
import com.wms.common.enums.LogBusinessTypeEnum;
import com.wms.common.enums.OperatorTypeEnum;
import com.wms.common.enums.YesNoEnum;
import com.wms.common.exception.file.ExcelException;
import com.wms.common.utils.MessageUtils;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.common.utils.mongodb.MongoUtils;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.entity.auto.SysFileTEntity;
import com.wms.file.vo.FileVO;
import com.wms.file.vo.ImportMessageVO;
import com.wms.services.sys.ISysFileService;
import com.wms.vo.OperLogVO;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;

/**
 * 异步工厂（产生任务用）
 *
 */
public class ExcelAsync {
	
	private static final Logger log = LoggerFactory.getLogger(ExcelAsync.class);

	public static TimerTask importData(final FileVO file, final ExcelTemplate template ) {
		return new TimerTask() {
			@Override
			public void run() {
				
				ISysFileService fileService = SpringUtils.getBean(ISysFileService.class);
				SysFileTEntity sysFile = fileService.find(file);
				if (!FileStatusEnum.Upload.getCode().equals(sysFile.getStatus())) 
					throw new ExcelException("import.file.status.error", new Object[] { file.getFileName() });
				
				SysFileTEntity updateFile = SysFileTEntity.builder()
												.warehouseId(sysFile.getWarehouseId())
												.companyId(sysFile.getCompanyId())
												.fileId(sysFile.getFileId())
												.status(FileStatusEnum.Process.getCode())
												.startTime(new Date())
												.updateBy("ImportAsync")
												.build();
				//更新处理中状态
				fileService.modify(updateFile);
				StringBuilder errorMsg = new StringBuilder();
				
				ImportMessageVO imp = new ImportMessageVO();
				imp.setFileId(sysFile.getFileId());
				imp.setFileName(sysFile.getFileName());
				imp.setTemplate(template.getTemplate());
				
				int successCount = 0;
				int errorCount = 0;
				
				try(InputStream is = MongoUtils.getResource(new ObjectId(sysFile.getObjectId()));) {
					
					ImportParams params = new ImportParams();
					params.setTitleRows(1);
					params.setHeadRows(1);
					List list = ExcelImportUtil.importExcel(is, template.getClz(), params);

					AjaxRequest request = new AjaxRequest(list, BeanUtils.bean2map(file));
					template.getExcelService().importData(request);
					String jsonResult = JSON.toJSONString(list);
					
					imp.setMessage(jsonResult);
					
					for (Object o : list) {
						IExcelModel r = (IExcelModel) o;
						if (YesNoEnum.No.getCode().equals(r.success()))
							errorCount ++;
					}
					String resultMssage = null;
					if (errorCount == list.size()) {
						resultMssage = MessageUtils.message("fail", null);
					}else if(errorCount > 0 && errorCount < list.size())
						resultMssage = MessageUtils.message("part.success", null);
					
					if (StringUtils.isNotEmpty(resultMssage))
						errorMsg.append(resultMssage);
					
					successCount = list.size() - errorCount;
					
				} catch (Exception e ) {
					log.error(e.getMessage(), e);
					errorMsg.append(e.getMessage());
					imp.setMessage(e.getMessage());
				} 
				
				MongoUtils.insert(imp);
				
				OperLogVO oper = null;
				
				String resultMessage = MessageUtils.message("success.import", successCount, errorCount);
				
				updateFile.setEndTime(new Date());
				if (StringUtils.isNotEmpty(errorMsg)) {
					errorMsg.append(", ").append(resultMessage);
					updateFile.setStatus(FileStatusEnum.ProcessFail.getCode());
					updateFile.setRemark(errorMsg.toString());
					oper = new OperLogVO(file.getFileName(), LogBusinessTypeEnum.File.getCode(), OperatorTypeEnum.Other.getCode(), "importData", file.getUpdateBy(), errorMsg.toString());
				}else {
					updateFile.setStatus(FileStatusEnum.ProcessSuccess.getCode());
					updateFile.setRemark(resultMessage);
					oper = new OperLogVO(file.getFileName(), LogBusinessTypeEnum.File.getCode(), OperatorTypeEnum.Other.getCode(), "importData", file.getUpdateBy());
				}
				//更新状态
				fileService.modify(updateFile);
				
				//异步记录日志
				AsyncManager.me().execute(AsyncFactory.recordOper(oper));
			}
		};
	}
	
	
	public static TimerTask exportData(final FileVO file, final ExcelTemplate template) {
		return new TimerTask() {
			@Override
			public void run() {
				ISysFileService fileService = SpringUtils.getBean(ISysFileService.class);
				SysFileTEntity sysFile = fileService.find(file);
				if (!FileStatusEnum.Init.getCode().equals(sysFile.getStatus())) 
					throw new ExcelException("export.file.status.error", new Object[] { file.getFileName() });
				
				SysFileTEntity updateFile = SysFileTEntity.builder()
												.warehouseId(sysFile.getWarehouseId())
												.companyId(sysFile.getCompanyId())
												.fileId(sysFile.getFileId())
												.status(FileStatusEnum.Process.getCode())
												.startTime(new Date())
												.updateBy("ExportAsync")
												.build();
				//更新处理中状态
				fileService.modify(updateFile);
				StringBuilder errorMsg = new StringBuilder();
				
				ByteArrayOutputStream os = null;
				InputStream is = null;
				int rowcount = 0;
				int size = 0;
				try{
					os = new ByteArrayOutputStream();
					List list = template.getExcelService().exportData(file.getData());
					rowcount = list.size();
					Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(file.getTemplate(), file.getTemplate(), ExcelType.XSSF),
							template.getClz(), list);
					workbook.write(os);
					is = new ByteArrayInputStream(os.toByteArray());
					ObjectId objectId = MongoUtils.store(is, file.getFileName(), file.getContentType());
					updateFile.setObjectId(objectId.toHexString());
					size = os.size();
					
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					errorMsg.append(e.getMessage());
				}finally {
					if (os != null)
						try {
							os.close();
						} catch (IOException e) {}
					if (is != null)
						try {
							is.close();
						} catch (IOException e) {}
				}
				
				OperLogVO oper = null;
				
				String resultMessage = MessageUtils.message("success.export", rowcount);
				
				updateFile.setEndTime(new Date());
				if (StringUtils.isNotEmpty(errorMsg)) {
					updateFile.setStatus(FileStatusEnum.ProcessFail.getCode());
					updateFile.setRemark(errorMsg.toString());
					oper = new OperLogVO(file.getFileName(), LogBusinessTypeEnum.File.getCode(), OperatorTypeEnum.Other.getCode(), "importData", file.getUpdateBy(), errorMsg.toString());
				}else {
					updateFile.setStatus(FileStatusEnum.ProcessSuccess.getCode());
					updateFile.setRemark(resultMessage);
					updateFile.setFileSize(new Long(size));
					oper = new OperLogVO(file.getFileName(), LogBusinessTypeEnum.File.getCode(), OperatorTypeEnum.Other.getCode(), "importData", file.getUpdateBy());
				}
				//更新状态
				fileService.modify(updateFile);
				
				//异步记录日志
				AsyncManager.me().execute(AsyncFactory.recordOper(oper));
			}
		};
	}

}
