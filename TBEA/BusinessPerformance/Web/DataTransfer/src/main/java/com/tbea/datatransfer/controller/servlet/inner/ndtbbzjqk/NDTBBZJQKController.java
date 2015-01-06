package com.tbea.datatransfer.controller.servlet.inner.ndtbbzjqk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.inner.ndtbbzjqk.NDTBBZJQKService;

@Controller
@RequestMapping(value = "NDTBBZJQK")
public class NDTBBZJQKController {

	@Autowired
	private NDTBBZJQKService ndtbbzjqkService;

	private String view = "ndtbbzjqkPage";

	private String commandName = "result";

	@RequestMapping(value = "importNDTBBZJQK.do", method = RequestMethod.GET)
	public ModelAndView importNDTBBZJQK(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = ndtbbzjqkService.importNDTBBZJQK();
		// System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}

}
