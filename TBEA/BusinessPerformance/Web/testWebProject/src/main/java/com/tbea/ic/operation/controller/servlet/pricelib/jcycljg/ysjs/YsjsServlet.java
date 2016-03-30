package com.tbea.ic.operation.controller.servlet.pricelib.jcycljg.ysjs;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.service.pricelib.jcycljg.ysjs.YsjsServiceImpl;
import com.tbea.ic.operation.service.pricelib.jcycljg.ysjs.YsjsService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "ysjs")
public class YsjsServlet {
	@Resource(name=YsjsServiceImpl.NAME)
	YsjsService ysjsService;

	@RequestMapping(value = "show.do", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(),
				true, true);
		dateSel.select(map);
		return new ModelAndView("priceLib/jcycljg/ysjs", map);
	}
	
	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] update(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		List<List<String>> result = ysjsService.getYsjs(Date.valueOf(start), Date.valueOf(end));
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}
	
}
