package com.tbea.datatransfer.controller.servlet.inner.yqkqsbhb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.inner.yqkqsbhb.YQKQSBHBService;

@Controller
@RequestMapping(value = "YQKQSBHB")
public class YQKQSBHBController {

	@Autowired
	private YQKQSBHBService yqkqsbhbService;

	private String view = "yqkqsbhbPage";

	private String commandName = "result";

	@RequestMapping(value = "importYQKQSBHB.do", method = RequestMethod.GET)
	public ModelAndView importYQKQSBHB(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = yqkqsbhbService.importYQKQSBHB();
		// System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}

}
