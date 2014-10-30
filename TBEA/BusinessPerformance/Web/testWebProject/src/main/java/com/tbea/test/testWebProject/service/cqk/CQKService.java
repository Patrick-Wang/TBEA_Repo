package com.tbea.test.testWebProject.service.cqk;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface CQKService {

	@Transactional("transactionManager")
	public boolean importCQK();

}
