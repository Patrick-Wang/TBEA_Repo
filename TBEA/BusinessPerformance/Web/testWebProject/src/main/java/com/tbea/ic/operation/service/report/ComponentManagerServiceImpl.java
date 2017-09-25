package com.tbea.ic.operation.service.report;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tbea.ic.operation.controller.servlet.report.ContextHandler;
import com.tbea.ic.operation.service.report.handlers.AuthContextHandler;
import com.tbea.ic.operation.service.report.handlers.ContextHandlers;
import com.tbea.ic.operation.service.report.handlers.DataNodeContextHandler;
import com.tbea.ic.operation.service.report.handlers.OrgsContextHandlers;
import com.tbea.ic.operation.service.report.handlers.QualityHandler;
import com.tbea.ic.operation.service.report.handlers.RequestContextHandler;
import com.tbea.ic.operation.service.report.handlers.TransactionContextHandler;
import com.tbea.ic.operation.service.report.handlers.UtilContextHandler;
import com.xml.frame.report.ReportLogger;
import com.xml.frame.report.component.ComponentManager;
import com.xml.frame.report.component.controller.Controller;
import com.xml.frame.report.component.controller.Scheduler;
import com.xml.frame.report.component.datasource.HikariCPDataSourceFactory;
import com.xml.frame.report.component.entity.Context;
import com.xml.frame.report.component.service.Service;
import com.xml.frame.report.util.DataNode;
@org.springframework.stereotype.Service
public class ComponentManagerServiceImpl implements ComponentManagerService,  Scheduler{

	protected static String resPath;
	protected static String dsConfigPath;
	
	static {
		try {
			resPath = new URI(ComponentManagerServiceImpl.class.getClassLoader().getResource("")
					.getPath()).getPath().substring(1)
					+ "META-INF/components/";
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
//		try {
//			dsConfigPath = new URI(ComponentManagerServiceImpl.class.getClassLoader().getResource("")
//					.getPath()).getPath().substring(1)
//					+ "META-INF/datasource.xml";
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		}
//		
//		HikariCPDataSourceFactory.start(dsConfigPath);
	}
	
	ComponentManager compMgr = new ComponentManager(this, resPath);
	
	@Resource(type = TransactionContextHandler.class)
	ContextHandler tranContext;
	
	@Resource(type = UtilContextHandler.class)
	ContextHandler utilContext;
	
	@Resource(type = OrgsContextHandlers.class)
	ContextHandler orgsContext;
	
	@Resource(type = AuthContextHandler.class)
	ContextHandler authContext;	
	
	@Resource(type = QualityHandler.class)
	ContextHandler qualityContext;

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
		return doController(request, response, controllor, new Context());
	}
	
	@Override
	public Context doController(HttpServletRequest request, HttpServletResponse response, String controllor, Context context) throws Exception {
		Controller controller = compMgr.createController(null, controllor);
		if (null != controller){
			ContextHandlers handlers = new ContextHandlers();			
			handlers.add(new RequestContextHandler(request, response))
					.add(tranContext)
					.add(utilContext)
					.add(orgsContext)
					.add(authContext)
					.add(qualityContext)
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
		Service service = compMgr.createService(null, serviceId);
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
		Service service = compMgr.createService(null, serviceId);
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
