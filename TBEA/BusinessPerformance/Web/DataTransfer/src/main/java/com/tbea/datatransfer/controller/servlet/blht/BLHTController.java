package com.tbea.datatransfer.controller.servlet.blht;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.blht.BLHTService;

@Controller
@RequestMapping(value = "BLHT")
public class BLHTController {

	@Autowired
	private BLHTService blhtService;

	private String view = "blhtPage";

	private String commandName = "result";

	@RequestMapping(value = "importBLHT.do", method = RequestMethod.GET)
	public ModelAndView importBLHT(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = blhtService.importBLHT();
		// System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}

}
