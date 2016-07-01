package com.tbea.ic.operation.controller.servlet.report;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tbea.ic.operation.common.EasyCalendar;
import com.tbea.ic.operation.reportframe.ComponentManager;
import com.tbea.ic.operation.reportframe.Context;
import com.tbea.ic.operation.service.report.TransactionProxy;

@Controller
@RequestMapping(value = "report")
public class ReportServlet {

	ComponentManager compMgr = new ComponentManager();

	@PersistenceContext(unitName = "localDB")
	EntityManager entityManager;
	
	@Autowired
	TransactionProxy trProxy;
	
	@RequestMapping(value = "show.do")
	public void ssoLogin(HttpServletRequest request,
			HttpServletResponse response) {
		com.tbea.ic.operation.reportframe.Controller controller = compMgr.getController(request.getParameter("res"));
		if (null != controller){
			Context context = new Context();
			
			String date = request.getParameter("date");
			EasyCalendar ec = new EasyCalendar();
			if (null != date){
				ec.setTime(java.sql.Date.valueOf(date));
			}
			context.put("date", new EasyCalendar());
			context.put("request", request);
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
