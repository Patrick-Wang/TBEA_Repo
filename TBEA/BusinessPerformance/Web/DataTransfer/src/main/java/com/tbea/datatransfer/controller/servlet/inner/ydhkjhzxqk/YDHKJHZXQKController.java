package com.tbea.datatransfer.controller.servlet.inner.ydhkjhzxqk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.inner.ydhkjhzxqk.YDHKJHZXQKService;

@Controller
@RequestMapping(value = "YDHKJHZXQK")
public class YDHKJHZXQKController {

	@Autowired
	private YDHKJHZXQKService ydhkjhzxqkService;

	private String view = "ydhkjhzxqkPage";

	private String commandName = "result";

	@RequestMapping(value = "importYDHKJHZXQK.do", method = RequestMethod.GET)
	public ModelAndView importYDHKJHZXQK(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = ydhkjhzxqkService.importYDHKJHZXQK();
		// System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}

}
