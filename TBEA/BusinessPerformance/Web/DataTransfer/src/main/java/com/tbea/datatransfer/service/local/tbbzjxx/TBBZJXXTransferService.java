package com.tbea.datatransfer.service.local.tbbzjxx;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface TBBZJXXTransferService {

	public boolean transferTBBZJXX();
	
}
