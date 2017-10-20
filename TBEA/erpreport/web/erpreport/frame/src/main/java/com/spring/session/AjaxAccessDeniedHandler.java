package com.spring.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import com.speed.frame.common.AjaxRedirect;
import com.speed.frame.common.ControllerTools;

import net.sf.json.JSONObject;

public class AjaxAccessDeniedHandler extends AccessDeniedHandlerImpl {


	protected String redirectPage;
	
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)  
            throws IOException, ServletException {  
        if(ControllerTools.isAjaxRequest(request)){
        	AjaxRedirect ar = new AjaxRedirect(accessDeniedException.getMessage(), this.redirectPage);
        		PrintWriter pw = response.getWriter();
			pw.print(JSONObject.fromObject(ar));
			pw.close();
        }else{  
            super.handle(request, response, accessDeniedException);
        }  
    }  
  
	public void setErrorPage(String errorPage) {
		redirectPage = errorPage;
		super.setErrorPage(errorPage);
	}

}
