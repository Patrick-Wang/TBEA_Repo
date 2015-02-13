package com.tbea.datatransfer.controller.servlet.local.jygk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.jygk.sjzb.SJZBTransferService;

@Controller
@RequestMapping(value = "sjzbTransfer")
public class SJZBTransferController {

	@Autowired
	private SJZBTransferService sjzbTransferService;

	private String view = "sjzbTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "sjzbTransfer.do", method = RequestMethod.GET)
	public ModelAndView sjzbTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = sjzbTransferService.transferSJZB();
		return new ModelAndView(view, commandName, result);
	}

}
