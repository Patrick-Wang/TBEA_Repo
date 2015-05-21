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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.approve.ApproveService;
import com.tbea.ic.operation.service.entry.EntryService;
import com.tbea.ic.operation.service.login.LoginService;

@Controller
@RequestMapping(value = "Login")
public class LoginServlet {

	//private String view = "index";
	//private static Logger logger = Logger.getLogger(LoginServlet.class);
	@Autowired
	private EntryService entryService;

	@Autowired
	private ApproveService approveService;

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getSession(false) != null){
			return new ModelAndView("redirect:/Login/index.do");
		}
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "logout.do", method = RequestMethod.GET)
	public @ResponseBody String logout(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession newSession = request.getSession(false);
		if (null != newSession){
			newSession.invalidate();
		}

		return "{\"error\" : \"invalidate session\", \"redirect\" : \"\"}";
	}
	
	@RequestMapping(value = "validate.do", method = RequestMethod.POST)
	public ModelAndView validate(HttpServletRequest request,
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
			
			newSession.setAttribute("CorpAuth",
					loginService.hasCorpAuth(account));
			
			newSession.setAttribute("SbdAuth",
					loginService.hasSbdAuth(account));
			
			return new ModelAndView("redirect:/Login/index.do", map);

		} else {
			map.put("error", true);
			return new ModelAndView("login", map);
		}
	}
	
	
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public ModelAndView getIndex(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession currentSession = request.getSession(false);

		Map<String, Object> map = new HashMap<String, Object>();
		Account account = (Account) currentSession.getAttribute("account");
		
		map.put("sbqgb", account.getName().equals("qgb"));
		
		map.put("entryPlan", currentSession.getAttribute("entryPlan"));

		map.put("entryPredict", currentSession.getAttribute("entryPredict"));

		map.put("approvePlan", currentSession.getAttribute("approvePlan"));

		map.put("approvePredict", currentSession.getAttribute("approvePredict"));
		
		map.put("CorpAuth", currentSession.getAttribute("CorpAuth"));

		map.put("SbdAuth", currentSession.getAttribute("SbdAuth"));
		
		map.put("userName", account.getName());
		
		map.put("admin", "admin".equals(account.getName()));
		
		return new ModelAndView("index", map);
	
	}
}
