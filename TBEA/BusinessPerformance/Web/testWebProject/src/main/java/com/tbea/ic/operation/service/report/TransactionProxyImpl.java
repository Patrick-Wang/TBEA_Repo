package com.tbea.ic.operation.service.report;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionProxyImpl implements TransactionProxy{

	@Override
	@Transactional("transactionManager")
	public void invokeTransactionManager(Runnable runnable) {
		runnable.run();
	}

}
