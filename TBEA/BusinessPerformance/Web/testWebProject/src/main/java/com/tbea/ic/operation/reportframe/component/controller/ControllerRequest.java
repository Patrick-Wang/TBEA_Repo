package com.tbea.ic.operation.reportframe.component.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.common.PropMap;

public class ControllerRequest extends PropMap{
	HttpServletRequest req;
	public static class Paramater{
		HttpServletRequest req;
		String paraName;
		
		public Paramater(HttpServletRequest req, String paraName) {
			super();
			this.req = req;
			this.paraName = paraName;
		}

		public JSONArray asJsonArray(){
			String val = req.getParameter(paraName);
			return JSONArray.fromObject(val);
		}
		
		public JSONObject asJsonObject(){
			String val = req.getParameter(paraName);
			return JSONObject.fromObject(val);
		}
		
		public EasyCalendar asCalendar(){
			String val = req.getParameter(paraName);
			return new EasyCalendar(Date.valueOf((String) val));
		}

		public Integer asInt(){
			String val = req.getParameter(paraName);
			return Integer.valueOf((String) val);
		}
		
		public Map<String, Object> asMap() throws Exception{
			if ("parameters".equals(paraName)){
				Map<String, Object> mp = new HashMap<String, Object>();
				String key = null;
				Enumeration<String> keys = req.getParameterNames();
				while (keys.hasMoreElements()){
					key = keys.nextElement();
					mp.put(key, req.getParameter(key));
				}
				return mp;
			}
			throw new Exception(paraName + " cannot be cast to map");
		}
		
		public String asString(){
			return req.getParameter(paraName);
		}
		
		public String toString(){
			return req.getParameter(paraName);
		}
		
		public XSSFWorkbook asExcel() throws IOException{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req; 
			CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile(paraName);
			return new XSSFWorkbook(file.getInputStream());
		}
	}
	
	
	
	public ControllerRequest(HttpServletRequest req) {
		super();
		this.req = req;
	}

	public HttpServletRequest getRequest(){
		return req;
	}
	
	public ServletContext getServletContext(){
		return req.getSession().getServletContext();
	}
	
	@Override
	public Object getProperty(Object key) throws Exception {
		return new Paramater(req, (String)key);
	}
}
