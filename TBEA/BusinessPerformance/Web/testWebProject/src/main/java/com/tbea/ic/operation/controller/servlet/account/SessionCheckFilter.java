package com.tbea.ic.operation.controller.servlet.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;

public class SessionCheckFilter implements Filter {

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
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		JSONObject oper = null;
		Calendar current = Calendar.getInstance();
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResp = (HttpServletResponse) resp;
			String url = httpRequest.getRequestURI();
			
			if (url.indexOf("/Login/ssoLogin.do")  < 0 
					&& url.indexOf("/Login/ssoLogout.do")  < 0 
					&& url.indexOf("/Login/validate.do") < 0
					&& url.indexOf("/Login/login.do") < 0
					&& url.indexOf("/Login/v2/validate.do") < 0
					&& url.indexOf("/Login/v2/login.do") < 0
					&& url.indexOf("/Account/resetPassword.do") < 0) {
				HttpSession session = httpRequest.getSession(false);
				if (!SessionManager.isOnline(session)) {
					String rootUrl = url.substring(1);
					int rootPos = rootUrl.indexOf('/');
					rootUrl = rootUrl.substring(0, rootPos);
					String redirUrl = "/" + rootUrl + "/Login/login.do";
					if (url.contains("v2")){
						redirUrl = "/" + rootUrl + "/Login/v2/login.do";
					}
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
