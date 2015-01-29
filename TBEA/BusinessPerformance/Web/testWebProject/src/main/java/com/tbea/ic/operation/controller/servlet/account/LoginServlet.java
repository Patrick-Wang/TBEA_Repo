package com.tbea.ic.operation.controller.servlet.account;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.model.entity.User;
import com.tbea.ic.operation.service.approve.ApproveService;
import com.tbea.ic.operation.service.entry.EntryService;

@Controller
@RequestMapping(value = "Login")
public class LoginServlet {

	private String view = "index";
	
	@Autowired
	private EntryService service;
	
	@Autowired
	private ApproveService approveService;

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public ModelAndView importBLHT(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "j_username") String j_username,
			@RequestParam(value = "j_password") String j_password) {
		System.out.println("j_username : " + j_username);
		System.out.println("j_password : " + j_password);
		Map<String, Object> map = new HashMap<String, Object>();
		User usr = new User();
		boolean hasPermission = service.hasEntryPlanPermission(usr);
		map.put("entryPlan", true);
		hasPermission = service.hasEntryPredictPermission(usr);
		map.put("entryPredict", true);
		hasPermission = approveService.hasApprovePlanPermission(usr);
		map.put("approvePlan", true);
		hasPermission = approveService.hasApprovePredictPermission(usr);
		map.put("approvePredict", true);
		return new ModelAndView(view, map);
	}
}
