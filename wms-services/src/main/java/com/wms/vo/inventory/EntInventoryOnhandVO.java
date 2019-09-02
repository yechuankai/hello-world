package com.wms.vo.inventory;

import com.wms.common.utils.bean.BeanUtils;
import com.wms.entity.auto.EntInventoryOnhandTEntity;

import java.math.BigDecimal;

/**
 * @description: 企业级库存VO
 * @author: pengzhen@cmhit.com
 * @create: 2019-08-29 16:40
 **/

public class EntInventoryOnhandVO extends EntInventoryOnhandTEntity {
    private BigDecimal quantityExpected;

    public EntInventoryOnhandVO() {
    }
    public EntInventoryOnhandVO(EntInventoryOnhandTEntity entInventoryOnhandTEntity) {

            BeanUtils.copyBeanProp(this, entInventoryOnhandTEntity);
    }

    public BigDecimal getQuantityExpected() {
        return quantityExpected;
    }

    public void setQuantityExpected(BigDecimal quantityExpected) {
        this.quantityExpected = quantityExpected;
    }
}
