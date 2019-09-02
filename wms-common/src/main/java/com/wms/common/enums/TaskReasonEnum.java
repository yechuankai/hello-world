package com.wms.common.enums;

public enum TaskReasonEnum {
	
    Skip("SKIP", "跳过"),
    Lose("LOSE","丢失"),
    Bad("BAD","损坏"),
    Pend("PEND","挂起"),
    UnAllocate("UNALLOCATED", "撤销分配");

    private final String code;
    private final String desc;

    private TaskReasonEnum(String code, String desc) {
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
