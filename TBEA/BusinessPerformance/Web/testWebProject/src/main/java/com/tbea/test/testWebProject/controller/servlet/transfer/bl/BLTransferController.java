package com.tbea.test.testWebProject.controller.servlet.transfer.bl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.test.testWebProject.service.transfer.bl.BLTransferService;

@Controller
@RequestMapping(value = "blTransfer")
public class BLTransferController {

	@Autowired
	private BLTransferService blTransferService;

	private String view = "blTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "blTransfer.do", method = RequestMethod.GET)
	public ModelAndView blTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = blTransferService.transferBL();
		// System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}
	
}
