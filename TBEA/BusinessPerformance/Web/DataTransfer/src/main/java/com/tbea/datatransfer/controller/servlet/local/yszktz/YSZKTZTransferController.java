package com.tbea.datatransfer.controller.servlet.local.yszktz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.yszktz.YSZKTZTransferService;

@Controller
@RequestMapping(value = "yszktzTransfer")
public class YSZKTZTransferController {

	@Autowired
	private YSZKTZTransferService yszktzTransferService;

	private String view = "yszktzTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "yszktzTransfer.do", method = RequestMethod.GET)
	public ModelAndView yszktzTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = yszktzTransferService.transferYSZKTZ();
		return new ModelAndView(view, commandName, result);
	}

}
