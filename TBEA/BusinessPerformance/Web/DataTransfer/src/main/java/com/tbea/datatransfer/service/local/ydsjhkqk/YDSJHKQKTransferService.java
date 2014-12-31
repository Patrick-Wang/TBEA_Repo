package com.tbea.datatransfer.service.local.ydsjhkqk;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface YDSJHKQKTransferService {

	public boolean transferYDSJHKQK();
	
}
