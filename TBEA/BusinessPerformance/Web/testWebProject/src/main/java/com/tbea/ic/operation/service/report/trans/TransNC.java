package com.tbea.ic.operation.service.report.trans;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xml.frame.report.component.service.ServiceRunnable;
import com.xml.frame.report.component.service.JpaTransaction;

@Service(TransNC.NAME)
public class TransNC implements JpaTransaction{
	
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
