package com.tbea.datatransfer.controller.servlet.local.xlwg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.xlwg.XLWGTransferService;

@Controller
@RequestMapping(value = "xlwgTransfer")
public class XLWGTransferController {

	@Autowired
	private XLWGTransferService xlwgTransferService;

	private String view = "xlwgTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "xlwgTransfer.do", method = RequestMethod.GET)
	public ModelAndView xlwgTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = xlwgTransferService.transferXLWG();
		return new ModelAndView(view, commandName, result);
	}

}
