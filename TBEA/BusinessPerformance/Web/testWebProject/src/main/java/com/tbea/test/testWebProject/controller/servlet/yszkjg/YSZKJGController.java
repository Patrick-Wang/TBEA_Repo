package com.tbea.test.testWebProject.controller.servlet.yszkjg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.test.testWebProject.service.yszkjg.YSZKJGService;

@Controller
@RequestMapping(value = "YSZKJG")
public class YSZKJGController {

	@Autowired
	private YSZKJGService yszkjgService;

	private String view = "yszkjgPage";

	private String commandName = "result";

	@RequestMapping(value = "importYSZKJG.do", method = RequestMethod.GET)
	public ModelAndView importYSZKJG(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = yszkjgService.importYSZKJG();
		System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}

}
