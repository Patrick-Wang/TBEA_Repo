package com.tbea.ic.carrier.controller.servlet.nacao;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tbea.ic.carrier.service.nacao.NacaoService;


@Controller
@RequestMapping(value = "/nacao")
public class NacaoServlet {

	
	@Autowired
	NacaoService nacaoService;
	
	
	@Scheduled(cron="0 0 12 ? * 2-6")
	public void carrryUnfixed(){
		nacaoService.fetchCompanyWithUnfixedKeywords();
	}
	
	@Scheduled(cron="0 0 13 ? * 6")
	public void carrryAll(){
		nacaoService.fetchCompanyWithAllKeywords();
	}
	
	@RequestMapping(value = "/query_by_name.do")
	public @ResponseBody byte[] queryByName(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String name = request.getParameter("companyName");
		JSONArray orgs = nacaoService.findByName(name);
		return orgs.toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "/carrry_unfixed.do")
	public void carrryUnfixed(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		nacaoService.fetchCompanyWithUnfixedKeywords();
	}
	
	@RequestMapping(value = "/carrry_all.do")
	public  void carrryAll(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		nacaoService.fetchCompanyWithAllKeywords();
	}
}
 