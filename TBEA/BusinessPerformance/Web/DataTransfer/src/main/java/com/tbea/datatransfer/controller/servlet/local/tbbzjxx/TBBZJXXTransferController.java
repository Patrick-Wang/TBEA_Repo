package com.tbea.datatransfer.controller.servlet.local.tbbzjxx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.tbbzjxx.TBBZJXXTransferService;

@Controller
@RequestMapping(value = "tbbzjxxTransfer")
public class TBBZJXXTransferController {

	@Autowired
	private TBBZJXXTransferService tbbzjxxTransferService;

	private String view = "tbbzjxxTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "tbbzjxxTransfer.do", method = RequestMethod.GET)
	public ModelAndView tbbzjxxTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = tbbzjxxTransferService.transferTBBZJXX();
		return new ModelAndView(view, commandName, result);
	}

}
