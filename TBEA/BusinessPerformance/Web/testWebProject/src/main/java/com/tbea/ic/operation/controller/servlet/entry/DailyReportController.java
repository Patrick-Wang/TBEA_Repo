package com.tbea.ic.operation.controller.servlet.entry;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Url;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.entry.DailyReportService;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(value = "dailyReport")
public class DailyReportController {
	
	@Autowired
	private DailyReportService dailyReportService;

	@RequestMapping(value = {"yszk.do" ,"v2/yszk.do"}, method = RequestMethod.GET)
	public ModelAndView getYszk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		DateSelection dateSel = new DateSelection();
		Map<String, Object> map = new HashMap<String, Object>();
		dateSel.select(map);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "yszkrb", map);
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

	/**
	 * 录入提交操作，返回是否成功
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "yszklr_submit.do")
	public @ResponseBody byte[] submitYszkLr(HttpServletRequest request,
										   HttpServletResponse response) throws UnsupportedEncodingException {
		Account account = SessionManager.getAccount(request.getSession());
		Date date = DateSelection.getDate(request);
		String data = request.getParameter("data");
		JSONArray jData = JSONArray.fromObject(data);
		ErrorCode code = dailyReportService.submitYszkLr(account, date, jData);
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

	/**
	 * 要进行录入操作时检查此月是否有录入过，如果有便将每月录入项显示出来
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "yszklr_update.do")
	public @ResponseBody byte[] getYszklrUpdate(HttpServletRequest request,
											  HttpServletResponse response) throws UnsupportedEncodingException {
		Account account = SessionManager.getAccount(request.getSession());
		Date date = DateSelection.getDate(request);
		List<String[]> data = dailyReportService.getYszkLRData(account, date);
		return JSONArray.fromObject(data).toString().replace("null", "\"\"").getBytes("utf-8");
	}
}
