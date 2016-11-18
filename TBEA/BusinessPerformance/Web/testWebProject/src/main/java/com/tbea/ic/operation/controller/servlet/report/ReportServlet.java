package com.tbea.ic.operation.controller.servlet.report;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.DataNode;
import com.tbea.ic.operation.controller.servlet.report.handlers.AuthContextHandler;
import com.tbea.ic.operation.controller.servlet.report.handlers.ContextHandlers;
import com.tbea.ic.operation.controller.servlet.report.handlers.DataNodeContextHandler;
import com.tbea.ic.operation.controller.servlet.report.handlers.OrgsContextHandlers;
import com.tbea.ic.operation.controller.servlet.report.handlers.QualityHandler;
import com.tbea.ic.operation.controller.servlet.report.handlers.RequestContextHandler;
import com.tbea.ic.operation.controller.servlet.report.handlers.TransactionContextHandler;
import com.tbea.ic.operation.controller.servlet.report.handlers.UtilContextHandler;
import com.tbea.ic.operation.reportframe.ReportLogger;
import com.tbea.ic.operation.reportframe.component.ComponentManager;
import com.tbea.ic.operation.reportframe.component.controller.Scheduler;
import com.tbea.ic.operation.reportframe.component.entity.Context;

@Controller
@RequestMapping(value = "report")
public class ReportServlet implements Scheduler {

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
	
	
	@RequestMapping(value = "console/show.do")
	public ModelAndView consoleShow(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DataNode tree = compMgr.getCSB();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("componentTree", JSONObject.fromObject(tree).toString());
		return new ModelAndView("report/report_console", model);
	}
	
	@RequestMapping(value = "{controller}.do")
	public ModelAndView onController(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("controller") String controllor) throws Exception {
		
		com.tbea.ic.operation.reportframe.component.controller.Controller controller = compMgr.createController(null, controllor);
		if (null != controller){
			ReportLogger.trace().debug("begin ==================================");
			Context context = new Context();
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
			ReportLogger.trace().debug("end +++++++++++++++++++++++++++++++++++++++ ==================================");
			return (ModelAndView) context.get(com.tbea.ic.operation.reportframe.component.controller.Controller.MODEL_AND_VIEW);
		}
		return null;
	}

	@Override
	public void onSchedule(
			Context context,
			com.tbea.ic.operation.reportframe.component.controller.Controller controller) throws Exception {
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
}
