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

import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.jygk.Account;

public class SessionCheckFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResp = (HttpServletResponse) resp;
			String url = httpRequest.getRequestURI();
			if (url.indexOf("/Login/validate.do") < 0
					&& url.indexOf("/Login/login.do") < 0
					&& url.indexOf("/Account/resetPassword.do") < 0) {
				HttpSession session = httpRequest.getSession(false);
//				boolean bValid = (null != session);
//				if (bValid) {
//					try {
//						Account account = (Account) session.getAttribute("account");
//						bValid = account.getId() != 0;
//					} catch (Exception e) {
//						bValid = false;
//					}
//				}
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
						pw.print("{\"error\" : \"invalidate session\", \"redirect\" : \"" + redirUrl + "\"}");
						pw.close();

					} else {
						httpResp.sendRedirect(redirUrl);
						return;
					}
				}
			}
		}
		chain.doFilter(request, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
