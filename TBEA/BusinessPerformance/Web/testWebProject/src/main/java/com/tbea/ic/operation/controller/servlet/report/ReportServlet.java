package com.tbea.ic.operation.controller.servlet.report;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tbea.ic.operation.reportframe.ComponentManager;
import com.tbea.ic.operation.reportframe.Context;
import com.tbea.ic.operation.reportframe.ControllerRequest;
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
	public void ssoLogin(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable("controllor") String controllor) {
		com.tbea.ic.operation.reportframe.Controller controller = compMgr.getController(controllor);
		if (null != controller){
			Context context = new Context();
			context.put("request", new ControllerRequest(request));
			context.put("response", response);

			context.put("localDB", entityManager);
			context.put("transactionManager", new com.tbea.ic.operation.reportframe.Transaction(){
				@Override
				public void run(Runnable runnable) {
					trProxy.invokeTransactionManager(runnable);
				}
			});
			
			controller.run(context);
		}
	}
}
