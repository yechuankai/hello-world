package com.wms.common.core.domain.response;


import com.alibaba.fastjson.JSON;
import com.wms.common.utils.MessageUtils;

/**
 * 操作消息提醒
 * 
 */
public class AjaxResult<T> {
	
	/**
	 * 状态类型
	 */
	public enum Type {
		/** 成功 */
		Success("1", "Success"),
		/** 错误 */
		Fail("0", "Fail"),
		/** 警告 */
		Warn("3", "Warn"),
		/** 没有登陆 */
		NoLogin("5", "NoLogin");
		
		private final String code, desc;

		Type(String code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public String getCode() {
			return this.code;
		}
		
		public String getDesc() {
			return this.desc;
		}
	}

	/** 状态码 */
	protected String code;

	/** 返回内容 */
	protected String msg;

	/** 数据对象 */
	protected T data;

	/**
	 * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
	 */
	public AjaxResult() {
	}

	/**
	 * 初始化一个新创建的 AjaxResult 对象
	 * 
	 * @param type 状态类型
	 * @param msg  返回内容
	 */
	public AjaxResult(Type type, String msg) {
		this.code = type.getCode();
		this.msg = type.getDesc();
	}

	/**
	 * 初始化一个新创建的 AjaxResult 对象
	 * 
	 * @param type 状态类型
	 * @param msg  返回内容
	 * @param data 数据对象
	 */
	public AjaxResult(Type type, String msg, T data) {
		String message = MessageUtils.message(msg, null);
		this.code = type.getCode();
		this.msg = message;
		this.data = data;
	}

	/**
	 * 返回成功消息
	 * 
	 * @return 成功消息
	 */
	public static <T> AjaxResult<T> success() {
		return AjaxResult.success(Type.Success.getDesc());
	}

	/**
	 * 返回成功消息
	 * 
	 * @param msg 返回内容
	 * @return 成功消息
	 */
	public static <T> AjaxResult<T> success(String msg) {
		return AjaxResult.success(msg, null);
	}
	
	/**
	 * 返回成功消息
	 * 
	 * @param data 数据对象
	 * @return 成功消息
	 */
	public static <T> AjaxResult<T> success(T data) {
		return new AjaxResult<T>(Type.Success, Type.Success.getDesc(), data);
	}

	/**
	 * 返回成功消息
	 * 
	 * @param msg  返回内容
	 * @param data 数据对象
	 * @return 成功消息
	 */
	public static <T> AjaxResult<T> success(String msg, T data) {
		return new AjaxResult<T>(Type.Success, msg, data);
	}

	/**
	 * 返回警告消息
	 * 
	 * @param msg 返回内容
	 * @return 警告消息
	 */
	public static <T> AjaxResult<T> warn(String msg) {
		return AjaxResult.warn(msg, null);
	}

	/**
	 * 返回警告消息
	 * 
	 * @param msg  返回内容
	 * @param data 数据对象
	 * @return 警告消息
	 */
	public static <T> AjaxResult<T> warn(String msg, T data) {
		return new AjaxResult<T>(Type.Warn, msg, data);
	}

	/**
	 * 返回错误消息
	 * 
	 * @return
	 */
	public static <T> AjaxResult<T> fail() {
		return AjaxResult.fail(Type.Fail.getDesc());
	}

	/**
	 * 返回错误消息
	 * 
	 * @param msg 返回内容
	 * @return 警告消息
	 */
	public static <T> AjaxResult<T> fail(String msg) {
		return AjaxResult.fail(msg, null);
	}
	
	/**
	 * 返回错误消息
	 * 
	 * @param msg 返回内容
	 * @return 警告消息
	 */
	public static <T> AjaxResult<T> fail(Type type, String msg) {
		return new AjaxResult<T>(Type.Warn, msg, null);
	}

	/**
	 * 返回错误消息
	 * 
	 * @param msg  返回内容
	 * @param data 数据对象
	 * @return 警告消息
	 */
	public static <T> AjaxResult<T> fail(String msg, T data) {
		return new AjaxResult<T>(Type.Fail, msg, data);
	}
	
	/**
	 * 返回错误消息
	 * 
	 * @param msg  返回内容
	 * @param data 数据对象
	 * @return 警告消息
	 */
	public static <T> AjaxResult<T> nologin() {
		String message = MessageUtils.message("user.not.login", null);
		return new AjaxResult<T>(Type.NoLogin, message);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
