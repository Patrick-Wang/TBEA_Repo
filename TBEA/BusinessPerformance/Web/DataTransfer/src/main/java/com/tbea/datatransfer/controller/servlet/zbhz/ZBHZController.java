package com.tbea.datatransfer.controller.servlet.zbhz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.zbhz.ZBHZService;

@Controller
@RequestMapping(value = "ZBHZ")
public class ZBHZController {

	@Autowired
	private ZBHZService zbhzService;

	private String view = "zbhzPage";

	private String commandName = "result";

	@RequestMapping(value = "importZBHZ.do", method = RequestMethod.GET)
	public ModelAndView importZBHZ(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = zbhzService.importZBHZ();
		// System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}

}
