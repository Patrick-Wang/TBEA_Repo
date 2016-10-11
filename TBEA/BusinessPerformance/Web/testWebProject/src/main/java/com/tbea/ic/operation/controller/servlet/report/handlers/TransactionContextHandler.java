package com.tbea.ic.operation.controller.servlet.report.handlers;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tbea.ic.operation.controller.servlet.report.ContextHandler;
import com.tbea.ic.operation.reportframe.component.entity.Context;
import com.tbea.ic.operation.reportframe.component.service.Transaction;
import com.tbea.ic.operation.service.report.TransDl;
import com.tbea.ic.operation.service.report.TransLl;
import com.tbea.ic.operation.service.report.TransLocal;
import com.tbea.ic.operation.service.report.TransSb;
import com.tbea.ic.operation.service.report.TransTb;
import com.tbea.ic.operation.service.report.TransXb;
import com.tbea.ic.operation.service.report.TransXl;


@Component
public class TransactionContextHandler implements ContextHandler {

	@Resource(name = TransLocal.NAME)
	Transaction trLocal;
	
	@Resource(name = TransDl.NAME)
	Transaction trDl;
	
	@Resource(name = TransXl.NAME)
	Transaction trXl;
	
	@Resource(name = TransLl.NAME)
	Transaction trLl;
	
	@Resource(name = TransSb.NAME)
	Transaction trSb;

	@Resource(name = TransXb.NAME)
	Transaction trXb;
	
	@Resource(name = TransTb.NAME)
	Transaction trTb;

	@Override
	public void onHandle(Context context) {
		context.put("transactionManager", trLocal);
		context.put("transDl", trDl);
		context.put("transXl", trXl);
		context.put("transLl", trLl);
		context.put("transSb", trSb);
		context.put("transXb", trXb);
		context.put("transTb", trTb);
	}

}
