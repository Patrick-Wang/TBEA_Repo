package com.tbea.ic.operation.controller.servlet.validate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.service.account.AccountService;

@Controller
@RequestMapping(value = "Validate")
public class ValidateServlet {

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "validate.do")
	public ModelAndView validateAccount(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "name") String name) {

		boolean result = accountService.validateAccount(name);
		ModelAndView modelAndView = null;
		if (result) {
			modelAndView = new ModelAndView("home");
		} else {
			modelAndView = new ModelAndView("error_page");
		}
		return modelAndView;
	}

}
