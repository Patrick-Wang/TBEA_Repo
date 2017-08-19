package com.tbea.ic.operation.service.report;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xml.frame.report.component.service.ServiceRunnable;
import com.xml.frame.report.component.service.Transaction;

@Service(TransNC.NAME)
public class TransNC implements Transaction{
	
	public static final String NAME = "TransNc";
	@PersistenceContext(unitName = "NcDB")
	EntityManager em;
	
	@Transactional("transNc")
	public void run(ServiceRunnable run) throws Exception{
		run.run();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
