package com.tbea.datatransfer.controller.servlet.local.jygk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.jygk.yj20zb.YJ20ZBTransferService;

@Controller
@RequestMapping(value = "yj20zbTransfer")
public class YJ20ZBTransferController {

	@Autowired
	private YJ20ZBTransferService yj20zbTransferService;

	private String view = "yj20zbTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "yj20zbTransfer.do", method = RequestMethod.GET)
	public ModelAndView yj20zbTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = yj20zbTransferService.transferYJ20ZB();
		return new ModelAndView(view, commandName, result);
	}

}
