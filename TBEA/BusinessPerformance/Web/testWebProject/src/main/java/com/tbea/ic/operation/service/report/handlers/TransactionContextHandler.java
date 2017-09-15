package com.tbea.ic.operation.service.report.handlers;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tbea.ic.operation.controller.servlet.report.ContextHandler;
import com.tbea.ic.operation.service.report.Trans15;
import com.tbea.ic.operation.service.report.TransDl;
import com.tbea.ic.operation.service.report.TransLl;
import com.tbea.ic.operation.service.report.TransLocal;
import com.tbea.ic.operation.service.report.TransNC;
import com.tbea.ic.operation.service.report.TransSb;
import com.tbea.ic.operation.service.report.TransTb;
import com.tbea.ic.operation.service.report.TransXb;
import com.tbea.ic.operation.service.report.TransXl;
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
