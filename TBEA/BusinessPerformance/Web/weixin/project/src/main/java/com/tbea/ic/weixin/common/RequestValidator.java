package com.tbea.ic.weixin.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class RequestValidator implements Filter {

	private List<String> uncheckedUrl;
	private String redirSubUrl;
	public void destroy() {
	}

	private boolean isAjaxRequest(HttpServletRequest httpRequest) {
		String requestType = httpRequest.getHeader("X-Requested-With");
		if (requestType != null && requestType.equals("XMLHttpRequest")) {
			return true;
		} else {
			return false;
		}
	}
	
	private String getRootUrl(HttpServletRequest httpRequest){
		String url = httpRequest.getRequestURI();
		String rootUrl = url.substring(1);
		int rootPos = rootUrl.indexOf('/');
		rootUrl = rootUrl.substring(0, rootPos);
		return rootUrl;
	}
	
	private String getRedictUrl(HttpServletRequest httpRequest, String subUrl){
		String rootUrl = getRootUrl(httpRequest);
		String redirUrl = "/" + rootUrl + subUrl;
		return redirUrl;
	}
	
	private boolean isUncheckedUrl(String url){
		for (String subUrl : uncheckedUrl){
			if (url.indexOf(subUrl) >= 0){
				return true;
			}
		}
		return false;
	}
	
	
	public void doFilter(ServletRequest request, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			if (!OnlineService.isOnline(httpRequest)) {
				if (!isUncheckedUrl(httpRequest.getRequestURI())) {
					String redirUrl = getRedictUrl(httpRequest, redirSubUrl);
					HttpServletResponse httpResp = (HttpServletResponse) resp;
					if (isAjaxRequest(httpRequest)) {
						PrintWriter pw = httpResp.getWriter();
						pw.print(JSONObject.fromObject(new AjaxRedirect(redirUrl)));
						pw.close();
					} else {
						httpResp.sendRedirect(redirUrl);
					}
					return;
				}
			}
		}
		chain.doFilter(request, resp);
	}

	public void init(FilterConfig arg0) throws ServletException {
		uncheckedUrl = new ArrayList<String>();
		redirSubUrl = "/account/login.do";
		uncheckedUrl.add("/account/login.do");
		uncheckedUrl.add("/account/validate.do");
		uncheckedUrl.add("/Account/resetPassword.do");
	}

}
