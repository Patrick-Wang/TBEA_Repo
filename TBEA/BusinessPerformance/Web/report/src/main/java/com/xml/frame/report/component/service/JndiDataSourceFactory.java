package com.xml.frame.report.component.service;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class JndiDataSourceFactory {
	public static DataSource getDataSource(String dsName) {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext(); 
		JndiObjectFactoryBean jofb = (JndiObjectFactoryBean) webApplicationContext.getBean(dsName);
		return (DataSource) jofb.getObject();
	}
}
