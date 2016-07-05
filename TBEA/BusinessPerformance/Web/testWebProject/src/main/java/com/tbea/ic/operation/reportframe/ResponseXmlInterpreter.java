package com.tbea.ic.operation.reportframe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Element;

import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.formatter.v2.core.FormatterServer;
import com.tbea.ic.operation.reportframe.XmlUtil.OnLoop;


public class ResponseXmlInterpreter implements XmlInterpreter {

	
	JSONObject parseJsonObject(Element pElem, ELParser elParser){
		JSONObject pJson = new JSONObject();
		XmlUtil.each(pElem.getChildNodes(), new XmlUtil.OnLoop(){
			@Override
			public void on(Element elem) {
				if ("array".equals(elem.getAttribute("type"))){
					JSONArray ja = parseJsonArray(elem, elParser);
					pJson.put(elem.getTagName(), ja);
				} else {
					String text = elem.getFirstChild().getTextContent();
					if (null != text && !text.isEmpty()){
						pJson.put(elem.getTagName(), parseElObj(elParser, text));
					}else {
						pJson.put(elem.getTagName(), parseJsonObject(elem, elParser));
					}
				}
			}
		});
		return pJson;
	}
	
	protected JSONArray parseJsonArray(Element elem, ELParser elParser) {
		JSONArray ja = new JSONArray();
		
		String text = elem.getFirstChild().getTextContent();
		text = text.replaceAll("\\s", "");
		if (!text.isEmpty()){
			String[] vals = text.split(",");
			for (String val : vals){
				ja.add(val);
			}
		}
		XmlUtil.each(elem.getChildNodes(), new XmlUtil.OnLoop(){
			@Override
			public void on(Element el) {
				if ("item".equals(el.getTagName())){
					if ("array".equals(el.getAttribute("type"))){
						ja.add(parseJsonArray(el, elParser));
					}else{
						ja.add(parseJsonObject(el, elParser));
					}
				}
			}
		});
		return ja;
	}

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {
		
		if (!"response".equals(e.getTagName())){
			return false;
		}
		
		HttpServletResponse resp = (HttpServletResponse) component.getVar("response");
		String type = e.getAttribute("type");
		if ("json".equalsIgnoreCase(type)){
			JSONObject jo = parseJsonObject(e, new ELParser(component));
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

	private Map parseMap(ELParser elp, Element e) {
		Map map = new HashMap();
		XmlUtil.each(e.getElementsByTagName("map"), new OnLoop(){

			@Override
			public void on(Element elem) {
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

	private Object parseElObj(ELParser elp, String text) {
		Object obj = text;
		List<ELExpression> elexps = elp.parser(text);
		if (!elexps.isEmpty()){
			try {
				obj = elexps.get(0).value();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
}