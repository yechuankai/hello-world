package com.wms.services.report.impl;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.text.html.parser.Entity;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wms.common.core.domain.CodelkupVO;
import com.wms.common.enums.FileTypeEnum;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.cache.CodelkUpUtils;
import com.wms.entity.auto.SysFileTEntity;
import com.wms.services.report.IReportService;
import com.wms.services.sys.ISysFileService;
import com.wms.vo.ReportVO;

@Service
public class ReportServiceImpl implements IReportService {
	
	//报表数据字典
	private static final String LISTNAME = "REPORT";
	
	@Autowired
	private ISysFileService fileService;

	@Override
	public List<ReportVO> find() {
		
		List<CodelkupVO> list = CodelkUpUtils.getCodelkup(LISTNAME);
		Map<String, SysFileTEntity> fileMap = getReportFile(null);
		
		List<ReportVO> reportList = Lists.newArrayList();
		
		list.forEach(l -> {
			ReportVO report = new ReportVO();
			report.setCode(l.getCode());
			report.setDescr(l.getDescr());
			
			//查询是否存在上传的文件
			SysFileTEntity file = fileMap.get(l.getCode());
			if (file != null) {
				report.setFileId(file.getFileId());
				report.setFileName(file.getFileName());
				report.setUpdateBy(file.getUpdateBy());
				report.setUpdateTime(file.getUpdateTime());
			}
			
			reportList.add(report);
			
		});
		return reportList;
	}
	
	@Override
	public Map<String, SysFileTEntity> getReportFile(String template) {
		Map<String, SysFileTEntity> map = Maps.newHashMap();
		
		List<SysFileTEntity> fileList = fileService.find(FileTypeEnum.Report, template);
		if (CollectionUtils.isEmpty(fileList))
			return map;
		
		//移除所有模板为空的数据
		HashSet<SysFileTEntity> noTemplateEntity = new HashSet<>();
		fileList.stream().forEach(entity -> {
			if (StringUtils.isEmpty(entity.getTemplate())) {
				noTemplateEntity.add(entity);
			}
		});
		fileList.removeAll(noTemplateEntity);
		
		//分组获取时间最新的数据
		Map<String, Optional<SysFileTEntity>> groupMap = fileList.stream().collect(Collectors.groupingBy(SysFileTEntity::getTemplate, Collectors.maxBy(new Comparator<SysFileTEntity>() {
			@Override
			public int compare(SysFileTEntity o1, SysFileTEntity o2) {
				return o1.getCreateTime().compareTo(o2.getCreateTime());
			}
		})));
		
		groupMap.forEach((k, v) -> {
			map.put(k, v.get());
		});
		
		return map;
	}

}
