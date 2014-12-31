package com.tbea.datatransfer.service.local.mrhkhz;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface MRHKHZTransferService {

	public boolean transferMRHKHZ();
	
}
