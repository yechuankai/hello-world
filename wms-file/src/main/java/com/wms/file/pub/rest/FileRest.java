package com.wms.file.pub.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

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
@RequestMapping("/file")
public class FileRest extends BaseController {
	
	private static Logger log = LoggerFactory.getLogger(FileRest.class);
	
	private static final String DEFAULT_APP = "apk/mobile.apk";
	
	@Autowired
	private IFileService fileService;
	
	@Autowired
	private ISysFileService sysFileService;
	
	@RequestMapping("/upload")
	public AjaxResult<List<FileVO>> upload(@RequestParam MultipartFile [] file, HttpServletRequest req){
		try {
			if (file == null || file.length == 0)
				return fail("upload.nofile");
			
			AjaxRequest<String> request = ajaxRequest(req);
			
			List<FileVO> fileVos = doupload(file, request);
			
			return success(fileVos);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	
	@RequestMapping("/download")
	public void download(@RequestParam Map<String, String> map, HttpServletResponse response){
		try {
			AjaxRequest request = ajaxRequest(map);
			SysFileTEntity file = new SysFileTEntity();
			file.setWarehouseId(request.getWarehouseId());
			file.setCompanyId(request.getCompanyId());
			file.setFileId(request.getLong(SysFileTEntity.Column.fileId.getJavaProperty()));
			
			file = sysFileService.find(file);
			
			// 配置文件下载
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getFileName(), "UTF-8"));
			// 下载文件能正常显示中文
			fileService.download(ajaxRequest(new FileVO(file), request), response.getOutputStream());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	
	@RequestMapping("/import")
	public AjaxResult<List<FileVO>> importFile(@RequestParam MultipartFile [] file, HttpServletRequest req){
		try {
			if (file == null || file.length == 0)
				return fail("upload.nofile");
			
			AjaxRequest<String> request = ajaxRequest(req);
			
			List<FileVO> fileVos = Lists.newArrayList();
			for (MultipartFile f : file) {
				FileVO vo = new FileVO();
				vo.setTemplate(request.getString(SysFileTEntity.Column.template.getJavaProperty()));
				vo.setFileType(FileTypeEnum.Import.getCode());
				vo.setFile(f);
				fileVos.add(vo);
			}
			
			fileService.upload(ajaxRequest(fileVos, request));
			
			for (FileVO fileVo : fileVos) {
				//处理导入
				fileService.importData(fileVo);
			}
			return success(fileVos);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	
	@RequestMapping("/export")
	public AjaxResult<List<FileVO>> exportFile(@RequestBody String req){
		try {
			PageRequest request = pageRequest(req);
			
			String template = request.getString(SysFileTEntity.Column.template.getJavaProperty());
			
			fileService.exportData(request, template);
			
			return success();
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	/**
	 * 上传移动app文件
	 * @param file 文件
	 * @param req
	 * @return
	 */
	@RequestMapping("/uploadApp")
	public AjaxResult<List<FileVO>> uploadApp(@RequestParam MultipartFile file, HttpServletRequest req){
		try {
			if (file == null) return fail("upload.nofile");
			AjaxRequest<String> request = ajaxRequest(req);
			List<FileVO> fileVos = Lists.newArrayList();
			FileVO vo = new FileVO();
			vo.setFile(file);
			vo.setTemplate(request.getString(SysFileTEntity.Column.template.getJavaProperty()));
			vo.setDescription(request.getString(SysFileTEntity.Column.description.getJavaProperty()));
			vo.setFileType(FileTypeEnum.Mobile.getCode());
			fileVos.add(vo);
			
			fileService.upload(ajaxRequest(fileVos, request));
			return success(fileVos);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	/**
	 * 上传移动app文件
	 * @param file 文件
	 * @param req
	 * @return
	 */
	@RequestMapping("/uploadReport")
	public AjaxResult<List<FileVO>> uploadReport(@RequestParam MultipartFile file, HttpServletRequest req){
		try {
			if (file == null) return fail("upload.nofile");
			AjaxRequest<String> request = ajaxRequest(req);
			List<FileVO> fileVos = Lists.newArrayList();
			FileVO vo = new FileVO();
			vo.setFile(file);
			vo.setTemplate(request.getString(SysFileTEntity.Column.template.getJavaProperty()));
			vo.setFileType(FileTypeEnum.Report.getCode());
			fileVos.add(vo);
			
			fileService.upload(ajaxRequest(fileVos, request));
			return success(fileVos);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	/**
	 * 下载app
	 * @throws IOException 
	 */
	@RequestMapping("mobile")
	public void downloadApp(HttpServletResponse response) throws IOException {
		try {
			SysFileTEntity file = sysFileService.findMobileApp();
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			if (file != null) {
				// 配置文件下载
				response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getFileName(), "UTF-8"));
				// 下载文件能正常显示中文
				MongoUtils.getResource(new ObjectId(file.getObjectId()), response.getOutputStream());
			}else {
				String path = this.getClass().getClassLoader().getResource(DEFAULT_APP).getFile();
				File defaultFile = new File(path);
				if (defaultFile.exists()) {
					response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(defaultFile.getName(), "UTF-8"));
					StreamUtils.copy(new FileInputStream(defaultFile), response.getOutputStream());
				}
			}
		} catch (Exception e) {
			response.getWriter().write(e.getMessage());
		}
		
	}
	
	/**
	 *  APP上传附件
	 * <p>Title: appUploadFile</p>  
	 * <p>Description: </p>  
	 * @author yupu.chnet
	 * @date 2019年9月2日
	 * @param req
	 * @return
	 */
	@RequestMapping("/appUploadFile")
	public AjaxResult<List<FileVO>> appUploadFile(HttpServletRequest req){
		try {
			StandardMultipartHttpServletRequest fileReq =  (StandardMultipartHttpServletRequest) req;
			Map<String, List<MultipartFile>> fileMap = fileReq.getMultiFileMap();
			List<MultipartFile> fileList = fileMap.get("short_pick");
			MultipartFile [] file = fileList.toArray(new MultipartFile [fileList.size()]);
			if (file == null || file.length == 0)
				return fail("upload.nofile");
			
			AjaxRequest<String> request = ajaxRequest(fileReq);
			List<FileVO> fileVos = doupload(file, request);
			fileVos.forEach(f -> {
				f.setFile(null); // 去除返回的file对象, 否则导致app响应时间过长卡死
			});
			return success(fileVos);
		} catch (Exception e) {
			return fail(e.getMessage());
		}
	}
	
	/**
	 * 公共上传方法
	 * <p>Title: doupload</p>  
	 * <p>Description: </p>  
	 * @author yupu.chnet
	 * @date 2019年9月2日
	 * @param file
	 * @param request
	 * @return
	 */
	private List<FileVO> doupload(MultipartFile [] file , AjaxRequest<String> request) {
		List<FileVO> fileVos = Lists.newArrayList();
		for (MultipartFile f : file) {
			FileVO vo = new FileVO();
			vo.setFile(f);
			vo.setFileType(FileTypeEnum.Save.getCode());
			fileVos.add(vo);
		}
		fileService.upload(ajaxRequest(fileVos, request));
		return fileVos;
	}
}
