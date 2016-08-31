package com.tbea.ic.operation.service.report;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.reportframe.component.service.ServiceRunnable;
import com.tbea.ic.operation.reportframe.component.service.Transaction;

@Service(TransHb.NAME)
public class TransHb implements Transaction{
	
	public static final String NAME = "TransHb";
	@PersistenceContext(unitName = "dbHb")
	EntityManager em;
	
	@Transactional("transHb")
	public void run(ServiceRunnable run) throws Exception{
		run.run();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
