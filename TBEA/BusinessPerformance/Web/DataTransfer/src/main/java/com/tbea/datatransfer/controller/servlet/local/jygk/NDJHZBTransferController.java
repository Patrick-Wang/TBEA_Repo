package com.tbea.datatransfer.controller.servlet.local.jygk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.jygk.ndjhzb.NDJHZBTransferService;

@Controller
@RequestMapping(value = "ndjhzbTransfer")
public class NDJHZBTransferController {

	@Autowired
	private NDJHZBTransferService ndjhzbTransferService;

	private String view = "ndjhzbTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "ndjhzbTransfer.do", method = RequestMethod.GET)
	public ModelAndView ndjhzbTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = ndjhzbTransferService.transferNDJHZB();
		return new ModelAndView(view, commandName, result);
	}

}
