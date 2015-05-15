package com.tbea.ic.operation.controller.servlet.ydzb;

import java.sql.Date;
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
import com.tbea.ic.operation.service.ydzb.gszb.GszbService;
import com.tbea.ic.operation.service.ydzb.rank.RankService;


@Controller
@RequestMapping(value = "ydzbRanking")
public class YDZBRankingController {
	
	@Autowired
	private YDZBService service;

	@Autowired
	private RankService rankService;
	
	@RequestMapping(value = "companys_ranking.do", method = RequestMethod.GET)
	public ModelAndView getCompanys_Ranking(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(service.getLatestHzbDate(),
				true, false);
		dateSel.select(map);
		return new ModelAndView("companys_ranking", map);
	}
	
	
	@RequestMapping(value = "companys_ranking_update.do", method = RequestMethod.GET)
	public @ResponseBody String getCompanys_Ranking_Update(HttpServletRequest request,
			HttpServletResponse response) {

		Date d = DateSelection.getDate(request);
		int rankingType = Integer.valueOf(request.getParameter("rankingType"));
		String ranking_val = null;
		if (rankingType == 1)
		{
			ranking_val = JSONArray.fromObject(rankService.getLrzeRank(d)).toString().replace("null", "\"--\"");
		}
		return ranking_val;
	}

}
