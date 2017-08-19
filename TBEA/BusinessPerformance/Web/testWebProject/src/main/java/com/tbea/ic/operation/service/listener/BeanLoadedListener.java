package com.tbea.ic.operation.service.listener;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.tbea.ic.operation.model.dao.dbversion.DBVersionDao;
import com.tbea.ic.operation.model.entity.DBVersion;

@Component("BeanLoadedListener")
public class BeanLoadedListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	DBVersionDao dbVersionDao;
	
	@Autowired
	private ServletContext servletContext;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")) {
			DBVersion lastVersion = dbVersionDao.getLatestVersion();
			servletContext.setAttribute("ver", lastVersion.getVersion());
		}
	}

}
