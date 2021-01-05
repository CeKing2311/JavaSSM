package com.ceking.crud.bean;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

/**
 * 通用的返回的类
 * @author cjq
 *
 */
public class WebResult {
	//状态码：
	private int code;
	private String msg;
	private Map<String, Object> data =new HashMap<>();
	
	public static WebResult success() {
		WebResult webResult =new WebResult();
		webResult.setCode(200);
		webResult.setMsg("处理成功!");
		return  webResult;				 
	}
	public static WebResult fail() {
		WebResult webResult =new WebResult();
		webResult.setCode(400);
		webResult.setMsg("处理失败!");
		return  webResult;				 
	}
	public WebResult add(String key,Object data) {
		this.getData().put(key, data);
		return this;			
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
