package com.tbea.datatransfer.service.local.byqtb;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface BYQTBTransferService {

	public boolean transferBYQTB();
	
}
