package com.spring.session.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.spring.session.SessionRepositoryUtil;

public class SpringSessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, javax.servlet.ServletResponse response,
			FilterChain chain) throws java.io.IOException, javax.servlet.ServletException {
		if (request instanceof HttpServletRequest){
			HttpSession session = ((HttpServletRequest)request).getSession(false);
			
			chain.doFilter(request, response);
			
			if (session == null){
				session = ((HttpServletRequest)request).getSession(false);
				if (session != null){
					if (null == session.getAttribute(SessionRepositoryUtil.SPRING_SESSION_ONLINE_INDEX)){
						session.setAttribute(SessionRepositoryUtil.SPRING_SESSION_ONLINE_INDEX, SessionRepositoryUtil.SPRING_SESSION_ONLINE_NAME);
					}
				}
			}
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
