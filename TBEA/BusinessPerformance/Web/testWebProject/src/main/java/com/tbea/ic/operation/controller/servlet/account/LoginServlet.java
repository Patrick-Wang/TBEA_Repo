package com.tbea.ic.operation.controller.servlet.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "Login")
public class LoginServlet {

	private String view = "homePage";

	private String commandName = "result";

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public ModelAndView importBLHT(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "j_username") String j_username,
			@RequestParam(value = "j_password") String j_password) {
		System.out.println("j_username : " + j_username);
		System.out.println("j_password : " + j_password);
		return new ModelAndView(view, commandName, true);
	}
}
