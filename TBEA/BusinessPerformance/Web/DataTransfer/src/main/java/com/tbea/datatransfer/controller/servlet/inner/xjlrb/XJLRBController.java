package com.tbea.datatransfer.controller.servlet.inner.xjlrb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.inner.xjlrb.XJLRBService;

@Controller
@RequestMapping(value = "XJLRB")
public class XJLRBController {

	@Autowired
	private XJLRBService xjlrbService;

	private String view = "xjlrbPage";

	private String commandName = "result";

	@RequestMapping(value = "importXJLRB.do", method = RequestMethod.GET)
	public ModelAndView importXJLRB(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = xjlrbService.importXJLRB();
		// System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}

}
