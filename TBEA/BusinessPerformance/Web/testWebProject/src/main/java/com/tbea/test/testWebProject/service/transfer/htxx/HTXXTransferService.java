package com.tbea.test.testWebProject.service.transfer.htxx;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface HTXXTransferService {

	@Transactional("transactionManager")
	public boolean transferHTXX();
	
}
