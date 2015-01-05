package com.tbea.datatransfer.controller.servlet.local.yszkpzgh;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.yszkpzgh.YSZKPZGHTransferService;

@Controller
@RequestMapping(value = "yszkpzghTransfer")
public class YSZKPZGHTransferController {

	@Autowired
	private YSZKPZGHTransferService yszkpzghTransferService;

	private String view = "yszkpzghTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "yszkpzghTransfer.do", method = RequestMethod.GET)
	public ModelAndView yszkpzghTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = yszkpzghTransferService.transferYSZKPZGH();
		return new ModelAndView(view, commandName, result);
	}

}
