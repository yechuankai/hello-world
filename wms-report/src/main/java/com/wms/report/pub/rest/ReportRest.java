package com.wms.report.pub.rest;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wms.report.utils.JasperUtils;
import com.wms.report.vo.ReportParameter;

@RestController
@RequestMapping("/report")
public class ReportRest {
	private static Logger logger = LoggerFactory.getLogger(ReportRest.class);
	
	@Value("${wms.url.report}")
	private String host;
	
    @RequestMapping("/{format}/{reportName}")
    public void viewReport(@PathVariable("format") String format, @PathVariable("reportName") String reportName, @RequestParam Map<String, String> map, HttpServletResponse response){
    	String formatUp =  format.toUpperCase();
    	//当前地址
    	map.put(JasperUtils.HOST, host);
    	ReportParameter rm = new ReportParameter(reportName, formatUp, map);
         try {
             ByteArrayOutputStream baos = JasperUtils.generate(rm);
             response.setContentType(JasperUtils.getContentType(formatUp));
             if (JasperUtils.isDownload(formatUp))
            	 response.setHeader("Content-Disposition", "attachment;filename=" + reportName + "_" + new Date().getTime() + "." + format);
  			 response.getOutputStream().write(baos.toByteArray());
 			 baos.close();
         } catch (Exception e) {
             logger.error(e.getMessage(), e);
         }

    }

}
