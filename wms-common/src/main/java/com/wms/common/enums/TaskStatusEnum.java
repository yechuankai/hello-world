package com.wms.common.enums;

/**
 * @description: 任务明细状态Enum
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-16 16:39
 **/

public enum TaskStatusEnum {
    New("10", "新建"),
    Processing("20","执行中"),
    Completed("30","已完成"),
    Wait("40","等待"),
    Cancel("50", "已取消");

    private final String code;
    private final String desc;

    private TaskStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
