package com.tbea.ic.operation.controller.servlet.yszkrb;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
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
import com.tbea.ic.operation.service.yszkrb.YSZKRBService;


@Controller
@RequestMapping(value = "yszkrb")
public class YSZKRBController {
	
	@Autowired
	private YSZKRBService yszkrbService;

	@RequestMapping(value = "yszk.do", method = RequestMethod.GET)
	public ModelAndView getYszk(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		DateSelection dateSel = new DateSelection();
		Map<String, Object> map = new HashMap<String, Object>();
		dateSel.select(map);
		return new ModelAndView("yszkrb", map);
	}

	@RequestMapping(value = "yszk_update.do")
	public @ResponseBody byte[] getYszkUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Date date = DateSelection.getDate(request);
		List<String[]> data = yszkrbService.getYszkData(date);
		return JSONArray.fromObject(data).toString().replace("null", "\"--\"").getBytes("utf-8");
	}
}
