package com.wuhandata.dataconvert.exception;

import lombok.Data;

/**
 * 统一异常返回对象
 *
 * @param <T>
 */
@Data
public class ResponseResult<T> {

	private String errorMsg;
	private boolean success;
	private Integer code;
	private T data;

	public static ResponseResult success(Object data) {
		return new ResponseResult<>(null, true, 1, data);
	}

	public static ResponseResult fale(String errorMsg) {
		return new ResponseResult<>(errorMsg, false, -1, null);
	}

	private ResponseResult(String errorMsg, boolean success, Integer code, T data) {
		this.errorMsg = errorMsg;
		this.success = success;
		this.code = code;
		this.data = data;
	}
}
