package com.tbea.ic.operation.controller.servlet.account;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.frame.script.el.ELParser;
import com.frame.script.el.ElContext;
import com.xml.frame.report.util.xml.XmlUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.Url;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager.OnSessionChangedListener;
import com.tbea.ic.operation.model.entity.ExtendAuthority.AuthType;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.model.entity.navigator.NavigatorItem;
import com.tbea.ic.operation.service.approve.ApproveService;
import com.tbea.ic.operation.service.entry.DailyReportService;
import com.tbea.ic.operation.service.entry.EntryService;
import com.tbea.ic.operation.service.extendauthority.ExtendAuthorityService;
import com.tbea.ic.operation.service.login.LoginService;
import com.tbea.ic.operation.service.navigator.NavigatorService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "Login")
public class LoginServlet implements OnSessionChangedListener {

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
	NavigatorService navigatorService;
	
	@Autowired
	public void init(){
		SessionManager.onChange(this);
	}
	
	@RequestMapping(value = {"ssoLogin.do", "v2/ssoLogin.do"})
	public ModelAndView ssoLogin(HttpServletRequest request,
			HttpServletResponse response) {

		String userName = request.getHeader("SSO_USER");
		Account account = loginServ.SSOLogin(userName);

		if (null != account) {
			if (Url.isV2(request)) {
				setAuthority2(request.getSession(), account);
			}else {
				setAuthority(request.getSession(), account);
			}
			request.getSession().setAttribute("sso", true);
			return new ModelAndView(Url.parse(request.getServletPath()).lastReplace(0, "index.do").redirectUrl());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", true);
		return new ModelAndView("login", map);
	}

	@RequestMapping(value = {"login.do", "v2/login.do"}, method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (SessionManager.isOnline(session)) {
			return new ModelAndView(Url.parse(request.getServletPath()).lastReplace(0, "index.do").redirectUrl());
		}

		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "login");
	}

