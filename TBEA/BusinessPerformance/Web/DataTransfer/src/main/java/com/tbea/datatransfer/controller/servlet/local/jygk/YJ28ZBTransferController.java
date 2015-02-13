package com.tbea.datatransfer.controller.servlet.local.jygk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.jygk.yj28zb.YJ28ZBTransferService;

@Controller
@RequestMapping(value = "yj28zbTransfer")
public class YJ28ZBTransferController {

	@Autowired
	private YJ28ZBTransferService yj28zbTransferService;

	private String view = "yj28zbTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "yj28zbTransfer.do", method = RequestMethod.GET)
	public ModelAndView yj28zbTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = yj28zbTransferService.transferYJ28ZB();
		return new ModelAndView(view, commandName, result);
	}

}
