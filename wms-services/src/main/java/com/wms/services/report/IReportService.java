package com.wms.services.report;

import java.util.List;
import java.util.Map;

import com.wms.entity.auto.SysFileTEntity;
import com.wms.vo.ReportVO;

public interface IReportService {

	List<ReportVO> find();
	
	Map<String, SysFileTEntity> getReportFile(String template);
}
