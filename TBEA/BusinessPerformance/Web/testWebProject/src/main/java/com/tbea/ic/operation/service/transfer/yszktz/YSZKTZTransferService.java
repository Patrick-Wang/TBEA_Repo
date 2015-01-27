package com.tbea.ic.operation.service.transfer.yszktz;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface YSZKTZTransferService {

	@Transactional("transactionManager")
	public boolean transferYSZKTZ();
	
}
