package com.xml.frame.report.component.service;

import javax.persistence.EntityManager;

public interface JpaTransaction{
	void run(ServiceRunnable run) throws Exception;
	EntityManager getEntityManager();
}