package com.tbea.ic.weixin.controller.servlet.weixin;


import com.tbea.ic.WeixinSdkException;
import com.tbea.ic.weixin.service.weixin.WeiXinService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qq.weixin.mp.aes.AesException;
import com.tbea.ic.auth.AuthException;
import com.tbea.ic.auth.Connection;
import com.tbea.ic.auth.Verifier;
import com.tbea.ic.message.entity.News;
import com.tbea.ic.message.sender.Messager;

@Controller
@RequestMapping(value = "weixin")
public class WeiXinServlet {
	
	@Autowired
	WeiXinService weiXinService;

		

	@RequestMapping(value = "verify.do")
	public void login(HttpServletRequest request,
			HttpServletResponse response) throws AesException, IOException {
		Verifier verifier = new Verifier("nH5", "wx40b71464a42adcf3", "fDYqXp5YJizTlMTLKu9b2L8RMH8jUaB1mivzfzV9As0");
		verifier.verify(request, response);
	}
	
	
	@RequestMapping(value = "testGetMsg.do")
	public void testGetMsg(HttpServletRequest request,
			HttpServletResponse response) throws IOException, WeixinSdkException  {
		Connection.getInstance().open("wx40b71464a42adcf3", "BW-Tuxi3fYjgjOOQv2d9iR_7Cz0mRSDVfVaHtjE-2Z1GaHQQIwV0awLsO17zPnPy");
		String id = request.getParameter("id");
		
		String art = weiXinService.getMsg(Integer.valueOf(id));
		response.getWriter().write("<div>" + art + "</div>");
	}
	
	@RequestMapping(value = "testMsg.do")
	public void testMsg(HttpServletRequest request,
			HttpServletResponse response) throws IOException, WeixinSdkException  {
		Connection.getInstance().open("wx40b71464a42adcf3", "BW-Tuxi3fYjgjOOQv2d9iR_7Cz0mRSDVfVaHtjE-2Z1GaHQQIwV0awLsO17zPnPy");
		String idstr = request.getParameter("ids");
		String usrstr = request.getParameter("users");
		String[] ids = idstr.split("_");
		String[] usrs = usrstr.split("_");
		List<Integer> allIds = new ArrayList<Integer>();
		for(int i = 0; i < ids.length; ++i){
			allIds.add(Integer.parseInt(ids[i]));
		}
		weiXinService.sendNews(allIds, usrs);
	}
	
	@RequestMapping(value = "test.do")
	public void test(HttpServletRequest request,
			HttpServletResponse response) throws IOException, WeixinSdkException  {
		Connection.getInstance().open("wx40b71464a42adcf3", "BW-Tuxi3fYjgjOOQv2d9iR_7Cz0mRSDVfVaHtjE-2Z1GaHQQIwV0awLsO17zPnPy");
		Messager msger = new Messager(25);
		News<News.Article> news = new News<News.Article>();
		News.Article art = new News.Article();
		art.setDescription("经营管控 系统测试 新闻");
		List<News.Article> arts = new ArrayList<News.Article>();
		arts.add(art);
		arts.add(art);
		arts.add(art);
		arts.add(art);
		arts.add(art);
		arts.add(art);
		arts.add(art);
		arts.add(art);
		arts.add(art);

		art.setTitle("经营管控");
		art.setPicurl("http://192.168.7.22/BusinessManagement/images/logo.png");
		art.setUrl("http://192.168.7.22/BusinessManagement");
		news.setArticles(arts);
		msger.news(news).toUser("sunfuda").send();
	}
	
	@RequestMapping(value = "transfer.do")
	public void transfer(HttpServletRequest request,
			HttpServletResponse response) throws AesException, IOException {
		weiXinService.transferOrg();
		weiXinService.transferPersion();
	}
}
