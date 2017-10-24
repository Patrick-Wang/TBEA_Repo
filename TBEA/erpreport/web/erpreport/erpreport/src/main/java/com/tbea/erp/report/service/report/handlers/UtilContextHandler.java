package com.tbea.erp.report.service.report.handlers;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.frame.script.maker.CodeMaker;
import com.frame.script.util.PropMap;
import com.xml.frame.report.component.Component;
import com.xml.frame.report.component.entity.Context;
import com.xml.frame.report.util.EasyCalendar;


public class UtilContextHandler implements ContextHandler {

	@Override
	public void onHandle(Context context) {
		context.put(Component.CALENDAR, new EasyCalendar());
		context.put("counterFactory", new Counter());
	}


}