package com.tbea.datatransfer.service.local.mrhk;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface MRHKTransferService {

	@Transactional("transactionManager")
	public boolean transferMRHK();
	
}
