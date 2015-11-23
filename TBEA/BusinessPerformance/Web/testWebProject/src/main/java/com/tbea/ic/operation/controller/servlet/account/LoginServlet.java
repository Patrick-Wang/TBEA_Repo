package com.tbea.ic.operation.controller.servlet.account;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
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

import com.geong.cas.support.SystemLinkClient;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.approve.ApproveService;
import com.tbea.ic.operation.service.entry.DailyReportService;
import com.tbea.ic.operation.service.entry.EntryService;
import com.tbea.ic.operation.service.login.LoginService;

@Controller
@RequestMapping(value = "Login")
public class LoginServlet {

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	// private String view = "index";
	// private static Logger logger = Logger.getLogger(LoginServlet.class);
	@Autowired
	private EntryService entryService;

	@Autowired
	private ApproveService approveService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private DailyReportService dailyReportService;

	@RequestMapping(value = "ssoLogin.do")
	public ModelAndView ssoLogin(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = SystemLinkClient.getLink(request, "43");
		String userCode = (String) map.get("userCode");
		// Assertion assertion = (Assertion)
		// request.getSession().getAttribute("_const_csa_assertion_");
		Account account = loginService.SSOLogin(userCode);

		if (null != account) {
			setAuthority(request.getSession(), account);
			request.getSession().setAttribute("sso", true);
			return new ModelAndView("redirect:/Login/index.do");
		}

		return new ModelAndView("login");
	}

	// @RequestMapping(value = "ssoLogout.do")
	// public void ssoLogout(HttpServletRequest request,
	// HttpServletResponse response) throws IOException {
	// HttpSession session = request.getSession(false);
	// if (null != session){
	// session.invalidate();
	// }
	// response.sendRedirect("http://192.168.10.68:10008/cas/logout");
	// }

	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (SessionManager.isOnline(session)) {
			return new ModelAndView("redirect:/Login/index.do");
		}
		return new ModelAndView("login");
	}

	@RequestMapping(value = "logout.do", method = RequestMethod.GET)
	public @ResponseBody byte[] logout(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		HttpSession session = request.getSession(false);
		Boolean isSSOLogin = false;
		if (null != session) {
			isSSOLogin = null != request.getSession().getAttribute("sso");
			session.invalidate();
		}

		AjaxRedirect ajaxRedirect;
		if (isSSOLogin) {
			ajaxRedirect = new AjaxRedirect(
					"http://192.168.10.68:10008/cas/logout");
		} else {
			ajaxRedirect = new AjaxRedirect();
		}
		return ajaxRedirect.toUtf8Bytes();
		// return "{\"error\" : \"invalidate session\", \"redirect\" : \"\"}";
	}

	private void setAuthority(HttpSession session, Account account) {
		SessionManager.setAccount(session, account);

		session.setAttribute("entryPlan",
				entryService.hasEntryPlanPermission(account));

		session.setAttribute("entryPredict",
				entryService.hasEntryPredictPermission(account));

		session.setAttribute("approvePlan",
				approveService.hasApprovePlanPermission(account));

		session.setAttribute("approvePredict",
				approveService.hasApprovePredictPermission(account));

		session.setAttribute("CorpAuth", loginService.hasCorpAuth(account));

		session.setAttribute("SbdAuth", loginService.hasSbdAuth(account));

		session.setAttribute("MarketAuth",
				entryService.hasMarketPermission(account));

		session.setAttribute("MarketAuth",
				entryService.hasMarketPermission(account));

		session.setAttribute("isJydw",
				dailyReportService.hasYszkAuthority(account));

		session.setAttribute("JYAnalysisEntry",
				dailyReportService.hasJYAnalysisEntryAuthority(account));

		session.setAttribute("JYAnalysisLookup",
				dailyReportService.hasJYAnalysisLookupAuthority(account));

		session.setAttribute("isByq", account.getId() == 9
				|| account.getId() == 25 || account.getId() == 33);

		session.setAttribute("isXl", account.getId() == 43
				|| account.getId() == 50 || account.getId() == 61);
		
		session.setAttribute("isSbdcy", account.getId() == 8);

	}

	@RequestMapping(value = "validate.do")
	public ModelAndView validate(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "j_username") String j_username,
			@RequestParam(value = "j_password") String j_password) {

		Account account = loginService.Login(j_username, j_password);
		if (null != account) {

			HttpSession currentSession = request.getSession(false);
			if (null != currentSession) {
				currentSession.invalidate();
			}

			HttpSession newSession = request.getSession(true);

			setAuthority(newSession, account);

			return new ModelAndView("redirect:/Login/index.do");

		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", true);
			return new ModelAndView("login", map);
		}
	}

	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public ModelAndView getIndex(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession currentSession = request.getSession(false);

		Map<String, Object> map = new HashMap<String, Object>();
		Account account = SessionManager.getAccount(currentSession);

		map.put("zhAuth", "众和公司".equals(account.getName()));

		map.put("sbqgb", "qgb".equals(account.getName()));

		map.put("entryPlan", currentSession.getAttribute("entryPlan"));

		map.put("entryPredict", currentSession.getAttribute("entryPredict"));

		map.put("approvePlan", currentSession.getAttribute("approvePlan"));

		map.put("approvePredict", currentSession.getAttribute("approvePredict"));

		map.put("CorpAuth", currentSession.getAttribute("CorpAuth"));

		map.put("SbdAuth", currentSession.getAttribute("SbdAuth"));

		map.put("MarketAuth", currentSession.getAttribute("MarketAuth"));

		map.put("userName", account.getName());

		map.put("admin", "admin".equals(account.getName()));

		return new ModelAndView("index", map);

	}
}
