package com.wms.common.enums;

public enum FileTypeEnum {
	
	Save("SAVE", "保存"),Import("IMPORT","导入"), Export("EXPORT", "导出"),Mobile("MOBILE","移动端");

	private final String code;
	private final String desc;

	private FileTypeEnum(String code, String desc) {
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
