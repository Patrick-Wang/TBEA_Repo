package com.tbea.ic.operation.controller.servlet.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tbea.ic.operation.common.Url;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SessionCheckFilter implements Filter {

	Pattern patterns[] = new Pattern[]{
			Pattern.compile("/Login/(v2/)?ssoLogin.do"),
			Pattern.compile("/Login/(v2/)?ssoLogout.do"),
			Pattern.compile("/Login/(v2/)?validate.do"),
			Pattern.compile("/Login/(v2/)?login.do"),
			Pattern.compile("/Account/(v2/)?resetPassword.do")
	};
	
	
	static final String DEFAULT_URL = "Login/login.do";
	static final String DEFAULT_URL_V2 = "Login/v2/login.do";
	
	@Override
	public void destroy() {
	}

	public String getRemoteIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	private boolean isAjaxRequest(HttpServletRequest httpRequest){
		String requestType = httpRequest
				.getHeader("X-Requested-With");
		return requestType != null
				&& requestType.equals("XMLHttpRequest");
	}
	
	private boolean isUncheckedUrl(String url){
		for (Pattern pat : patterns){
			if (pat.matcher(url).find()){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		JSONObject oper = null;
		Calendar current = Calendar.getInstance();
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResp = (HttpServletResponse) resp;
			String url = httpRequest.getRequestURI();
			if (!isUncheckedUrl(url)) {
				
				HttpSession session = httpRequest.getSession(false);
				if (!SessionManager.isOnline(session)) {
//					Url uri = Url.parse(httpRequest.getServletPath());
					String redirUrl = "/" + Url.parse(url).segment(0) + "/" + DEFAULT_URL_V2;
//					if (uri.segCount() >= 2 && "v2".equals(uri.segment(1))){
//						redirUrl =  "/" + Url.parse(url).segment(0) + "/" + DEFAULT_URL_V2;
//					}else{
//						redirUrl =  "/" + Url.parse(url).segment(0) + "/" + DEFAULT_URL;
//					}
					
					if (isAjaxRequest(httpRequest)) {
						PrintWriter pw = httpResp.getWriter();
						pw.print(JSONObject.fromObject(new AjaxRedirect(redirUrl)));
						pw.close();
					} else {
						httpResp.sendRedirect(redirUrl);
						return;
					}
				}else{
					if (session.getAttribute("remoteIP") == null){
						session.setAttribute("remoteIP", getRemoteIP((HttpServletRequest) request));
					}
					if (session.getAttribute("reqs") == null){
						session.setAttribute("reqs", new JSONArray());
					}
					JSONArray reqs = (JSONArray) session.getAttribute("reqs");
					oper = new JSONObject();
					oper.put("url", url);
					oper.put("requestTime", current.getTimeInMillis());
					oper.put("isAjax", isAjaxRequest(httpRequest));
					reqs.add(oper);
				}
			}
		}
		chain.doFilter(request, resp);
		if (oper != null){
			Calendar respTime = Calendar.getInstance();
			oper.put("responseTime", respTime.getTimeInMillis());
			oper.put("elapse", respTime.getTimeInMillis() - current.getTimeInMillis());
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
