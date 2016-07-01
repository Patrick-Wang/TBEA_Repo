package com.tbea.ic.operation.service.report;

public interface TransactionProxy {
	void invokeTransactionManager(Runnable run);
}
