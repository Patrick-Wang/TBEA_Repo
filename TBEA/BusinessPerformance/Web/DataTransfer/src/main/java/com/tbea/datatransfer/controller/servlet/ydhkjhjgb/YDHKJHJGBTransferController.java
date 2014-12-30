package com.tbea.datatransfer.controller.servlet.ydhkjhjgb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.ydhkjhjgb.YDHKJHJGBTransferService;

@Controller
@RequestMapping(value = "ydhkjhjgbTransfer")
public class YDHKJHJGBTransferController {

	@Autowired
	private YDHKJHJGBTransferService ydhkjhjgbTransferService;

	private String view = "ydhkjhjgbTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "ydhkjhjgbTransfer.do", method = RequestMethod.GET)
	public ModelAndView ydhkjhjgbTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = ydhkjhjgbTransferService.transferYDHKJHJGB();
		return new ModelAndView(view, commandName, result);
	}

}
