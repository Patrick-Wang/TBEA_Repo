package com.tbea.ic.operation.service.report;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xml.frame.report.component.service.ServiceRunnable;
import com.xml.frame.report.component.service.JpaTransaction;

@Service(TransDl.NAME)
public class TransDl implements JpaTransaction{
	
	public static final String NAME = "TransDl";
	@PersistenceContext(unitName = "DlDB")
	EntityManager em;
	
	@Transactional("transDl")
	public void run(ServiceRunnable run) throws Exception{
		run.run();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
