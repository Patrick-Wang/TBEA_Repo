package com.tbea.ic.operation.controller.servlet.report.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.controller.servlet.report.ContextHandler;
import com.tbea.ic.operation.reportframe.component.Component;
import com.tbea.ic.operation.reportframe.component.controller.ControllerRequest;
import com.tbea.ic.operation.reportframe.component.controller.ControllerSession;
import com.tbea.ic.operation.reportframe.component.entity.Context;

public class RequestContextHandler implements ContextHandler {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	public RequestContextHandler(HttpServletRequest request,
			HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}

	@Override
	public void onHandle(Context context) {
		context.put(Component.REQUEST, new ControllerRequest(request));
		context.put(Component.SESSION, new ControllerSession(request.getSession()));
		context.put(Component.RESPONSE, response);
		context.put(Component.CALENDAR, new EasyCalendar());
	}

}
