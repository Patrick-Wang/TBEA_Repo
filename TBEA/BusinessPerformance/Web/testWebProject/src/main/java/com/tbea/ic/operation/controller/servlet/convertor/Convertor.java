package com.tbea.ic.operation.controller.servlet.convertor;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.service.convertor.ConvertorSevice;


@Controller
@RequestMapping(value = "convertor")
public class Convertor {
	
	@Autowired
	ConvertorSevice service;
	
	private byte[] log;
	
	@RequestMapping(value = "ndjh.do", method = RequestMethod.GET)
	public ModelAndView Ndjh(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("convertor");
	
	}
		
	@RequestMapping(value = "ndjh_log.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getLog(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		return log;
	}
	
	@RequestMapping(value = "ndjh_convert.do", method = RequestMethod.GET)
	public @ResponseBody byte[] convertNdjh(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		return log = service.convertNdjh(request, response).getBytes("utf-8");
	
	}
}
