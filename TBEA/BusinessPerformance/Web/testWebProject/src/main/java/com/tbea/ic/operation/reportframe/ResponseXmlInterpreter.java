package com.tbea.ic.operation.reportframe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.w3c.dom.Element;

import com.tbea.ic.operation.common.excel.ExcelTemplate;


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
		XmlUtil.each(elem.getElementsByTagName("item"), new XmlUtil.OnLoop(){
			@Override
			public void on(Element el) {
				if ("array".equals(el.getAttribute("type"))){
					ja.add(parseJsonArray(el, elParser));
				}else{
					ja.add(parseJsonObject(el, elParser));
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
		if ("json".equals(type)){
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
			
		}else if ("stream".equals(type)){
			ExcelTemplate temp = (ExcelTemplate) component.getVar(e.getAttribute("ref"));
			
			try {
				temp.write(resp, e.getAttribute("name"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		return true;
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