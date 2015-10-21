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

import com.tbea.ic.greet.model.entity.Account;
import com.tbea.ic.greet.service.account.AccountService;


@Controller
@RequestMapping(value = "/account")
public class AccountServlet {


	//绩效管理信息平台 
	//http://172.28.8.119/HQ/myportal/__ac0x3login/__tpaction?requestSource=HQ_login&ssousername=sunfuda&ssopassword=123456
	//绩效管理信息平台 
	final static String JXUrl = "http://172.28.8.119/HQ/myportal/__ac0x3login/__tpaction?requestSource=HQ_login&ssousername=#UN#&ssopassword=#PW#";
	//OA
	final static String OAUrl = "http://192.168.7.12:8080/login.do?validate=login&ABS_SchemeName=jxkh&userId=#UN#&pass=#PW#";
	//jingyingguankong
	final static String JYGKUrl = "http://192.168.7.22/BusinessManagement/Login/validate.do?j_username=#UN#&j_password=#PW#";
	//zhihuiyinhang
	final static String ZHYHUrl = "http://km.tbea.com:8080/j_acegi_security_check?j_username=#UN#&j_password=#PW#";
	//人力
	final static String HRUrl = "http://192.168.7.76/login.jsp";
	//NC
	final static String NCUrl = "http://192.168.7.24:9083/login.jsp";

	@Autowired
	AccountService accountService;
	

	@RequestMapping(value = "/index.do")
	public ModelAndView goIndex(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		Account account = (Account) request.getSession().getAttribute("account");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usrName", account.getName());
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
			request.getSession().setAttribute("account", account);
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
	
	@RequestMapping(value = "/get_login_url.do")
	public @ResponseBody byte[] getLoginUrl(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		Account account = (Account) request.getSession().getAttribute("account");
		String sysId = request.getParameter("sysId");
		String url="";
		if ("1".equals(sysId)){
			url = JXUrl.replace("#UN#", account.getJxglName()).replace("#PW#", account.getJxglPassword());
		}else if ("3".equals(sysId)){
			url = OAUrl.replace("#UN#", account.getOAName()).replace("#PW#", account.getOAPassword());
		}else if ("4".equals(sysId)){
			url = ZHYHUrl.replace("#UN#", account.getZhyhName()).replace("#PW#", account.getZhyhPassword());
		}else if ("5".equals(sysId)){
			url = JYGKUrl.replace("#UN#", account.getJygkName()).replace("#PW#", account.getJygkPassword());
		}else if ("6".equals(sysId)){
			url = HRUrl;
		}else if ("7".equals(sysId)){
			url = NCUrl;
		}
		return ("{\"url\":\"" + url +"\"}").getBytes("utf-8");
	}
		
	
}
 