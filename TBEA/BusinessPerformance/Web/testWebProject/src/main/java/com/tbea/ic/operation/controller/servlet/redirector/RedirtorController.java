package com.tbea.ic.operation.controller.servlet.redirector;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "redirector")
public class RedirtorController {
	
	@RequestMapping(value = {"redirect.do"}, method = RequestMethod.GET)
	public ModelAndView redirect(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		return new ModelAndView("ui2/redirector");
	}
}
