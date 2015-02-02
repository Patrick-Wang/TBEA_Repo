package com.tbea.datatransfer.controller.servlet.local.byqtb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.byqtb.BYQTBTransferService;

@Controller
@RequestMapping(value = "byqtbTransfer")
public class BYQTBTransferController {

	@Autowired
	private BYQTBTransferService byqtbTransferService;

	private String view = "byqtbTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "byqtbTransfer.do", method = RequestMethod.GET)
	public ModelAndView xltbTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = byqtbTransferService.transferBYQTB();
		return new ModelAndView(view, commandName, result);
	}

}
