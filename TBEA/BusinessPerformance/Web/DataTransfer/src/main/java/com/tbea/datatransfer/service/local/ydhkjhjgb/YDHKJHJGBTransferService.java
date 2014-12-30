package com.tbea.datatransfer.service.local.ydhkjhjgb;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface YDHKJHJGBTransferService {

	public boolean transferYDHKJHJGB();
	
}
