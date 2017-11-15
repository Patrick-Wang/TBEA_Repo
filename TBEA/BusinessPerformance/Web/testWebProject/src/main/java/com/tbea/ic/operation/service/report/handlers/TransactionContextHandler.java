package com.tbea.ic.operation.service.report.handlers;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tbea.ic.operation.controller.servlet.report.ContextHandler;
import com.tbea.ic.operation.service.report.trans.Trans15;
import com.tbea.ic.operation.service.report.trans.TransDl;
import com.tbea.ic.operation.service.report.trans.TransLl;
import com.tbea.ic.operation.service.report.trans.TransLocal;
import com.tbea.ic.operation.service.report.trans.TransNC;
import com.tbea.ic.operation.service.report.trans.TransSb;
import com.tbea.ic.operation.service.report.trans.TransTb;
import com.tbea.ic.operation.service.report.trans.TransXb;
import com.tbea.ic.operation.service.report.trans.TransXl;
import com.xml.frame.report.component.entity.Context;
import com.xml.frame.report.component.service.JpaTransaction;


@Component
public class TransactionContextHandler implements ContextHandler {

	@Resource(name = TransLocal.NAME)
	JpaTransaction trLocal;
	
	@Resource(name = TransDl.NAME)
	JpaTransaction trDl;
	
	@Resource(name = TransXl.NAME)
	JpaTransaction trXl;
	
	@Resource(name = TransLl.NAME)
	JpaTransaction trLl;
	
	@Resource(name = TransSb.NAME)
	JpaTransaction trSb;

	@Resource(name = TransXb.NAME)
	JpaTransaction trXb;
	
	@Resource(name = TransTb.NAME)
	JpaTransaction trTb;
	
	@Resource(name = TransNC.NAME)
	JpaTransaction trNc;
	
	@Resource(name = Trans15.NAME)
	JpaTransaction tr15;

	@Override
	public void onHandle(Context context) {
		context.put("transactionManager", trLocal);
		context.put("transDl", trDl);
		context.put("transXl", trXl);
		context.put("transLl", trLl);
		context.put("transSb", trSb);
		context.put("transXb", trXb);
		context.put("transTb", trTb);
		context.put("transNc", trNc);
		context.put("trans15", tr15);
		
	}

}
