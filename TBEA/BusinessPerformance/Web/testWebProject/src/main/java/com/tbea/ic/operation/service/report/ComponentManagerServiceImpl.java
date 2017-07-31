package com.tbea.ic.operation.service.report;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.tbea.ic.operation.common.DataNode;
import com.tbea.ic.operation.controller.servlet.report.ContextHandler;
import com.tbea.ic.operation.reportframe.ReportLogger;
import com.tbea.ic.operation.reportframe.component.ComponentManager;
import com.tbea.ic.operation.reportframe.component.controller.Controller;
import com.tbea.ic.operation.reportframe.component.controller.Scheduler;
import com.tbea.ic.operation.reportframe.component.entity.Context;
import com.tbea.ic.operation.service.report.handlers.AuthContextHandler;
import com.tbea.ic.operation.service.report.handlers.ContextHandlers;
import com.tbea.ic.operation.service.report.handlers.DataNodeContextHandler;
import com.tbea.ic.operation.service.report.handlers.OrgsContextHandlers;
import com.tbea.ic.operation.service.report.handlers.QualityHandler;
import com.tbea.ic.operation.service.report.handlers.RequestContextHandler;
import com.tbea.ic.operation.service.report.handlers.TransactionContextHandler;
import com.tbea.ic.operation.service.report.handlers.UtilContextHandler;
@Service
public class ComponentManagerServiceImpl implements ComponentManagerService,  Scheduler{

	ComponentManager compMgr = new ComponentManager(this);
	
	@Resource(type = TransactionContextHandler.class)
	ContextHandler tranContext;
	
	@Resource(type = UtilContextHandler.class)
	ContextHandler utilContext;
	
	@Resource(type = OrgsContextHandlers.class)
	ContextHandler orgsContext;
	
	@Resource(type = AuthContextHandler.class)
	AuthContextHandler authContext;	
	
	@Resource(type = QualityHandler.class)
	QualityHandler qualityContext;

	@Override
	public void onSchedule(Context context, Controller controller) throws Exception {
		ReportLogger.trace().info(" on schedule " + controller.getId());
		ContextHandlers handlers = new ContextHandlers();
		handlers.add(tranContext)
				.add(utilContext)
				.add(orgsContext)
				.add(new DataNodeContextHandler());
		context.put("isSchedule", true);
		handlers.onHandle(context);
		controller.run(context);
	}



	@Override
	public DataNode getCSN() {
		return compMgr.getCSN();
	}



	@Override
	public Context doController(HttpServletRequest request, HttpServletResponse response, String controllor) throws Exception {
		Controller controller = compMgr.createController(null, controllor);
		Context context = new Context();
		if (null != controller){
			ContextHandlers handlers = new ContextHandlers();			
			handlers.add(new RequestContextHandler(request, response))
					.add(tranContext)
					.add(utilContext)
					.add(orgsContext)
					.add(new DataNodeContextHandler());
			context.put("isSchedule", false);
			handlers.onHandle(context);
			controller.run(context);
		}
		return context;
	}

	@Override
	public Context doService(String service) throws Exception {
		return doService(new Context(), service);
	}

	@Override
	public Context doService(Context context, String serviceId) throws Exception {
		com.tbea.ic.operation.reportframe.component.service.Service service = compMgr.createService(null, serviceId);
		if (null != service){
			ContextHandlers handlers = new ContextHandlers();			
			handlers.add(tranContext)
					.add(utilContext)
					.add(orgsContext)
					.add(new DataNodeContextHandler());
			context.put("isSchedule", false);
			handlers.onHandle(context);
			service.run(context);
		}
		return context;
	}



	@Override
	public Context doService(HttpServletRequest request, HttpServletResponse response, String serviceId)
			throws Exception {
		com.tbea.ic.operation.reportframe.component.service.Service service = compMgr.createService(null, serviceId);
		Context context = new Context();
		if (null != service){
			ContextHandlers handlers = new ContextHandlers();			
			handlers.add(new RequestContextHandler(request, response))
					.add(tranContext)
					.add(utilContext)
					.add(orgsContext)
					.add(new DataNodeContextHandler());
			context.put("isSchedule", false);
			handlers.onHandle(context);
			service.run(context);
		}
		return context;
	}

}
