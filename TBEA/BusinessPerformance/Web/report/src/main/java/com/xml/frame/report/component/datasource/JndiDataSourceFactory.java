package com.xml.frame.report.component.datasource;

import javax.sql.DataSource;

import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class JndiDataSourceFactory implements DataSourceFactory{
	static JndiDataSourceFactory ins = new JndiDataSourceFactory();
	
	private JndiDataSourceFactory() {
		
	}
	
	public DataSource getDataSource(String dsName) {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext(); 
		JndiObjectFactoryBean jofb = (JndiObjectFactoryBean) webApplicationContext.getBean(dsName);
		return (DataSource) jofb.getObject();
	}
	
	public static DataSourceFactory getInstance() {
		return ins;
	}
}
