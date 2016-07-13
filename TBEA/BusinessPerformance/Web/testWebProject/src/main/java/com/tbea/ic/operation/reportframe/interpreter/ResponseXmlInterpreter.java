package com.tbea.ic.operation.reportframe.interpreter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.formatter.v2.core.FormatterServer;
import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.component.controller.Controller;
import com.tbea.ic.operation.reportframe.el.ELParser;
import com.tbea.ic.operation.reportframe.util.XmlUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil.OnLoop;


public class ResponseXmlInterpreter implements XmlInterpreter {

	ELParser elp;
	
	JSONObject parseJsonObject(Element pElem) throws Exception{
		JSONObject pJson = new JSONObject();
		XmlUtil.each(pElem.getChildNodes(), new XmlUtil.OnLoop(){
			@Override
			public void on(Element elem) throws Exception {
				if ("array".equals(elem.getAttribute("type"))){
					JSONArray ja = parseJsonArray(elem);
					pJson.put(elem.getTagName(), ja);
				} else {
					String text = elem.getFirstChild().getTextContent();
					if (null != text && !text.isEmpty()){
						pJson.put(elem.getTagName(), parseElObj(text));
					}else {
						pJson.put(elem.getTagName(), parseJsonObject(elem));
					}
				}
			}
		});
		return pJson;
	}
	
	protected JSONArray parseJsonArray(Element elem) throws DOMException, Exception {
		JSONArray ja = new JSONArray();
		ja.addAll(XmlUtil.toStringList(elem.getFirstChild().getTextContent(), elp));
		XmlUtil.each(elem.getChildNodes(), new XmlUtil.OnLoop(){
			@Override
			public void on(Element el) throws DOMException, Exception {
				if ("item".equals(el.getTagName())){
					if ("array".equals(el.getAttribute("type"))){
						ja.add(parseJsonArray(el));
					}else{
						ja.add(parseJsonObject(el));
					}
				}
			}
		});
		return ja;
	}

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (!Schema.isResponse(e)){
			return false;
		}
		
		elp = new ELParser(component);
		
		HttpServletResponse resp = (HttpServletResponse) component.getVar("response");
		String type = e.getAttribute("type");
		if ("json".equalsIgnoreCase(type)){
			JSONObject jo = parseJsonObject(e);
			try {
				resp.setCharacterEncoding("utf-8");       
				resp.setContentType("text/html; charset=utf-8");
				PrintWriter pw = resp.getWriter();
				pw.write(jo.toString());
				pw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} else if ("excel".equalsIgnoreCase(type)){
			try {
				ExcelTemplate temp = (ExcelTemplate) component.getVar(e.getAttribute("ref"));
				FormatterServer serv = (FormatterServer) component.getVar(e.getAttribute("serv"));
				serv.getResult();
				temp.write(resp, e.getAttribute("name"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if ("jsp".equalsIgnoreCase(type)){
			ELParser elp = new ELParser(component);
			String jspName = (String) XmlUtil.getObjectAttr(e, "name", elp);
			Map map = parseMap(elp, e);
			component.global(Controller.MODEL_AND_VIEW, new ModelAndView(jspName, map));
		}
		
		return true;
	}

	private Map parseMap(ELParser elp, Element e) throws Exception {
		Map map = new HashMap();
		XmlUtil.each(e.getElementsByTagName("map"), new OnLoop(){

			@Override
			public void on(Element elem) throws Exception {
				String key = elem.getAttribute("key");
				if (!key.isEmpty()){
					Object val = XmlUtil.getObjectAttr(elem, "value", elp);
					map.put(key, val);
				}else{
					Map<String, String> mp = (Map) XmlUtil.getObjectAttr(elem, "map", elp);
					map.putAll(mp);					
				}
			}
		});
		return map;
	}

	private Object parseElObj(String text) throws Exception {
		Object obj = XmlUtil.getELValue(text, elp);
		if (null == obj){
			obj = text;
		}
		return obj;
	}
}