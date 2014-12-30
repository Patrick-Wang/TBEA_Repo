package com.tbea.datatransfer.controller.servlet.mrhk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.mrhk.MRHKTransferService;

@Controller
@RequestMapping(value = "mrhkTransfer")
public class MRHKTransferController {

	@Autowired
	private MRHKTransferService mrhkTransferService;

	private String view = "mrhkTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "mrhkTransfer.do", method = RequestMethod.GET)
	public ModelAndView mrhkTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = mrhkTransferService.transferMRHK();
		return new ModelAndView(view, commandName, result);
	}

}
