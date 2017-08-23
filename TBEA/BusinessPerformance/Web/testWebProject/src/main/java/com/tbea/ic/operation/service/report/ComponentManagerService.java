package com.tbea.ic.operation.service.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xml.frame.report.component.entity.Context;
import com.xml.frame.report.util.DataNode;

public interface ComponentManagerService {

	Context doController(HttpServletRequest request, HttpServletResponse response, String controllor) throws Exception;

	Context doService(String service) throws Exception;

	Context doService(Context context, String service) throws Exception;

	Context doService(HttpServletRequest request, HttpServletResponse response, String service) throws Exception;

	DataNode getCSN();

	Context doController(HttpServletRequest request, HttpServletResponse response, String controllor, Context context)
			throws Exception;

}

