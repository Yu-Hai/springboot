package com.office.springboot.common.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class ResponseUtil {

	private static Logger logger = Logger.getLogger(ResponseUtil.class);
	
	public static void writeContent(Object object,HttpServletResponse response){
		//将实体对象转换为JSON String转换  
		String json=JSONObject.toJSONString(object);
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8"); 
	    PrintWriter out = null;  
	    try {  
	        out = response.getWriter();  
	        out.append(json);  
	        logger.debug("返回是\n");  
	        logger.debug(json);  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (out != null) {  
	            out.close();  
	        }  
	    }  
	}
}
