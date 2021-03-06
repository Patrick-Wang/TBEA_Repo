package com.tbea.datatransfer.controller.servlet.local.byqwg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.byqwg.BYQWGTransferService;

@Controller
@RequestMapping(value = "byqwgTransfer")
public class BYQWGTransferController {

	@Autowired
	private BYQWGTransferService byqwgTransferService;

	private String view = "byqwgTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "byqwgTransfer.do", method = RequestMethod.GET)
	public ModelAndView xlwgTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = byqwgTransferService.transferBYQWG();
		return new ModelAndView(view, commandName, result);
	}

}
