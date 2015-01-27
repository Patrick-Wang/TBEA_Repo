package com.tbea.datatransfer.controller.servlet.inner.yszkjgqkb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.inner.yszkjgqkb.YSZKJGQKBService;

@Controller
@RequestMapping(value = "YSZKJGQKB")
public class YSZKJGQKBController {

	@Autowired
	private YSZKJGQKBService yszkjgqkbService;

	private String view = "yszkjgqkbPage";

	private String commandName = "result";

	@RequestMapping(value = "importYSZKJGQKB.do", method = RequestMethod.GET)
	public ModelAndView importYSZKJGQKB(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = yszkjgqkbService.importYSZKJGQKB();
		// System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}

}
