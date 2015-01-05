package com.tbea.datatransfer.controller.servlet.inner.ydzbfdw;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.inner.ydzbfdw.YDZBFDWService;

@Controller
@RequestMapping(value = "YDZBFDW")
public class YDZBFDWController {

	@Autowired
	private YDZBFDWService ydzbfdwService;

	private String view = "ydzbfdwPage";

	private String commandName = "result";

	@RequestMapping(value = "importYDZBFDW.do", method = RequestMethod.GET)
	public ModelAndView importYDZBFDW(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = ydzbfdwService.importYDZBFDW();
		// System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}

}
