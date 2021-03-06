package com.tbea.ic.operation.controller.servlet.entry;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Util;
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
		DateSelection dateSel = new DateSelection();
		Map<String, Object> map = new HashMap<String, Object>();
		dateSel.select(map);
		return new ModelAndView("yszkrb", map);
	}

	@RequestMapping(value = "yszk_submit.do")
	public @ResponseBody byte[] submitYszk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Account account = SessionManager.getAccount(request.getSession());
		Date date = DateSelection.getDate(request);
		String data = request.getParameter("data");
		JSONArray jData = JSONArray.fromObject(data);
		ErrorCode code = dailyReportService.submitYszk(account, date, jData);
		return Util.response(code);
	}
	
	@RequestMapping(value = "yszk_update.do")
	public @ResponseBody byte[] getYszkUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Account account = SessionManager.getAccount(request.getSession());
		Date date = DateSelection.getDate(request);
		List<String[]> data = dailyReportService.getYszkData(account, date);
		return JSONArray.fromObject(data).toString().replace("null", "\"\"").getBytes("utf-8");
	}
}
