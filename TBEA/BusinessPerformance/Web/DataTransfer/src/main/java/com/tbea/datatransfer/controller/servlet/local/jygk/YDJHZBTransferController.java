package com.tbea.datatransfer.controller.servlet.local.jygk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.jygk.ydjhzb.YDJHZBTransferService;

@Controller
@RequestMapping(value = "ydjhzbTransfer")
public class YDJHZBTransferController {

	@Autowired
	private YDJHZBTransferService ydjhzbTransferService;

	private String view = "ydjhzbTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "ydjhzbTransfer.do", method = RequestMethod.GET)
	public ModelAndView ydjhzbTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = ydjhzbTransferService.transferYDJHZB();
		return new ModelAndView(view, commandName, result);
	}

}
