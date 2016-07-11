package com.tbea.ic.operation.controller.servlet.report;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.reportframe.component.service.Transaction;

@Component
public class TransactionProxy implements Transaction{
	
	@PersistenceContext(unitName = "localDB")
	EntityManager em;
	
	@Transactional
	public void run(Runnable run){
		run.run();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
