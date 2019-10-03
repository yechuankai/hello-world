package com.wms.services.inventory.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wms.common.core.domain.ExcelTemplate;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.services.IExcelService;
import com.wms.common.enums.ExcelTemplateEnum;
import com.wms.common.exception.BusinessServiceException;
import com.wms.common.utils.StringUtils;
import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.TaskDetailTEntity;
import com.wms.services.inventory.IPutawayTaskService;
import com.wms.services.inventory.ITaskService;
import com.wms.vo.TaskDetailVO;
import com.wms.vo.excel.LoadTaskExcelVO;
import com.wms.vo.excel.PutawayTaskExcelVO;

@Service
public class LoadTaskServiceImpl implements IPutawayTaskService, IExcelService<LoadTaskExcelVO> {

	@Autowired
	private ITaskService taskService;
	
	@Override
    public ExcelTemplate getExcelTemplate() {
        return new ExcelTemplate<LoadTaskExcelVO>(ExcelTemplateEnum.PutawayTask.getCode(), LoadTaskExcelVO.class);
    }
	
	@Override
    public List<LoadTaskExcelVO> exportData(PageRequest request) throws BusinessServiceException {
		final String PRO_SEARCH_USERNAME = "suserName";
		String username = request.getString(PRO_SEARCH_USERNAME);
		if (StringUtils.isEmpty(username)){
			request.remove(TaskDetailTEntity.Column.userName.getJavaProperty());
        }else{
        	request.put(TaskDetailTEntity.Column.userName.getJavaProperty(), username);
        }
		List<LoadTaskExcelVO> returnList = Lists.newArrayList();
        List<TaskDetailVO> task = taskService.findLoad(request);
        if (CollectionUtils.isEmpty(task)) {
            return returnList;
        }
        task.forEach(d ->{
        	LoadTaskExcelVO load = new LoadTaskExcelVO();
            BeanUtils.copyBeanProp(load, d);
            returnList.add(load);
        });
        return returnList;
    }
	
	
	
}
