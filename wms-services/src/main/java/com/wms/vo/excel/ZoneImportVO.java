package com.wms.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.wms.common.core.domain.AbstractExcelModel;

/**
 * @description: 库区导出类
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-31 10:22
 **/

public class ZoneImportVO extends AbstractExcelModel {

    @Excel(name = "zoneCode")
    private String zoneCode;

    @Excel(name = "type")
    private String type;

    @Excel(name = "pickToLocation")
    private String pickToLocation;

    @Excel(name = "active")
    private String active;

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPickToLocation() {
        return pickToLocation;
    }

    public void setPickToLocation(String pickToLocation) {
        this.pickToLocation = pickToLocation;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
