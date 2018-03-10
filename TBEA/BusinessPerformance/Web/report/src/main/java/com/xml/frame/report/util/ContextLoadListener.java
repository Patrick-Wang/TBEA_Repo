package com.xml.frame.report.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Enumeration;

public class ContextLoadListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        Enumeration<String> e = servletContextEvent.getServletContext().getInitParameterNames();
        while (e.hasMoreElements()){
            String name = e.nextElement();
            if (name != null && !"".equals(name)){
                System.setProperty(name,servletContextEvent.getServletContext().getInitParameter(name));
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
