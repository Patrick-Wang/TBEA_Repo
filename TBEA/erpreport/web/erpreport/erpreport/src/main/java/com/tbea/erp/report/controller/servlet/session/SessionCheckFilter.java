package com.tbea.erp.report.controller.servlet.session;

import com.speed.frame.common.AjaxRedirect;
import com.tbea.erp.report.model.entity.UserRequestEntity;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

public class SessionCheckFilter implements Filter {

	Pattern patterns[] = new Pattern[]{
			Pattern.compile("/Login/portal.do"),
			Pattern.compile("/Login/erp.do"),
			Pattern.compile("/Login/timeout.do"),
			Pattern.compile("/Login/token.do"),
			Pattern.compile("/Login/urltime.do")
	};
	
	
	static final String DEFAULT_URL = "Login/timeout.do";
	
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
	
	private boolean passCheck(String url){
		for (Pattern pat : patterns){
			if (pat.matcher(url).find()){
				return true;
			}
		}
		return false;
	}
	
	UserRequestEntity createUserRequest(HttpServletRequest httpRequest){
		HttpSession session = httpRequest.getSession(false);
		if (session.getAttribute(SessionKey.IP) == null){
			session.setAttribute(SessionKey.IP, getRemoteIP(httpRequest));
		}
		if (session.getAttribute(SessionKey.REQUESTS) == null){
			session.setAttribute(SessionKey.REQUESTS, new ArrayList<UserRequestEntity>());
		}
		@SuppressWarnings("unchecked")
		List<UserRequestEntity> reqs = (List<UserRequestEntity>) session.getAttribute(SessionKey.REQUESTS);
		UserRequestEntity ure = new UserRequestEntity();
		ure.setUrl(httpRequest.getRequestURI());
		ure.setRequestTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		ure.setIsAjax(isAjaxRequest(httpRequest) ? 1: 0);
		reqs.add(ure);
		return ure;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResp = (HttpServletResponse) resp;
			String url = httpRequest.getRequestURI();
			if (!passCheck(url)) {
				if (SessionManager.isOnline(httpRequest)) {
					UserRequestEntity ure = createUserRequest(httpRequest);
					
					chain.doFilter(request, resp);
					
					ure.setResponseTime(new Timestamp(Calendar.getInstance().getTimeInMillis()));
				}else{
					String redirUrl = "/" + Url.parse(url).segment(0) + "/" + DEFAULT_URL;
					redirect(httpRequest, httpResp, redirUrl);
				}
			} else {
				chain.doFilter(request, resp);
			}
		}else {
			chain.doFilter(request, resp);
		}		
	}

	private void redirect(HttpServletRequest httpRequest, HttpServletResponse httpResp, String redirUrl) throws IOException {
		if (isAjaxRequest(httpRequest)) {
			PrintWriter pw = httpResp.getWriter();
			pw.print(JSONObject.fromObject(new AjaxRedirect(redirUrl)));
			pw.close();
		} else {
			httpResp.sendRedirect(redirUrl);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
