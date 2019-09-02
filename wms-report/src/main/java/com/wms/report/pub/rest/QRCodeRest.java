package com.wms.report.pub.rest;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wms.report.utils.QRCodeUtils;

/**
 * 二维码生成
 * @author liuxiangying.chnet
 *
 */
@RestController
@RequestMapping("QRCode")
public class QRCodeRest {
	@RequestMapping("create")
	public void createQRCode(@RequestParam String content,HttpServletResponse response) throws Exception {
		BufferedImage image = QRCodeUtils.encode(content, null, false);
		response.setContentType("application/octet-stream");
		ImageIO.write(image, "jpg", response.getOutputStream());
		return;
	}
}
