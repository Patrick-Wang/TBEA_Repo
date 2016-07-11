package com.tbea.ic.operation.reportframe.component.service;

import javax.persistence.EntityManager;

public interface Transaction{
	void run(Runnable run);
	EntityManager getEntityManager();
}