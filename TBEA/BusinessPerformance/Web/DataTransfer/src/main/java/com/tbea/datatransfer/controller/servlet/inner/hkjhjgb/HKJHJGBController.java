package com.tbea.datatransfer.controller.servlet.inner.hkjhjgb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.inner.hkjhjgb.HKJHJGBService;

@Controller
@RequestMapping(value = "HKJHJGB")
public class HKJHJGBController {

	@Autowired
	private HKJHJGBService hkjhjgbService;

	private String view = "hkjhjgbPage";

	private String commandName = "result";

	@RequestMapping(value = "importHKJHJGB.do", method = RequestMethod.GET)
	public ModelAndView importHKJHJGB(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = hkjhjgbService.importHKJHJGB();
		// System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}

}
