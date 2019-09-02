package com.wms.common.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

public class MyBatisJavaTypeResolver extends JavaTypeResolverDefaultImpl {

	@Override
	protected FullyQualifiedJavaType calculateBigDecimalReplacement(IntrospectedColumn column,
			FullyQualifiedJavaType defaultType) {
		FullyQualifiedJavaType answer = null;
		if (column.getScale() > 0 || forceBigDecimals) {
            answer = defaultType;
        } else  {
            answer = new FullyQualifiedJavaType(Long.class.getName());
        } 
		return answer;
	}
	
}
