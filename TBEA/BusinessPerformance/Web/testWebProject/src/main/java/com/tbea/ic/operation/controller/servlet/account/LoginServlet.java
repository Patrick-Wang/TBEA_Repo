package com.tbea.ic.operation.controller.servlet.account;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.model.entity.User;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.approve.ApproveService;
import com.tbea.ic.operation.service.entry.EntryService;
import com.tbea.ic.operation.service.login.LoginService;

@Controller
@RequestMapping(value = "Login")
public class LoginServlet {

	private String view = "index";

	@Autowired
	private EntryService entryService;

	@Autowired
	private ApproveService approveService;

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public ModelAndView importBLHT(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "j_username") String j_username,
			@RequestParam(value = "j_password") String j_password) {

		Map<String, Object> map = new HashMap<String, Object>();

		Account account = loginService.Login(j_username, j_password);
		if (null != account) {
			HttpSession currentSession = request.getSession(false);
			if (null != currentSession) {
				currentSession.invalidate();
			}

			HttpSession newSession = request.getSession(true);
			newSession.setAttribute("account", account);

			newSession.setAttribute("entryPlan",
					entryService.hasEntryPlanPermission(account));

			newSession.setAttribute("entryPredict",
					entryService.hasEntryPredictPermission(account));

			newSession.setAttribute("approvePlan",
					approveService.hasApprovePlanPermission(account));

			newSession.setAttribute("approvePredict",
					approveService.hasApprovePredictPermission(account));

			map.put("entryPlan", newSession.getAttribute("entryPlan"));

			map.put("entryPredict", newSession.getAttribute("entryPredict"));

			map.put("approvePlan", newSession.getAttribute("approvePlan"));

			map.put("approvePredict", newSession.getAttribute("approvePredict"));

			
//			if ("qgb".equals(j_username)){
//				map.put("sbqgb", true);
//			}else {
//				map.put("sbqgb", false);
//			}
			
			return new ModelAndView(view, map);
		} else {
			map.put("error", true);
		}

		return new ModelAndView("login", map);

	}
}
