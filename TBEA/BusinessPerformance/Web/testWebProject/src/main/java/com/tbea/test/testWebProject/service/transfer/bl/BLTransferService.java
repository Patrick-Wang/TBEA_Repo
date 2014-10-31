package com.tbea.test.testWebProject.service.transfer.bl;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface BLTransferService {

	@Transactional("transactionManager")
	public boolean transferBL();
	
}
