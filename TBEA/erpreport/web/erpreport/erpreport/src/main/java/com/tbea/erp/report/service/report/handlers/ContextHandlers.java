package com.tbea.erp.report.service.report.handlers;

import java.util.ArrayList;
import java.util.List;

import com.xml.frame.report.component.entity.Context;

public class ContextHandlers implements ContextHandler{
	
	List<ContextHandler> handlers = new ArrayList<ContextHandler>();
	
	public ContextHandlers add(ContextHandler handler) {
		handlers.add(handler);
		return this;
	}
	
	public void onHandle(Context context){
		for (ContextHandler handler : handlers){
			handler.onHandle(context);
		}
	}
}
