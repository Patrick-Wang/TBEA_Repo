package com.tbea.ic.operation.reportframe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.controller.servlet.account.AjaxRedirect;


public class ResponseXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {
		
		if (!"response".equals(e.getTagName())){
			return false;
		}
		
		HttpServletResponse resp = (HttpServletResponse) component.getVar("response");
		String type = e.getAttribute("type");
		if ("json".equals(type)){
			NodeList children = e.getChildNodes();
			JSONObject jo = new JSONObject();
			for (int i = 0; i < children.getLength(); ++i){
				if (children.item(i) instanceof Element) {
					Element child = (Element) children.item(i);
					if ("name".equals(child.getTagName())){
						jo.put("name", parseElObj(component, child.getFirstChild().getTextContent()));
					} else if ("header".equals(child.getTagName())){
						jo.put("header", JSONObject.fromObject(parseElObj(component, child.getFirstChild().getTextContent())));
					} else if ("data".equals(child.getTagName())){
						jo.put("data", JSONArray.fromObject(parseElObj(component, child.getFirstChild().getTextContent())));
					} 
				}
			}
			try {
				resp.setCharacterEncoding("utf-8");       
				resp.setContentType("text/html; charset=utf-8");
				PrintWriter pw = resp.getWriter();
				pw.write(jo.toString());
				pw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else if ("stream".equals(type)){
			ExcelTemplate temp = (ExcelTemplate) component.getVar(e.getAttribute("ref"));
			
			try {
				temp.write(resp, e.getAttribute("name"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return true;
	}

	private Object parseElObj(AbstractXmlComponent component, String textContent) {
		ELParser elp = new ELParser(component);
		List<ELExpression> elexps = elp.parser(textContent);
		Object obj = null;
		try {
			obj = elexps.get(0).value();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}