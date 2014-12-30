package com.tbea.datatransfer.service.local.ztyszkfxb;

import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManager")
public interface ZTYSZKFXBTransferService {

	public boolean transferZTYSZKFXB();
	
}
