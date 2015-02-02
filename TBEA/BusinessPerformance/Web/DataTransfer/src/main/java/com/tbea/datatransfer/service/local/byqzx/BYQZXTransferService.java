package com.tbea.datatransfer.service.local.byqzx;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface BYQZXTransferService {

	public boolean transferBYQZX();
	
}
