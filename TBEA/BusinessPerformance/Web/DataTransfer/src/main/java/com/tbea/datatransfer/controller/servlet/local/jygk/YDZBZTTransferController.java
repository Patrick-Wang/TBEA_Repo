package com.tbea.datatransfer.controller.servlet.local.jygk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.jygk.ydzbzt.YDZBZTTransferService;

@Controller
@RequestMapping(value = "ydzbztTransfer")
public class YDZBZTTransferController {

	@Autowired
	private YDZBZTTransferService ydzbztTransferService;

	private String view = "ydzbztTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "ydzbztTransfer.do", method = RequestMethod.GET)
	public ModelAndView ydzbztTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = ydzbztTransferService.transferYDZBZT();
		return new ModelAndView(view, commandName, result);
	}

}
