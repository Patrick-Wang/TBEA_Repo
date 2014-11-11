package com.tbea.test.testWebProject.controller.servlet.yqk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.test.testWebProject.service.yqk.YQKService;

@Controller
@RequestMapping(value = "YQK")
public class YQKController {

	@Autowired
	private YQKService yqkService;

	private String view = "yqkPage";

	private String commandName = "result";

	@RequestMapping(value = "importYQK.do", method = RequestMethod.GET)
	public ModelAndView importYQK(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = yqkService.importYQK();
		// System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}

}
