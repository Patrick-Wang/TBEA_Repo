package com.tbea.datatransfer.controller.servlet.local.mrhkhz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.mrhkhz.MRHKHZTransferService;

@Controller
@RequestMapping(value = "mrhkhzTransfer")
public class MRHKHZTransferController {

	@Autowired
	private MRHKHZTransferService mrhkhzTransferService;

	private String view = "mrhkhzTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "mrhkhzTransfer.do", method = RequestMethod.GET)
	public ModelAndView mrhkhzTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = mrhkhzTransferService.transferMRHKHZ();
		return new ModelAndView(view, commandName, result);
	}

}
