package com.tbea.ic.operation.service.report.trans;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xml.frame.report.component.service.ServiceRunnable;
import com.xml.frame.report.component.service.JpaTransaction;

@Service(TransSb.NAME)
public class TransSb implements JpaTransaction{
	
	public static final String NAME = "TransSb";
	@PersistenceContext(unitName = "SbDB")
	EntityManager em;
	
	@Transactional("transSb")
	public void run(ServiceRunnable run) throws Exception{
		run.run();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
