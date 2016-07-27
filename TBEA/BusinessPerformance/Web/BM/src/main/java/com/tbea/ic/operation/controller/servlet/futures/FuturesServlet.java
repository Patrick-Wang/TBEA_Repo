package com.tbea.ic.operation.controller.servlet.futures;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.service.futures.FuturesService;
import com.tbea.ic.operation.service.futures.FuturesServiceImpl;

@Controller
@RequestMapping(value = "futures")
public class FuturesServlet {

	@Resource(name = FuturesServiceImpl.NAME)
	FuturesService futuresService;

	@RequestMapping(value = "show.do", method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request,
			HttpServletResponse response) {

		return new ModelAndView("futures/futures");
	}

	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] futuresUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String type = request.getParameter("type");
		Map<String, Object> result = new HashMap<String, Object>();
		JSONArray chartArray = futuresService.getChartValues(type);
		JSONArray tableArray = futuresService.getTableValues(type);
		result.put("chartData", chartArray);
		result.put("tableData", tableArray);

		return JSONObject.fromObject(result).toString().getBytes("utf-8");
	}

}
