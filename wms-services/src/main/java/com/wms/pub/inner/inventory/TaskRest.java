package com.wms.pub.inner.inventory;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.wms.common.core.controller.BaseController;
import com.wms.common.core.domain.request.AjaxRequest;
import com.wms.common.core.domain.request.PageRequest;
import com.wms.common.core.domain.response.AjaxResult;
import com.wms.common.core.domain.response.PageResult;
import com.wms.common.utils.StringUtils;
import com.wms.entity.auto.TaskDetailTEntity;
import com.wms.services.inventory.ITaskService;

/**
 * @description: 上架/拣货任务Rest
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-04 14:55
 **/
@RestController
@RequestMapping("/services/inner/inventory/task")
public class TaskRest extends BaseController {

    @Autowired
    ITaskService taskService;

    @RequestMapping(value = "/find")
    public PageResult<TaskDetailTEntity> find(@RequestBody String req) {
        List<TaskDetailTEntity> list = null;
        final String PRO_SEARCH_USERNAME = "suserName";
        try {
        	PageRequest pageRequest = pageRequest(req);
            //处理请求对象与查询条件重复
            String username = pageRequest.getString(PRO_SEARCH_USERNAME);
            
            PageHelper.startPage(pageRequest.getPageStart(), pageRequest.getPageSize());

            if (StringUtils.isEmpty(username)){
                pageRequest.remove(TaskDetailTEntity.Column.userName.getJavaProperty());
            }else{
                pageRequest.put(TaskDetailTEntity.Column.userName.getJavaProperty(), username);
            }

            list = taskService.find(pageRequest);
        } catch (Exception e) {
            return pageFail(e.getMessage());
        }
        return page(list);
    }

    @RequestMapping(value = "/save")
    public AjaxResult save(@RequestBody String req) {
        try {
            AjaxRequest<List<TaskDetailTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<TaskDetailTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = taskService.modify(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }
    
    @RequestMapping(value = "/delete")
	public AjaxResult delete(@RequestBody String req) {
    	try {
            AjaxRequest<List<TaskDetailTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<TaskDetailTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record delete.");
            }
            boolean flag = taskService.delete(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
	}

    @RequestMapping(value = "/cancel")
    public AjaxResult cancel(@RequestBody String req) {
        try {
            AjaxRequest<List<TaskDetailTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<TaskDetailTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = taskService.cancel(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }
    @RequestMapping(value = "/putawayConfirm")
    public AjaxResult putawayConfirm(@RequestBody String req) {
        try {
            AjaxRequest<List<TaskDetailTEntity>> request = ajaxRequest(req, new TypeReference<AjaxRequest<List<TaskDetailTEntity>>>() {});
            if (CollectionUtils.isEmpty(request.getData())) {
                return fail("no record update.");
            }
            boolean flag = taskService.putawayConfirm(request);
            if (flag) {
                return success();
            }
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return fail();
    }

}
