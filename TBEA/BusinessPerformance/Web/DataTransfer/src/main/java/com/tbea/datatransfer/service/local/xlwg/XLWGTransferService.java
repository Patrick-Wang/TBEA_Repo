package com.tbea.datatransfer.service.local.xlwg;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface XLWGTransferService {

	public boolean transferXLWG();
	
}
