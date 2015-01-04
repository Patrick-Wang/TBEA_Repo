package com.tbea.datatransfer.service.local.fkfs.byq;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface FKFSBYQGWTransferService {

	public boolean transferFKFSBYQGW();
	
}
