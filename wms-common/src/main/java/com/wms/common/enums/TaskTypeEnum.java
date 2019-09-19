package com.wms.common.enums;

/**
 * @description: 任务明细状态Enum
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-16 16:39
 **/

public enum TaskTypeEnum {
	
    Pick("PK", "拣货"),
    Putaway("PA","上架"),
    CycleCount("CC","盘点"),
    Move("MV","移动"),
    Load("LOAD", "装车"),
    Unload("UNLOAD", "卸车");
	
    private final String code;
    private final String desc;

    private TaskTypeEnum(String code, String desc) {
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
