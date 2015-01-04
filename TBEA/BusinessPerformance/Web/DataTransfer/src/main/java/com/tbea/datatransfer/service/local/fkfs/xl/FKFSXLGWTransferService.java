package com.tbea.datatransfer.service.local.fkfs.xl;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface FKFSXLGWTransferService {

	public boolean transferFKFSXLGW();
	
}
