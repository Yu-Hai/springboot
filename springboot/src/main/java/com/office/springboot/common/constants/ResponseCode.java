package com.office.springboot.common.constants;

/**
 * 返回标记类
 * 
 * @author Neo 2017-4-30
 *
 */
public class ResponseCode {
	
	/** 成功 **/
	public final static String RESPONSE_OK="200";
	/** 服务器异常 **/
	public final static String RESPONSE_ERROR="50050";
	/** 参数校验不通过 **/
	public final static String VALIDATE_NOT_PASS="50051";
	/** 未登录或者登录超时 **/
	public final static String SESSION_TIME_OUT="50052";
	/** 未知错误code **/
	public final static String UNKNOWN_ERROR_CODE="50053";
	

}
