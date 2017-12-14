package com.xml.frame.report.interpreter;

import com.frame.script.el.ELParser;
import com.util.tools.Pair;
import com.util.tools.xml.Loop;
import com.util.tools.xml.XmlWalker;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.component.controller.ControllerRequest;
import com.xml.frame.report.component.entity.WebResponse;
import com.xml.frame.report.util.xml.XmlElWalker;
import com.xml.frame.report.util.xml.XmlUtil;
import org.apache.poi.util.IOUtils;
import org.w3c.dom.Element;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;


public class HttpXmlInterpreter implements XmlInterpreter {

	ELParser elp;

	@Override
	public boolean accept(AbstractXmlComponent component, Element e)
			throws Exception {
		if (!Schema.isHttp(e)) {
			return false;
		}
		elp = new ELParser(component);
		String type = "GET";
		WebResponse wr = null;
		if (e.hasAttribute("type")) {
			type = e.getAttribute("type");
		}

		if ("redirect".equals(type)){
			wr = redirectSend(type, e);
		}else{
			wr = directSend(type, e,
					new ArrayList<Pair<String, String>>(),
					new ArrayList<Pair<String, String>>(),
					new ByteArrayOutputStream());
		}

		component.put(e, wr);

		return true;
	}

	private WebResponse redirectSend(String type, Element e) throws Exception {
		ControllerRequest creq = (ControllerRequest) XmlUtil.parseELText(e.getAttribute("request"), elp);
		HttpServletRequest req = creq.getRequest();
		Enumeration<String> pns = req.getParameterNames();
		List<Pair<String, String>> ps = new ArrayList<Pair<String, String>>();
		while (pns.hasMoreElements()){
			String name = pns.nextElement();
			ps.add(new Pair<>(name, req.getParameter(name)));
		}

		List<Pair<String, String>> hs = new ArrayList<Pair<String, String>>();
		Enumeration<String> hns = req.getHeaderNames();
		while (hns.hasMoreElements()){
			String name = hns.nextElement();
			hs.add(new Pair<>(name, req.getHeader(name)));
		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		IOUtils.copy(req.getInputStream(), os);

		WebResponse wr = directSend(req.getMethod(), e, hs, ps, os);
		if (e.hasAttribute("response")){
		    Set<String> noHeaders = new HashSet<String>();
		    XmlElWalker.eachChildren(e, elp, new Loop(){

                @Override
                public void on(Element elem) throws Exception {
                    if (elem.getTagName().equals("noHeader")){
                        noHeaders.add(XmlUtil.getString(XmlUtil.getText(elem), elp).toLowerCase());
                    }
                }
            });
			HttpServletResponse resp = (HttpServletResponse) XmlUtil.parseELText(e.getAttribute("response"), elp);
			for (Map.Entry<String, List<String>> entry : wr.getHeaders().entrySet()){
				if (null == entry.getKey() || !noHeaders.contains(entry.getKey().toLowerCase())){
                    resp.setHeader(entry.getKey(), entry.getValue().get(0));
                }
			}

			resp.setStatus(wr.getCode());
			byte[] ret = wr.getResult();
			if (null != ret && ret.length > 0){
				resp.getOutputStream().write(ret);
				resp.getOutputStream().flush();
				resp.getOutputStream().close();
			}else{
				resp.getWriter().write(wr.getMsg());
			}
		}
		return wr;
	}

	private WebResponse directSend(String type, Element e,
								   List<Pair<String, String>> hs,
								   List<Pair<String, String>> ps,
								   ByteArrayOutputStream os) throws Exception {
		String url = e.getAttribute("url");
		Element headers = XmlWalker.child(e, "headers");
		if (null != headers){
			XmlElWalker.eachChildren(headers, elp, new Loop(){
				@Override
				public void on(Element elem) throws Exception {
					hs.add(new Pair<String, String>(elem.getTagName(),
							XmlUtil.getString(XmlUtil.getText(elem), elp)));
				}
			});
		}
		Element params = XmlWalker.child(e, "params");
		if (null != params){
			XmlElWalker.eachChildren(params, elp, new Loop(){
				@Override
				public void on(Element elem) throws Exception {
					ps.add(new Pair<String, String>(elem.getTagName(),
							XmlUtil.getString(XmlUtil.getText(elem), elp)));
				}
			});
		}

		Element body = XmlWalker.child(e, "body");
		if (null != body){
			XmlElWalker.eachChildren(body, elp, new Loop(){
				@Override
				public void on(Element elem) throws Exception {
					if ("bytes".equals(elem.getAttribute("type"))){

					} else if ("stream".equals(elem.getAttribute("type"))){

					} else {
						String encode = elem.getAttribute("encode");
						if (encode == null || encode.isEmpty()){
							encode = "utf-8";
						}
						String val = XmlUtil.getString(XmlUtil.getText(elem), elp);
						os.write(val.getBytes(encode));
					}
				}
			});
		}

		WebResponse resp = sendHttp(url, type, hs, ps, os);
		return resp;
	}

	private WebResponse sendHttp(String addr, String type, List<Pair<String, String>> hs, List<Pair<String, String>> ps, ByteArrayOutputStream baos) {
		OutputStream os = null;
		HttpURLConnection httpConnection = null;
		try {
			String q = "";
			for (Pair<String, String> p : ps){
				q += "&" + p.getFirst() + "=" + URLEncoder.encode(p.getSecond(), "utf-8");
			}

			if (addr.indexOf("?") > 0){
				addr += q;
			}else{
				addr += "?" + q.substring(1);
			}

			URL url = new URL(addr);
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod(type);
			httpConnection.setDoOutput(true);
			httpConnection.setDoInput(true);
			httpConnection.setReadTimeout(20000);
			httpConnection.setConnectTimeout(20000);
			httpConnection.setUseCaches(false);

			for (Pair<String, String> h : hs){
				httpConnection.setRequestProperty(h.getFirst(), h.getSecond());
			}
	//				httpConnection.setRequestProperty("Content-Length", "" + msgBody.length);
//				httpConnection.setRequestProperty("Connection", "Keep-Alive");
//				httpConnection.setRequestProperty("Host", host);
//			httpConnection.setRequestProperty("User-Agent", "JSoap Channel");

//			if (this.basicAuth != null) {
//				httpConnection.setRequestProperty("Authorization", this.basicAuth);
//			}

//				Map req = httpConnection.getRequestProperties();
			httpConnection.connect();
			byte[] out = baos.toByteArray();
			if (out != null && out.length > 0){
				os = httpConnection.getOutputStream();
				os.write(out);
				os.flush();
			}

			WebResponse wr = new WebResponse();
			wr.setCode(httpConnection.getResponseCode());
			wr.setMsg(httpConnection.getResponseMessage());

			if (2 == httpConnection.getResponseCode() / 100) {
				ByteArrayOutputStream oResult = new ByteArrayOutputStream();
				IOUtils.copy(httpConnection.getInputStream(), oResult);
				wr.setResult(oResult);
			} else {
				ByteArrayOutputStream oError = new ByteArrayOutputStream();
				IOUtils.copy(httpConnection.getErrorStream(), oError);
				wr.setError(oError);
			}
			wr.setHeaders(httpConnection.getHeaderFields());
			return wr;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (httpConnection != null) {
					httpConnection.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}