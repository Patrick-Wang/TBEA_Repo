package com.tbea.ic.operation.service.report.handlers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.tbea.ic.operation.service.report.handlers.ControllerContextHandler;
import com.tbea.ic.operation.service.report.work.WorkReportService;
import com.tbea.ic.operation.service.report.work.WorkReportServiceImpl;
import com.xml.frame.report.component.entity.Context;

@Component
public class WorkReportHandler extends ControllerContextHandler  {

	@Resource(type = WorkReportServiceImpl.class)
	WorkReportService wrs;
	
	
	@Override
	protected void onHandle(Context context, HttpServletRequest request, HttpServletResponse resp) {
		context.put("wrs", wrs);
	}
}
