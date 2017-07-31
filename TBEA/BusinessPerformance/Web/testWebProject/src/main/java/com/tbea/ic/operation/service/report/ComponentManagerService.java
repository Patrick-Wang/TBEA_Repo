package com.tbea.ic.operation.service.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tbea.ic.operation.common.DataNode;
import com.tbea.ic.operation.reportframe.component.entity.Context;

public interface ComponentManagerService {

	Context doController(HttpServletRequest request, HttpServletResponse response, String controllor) throws Exception;

	Context doService(String service) throws Exception;

	Context doService(Context context, String service) throws Exception;

	Context doService(HttpServletRequest request, HttpServletResponse response, String service) throws Exception;

	DataNode getCSN();

}
