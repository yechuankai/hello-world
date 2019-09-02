package com.wms.common.utils;

import java.text.MessageFormat;

import com.wms.common.utils.cache.LocaleUtils;

/**
 *   翻译文件
 * 
 */
public class MessageUtils{
    /**
     * 根据消息键和参数 获取消息 委托给spring messageSource
     *
     * @param code 消息键
     * @param args 参数
     * @return
     */
    public static String message(String code, Object... args){
    	String convertMessage = null;
    	String message = LocaleUtils.getLocaleLabel(code);
    	try {
    		Object [] newArgs = new Object[args.length];
    		if (args != null) {
    			for (int i = 0; i < args.length; i++) {
					Object o = args[i];
					if (o instanceof Long)
    					o = String.valueOf(o);
					newArgs[i] = o;
				}
    		}
    		
        	if (StringUtils.isEmpty(message)) 
        		convertMessage = "null";
        	else
        		convertMessage = MessageFormat.format(message, newArgs);
		} catch (Exception e) {
			convertMessage = message;
		}
    	return convertMessage;
    }
}
