package com.tbea.datatransfer.service.local.yszktz;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface YSZKTZTransferService {

	public boolean transferYSZKTZ();
	
}
