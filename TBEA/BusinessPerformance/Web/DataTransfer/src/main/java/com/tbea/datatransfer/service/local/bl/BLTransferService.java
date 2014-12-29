package com.tbea.datatransfer.service.local.bl;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface BLTransferService {

	public boolean transferBL();
	
}
