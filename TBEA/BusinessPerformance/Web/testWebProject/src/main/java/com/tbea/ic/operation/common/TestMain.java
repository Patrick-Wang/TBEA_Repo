package com.tbea.ic.operation.common;

import com.tbea.ic.operation.service.transfer.bl.BLTransferService;
import com.tbea.ic.operation.service.transfer.bl.BLTransferServiceImpl;

public class TestMain {

	public static void main(String[] args) {

		BLTransferService blTransferService = new BLTransferServiceImpl();
		boolean result = blTransferService.transferBL();
		// System.out.println("result:" + result);

	}

}
