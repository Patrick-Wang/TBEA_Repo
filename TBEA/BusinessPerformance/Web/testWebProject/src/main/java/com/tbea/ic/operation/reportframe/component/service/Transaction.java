package com.tbea.ic.operation.reportframe.component.service;

import javax.persistence.EntityManager;

public interface Transaction{
	void run(ServiceRunnable run) throws Exception;
	EntityManager getEntityManager();
}