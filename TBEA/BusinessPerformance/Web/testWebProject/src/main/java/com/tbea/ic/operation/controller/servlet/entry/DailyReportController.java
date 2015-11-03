package com.tbea.ic.operation.controller.servlet.entry;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.entry.DailyReportService;


@Controller
@RequestMapping(value = "dailyReport")
public class DailyReportController {
	
	@Autowired
	private DailyReportService dailyReportService;

	
	

	@RequestMapping(value = "yszk.do", method = RequestMethod.GET)
	public ModelAndView getYszk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		return new ModelAndView("yszkDaily");
	}

	@RequestMapping(value = "yszk_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getYszkUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Account account = SessionManager.getAccount(request.getSession());
		String result = "";
		return result.getBytes("utf-8");
	}
}
