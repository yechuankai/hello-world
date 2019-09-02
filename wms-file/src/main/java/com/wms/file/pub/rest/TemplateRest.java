package com.wms.file.pub.rest;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.enums.FileTypeEnum;
import com.wms.common.utils.mongodb.MongoUtils;
import com.wms.entity.auto.SysFileTEntity;
import com.wms.file.services.IFileService;
import com.wms.file.vo.FileVO;
import com.wms.services.sys.ISysFileService;

@RestController
@RequestMapping("/template")
public class TemplateRest extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(TemplateRest.class);

	@RequestMapping("/download/import")
	public void download(@RequestParam Map<String, String> map, HttpServletResponse response){
		try {
			AjaxRequest request = ajaxRequest(map);
			
			String template = request.getString(SysFileTEntity.Column.template.getJavaProperty());
			
			//从环境变量中获取导入模板
			ClassPathResource resource = new ClassPathResource("template/import/" + template + ".xlsx");
			
			// 配置文件下载
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(resource.getFilename(), "UTF-8"));
			
			try (InputStream is = resource.getInputStream();){
				int ch;
		        while ((ch = is.read()) != -1) {
		        	response.getOutputStream().write(ch);
		        }
			}catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	
}
