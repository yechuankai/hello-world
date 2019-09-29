package com.wms.pub.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.utils.mongodb.MongoUtils;
import com.wms.common.utils.spring.SpringUtils;

@RestController
@RequestMapping("/services/public/")
public class RestServices extends BaseController{

	@RequestMapping("/test")
	public AjaxResult test(){
		getFile("E:\\数据恢复\\p\\labor_card");
		return success();
	}
	
	
	public static void getFile(String path){
		File f = new File(path);
		if (!f.exists()) {
			return;
		}
		if (f.isDirectory()) {
			File [] fileList = f.listFiles();
			for (File file : fileList) {
				if (file.isDirectory()) {
					//递归调用
					getFile(file.getAbsolutePath());
				}else {
					//存储到mongodb中
					GridFsTemplate gridFs = SpringUtils.getBean(GridFsTemplate.class);
					try {
						ObjectId id = gridFs.store(new FileInputStream(file), f.getName());
						System.out.println(id.toHexString() + "\t"+ file.getName()  + "\t" + f.getAbsolutePath());
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
