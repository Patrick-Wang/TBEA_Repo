package com.tbea.datatransfer.controller.servlet.local.byqzx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.byqzx.BYQZXTransferService;

@Controller
@RequestMapping(value = "byqzxTransfer")
public class BYQZXTransferController {

	@Autowired
	private BYQZXTransferService byqzxTransferService;

	private String view = "byqzxTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "byqzxTransfer.do", method = RequestMethod.GET)
	public ModelAndView xlzxTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = byqzxTransferService.transferBYQZX();
		return new ModelAndView(view, commandName, result);
	}

}
