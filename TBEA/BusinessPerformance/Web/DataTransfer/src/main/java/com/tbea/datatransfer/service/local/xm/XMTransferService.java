package com.tbea.datatransfer.service.local.xm;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface XMTransferService {

	public boolean transferXM();
	
}
