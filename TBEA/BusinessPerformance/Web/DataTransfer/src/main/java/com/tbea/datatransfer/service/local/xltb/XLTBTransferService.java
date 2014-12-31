package com.tbea.datatransfer.service.local.xltb;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface XLTBTransferService {

	public boolean transferXLTB();
	
}
