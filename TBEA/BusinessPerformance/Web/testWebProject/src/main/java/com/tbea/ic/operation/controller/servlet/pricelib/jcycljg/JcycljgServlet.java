package com.tbea.ic.operation.controller.servlet.pricelib.jcycljg;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgServiceImpl;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgService;

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
@RequestMapping(value = "jcycljg")
public class JcycljgServlet {
	@Resource(name=JcycljgServiceImpl.NAME)
	JcycljgService jcycljgService;

	@RequestMapping(value = "show.do", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(),
				true, true);
		dateSel.select(map);
		return new ModelAndView("priceLib/jcycljg/jcycljg", map);
	}
	
	@RequestMapping(value = "ysjs/update.do")
	public @ResponseBody byte[] ysjsUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		List<List<String>> result = jcycljgService.getYsjs(Date.valueOf(start), Date.valueOf(end));
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}
	
}
