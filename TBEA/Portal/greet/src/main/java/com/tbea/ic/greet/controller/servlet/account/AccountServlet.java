package com.tbea.ic.greet.controller.servlet.account;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.greet.common.OnlineService;
import com.tbea.ic.greet.model.entity.Account;
import com.tbea.ic.greet.service.account.AccountService;


@Controller
@RequestMapping(value = "/account")
public class AccountServlet {


	@Autowired
	AccountService accountService;
	

	@RequestMapping(value = "/index.do")
	public ModelAndView goIndex(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		Account account = (Account) request.getSession().getAttribute("account");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usrName", account.getShortName());
		if (null != request.getParameter("isCamel")){
			return new ModelAndView("index_camel", map);
		}
		return new ModelAndView("index", map);
	}
	
	@RequestMapping(value = "/welcome.do")
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		if (!OnlineService.isOnline(request.getSession())){
			return new ModelAndView("redirect:/account/login.do");
		}else{
			return new ModelAndView("redirect:/account/index.do");
		}
	}

	@RequestMapping(value = "/validate.do")
	public ModelAndView validate(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{	
		if (!OnlineService.isOnline(request.getSession())){
			String name = request.getParameter("username");
			String psw = request.getParameter("password");
			Account account = accountService.validate(name, psw);
			if (null != account){
				OnlineService.goOnline(request);
				request.getSession().setAttribute("account", account);
				return new ModelAndView("redirect:/account/index.do");
			}else{
				return new ModelAndView("redirect:/account/login.do?error=failed");
			}
		}else{
			return new ModelAndView("redirect:/account/index.do");
		}
	}
	
	@RequestMapping(value = "/login.do")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String error = request.getParameter("error");
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != error){
			map.put("showError", "visiable");
		}else{
			map.put("showError", "hidden");
		}
		return new ModelAndView("login", map);
	}
	
	@RequestMapping(value = "/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		if (OnlineService.isOnline(request)){
			request.getSession().setAttribute("account", null);
			OnlineService.goOffline(request);
		}
		return new ModelAndView("redirect:/account/login.do");
	}
	
	@RequestMapping(value = "/bind.do")
	public @ResponseBody byte[] bind(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String name = request.getParameter("bindName");
		String psw = request.getParameter("bindPsw");
		String sysId = request.getParameter("bindSystem");
		Account account = (Account) request.getSession().getAttribute("account");
		boolean ret = this.accountService.bindSystem(account, sysId, name, psw);
		return ("{\"result\":" + ret +"}").getBytes("utf-8");
	}
	
	@RequestMapping(value = "/get_login_url.do")
	public ModelAndView getLoginUrl(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		Account account = (Account) request.getSession().getAttribute("account");
		String sysId = request.getParameter("sysId");
		String url = this.accountService.getLoginUrl(account, sysId, request.getRemoteAddr());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", url);
		return new ModelAndView("host", map);
	}
		
	
}
 