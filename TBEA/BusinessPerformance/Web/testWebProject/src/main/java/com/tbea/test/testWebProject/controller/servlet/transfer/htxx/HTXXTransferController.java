package com.tbea.test.testWebProject.controller.servlet.transfer.htxx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.test.testWebProject.service.transfer.htxx.HTXXTransferService;

@Controller
@RequestMapping(value = "htxxTransfer")
public class HTXXTransferController {

	@Autowired
	private HTXXTransferService htxxTransferService;

	private String view = "htxxTransferPage";

	private String commandName = "result";

	@RequestMapping(value = "htxxTransfer.do", method = RequestMethod.GET)
	public ModelAndView htxxTransfer(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = htxxTransferService.transferHTXX();
		System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}
	
}
