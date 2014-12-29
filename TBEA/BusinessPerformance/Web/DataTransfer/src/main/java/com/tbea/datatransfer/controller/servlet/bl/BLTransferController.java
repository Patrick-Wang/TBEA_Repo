package com.tbea.datatransfer.controller.servlet.bl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.bl.BLTransferService;

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
		return new ModelAndView(view, commandName, result);
	}
	
}
