package com.tbea.test.testWebProject.common;

import com.tbea.test.testWebProject.service.transfer.bl.BLTransferService;
import com.tbea.test.testWebProject.service.transfer.bl.BLTransferServiceImpl;

public class TestMain {

	public static void main(String[] args) {

		BLTransferService blTransferService = new BLTransferServiceImpl();
		boolean result = blTransferService.transferBL();
		System.out.println("result:" + result);

	}

}
