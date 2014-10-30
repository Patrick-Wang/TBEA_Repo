package com.tbea.test.testWebProject.controller.servlet.cqk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.test.testWebProject.service.cqk.CQKService;

@Controller
@RequestMapping(value = "CQK")
public class CQKController {

	@Autowired
	private CQKService cqkService;

	private String view = "cqkPage";

	private String commandName = "result";

	@RequestMapping(value = "importCQK.do", method = RequestMethod.GET)
	public ModelAndView importCQK(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = cqkService.importCQK();
		System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}

}
