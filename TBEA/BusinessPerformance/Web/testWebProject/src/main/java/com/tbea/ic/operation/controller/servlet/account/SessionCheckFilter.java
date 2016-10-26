package com.tbea.ic.operation.controller.servlet.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	@Override
	public void doFilter(ServletRequest request, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResp = (HttpServletResponse) resp;
			String url = httpRequest.getRequestURI();
			
			if (url.indexOf("/Login/ssoLogin.do")  < 0 
					&& url.indexOf("/Login/ssoLogout.do")  < 0 
					&& url.indexOf("/Login/validate.do") < 0
					&& url.indexOf("/Login/login.do") < 0
					&& url.indexOf("/Account/resetPassword.do") < 0) {
				HttpSession session = httpRequest.getSession(false);
				if (!SessionManager.isOnline(session)) {
					String requestType = httpRequest
							.getHeader("X-Requested-With");
					String rootUrl = url.substring(1);
					int rootPos = rootUrl.indexOf('/');
					rootUrl = rootUrl.substring(0, rootPos);
					String redirUrl = "/" + rootUrl + "/Login/login.do";
					if (requestType != null
							&& requestType.equals("XMLHttpRequest")) {
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
				}
			}
		}
		chain.doFilter(request, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
