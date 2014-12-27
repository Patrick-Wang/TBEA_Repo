package com.tbea.datatransfer.service.local.htxx;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface HTXXTransferService {

	@Transactional("transactionManager")
	public boolean transferHTXX();
	
}
