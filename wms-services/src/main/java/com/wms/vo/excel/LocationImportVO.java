package com.wms.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.wms.common.core.domain.AbstractExcelModel;

/**
 * @description: 库位导出VO类
 * @author: pengzhen@cmhit.com
 * @create: 2019-07-31 09:26
 **/

public class LocationImportVO extends AbstractExcelModel {

    @Excel(name = "locationCode")
    private String locationCode;

    @Excel(name = "locationType")
    private String locationType;

    @Excel(name = "locationLogical")
    private String locationLogical;

    @Excel(name = "zoneCode")
    private String zoneCode;

    @Excel(name = "x")
    private Long x;

    @Excel(name = "y")
    private Long y;

    @Excel(name = "z")
    private Long z;

    @Excel(name = "skuMix")
    private String skuMix;

    @Excel(name = "lotMix")
    private String lotMix;

    @Excel(name = "active")
    private String active;

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getLocationLogical() {
        return locationLogical;
    }

    public void setLocationLogical(String locationLogical) {
        this.locationLogical = locationLogical;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public Long getZ() {
        return z;
    }

    public void setZ(Long z) {
        this.z = z;
    }

    public String getSkuMix() {
        return skuMix;
    }

    public void setSkuMix(String skuMix) {
        this.skuMix = skuMix;
    }

    public String getLotMix() {
        return lotMix;
    }

    public void setLotMix(String lotMix) {
        this.lotMix = lotMix;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
