package com.tbea.datatransfer.service.local.byqwg;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface BYQWGTransferService {

	public boolean transferBYQWG();
	
}
