package com.tbea.datatransfer.controller.servlet.local.ydsjhkqk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.ydsjhkqk.YDSJHKQKTransferService;

@Controller
@RequestMapping(value = "ydsjhkqkTransfer")
public class YDSJHKQKTransferController {

	@Autowired
	private YDSJHKQKTransferService ydsjhkqkTransferService;

	private String view = "ydsjhkqkTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "ydsjhkqkTransfer.do", method = RequestMethod.GET)
	public ModelAndView ydsjhkqkTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = ydsjhkqkTransferService.transferYDSJHKQK();
		return new ModelAndView(view, commandName, result);
	}

}
