package com.xml.frame.report.interpreter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

import com.frame.script.el.ELParser;
import com.util.tools.StringUtil;
import com.util.tools.word.POIPdf;
import com.util.tools.xml.Loop;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.component.controller.Controller;
import com.xml.frame.report.util.ExcelHelper;
import com.xml.frame.report.util.v2.core.FormatterServer;
import com.xml.frame.report.util.xml.XmlElWalker;
import com.xml.frame.report.util.xml.XmlUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class ResponseXmlInterpreter implements XmlInterpreter {

	ELParser elp;
	
	JSONObject parseJsonObject(Element pElem) throws Exception{
		JSONObject pJson = new JSONObject();
		
		XmlElWalker.eachChildren(pElem, elp, new Loop(){
			@Override
			public void on(Element elem) throws Exception {
				if ("array".equals(elem.getAttribute("type"))){
					JSONArray ja = parseJsonArray(elem);
					pJson.put(elem.getTagName(), ja);
				} else if ("jarray".equals(elem.getAttribute("type"))){
					if (XmlUtil.hasText(elem)){
						Object obj = XmlUtil.parseELText(XmlUtil.getText(elem), elp);
						pJson.put(elem.getTagName(), JSONArray.fromObject(obj));
					}
				}  else if ("jobject".equals(elem.getAttribute("type"))){
					if (XmlUtil.hasText(elem)){
						Object obj = XmlUtil.parseELText(XmlUtil.getText(elem), elp);
						pJson.put(elem.getTagName(), JSONObject.fromObject(obj));
					}					
				} else {
					String text = null;
					if (XmlUtil.hasText(elem)){
						text = XmlUtil.getText(elem).replaceAll("\\s", "");
					}
					if (null != text && !text.isEmpty()){
						pJson.put(elem.getTagName(), XmlUtil.parseELText(text, elp));
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
		if (XmlUtil.hasText(elem)){
			ja.addAll(XmlUtil.toStringList(StringUtil.shrink(XmlUtil.getText(elem)), elp));
		}
		
		XmlElWalker.eachChildren(elem, elp, new Loop(){
			@Override
			public void on(Element el) throws DOMException, Exception {
				if ("item".equals(el.getTagName())){
					if ("array".equals(el.getAttribute("type"))){
						ja.add(parseJsonArray(el));
					}else if ("json".equals(el.getAttribute("type"))){
						if (XmlUtil.hasText(elem)){
							ja.add(XmlUtil.parseELText(XmlUtil.getText(elem), elp));
						}						
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
		if (resp == null){
			return true;
		}
		
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
				ExcelHelper temp = (ExcelHelper) component.getVar(e.getAttribute("ref"));
				FormatterServer serv = (FormatterServer) component.getVar(e.getAttribute("serv"));
				serv.getResult();

				if ("pdf".equals(e.getAttribute("target"))) {
//					resp.setContentType("application/octet-stream");
//					resp.setHeader("Content-disposition","attachment;filename=\""+ java.net.URLEncoder.encode( XmlUtil.getString(e.getAttribute("name"), elp), "UTF-8")  +"\"");
//					resp.getOutputStream().write(ExcelUtil.toPdf(temp.getWorkbook()));
				} else{
					temp.write(resp, XmlUtil.getString(e.getAttribute("name"), elp));
				}

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if ("word".equalsIgnoreCase(type)){
			try {
				WordprocessingMLPackage pkg = (WordprocessingMLPackage) component.getVar(e.getAttribute("ref"));


				resp.setContentType("application/octet-stream");
				resp.setHeader("Content-disposition","attachment;filename=\""+ java.net.URLEncoder.encode( XmlUtil.getString(e.getAttribute("name"), elp), "UTF-8")  +"\"");

				if ("pdf".equals(e.getAttribute("target"))){
					byte[] pdf = POIPdf.docx2Pdf(pkg);
					resp.getOutputStream().write(pdf);
				}else{
					pkg.save(resp.getOutputStream());
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if ("file".equalsIgnoreCase(type)){
			try {
				byte[] file = (byte[]) component.getVar(e.getAttribute("ref"));
				resp.setContentType("application/octet-stream");
				resp.setHeader("Content-disposition","attachment;filename=\""+ java.net.URLEncoder.encode( XmlUtil.getString(e.getAttribute("name"), elp), "UTF-8")  +"\"");
				resp.getOutputStream().write(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if ("jsp".equalsIgnoreCase(type)){
			ELParser elp = new ELParser(component);
			String jspName = (String) XmlUtil.getObjectAttr(e, "name", elp);
			Map map = parseMap(elp, e);
			component.global(Controller.MODEL_AND_VIEW, new ModelAndView(jspName, map));
		}
		
		return true;
	}

	private Map parseMap(ELParser elp, Element e) throws Exception {
		Map map = new HashMap();
		XmlElWalker.each(e.getElementsByTagName("map"), elp, new Loop(){

			@Override
			public void on(Element elem) throws Exception {
				String key = elem.getAttribute("key");
				if (!key.isEmpty()){
					Object val = XmlUtil.getObjectAttr(elem, "value", elp);
					if (val == null){
						if (XmlUtil.hasText(elem)){
							val = XmlUtil.parseELText(XmlUtil.getText(elem), elp);
						}
						if (null != val && val instanceof String){
							val = ((String)val).replaceAll("\\n", "");
						}
					}
					map.put(key, val);
				}else{
					Map<String, String> mp = (Map) XmlUtil.getObjectAttr(elem, "map", elp);
					map.putAll(mp);					
				}
			}
		});
		return map;
	}
}