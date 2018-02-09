package com.xml.frame.report.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Enumeration;

public class ContextLoadListener implements HttpSessionListener {

		private boolean isInited = false;
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
	    if (!isInited){
            isInited = true;
            Enumeration<String> e = event.getSession().getServletContext().getInitParameterNames();
            while (e.hasMoreElements()){
                String name = e.nextElement();
                if (name != null && !"".equals(name)){
                    System.setProperty(name,event.getSession().getServletContext().getInitParameter(name));
               }
            }
        }
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {

	}

}
