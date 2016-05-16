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
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager.OnSessionChangedListener;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.approve.ApproveService;
import com.tbea.ic.operation.service.entry.DailyReportService;
import com.tbea.ic.operation.service.entry.EntryService;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.tbea.ic.operation.service.login.LoginService;

@Controller
@RequestMapping(value = "Login")
public class LoginServlet implements OnSessionChangedListener {

	class Logic{
		boolean ret = false;
		
		public Logic(boolean ret) {
			super();
			this.ret = ret;
		}
		public boolean or(boolean val){
			ret = ret || val;
			return val;
		}
		public boolean and(boolean val){
			ret = ret && val;
			return val;
		}
		public boolean value(){
			return ret;
		}
	}
	
	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;

	// private String view = "index";
	// private static Logger logger = Logger.getLogger(LoginServlet.class);
	@Autowired
	private EntryService entryServ;

	@Autowired
	private ApproveService approveServ;

	@Autowired
	private LoginService loginServ;

	@Autowired
	private DailyReportService drServ;

	@Autowired
	ExtendAuthorityService extAuthServ;

	@Autowired
	public void init(){
		SessionManager.onChange(this);
	}
	
	@RequestMapping(value = "ssoLogin.do")
	public ModelAndView ssoLogin(HttpServletRequest request,
			HttpServletResponse response) {

		String userName = request.getHeader("SSO_USER");
		Account account = loginServ.SSOLogin(userName);

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

	@RequestMapping(value = "exitSystem.do", method = RequestMethod.GET)
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
		
		Logic lookup = new Logic(false);
		Logic entry = new Logic(false);
		Logic comGbLookup = new Logic(false);
		Logic comGbEntry = new Logic(false);
		acl
		.add("entryPlan", entryServ.hasEntryPlanPermission(account))
		.add("entryPredict", entryServ.hasEntryPredictPermission(account))
		.add("approvePlan", approveServ.hasApprovePlanPermission(account))
		.add("approvePredict", approveServ.hasApprovePredictPermission(account))
		.add("CorpAuth", loginServ.hasCorpAuth(account))
		.add("SbdAuth", loginServ.hasSbdAuth(account))
		.add("MarketAuth", entryServ.hasMarketPermission(account))
		.add("isJydw", drServ.hasYszkAuthority(account))
		.add("JYAnalysisEntry", drServ.hasJYAnalysisEntryAuthority(account))
		.add("JYAnalysisSummary", drServ.hasJYAnalysisLookupAuthority(account))
		.add("YSZKDialyLookup", drServ.hasYSZKDialyLookupAuthority(account))
		.add("XJLDialyLookup", drServ.hasXJLDialyLookupAuthority(account))
		.add("JYAnalysisLookup", drServ.hasJYAnalysisLookupAuthority(account)
								|| drServ.hasYSZKDialyLookupAuthority(account))
		.add("JYEntryLookup", drServ.hasJYEntryLookupAuthority(account))
		.add("PriceLibAuth", extAuthServ.hasAuthority(account, AuthType.PriceLib))
		.add("ChgbLookup", comGbLookup.or(lookup.or(extAuthServ.hasAuthority(account, AuthType.ChgbLookup))))
		.add("YszkgbLookup", comGbLookup.or(lookup.or(extAuthServ.hasAuthority(account, AuthType.YszkgbLookup))))
		.add("SbdgbLookup", lookup.or(extAuthServ.hasAuthority(account, AuthType.SbdgbLookup)))
		.add("XnygbLookup", lookup.or(extAuthServ.hasAuthority(account, AuthType.XnygbLookup)))
		.add("NygbLookup", lookup.or(extAuthServ.hasAuthority(account, AuthType.NygbLookup)))
		.add("SbdgbLookup", lookup.or(extAuthServ.hasAuthority(account, AuthType.SbdgbLookup)))
		.add("ChgbEntry", comGbEntry.or(entry.or(extAuthServ.hasAuthority(account, AuthType.ChgbEntry))))
		.add("YszkgbEntry", comGbEntry.or(entry.or(extAuthServ.hasAuthority(account, AuthType.YszkgbEntry))))
		.add("SbdgbEntry", entry.or(extAuthServ.hasAuthority(account, AuthType.SbdgbEntry)))
		.add("XnygbEntry", entry.or(extAuthServ.hasAuthority(account, AuthType.XnygbEntry)))
		.add("NygbEntry", entry.or(extAuthServ.hasAuthority(account, AuthType.NygbEntry)))
		.add("GbLookup", lookup.value())
		.add("GbEntry", entry.value())
		.add("ComGbLookup", comGbLookup.value())
		.add("ComGbEntry", comGbEntry.value())
		.add("GbEntry", entry.value())
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

		Account account = loginServ.Login(j_username, j_password);
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

	@Override
	public void onCreated(HttpSession session) {

	}

	@Override
	public void onDestroyed(HttpSession session) {
		extAuthServ.removeCache(SessionManager.getAccount(session));
	}
}
