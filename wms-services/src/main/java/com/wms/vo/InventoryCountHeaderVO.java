/**  
* <p>Title: InventoryCountHeaderVO.java</p>  
* <p>Description: </p>    
* <p>Company: CMHIT</p>  
* @author yupu.chnet  
* @date 2019年10月24日  
* @version 1.0  
*/  
package com.wms.vo;

import com.wms.entity.auto.InventoryCountHeaderTEntity;

/**
 * <p>Title: InventoryCountHeaderVO</p>  
 * <p>Description: </p>  
 * @author yupu.chnet
 * @date 2019年10月24日
 * @version V1.0.0
 */
public class InventoryCountHeaderVO extends InventoryCountHeaderTEntity{

	private String quantityShowFlag;

	/**
	 * @return the quantityShowFlag
	 */
	public String getQuantityShowFlag() {
		return quantityShowFlag;
	}

	/**
	 * @param quantityShowFlag the quantityShowFlag to set
	 */
	public void setQuantityShowFlag(String quantityShowFlag) {
		this.quantityShowFlag = quantityShowFlag;
	} 
}
