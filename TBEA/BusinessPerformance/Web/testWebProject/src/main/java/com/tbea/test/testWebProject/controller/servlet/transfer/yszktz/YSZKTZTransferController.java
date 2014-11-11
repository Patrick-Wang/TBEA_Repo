package com.tbea.test.testWebProject.controller.servlet.transfer.yszktz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.test.testWebProject.service.transfer.yszktz.YSZKTZTransferService;

@Controller
@RequestMapping(value = "yszktzTransfer")
public class YSZKTZTransferController {

	@Autowired
	private YSZKTZTransferService yszktzTransferService;

	private String view = "yszktzTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "yszktzTransfer.do", method = RequestMethod.GET)
	public ModelAndView yszktzTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = yszktzTransferService.transferYSZKTZ();
		// System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}

}
