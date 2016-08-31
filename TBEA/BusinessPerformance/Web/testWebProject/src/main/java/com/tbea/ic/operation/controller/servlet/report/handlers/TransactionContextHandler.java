package com.tbea.ic.operation.controller.servlet.report.handlers;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tbea.ic.operation.controller.servlet.report.ContextHandler;
import com.tbea.ic.operation.reportframe.component.entity.Context;
import com.tbea.ic.operation.reportframe.component.service.Transaction;
import com.tbea.ic.operation.service.report.TransLocal;

@Component
public class TransactionContextHandler implements ContextHandler {

	@Resource(name = TransLocal.NAME)
	Transaction trLocal;

	@Override
	public void onHandle(Context context) {
		context.put("transactionManager", trLocal);
	}

}
