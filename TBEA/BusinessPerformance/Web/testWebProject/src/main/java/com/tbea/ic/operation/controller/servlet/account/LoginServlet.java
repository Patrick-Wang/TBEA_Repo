package com.tbea.ic.operation.controller.servlet.account;

import java.io.IOException;
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

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.approve.ApproveService;
import com.tbea.ic.operation.service.entry.DailyReportService;
import com.tbea.ic.operation.service.entry.EntryService;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
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

	@Autowired
	ExtendAuthorityService extendAuthService;

	@RequestMapping(value = "ssoLogin.do")
	public ModelAndView ssoLogin(HttpServletRequest request,
			HttpServletResponse response) {

		String userName = request.getHeader("SSO_USER");
		Account account = loginService.SSOLogin(userName);

		if (null != account) {
			setAuthority(request.getSession(), account);
			request.getSession().setAttribute("sso", true);
			return new ModelAndView("redirect:/Login/index.do");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", true);
		return new ModelAndView("login", map);
	}

	@RequestMapping(value = "ssoLogout.do")
	public void ssoLogout(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		if (null != session) {
			session.invalidate();
		}
//		response.sendRedirect("http://192.168.10.68:10008/cas/logout");
	}

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
					"http://dbdev.tbea.com:14100/oam/server/logout?end_url=http://dbdev.tbea.com/tbeacloud/webcenter/view/index.jsp");
		} else {
			ajaxRedirect = new AjaxRedirect();
		}
		return ajaxRedirect.toUtf8Bytes();
	}

	private void setAuthority(HttpSession session, Account account) {
		ACL acl = new ACL();
		SessionManager.setAccount(session, account);
		SessionManager.setAcl(session, acl);
		
		acl.add("entryPlan", entryService.hasEntryPlanPermission(account))
		.add("entryPredict", entryService.hasEntryPredictPermission(account))
		.add("approvePlan", approveService.hasApprovePlanPermission(account))
		.add("approvePredict", approveService.hasApprovePredictPermission(account))
		.add("CorpAuth", loginService.hasCorpAuth(account))
		.add("SbdAuth", loginService.hasSbdAuth(account))
		.add("MarketAuth", entryService.hasMarketPermission(account))
		.add("isJydw", dailyReportService.hasYszkAuthority(account))
		.add("JYAnalysisEntry", dailyReportService.hasJYAnalysisEntryAuthority(account))
		.add("JYAnalysisSummary", dailyReportService.hasJYAnalysisLookupAuthority(account))
		.add("YSZKDialyLookup", dailyReportService.hasYSZKDialyLookupAuthority(account))
		.add("XJLDialyLookup", dailyReportService.hasXJLDialyLookupAuthority(account))
		.add("JYAnalysisLookup", dailyReportService.hasJYAnalysisLookupAuthority(account)
								|| dailyReportService.hasYSZKDialyLookupAuthority(account))
		.add("JYEntryLookup", dailyReportService.hasJYEntryLookupAuthority(account))
		.add("PriceLibAuth", extendAuthService.hasAuthority(account, AuthType.PriceLib));


		boolean hasGbLookupAuthority = false;
				
		boolean hasAuthority = extendAuthService.hasAuthority(account, AuthType.ChgbLookup);
		hasGbLookupAuthority = hasGbLookupAuthority || hasAuthority;
		acl.add("ChgbLookup", hasAuthority);
		hasAuthority = extendAuthService.hasAuthority(account, AuthType.YszkgbLookup);
		hasGbLookupAuthority = hasGbLookupAuthority || hasAuthority;
		acl.add("YszkgbLookup", hasAuthority);
		hasAuthority = extendAuthService.hasAuthority(account, AuthType.WlyddLookup);
		hasGbLookupAuthority = hasGbLookupAuthority || hasAuthority;
		acl.add("WlyddLookup", hasAuthority);

		boolean hasGbEntryAuthority = false;
		
		hasAuthority = extendAuthService.hasAuthority(account, AuthType.ChgbEntry);
		hasGbEntryAuthority = hasGbEntryAuthority || hasAuthority;
		acl.add("ChgbEntry", hasAuthority);
		hasAuthority = extendAuthService.hasAuthority(account, AuthType.YszkgbEntry);
		hasGbEntryAuthority = hasGbEntryAuthority || hasAuthority;
		acl.add("YszkgbEntry", hasAuthority);
		hasAuthority = extendAuthService.hasAuthority(account,	AuthType.WlyddEntry);
		hasGbEntryAuthority = hasGbEntryAuthority || hasAuthority;
		acl.add("WlyddEntry", hasAuthority);
		
		acl.add("GbLookup", hasGbLookupAuthority)
			.add("GbEntry", hasGbEntryAuthority)
			.add("isByq", account.getId() == 9 || account.getId() == 25 || account.getId() == 33)
			.add("isXl", account.getId() == 43 || account.getId() == 50 || account.getId() == 61)
			.add("isSbdcy",	account.getId() == 8 || account.getId() == 6
						|| account.getId() == 147 || account.getId() == 119
						|| account.getId() == 146)
			.add("zhAuth", Account.KNOWN_ACCOUNT_ZHGS.equals(account.getName()))
			.add("notSbqgb", !Account.KNOWN_ACCOUNT_QGB.equals(account.getName()))
			.add("admin", Account.KNOWN_ACCOUNT_ADMIN.equals(account.getName()))
			.add("debug", false);
		
		if (Account.KNOWN_ACCOUNT_AFL.equals(account.getName())){
			acl.openAll();
			acl.close("MarketAuth");
		}
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
		map.put("userName", account.getName());
		
		SessionManager.getAcl(currentSession).select(map);
				

		return new ModelAndView("index", map);

	}
}
