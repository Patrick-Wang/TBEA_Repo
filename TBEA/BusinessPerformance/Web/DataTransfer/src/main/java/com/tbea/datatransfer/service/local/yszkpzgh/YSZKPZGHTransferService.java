package com.tbea.datatransfer.service.local.yszkpzgh;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface YSZKPZGHTransferService {

	public boolean transferYSZKPZGH();
	
}
