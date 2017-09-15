package com.tbea.ic.operation.service.report;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xml.frame.report.component.service.ServiceRunnable;
import com.xml.frame.report.component.service.JpaTransaction;

@Service(Trans15.NAME)
public class Trans15 implements JpaTransaction{
	
	public static final String NAME = "Trans15";
	@PersistenceContext(unitName = "15DB")
	EntityManager em;
	
	@Transactional("transactionManager2")
	public void run(ServiceRunnable run) throws Exception{
		run.run();
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}
}
