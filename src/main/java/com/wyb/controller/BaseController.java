package com.wyb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**  
*  
*
* @author ghy  
* @date 2017�?�?3�?
* 类说�? :
*/
public class BaseController extends MultiActionController{

	/**
	 * 将一个对象转换成json字符�?
	 * 
	 * @param obj
	 * @return
	 */
	protected String toJsonString(Object obj) {
		String result = JSONObject.toJSONString(obj,SerializerFeature.WriteDateUseDateFormat);
		logger.debug("convert Java Object=" + obj + " to json string=" + result);
		return result;
	}

	/**
	 * 将一个json字符串装换成�?��java对象
	 * 
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	protected <T> T parseJsonString(String jsonString, Class<T> clazz) {
		T t = JSONObject.parseObject(jsonString, clazz);
		logger.debug("convert jsonString=" + jsonString + " to Java Object=" + t);
		return t;
	}

	/**
	 * 响应Hash错误码键
	 */
	public static final String KEY_RESP_CODE = "code";
	/**
	 * 响应原因Hash�?
	 */
	public static final String KEY_RESP_REASON = "reason";

	/**
	 * 请求响应成功状�?
	 */
	public final static String RESPONE_MESSAGE_STATUS_SUCCESS = "0";

	/**
	 * 请求响应失败状�?
	 */
	public final static String RESPONE_MESSAGE_STATUS_FAILURE = "1";

	/**
	 * 获取操作失败的响�?
	 * 
	 * @param errorMessage
	 * @return
	 */
	protected String buildErrorRespone(String errorMessage) {
		return buildRespone(errorMessage, RESPONE_MESSAGE_STATUS_FAILURE);
	}

	/**
	 * 获取操作失败的响�?
	 * @param parms
	 * @return
	 */
	protected String buildErrorRespone(Map<String, Object> parms) {
		return buildRespone(parms, "操作失败!", RESPONE_MESSAGE_STATUS_FAILURE);
	}
	
	
	/**
	 * 获取操作失败的缺省响�?
	 * 
	 * @return
	 */
	protected String buildDefaultErrorRespone() {
		return buildRespone("", RESPONE_MESSAGE_STATUS_FAILURE);
	}

	/**
	 * 
	 * 获取操作成功的缺省响�?
	 * 
	 * @param errorMessage
	 * @return
	 */
	protected String buildDefaultSuccessRespone() {
		return buildRespone("成功!", RESPONE_MESSAGE_STATUS_SUCCESS);
	}

	/**
	 * 
	 * 获取操作成功的响�?
	 * 
	 * @param errorMessage
	 * @return
	 */
	protected String buildSuccessRespone(String message) {
		return buildRespone(message, RESPONE_MESSAGE_STATUS_SUCCESS);
	}

	/**
	 * 
	 * 获取操作成功的响�?
	 * 
	 * @param errorMessage
	 * @return
	 */
	protected String buildSuccessRespone(Map<String, Object> parms) {
		return buildRespone(parms, "操作成功!", RESPONE_MESSAGE_STATUS_SUCCESS);
	}
	
	protected String buildSuccessRespone(Object obj) {
		return buildRespone(obj, "操作成功!", RESPONE_MESSAGE_STATUS_SUCCESS);
	}

	/**
	 * 建�?响应信息
	 * 
	 * @param messageCategory
	 * @param message
	 * @param code
	 * @return
	 */
	private String buildRespone(Map<String, Object> parms, String message, String code) {
		Map<String, Object> respone = parms;
		if (respone == null) {
			respone = new HashMap<String, Object>();
		}
		respone.put("message", message);
		respone.put("code", code);
		if("0".equals(code)){
			respone.put("success", true);
		}else{
			respone.put("success", false);
		}
		return JSONObject.toJSONString(respone,SerializerFeature.WriteDateUseDateFormat);
	}
	
	
	private String buildRespone(Object obj, String message, String code) {
		Map<String, Object> respone =  new HashMap<String, Object>();
		respone.put("data", obj);
		respone.put("code", code);
		if("0".equals(code)){
			respone.put("success", true);
		}else{
			respone.put("success", false);
		}
		return JSONObject.toJSONString(respone,SerializerFeature.WriteDateUseDateFormat);
	}

	/**
	 * 建�?响应信息
	 * 
	 * @param messageCategory
	 * @param message
	 * @return
	 */
	private String buildRespone(String message, String code) {
		return buildRespone(null, message, code);
	}
	
		PrintWriter printWriter = null;
		protected void renderJson(HttpServletResponse response,String responseString){
		try {
			printWriter = response.getWriter();
			printWriter.print(responseString);
		} catch (IOException e) {
			logger.error(e);
		} finally {
			printWriter.flush();
			printWriter.close();
		}
	}
	
	protected String getPara(HttpServletRequest request,String key){
		return StringUtils.hasText(request.getParameter(key)) ? request.getParameter(key) : "";
	}

	/**
	 * 获取整型参数
	 * 
	 * @return
	 */
	protected int getPara2Int(HttpServletRequest request, String key) {
		int value = 0;

		try {
			if (StringUtils.hasText(request.getParameter(key))) {
				value = Integer.valueOf(request.getParameter(key));
			}
		} catch (Exception e) {
			logger.error(e);
		}

		return value;
	}
	
	
	
	
	
	
}

