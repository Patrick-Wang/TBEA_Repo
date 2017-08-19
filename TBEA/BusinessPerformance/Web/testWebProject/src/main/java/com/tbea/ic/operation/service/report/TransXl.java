package com.tbea.ic.operation.service.report;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xml.frame.report.component.service.ServiceRunnable;
import com.xml.frame.report.component.service.Transaction;

@Service(TransXl.NAME)
public class TransXl implements Transaction{
	
	public static final String NAME = "TransXl";
	@PersistenceContext(unitName = "XlDB")
	EntityManager em; 
	
	@Transactional("transXl")
	public void run(ServiceRunnable run) throws Exception{
		run.run();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
