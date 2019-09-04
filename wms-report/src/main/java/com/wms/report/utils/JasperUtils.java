package com.wms.report.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.wms.common.config.Global;
import com.wms.common.core.domain.CodelkupVO;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.cache.CodelkUpUtils;
import com.wms.common.utils.cache.LocaleUtils;
import com.wms.common.utils.mongodb.MongoUtils;
import com.wms.common.utils.spring.SpringUtils;
import com.wms.entity.auto.SysFileTEntity;
import com.wms.report.vo.ReportParameter;
import com.wms.services.report.IReportService;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class JasperUtils {

	private static Logger log = LoggerFactory.getLogger(JasperUtils.class);

	private final static String LOCALE = "locale";
	private final static String PREFIX = "report/";
	private final static String SUFFIX = ".jasper";
	private final static String CODELKUP = "__codelkup"; //数据字典标识
	private static Map<String, Class> types = Maps.newHashMap();
	private static Map<String, String> contentTypes = Maps.newHashMap();
	private static Set<String> downloadTypes = Sets.newHashSet();
	
	private static final String BATCH = "batch_";
	private static final String SEPARATOR = ",";

	private static final String PDF = "PDF";
	private static final String DOC = "DOC";
	private static final String XLS = "XLS";
	private static final String CSV = "CSV";
	private static final String HTML = "HTML";

	static {
		types.put(PDF, JRPdfExporter.class);
		types.put(DOC, JRDocxExporter.class);
		types.put(XLS, JRXlsxExporter.class);
		types.put(CSV, JRCsvExporter.class);
		types.put(HTML, HtmlExporter.class);
		
		contentTypes.put(PDF, "application/pdf");
		contentTypes.put(DOC, "application/x-msword");
		contentTypes.put(XLS, "application/vnd_ms-excel");
		contentTypes.put(CSV, "");
		contentTypes.put(HTML, "text/html");
		
		downloadTypes.add(DOC);
		downloadTypes.add(XLS);
		downloadTypes.add(CSV);
	}
	
	public static String getContentType(String format) {
		return contentTypes.get(format);
	}
	
	public static Boolean isDownload(String fromat) {
		return downloadTypes.contains(fromat);
	}

	public static ByteArrayOutputStream generate(ReportParameter rptParam) {
		// ByteArrayOutputStream 底层维护了一个byte[]，可以自动扩容
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Connection conn = null;
		InputStream is = null;
		try {
			
			//优先查询最新文件
			IReportService reportService = SpringUtils.getBean(IReportService.class);
			Map<String, SysFileTEntity> fileMap = reportService.getReportFile(rptParam.getReportName());
			if (fileMap != null && fileMap.containsKey(rptParam.getReportName())) {
				ObjectId fileId = new ObjectId(fileMap.get(rptParam.getReportName()).getObjectId());
				is = MongoUtils.getResource(fileId);
			}else {
				ClassPathResource cpr = new ClassPathResource(StringUtils.join(PREFIX, rptParam.getReportName(), SUFFIX));
				is = cpr.getInputStream();
			}

			// 注入数据连接对象， 方式2，从系统中获取连接对象
			SqlSession sqlSession = SpringUtils.getBean(SqlSessionFactory.class).openSession();
			conn = sqlSession.getConnection();

			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
					setParameters(jasperReport, rptParam.getParameter()), conn);

			Class clz = types.get(rptParam.getFormat());
			if (clz == null)
				throw new BusinessServiceException("JasperUtils", "format {0} error",
						new Object[] { rptParam.getFormat() });

			JRAbstractExporter exporter = (JRAbstractExporter) clz.newInstance();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			if (HTML.equals(rptParam.getFormat()))
				exporter.setExporterOutput(new SimpleHtmlExporterOutput(baos));
			else
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));

			exporter.exportReport();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					conn = null;
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					is = null;
				}
			}
		}
		return baos;
	}

	private static HashMap<String, Object> setParameters(JasperReport report, Map<String, String> m) throws Exception {
		HashMap<String, Object> parms = Maps.newHashMap();
		JRParameter[] jrp = report.getParameters();
		for (JRParameter p : jrp) {
			if (m.containsKey(p.getName())) {
				parms.put(p.getName(), m.get(p.getName()));
			}
		}
		//加载国际化
		String locale = Global.locale;
		if (m.containsKey(LOCALE)) {
			locale = m.get(LOCALE);
			//设置全局国际化
			LocaleUtils.setLocale(locale);
		}
		Map<String, String> localeLabels = LocaleUtils.getLocaleLabels(locale);
		parms.putAll(localeLabels);
		
		
		if (!m.containsKey(CODELKUP)) {
			return parms;
		}
		
		String [] listName = m.get(CODELKUP).split(",");
		//加载数据字典
		AjaxRequest request = new AjaxRequest(m);
		long warehouseId = request.getWarehouseId();
		long companyId = request.getCompanyId();
		for (String l : listName) {
			List<CodelkupVO> codelist = CodelkUpUtils.getCodelkup(companyId, warehouseId, l);
			codelist.forEach(c -> {
				parms.put(l + c.getCode(), c.getDescr());
			});
		}
		return parms;
	}

}
