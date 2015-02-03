package com.tbea.datatransfer.service.local.yqysysfl;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface YQYSYSFLTransferService {

	public boolean transferYQYSYSFL();
	
}
