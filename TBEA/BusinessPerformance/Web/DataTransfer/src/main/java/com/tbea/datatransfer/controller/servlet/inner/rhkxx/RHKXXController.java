package com.tbea.datatransfer.controller.servlet.inner.rhkxx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.inner.rhkxx.RHKXXService;

@Controller
@RequestMapping(value = "RHKXX")
public class RHKXXController {

	@Autowired
	private RHKXXService rhkxxService;

	private String view = "rhkxxPage";

	private String commandName = "result";

	@RequestMapping(value = "importRHKXX.do", method = RequestMethod.GET)
	public ModelAndView importRHKXX(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = rhkxxService.importRHKXX();
		// System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}

}
