package com.wms.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.WebEngineContext;

public class ParserUtils {

	private static final Logger log = LoggerFactory.getLogger(ParserUtils.class);

	public static final String PRE_FIX = "${";
	public static final String SUFF_FIX = "}";
	private static final String TRANSFER = "'";
	
	public static List<String> getVariables(String input){
		int fromIndex = 0;
		int start = -1;
		int end = -1;
		List<String> variables = new ArrayList<String>();
		try {
			while((start = input.indexOf(PRE_FIX, fromIndex)) > -1) {
				end = input.indexOf(SUFF_FIX, start);
				fromIndex = end + 1;
				String variable = input.substring(start + 2, end);
				if (variables.contains(variable)) {
					continue;
				}
				variables.add(variable);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return variables;
	}
	
	public static String setVariables(String input, List<String> listKey, List<String> listValue, Boolean force){
		String newText = input;
		int length = listKey.size() == listValue.size()? listKey.size() : 0;
		for (int i = 0; i < length; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append(PRE_FIX).append(listKey.get(i)).append(SUFF_FIX);
			String value = listValue.get(i);
			if (!force 
					&& StringUtils.isNotEmpty(value) 
					&& value.indexOf(TRANSFER) > -1) {
				value = value.replace(TRANSFER, "\\" + TRANSFER); //转义
			}
			newText = newText.replace(sb.toString(), value);
		}
		
		return newText;
	}
	
	public static String fillAttributeValue(WebEngineContext ctx, String attributeValue) {
		return fillAttributeValue(ctx, attributeValue, true);
	}
	
	public static String fillAttributeValue(WebEngineContext ctx, String attributeValue, Boolean force) {
		List<String> variables = ParserUtils.getVariables(attributeValue);
		Iterator<String> variableIterator = variables.iterator();
		List<String> values = new ArrayList<String>();
		while(variableIterator.hasNext()) {
			String variable = variableIterator.next();
			Object label = ctx.getVariable(variable);
			if (label == null) {
				variableIterator.remove();
				continue;
			}
			values.add(label.toString()); 
		}
		if(CollectionUtils.isEmpty(variables)) {
			return attributeValue;
		}
		String text = ParserUtils.setVariables(attributeValue, variables, values, force);
		return text;
	}
	
	
	
}