	@RequestMapping(value = {"exitSystem.do", "v2/exitSystem.do"}, method = RequestMethod.GET)
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
					"http://soa1.tbea.com/oam/server/logout?end_url=http://soa.tbea.com");
		} else {
			ajaxRedirect = new AjaxRedirect();
		}
		return ajaxRedirect.toUtf8Bytes();
	}
	
	private void setAuthority(HttpSession session, Account account) {
		ACL acl = new ACL();
		SessionManager.setAccount(session, account);
		SessionManager.setAcl(session, acl);
		boolean yszkrb = false;
		Logic lookup = new Logic(false);
		Logic entry = new Logic(false);
		Logic jyfxEntry = new Logic(false);
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
		.add("isJydw", jyfxEntry.or(drServ.hasYszkAuthority(account)))
		.add("JYAnalysisEntry", drServ.hasJYAnalysisEntryAuthority(account))
		.add("JYAnalysisSummary", drServ.hasJYAnalysisLookupAuthority(account))
		.add("YSZKDialyLookup", yszkrb = (drServ.hasYSZKDialyLookupAuthority(account) ||
				extAuthServ.hasAuthority(account, 28) ||
				extAuthServ.hasAuthority(account, 27)))
		.add("XJLDialyLookup", drServ.hasXJLDialyLookupAuthority(account))
		.add("JYAnalysisLookup", yszkrb || drServ.hasJYAnalysisLookupAuthority(account)
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
		.add("NYzbscqkEntry", entry.or(extAuthServ.hasAuthority(account, AuthType.NYzbscqkEntry)))
		.add("QualityEntry", extAuthServ.hasAuthority(account, AuthType.QualityEntry))
		.add("QualityApprove", extAuthServ.hasAuthority(account, AuthType.QualityApprove) ||
				extAuthServ.hasAuthority(account, 53) ||
				extAuthServ.hasAuthority(account, 54) ||
				extAuthServ.hasAuthority(account, 55))
		.add("QualityLookup", extAuthServ.hasAuthority(account, AuthType.QualityLookup))
		.add("FinanceLookup", extAuthServ.hasAuthority(account, AuthType.FinanceLookup))
		.add("FinanceEntry", entry.or(extAuthServ.hasAuthority(account, AuthType.FinanceEntry)))
		.add("GbEntry", entry.value())
		.add("ComGbLookup", comGbLookup.value())
		.add("ComGbEntry", comGbEntry.value())
		.add("GbEntry", entry.value())
		.add("isByq", account.getId() == 9 || account.getId() == 25 || account.getId() == 33)
		.add("isXl", account.getId() == 43 || account.getId() == 50 || account.getId() == 61)
		.add("isSbdcy",	account.getId() == 8 || account.getId() == 6
						|| account.getId() == 147 || account.getId() == 119
						|| account.getId() == 146)
		.add("zhAuth", extAuthServ.hasAuthority(account, 81))
		.add("admin", extAuthServ.hasAuthority(account, 80))
		.add("debug", false)
		.add("xnyJyfxEntryAuth", jyfxEntry.or(extAuthServ.hasAuthority(account, 25)||
				extAuthServ.hasAuthority(account, 29) ||
				extAuthServ.hasAuthority(account, 31)))
		.add("xnyJyfxLookupAuth", extAuthServ.hasAuthority(account, 26)||
				extAuthServ.hasAuthority(account, 30) ||
				extAuthServ.hasAuthority(account, 32))
		.add("zhJyfxLookupAuth", extAuthServ.hasAuthority(account, 34))
		.add("xtnyrbEntryAuth", jyfxEntry.or(extAuthServ.hasAuthority(account, 35)))
		.add("xtnyrbLookupAuth", extAuthServ.hasAuthority(account, 36))
		.add("QualityAuth", account.getRole() == 3 || account.getRole() == 4)
		.add("I_EQualityImport", jyfxEntry.or(extAuthServ.hasAuthority(account, 78)))
		.add("jyfxEntry", jyfxEntry.value())
		.add("scgsdbqx", extAuthServ.hasAuthority(account, 56))
		.add("scbsjLookup", extAuthServ.hasAuthority(account, 57))
		.add("scbsjEntry", extAuthServ.hasAuthority(account, 58))
		.add("gcyzbLookup", extAuthServ.hasAuthority(account, 59))
		.add("gcyzbImport", extAuthServ.hasAuthority(account, 60))
		.add("sddbLookup", extAuthServ.hasAuthority(account, 61))
		.add("sddbImport", extAuthServ.hasAuthority(account, 62))
		.add("zhzlEntry", extAuthServ.hasAuthority(account, 63))
		.add("zhzlLookup", extAuthServ.hasAuthority(account, 64))
		.add("zk", extAuthServ.hasAuthority(account, 65))
		.add("rl", extAuthServ.hasAuthority(account, 66))
		.add("xcp", extAuthServ.hasAuthority(account, 67))
		.add("rlImport", extAuthServ.hasAuthority(account, 68));
	}

	private void setAuthority2(HttpSession session, Account account) {
		ACL acl = new ACL();
		SessionManager.setAccount(session, account);
		SessionManager.setAcl(session, acl);
		acl
		.add("entryPlan", entryServ.hasEntryPlanPermission(account))
		.add("entryPredict", entryServ.hasEntryPredictPermission(account))
		.add("approvePlan", approveServ.hasApprovePlanPermission(account))
		.add("approvePredict", approveServ.hasApprovePredictPermission(account))
		.add("CorpAuth", loginServ.hasCorpAuth(account))
		.add("SbdAuth", loginServ.hasSbdAuth(account))
		.add("MarketAuth", entryServ.hasMarketPermission(account))
		.add("QualityAuth", account.getRole() == 3 || account.getRole() == 4);
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

	@RequestMapping(value = "v2/validate.do")
	public ModelAndView validate2(HttpServletRequest request,
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

			setAuthority2(newSession, account);

			return new ModelAndView("redirect:/Login/v2/index.do");

		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", true);
			return new ModelAndView("ui2/pages/login", map);
		}
	}
	
	@RequestMapping(value = {"index.do"}, method = RequestMethod.GET)
	public ModelAndView getIndex(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession currentSession = request.getSession(false);
		
		Map<String, Object> map = new HashMap<String, Object>();
		Account account = SessionManager.getAccount(currentSession);
		setAuthority(currentSession, account);
		map.put("userName", account.getName());
		for (Integer auth : extAuthServ.getAuths(account)){
			map.put("_" + auth, true);
		}
		
		//map.put("auths", auths);
		SessionManager.getAcl(currentSession).select(map);

		return new ModelAndView("index", map);

	}
	
	@RequestMapping(value = "v2/index.do", method = RequestMethod.GET)
	public ModelAndView getIndexV2(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession currentSession = request.getSession(false);

		Map<String, Object> map = new HashMap<String, Object>();
		Account account = SessionManager.getAccount(currentSession);
		map.put("userName", account.getName());
		List<Integer> auths = extAuthServ.getAuths(account);
		for (Integer auth : auths){
			map.put("_" + auth, true);
		}
		ACL acl = SessionManager.getAcl(currentSession);
		acl.select(map);

		List<NavigatorItem> navItems = this.navigatorService.getNaviItems(auths, acl.getOpenAuths());
		ELParser elp = new ELParser(new ElContext(){

			@Override
			public Object onGetObject(String key) {
				return hasObject(key) ? System.getProperty(key) : null;
			}

			@Override
			public boolean hasObject(String key) {
				if (null != key && !"".equals(key)){
					return System.getProperties().containsKey(key);
				}
				return false;
			}

			@Override
			public void storeObject(String key, Object obj) {

			}
		});
		for (NavigatorItem item : navItems){
			if (item.getUrl() != null){
                try {
                    item.setUrl((String) XmlUtil.parseELText(item.getUrl(), elp));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
		}
		map.put("naviItems", JSONArray.fromObject(navItems));
		return new ModelAndView("ui2/home", map);
	}

	@Override
	public void onCreated(HttpSession session) {
		extAuthServ.removeCache(SessionManager.getAccount(session));
	}

	@Override
	public void onDestroyed(HttpSession session) {
		if (SessionManager.isOnline(session)){
			loginServ.logout(
					SessionManager.getAccount(session), 
					session.getCreationTime(), 
					session.getLastAccessedTime(),
					(String) session.getAttribute("remoteIP"),
					(JSONArray)session.getAttribute("reqs"));
		}else{
			LoggerFactory.getLogger("ACCOUNT").info("onDestroyed is not online");
		}
		extAuthServ.removeCache(SessionManager.getAccount(session));
	}
}
