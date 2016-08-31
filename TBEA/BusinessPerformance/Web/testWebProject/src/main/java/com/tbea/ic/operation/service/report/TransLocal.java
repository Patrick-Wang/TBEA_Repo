package com.tbea.ic.operation.service.report;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.reportframe.component.service.ServiceRunnable;
import com.tbea.ic.operation.reportframe.component.service.Transaction;

@Service(TransLocal.NAME)
public class TransLocal implements Transaction{
	
	public static final String NAME = "TransLocal";
	@PersistenceContext(unitName = "localDB")
	EntityManager em;
	
	@Transactional("transactionManager")
	public void run(ServiceRunnable run) throws Exception{
		run.run();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
