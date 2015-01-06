package com.tbea.datatransfer.controller.servlet.local.xm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.xm.XMTransferService;

@Controller
@RequestMapping(value = "xmTransfer")
public class XMTransferController {

	@Autowired
	private XMTransferService xmTransferService;

	private String view = "xmTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "xmTransfer.do", method = RequestMethod.GET)
	public ModelAndView xmTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = xmTransferService.transferXM();
		return new ModelAndView(view, commandName, result);
	}

}
