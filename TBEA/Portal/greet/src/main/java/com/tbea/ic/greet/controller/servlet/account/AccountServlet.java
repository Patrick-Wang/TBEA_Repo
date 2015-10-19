package com.tbea.ic.greet.controller.servlet.account;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.greet.model.entity.Account;
import com.tbea.ic.greet.service.account.AccountService;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(value = "/account")
public class AccountServlet {


	@Autowired
	AccountService accountService;
	

	@RequestMapping(value = "/index.do")
	public ModelAndView goIndex(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		Account account = (Account) request.getSession().getAttribute("account");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usrName", "test");
		return new ModelAndView("index", map);
	}
	
	@RequestMapping(value = "/welcome.do")
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		if (null == (Account) request.getSession().getAttribute("account")){
			return new ModelAndView("redirect:/account/login.do");
		}else{
			return new ModelAndView("redirect:/account/index.do");
		}
	}
		
	@RequestMapping(value = "/login.do")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String name = request.getParameter("username");
		String psw = request.getParameter("password");
		Account account = (Account) request.getSession().getAttribute("account");
		if (null == account){
			account = accountService.login(name, psw);
		}
		
		if (null != account){
			return new ModelAndView("redirect:/account/index.do");
		}else{
			return new ModelAndView("login");
		}
	}
	
	@RequestMapping(value = "/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		request.getSession().setAttribute("account", null);
		request.getSession().invalidate();
		return new ModelAndView("login");
	}
}
 