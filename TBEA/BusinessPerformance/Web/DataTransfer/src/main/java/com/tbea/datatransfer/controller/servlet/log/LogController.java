package com.tbea.datatransfer.controller.servlet.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.datatransfer.service.local.log.LogService;
import com.tbea.datatransfer.service.local.ydzbfdw.YDZBFDWService;

@Controller
@RequestMapping(value = "Log")
public class LogController {

	@Autowired
	private LogService logService;

	private String view = "ydzbfdwPage";

	private String commandName = "result";

	@RequestMapping(value = "getLog.do", method = RequestMethod.GET)
	public ModelAndView getLog(HttpServletRequest request,
			HttpServletResponse response) {
		boolean result = logService.logYDZBFDW();
		// System.out.println("result:" + result);
		return new ModelAndView(view, commandName, result);
	}

}
