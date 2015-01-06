package com.tbea.datatransfer.controller.servlet.local.xltb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.xltb.XLTBTransferService;

@Controller
@RequestMapping(value = "xltbTransfer")
public class XLTBTransferController {

	@Autowired
	private XLTBTransferService xltbTransferService;

	private String view = "xltbTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "xltbTransfer.do", method = RequestMethod.GET)
	public ModelAndView xltbTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = xltbTransferService.transferXLTB();
		return new ModelAndView(view, commandName, result);
	}

}
