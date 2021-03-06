package com.xml.frame.report.component.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.frame.script.util.PropMap;
import com.xml.frame.report.component.controller.ControllerRequest.Paramater;
import com.xml.frame.report.util.EasyCalendar;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



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
			try{
				String val = req.getParameter(paraName);
				if (null != val){
                    return JSONArray.fromObject(val);
                }
			}catch(Exception e){
//				e.printStackTrace();
                LoggerFactory.getLogger("REPORT-APPENDER").warn("paraName : " + paraName + "  " + e.getMessage());
			}
			return null;
		}
		
		public List asList(){
			try{
				String val = req.getParameter(paraName);
				if (null != val){
                    JSONArray arr = JSONArray.fromObject(val);
                    List ret = new ArrayList();
                    for (int i = 0; i < arr.size(); ++i){
                        ret.add(arr.get(i));
                    }
                    return ret;
                }
			}catch(Exception e){
//				e.printStackTrace();
                LoggerFactory.getLogger("REPORT-APPENDER").warn("paraName : " + paraName + "  " + e.getMessage());
            }
			return null;

		}
		
		public JSONObject asJsonObject(){
			try{
				String val = req.getParameter(paraName);
				if (null != val) {
                    return JSONObject.fromObject(val);
                }
			}catch(Exception e){
//				e.printStackTrace();
                LoggerFactory.getLogger("REPORT-APPENDER").warn("paraName : " + paraName + "  " + e.getMessage());

            }
			return null;

		}
		
		public EasyCalendar asCalendar(){
			try{
				String val = req.getParameter(paraName);
				return new EasyCalendar(Date.valueOf((String) val));
			}catch(Exception e){
//				e.printStackTrace();
                LoggerFactory.getLogger("REPORT-APPENDER").warn("paraName : " + paraName + "  " + e.getMessage());

            }
			return null;

		}

		public Integer asInt(){
			try{
				String val = req.getParameter(paraName);
				return Integer.valueOf((String) val);
			}catch(Exception e){
//				e.printStackTrace();
                LoggerFactory.getLogger("REPORT-APPENDER").warn("paraName : " + paraName + "  " + e.getMessage());

            }
			return null;

		}
		
		public Map<String, Object> asMap() throws Exception{
			try{
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
			}catch(Exception e){
				e.printStackTrace();
                LoggerFactory.getLogger("REPORT-APPENDER").warn("paraName : " + paraName + "  " + e.getMessage());

            }
			return null;

		}
		
		public String asString(){
			return req.getParameter(paraName);
		}
		
		public String toString(){
			return req.getParameter(paraName);
		}
		
		public XSSFWorkbook asExcel() throws IOException{
			try{
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
				CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile(paraName);
				return new XSSFWorkbook(file.getInputStream());
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;

		}
		
		public String getFileName() throws IOException{
			try{
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
				CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile(paraName);
				return file.getFileItem().getName();
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
		public List<String[]> asCSVUtf8() throws IOException{
			try{
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
				CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile(paraName);
				String csv = IOUtils.toString(file.getInputStream(), "utf-8");
				List<String[]> result = new ArrayList<String[]>();
				String[] rows = csv.split("\n");
				for (int i = 1; i < rows.length; ++i){
					result.add(rows[i].split(","));
				}
				return result;
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
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
