package com.tbea.datatransfer.controller.servlet.local.yqysysfl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.yqysysfl.YQYSYSFLTransferService;

@Controller
@RequestMapping(value = "yqysysflTransfer")
public class YQYSYSFLTransferController {

	@Autowired
	private YQYSYSFLTransferService yqysysflTransferService;

	private String view = "yqysysflTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "yqysysflTransfer.do", method = RequestMethod.GET)
	public ModelAndView yqysysflTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = yqysysflTransferService.transferYQYSYSFL();
		return new ModelAndView(view, commandName, result);
	}

}
