package com.tbea.ic.operation.controller.servlet.ydzb;

import java.io.UnsupportedEncodingException;
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
		DateSelection dateSel = new DateSelection(Calendar.getInstance(),
				true, false);
		dateSel.select(map);
		return new ModelAndView("companys_ranking", map);
	}
	
	
	@RequestMapping(value = "companys_ranking_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getCompanys_Ranking_Update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {

		Date d = DateSelection.getDate(request);
		int rankingType = Integer.valueOf(request.getParameter("rankingType"));
		String ranking_val = null;
		if (rankingType == 1) {
			ranking_val = JSONArray.fromObject(rankService.getJhlrRank(d))
					.toString().replace("null", "\"--\"");
		} else if (rankingType == 11) {
			ranking_val = JSONArray.fromObject(rankService.getXmgsJhlrRank(d))
					.toString().replace("null", "\"--\"");
		} else if (rankingType == 2) {
			ranking_val = JSONArray.fromObject(rankService.getLjlrRank(d))
					.toString().replace("null", "\"--\"");
		} else if (rankingType == 9) {
			ranking_val = JSONArray.fromObject(rankService.getJxjlRank(d))
					.toString().replace("null", "\"--\"");
		} else if (rankingType == 3) {
			ranking_val = JSONArray.fromObject(rankService.getRjlrRank(d))
					.toString().replace("null", "\"--\"");
		} else if (rankingType == 4) {
			ranking_val = JSONArray.fromObject(rankService.getRjsrRank(d))
					.toString().replace("null", "\"--\"");
		} else if (rankingType == 13) {
			ranking_val = JSONArray.fromObject(rankService.getXmgsRjsrRank(d))
					.toString().replace("null", "\"--\"");
		} else if (rankingType == 14) {
			ranking_val = JSONArray.fromObject(rankService.getXmgsRjlrRank(d))
					.toString().replace("null", "\"--\"");
		} else if (rankingType == 15) {
			ranking_val = JSONArray.fromObject(rankService.getYszkzsrbRank(d))
					.toString().replace("null", "\"--\"");
		}else if (rankingType == 16) {
			ranking_val = JSONArray.fromObject(rankService.getChzbRank(d))
					.toString().replace("null", "\"--\"");
		}else if (rankingType == 17) {
			ranking_val = JSONArray.fromObject(rankService.getYsAndChzbRank(d))
					.toString().replace("null", "\"--\"");
		}else if (rankingType == 18) {
			ranking_val = JSONArray.fromObject(rankService.getYszkAndBlzbRank(d))
					.toString().replace("null", "\"--\"");
		}
		return ranking_val.getBytes("utf-8");
	}

}
