package com.tbea.ic.operation.service.report;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xml.frame.report.component.service.ServiceRunnable;
import com.xml.frame.report.component.service.Transaction;

@Service(TransXb.NAME)
public class TransXb implements Transaction{
	
	public static final String NAME = "TransXb";
	@PersistenceContext(unitName = "XbDB")
	EntityManager em;
	
	@Transactional("transXb")
	public void run(ServiceRunnable run) throws Exception{
		run.run();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
