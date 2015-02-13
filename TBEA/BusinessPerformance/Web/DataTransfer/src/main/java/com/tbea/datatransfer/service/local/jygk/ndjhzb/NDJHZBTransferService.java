package com.tbea.datatransfer.service.local.jygk.ndjhzb;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface NDJHZBTransferService {

	public boolean transferNDJHZB();
	
}
