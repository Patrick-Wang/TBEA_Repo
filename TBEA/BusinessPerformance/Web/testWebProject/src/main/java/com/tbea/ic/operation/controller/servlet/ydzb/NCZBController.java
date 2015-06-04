package com.tbea.ic.operation.controller.servlet.ydzb;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.service.ydzb.YDZBService;
import com.tbea.ic.operation.service.ydzb.rank.RankService;


@Controller
@RequestMapping(value = "NCzb")
public class NCZBController {
	
	@Autowired
	private YDZBService service;
	
	
	@RequestMapping(value = "AllCompanysNC_overview.do", method = RequestMethod.GET)
	public ModelAndView getAllCompanysNC_overview(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(),
				true, false);
		dateSel.select(map);
		return new ModelAndView("hzbNC_zbhz", map);
	}
	
	@RequestMapping(value = "CompanysNC.do", method = RequestMethod.GET)
	public ModelAndView getCompanysNC(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(),
				true, false);
		dateSel.select(map);
		return new ModelAndView("companys_ranking", map);
	}
	
	
	@RequestMapping(value = "AllCompanysNC_overview_update.do", method = RequestMethod.GET)
	public @ResponseBody String getAllCompanysNC_overview_update(HttpServletRequest request,
			HttpServletResponse response) {

		Date d = DateSelection.getDate(request);
		String ranking_val = "[]";
		
		return ranking_val;
	}
	
	@RequestMapping(value = "CompanysNC_update.do", method = RequestMethod.GET)
	public @ResponseBody String getCompanysNC_update(HttpServletRequest request,
			HttpServletResponse response) {

		Date d = DateSelection.getDate(request);
		String ranking_val = null;
		
		return ranking_val;
	}

}