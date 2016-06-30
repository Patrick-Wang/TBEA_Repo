package com.tbea.ic.operation.controller.servlet.report;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.reportframe.ComponentManager;
import com.tbea.ic.operation.reportframe.Context;

@Controller
@RequestMapping(value = "report")
public class ReportServlet {

	ComponentManager compMgr = new ComponentManager();

	@PersistenceContext(unitName = "localDB")
	EntityManager entityManager;
	
	@RequestMapping(value = "show.do")
	public void ssoLogin(HttpServletRequest request,
			HttpServletResponse response) {
		com.tbea.ic.operation.reportframe.Controller controller = compMgr.getController(request.getParameter("res"));
		if (null != controller){
			Context context = new Context();
			context.put("localDB", entityManager);
			context.put("transactionManager", new com.tbea.ic.operation.reportframe.Transaction(){
				@Override
				public void run(Runnable run) {
					ReportServlet.this.onTransactionManager(run);
				}
			});   
			context.put("date", new EasyCalendar());
			context.put("request", request);
			context.put("response", response);
			controller.run(context);
		}
	}
	
	@Transactional("transactionManager")
	public void onTransactionManager(Runnable run){
		run.run();
	}

}
