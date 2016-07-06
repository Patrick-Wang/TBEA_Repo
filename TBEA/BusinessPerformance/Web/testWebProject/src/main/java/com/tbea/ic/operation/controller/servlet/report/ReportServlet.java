package com.tbea.ic.operation.controller.servlet.report;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.reportframe.component.ComponentManager;
import com.tbea.ic.operation.reportframe.component.controller.ControllerRequest;
import com.tbea.ic.operation.reportframe.component.entity.Context;
import com.tbea.ic.operation.service.report.TransactionProxy;

@Controller
@RequestMapping(value = "report")
public class ReportServlet {

	ComponentManager compMgr = new ComponentManager();

	@PersistenceContext(unitName = "localDB")
	EntityManager entityManager;
	
	@Autowired
	TransactionProxy trProxy;
	
	@RequestMapping(value = "{controllor}.do")
	public ModelAndView ssoLogin(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("controllor") String controllor) {
		com.tbea.ic.operation.reportframe.component.controller.Controller controller = compMgr.getController(controllor);
		if (null != controller){
			Context context = new Context();
			context.put("request", new ControllerRequest(request));
			context.put("response", response);
			context.put("time", new EasyCalendar());
			context.put("localDB", entityManager);
			context.put("modelAndView", entityManager);
			context.put("transactionManager", new com.tbea.ic.operation.reportframe.component.service.Transaction(){
				@Override
				public void run(Runnable runnable) {
					trProxy.invokeTransactionManager(runnable);
				}
			});
			
			controller.run(context);
			return (ModelAndView) context.get(com.tbea.ic.operation.reportframe.component.controller.Controller.MODEL_AND_VIEW);
		}
		return null;
	}
}
